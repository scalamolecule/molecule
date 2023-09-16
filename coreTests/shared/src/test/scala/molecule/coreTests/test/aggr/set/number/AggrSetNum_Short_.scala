// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantShortEquality(toleranceShort)
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        _ <- Ns.shorts(sum).query.get.map(_.head.head ==~ short1 + short2 + short3 + short4)

        _ <- Ns.i.shorts(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ short1 + short2
          case (2, setWithSum) => setWithSum.head ==~ short2 + short3 + short4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts.query.get.map(_ ==> List(Set(short1, short2, short3, short4)))
        _ <- Ns.shorts(median).query.get.map(_.head ==~ (short2 + short3).toDouble / 2.0)

        _ <- Ns.i.shorts.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
        _ <- Ns.i.shorts(median).query.get.map(_.map {
          case (1, median) => median ==~ (short1 + short2).toDouble / 2.0
          case (2, median) => median ==~ short3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts.query.get.map(_ ==> List(Set(short1, short2, short3, short4)))
        _ <- Ns.shorts(avg).query.get.map(_.head ==~ (short1 + short2 + short3 + short4).toDouble / 4.0)

        _ <- Ns.i.shorts.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
        _ <- Ns.i.shorts(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (short1 + short2).toDouble / 2.0
          case (2, avg) => avg ==~ (short2 + short3 + short4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts.query.get.map(_ ==> List(Set(short1, short2, short3, short4)))
        _ <- Ns.shorts(variance).query.get.map(_.head ==~ varianceOf(short1, short2, short3, short4))

        _ <- Ns.i.shorts.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
        _ <- Ns.i.shorts(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(short1, short2)
          case (2, variance) => variance ==~ varianceOf(short2, short3, short4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts.query.get.map(_ ==> List(Set(short1, short2, short3, short4)))
        _ <- Ns.shorts(stddev).query.get.map(_.head ==~ stdDevOf(short1, short2, short3, short4))

        _ <- Ns.i.shorts.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
        _ <- Ns.i.shorts(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(short1, short2)
          case (2, stddev) => stddev ==~ stdDevOf(short2, short3, short4)
        })
      } yield ()
    }
  }
}