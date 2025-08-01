// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_Double_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(double1, double2))
  val b = (2, List(double2, double3, double3))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.doubleSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.doubleSeq.has(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.has(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.has(double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(double3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.doubleSeq.has(List(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.has(List(double1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.has(List(double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(List(double3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.doubleSeq.has(double0, double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.has(double1, double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(double1, double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(double2, double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(double1, double2, double3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.doubleSeq.has(List(double0, double1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.has(List(double1, double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(List(double1, double3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(List(double2, double3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.has(List(double1, double2, double3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.doubleSeq.has(List.empty[Double]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.doubleSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.doubleSeq.hasNo(double0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.hasNo(double1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.doubleSeq.hasNo(double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(double3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.hasNo(double3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.hasNo(double5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.doubleSeq.hasNo(double1, double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(double1, double5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double1, double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq.hasNo(List(double1, double5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.doubleSeq.hasNo(List.empty[Double]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.doubleSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.doubleSeq_.has(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.has(double1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.has(double2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(double3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSeq_.has(List(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.has(List(double1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.has(List(double2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(List(double3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.doubleSeq_.has(double0, double1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.has(double1, double2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(double1, double3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(double2, double3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(double3, double4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSeq_.has(List(double0, double1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.has(List(double1, double2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(List(double1, double3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(List(double2, double3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.has(List(double3, double4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.doubleSeq_.has(List.empty[Double]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.doubleSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.doubleSeq_.hasNo(double0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.hasNo(double1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.doubleSeq_.hasNo(double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(double3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.hasNo(double3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.hasNo(double5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.doubleSeq_.hasNo(double1, double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(double1, double5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double1, double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSeq_.hasNo(List(double1, double5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.doubleSeq_.hasNo(List.empty[Double]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}