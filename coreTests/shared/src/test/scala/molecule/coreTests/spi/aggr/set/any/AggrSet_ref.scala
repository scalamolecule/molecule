package molecule.coreTests.spi.aggr.set.any

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.refs(distinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.refs(min).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }

        _ <- Ns.refs(min(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.refs(max).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }

        _ <- Ns.refs(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.refs(rand).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }

        _ <- Ns.refs(rand(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.refs(sample).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }

        _ <- Ns.refs(sample(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.refs(count).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }

        _ <- Ns.refs(countDistinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating Sets of ref ids not supported."
          }
      } yield ()
    }
  }
}