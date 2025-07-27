package molecule.db.compliance.test.filter.set.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSet_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(int1, int2))
  val b = (2, Set(int2, int3, int4))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.intSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.intSet.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.has(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.has(int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(int3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intSet.has(Seq(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.has(Seq(int1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.has(Seq(int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(Seq(int3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.intSet.has(int1, int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(int1, int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(int2, int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.intSet.has(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(Seq(int1, int3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.has(Seq(int1, int2, int3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.intSet.has(Seq.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.intSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.intSet.hasNo(int0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.hasNo(int1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intSet.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.hasNo(int4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.hasNo(int5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.intSet.hasNo(Seq(int0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSet.hasNo(Seq(int1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intSet.hasNo(Seq(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(Seq(int3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.hasNo(Seq(int4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSet.hasNo(Seq(int5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.intSet.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(int1, int4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(int1, int5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intSet.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.intSet.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.intSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.intSet_.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.has(int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.has(int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(int3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSet_.has(Seq(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.has(Seq(int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.has(Seq(int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(Seq(int3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.intSet_.has(int0, int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.has(int1, int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(int1, int3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(int2, int3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(int3, int4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSet_.has(Seq(int0, int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.has(Seq(int1, int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(Seq(int1, int3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(Seq(int2, int3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.has(Seq(int3, int4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.intSet_.has(Seq.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.intSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.intSet_.hasNo(int0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.hasNo(int1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intSet_.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(int3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.hasNo(int4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.hasNo(int5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.intSet_.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(int1, int4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(int1, int5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSet_.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.intSet_.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}