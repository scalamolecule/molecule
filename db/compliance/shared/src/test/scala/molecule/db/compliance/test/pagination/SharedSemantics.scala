package molecule.db.compliance.test.pagination

import scala.annotation.nowarn
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class SharedSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Same query" - unique {
    for {
      _ <- Uniques.int.insert(1, 2, 3).transact

      c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
      _ <- Uniques.i_(1).int.a1.query.from(c1).limit(2).get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only use cursor for un-modified query."
        }
    } yield ()
  }


  "Premature turnaround" - unique {
    for {
      _ <- Uniques.int.insert(1, 2, 3).transact

      c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

      // Turning around with first cursor leads nowhere
      _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
    } yield ()
  }


  "Retry" - unique {
    for {
      _ <- Uniques.int.insert(1, 2).transact

      c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, false) => c }

      // No following rows yet
      _ <- Uniques.int.a1.query.from(c1).limit(2).get.map { case (Nil, _, false) => () }

      // New row
      _ <- Uniques.int.insert(3).transact

      // Now there are new rows
      _ <- Uniques.int.a1.query.from(c1).limit(2).get.map { case (List(3), _, false) => () }
    } yield ()
  }
}