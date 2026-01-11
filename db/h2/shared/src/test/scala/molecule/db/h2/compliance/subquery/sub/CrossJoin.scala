package molecule.db.h2.compliance.subquery.sub

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.spi.Conn
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

//import molecule.core.dataModel.*

class CrossJoin extends MUnit with DbProviders_h2 with TestUtils {


  "Only tacit filter attributes allowed" - types {
    for {
      _ <- Entity.s.i.Ref.i.s.insert(
        ("a", 1, 2, "x"),
        ("b", 4, 3, "y"),
      ).transact

      _ <- Entity.s.i.>(Ref.i).Ref.i.s
        .query.i.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attribute Ref.i should be tacit."
        }

      // Note that when a compared mandatory attribute (Ref.i) points to no outer attribute,
      // a cross join with a subquery is build instead with no correlation to the outer query.
      _ <- Entity.s.i.>(Ref.i).query.i.get.map(_ ==> List(
        ("b", 4, 2), // Ref.i == 2
        ("b", 4, 3), // Ref.i == 3
      ))
    } yield ()
  }

  //  "Non-correlated" - types {
  //    for {
  //      _ <- Entity.s.Refs.*(Ref.i).insert(
  //        ("a", List(1, 2)),
  //        ("b", List(3, 4, 5)),
  //      ).transact
  //
  //      // Subquery without correlation is repeated for all outer rows
  //      _ <- Entity.s.sub(Ref.id(count)).query.i.get.map(_ ==> List(
  //        ("a", 5),
  //        ("b", 5),
  //      ))
  //
  //      // Subquery that correlates to outer entity returns result for each outer row
  //      _ <- Entity.s.sub(Ref.id(count).entity(Entity.id_)).query.i.get.map(_ ==> List(
  //        ("a", (2, 1)), // (count, entity_id)
  //        ("b", (3, 2)),
  //      ))
  //    } yield ()
  //  }
  //
  //
  //  "cross join" - types {
  //    for {
  //      _ <- Entity.i.Ref.i.insert(
  //        (1, 1),
  //        (2, 3),
  //      ).transact
  //
  //      _ <- Ref.i.insert(7).transact
  //      _ <- Other.i.insert(2, 4).transact
  //
  //      // Normal relationship (and no comparison)
  //      _ <- Entity.i.Ref.i.query.get.map(_ ==> List(
  //        (1, 1),
  //        (2, 3),
  //      ))
  //
  //      // Subquery matching related
  //      _ <- Entity.i.apply(Ref.i).query.i.get.map(_ ==> List(
  //        (1, 1),
  //      ))
  //      // Subquery matching unrelated
  //      _ <- Entity.i.apply(Other.i).query.i.get.map(_ ==> List(
  //        (2, 2),
  //      ))
  //
  //      // Filter attribute - more to write, but a bit more efficient than subquery
  //      _ <- Entity.i.apply(Ref.i_).Ref.i.query.i.get.map(_ ==> List(
  //        (1, 1),
  //      ))
  //
  //      _ <- Ref.i.sub(Entity.i.ref_.apply(Ref.id_)).query.get.map(_ ==> List(
  //        (1, 1),
  //        (3, 2),
  //        (7, 0), // Entity.i seems to default to 0 (jdbc Integer default?) - this is confusing...
  //      ))
  //
  //      // Explicit cross join to subquery
  //      _ <- Entity.i.sub(Ref.i).query.i.get.map(_ ==> List(
  //        (1, 1),
  //        (1, 3),
  //        (2, 1),
  //        (2, 3),
  //      ))
  //      _ <- Entity.i.sub(Other.i).query.i.get.map(_ ==> List(
  //        (1, 2),
  //        (1, 4),
  //        (2, 2),
  //        (2, 4),
  //      ))
  //    } yield ()
  //  }
}
