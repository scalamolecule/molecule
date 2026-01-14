package molecule.db.compliance.test.pagination.cursor.primaryUnique

import scala.annotation.nowarn
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class MutationDelete(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {
  val x     = ""
  val query = Uniques.int.a1.query

  import api.*
  import suite.*

  "Delete row before" - unique {
    for {
      List(e1, e2, e3, e4) <- Uniques.int.insert(1, 2, 3, 4).transact.map(_.ids)
      c <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

      // Delete row before next page
      _ <- Uniques(e2).delete.transact

      // Next page unaffected
      _ <- query.from(c).limit(2).get.map { case (List(3, 4), _, false) => () }
    } yield ()
  }

  "Delete row after" - unique {
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