package molecule.coreTests.spi.aggr.seq.any

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "boolean seqs, no aggregates" - types { implicit conn =>
      for {
        _ <- Ns.i.booleanSeq.insert(List(
          (1, List(true)),
          (2, List(false)),
          (2, List(true, false))
        )).transact

        // Non-aggregated card-many Seq of attribute values (doesn't coalesce as Sets)
        _ <- Ns.i.a1.booleanSeq.query.get.map(_ ==> List(
          (1, List(true)),
          (2, List(false)),
          (2, List(true, false))
        ))

        // Counts allowed (makes sense with list of booleans)

        _ <- Ns.i.booleanSeq(count).a1.query.get.map(_ ==> List(
          (1, 1),
          (2, 3),
        ))
        _ <- Ns.i.booleanSeq(count).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 1),
        ))

        _ <- Ns.i.booleanSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 1),
          (2, 2),
        ))
        _ <- Ns.i.booleanSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 2),
          (1, 1),
        ))


        // Aggregates not implemented for Sets of Boolean values

        _ <- Ns.i.booleanSeq(distinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(min).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(min(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(max).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(sample).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

        _ <- Ns.i.booleanSeq(sample(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }
      } yield ()
    }
  }
}