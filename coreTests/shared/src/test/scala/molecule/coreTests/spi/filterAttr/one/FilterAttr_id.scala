package molecule.coreTests.spi.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterAttr_id extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Can't use entity ids with filter attributes

  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.s.id(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        // Cross reference filter attributes not allowed either
        _ <- Ns.s.long(Ref.id_).Ref.id.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
        _ <- Ns.s.long_(Ref.id_).Ref.id.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.s.id.not(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long.not(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.s.id.<(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long.<(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.s.id.<=(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long.<=(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.s.id.>(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long.>(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.s.id.>=(Ns.long).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }

        _ <- Ns.s.long.>=(Ns.id).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
      } yield ()
    }
  }
}
