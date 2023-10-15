package molecule.coreTests.spi.aggr.set.number

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.refs(sum).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "median" - types { implicit conn =>
      for {
        _ <- Ns.refs(median).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "avg" - types { implicit conn =>
      for {
        _ <- Ns.refs(avg).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "variance" - types { implicit conn =>
      for {
        _ <- Ns.refs(variance).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.refs(stddev).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }
  }
}