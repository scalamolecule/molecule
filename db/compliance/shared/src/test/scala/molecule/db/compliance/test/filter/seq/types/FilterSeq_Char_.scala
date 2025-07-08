// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(char1, char2))
  val b = (2, List(char2, char3, char3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.charSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.charSeq.has(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.has(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.has(char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(char3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.charSeq.has(List(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.has(List(char1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.has(List(char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(List(char3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.charSeq.has(char0, char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.has(char1, char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(char1, char3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(char2, char3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.charSeq.has(List(char0, char1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.has(List(char1, char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(List(char1, char3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(List(char2, char3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.has(List(char1, char2, char3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.charSeq.has(List.empty[Char]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.charSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.charSeq.hasNo(char0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.hasNo(char1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charSeq.hasNo(char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.hasNo(char5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.charSeq.hasNo(List(char0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.charSeq.hasNo(List(char1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.charSeq.hasNo(List(char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.charSeq.hasNo(List(char5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.charSeq.hasNo(char1, char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(char1, char5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.charSeq.hasNo(List(char1, char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq.hasNo(List(char1, char5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.charSeq.hasNo(List.empty[Char]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.charSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.charSeq_.has(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.has(char1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.has(char2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(char3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.charSeq_.has(List(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.has(List(char1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.has(List(char2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(List(char3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.charSeq_.has(char0, char1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.has(char1, char2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(char1, char3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(char2, char3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(char3, char4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.charSeq_.has(List(char0, char1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.has(List(char1, char2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(List(char1, char3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.has(List(char3, char4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.charSeq_.has(List.empty[Char]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.charSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.charSeq_.hasNo(char0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.hasNo(char1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charSeq_.hasNo(char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.hasNo(char5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.charSeq_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.charSeq_.hasNo(List(char1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.charSeq_.hasNo(List(char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.charSeq_.hasNo(List(char5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.charSeq_.hasNo(char1, char2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(char1, char5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.charSeq_.hasNo(List(char1, char2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.charSeq_.hasNo(List(char1, char5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.charSeq_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}