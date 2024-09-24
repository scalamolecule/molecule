package molecule.coreTests.spi.filter.set

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SetSemantics extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "equal" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet(Set(int1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSet) not supported in queries."
          }

        _ <- Ns.i.intSet_(Set(int1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSet) not supported in queries."
          }

        _ <- Ns.i.intSet_?(Some(Set(int1))).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSet) not supported in queries."
          }
      } yield ()
    }


    "equal nothing" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet_?.insert(List(
          (0, None),
          (1, Some(Set(int1, int2))),
        )).transact

        // Match non-asserted attribute (null) with tacit attribute
        _ <- Ns.i.intSet_().query.get.map(_ ==> List(0))

        // Can't query for empty attribute
        _ <- Ns.i.intSet().query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Applying nothing to mandatory attribute is reserved for updates to retract."
          }
      } yield ()
    }
  }
}