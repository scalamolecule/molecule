package molecule.db.h2.compliance.subquery

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class LimitOffset extends MUnit with DbProviders_h2 with TestUtils {

  "limit" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 4),
        (2, 5),
        (3, 6),
      ).transact

      _ <- Entity.i.select(Ref.i.a1.query.limit(1)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.i,
          |  (
          |    SELECT DISTINCT
          |      Ref.i
          |    FROM Ref
          |    WHERE
          |      Ref.i IS NOT NULL
          |    ORDER BY Ref.i
          |    LIMIT 1
          |  )
          |FROM Entity
          |WHERE
          |  Entity.i IS NOT NULL
          |ORDER BY 2;""".stripMargin
      ) ==> true)

      _ <- Entity.i.select(Ref.i.a1.query.limit(1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))

      _ <- Entity.i.select(Ref.i.d1.query.limit(1)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
    } yield ()
  }


  "limit + offset" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 4),
        (2, 5),
        (3, 6),
      ).transact

      _ <- Entity.i.select(Ref.i.a1.query.limit(1).offset(2)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.i,
          |  (
          |    SELECT DISTINCT
          |      Ref.i
          |    FROM Ref
          |    WHERE
          |      Ref.i IS NOT NULL
          |    ORDER BY Ref.i
          |    LIMIT 1
          |    OFFSET 2
          |  )
          |FROM Entity
          |WHERE
          |  Entity.i IS NOT NULL""".stripMargin
      ) ==> true)

      // Ascending
      _ <- Entity.i.select(Ref.i.a1.query.limit(1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(1).offset(0)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(1).offset(1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(1).offset(2)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))

      // Descending
      _ <- Entity.i.select(Ref.i.d1.query.limit(1)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
      _ <- Entity.i.select(Ref.i.d1.query.limit(1).offset(1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.select(Ref.i.d1.query.limit(1).offset(2)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
    } yield ()
  }


  "limit backwards" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 4),
        (2, 5),
        (3, 6),
      ).transact

      // When "going backwards" by using a negative limit ("from the end"), orderings are reversed
      _ <- Entity.i.select(Ref.i.a1.query.limit(-1)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.i,
          |  (
          |    SELECT DISTINCT
          |      Ref.i
          |    FROM Ref
          |    WHERE
          |      Ref.i IS NOT NULL
          |    ORDER BY Ref.i DESC
          |    LIMIT 1
          |  )
          |FROM Entity
          |WHERE
          |  Entity.i IS NOT NULL""".stripMargin
      ) ==> true)

      // Ascending
      _ <- Entity.i.select(Ref.i.a1.query.limit(1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(-1).offset(0)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(-1).offset(-1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.select(Ref.i.a1.query.limit(-1).offset(-2)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))

      // Descending
      _ <- Entity.i.select(Ref.i.d1.query.limit(-1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.select(Ref.i.d1.query.limit(-1).offset(0)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.select(Ref.i.d1.query.limit(-1).offset(-1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.select(Ref.i.d1.query.limit(-1).offset(-2)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
    } yield ()
  }
}
