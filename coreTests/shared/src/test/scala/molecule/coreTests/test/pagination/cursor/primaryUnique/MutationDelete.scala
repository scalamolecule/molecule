package molecule.coreTests.test.pagination.cursor.primaryUnique

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.annotation.nowarn

trait MutationDelete extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>
  val x     = ""
  val query = Unique.int.a1.query

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Delete row before" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Unique.int.insert(1, 2, 3, 4).transact.map(_.eids)
          c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

          // Delete row before next page
          _ <- Unique(e2).delete.transact

          // Next page unaffected
          _ <- query.from(c).limit(2).get.map { case (List(3, 4), _, false) => () }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Unique.int.insert(1, 2, 3, 4).transact.map(_.eids)
          c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

          // Delete row after this page
          _ <- Unique(e3).delete.transact

          // Next page without deleted row
          _ <- query.from(c).limit(2).get.map { case (List(4), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Delete row before" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Unique.int.insert(1, 2, 3, 4).transact.map(_.eids)
          c <- query.from("").limit(-2).get.map { case (List(3, 4), c, true) => c }

          // Delete row before next page (backwards)
          _ <- Unique(e3).delete.transact

          // Next page unaffected
          _ <- query.from(c).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        for {
          List(e1, e2, e3, e4) <- Unique.int.insert(1, 2, 3, 4).transact.map(_.eids)
          c <- query.from("").limit(-2).get.map { case (List(3, 4), c, true) => c }

          // Delete row after this page (backwards)
          _ <- Unique(e2).delete.transact

          // Next page without deleted row
          _ <- query.from(c).limit(-2).get.map { case (List(1), _, false) => () }
        } yield ()
      }
    }
  }
}