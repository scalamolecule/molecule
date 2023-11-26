package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_avg extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Average of unique values (Set semantics)

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.ii(avg).query.get.map(_.head ==~ (1 + 2 + 3 + 4).toDouble / 4.0)

        _ <- A.i.B.ii(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (1 + 2).toDouble / 2.0
          case (2, avg) => avg ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.ii(avg).query.get.map(_.head ==~ (1 + 2 + 3 + 4).toDouble / 4.0)

        _ <- A.i.B.i.C.ii(avg).query.get.map(_.map {
          case (1, 1, avg) => avg ==~ (1 + 2).toDouble / 2.0
          case (2, 2, avg) => avg ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.B.ii(avg).C.ii(avg).query.get.map(_.map {
          case (1, avgB, avgC) =>
            avgB ==~ (1 + 2).toDouble / 2.0
            avgC ==~ (1 + 2).toDouble / 2.0
          case (2, avgB, avgC) =>
            avgB ==~ (2 + 3 + 4).toDouble / 3.0
            avgC ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii._A.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.B.ii(avg)._A.C.ii(avg).query.get.map(_.map {
          case (1, avgB, avgC) =>
            avgB ==~ (1 + 2).toDouble / 2.0
            avgC ==~ (1 + 2).toDouble / 2.0
          case (2, avgB, avgC) =>
            avgB ==~ (2 + 3 + 4).toDouble / 3.0
            avgC ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }
  }
}