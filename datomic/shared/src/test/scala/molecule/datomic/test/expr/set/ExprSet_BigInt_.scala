// GENERATED CODE ********************************
package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object ExprSet_BigInt_ extends DatomicTestSuite {


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
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
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


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(Set(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts.has(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.has(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
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


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts.hasNo(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasNo(Seq(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // <
          _ <- Ns.i.a1.bigInts.hasLt(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasLt(bigInt1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasLt(bigInt2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasLt(bigInt3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.bigInts.hasLe(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.hasLe(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.hasLe(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasLe(bigInt3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.bigInts.hasGt(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasGt(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasGt(bigInt2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.hasGt(bigInt3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.bigInts.hasGe(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasGe(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasGe(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.hasGe(bigInt3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          _ <- Ns.i.a1.bigInts_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts_.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.has(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_.has(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.has(Seq.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.has(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_.hasNo(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasNo(Seq(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // <
          _ <- Ns.i.a1.bigInts_.hasLt(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasLt(bigInt1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasLt(bigInt2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasLt(bigInt3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.bigInts_.hasLe(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.hasLe(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.hasLe(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasLe(bigInt3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.bigInts_.hasGt(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasGt(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasGt(bigInt2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.hasGt(bigInt3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.bigInts_.hasGe(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasGe(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasGe(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.hasGe(bigInt3).query.get.map(_ ==> List(b))
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

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
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


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(Set.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.has(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[BigInt]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.has(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
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


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Some(Seq(Set.empty[BigInt]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasNo(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // <
          _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt3)).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt3)).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt3)).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt3)).query.get.map(_ ==> List(b))


          // None comparison matches any asserted values
          _ <- Ns.i.a1.bigInts_?.hasLt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasLe(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.hasGe(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}