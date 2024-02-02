package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_sum extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Sum of unique values (Set semantics)

  override lazy val tests = Tests {
    val all  = 1 + 2 + 2 + 3 + 4 + 3 + 4
    val all2 = 2 + 3 + 4 + 3 + 4

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.ii(sum).query.get.map(_.head.head ==~ all)

        _ <- A.i.B.ii(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ 1 + 2
          case (2, setWithSum) => setWithSum.head ==~ all2
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.ii(sum).query.get.map(_.head.head ==~ all)

        _ <- A.i.B.i.C.ii(sum).query.get.map(_.map {
          case (1, 1, setWithSum) => setWithSum.head ==~ 1 + 2
          case (2, 2, setWithSum) => setWithSum.head ==~ all2
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.ii(sum).query.get.map(_.map {
          case (1, 1, setWithSum) => setWithSum.head ==~ 1 + 2
          case (2, 2, setWithSum) => setWithSum.head ==~ all2
        })
      } yield ()
    }
  }
}