// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqNum_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantLongEquality(toleranceLong)
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4)),
          (2, List(long3, long4)),
        )).transact

        // Sum of all values
        _ <- Ns.longSeq(sum).query.get.map(_.head ==~ (
          long1 + long2 + long2 +
            long2 +
            long3 + long4 +
            long3 + long4))

        // Sort by sum
        _ <- Ns.i.longSeq(sum).a1.query.get.map(_ ==~ List(
          (1, long1 + long2 + long2),
          (2, long2 + long3 + long4 + long3 + long4),
        ))
        _ <- Ns.i.longSeq(sum).d1.query.get.map(_ ==~ List(
          (2, long2 + long3 + long4 + long3 + long4),
          (1, long1 + long2 + long2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.longSeq.insert(List(
              (1, List(long1, long2)),
              (2, List(long2)),
              (2, List(long5, long9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.longSeq(median).query.get.map(_.head ==~ long2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.longSeq(median).a1.query.get.map(_ ==~ List(
              (1, long1.toDouble.floor), // lower whole number
              (2, long5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.longSeq(median).d1.query.get.map(_ ==~ List(
              (2, long5.toString.toDouble), // whole middle number
              (1, long1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.longSeq.insert(List(
              (1, List(long1, long2)),
              (2, List(long2)),
              (2, List(long5, long9)),
            )).transact

            _ <- Ns.longSeq(median).query.get.map(_.head ==~ long2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.longSeq(median).a1.query.get.map(_ ==~ List(
              (1, long1.toDouble), // lower number
              (2, long5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.longSeq(median).d1.query.get.map(_ ==~ List(
              (2, long5.toString.toDouble), // whole middle number
              (1, long1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.longSeq.insert(List(
              (1, List(long1, long2)),
              (2, List(long2)),
              (2, List(long5, long9)),
            )).transact

            _ <- Ns.longSeq(median).query.get.map(_.head ==~ long2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.longSeq(median).a1.query.get.map(_ ==~ List(
              (1, (long1 + long2 + long2).toDouble / 2.0), // average of 2 middle numbers
              (2, long5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.longSeq(median).d1.query.get.map(_ ==~ List(
              (2, long5.toString.toDouble), // middle number
              (1, (long1 + long2 + long2).toDouble / 2.0), // average of 2 middle numbers
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4)),
          (2, List(long3, long4)),
        )).transact

        // Average of all values
        _ <- Ns.longSeq(avg).query.get.map(_.head ==~ (
          long1 + long2 + long2 +
            long2 +
            long3 + long4 +
            long3 + long4
          ).toDouble / 8.0)

        // Sort by average
        _ <- Ns.i.longSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (long1 + long2 + long2).toDouble / 3.0),
          (2, (long2 + long3 + long4 + long3 + long4).toDouble / 5.0),
        ))
        _ <- Ns.i.longSeq(avg).d1.query.get.map(_ ==~ List(
          (2, (long2 + long3 + long4 + long3 + long4).toDouble / 5.0),
          (1, (long1 + long2 + long2).toDouble / 3.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4)),
          (2, List(long3, long4)),
        )).transact

        // Variance of all values
        _ <- Ns.longSeq(variance).query.get.map(_.head ==~ varianceOf(
          long1, long2, long2,
          long2,
          long3, long4,
          long3, long4
        ))

        // Sort by variance
        _ <- Ns.i.longSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(long1, long2, long2)),
          (2, varianceOf(long2, long3, long4, long3, long4)),
        ))
        _ <- Ns.i.longSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(long2, long3, long4, long3, long4)),
          (1, varianceOf(long1, long2, long2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4)),
          (2, List(long3, long4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.longSeq(stddev).query.get.map(_.head ==~ stdDevOf(
          long1, long2, long2,
          long2,
          long3, long4,
          long3, long4
        ))

        // Sort by standard deviation
        _ <- Ns.i.longSeq(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(long1, long2, long2)),
          (2, stdDevOf(long2, long3, long4, long3, long4)),
        ))
        _ <- Ns.i.longSeq(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(long2, long3, long4, long3, long4)),
          (1, stdDevOf(long1, long2, long2)),
        ))
      } yield ()
    }
  }
}