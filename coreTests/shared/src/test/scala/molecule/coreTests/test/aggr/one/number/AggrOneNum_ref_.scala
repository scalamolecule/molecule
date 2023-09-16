// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOneNum_ref_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantLongEquality(toleranceLong)
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (1, ref2),
          (2, ref2),
          (2, ref3),
          (2, ref4),
        )).transact

        // Using tolerant equality so that the test works with decimal number types too
        // Distinct values (Set semantics) used
        _ <- Ns.ref(sum).query.get.map(_.head ==~ ref1 + ref2 + ref3 + ref4)
        _ <- Ns.i.ref(sum).query.get.map(_.map {
          case (1, sum) => sum ==~ ref1 + ref2
          case (2, sum) => sum ==~ ref2 + ref3 + ref4
        })
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (1, ref2),
          (2, ref2),
          (2, ref3),
          (2, ref4),
        )).transact

        _ <- Ns.ref.a1.query.get.map(_ ==> List(ref1, ref2, ref3, ref4))
        _ <- Ns.ref(median).query.get.map(_.head ==~ (ref2 + ref3).toDouble / 2.0)

        _ <- Ns.i.ref(median).query.get.map(_.map {
          case (1, median) => median ==~ (ref1 + ref2).toDouble / 2.0
          case (2, median) => median ==~ ref3.toString.toDouble
        })
      } yield ()
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (1, ref2),
          (2, ref2),
          (2, ref3),
          (2, ref4),
        )).transact

        _ <- Ns.ref.a1.query.get.map(_ ==> List(ref1, ref2, ref3, ref4))
        _ <- Ns.ref(avg).query.get.map(_.head ==~ (ref1 + ref2 + ref3 + ref4).toDouble / 4.0)

        _ <- Ns.i.ref(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (ref1 + ref2).toDouble / 2.0
          case (2, avg) => avg ==~ (ref2 + ref3 + ref4).toDouble / 3.0
        })

      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (1, ref2),
          (2, ref2),
          (2, ref3),
          (2, ref4),
        )).transact

        _ <- Ns.ref.a1.query.get.map(_ ==> List(ref1, ref2, ref3, ref4))
        _ <- Ns.ref(variance).query.get.map(_.head ==~ varianceOf(ref1, ref2, ref3, ref4))

        _ <- Ns.i.ref(variance).query.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(ref1, ref2)
          case (2, variance) => variance ==~ varianceOf(ref2, ref3, ref4)
        })
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (1, ref2),
          (2, ref2),
          (2, ref3),
          (2, ref4),
        )).transact

        _ <- Ns.ref.a1.query.get.map(_ ==> List(ref1, ref2, ref3, ref4))
        _ <- Ns.ref(stddev).query.get.map(_.head ==~ stdDevOf(ref1, ref2, ref3, ref4))

        _ <- Ns.i.ref(stddev).query.get.map(_.map {
          case (1, stddev) => stddev ==~ stdDevOf(ref1, ref2)
          case (2, stddev) => stddev ==~ stdDevOf(ref2, ref3, ref4)
        })
      } yield ()
    }
  }
}