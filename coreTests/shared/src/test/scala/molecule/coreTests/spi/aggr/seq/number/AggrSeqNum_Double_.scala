// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqNum_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4)),
          (2, List(double3, double4)),
        )).transact

        // Sum of all values
        _ <- Ns.doubleSeq(sum).query.get.map(_.head ==~ (
          double1 + double2 + double2 +
            double2 +
            double3 + double4 +
            double3 + double4))

        // Sort by sum
        _ <- Ns.i.doubleSeq(sum).a1.query.get.map(_ ==~ List(
          (1, double1 + double2 + double2),
          (2, double2 + double3 + double4 + double3 + double4),
        ))
        _ <- Ns.i.doubleSeq(sum).d1.query.get.map(_ ==~ List(
          (2, double2 + double3 + double4 + double3 + double4),
          (1, double1 + double2 + double2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.doubleSeq.insert(List(
              (1, List(double1, double2)),
              (2, List(double2)),
              (2, List(double5, double9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.doubleSeq(median).query.get.map(_.head ==~ double2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.doubleSeq(median).a1.query.get.map(_ ==~ List(
              (1, double1.toDouble.floor), // lower whole number
              (2, double5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.doubleSeq(median).d1.query.get.map(_ ==~ List(
              (2, double5.toString.toDouble), // whole middle number
              (1, double1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.doubleSeq.insert(List(
              (1, List(double1, double2)),
              (2, List(double2)),
              (2, List(double5, double9)),
            )).transact

            _ <- Ns.doubleSeq(median).query.get.map(_.head ==~ double2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.doubleSeq(median).a1.query.get.map(_ ==~ List(
              (1, double1.toDouble), // lower number
              (2, double5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.doubleSeq(median).d1.query.get.map(_ ==~ List(
              (2, double5.toString.toDouble), // whole middle number
              (1, double1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.doubleSeq.insert(List(
              (1, List(double1, double2)),
              (2, List(double2)),
              (2, List(double5, double9)),
            )).transact

            _ <- Ns.doubleSeq(median).query.get.map(_.head ==~ double2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.doubleSeq(median).a1.query.get.map(_ ==~ List(
              (1, (double1 + double2 + double2).toDouble / 2.0), // average of 2 middle numbers
              (2, double5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.doubleSeq(median).d1.query.get.map(_ ==~ List(
              (2, double5.toString.toDouble), // middle number
              (1, (double1 + double2 + double2).toDouble / 2.0), // average of 2 middle numbers
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4)),
          (2, List(double3, double4)),
        )).transact

        // Average of all values
        _ <- Ns.doubleSeq(avg).query.get.map(_.head ==~ (
          double1 + double2 + double2 +
            double2 +
            double3 + double4 +
            double3 + double4
          ).toDouble / 8.0)

        // Sort by average
        _ <- Ns.i.doubleSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (double1 + double2 + double2).toDouble / 3.0),
          (2, (double2 + double3 + double4 + double3 + double4).toDouble / 5.0),
        ))
        _ <- Ns.i.doubleSeq(avg).d1.query.get.map(_ ==~ List(
          (2, (double2 + double3 + double4 + double3 + double4).toDouble / 5.0),
          (1, (double1 + double2 + double2).toDouble / 3.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4)),
          (2, List(double3, double4)),
        )).transact

        // Variance of all values
        _ <- Ns.doubleSeq(variance).query.get.map(_.head ==~ varianceOf(
          double1, double2, double2,
          double2,
          double3, double4,
          double3, double4
        ))

        // Sort by variance
        _ <- Ns.i.doubleSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(double1, double2, double2)),
          (2, varianceOf(double2, double3, double4, double3, double4)),
        ))
        _ <- Ns.i.doubleSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(double2, double3, double4, double3, double4)),
          (1, varianceOf(double1, double2, double2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4)),
          (2, List(double3, double4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.doubleSeq(stddev).query.get.map(_.head ==~ stdDevOf(
          double1, double2, double2,
          double2,
          double3, double4,
          double3, double4
        ))

        // Sort by standard deviation
        _ <- Ns.i.doubleSeq(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(double1, double2, double2)),
          (2, stdDevOf(double2, double3, double4, double3, double4)),
        ))
        _ <- Ns.i.doubleSeq(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(double2, double3, double4, double3, double4)),
          (1, stdDevOf(double1, double2, double2)),
        ))
      } yield ()
    }
  }
}