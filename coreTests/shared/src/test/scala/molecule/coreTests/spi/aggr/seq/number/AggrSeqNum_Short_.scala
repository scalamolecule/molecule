// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqNum_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Using tolerant equality so that the test works with decimal number types too

  override lazy val tests = Tests {

    "sum" - types { implicit conn =>
      implicit val tolerant = tolerantShortEquality(toleranceShort)
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4)),
          (2, List(short3, short4)),
        )).transact

        // Sum of all values
        _ <- Ns.shortSeq(sum).query.get.map(_.head ==~ (
          short1 + short2 + short2 +
            short2 +
            short3 + short4 +
            short3 + short4))

        // Sort by sum
        _ <- Ns.i.shortSeq(sum).a1.query.get.map(_ ==~ List(
          (1, short1 + short2 + short2),
          (2, short2 + short3 + short4 + short3 + short4),
        ))
        _ <- Ns.i.shortSeq(sum).d1.query.get.map(_ ==~ List(
          (2, short2 + short3 + short4 + short3 + short4),
          (1, short1 + short2 + short2),
        ))
      } yield ()
    }


    "median" - types { implicit futConn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      // Different databases have different ways of calculating a median
      database match {
        case "Datomic" =>
          for {
            _ <- Ns.i.shortSeq.insert(List(
              (1, List(short1, short2)),
              (2, List(short2)),
              (2, List(short5, short9)),
            )).transact

            // Median of all values - middle number used if odd number of values
            // 1  2  2  5  9
            //       ^
            _ <- Ns.shortSeq(median).query.get.map(_.head ==~ short2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.shortSeq(median).a1.query.get.map(_ ==~ List(
              (1, short1.toDouble.floor), // lower whole number
              (2, short5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.shortSeq(median).d1.query.get.map(_ ==~ List(
              (2, short5.toString.toDouble), // whole middle number
              (1, short1.toDouble.floor), // lower whole number
            ))
          } yield ()

        case "MongoDB" =>
          for {
            _ <- Ns.i.shortSeq.insert(List(
              (1, List(short1, short2)),
              (2, List(short2)),
              (2, List(short5, short9)),
            )).transact

            _ <- Ns.shortSeq(median).query.get.map(_.head ==~ short2.toString.toDouble) // whole middle number

            // Sort by median
            _ <- Ns.i.shortSeq(median).a1.query.get.map(_ ==~ List(
              (1, short1.toDouble), // lower number
              (2, short5.toString.toDouble), // whole middle number
            ))
            _ <- Ns.i.shortSeq(median).d1.query.get.map(_ ==~ List(
              (2, short5.toString.toDouble), // whole middle number
              (1, short1.toDouble), // lower number
            ))
          } yield ()

        case _ =>
          for {
            _ <- Ns.i.shortSeq.insert(List(
              (1, List(short1, short2)),
              (2, List(short2)),
              (2, List(short5, short9)),
            )).transact

            _ <- Ns.shortSeq(median).query.get.map(_.head ==~ short2.toString.toDouble) // middle number

            // Sort by median
            _ <- Ns.i.shortSeq(median).a1.query.get.map(_ ==~ List(
              (1, (short1 + short2 + short2).toDouble / 2.0), // average of 2 middle numbers
              (2, short5.toString.toDouble), // middle number
            ))
            _ <- Ns.i.shortSeq(median).d1.query.get.map(_ ==~ List(
              (2, short5.toString.toDouble), // middle number
              (1, (short1 + short2 + short2).toDouble / 2.0), // average of 2 middle numbers
            ))
          } yield ()
      }
    }


    "avg" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4)),
          (2, List(short3, short4)),
        )).transact

        // Average of all values
        _ <- Ns.shortSeq(avg).query.get.map(_.head ==~ (
          short1 + short2 + short2 +
            short2 +
            short3 + short4 +
            short3 + short4
          ).toDouble / 8.0)

        // Sort by average
        _ <- Ns.i.shortSeq(avg).a1.query.get.map(_ ==~ List(
          (1, (short1 + short2 + short2).toDouble / 3.0),
          (2, (short2 + short3 + short4 + short3 + short4).toDouble / 5.0),
        ))
        _ <- Ns.i.shortSeq(avg).d1.query.get.map(_ ==~ List(
          (2, (short2 + short3 + short4 + short3 + short4).toDouble / 5.0),
          (1, (short1 + short2 + short2).toDouble / 3.0),
        ))
      } yield ()
    }


    "variance" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4)),
          (2, List(short3, short4)),
        )).transact

        // Variance of all values
        _ <- Ns.shortSeq(variance).query.get.map(_.head ==~ varianceOf(
          short1, short2, short2,
          short2,
          short3, short4,
          short3, short4
        ))

        // Sort by variance
        _ <- Ns.i.shortSeq(variance).a1.query.get.map(_ ==~ List(
          (1, varianceOf(short1, short2, short2)),
          (2, varianceOf(short2, short3, short4, short3, short4)),
        ))
        _ <- Ns.i.shortSeq(variance).d1.query.get.map(_ ==~ List(
          (2, varianceOf(short2, short3, short4, short3, short4)),
          (1, varianceOf(short1, short2, short2)),
        ))
      } yield ()
    }


    "stddev" - types { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4)),
          (2, List(short3, short4)),
        )).transact


        // Standard deviation of all values
        _ <- Ns.shortSeq(stddev).query.get.map(_.head ==~ stdDevOf(
          short1, short2, short2,
          short2,
          short3, short4,
          short3, short4
        ))

        // Sort by standard deviation
        _ <- Ns.i.shortSeq(stddev).a1.query.get.map(_ ==~ List(
          (1, stdDevOf(short1, short2, short2)),
          (2, stdDevOf(short2, short3, short4, short3, short4)),
        ))
        _ <- Ns.i.shortSeq(stddev).d1.query.get.map(_ ==~ List(
          (2, stdDevOf(short2, short3, short4, short3, short4)),
          (1, stdDevOf(short1, short2, short2)),
        ))
      } yield ()
    }
  }
}