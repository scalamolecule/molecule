package molecule.coreTests.test.pagination.cursor

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait SharedSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  @nowarn lazy val tests = Tests {

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
}