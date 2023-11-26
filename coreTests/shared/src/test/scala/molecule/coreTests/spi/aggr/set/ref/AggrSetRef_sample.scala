package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_sample extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact
        all = Set(1, 2, 3, 4)
        _ <- A.B.ii(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- A.B.ii(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- A.B.ii(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.C.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact
        all = Set(1, 2, 3, 4)
        _ <- A.B.C.ii(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- A.B.C.ii(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- A.B.C.ii(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        all = Set(1, 2, 3, 4)

        _ <- A.B.ii(sample).C.ii(sample).query.get.map {
          _.map {
            case (s1, s2) =>
              all.contains(s1.head) ==> true
              all.contains(s2.head) ==> true
          }
        }
        _ <- A.B.ii(sample(1)).C.ii(sample(1)).query.get.map {
          _.map {
            case (s1, s2) =>
              all.intersect(s1).nonEmpty ==> true
              all.intersect(s2).nonEmpty ==> true
          }
        }
        _ <- A.B.ii(sample(2)).C.ii(sample(2)).query.get.map {
          _.map {
            case (s1, s2) =>
              all.intersect(s1).nonEmpty ==> true
              all.intersect(s2).nonEmpty ==> true
          }
        }
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii._A.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        all = Set(1, 2, 3, 4)

        _ <- A.B.ii(sample)._A.C.ii(sample).query.get.map {
          _.map {
            case (s1, s2) =>
              all.contains(s1.head) ==> true
              all.contains(s2.head) ==> true
          }
        }
        _ <- A.B.ii(sample(1))._A.C.ii(sample(1)).query.get.map {
          _.map {
            case (s1, s2) =>
              all.intersect(s1).nonEmpty ==> true
              all.intersect(s2).nonEmpty ==> true
          }
        }
        _ <- A.B.ii(sample(2))._A.C.ii(sample(2)).query.get.map {
          _.map {
            case (s1, s2) =>
              all.intersect(s1).nonEmpty ==> true
              all.intersect(s2).nonEmpty ==> true
          }
        }
      } yield ()
    }
  }
}