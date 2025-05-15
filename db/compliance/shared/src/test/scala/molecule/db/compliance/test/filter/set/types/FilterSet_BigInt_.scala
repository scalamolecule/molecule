// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_BigInt_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(bigInt1, bigInt2))
  val b = (2, Set(bigInt2, bigInt3, bigInt4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigIntSet.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.has(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.has(bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(bigInt3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigIntSet.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.has(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.bigIntSet.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt1, bigInt4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.bigIntSet.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigIntSet_.has(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.has(bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigIntSet_.has(bigInt0, bigInt1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt1, bigInt3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.has(Seq(bigInt3, bigInt4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.bigIntSet_.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigIntSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt1, bigInt4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.bigIntSet_.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}