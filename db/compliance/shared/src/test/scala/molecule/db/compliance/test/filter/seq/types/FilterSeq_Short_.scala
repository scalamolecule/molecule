// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSeq_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(short1, short2))
  val b = (2, List(short2, short3, short3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.shortSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.shortSeq.has(short0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.has(short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.has(short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(short3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.shortSeq.has(List(short0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.has(List(short1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.has(List(short2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(List(short3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.shortSeq.has(short0, short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.has(short1, short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(short1, short3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(short2, short3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.shortSeq.has(List(short0, short1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.has(List(short1, short2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(List(short1, short3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(List(short2, short3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.has(List(short1, short2, short3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.shortSeq.has(List.empty[Short]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.shortSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.shortSeq.hasNo(short0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.hasNo(short1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.shortSeq.hasNo(short2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.hasNo(short5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.shortSeq.hasNo(List(short0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.shortSeq.hasNo(List(short1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.shortSeq.hasNo(List(short2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.shortSeq.hasNo(List(short5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.shortSeq.hasNo(short1, short2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(short1, short5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.shortSeq.hasNo(List(short1, short2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq.hasNo(List(short1, short5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.shortSeq.hasNo(List.empty[Short]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.shortSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.shortSeq_.has(short0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.has(short1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.has(short2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(short3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.shortSeq_.has(List(short0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.has(List(short1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.has(List(short2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(List(short3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.shortSeq_.has(short0, short1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.has(short1, short2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(short1, short3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(short2, short3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(short3, short4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.shortSeq_.has(List(short0, short1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.has(List(short1, short2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(List(short1, short3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.has(List(short3, short4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.shortSeq_.has(List.empty[Short]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.shortSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.shortSeq_.hasNo(short0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.hasNo(short1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.shortSeq_.hasNo(short2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.hasNo(short5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.shortSeq_.hasNo(short1, short2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(short1, short5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short1, short2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.shortSeq_.hasNo(List(short1, short5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.shortSeq_.hasNo(List.empty[Short]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}