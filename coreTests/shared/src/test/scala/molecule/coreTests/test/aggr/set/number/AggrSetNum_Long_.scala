// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantLongEquality(toleranceLong)
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        _ <- Ns.longs(sum).query.get.map(_.head.head ==~ long1 + long2 + long3 + long4)

        _ <- Ns.i.longs(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ long1 + long2
          case (2, setWithSum) => setWithSum.head ==~ long2 + long3 + long4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs.query.get.map(_ ==> List(Set(long1, long2, long3, long4)))
        _ <- Ns.longs(median).query.get.map(_.head ==~ (long2 + long3).toDouble / 2.0)

        _ <- Ns.i.a1.longs.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
        _ <- Ns.i.longs(median).query.get.map(_.map {
          case (1, median) => median ==~ (long1 + long2).toDouble / 2.0
          case (2, median) => median ==~ long3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs.query.get.map(_ ==> List(Set(long1, long2, long3, long4)))
        _ <- Ns.longs(avg).query.get.map(_.head ==~ (long1 + long2 + long3 + long4).toDouble / 4.0)

        _ <- Ns.i.a1.longs.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
        _ <- Ns.i.longs(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (long1 + long2).toDouble / 2.0
          case (2, avg) => avg ==~ (long2 + long3 + long4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs.query.get.map(_ ==> List(Set(long1, long2, long3, long4)))
        _ <- Ns.longs(variance).query.get.map(_.head ==~ varianceOf(long1, long2, long3, long4))

        _ <- Ns.i.a1.longs.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
        _ <- Ns.i.longs(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(long1, long2)
          case (2, variance) => variance ==~ varianceOf(long2, long3, long4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs.query.get.map(_ ==> List(Set(long1, long2, long3, long4)))
        _ <- Ns.longs(stddev).query.get.map(_.head ==~ stdDevOf(long1, long2, long3, long4))

        _ <- Ns.i.a1.longs.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
        _ <- Ns.i.longs(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(long1, long2)
          case (2, stddev) => stddev ==~ stdDevOf(long2, long3, long4)
        })
      } yield ()
    }
  }
}