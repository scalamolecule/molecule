package molecule.coreTests.spi.aggr.one.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneRefNum_stddev extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Standard deviation of distinct values (Set semantics)

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

        _ <- A.B.i(stddev).query.get.map(_.head ==~ stdDevOf(1, 2, 3, 4))

        _ <- A.i.B.i(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(1, 2)
          case (2, stddev) => stddev ==~ stdDevOf(2, 3, 4)
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

        _ <- A.B.C.i(stddev).query.get.map(_.head ==~ stdDevOf(1, 2, 3, 4))

        _ <- A.i.B.i.C.i(stddev).query.get.map(_.map {
          case (1, 1, stddev) => stddev ==~ stdDevOf(1, 2)
          case (2, 2, stddev) => stddev ==~ stdDevOf(2, 3, 4)
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

        _ <- A.i.B.i(stddev).C.i(stddev).query.get.map(_.map {
          case (1, stddevA, stddevB) =>
            stddevA ==~ stdDevOf(1, 2)
            stddevB ==~ stdDevOf(1, 2)
          case (2, stddevA, stddevB) =>
            stddevA ==~ stdDevOf(2, 3, 4)
            stddevB ==~ stdDevOf(2, 3, 4)
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

        _ <- A.i.B.i(stddev)._A.C.i(stddev).query.get.map(_.map {
          case (1, stddevA, stddevB) =>
            stddevA ==~ stdDevOf(1, 2)
            stddevB ==~ stdDevOf(1, 2)
          case (2, stddevA, stddevB) =>
            stddevA ==~ stdDevOf(2, 3, 4)
            stddevB ==~ stdDevOf(2, 3, 4)
        })
      } yield ()
    }
  }
}