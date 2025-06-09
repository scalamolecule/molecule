package molecule.db.sql.h2

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.sql.h2.async.*
import molecule.db.sql.h2.setup.DbProviders_h2

class Adhoc_h2_js_async extends Test with DbProviders_h2 with TestUtils {


  //  "types" - types { implicit conn =>
  //    for {
  //      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
  //      _ <- Entity.int(3).save.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
  //      _ <- Entity(a).int(10).update.transact
  //      _ <- Entity(b).delete.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
  //    } yield ()
  //  }

  "Mutations call back" - types { implicit conn =>
    var intermediaryResults = List.empty[List[Int]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact

      // Start subscription and define a callback function
      _ <- Entity.i.query.subscribe { updatedResult =>
        // Do something with updated result
        intermediaryResults = intermediaryResults :+ updatedResult.sorted
      }

      // When calling from ScalaJS, calls are asynchronous, and we need to wait
      // a bit for the subscription websocket to be ready to serve callbacks.
      _ <- delay(1000)

      // Mutations to be monitored by subscription
      id <- Entity.i(2).save.transact.map(_.id)
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2))

      _ <- Entity.i.insert(3, 4).transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2, 3, 4))

      _ <- Entity(id).i(20).update.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4, 20))

      _ <- Entity(id).delete.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4))

      // Mutations with no callback-involved attributes don't call back
      _ <- Entity.string("foo").save.transact

      // Callback produced all intermediary results correctly
      _ = intermediaryResults ==> List(
        List(1, 2), //        query result after 2 was saved
        List(1, 2, 3, 4), //  query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), //     query result after 20 was deleted
      )
    } yield ()
  }

  //      "refs" - refs { implicit conn =>
  //        import molecule.db.compliance.domains.dsl.Refs._
  //        for {
  //
  //          _ <- A.i(1).save.transact
  //          _ <- A.i(2).B.s("b").save.transact
  //          _ <- A.i(3).B.s("c").i(3).save.transact
  //
  //          // Current entity with A value and ref to B value
  //          _ <- A.i.a1.B.i.query.get.map(_ ==> List(
  //            (3, 3)
  //          ))
  //
  //          // Filter by A value, update existing B values
  //          _ <- A.i_.B.i(4).update.transact
  //
  //          _ <- A.i.a1.B.i.query.get.map(_ ==> List(
  //            (3, 4) // B value updated since there was a previous value
  //          ))
  //
  //          // Filter by A ids, upsert B values (insert if not already present)
  //          _ <- A.i_.B.i(5).upsert.i.transact
  //
  //          // Now three A entities with referenced B value
  //          _ <- A.i.a1.B.i.query.get.map(_ ==> List(
  //            (1, 5), // relationship to B created and B value inserted
  //            (2, 5), // B value inserted
  //            (3, 5), // B value updated
  //          ))
  //
  //        } yield ()
  //      }
  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //
  //        id <- MandatoryAttr.name("Bob").age(42)
  //          .hobbies(Set("golf")).save.transact.map(_.id)
  //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        //        // We can remove a value from a Set as long as it's not the last value
  //        //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
  //
  //        // Can't remove the last value of a mandatory attribute Set of values
  //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                  |  MandatoryAttr.hobbies
  //                  |""".stripMargin
  //          }
  //
  //      } yield ()
  //    }
}
