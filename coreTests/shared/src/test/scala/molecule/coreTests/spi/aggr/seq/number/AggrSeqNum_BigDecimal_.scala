// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqNum_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigDecimalEquality(toleranceBigDecimal)
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4)),
        )).transact

        // Sum of all values
        _ <- Ns.bigDecimalSeq(sum).query.get.map(_.head ==~ (
          bigDecimal1 + bigDecimal2 + bigDecimal2 +
            bigDecimal2 +
            bigDecimal3 + bigDecimal4 +
            bigDecimal3 + bigDecimal4))

        // Sort by sum
        _ <- Ns.i.bigDecimalSeq(sum).a1.query.get.map(_ ==~ List(
          (1, bigDecimal1 + bigDecimal2 + bigDecimal2),
          (2, bigDecimal2 + bigDecimal3 + bigDecimal4 + bigDecimal3 + bigDecimal4),
        ))
        _ <- Ns.i.bigDecimalSeq(sum).d1.query.get.map(_ ==~ List(
          (2, bigDecimal2 + bigDecimal3 + bigDecimal4 + bigDecimal3 + bigDecimal4),
          (1, bigDecimal1 + bigDecimal2 + bigDecimal2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.bigDecimalSeq.insert(List(
              (1, List(bigDecimal1, bigDecimal2)),
              (2, List(bigDecimal2)),
              (2, List(bigDecimal5, bigDecimal9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.bigDecimalSeq(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.bigDecimalSeq(median).a1.query.get.map(_ ==~ List(
              (1, bigDecimal1.toDouble.floor), // lower whole number
              (2, bigDecimal5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.bigDecimalSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigDecimal5.toString.toDouble), // whole middle number
              (1, bigDecimal1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.bigDecimalSeq.insert(List(
              (1, List(bigDecimal1, bigDecimal2)),
              (2, List(bigDecimal2)),
              (2, List(bigDecimal5, bigDecimal9)),
            )).transact

            _ <- Ns.bigDecimalSeq(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.bigDecimalSeq(median).a1.query.get.map(_ ==~ List(
              (1, bigDecimal1.toDouble), // lower number
              (2, bigDecimal5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.bigDecimalSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigDecimal5.toString.toDouble), // whole middle number
              (1, bigDecimal1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.bigDecimalSeq.insert(List(
              (1, List(bigDecimal1, bigDecimal2)),
              (2, List(bigDecimal2)),
              (2, List(bigDecimal5, bigDecimal9)),
            )).transact

            _ <- Ns.bigDecimalSeq(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.bigDecimalSeq(median).a1.query.get.map(_ ==~ List(
              (1, (bigDecimal1 + bigDecimal2 + bigDecimal2).toDouble / 2.0), // average of 2 middle numbers
              (2, bigDecimal5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.bigDecimalSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigDecimal5.toString.toDouble), // middle number
              (1, (bigDecimal1 + bigDecimal2 + bigDecimal2).toDouble / 2.0), // average of 2 middle numbers
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4)),
        )).transact

        // Average of all values
        _ <- Ns.bigDecimalSeq(avg).query.get.map(_.head ==~ (
          bigDecimal1 + bigDecimal2 + bigDecimal2 +
            bigDecimal2 +
            bigDecimal3 + bigDecimal4 +
            bigDecimal3 + bigDecimal4
          ).toDouble / 8.0)

        // Sort by average
        _ <- Ns.i.bigDecimalSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (bigDecimal1 + bigDecimal2 + bigDecimal2).toDouble / 3.0),
          (2, (bigDecimal2 + bigDecimal3 + bigDecimal4 + bigDecimal3 + bigDecimal4).toDouble / 5.0),
        ))
        _ <- Ns.i.bigDecimalSeq(avg).d1.query.get.map(_ ==~ List(
          (2, (bigDecimal2 + bigDecimal3 + bigDecimal4 + bigDecimal3 + bigDecimal4).toDouble / 5.0),
          (1, (bigDecimal1 + bigDecimal2 + bigDecimal2).toDouble / 3.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4)),
        )).transact

        // Variance of all values
        _ <- Ns.bigDecimalSeq(variance).query.get.map(_.head ==~ varianceOf(
          bigDecimal1, bigDecimal2, bigDecimal2,
          bigDecimal2,
          bigDecimal3, bigDecimal4,
          bigDecimal3, bigDecimal4
        ))

        // Sort by variance
        _ <- Ns.i.bigDecimalSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, varianceOf(bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimalSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal3, bigDecimal4)),
          (1, varianceOf(bigDecimal1, bigDecimal2, bigDecimal2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.bigDecimalSeq(stddev).query.get.map(_.head ==~ stdDevOf(
          bigDecimal1, bigDecimal2, bigDecimal2,
          bigDecimal2,
          bigDecimal3, bigDecimal4,
          bigDecimal3, bigDecimal4
        ))

        // Sort by standard deviation
        _ <- Ns.i.bigDecimalSeq(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal3, bigDecimal4)),
        ))
        _ <- Ns.i.bigDecimalSeq(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal3, bigDecimal4)),
          (1, stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2)),
        ))
      } yield ()
    }
  }
}