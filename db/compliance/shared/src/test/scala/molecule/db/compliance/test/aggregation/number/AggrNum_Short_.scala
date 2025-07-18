// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantShortEquality(toleranceShort)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // Sum of all values
      _ <- Entity.short(sum).query.get.map(
        _.head ==~ short1 + short2 + short2 + short3 + short4
      )

      _ <- Entity.i.short(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ short1 + short2
        case (2, sum) => sum ==~ short2 + short3 + short4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short5),
        (2, short9),
      )).transact

      _ <- Entity.short(median).query.get.map(_.head ==~ short2.toString.toDouble) // middle number

      _ <- Entity.i.short(median).query.get.map(_.collect {
        case (1, median) => median ==~ (short1 + short2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ short5.toString.toDouble // middle number
      })
    } yield ()
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // Average of all values
      _ <- Entity.short(avg).query.get.map(
        _.head ==~ (short1 + short2 + short2 + short3 + short4).toDouble / 5.0
      )

      _ <- Entity.i.short(avg).query.get.map(_.collect {
        case (1, avg) => avg ==~ (short1 + short2).toDouble / 2.0
        case (2, avg) => avg ==~ (short2 + short3 + short4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // Variance of all values
      _ <- Entity.short(variance).query.get.map(
        _.head ==~ varianceOf(short1, short2, short2, short3, short4)
      )

      _ <- Entity.i.short(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(short1, short2)
        case (2, variance) => variance ==~ varianceOf(short2, short3, short4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (1, short2),
        (2, short2),
        (2, short3),
        (2, short4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.short(stddev).query.get.map(
        _.head ==~ stdDevOf(short1, short2, short2, short3, short4)
      )

      _ <- Entity.i.short(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(short1, short2)
        case (2, stddev) => stddev ==~ stdDevOf(short2, short3, short4)
      })
    } yield ()
  }
}