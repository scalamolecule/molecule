package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantIntEquality(toleranceInt)
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Sum of all values
        _ <- Ns.intSet(sum).query.get.map(_.head ==~ (
          int1 + int2 +
            int2 +
            int3 + int4 +
            int3 + int4))

        // Sort by sum
        _ <- Ns.i.intSet(sum).a1.query.get.map(_ ==~ List(
          (1, int1 + int2),
          (2, int2 + int3 + int4 + int3 + int4),
        ))
        _ <- Ns.i.intSet(sum).d1.query.get.map(_ ==~ List(
          (2, int2 + int3 + int4 + int3 + int4),
          (1, int1 + int2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.intSet.insert(List(
              (1, Set(int1, int2)),
              (2, Set(int2)),
              (2, Set(int5, int9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.intSet(median).query.get.map(_.head ==~ int2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.intSet(median).a1.query.get.map(_ ==~ List(
              (1, int1.toDouble.floor), // lower whole number
              (2, int5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.intSet(median).d1.query.get.map(_ ==~ List(
              (2, int5.toString.toDouble), // whole middle number
              (1, int1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.intSet.insert(List(
              (1, Set(int1, int2)),
              (2, Set(int2)),
              (2, Set(int5, int9)),
            )).transact

            _ <- Ns.intSet(median).query.get.map(_.head ==~ int2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.intSet(median).a1.query.get.map(_ ==~ List(
              (1, int1.toDouble), // lower number
              (2, int5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.intSet(median).d1.query.get.map(_ ==~ List(
              (2, int5.toString.toDouble), // whole middle number
              (1, int1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.intSet.insert(List(
              (1, Set(int1, int2)),
              (2, Set(int2)),
              (2, Set(int5, int9)),
            )).transact

            _ <- Ns.intSet(median).query.get.map(_.head ==~ int2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.intSet(median).a1.query.get.map(_ ==~ List(
              (1, (int1 + int2).toDouble / 2.0), // average of 2 middle numbers
              (2, int5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.intSet(median).d1.query.get.map(_ ==~ List(
              (1, (int1 + int2).toDouble / 2.0), // average of 2 middle numbers
              (2, int5.toString.toDouble), // middle number
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Average of all values
        _ <- Ns.intSet(avg).query.get.map(_.head ==~ (
          int1 + int2 +
            int2 +
            int3 + int4 +
            int3 + int4
          ).toDouble / 7.0)

        // Sort by average
        _ <- Ns.i.intSet(avg).a1.query.get.map(_ ==~ List(
          (1, (int1 + int2).toDouble / 2.0),
          (2, (int2 + int3 + int4 + int3 + int4).toDouble / 5.0),
        ))
        _ <- Ns.i.intSet(avg).d1.query.get.map(_ ==~ List(
          (2, (int2 + int3 + int4 + int3 + int4).toDouble / 5.0),
          (1, (int1 + int2).toDouble / 2.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Variance of all values
        _ <- Ns.intSet(variance).query.get.map(_.head ==~ varianceOf(
          int1, int2,
          int2,
          int3, int4,
          int3, int4
        ))

        // Sort by variance
        _ <- Ns.i.intSet(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(int1, int2)),
          (2, varianceOf(int2, int3, int4, int3, int4)),
        ))
        _ <- Ns.i.intSet(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(int2, int3, int4, int3, int4)),
          (1, varianceOf(int1, int2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Standard deviation of all values
        _ <- Ns.intSet(stddev).query.get.map(_.head ==~ stdDevOf(
          int1, int2,
          int2,
          int3, int4,
          int3, int4
        ))

        // Sort by standard deviation
        _ <- Ns.i.intSet(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(int1, int2)),
          (2, stdDevOf(int2, int3, int4, int3, int4)),
        ))
        _ <- Ns.i.intSet(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(int2, int3, int4, int3, int4)),
          (1, stdDevOf(int1, int2)),
        ))
      } yield ()
    }
  }
}