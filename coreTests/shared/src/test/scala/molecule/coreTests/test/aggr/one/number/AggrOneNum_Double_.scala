// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.double(sum).query.get.map(_.head ==~ double1 + double2 + double3 + double4)
        _ <- Ns.i.double(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ double1 + double2
          case (2, sum) => sum ==~ double2 + double3 + double4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2, double3, double4))
        _ <- Ns.double(median).query.get.map(_.head ==~ (double2 + double3).toDouble / 2.0)

        _ <- Ns.i.double(median).query.get.map(_.map {
          case (1, median) => median ==~ (double1 + double2).toDouble / 2.0
          case (2, median) => median ==~ double3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2, double3, double4))
        _ <- Ns.double(avg).query.get.map(_.head ==~ (double1 + double2 + double3 + double4).toDouble / 4.0)

        _ <- Ns.i.double(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (double1 + double2).toDouble / 2.0
          case (2, avg) => avg ==~ (double2 + double3 + double4).toDouble / 3.0
        })

      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2, double3, double4))
        _ <- Ns.double(variance).query.get.map(_.head ==~ varianceOf(double1, double2, double3, double4))

        _ <- Ns.i.double(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(double1, double2)
          case (2, variance) => variance ==~ varianceOf(double2, double3, double4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (1, double2),
          (2, double2),
          (2, double3),
          (2, double4),
        )).transact

        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2, double3, double4))
        _ <- Ns.double(stddev).query.get.map(_.head ==~ stdDevOf(double1, double2, double3, double4))

        _ <- Ns.i.double(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(double1, double2)
          case (2, stddev) => stddev ==~ stdDevOf(double2, double3, double4)
        })
      } yield ()
    }
  }
}