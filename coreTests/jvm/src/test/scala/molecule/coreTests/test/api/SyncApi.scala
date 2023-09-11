package molecule.coreTests.test.api

import molecule.base.error._
import molecule.core.spi.SpiSync
import molecule.coreTests.api.ApiSyncImplicits
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.sync._
import utest._
import scala.annotation.nowarn

// Testing the synchronous api only on the JVM platform since the JS platform
// is dependent on asynchronous retrieval of data from the server.
trait SyncApi extends CoreTestSuite with ApiSyncImplicits { self: SpiSync =>

  @nowarn override lazy val tests = Tests {

    "Molecule synchronous api" - {

      "Crud actions" - types { implicit conn =>
        val List(a, b) = Ns.int.insert(1, 2).transact.ids
        Ns.int(3).save.transact
        Ns.int.query.get ==> List(1, 2, 3)
        Ns(a).int(10).update.transact
        Ns(b).delete.transact
        Ns.int.query.get ==> List(3, 10)
      }


      "Error handling" - validation { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Validation.Type

        intercept[ValidationErrors](
          Type.string("a").save.transact
        ) match {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
        }
        intercept[InsertErrors](
          Type.string.insert("a").transact
        ) match {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
        }

        val id = Type.string("c").save.transact.ids.head
        intercept[ValidationErrors](
          Type(id).string("a").update.transact
        ) match {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
        }
      }


      "Inspection" - types { implicit conn =>
        val List(a, b) = Ns.int.insert(1, 2).transact.ids // Need data for update and delete
        Ns.int.insert(1, 2).inspect
        Ns.int(3).save.inspect
        Ns.int.query.inspect
        Ns(a).int(10).update.inspect
        Ns(b).delete.inspect
      }


      "Offset query" - types { implicit conn =>
        Ns.int.insert(1, 2, 3).transact
        Ns.int.query.get ==> List(1, 2, 3)
        Ns.int.query.limit(2).get ==> List(1, 2)
        Ns.int.query.offset(1).get ==> (List(2, 3), 3, true)
        Ns.int.query.offset(1).limit(1).get ==> (List(2), 3, true)
      }


      "Cursor query" - unique { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Uniques._

        val query = Uniques.int.a1.query
        Uniques.int.insert(1, 2, 3, 4, 5).transact
        val c1 = query.from("").limit(2).get match {
          case (List(1, 2), c, true) => c
        }
        val c2 = query.from(c1).limit(2).get match {
          case (List(3, 4), c, true) => c
        }
        val c3 = query.from(c2).limit(2).get match {
          case (List(5), c, false) => c
        }
        val c4 = query.from(c3).limit(-2).get match {
          case (List(3, 4), c, true) => c
        }
        query.from(c4).limit(-2).get match {
          case (List(1, 2), _, false) => ()
        }
      }


      "Subscription" - types { implicit conn =>
        var intermediaryCallbackResults = List.empty[List[Int]]

        // Initial data
        Ns.i(1).save.transact

        // Start subscription
        Ns.i.query.subscribe { freshResult =>
          intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
        }

        // Mutations to be monitored by subscription
        val id = Ns.i(2).save.transact.id
        Ns.i.a1.query.get ==> List(1, 2)

        // For testing purpose, allow each mutation to finish so that we can
        // catch the intermediary callback result in order
        delay(50)(())

        Ns.i.insert(3, 4).transact
        Ns.i.a1.query.get ==> List(1, 2, 3, 4)
        delay(50)(())

        Ns(id).i(20).update.transact
        Ns.i.a1.query.get ==> List(1, 3, 4, 20)
        delay(50)(())

        Ns(id).delete.transact
        Ns.i.a1.query.get ==> List(1, 3, 4)
        delay(50)(())

        // Mutations with no callback-involved attributes don't call back
        Ns.string("foo").save.transact

        // Callback catched all intermediary results correctly
        intermediaryCallbackResults.map(_.sorted) ==> List(
          List(1, 2), // query result after 2 was saved
          List(1, 2, 3, 4), // query result after 3 and 4 were inserted
          List(1, 3, 4, 20), // query result after 2 was updated to 20
          List(1, 3, 4), // query result after 20 was deleted
        )
      }
    }
  }
}
