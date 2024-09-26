package molecule.coreTests.spi.filter.seq

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SeqSemantics extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "equal" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq(Seq(int1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSeq) not supported in queries."
          }

        _ <- Ns.i.intSeq_(Seq(int1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSeq) not supported in queries."
          }

        _ <- Ns.i.intSeq_?(Some(Seq(int1))).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intSeq) not supported in queries."
          }
      } yield ()
    }


    "equal nothing" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq_?.insert(List(
          (0, None),
          (1, Some(Seq(int1, int2))),
        )).transact

        // Match non-asserted attribute (null) with tacit attribute
        _ <- Ns.i.intSeq_().query.get.map(_ ==> List(0))

        // Can't query for empty attribute
        _ <- Ns.i.intSeq().query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Applying nothing to mandatory attribute (Ns.intSeq) is reserved for updates to retract."
          }
      } yield ()
    }
  }
}