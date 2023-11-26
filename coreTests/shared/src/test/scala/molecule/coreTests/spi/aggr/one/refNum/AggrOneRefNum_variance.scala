package molecule.coreTests.spi.aggr.one.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneRefNum_variance extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Variance of distinct values (Set semantics)

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

        _ <- A.B.i(variance).query.get.map(_.head ==~ varianceOf(1, 2, 3, 4))

        _ <- A.i.B.i(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(1, 2)
          case (2, variance) => variance ==~ varianceOf(2, 3, 4)
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

        _ <- A.B.C.i(variance).query.get.map(_.head ==~ varianceOf(1, 2, 3, 4))

        _ <- A.i.B.i.C.i(variance).query.get.map(_.map {
          case (1, 1, variance) => variance ==~ varianceOf(1, 2)
          case (2, 2, variance) => variance ==~ varianceOf(2, 3, 4)
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

        _ <- A.i.B.i(variance).C.i(variance).query.get.map(_.map {
          case (1, varianceA, varianceB) =>
            varianceA ==~ varianceOf(1, 2)
            varianceB ==~ varianceOf(1, 2)
          case (2, varianceA, varianceB) =>
            varianceA ==~ varianceOf(2, 3, 4)
            varianceB ==~ varianceOf(2, 3, 4)
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

        _ <- A.i.B.i(variance)._A.C.i(variance).query.get.map(_.map {
          case (1, varianceA, varianceB) =>
            varianceA ==~ varianceOf(1, 2)
            varianceB ==~ varianceOf(1, 2)
          case (2, varianceA, varianceB) =>
            varianceA ==~ varianceOf(2, 3, 4)
            varianceB ==~ varianceOf(2, 3, 4)
        })
      } yield ()
    }
  }
}