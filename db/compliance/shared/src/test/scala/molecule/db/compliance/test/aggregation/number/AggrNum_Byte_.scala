// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantByteEquality(toleranceByte)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // Sum of all values
      _ <- Entity.byte(sum).query.get.map(
        _.head ==~ byte1 + byte2 + byte2 + byte3 + byte4
      )

      _ <- Entity.i.byte(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ byte1 + byte2
        case (2, sum) => sum ==~ byte2 + byte3 + byte4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte5),
        (2, byte9),
      )).transact

      _ <- Entity.byte(median).query.get.map(_.head ==~ byte2.toString.toDouble) // middle number

      _ <- Entity.i.byte(median).query.get.map(_.collect {
        case (1, median) => median ==~ (byte1 + byte2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ byte5.toString.toDouble // middle number
      })
    } yield ()
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // Average of all values
      _ <- Entity.byte(avg).query.get.map(
        _.head ==~ (byte1 + byte2 + byte2 + byte3 + byte4).toDouble / 5.0
      )

      _ <- Entity.i.byte(avg).query.get.map(_.collect {
        case (1, avg) => avg ==~ (byte1 + byte2).toDouble / 2.0
        case (2, avg) => avg ==~ (byte2 + byte3 + byte4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // Variance of all values
      _ <- Entity.byte(variance).query.get.map(
        _.head ==~ varianceOf(byte1, byte2, byte2, byte3, byte4)
      )

      _ <- Entity.i.byte(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(byte1, byte2)
        case (2, variance) => variance ==~ varianceOf(byte2, byte3, byte4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.byte(stddev).query.get.map(
        _.head ==~ stdDevOf(byte1, byte2, byte2, byte3, byte4)
      )

      _ <- Entity.i.byte(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(byte1, byte2)
        case (2, stddev) => stddev ==~ stdDevOf(byte2, byte3, byte4)
      })
    } yield ()
  }
}