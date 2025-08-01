package molecule.db.compliance.test.aggregation.refNum

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrRefNum_variance(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Variance of distinct values (Set semantics)

  import api.*
  import suite.*

  "ref" - refs {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (1, 2),
        (2, 2),
        (2, 3),
        (2, 4),
      )).transact

      // Variance of all (non-coalesced) values
      _ <- A.B.i(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4))

      _ <- A.i.B.i(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(1, 2)
        case (2, variance) => variance ==~ varianceOf(2, 3, 4)
      })
    } yield ()
  }


  "2nd ref" - refs {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 1, 2),
        (2, 2, 2),
        (2, 2, 3),
        (2, 2, 4),
      )).transact

      _ <- A.B.C.i(variance).query.get.map(_.head ==~ varianceOf(1, 2, 2, 3, 4))

      _ <- A.i.B.i.C.i(variance).query.get.map(_.collect {
        case (1, 1, variance) => variance ==~ varianceOf(1, 2)
        case (2, 2, variance) => variance ==~ varianceOf(2, 3, 4)
      })
    } yield ()
  }


  "multiple refs" - refs {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 2),
        (2, 3, 3),
        (2, 4, 4),
      )).transact

      _ <- A.i.B.i(variance).C.i(variance).query.get.map(_.collect {
        case (1, varianceA, varianceB) =>
          varianceA ==~ varianceOf(1, 2)
          varianceB ==~ varianceOf(1, 2)
        case (2, varianceA, varianceB) =>
          varianceA ==~ varianceOf(2, 3, 4)
          varianceB ==~ varianceOf(2, 3, 4)
      })
    } yield ()
  }


  "backref" - refs {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- A.i.B.i._A.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 2),
        (2, 3, 3),
        (2, 4, 4),
      )).transact

      _ <- A.i.B.i(variance)._A.C.i(variance).query.get.map(_.collect {
        case (1, varianceA, varianceB) =>
          varianceA ==~ varianceOf(1, 2)
          varianceB ==~ varianceOf(1, 2)
        case (2, varianceA, varianceB) =>
          varianceA ==~ varianceOf(2, 3, 4)
          varianceB ==~ varianceOf(2, 3, 4)
      })
    } yield ()
  }
}