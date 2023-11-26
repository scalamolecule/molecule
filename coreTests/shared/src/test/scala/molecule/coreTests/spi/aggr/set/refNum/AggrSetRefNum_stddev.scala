package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_stddev extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Standard deviation of unique values (Set semantics)

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

        _ <- A.B.ii(stddev).query.get.map(_.head ==~ stdDevOf(1, 2, 3, 4))

        _ <- A.i.B.ii(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(1, 2)
          case (2, stddev) => stddev ==~ stdDevOf(2, 3, 4)
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

        _ <- A.B.C.ii(stddev).query.get.map(_.head ==~ stdDevOf(1, 2, 3, 4))

        _ <- A.i.B.i.C.ii(stddev).query.get.map(_.map {
          case (1, 1, stddev) => stddev ==~ stdDevOf(1, 2)
          case (2, 2, stddev) => stddev ==~ stdDevOf(2, 3, 4)
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

        _ <- A.i.B.ii(stddev).C.ii(stddev).query.get.map(_.map {
          case (1, stddevB, stddevC) =>
            stddevB ==~ stdDevOf(1, 2)
            stddevC ==~ stdDevOf(1, 2)
          case (2, stddevB, stddevC) =>
            stddevB ==~ stdDevOf(2, 3, 4)
            stddevC ==~ stdDevOf(2, 3, 4)
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

        _ <- A.i.B.ii(stddev)._A.C.ii(stddev).query.get.map(_.map {
          case (1, stddevB, stddevC) =>
            stddevB ==~ stdDevOf(1, 2)
            stddevC ==~ stdDevOf(1, 2)
          case (2, stddevB, stddevC) =>
            stddevB ==~ stdDevOf(2, 3, 4)
            stddevC ==~ stdDevOf(2, 3, 4)
        })
      } yield ()
    }
  }
}