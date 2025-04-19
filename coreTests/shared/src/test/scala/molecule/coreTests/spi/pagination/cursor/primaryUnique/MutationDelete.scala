package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Uniques.*
import molecule.coreTests.setup.*
import scala.annotation.nowarn

@nowarn
case class MutationDelete(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {
  val x     = ""
  val query = Uniques.int.a1.query

  import api.*
  import suite.*

  "Forward: Delete row before" - unique { implicit conn =>
    for {
      List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
      c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

      // Delete row before next page
      _ <- Uniques(e2).delete.transact

      // Next page unaffected
      _ <- query.from(c).limit(2).get.map { case (List(3, 4), _, false) => () }
    } yield ()
  }

  "Forward: Delete row after" - unique { implicit conn =>
    for {
      List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
      c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

      // Delete row after this page
      _ <- Uniques(e3).delete.transact

      // Next page without deleted row
      _ <- query.from(c).limit(2).get.map { case (List(4), _, false) => () }
    } yield ()
  }


  "Backwards: Delete row before" - unique { implicit conn =>
    for {
      List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
      c <- query.from("").limit(-2).get.map { case (List(3, 4), c, true) => c }

      // Delete row before next page (backwards)
      _ <- Uniques(e3).delete.transact

      // Next page unaffected
      _ <- query.from(c).limit(-2).get.map { case (List(1, 2), _, false) => () }
    } yield ()
  }

  "Backwards: Delete row after" - unique { implicit conn =>
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