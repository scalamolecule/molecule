package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

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

      _ <- Entity.i.int.apply(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ int1 + int2
        case (2, sum) => sum ==~ int2 + int3 + int4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int5),
        (2, int9),
      )).transact

      _ <- Entity.int(median).query.get.map(_.head ==~ int2.toString.toDouble) // middle number

      _ <- Entity.i.int(median).query.get.map(_.collect {
        case (1, median) => median ==~ (int1 + int2).toDouble / 2.0 // average of 2 middle numbers
        case (2, median) => median ==~ int5.toString.toDouble // middle number
      })
    } yield ()
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

      _ <- Entity.i.int(avg).query.get.map(_.collect {
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

      _ <- Entity.i.int(variance).query.get.map(_.collect {
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

      _ <- Entity.i.int(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(int1, int2)
        case (2, stddev) => stddev ==~ stdDevOf(int2, int3, int4)
      })
    } yield ()
  }
}