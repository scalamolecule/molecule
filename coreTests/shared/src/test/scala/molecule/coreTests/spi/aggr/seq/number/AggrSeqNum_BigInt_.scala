// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqNum_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantBigIntEquality(toleranceBigInt)
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4)),
          (2, List(bigInt3, bigInt4)),
        )).transact

        // Sum of all values
        _ <- Ns.bigIntSeq(sum).query.get.map(_.head ==~ (
          bigInt1 + bigInt2 + bigInt2 +
            bigInt2 +
            bigInt3 + bigInt4 +
            bigInt3 + bigInt4))

        // Sort by sum
        _ <- Ns.i.bigIntSeq(sum).a1.query.get.map(_ ==~ List(
          (1, bigInt1 + bigInt2 + bigInt2),
          (2, bigInt2 + bigInt3 + bigInt4 + bigInt3 + bigInt4),
        ))
        _ <- Ns.i.bigIntSeq(sum).d1.query.get.map(_ ==~ List(
          (2, bigInt2 + bigInt3 + bigInt4 + bigInt3 + bigInt4),
          (1, bigInt1 + bigInt2 + bigInt2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.bigIntSeq.insert(List(
              (1, List(bigInt1, bigInt2)),
              (2, List(bigInt2)),
              (2, List(bigInt5, bigInt9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.bigIntSeq(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.bigIntSeq(median).a1.query.get.map(_ ==~ List(
              (1, bigInt1.toDouble.floor), // lower whole number
              (2, bigInt5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.bigIntSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigInt5.toString.toDouble), // whole middle number
              (1, bigInt1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.bigIntSeq.insert(List(
              (1, List(bigInt1, bigInt2)),
              (2, List(bigInt2)),
              (2, List(bigInt5, bigInt9)),
            )).transact

            _ <- Ns.bigIntSeq(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.bigIntSeq(median).a1.query.get.map(_ ==~ List(
              (1, bigInt1.toDouble), // lower number
              (2, bigInt5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.bigIntSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigInt5.toString.toDouble), // whole middle number
              (1, bigInt1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.bigIntSeq.insert(List(
              (1, List(bigInt1, bigInt2)),
              (2, List(bigInt2)),
              (2, List(bigInt5, bigInt9)),
            )).transact

            _ <- Ns.bigIntSeq(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.bigIntSeq(median).a1.query.get.map(_ ==~ List(
              (1, (bigInt1 + bigInt2 + bigInt2).toDouble.toDouble / 2.0), // average of 2 middle numbers
              (2, bigInt5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.bigIntSeq(median).d1.query.get.map(_ ==~ List(
              (2, bigInt5.toString.toDouble), // middle number
              (1, (bigInt1 + bigInt2 + bigInt2).toDouble.toDouble / 2.0), // average of 2 middle numbers
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4)),
          (2, List(bigInt3, bigInt4)),
        )).transact

        // Average of all values
        _ <- Ns.bigIntSeq(avg).query.get.map(_.head ==~ (
          bigInt1 + bigInt2 + bigInt2 +
            bigInt2 +
            bigInt3 + bigInt4 +
            bigInt3 + bigInt4
          ).toDouble.toDouble / 8.0)

        // Sort by average
        _ <- Ns.i.bigIntSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (bigInt1 + bigInt2 + bigInt2).toDouble.toDouble / 3.0),
          (2, (bigInt2 + bigInt3 + bigInt4 + bigInt3 + bigInt4).toDouble.toDouble / 5.0),
        ))
        _ <- Ns.i.bigIntSeq(avg).d1.query.get.map(_ ==~ List(
          (2, (bigInt2 + bigInt3 + bigInt4 + bigInt3 + bigInt4).toDouble.toDouble / 5.0),
          (1, (bigInt1 + bigInt2 + bigInt2).toDouble.toDouble / 3.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4)),
          (2, List(bigInt3, bigInt4)),
        )).transact

        // Variance of all values
        _ <- Ns.bigIntSeq(variance).query.get.map(_.head ==~ varianceOf(
          bigInt1, bigInt2, bigInt2,
          bigInt2,
          bigInt3, bigInt4,
          bigInt3, bigInt4
        ))

        // Sort by variance
        _ <- Ns.i.bigIntSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(bigInt1, bigInt2, bigInt2)),
          (2, varianceOf(bigInt2, bigInt3, bigInt4, bigInt3, bigInt4)),
        ))
        _ <- Ns.i.bigIntSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(bigInt2, bigInt3, bigInt4, bigInt3, bigInt4)),
          (1, varianceOf(bigInt1, bigInt2, bigInt2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4)),
          (2, List(bigInt3, bigInt4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.bigIntSeq(stddev).query.get.map(_.head ==~ stdDevOf(
          bigInt1, bigInt2, bigInt2,
          bigInt2,
          bigInt3, bigInt4,
          bigInt3, bigInt4
        ))

        // Sort by standard deviation
        _ <- Ns.i.bigIntSeq(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(bigInt1, bigInt2, bigInt2)),
          (2, stdDevOf(bigInt2, bigInt3, bigInt4, bigInt3, bigInt4)),
        ))
        _ <- Ns.i.bigIntSeq(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(bigInt2, bigInt3, bigInt4, bigInt3, bigInt4)),
          (1, stdDevOf(bigInt1, bigInt2, bigInt2)),
        ))
      } yield ()
    }
  }
}