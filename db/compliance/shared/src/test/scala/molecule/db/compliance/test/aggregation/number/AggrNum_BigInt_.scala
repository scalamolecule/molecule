// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

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

      _ <- Entity.i.bigInt(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ bigInt1 + bigInt2
        case (2, sum) => sum ==~ bigInt2 + bigInt3 + bigInt4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt5),
        (2, bigInt9),
      )).transact

      _ <- Entity.bigInt(median).query.get.map(_.head ==~ bigInt2.toString.toDouble) // middle number

      _ <- Entity.i.bigInt(median).query.get.map(_.collect {
        case (1, median) => median ==~ (bigInt1 + bigInt2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ bigInt5.toString.toDouble // middle number
      })
    } yield ()
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

      _ <- Entity.i.bigInt(avg).query.get.map(_.collect {
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

      _ <- Entity.i.bigInt(variance).query.get.map(_.collect {
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

      _ <- Entity.i.bigInt(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(bigInt1, bigInt2)
        case (2, stddev) => stddev ==~ stdDevOf(bigInt2, bigInt3, bigInt4)
      })
    } yield ()
  }
}