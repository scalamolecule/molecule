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

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.ii(sum).query.get.map(_.head.head ==~ 1 + 2 + 3 + 4)

        _ <- A.i.B.ii(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ 1 + 2
          case (2, setWithSum) => setWithSum.head ==~ 2 + 3 + 4
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.ii(sum).query.get.map(_.head.head ==~ 1 + 2 + 3 + 4)

        _ <- A.i.B.i.C.ii(sum).query.get.map(_.map {
          case (1, 1, setWithSum) => setWithSum.head ==~ 1 + 2
          case (2, 2, setWithSum) => setWithSum.head ==~ 2 + 3 + 4
        })
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

        _ <- A.i.B.ii(sum).C.ii(sum).query.get.map(_.map {
          case (1, sumB, sumC) =>
            sumB.head ==~ 1 + 2
            sumC.head ==~ 1 + 2
          case (2, sumB, sumC) =>
            sumB.head ==~ 2 + 3 + 4
            sumC.head ==~ 2 + 3 + 4
        })
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

        _ <- A.i.B.ii(sum)._A.C.ii(sum).query.get.map(_.map {
          case (1, sumB, sumC) =>
            sumB.head ==~ 1 + 2
            sumC.head ==~ 1 + 2
          case (2, sumB, sumC) =>
            sumB.head ==~ 2 + 3 + 4
            sumC.head ==~ 2 + 3 + 4
        })
      } yield ()
    }
  }
}