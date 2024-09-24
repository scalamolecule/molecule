package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait MutationAdd extends CoreTestSuite with Api_async { spi: Spi_async =>
  val x     = ""
  val query = Uniques.int.a1.query

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Add row before" - unique { implicit conn =>
        for {
          _ <- Uniques.int.insert(1, 3, 5).transact
          c <- query.from("").limit(2).get.map { case (List(1, 3), c, true) => c }

          // Add row before next page
          _ <- Uniques.int.insert(2).transact

          // Next page unaffected
          _ <- query.from(c).limit(2).get.map { case (List(5), _, false) => () }
        } yield ()
      }

      "Add row after" - unique { implicit conn =>
        for {
          _ <- Uniques.int.insert(1, 3, 5).transact
          c <- query.from("").limit(2).get.map { case (List(1, 3), c, true) => c }

          // Add row after this page
          _ <- Uniques.int.insert(4).transact

          // Next page includes new row
          _ <- query.from(c).limit(2).get.map { case (List(4, 5), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Add row before" - unique { implicit conn =>
        for {
          _ <- Uniques.int.insert(1, 3, 5).transact
          c <- query.from("").limit(-2).get.map { case (List(3, 5), c, true) => c }

          // Add row before next page
          _ <- Uniques.int.insert(4).transact

          // Next page unaffected
          _ <- query.from(c).limit(-2).get.map { case (List(1), _, false) => () }
        } yield ()
      }

      "Add row after" - unique { implicit conn =>
        for {
          _ <- Uniques.int.insert(1, 3, 5).transact
          c <- query.from("").limit(-2).get.map { case (List(3, 5), c, true) => c }

          // Add row after this page
          _ <- Uniques.int.insert(2).transact

          // Next page includes new row
          _ <- query.from(c).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }
    }
  }
}