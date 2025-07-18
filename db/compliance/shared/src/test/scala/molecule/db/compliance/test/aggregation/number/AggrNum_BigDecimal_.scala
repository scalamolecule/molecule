// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class AggrNum_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantBigDecimalEquality(toleranceBigDecimal)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // Sum of all values
      _ <- Entity.bigDecimal(sum).query.get.map(
        _.head ==~ bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4
      )

      _ <- Entity.i.bigDecimal(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ bigDecimal1 + bigDecimal2
        case (2, sum) => sum ==~ bigDecimal2 + bigDecimal3 + bigDecimal4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal5),
        (2, bigDecimal9),
      )).transact

      _ <- Entity.bigDecimal(median).query.get.map(_.head ==~ bigDecimal2.toString.toDouble) // middle number

      _ <- Entity.i.bigDecimal(median).query.get.map(_.collect {
        case (1, median) => median ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ bigDecimal5.toString.toDouble // middle number
      })
    } yield ()
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // Average of all values
      _ <- Entity.bigDecimal(avg).query.get.map(
        _.head ==~ (bigDecimal1 + bigDecimal2 + bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 5.0
      )

      _ <- Entity.i.bigDecimal(avg).query.get.map(_.collect {
        case (1, avg) => avg ==~ (bigDecimal1 + bigDecimal2).toDouble / 2.0
        case (2, avg) => avg ==~ (bigDecimal2 + bigDecimal3 + bigDecimal4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // Variance of all values
      _ <- Entity.bigDecimal(variance).query.get.map(
        _.head ==~ varianceOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
      )

      _ <- Entity.i.bigDecimal(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(bigDecimal1, bigDecimal2)
        case (2, variance) => variance ==~ varianceOf(bigDecimal2, bigDecimal3, bigDecimal4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.bigDecimal(stddev).query.get.map(
        _.head ==~ stdDevOf(bigDecimal1, bigDecimal2, bigDecimal2, bigDecimal3, bigDecimal4)
      )

      _ <- Entity.i.bigDecimal(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(bigDecimal1, bigDecimal2)
        case (2, stddev) => stddev ==~ stdDevOf(bigDecimal2, bigDecimal3, bigDecimal4)
      })
    } yield ()
  }
}