// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AggrNum_BigInt_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api._
  import suite._

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantBigIntEquality(toleranceBigInt)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // Sum of all values
      _ <- Entity.bigInt(sum).query.get.map(
        _.head ==~ bigInt1 + bigInt2 + bigInt2 + bigInt3 + bigInt4
      )

      _ <- Entity.i.bigInt(sum).query.get.map(_.map {
        case (1, sum) => sum ==~ bigInt1 + bigInt2
        case (2, sum) => sum ==~ bigInt2 + bigInt3 + bigInt4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    // Different databases have different ways of calculating a median
    database match {
      case "datomic" =>
        for {
          _ <- Entity.i.bigInt.insert(List(
            (1, bigInt1),
            (1, bigInt2),
            (2, bigInt2),
            (2, bigInt5),
            (2, bigInt9),
          )).transact

          // Median of all values - middle number used if odd number of values
          // 1  2  2  5  9
          //       ^
          _ <- Entity.bigInt(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // middle number

          _ <- Entity.i.bigInt(median).query.get.map(_.map {
            case (1, median) => median ==~ bigInt1.toDouble.floor // lower whole number
            case (2, median) => median ==~ bigInt5.toString.toDouble // middle number
          })
        } yield ()

      case _ =>
        for {
          _ <- Entity.i.bigInt.insert(List(
            (1, bigInt1),
            (1, bigInt2),
            (2, bigInt2),
            (2, bigInt5),
            (2, bigInt9),
          )).transact

          _ <- Entity.bigInt(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // middle number

          _ <- Entity.i.bigInt(median).query.get.map(_.map {
            case (1, median) => median ==~ (bigInt1 + bigInt2).toDouble / 2.0 // average of 2 middle numbers
            case (2, median) => median ==~ bigInt5.toString.toDouble // middle number
          })
        } yield ()
    }
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // Average of all values
      _ <- Entity.bigInt(avg).query.get.map(
        _.head ==~ (bigInt1 + bigInt2 + bigInt2 + bigInt3 + bigInt4).toDouble / 5.0
      )

      _ <- Entity.i.bigInt(avg).query.get.map(_.map {
        case (1, avg) => avg ==~ (bigInt1 + bigInt2).toDouble / 2.0
        case (2, avg) => avg ==~ (bigInt2 + bigInt3 + bigInt4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // Variance of all values
      _ <- Entity.bigInt(variance).query.get.map(
        _.head ==~ varianceOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
      )

      _ <- Entity.i.bigInt(variance).query.get.map(_.map {
        case (1, variance) => variance ==~ varianceOf(bigInt1, bigInt2)
        case (2, variance) => variance ==~ varianceOf(bigInt2, bigInt3, bigInt4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.bigInt(stddev).query.get.map(
        _.head ==~ stdDevOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
      )

      _ <- Entity.i.bigInt(stddev).query.get.map(_.map {
        case (1, stddev) => stddev ==~ stdDevOf(bigInt1, bigInt2)
        case (2, stddev) => stddev ==~ stdDevOf(bigInt2, bigInt3, bigInt4)
      })
    } yield ()
  }
}