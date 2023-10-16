package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait MutationDelete extends CoreTestSuite with ApiAsync { spi: SpiAsync =>
  val x     = ""
  val query = Uniques.int.a1.query

  @nowarn // (Allow pattern matching the result without warnings)
  lazy val tests = Tests {

    "Forward" - {

      "Delete row before" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
          c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

          // Delete row before next page
          _ <- Uniques(e2).delete.transact

          // Next page unaffected
          _ <- query.from(c).limit(2).get.map { case (List(3, 4), _, false) => () }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
          c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

          // Delete row after this page
          _ <- Uniques(e3).delete.transact

          // Next page without deleted row
          _ <- query.from(c).limit(2).get.map { case (List(4), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Delete row before" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
          c <- query.from("").limit(-2).get.map { case (List(3, 4), c, true) => c }

          // Delete row before next page (backwards)
          _ <- Uniques(e3).delete.transact

          // Next page unaffected
          _ <- query.from(c).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
          c <- query.from("").limit(-2).get.map { case (List(3, 4), c, true) => c }

          // Delete row after this page (backwards)
          _ <- Uniques(e2).delete.transact

          // Next page without deleted row
          _ <- query.from(c).limit(-2).get.map { case (List(1), _, false) => () }
        } yield ()
      }
    }
  }
}