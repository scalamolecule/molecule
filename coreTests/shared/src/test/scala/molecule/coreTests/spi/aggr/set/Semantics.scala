package molecule.coreTests.spi.aggr.set

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

    "Only single card-set aggregation" - types { implicit conn =>
      for {
        _ <- Ns.s.i.iSet.intSet.insert(List(
          ("a", 1, Set(1, 2, 3), Set(1, 2, 3)),
          ("b", 1, Set(2, 3, 4), Set(2, 3, 4)),
          ("b", 2, Set(3, 4, 5), Set(3, 4, 5)),
        )).transact

        // Multiple cardinality-one aggregations ok
        _ <- Ns.s(max).i(sum).query.get.map(_ ==> List(
          ("b", 4),
        ))

        // Mixing cardinality-one/set aggregations not allowed
        _ <- Ns.i(min).iSet(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only a single aggregation is allowed with card-set attributes."
          }

        // Multiple cardinality-set aggregations not allowed
        _ <- Ns.iSet(min(2)).intSet(max(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only a single aggregation is allowed with card-set attributes."
          }
      } yield ()
    }
  }
}