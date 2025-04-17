// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.Instant
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSet_Instant_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(instant1, instant2))
  val b = (2, Set(instant2, instant3, instant4))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.instantSet.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.has(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.has(instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(instant3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantSet.has(Seq(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.has(Seq(instant1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.has(Seq(instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(Seq(instant3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.instantSet.has(instant1, instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(instant1, instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(instant2, instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(instant1, instant2, instant3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.instantSet.has(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(Seq(instant1, instant3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(Seq(instant2, instant3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.has(Seq(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.instantSet.has(Seq.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.instantSet.hasNo(instant0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.hasNo(instant1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantSet.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.hasNo(instant4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.hasNo(instant5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.instantSet.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(instant1, instant4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.instantSet.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.instantSet_.has(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.has(instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.has(instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(instant3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSet_.has(Seq(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.has(Seq(instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.instantSet_.has(instant0, instant1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(instant1, instant3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(instant3, instant4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSet_.has(Seq(instant0, instant1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant1, instant2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant1, instant3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant2, instant3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.has(Seq(instant3, instant4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.instantSet_.has(Seq.empty[Instant]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.instantSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.instantSet_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.hasNo(instant1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantSet_.hasNo(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(instant3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.hasNo(instant4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.hasNo(instant5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.instantSet_.hasNo(instant1, instant2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(instant1, instant3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(instant1, instant4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(instant1, instant5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instantSet_.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.instantSet_.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}