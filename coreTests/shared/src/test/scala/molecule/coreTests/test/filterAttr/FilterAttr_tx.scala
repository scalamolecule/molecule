package molecule.coreTests.test.filterAttr

import molecule.base.error.ModelError
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterAttr_tx extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.s.tx(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        // Cross reference filter attributes not allowed either
        _ <- Ns.s.long(Ref.tx_).Ref.tx.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
        _ <- Ns.s.long_(Ref.tx_).Ref.tx.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.s.tx.not(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long.not(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.s.tx.<(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long.<(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.s.tx.<=(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long.<=(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.s.tx.>(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long.>(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.s.tx.>=(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }

        _ <- Ns.s.long.>=(Ns.tx).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve transaction entity ids."
          }
      } yield ()
    }
  }
}
