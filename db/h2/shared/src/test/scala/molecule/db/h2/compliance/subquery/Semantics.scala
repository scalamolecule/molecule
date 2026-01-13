package molecule.db.h2.compliance.subquery

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2


class Semantics extends MUnit with DbProviders_h2 with TestUtils {


  "tacit/mandatory attribute" - types {
    for {
      _ <- Entity.s.i.Ref.i.s.insert(
        ("a", 1, 2, "x"),
        ("b", 4, 3, "y"),
      ).transact

      // Filter attribute (tacit) - points to outer Ref.i
      _ <- Entity.s.i.>(Ref.i_).Ref.i.s.query.get.map(_ ==> List(
        ("b", 4, 3, "y"),
      ))

      // Filter attribute should be tacit
      _ <- Entity.s.i.>(Ref.i).Ref.i.s
        .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attribute Ref.i should be tacit."
        }

      // Note that when a compared mandatory attribute (Ref.i) points to no outer attribute,
      // a cross join with a subquery is build instead with no correlation to the outer query.
      _ <- Entity.s.i.>(Ref.i).query.get.map(_ ==> List(
        ("b", 4, 2), // Ref.i == 2
        ("b", 4, 3), // Ref.i == 3
      ))

      // Subquery without correlation is repeated for all outer rows
      _ <- Entity.s.select(Ref.id(count)).query.get.map(_ ==> List(
        ("a", 2), // Ref.id(count) is 2 for all outer rows
        ("b", 2),
      ))
    } yield ()
  }


  "empty sub rows filtered out" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 3),
      ).transact

      _ <- Ref.i.insert(7).transact

      _ <- Ref.i.select(Entity.i_?.ref_(Ref.id_)).query.get.map(_ ==> List(
        (1, Some(1)),
        (3, Some(2)),
        (7, None), // correct
      ))
      _ <- Ref.i.select(Entity.i.ref_(Ref.id_)).query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
        //        (7, null), // excluded
      ))
    } yield ()
  }

  "empty sub rows still aggregated" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()),
      ).transact

      _ <- Entity.s.select(Ref.id(count).i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (2, 3)),
        ("b", (3, 12)),
        ("c", (0, 0)), // Both subqueries return default value 0 for empty relationship
      ))
    } yield ()
  }


  // https://github.com/com-lihaoyi/scalasql/blob/main/docs/tutorial.md#subqueries
  "ScalaSql example 1" - types {
    for {
      // Entity ~ countrylanguage,
      // Entity.s ~ countrylanguage.countrycode,
      // Entity.string ~ countrylanguage.language,
      // Entity.i ~ country.population
      _ <- Entity.s.string.i.insert(
        ("dk", "Denmark", 5500000),
        ("cn", "China", 1400000000),
        ("in", "India", 1450000000),
      ).transact

      // Ref ~ country
      // Ref.s ~ country.code
      // Ref.string ~ country.name
      _ <- Ref.s.string.insert(
        ("dk", "Danish"),
        ("cn", "Chinese"),
        ("cn", "Dong"),
        ("in", "Asami"),
        ("in", "Bengali"),
        ("in", "Gujarati")
      ).transact

      /*
      ScalaSql DSL query:
      CountryLanguage.select
        .join(Country.select.sortBy(_.population).desc.take(2))(_.countryCode === _.code)
        .map { case (language, country) => (language.language, country.name) }
        .sortBy(_._1)

      which generates

      SELECT countrylanguage0.language AS res_0, subquery1.name AS res_1
      FROM countrylanguage countrylanguage0
               JOIN (SELECT
                         country1.code AS code,
                         country1.name AS name,
                         country1.population AS population
                     FROM country country1
                     ORDER BY population DESC
                         LIMIT ?) subquery1
                    ON (countrylanguage0.countrycode = subquery1.code)
      ORDER BY res_0
       */
      _ <- Ref.string.a1.join(Entity.string.i.d1.s_(Ref.s_).query.limit(2)).query.get.map(_ ==> List(
        ("Asami", ("India", 1450000000)),
        ("Bengali", ("India", 1450000000)),
        ("Chinese", ("China", 1400000000)),
        ("Dong", ("China", 1400000000)),
        ("Gujarati", ("India", 1450000000))
      ))

      _ <- Ref.string.a1.join(Entity.string.i.d1.s_(Ref.s_).query.limit(2)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Ref.string,
          |  subquery1.string,
          |  subquery1.i
          |FROM Ref INNER JOIN (
          |    SELECT DISTINCT
          |      Entity.string,
          |      Entity.i,
          |      Entity.s
          |    FROM Entity
          |    WHERE
          |      Entity.string IS NOT NULL AND
          |      Entity.i      IS NOT NULL
          |    ORDER BY Entity.i DESC
          |    LIMIT 2
          |  ) subquery1 ON Ref.s = subquery1.s
          |WHERE
          |  Ref.string IS NOT NULL
          |ORDER BY Ref.string, subquery1.i DESC;""".stripMargin
      ) ==> true)
    } yield ()
  }
}
