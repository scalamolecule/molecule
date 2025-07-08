// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(bigInt1, bigInt2))
  val b = (2, List(bigInt2, bigInt3, bigInt3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigIntSeq.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.has(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigIntSeq.has(bigInt0, bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.has(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.bigIntSeq.has(List.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.bigIntSeq.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt0, bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt1, bigInt3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.has(List(bigInt3, bigInt4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.bigIntSeq_.has(List.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.bigIntSeq_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}