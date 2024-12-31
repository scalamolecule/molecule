package molecule.coreTests.spi.pagination.cursor

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Uniques._
import molecule.coreTests.setup._
import scala.annotation.nowarn

@nowarn
case class SharedSemantics(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Same query" - unique { implicit conn =>
    for {
      _ <- Uniques.int.insert(1, 2, 3).transact

      c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
      _ <- Uniques.i_(1).int.a1.query.from(c1).limit(2).get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only use cursor for un-modified query."
        }
    } yield ()
  }


  "Premature turnaround" - unique { implicit conn =>
    for {
      _ <- Uniques.int.insert(1, 2, 3).transact

      c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

      // Turning around with first cursor leads nowhere
      _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
    } yield ()
  }


  "Retry" - unique { implicit conn =>
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