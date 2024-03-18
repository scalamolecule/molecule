package molecule.coreTests.spi.aggr.seq

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Semantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Only single card-seq aggregation" - types { implicit conn =>
      for {
        _ <- Ns.s.i.longSeq.intSeq.insert(List(
          ("a", 1, Seq(1L, 2L, 3L), Seq(1, 2, 3)),
          ("b", 1, Seq(2L, 3L, 4L), Seq(2, 3, 4)),
          ("b", 2, Seq(3L, 4L, 5L), Seq(3, 4, 5)),
        )).transact

        // Multiple cardinality-one aggregations ok
        _ <- Ns.s(max).i(sum).query.get.map(_ ==> List(
          ("b", 4),
        ))

        // Mixing cardinality-one/set aggregations not allowed
        _ <- Ns.i(min).intSeq(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only a single aggregation is allowed with card-set attributes."
          }

        // Multiple cardinality-set aggregations not allowed
        _ <- Ns.longSeq(min(2)).intSeq(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only a single aggregation is allowed with card-set attributes."
          }
      } yield ()
    }
  }
}