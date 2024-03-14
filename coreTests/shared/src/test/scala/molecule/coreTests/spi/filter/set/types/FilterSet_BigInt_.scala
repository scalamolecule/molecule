// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigInts.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts(Set(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set.empty[BigInt], Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set.empty[BigInt], Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq(Set.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


//          // AND semantics when multiple values in a _Set_
//
//          // "Has this AND that"
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt2)).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(b))
//          // Same as
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2))).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))
//
//
//          // AND/OR semantics with multiple Sets
//
//          // "(has this AND that) OR (has this AND that)"
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
//          // Same as
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
//          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.bigInts.has(Set.empty[BigInt]).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.bigInts.has(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(bigInt4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(bigInt1, bigInt4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigInts.insert(List(
            (1, Set(bigInt1, bigInt2)),
            (2, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // bigInts not asserted for i = 0
          _ <- Ns.i.a1.bigInts_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.bigInts_?.insert(List(
            (0, None),
            (1, Some(Set(bigInt1, bigInt2))),
            (2, Some(Set(bigInt2, bigInt3, bigInt4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.bigInts_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigInts.insert(List(
            (1, Set(bigInt1, bigInt2)),
            (2, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.not(Set.empty[BigInt]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.not(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigInts.insert(List(
            (1, Set(bigInt1, bigInt2)),
            (2, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts_.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.has(bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(bigInt3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigInts.insert(List(
            (1, Set(bigInt1, bigInt2)),
            (2, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigInts_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no bigInt value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.bigInts_?.query.get.map(_ ==> List(
            (2, Some(Set(bigInt2, bigInt3, bigInt4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_?(Some(Set.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set.empty[BigInt]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts_?.has(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(bigInt3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt1, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq.empty[BigInt])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[BigInt]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(bigInt5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt1, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt1, bigInt4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(bigInt1, bigInt5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq.empty[BigInt])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}