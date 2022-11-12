// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        NsSet.n.a1.bigDecimals.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bigDecimals(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals(bigDecimal1).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(bigDecimal3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals(Seq(bigDecimal0)).query.get ==> List()
        NsSet.n.a1.bigDecimals(Seq(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(bigDecimal3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bigDecimals(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(bigDecimal1, bigDecimal3).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(bigDecimal2, bigDecimal3).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(bigDecimal1, bigDecimal2, bigDecimal3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(bigDecimal1, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bigDecimals(Set(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals(Set(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Set(bigDecimal2, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal2))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal2, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(a)
        NsSet.n.a1.bigDecimals(Seq.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals(Set.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals(Seq.empty[Set[BigDecimal]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bigDecimals.not(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(bigDecimal1).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(bigDecimal2).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(bigDecimal3).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(bigDecimal4).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(bigDecimal5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal0)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal1)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal4)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bigDecimals.not(bigDecimal1, bigDecimal2).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(bigDecimal1, bigDecimal3).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(bigDecimal1, bigDecimal4).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(bigDecimal1, bigDecimal5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal4)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal2, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal2))).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal2, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.not(Seq.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Set.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Seq.empty[Set[BigDecimal]]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.not(Seq(Set.empty[BigDecimal])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1)).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1))).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.==(Set.empty[BigDecimal], Set(bigDecimal1, bigDecimal2)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.==(Set.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Seq.empty[Set[BigDecimal]]).query.get ==> List()
        NsSet.n.a1.bigDecimals.==(Seq(Set.empty[BigDecimal])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.!=(Set.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.!=(Seq.empty[Set[BigDecimal]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        NsSet.n.bigDecimals.insert(List(a, b)).transact

        NsSet.n.a1.bigDecimals.<(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals.<(bigDecimal1).query.get ==> List()
        NsSet.n.a1.bigDecimals.<(bigDecimal2).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.<(bigDecimal3).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals.<=(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals.<=(bigDecimal1).query.get ==> List(a)
        NsSet.n.a1.bigDecimals.<=(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.<=(bigDecimal3).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals.>(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.>(bigDecimal1).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.>(bigDecimal2).query.get ==> List(b)
        NsSet.n.a1.bigDecimals.>(bigDecimal3).query.get ==> List(b)

        NsSet.n.a1.bigDecimals.>=(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.>=(bigDecimal1).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.>=(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals.>=(bigDecimal3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        NsSet.n.a1.bigDecimals_.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bigDecimals_(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals_(bigDecimal1).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(bigDecimal3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal0)).query.get ==> List()
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bigDecimals_(bigDecimal1, bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(bigDecimal1, bigDecimal3).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(bigDecimal2, bigDecimal3).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(bigDecimal1, bigDecimal2, bigDecimal3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals_(Set(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal2, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal2))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal2, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_(Seq.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals_(Set.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals_(Seq.empty[Set[BigDecimal]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bigDecimals_.not(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(bigDecimal1).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(bigDecimal2).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(bigDecimal3).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(bigDecimal4).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(bigDecimal5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal0)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal1)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal4)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bigDecimals_.not(bigDecimal1, bigDecimal2).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(bigDecimal1, bigDecimal3).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(bigDecimal1, bigDecimal4).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(bigDecimal1, bigDecimal5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal4)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal2, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal2))).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal2, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.not(Seq.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Set.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Seq.empty[Set[BigDecimal]]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.not(Seq(Set.empty[BigDecimal])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1))).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.==(Set.empty[BigDecimal]).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Seq.empty[Set[BigDecimal]]).query.get ==> List()
        NsSet.n.a1.bigDecimals_.==(Seq(Set.empty[BigDecimal])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.!=(Set.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.!=(Seq.empty[Set[BigDecimal]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        NsSet.n.a1.bigDecimals_.<(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals_.<(bigDecimal1).query.get ==> List()
        NsSet.n.a1.bigDecimals_.<(bigDecimal2).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.<(bigDecimal3).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals_.<=(bigDecimal0).query.get ==> List()
        NsSet.n.a1.bigDecimals_.<=(bigDecimal1).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_.<=(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.<=(bigDecimal3).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals_.>(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.>(bigDecimal1).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.>(bigDecimal2).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_.>(bigDecimal3).query.get ==> List(b)

        NsSet.n.a1.bigDecimals_.>=(bigDecimal0).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.>=(bigDecimal1).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.>=(bigDecimal2).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_.>=(bigDecimal3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        NsSet.n.a1.bigDecimals_?.query.get ==> List(a, b, c)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.bigDecimals_?(Some(bigDecimal0)).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(bigDecimal3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal0))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal1))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal2))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal1))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal2))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal2, bigDecimal3))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?(Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2)))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2, bigDecimal3)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals_?(Some(Seq.empty[BigDecimal])).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(Set.empty[BigDecimal])).query.get ==> List()
        NsSet.n.a1.bigDecimals_?(Some(Seq.empty[Set[BigDecimal]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.bigDecimals_?(Option.empty[BigDecimal]).query.get ==> List(c)
        NsSet.n.a1.bigDecimals_?(Option.empty[Seq[BigDecimal]]).query.get ==> List(c)
        NsSet.n.a1.bigDecimals_?(Option.empty[Set[BigDecimal]]).query.get ==> List(c)
        NsSet.n.a1.bigDecimals_?(Option.empty[Seq[Set[BigDecimal]]]).query.get ==> List(c)
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal0)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal1)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal2)).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal3)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal4)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(bigDecimal5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal0))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal1))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal2))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal4))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal2))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal3))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal4))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal1))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal2))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal2, bigDecimal3))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2)))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2, bigDecimal3)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.bigDecimals_?.not(Some(Seq.empty[BigDecimal])).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Set.empty[BigDecimal])).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq.empty[Set[BigDecimal]])).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Some(Seq(Set.empty[BigDecimal]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.bigDecimals_?.not(Option.empty[BigDecimal]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Option.empty[Seq[BigDecimal]]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Option.empty[Set[BigDecimal]]).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.not(Option.empty[Seq[Set[BigDecimal]]]).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.bigDecimals_?.==(Some(Set(bigDecimal1))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.==(Some(Set(bigDecimal1, bigDecimal2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.bigDecimals_?.==(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List()
        // Same as
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1)))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.bigDecimals_?.==(Some(Set.empty[BigDecimal])).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.==(Some(Seq.empty[Set[BigDecimal]])).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.==(Some(Seq(Set.empty[BigDecimal]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.bigDecimals_?.==(Option.empty[Set[BigDecimal]]).query.get ==> List(c)
        NsSet.n.a1.bigDecimals_?.==(Option.empty[Seq[Set[BigDecimal]]]).query.get ==> List(c)
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.bigDecimals_?.!=(Some(Set(bigDecimal1))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Set(bigDecimal1, bigDecimal2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.bigDecimals_?.!=(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1)))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]))).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Set.empty[BigDecimal])).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.!=(Some(Seq.empty[Set[BigDecimal]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.bigDecimals_?.==(Option.empty[Set[BigDecimal]]).query.get ==> List(c)
        NsSet.n.a1.bigDecimals_?.==(Option.empty[Seq[Set[BigDecimal]]]).query.get ==> List(c)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        NsSet.n.bigDecimals_?.insert(a, b, c).transact

        NsSet.n.a1.bigDecimals_?.<(Some(bigDecimal0)).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.<(Some(bigDecimal1)).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.<(Some(bigDecimal2)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.<(Some(bigDecimal3)).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals_?.<=(Some(bigDecimal0)).query.get ==> List()
        NsSet.n.a1.bigDecimals_?.<=(Some(bigDecimal1)).query.get ==> List(a)
        NsSet.n.a1.bigDecimals_?.<=(Some(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.<=(Some(bigDecimal3)).query.get ==> List(a, b)

        NsSet.n.a1.bigDecimals_?.>(Some(bigDecimal0)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>(Some(bigDecimal1)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>(Some(bigDecimal2)).query.get ==> List(b)
        NsSet.n.a1.bigDecimals_?.>(Some(bigDecimal3)).query.get ==> List(b)

        NsSet.n.a1.bigDecimals_?.>=(Some(bigDecimal0)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>=(Some(bigDecimal1)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>=(Some(bigDecimal2)).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>=(Some(bigDecimal3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.bigDecimals_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.bigDecimals_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}