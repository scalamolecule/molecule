// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_Double_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(double1, double2))
  val b = (2, Set(double2, double3, double4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.doubleSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.doubleSet.has(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.has(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.has(double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(double3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.doubleSet.has(Seq(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.has(Seq(double1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.has(Seq(double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(Seq(double3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.doubleSet.has(double1, double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(double1, double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(double2, double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(double1, double2, double3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.doubleSet.has(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(Seq(double1, double3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.has(Seq(double1, double2, double3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.doubleSet.has(Seq.empty[Double]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.doubleSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.doubleSet.hasNo(double0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.hasNo(double1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.doubleSet.hasNo(double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(double3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.hasNo(double4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.hasNo(double5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.doubleSet.hasNo(double1, double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(double1, double4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(double1, double5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.doubleSet.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.doubleSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.doubleSet_.has(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.has(double1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.has(double2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(double3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSet_.has(Seq(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.has(Seq(double1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.doubleSet_.has(double0, double1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.has(double1, double2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(double1, double3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(double2, double3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(double3, double4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSet_.has(Seq(double0, double1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double1, double2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double1, double3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double2, double3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.has(Seq(double3, double4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.doubleSet_.has(Seq.empty[Double]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.doubleSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.doubleSet_.hasNo(double0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.hasNo(double1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.doubleSet_.hasNo(double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(double3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.hasNo(double4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.hasNo(double5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.doubleSet_.hasNo(double1, double2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(double1, double3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(double1, double4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(double1, double5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double1, double2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double1, double3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double1, double4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq(double1, double5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.doubleSet_.hasNo(Seq.empty[Double]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}