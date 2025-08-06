package molecule.db.compliance.test.aggregation.refNum

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class AggrRefNum_median(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Median of unique values (Set semantics)

  import api.*
  import suite.*

  val average = (int1 + int2).toDouble / 2.0

  "ref" - refs {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (1, 2),
        (2, 2),
        (2, 3),
        (2, 9),
      )).transact

      // Median of all (non-coalesced) values
      _ <- A.B.i(median).query.get.map(_.head ==~ 2)

      _ <- A.i.B.i(median).query.get.map(_.collect {
        case (1, median) => median ==~ average
        case (2, median) => median ==~ 3
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
        (2, 2, 9),
      )).transact

      _ <- A.B.C.i(median).query.get.map(_.head ==~ 2)

      _ <- A.i.B.i.C.i(median).query.get.map(_.collect {
        case (1, 1, median) => median ==~ average
        case (2, 2, median) => median ==~ 3
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
        (2, 9, 9),
      )).transact

      _ <- A.i.a1.B.i(median).C.i(median).query.get.map(_.collect {
        case (1, median1, median2) =>
          median1 ==~ average
          median2 ==~ average
        case (2, median1, median2) =>
          median1 ==~ 3.0
          median2 ==~ 3.0
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
        (2, 9, 9),
      )).transact

      _ <- A.i.a1.B.i(median)._A.C.i(median).query.get.map(_.collect {
        case (1, median1, median2) =>
          median1 ==~ average
          median2 ==~ average
        case (2, median1, median2) =>
          median1 ==~ 3.0
          median2 ==~ 3.0
      })
    } yield ()
  }
}