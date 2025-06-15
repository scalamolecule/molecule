package molecule.db.compliance.test.filter.seq.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSeq_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(int1, int2))
  val b = (2, List(int2, int3, int3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.intSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.intSeq.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.has(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.has(int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(int3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intSeq.has(List(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.has(List(int1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.has(List(int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(List(int3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.intSeq.has(int0, int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.has(int1, int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(int1, int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(int2, int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.intSeq.has(List(int0, int1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.has(List(int1, int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(List(int1, int3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(List(int2, int3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.has(List(int1, int2, int3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.intSeq.has(List.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.intSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.intSeq.hasNo(int0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.hasNo(int1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intSeq.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.hasNo(int4).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.intSeq.hasNo(List(int0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.intSeq.hasNo(List(int1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.intSeq.hasNo(List(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(List(int3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.intSeq.hasNo(List(int4)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.intSeq.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(int1, int4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.intSeq.hasNo(List(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(List(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq.hasNo(List(int1, int4)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.intSeq.hasNo(List.empty[Int]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.intSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.intSeq_.has(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.has(int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.has(int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(int3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSeq_.has(List(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.has(List(int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.has(List(int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(List(int3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.intSeq_.has(int0, int1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.has(int1, int2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(int1, int3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(int2, int3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(int3, int4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSeq_.has(List(int0, int1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.has(List(int1, int2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(List(int1, int3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.has(List(int3, int4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.intSeq_.has(List.empty[Int]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.intSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.intSeq_.hasNo(int0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.hasNo(int1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intSeq_.hasNo(int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(int3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.hasNo(int4).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.intSeq_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.intSeq_.hasNo(List(int1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.intSeq_.hasNo(List(int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(List(int3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.intSeq_.hasNo(List(int4)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.intSeq_.hasNo(int1, int2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(int1, int3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(int1, int4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.intSeq_.hasNo(List(int1, int2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.intSeq_.hasNo(List(int1, int4)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.intSeq_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}