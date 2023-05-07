package molecule.datalog.datomic.test.api

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.nowarn


object AsyncApi extends DatomicTestSuite {

  @nowarn lazy val tests = Tests {

    "Molecule asynchronous api" - {

      "Crud actions" - types { implicit conn =>
        for {
          List(a, b) <- Ns.int.insert(1, 2).transact.map(_.eids)
          _ <- Ns.int(3).save.transact
          _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
          _ <- Ns(a).int(10).update.transact
          _ <- Ns(b).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(3, 10))
        } yield ()
      }


      "Error handling" - validation { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Validation.Type

        for {
          _ <- Type.string("a").save.transact
            .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
          }

          _ <- Type.string.insert("a").transact
            .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
          }

          eid <- Type.string("c").save.transact.map(_.eid)
          _ <- Type(eid).string("a").update.transact
            .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
          }
        } yield ()
      }


      "Inspection" - types { implicit conn =>
        for {
          List(a, b) <- Ns.int.insert(1, 2).transact.map(_.eids) // Need data for update and delete
          _ <- Ns.int.insert(1, 2).inspect
          _ <- Ns.int(3).save.inspect
          _ <- Ns.int.query.inspect
          _ <- Ns(a).int(10).update.inspect
          _ <- Ns(b).delete.inspect
        } yield ()
      }


      "Offset query" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1, 2, 3).transact
          _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
          _ <- Ns.int.query.limit(2).get.map(_ ==> List(1, 2))
          _ <- Ns.int.query.offset(1).get.map(_ ==> (List(2, 3), 3, true))
          _ <- Ns.int.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
        } yield ()
      }


      "Cursor query" - unique { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Unique.Unique
        val query = Unique.int.a1.query
        for {
          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact
          c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
          c4 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
          _ <- query.from(c4).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }


      "Subscription" - types { implicit conn =>
        var intermediaryResults = List.empty[List[Int]]
        for {
          // Initial data (not pushed)
          _ <- Ns.i(1).save.transact

          // Start subscription in separate thread on server
          _ = Ns.i.query.subscribe { freshResult =>
            intermediaryResults = intermediaryResults :+ freshResult
          }
          // Wait for subscription thread to startup to propagate first result
          _ <- delay(500)(())

          // Make changes to generate new results to be pushed
          _ <- Ns.i(2).save.transact
          _ <- Ns.i(3).save.transact

          // Not affecting subscription since it doesn't mach subscription query (Ns.i)
          _ <- Ns.string("foo").save.transact

          // Wait for subscription thread to propagate last result
          _ <- delay(100)(())

          _ = intermediaryResults ==> List(
            List(1, 2), // query result after 2 was added
            List(1, 2, 3), // query result after 3 was added
          )
        } yield ()
      }
    }
  }
}
