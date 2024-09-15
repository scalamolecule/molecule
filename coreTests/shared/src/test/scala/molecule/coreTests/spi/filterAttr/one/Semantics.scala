package molecule.coreTests.spi.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Semantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Missing filter attributes" - types { implicit conn =>
      for {
        _ <- Ns.int(Ref.int_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add missing filter attribute Ref.int"
          }
      } yield ()
    }


    "No expression in cross-ns definition" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ref.int_.not(3)).Ref.int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filtering inside cross-namespace attribute filter not allowed."
          }
      } yield ()
    }


    "Can't filter by same attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ns.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't filter by the same attribute `Ns.i`"
          }
      } yield ()
    }
  }
}
