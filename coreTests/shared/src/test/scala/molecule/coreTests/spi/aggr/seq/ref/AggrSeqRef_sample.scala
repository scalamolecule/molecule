package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_sample extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact
        all = Set(1, 2, 3, 4)
        _ <- A.B.iSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- A.B.iSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- A.B.iSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.C.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact
        all = Set(1, 2, 3, 4)
        _ <- A.B.C.iSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- A.B.C.iSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- A.B.C.iSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        all = Set(1, 2, 3, 4)

        _ <- A.B.i._A.C.iSet(sample).query.get.map {
          _.map {
            case (_, set) => all.contains(set.head) ==> true
          }
        }
        _ <- A.B.i._A.C.iSet(sample(1)).query.get.map {
          _.map {
            case (_, set) => all.intersect(set).nonEmpty ==> true
          }
        }
        _ <- A.B.i._A.C.iSet(sample(2)).query.get.map {
          _.map {
            case (_, set) => all.intersect(set).nonEmpty ==> true
          }
        }
      } yield ()
    }
  }
}