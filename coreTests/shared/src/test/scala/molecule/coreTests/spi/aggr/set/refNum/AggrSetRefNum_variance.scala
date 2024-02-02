package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_variance extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Variance of unique values (Set semantics)

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.ii(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4, 3, 4))

        _ <- A.i.B.ii(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(1, 2)
          case (2, variance) => variance ==~ varianceOf(2, 3, 4, 3, 4)
        })
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.ii(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4, 3, 4))

        _ <- A.i.B.i.C.ii(variance).query.get.map(_.map {
          case (1, 1, variance) => variance ==~ varianceOf(1, 2)
          case (2, 2, variance) => variance ==~ varianceOf(2, 3, 4, 3, 4)
        })
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.ii(variance).query.get.map(_.map {
          case (1, 1, variance) => variance ==~ varianceOf(1, 2)
          case (2, 2, variance) => variance ==~ varianceOf(2, 3, 4, 3, 4)
        })
      } yield ()
    }
  }
}