// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_Long_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    implicit val tolerant = tolerantLongEquality(toleranceLong)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // Sum of all values
      _ <- Entity.long(sum).query.get.map(
        _.head ==~ long1 + long2 + long2 + long3 + long4
      )

      _ <- Entity.i.long(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ long1 + long2
        case (2, sum) => sum ==~ long2 + long3 + long4
      })
    } yield ()
  }


  "median" - types {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long5),
        (2, long9),
      )).transact

      _ <- Entity.long(median).query.get.map(_.head ==~ long2.toString.toDouble) // middle number

      _ <- Entity.i.long(median).query.get.map(_.collect {
        case (1, median) => median ==~ (long1 + long2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ long5.toString.toDouble // middle number
      })
    } yield ()
  }


  "avg" - types {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // Average of all values
      _ <- Entity.long(avg).query.get.map(
        _.head ==~ (long1 + long2 + long2 + long3 + long4).toDouble / 5.0
      )

      _ <- Entity.i.long(avg).query.get.map(_.collect {
        case (1, avg) => avg ==~ (long1 + long2).toDouble / 2.0
        case (2, avg) => avg ==~ (long2 + long3 + long4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // Variance of all values
      _ <- Entity.long(variance).query.get.map(
        _.head ==~ varianceOf(long1, long2, long2, long3, long4)
      )

      _ <- Entity.i.long(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(long1, long2)
        case (2, variance) => variance ==~ varianceOf(long2, long3, long4)
      })
    } yield ()
  }


  "stddev" - types {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (1, long2),
        (2, long2),
        (2, long3),
        (2, long4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.long(stddev).query.get.map(
        _.head ==~ stdDevOf(long1, long2, long2, long3, long4)
      )

      _ <- Entity.i.long(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(long1, long2)
        case (2, stddev) => stddev ==~ stdDevOf(long2, long3, long4)
      })
    } yield ()
  }
}