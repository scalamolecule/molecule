package molecule.coreTests.test.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantIntEquality(toleranceInt)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.int(sum).query.get.map(_.head ==~ int1 + int2 + int3 + int4)
        _ <- Ns.i.int(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ int1 + int2
          case (2, sum) => sum ==~ int2 + int3 + int4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3, int4))
        _ <- Ns.int(median).query.get.map(_.head ==~ (int2 + int3).toDouble / 2.0)

        _ <- Ns.i.int(median).query.get.map(_.map {
          case (1, median) => median ==~ (int1 + int2).toDouble / 2.0
          case (2, median) => median ==~ int3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3, int4))
        _ <- Ns.int(avg).query.get.map(_.head ==~ (int1 + int2 + int3 + int4).toDouble / 4.0)

        _ <- Ns.i.int(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (int1 + int2).toDouble / 2.0
          case (2, avg) => avg ==~ (int2 + int3 + int4).toDouble / 3.0
        })

      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3, int4))
        _ <- Ns.int(variance).query.get.map(_.head ==~ varianceOf(int1, int2, int3, int4))

        _ <- Ns.i.int(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(int1, int2)
          case (2, variance) => variance ==~ varianceOf(int2, int3, int4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3, int4))
        _ <- Ns.int(stddev).query.get.map(_.head ==~ stdDevOf(int1, int2, int3, int4))

        _ <- Ns.i.int(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(int1, int2)
          case (2, stddev) => stddev ==~ stdDevOf(int2, int3, int4)
        })
      } yield ()
    }
  }
}