// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetNum_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantByteEquality(toleranceByte)
      for {
        _ <- Ns.i.byteSet.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Sum of all values
        _ <- Ns.byteSet(sum).query.get.map(_.head ==~ (
          byte1 + byte2 +
            byte2 +
            byte3 + byte4 +
            byte3 + byte4))

        // Sort by sum
        _ <- Ns.i.byteSet(sum).a1.query.get.map(_ ==~ List(
          (1, byte1 + byte2),
          (2, byte2 + byte3 + byte4 + byte3 + byte4),
        ))
        _ <- Ns.i.byteSet(sum).d1.query.get.map(_ ==~ List(
          (2, byte2 + byte3 + byte4 + byte3 + byte4),
          (1, byte1 + byte2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.byteSet.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.byteSet(median).query.get.map(_.head ==~ byte2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.byteSet(median).a1.query.get.map(_ ==~ List(
              (1, byte1.toDouble.floor), // lower whole number
              (2, byte5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.byteSet(median).d1.query.get.map(_ ==~ List(
              (2, byte5.toString.toDouble), // whole middle number
              (1, byte1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.byteSet.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            _ <- Ns.byteSet(median).query.get.map(_.head ==~ byte2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.byteSet(median).a1.query.get.map(_ ==~ List(
              (1, byte1.toDouble), // lower number
              (2, byte5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.byteSet(median).d1.query.get.map(_ ==~ List(
              (2, byte5.toString.toDouble), // whole middle number
              (1, byte1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.byteSet.insert(List(
              (1, Set(byte1, byte2)),
              (2, Set(byte2)),
              (2, Set(byte5, byte9)),
            )).transact

            _ <- Ns.byteSet(median).query.get.map(_.head ==~ byte2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.byteSet(median).a1.query.get.map(_ ==~ List(
              (1, (byte1 + byte2).toDouble / 2.0), // average of 2 middle numbers
              (2, byte5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.byteSet(median).d1.query.get.map(_ ==~ List(
              (1, (byte1 + byte2).toDouble / 2.0), // average of 2 middle numbers
              (2, byte5.toString.toDouble), // middle number
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byteSet.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Average of all values
        _ <- Ns.byteSet(avg).query.get.map(_.head ==~ (
          byte1 + byte2 +
            byte2 +
            byte3 + byte4 +
            byte3 + byte4
          ).toDouble / 7.0)

        // Sort by average
        _ <- Ns.i.byteSet(avg).a1.query.get.map(_ ==~ List(
          (1, (byte1 + byte2).toDouble / 2.0),
          (2, (byte2 + byte3 + byte4 + byte3 + byte4).toDouble / 5.0),
        ))
        _ <- Ns.i.byteSet(avg).d1.query.get.map(_ ==~ List(
          (2, (byte2 + byte3 + byte4 + byte3 + byte4).toDouble / 5.0),
          (1, (byte1 + byte2).toDouble / 2.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byteSet.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Variance of all values
        _ <- Ns.byteSet(variance).query.get.map(_.head ==~ varianceOf(
          byte1, byte2,
          byte2,
          byte3, byte4,
          byte3, byte4
        ))

        // Sort by variance
        _ <- Ns.i.byteSet(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(byte1, byte2)),
          (2, varianceOf(byte2, byte3, byte4, byte3, byte4)),
        ))
        _ <- Ns.i.byteSet(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(byte2, byte3, byte4, byte3, byte4)),
          (1, varianceOf(byte1, byte2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.byteSet.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Standard deviation of all values
        _ <- Ns.byteSet(stddev).query.get.map(_.head ==~ stdDevOf(
          byte1, byte2,
          byte2,
          byte3, byte4,
          byte3, byte4
        ))

        // Sort by standard deviation
        _ <- Ns.i.byteSet(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(byte1, byte2)),
          (2, stdDevOf(byte2, byte3, byte4, byte3, byte4)),
        ))
        _ <- Ns.i.byteSet(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(byte2, byte3, byte4, byte3, byte4)),
          (1, stdDevOf(byte1, byte2)),
        ))
      } yield ()
    }
  }
}