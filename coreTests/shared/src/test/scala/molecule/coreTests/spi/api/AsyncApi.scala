package molecule.coreTests.spi.api

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait AsyncApi extends CoreTestSuite with Api_async { spi: Spi_async =>

  @nowarn override lazy val tests = Tests {

    "Crud actions" - types { implicit conn =>
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))
      } yield ()
    }


    "Validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Validation.Type

      for {
        _ <- Type.string("a").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        _ <- Type.string.insert("a").transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        id <- Type.string("c").save.transact.map(_.id)
        _ <- Type(id).string("a").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }
      } yield ()
    }


    "Inspection" - types { implicit conn =>
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids) // Need data for update and delete
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
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
        _ <- Ns.int.a1.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
      } yield ()
    }


    "Cursor query" - unique { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Uniques._
      val query = Uniques.int.a1.query
      for {
        _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact
        c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
        c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
        c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
        c4 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
        _ <- query.from(c4).limit(-2).get.map { case (List(1, 2), _, false) => () }
      } yield ()
    }


    "Subscription" - types { implicit conn =>
      var intermediaryCallbackResults = List.empty[List[Int]]
      for {
        // Initial data
        _ <- Ns.i(1).save.transact

        // Start subscription
        _ <- Ns.i.query.subscribe { freshResult =>
          intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
        }

        // Mutations to be monitored by subscription
        id <- Ns.i(2).save.transact.map(_.id)
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2))

        _ <- Ns.i.insert(3, 4).transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2, 3, 4))

        _ <- Ns(id).i(20).update.transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 3, 4, 20))

        _ <- Ns(id).delete.transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 3, 4))

        // Mutations with no callback-involved attributes don't call back
        _ <- Ns.string("foo").save.transact

        // Callback catched all intermediary results correctly
        _ = intermediaryCallbackResults.map(_.sorted).toSet ==> Set(
          List(1, 2), // query result after 2 was saved
          List(1, 2, 3, 4), // query result after 3 and 4 were inserted
          List(1, 3, 4, 20), // query result after 2 was updated to 20
          List(1, 3, 4), // query result after 20 was deleted
        )
      } yield ()
    }
  }
}

