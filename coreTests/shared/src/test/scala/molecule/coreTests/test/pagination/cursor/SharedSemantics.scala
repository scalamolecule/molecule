package molecule.coreTests.test.pagination.cursor

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.annotation.nowarn

trait SharedSemantics extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  @nowarn lazy val tests = Tests {

    "Same query" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1, 2, 3).transact

        c1 <- Unique.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
        _ <- Unique.i_(1).int.a1.query.from(c1).limit(2).get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only use cursor for un-modified query."
        }
      } yield ()
    }


    "Premature turnaround" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1, 2, 3).transact

        c1 <- Unique.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

        // Turning around with first cursor leads nowhere
        _ <- Unique.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
      } yield ()
    }


    "Retry" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1, 2).transact

        c1 <- Unique.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, false) => c }

        // No following rows yet
        _ <- Unique.int.a1.query.from(c1).limit(2).get.map { case (Nil, _, false) => () }

        // New row
        _ <- Unique.int.insert(3).transact

        // Now there are new rows
        _ <- Unique.int.a1.query.from(c1).limit(2).get.map { case (List(3), _, false) => () }
      } yield ()
    }
  }
}