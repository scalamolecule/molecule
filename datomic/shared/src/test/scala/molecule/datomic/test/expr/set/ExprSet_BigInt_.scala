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


      "apply" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(bigInt3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts(Set(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Set(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts(Seq.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts.not(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(bigInt4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts.not(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(bigInt1, bigInt4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.not(Seq(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.==(Set.empty[BigInt], Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.==(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.==(Seq(Set.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.!=(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.!=(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        for {
          _ <- Ns.i.bigInts.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigInts.<(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.<(bigInt1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.<(bigInt2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.<(bigInt3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts.<=(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts.<=(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts.<=(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.<=(bigInt3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts.>(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.>(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.>(bigInt2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts.>(bigInt3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigInts.>=(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.>=(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.>=(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts.>=(bigInt3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts_(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(bigInt3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts_(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_(Seq.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts_.not(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(bigInt4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts_.not(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(bigInt1, bigInt4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.not(Seq(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.==(Set.empty[BigInt]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.==(Seq(Set.empty[BigInt])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.!=(Set.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.!=(Seq.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigInts.insert(List(
            (a, Set(bigInt1, bigInt2)),
            (b, Set(bigInt2, bigInt3, bigInt4))
          )).transact

          _ <- Ns.i.a1.bigInts_.<(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.<(bigInt1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.<(bigInt2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.<(bigInt3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts_.<=(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_.<=(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_.<=(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.<=(bigInt3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts_.>(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.>(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.>(bigInt2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_.>(bigInt3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigInts_.>=(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.>=(bigInt1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.>=(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_.>=(bigInt3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigInts_?(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(bigInt3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?(Some(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_?(Some(Seq.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Set.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?(Option.empty[BigInt]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(bigInt5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt2, bigInt3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set(bigInt2, bigInt3, bigInt4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Some(Seq(Set.empty[BigInt]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[BigInt]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[Seq[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.not(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigInts_?.==(Some(Set(bigInt1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.==(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigInts_?.==(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigInts_?.==(Some(Set.empty[BigInt])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.==(Some(Seq(Set.empty[BigInt]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?.==(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.==(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1, bigInt2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1, bigInt2, bigInt3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Set.empty[BigInt])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.!=(Some(Seq.empty[Set[BigInt]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.bigInts_?.==(Option.empty[Set[BigInt]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigInts_?.==(Option.empty[Seq[Set[BigInt]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigInts_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigInts_?.<(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.<(Some(bigInt1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.<(Some(bigInt2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.<(Some(bigInt3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts_?.<=(Some(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigInts_?.<=(Some(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigInts_?.<=(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.<=(Some(bigInt3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigInts_?.>(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>(Some(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>(Some(bigInt2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigInts_?.>(Some(bigInt3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigInts_?.>=(Some(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>=(Some(bigInt1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>=(Some(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>=(Some(bigInt3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.bigInts_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigInts_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}