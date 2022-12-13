// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        Ns.i.a1.bigInts.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.bigInts(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts(bigInt1).query.get ==> List(a)
        Ns.i.a1.bigInts(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts(bigInt3).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts(Seq(bigInt0)).query.get ==> List()
        Ns.i.a1.bigInts(Seq(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(bigInt3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.bigInts(bigInt1, bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts(bigInt1, bigInt3).query.get ==> List(a, b)
        Ns.i.a1.bigInts(bigInt2, bigInt3).query.get ==> List(a, b)
        Ns.i.a1.bigInts(bigInt1, bigInt2, bigInt3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(bigInt1, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.bigInts(Set(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2)).query.get ==> List(a)
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts(Set(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Set(bigInt2, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts(Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts(Seq(Set(bigInt1))).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2))).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts(Seq(Set(bigInt2))).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(Set(bigInt2, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt0)).query.get ==> List(a)
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(a)
        Ns.i.a1.bigInts(Seq.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts(Set.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts(Seq.empty[Set[BigInt]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.bigInts.not(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(bigInt1).query.get ==> List(b)
        Ns.i.a1.bigInts.not(bigInt2).query.get ==> List()
        Ns.i.a1.bigInts.not(bigInt3).query.get ==> List(a)
        Ns.i.a1.bigInts.not(bigInt4).query.get ==> List(a)
        Ns.i.a1.bigInts.not(bigInt5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts.not(Seq(bigInt0)).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Seq(bigInt1)).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq(bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts.not(Seq(bigInt4)).query.get ==> List(a)
        Ns.i.a1.bigInts.not(Seq(bigInt5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.bigInts.not(bigInt1, bigInt2).query.get ==> List()
        Ns.i.a1.bigInts.not(bigInt1, bigInt3).query.get ==> List()
        Ns.i.a1.bigInts.not(bigInt1, bigInt4).query.get ==> List()
        Ns.i.a1.bigInts.not(bigInt1, bigInt5).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt4)).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(bigInt1, bigInt5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.bigInts.not(Set(bigInt1)).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2)).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Set(bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts.not(Set(bigInt2, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts.not(Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1))).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2))).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt2))).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(Set(bigInt2, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt0)).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.bigInts.not(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(b)
        Ns.i.a1.bigInts.not(Seq.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Set.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Seq.empty[Set[BigInt]]).query.get ==> List(a, b)
        Ns.i.a1.bigInts.not(Seq(Set.empty[BigInt])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.bigInts.==(Set(bigInt1)).query.get ==> List()
        Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2)).query.get ==> List(a) // include exact match
        Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1))).query.get ==> List()
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2))).query.get ==> List(a)
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts.==(Set(bigInt1), Set(bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts.==(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(a)
        Ns.i.a1.bigInts.==(Set.empty[BigInt], Set(bigInt1, bigInt2)).query.get ==> List(a)
        Ns.i.a1.bigInts.==(Set.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts.==(Seq.empty[Set[BigInt]]).query.get ==> List()
        Ns.i.a1.bigInts.==(Seq(Set.empty[BigInt])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.bigInts.!=(Set(bigInt1)).query.get ==> List(a, b)
        Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1))).query.get ==> List(a, b)
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2))).query.get ==> List(b)
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts.!=(Set(bigInt1), Set(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.bigInts.!=(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get ==> List(b)
        Ns.i.a1.bigInts.!=(Set.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts.!=(Seq.empty[Set[BigInt]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(bigInt1, bigInt2))
        val b = (2, Set(bigInt2, bigInt3, bigInt4))
        Ns.i.bigInts.insert(List(a, b)).transact

        Ns.i.a1.bigInts.<(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts.<(bigInt1).query.get ==> List()
        Ns.i.a1.bigInts.<(bigInt2).query.get ==> List(a)
        Ns.i.a1.bigInts.<(bigInt3).query.get ==> List(a, b)

        Ns.i.a1.bigInts.<=(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts.<=(bigInt1).query.get ==> List(a)
        Ns.i.a1.bigInts.<=(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts.<=(bigInt3).query.get ==> List(a, b)

        Ns.i.a1.bigInts.>(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts.>(bigInt1).query.get ==> List(a, b)
        Ns.i.a1.bigInts.>(bigInt2).query.get ==> List(b)
        Ns.i.a1.bigInts.>(bigInt3).query.get ==> List(b)

        Ns.i.a1.bigInts.>=(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts.>=(bigInt1).query.get ==> List(a, b)
        Ns.i.a1.bigInts.>=(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts.>=(bigInt3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        Ns.i.a1.bigInts_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.bigInts_(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts_(bigInt1).query.get ==> List(a)
        Ns.i.a1.bigInts_(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(bigInt3).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts_(Seq(bigInt0)).query.get ==> List()
        Ns.i.a1.bigInts_(Seq(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(bigInt3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.bigInts_(bigInt1, bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(bigInt1, bigInt3).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(bigInt2, bigInt3).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(bigInt1, bigInt2, bigInt3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_(Seq(bigInt1, bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(bigInt1, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.bigInts_(Set(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2)).query.get ==> List(a)
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts_(Set(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Set(bigInt2, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts_(Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts_(Seq(Set(bigInt1))).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2))).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts_(Seq(Set(bigInt2))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(Set(bigInt2, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts_(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt0)).query.get ==> List(a)
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts_(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(a)
        Ns.i.a1.bigInts_(Seq.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts_(Set.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts_(Seq.empty[Set[BigInt]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.bigInts_.not(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(bigInt1).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(bigInt2).query.get ==> List()
        Ns.i.a1.bigInts_.not(bigInt3).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(bigInt4).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(bigInt5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_.not(Seq(bigInt0)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Seq(bigInt1)).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq(bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(Seq(bigInt4)).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(Seq(bigInt5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.bigInts_.not(bigInt1, bigInt2).query.get ==> List()
        Ns.i.a1.bigInts_.not(bigInt1, bigInt3).query.get ==> List()
        Ns.i.a1.bigInts_.not(bigInt1, bigInt4).query.get ==> List()
        Ns.i.a1.bigInts_.not(bigInt1, bigInt5).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt4)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(bigInt1, bigInt5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.bigInts_.not(Set(bigInt1)).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2)).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Set(bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Set(bigInt2, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1))).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2))).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt2))).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt2, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt0)).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0))).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts_.not(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.bigInts_.not(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(b)
        Ns.i.a1.bigInts_.not(Seq.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Set.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Seq.empty[Set[BigInt]]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.not(Seq(Set.empty[BigInt])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.bigInts_.==(Set(bigInt1)).query.get ==> List()
        Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2)).query.get ==> List(a) // include exact match
        Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1))).query.get ==> List()
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2))).query.get ==> List(a)
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts_.==(Set(bigInt1), Set(bigInt2, bigInt3)).query.get ==> List()
        Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts_.==(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts_.==(Set(bigInt1, bigInt2), Set.empty[BigInt]).query.get ==> List(a)
        Ns.i.a1.bigInts_.==(Set.empty[BigInt]).query.get ==> List()
        Ns.i.a1.bigInts_.==(Seq.empty[Set[BigInt]]).query.get ==> List()
        Ns.i.a1.bigInts_.==(Seq(Set.empty[BigInt])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.bigInts_.!=(Set(bigInt1)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2, bigInt3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2))).query.get ==> List(b)
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts_.!=(Set(bigInt1), Set(bigInt2, bigInt3)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)).query.get ==> List(b)
        Ns.i.a1.bigInts_.!=(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1), Set(bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.bigInts_.!=(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt])).query.get ==> List(b)
        Ns.i.a1.bigInts_.!=(Set.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.!=(Seq.empty[Set[BigInt]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        Ns.i.a1.bigInts_.<(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts_.<(bigInt1).query.get ==> List()
        Ns.i.a1.bigInts_.<(bigInt2).query.get ==> List(a)
        Ns.i.a1.bigInts_.<(bigInt3).query.get ==> List(a, b)

        Ns.i.a1.bigInts_.<=(bigInt0).query.get ==> List()
        Ns.i.a1.bigInts_.<=(bigInt1).query.get ==> List(a)
        Ns.i.a1.bigInts_.<=(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.<=(bigInt3).query.get ==> List(a, b)

        Ns.i.a1.bigInts_.>(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.>(bigInt1).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.>(bigInt2).query.get ==> List(b)
        Ns.i.a1.bigInts_.>(bigInt3).query.get ==> List(b)

        Ns.i.a1.bigInts_.>=(bigInt0).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.>=(bigInt1).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.>=(bigInt2).query.get ==> List(a, b)
        Ns.i.a1.bigInts_.>=(bigInt3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        Ns.i.a1.bigInts_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.bigInts_?(Some(bigInt0)).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(bigInt3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts_?(Some(Seq(bigInt0))).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(Seq(bigInt1))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Seq(bigInt2))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(bigInt3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt2))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.bigInts_?(Some(Set(bigInt1))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(Set(bigInt2))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Set(bigInt2, bigInt3))).query.get ==> List(b)
        Ns.i.a1.bigInts_?(Some(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2)))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2, bigInt3)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts_?(Some(Seq.empty[BigInt])).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(Set.empty[BigInt])).query.get ==> List()
        Ns.i.a1.bigInts_?(Some(Seq.empty[Set[BigInt]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.bigInts_?(Option.empty[BigInt]).query.get ==> List(c)
        Ns.i.a1.bigInts_?(Option.empty[Seq[BigInt]]).query.get ==> List(c)
        Ns.i.a1.bigInts_?(Option.empty[Set[BigInt]]).query.get ==> List(c)
        Ns.i.a1.bigInts_?(Option.empty[Seq[Set[BigInt]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.bigInts_?.not(Some(bigInt0)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(bigInt1)).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(bigInt2)).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(bigInt3)).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(bigInt4)).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(bigInt5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt0))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt2))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt4))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt2))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt3))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt4))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(bigInt1, bigInt5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt1))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt2))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt2, bigInt3))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(Set(bigInt2, bigInt3, bigInt4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2)))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2, bigInt3)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt0, bigInt3)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get ==> List()
        Ns.i.a1.bigInts_?.not(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.bigInts_?.not(Some(Seq.empty[BigInt])).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Set.empty[BigInt])).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Seq.empty[Set[BigInt]])).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Some(Seq(Set.empty[BigInt]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.bigInts_?.not(Option.empty[BigInt]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Option.empty[Seq[BigInt]]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Option.empty[Set[BigInt]]).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.not(Option.empty[Seq[Set[BigInt]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.bigInts_?.==(Some(Set(bigInt1))).query.get ==> List()
        Ns.i.a1.bigInts_?.==(Some(Set(bigInt1, bigInt2))).query.get ==> List(a) // include exact match
        Ns.i.a1.bigInts_?.==(Some(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List()
        // Same as
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1)))).query.get ==> List()
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get ==> List()
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get ==> List(a)
        Ns.i.a1.bigInts_?.==(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.bigInts_?.==(Some(Set.empty[BigInt])).query.get ==> List()
        Ns.i.a1.bigInts_?.==(Some(Seq.empty[Set[BigInt]])).query.get ==> List()
        Ns.i.a1.bigInts_?.==(Some(Seq(Set.empty[BigInt]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.bigInts_?.==(Option.empty[Set[BigInt]]).query.get ==> List(c)
        Ns.i.a1.bigInts_?.==(Option.empty[Seq[Set[BigInt]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1, bigInt2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.bigInts_?.!=(Some(Set(bigInt1, bigInt2, bigInt3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1)))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2, bigInt3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1), Set(bigInt2, bigInt3)))).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set(bigInt2, bigInt3, bigInt4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.bigInts_?.!=(Some(Seq(Set(bigInt1, bigInt2), Set.empty[BigInt]))).query.get ==> List(b)
        Ns.i.a1.bigInts_?.!=(Some(Set.empty[BigInt])).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.!=(Some(Seq.empty[Set[BigInt]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.bigInts_?.==(Option.empty[Set[BigInt]]).query.get ==> List(c)
        Ns.i.a1.bigInts_?.==(Option.empty[Seq[Set[BigInt]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(bigInt1, bigInt2)))
        val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
        val c = (3, None)
        Ns.i.bigInts_?.insert(a, b, c).transact

        Ns.i.a1.bigInts_?.<(Some(bigInt0)).query.get ==> List()
        Ns.i.a1.bigInts_?.<(Some(bigInt1)).query.get ==> List()
        Ns.i.a1.bigInts_?.<(Some(bigInt2)).query.get ==> List(a)
        Ns.i.a1.bigInts_?.<(Some(bigInt3)).query.get ==> List(a, b)

        Ns.i.a1.bigInts_?.<=(Some(bigInt0)).query.get ==> List()
        Ns.i.a1.bigInts_?.<=(Some(bigInt1)).query.get ==> List(a)
        Ns.i.a1.bigInts_?.<=(Some(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.<=(Some(bigInt3)).query.get ==> List(a, b)

        Ns.i.a1.bigInts_?.>(Some(bigInt0)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>(Some(bigInt1)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>(Some(bigInt2)).query.get ==> List(b)
        Ns.i.a1.bigInts_?.>(Some(bigInt3)).query.get ==> List(b)

        Ns.i.a1.bigInts_?.>=(Some(bigInt0)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>=(Some(bigInt1)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>=(Some(bigInt2)).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>=(Some(bigInt3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.bigInts_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.bigInts_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}