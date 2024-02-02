package molecule.coreTests.spi.aggr.one.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneRefNum_avg extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Average of distinct values (Set semantics)

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (2, 2),
          (2, 3),
          (2, 4),
        )).transact

        // Average of all (non-coalesced) values
        _ <- A.B.i(avg).query.get.map(_.head ==~ (1 + 2 + 2 + 3 + 4).toDouble / 5.0)

        _ <- A.i.B.i(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (1 + 2).toDouble / 2.0
          case (2, avg) => avg ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 1, 2),
          (2, 2, 2),
          (2, 2, 3),
          (2, 2, 4),
        )).transact

        _ <- A.B.C.i(avg).query.get.map(_.head ==~ (1 + 2 + 2 + 3 + 4).toDouble / 5.0)
        _ <- A.i.a1.B.i.C.i(avg).query.get.map(_.map {
          case (1, 1, avg1) => avg1 ==~ (1 + 2).toDouble / 2.0
          case (2, 2, avg2) => avg2 ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (2, 2, 2),
          (2, 3, 3),
          (2, 4, 4),
        )).transact

        _ <- A.i.B.i(avg).C.i(avg).query.get.map(_.map {
          case (1, avgA, avgB) =>
            avgA ==~ (1 + 2).toDouble / 2.0
            avgB ==~ (1 + 2).toDouble / 2.0
          case (2, avgA, avgB) =>
            avgA ==~ (2 + 3 + 4).toDouble / 3.0
            avgB ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (2, 2, 2),
          (2, 3, 3),
          (2, 4, 4),
        )).transact

        _ <- A.i.B.i(avg)._A.C.i(avg).query.get.map(_.map {
          case (1, avgA, avgB) =>
            avgA ==~ (1 + 2).toDouble / 2.0
            avgB ==~ (1 + 2).toDouble / 2.0
          case (2, avgA, avgB) =>
            avgA ==~ (2 + 3 + 4).toDouble / 3.0
            avgB ==~ (2 + 3 + 4).toDouble / 3.0
        })
      } yield ()
    }
  }
}