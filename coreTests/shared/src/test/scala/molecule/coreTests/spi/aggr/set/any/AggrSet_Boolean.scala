package molecule.coreTests.spi.aggr.set.any

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "boolean sets, no aggregates" - types { implicit conn =>
      for {
        _ <- Ns.i.booleanSet.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.booleanSet.query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(true, false)), // 2 rows coalesced
        ))

        // Aggregates not implemented for Sets of Boolean values

        _ <- Ns.i.booleanSet(distinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(min).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(min(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(max).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(sample).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(sample(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(count).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSet(countDistinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }
      } yield ()
    }
  }
}