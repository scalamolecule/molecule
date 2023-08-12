// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Long_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantLongEquality(toleranceLong)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.long(sum).query.get.map(_.head ==~ long1 + long2 + long3 + long4)
        _ <- Ns.i.long(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ long1 + long2
          case (2, sum) => sum ==~ long2 + long3 + long4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2, long3, long4))
        _ <- Ns.long(median).query.get.map(_.head ==~ (long2 + long3).toDouble / 2.0)

        _ <- Ns.i.long(median).query.get.map(_.map {
          case (1, median) => median ==~ (long1 + long2).toDouble / 2.0
          case (2, median) => median ==~ long3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2, long3, long4))
        _ <- Ns.long(avg).query.get.map(_.head ==~ (long1 + long2 + long3 + long4).toDouble / 4.0)

        _ <- Ns.i.long(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (long1 + long2).toDouble / 2.0
          case (2, avg) => avg ==~ (long2 + long3 + long4).toDouble / 3.0
        })

      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2, long3, long4))
        _ <- Ns.long(variance).query.get.map(_.head ==~ varianceOf(long1, long2, long3, long4))

        _ <- Ns.i.long(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(long1, long2)
          case (2, variance) => variance ==~ varianceOf(long2, long3, long4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (1, long2),
          (2, long2),
          (2, long3),
          (2, long4),
        )).transact

        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2, long3, long4))
        _ <- Ns.long(stddev).query.get.map(_.head ==~ stdDevOf(long1, long2, long3, long4))

        _ <- Ns.i.long(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(long1, long2)
          case (2, stddev) => stddev ==~ stdDevOf(long2, long3, long4)
        })
      } yield ()
    }
  }
}