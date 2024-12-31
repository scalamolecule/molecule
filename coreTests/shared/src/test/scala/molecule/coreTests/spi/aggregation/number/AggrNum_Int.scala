package molecule.coreTests.spi.aggregation.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AggrNum_Int(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api._
  import suite._

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantIntEquality(toleranceInt)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // Sum of all values
      _ <- Entity.int(sum).query.get.map(
        _.head ==~ int1 + int2 + int2 + int3 + int4
      )

      _ <- Entity.i.int(sum).query.get.map(_.map {
        case (1, sum) => sum ==~ int1 + int2
        case (2, sum) => sum ==~ int2 + int3 + int4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    // Different databases have different ways of calculating a median
    database match {
      case "datomic" =>
        for {
          _ <- Entity.i.int.insert(List(
            (1, int1),
            (1, int2),
            (2, int2),
            (2, int5),
            (2, int9),
          )).transact

          // Median of all values - middle number used if odd number of values
          // 1  2  2  5  9
          //       ^
          _ <- Entity.int(median).query.get.map(_.head ==~ int2.toString.toDouble) // middle number

          _ <- Entity.i.int(median).query.get.map(_.map {
            case (1, median) => median ==~ int1.toDouble.floor // lower whole number
            case (2, median) => median ==~ int5.toString.toDouble // middle number
          })
        } yield ()

      case _ =>
        for {
          _ <- Entity.i.int.insert(List(
            (1, int1),
            (1, int2),
            (2, int2),
            (2, int5),
            (2, int9),
          )).transact

          _ <- Entity.int(median).query.get.map(_.head ==~ int2.toString.toDouble) // middle number

          _ <- Entity.i.int(median).query.get.map(_.map {
            case (1, median) => median ==~ (int1 + int2).toDouble / 2.0 // average of 2 middle numbers
            case (2, median) => median ==~ int5.toString.toDouble // middle number
          })
        } yield ()
    }
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // Average of all values
      _ <- Entity.int(avg).query.get.map(
        _.head ==~ (int1 + int2 + int2 + int3 + int4).toDouble / 5.0
      )

      _ <- Entity.i.int(avg).query.get.map(_.map {
        case (1, avg) => avg ==~ (int1 + int2).toDouble / 2.0
        case (2, avg) => avg ==~ (int2 + int3 + int4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // Variance of all values
      _ <- Entity.int(variance).query.get.map(
        _.head ==~ varianceOf(int1, int2, int2, int3, int4)
      )

      _ <- Entity.i.int(variance).query.get.map(_.map {
        case (1, variance) => variance ==~ varianceOf(int1, int2)
        case (2, variance) => variance ==~ varianceOf(int2, int3, int4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.int(stddev).query.get.map(
        _.head ==~ stdDevOf(int1, int2, int2, int3, int4)
      )

      _ <- Entity.i.int(stddev).query.get.map(_.map {
        case (1, stddev) => stddev ==~ stdDevOf(int1, int2)
        case (2, stddev) => stddev ==~ stdDevOf(int2, int3, int4)
      })
    } yield ()
  }
}