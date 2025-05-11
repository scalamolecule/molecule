package molecule.db.compliance.test.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*
import scala.annotation.nowarn
import molecule.db.compliance.domains.dsl.Uniques.*

@nowarn
case class MutationAdd(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {
  val x     = ""
  val query = Uniques.int.a1.query

  // (Allow pattern matching the result without warnings)

  import api.*
  import suite.*

  "Forward: Add row before" - unique { implicit conn =>
    for {
      _ <- Uniques.int.insert(1, 3, 5).transact
      c <- query.from("").limit(2).get.map { case (List(1, 3), c, true) => c }

      // Add row before next page
      _ <- Uniques.int.insert(2).transact

      // Next page unaffected
      _ <- query.from(c).limit(2).get.map { case (List(5), _, false) => () }
    } yield ()
  }

  "Forward: Add row after" - unique { implicit conn =>
    for {
      _ <- Uniques.int.insert(1, 3, 5).transact
      c <- query.from("").limit(2).get.map { case (List(1, 3), c, true) => c }

      // Add row after this page
      _ <- Uniques.int.insert(4).transact

      // Next page includes new row
      _ <- query.from(c).limit(2).get.map { case (List(4, 5), _, false) => () }
    } yield ()
  }


  "Backwards: Add row before" - unique { implicit conn =>
    for {
      _ <- Uniques.int.insert(1, 3, 5).transact
      c <- query.from("").limit(-2).get.map { case (List(3, 5), c, true) => c }

      // Add row before next page
      _ <- Uniques.int.insert(4).transact

      // Next page unaffected
      _ <- query.from(c).limit(-2).get.map { case (List(1), _, false) => () }
    } yield ()
  }

  "Backwards: Add row after" - unique { implicit conn =>
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