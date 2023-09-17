package molecule.coreTests.test.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantIntEquality(toleranceInt)
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        _ <- Ns.ints(sum).query.get.map(_.head.head ==~ int1 + int2 + int3 + int4)

        _ <- Ns.i.ints(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ int1 + int2
          case (2, setWithSum) => setWithSum.head ==~ int2 + int3 + int4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.query.get.map(_ ==> List(Set(int1, int2, int3, int4)))
        _ <- Ns.ints(median).query.get.map(_.head ==~ (int2 + int3).toDouble / 2.0)

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
        _ <- Ns.i.ints(median).query.get.map(_.map {
          case (1, median) => median ==~ (int1 + int2).toDouble / 2.0
          case (2, median) => median ==~ int3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.query.get.map(_ ==> List(Set(int1, int2, int3, int4)))
        _ <- Ns.ints(avg).query.get.map(_.head ==~ (int1 + int2 + int3 + int4).toDouble / 4.0)

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
        _ <- Ns.i.ints(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (int1 + int2).toDouble / 2.0
          case (2, avg) => avg ==~ (int2 + int3 + int4).toDouble / 3.0
        })
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.query.get.map(_ ==> List(Set(int1, int2, int3, int4)))
        _ <- Ns.ints(variance).query.get.map(_.head ==~ varianceOf(int1, int2, int3, int4))

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
        _ <- Ns.i.ints(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(int1, int2)
          case (2, variance) => variance ==~ varianceOf(int2, int3, int4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.ints.query.get.map(_ ==> List(Set(int1, int2, int3, int4)))
        _ <- Ns.ints(stddev).query.get.map(_.head ==~ stdDevOf(int1, int2, int3, int4))

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
        _ <- Ns.i.ints(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(int1, int2)
          case (2, stddev) => stddev ==~ stdDevOf(int2, int3, int4)
        })
      } yield ()
    }
  }
}