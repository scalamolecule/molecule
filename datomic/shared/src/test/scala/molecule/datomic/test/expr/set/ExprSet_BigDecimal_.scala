// GENERATED CODE ********************************
package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object ExprSet_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals.not(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Set.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.not(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals.==(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.==(Set.empty[BigDecimal], Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.==(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.==(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigDecimals.!=(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.!=(Set.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.!=(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

          _ <- Ns.i.a1.bigDecimals.<(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.<(bigDecimal1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.<(bigDecimal2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.<(bigDecimal3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals.<=(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals.<=(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.<=(bigDecimal3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals.>(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.>(bigDecimal1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.>(bigDecimal2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals.>(bigDecimal3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigDecimals.>=(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.>=(bigDecimal1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.>=(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals.>=(bigDecimal3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          _ <- Ns.i.a1.bigDecimals_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals_(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals_(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals_.not(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Set.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.not(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_.==(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.==(Set.empty[BigDecimal]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.==(Seq(Set.empty[BigDecimal])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.!=(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigDecimals_.!=(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.!=(Set.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.!=(Seq.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.bigDecimals.insert(List(
            (a, Set(bigDecimal1, bigDecimal2)),
            (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          _ <- Ns.i.a1.bigDecimals_.<(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.<(bigDecimal1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.<(bigDecimal2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.<(bigDecimal3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals_.<=(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_.<=(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.<=(bigDecimal3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals_.>(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.>(bigDecimal1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.>(bigDecimal2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_.>(bigDecimal3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigDecimals_.>=(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.>=(bigDecimal1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.>=(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_.>=(bigDecimal3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimals_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no bigDecimal value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.bigDecimals_?.query.get.map(_ ==> List(
            (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[BigDecimal]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(bigDecimal5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(bigDecimal1, bigDecimal5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set(bigDecimal2, bigDecimal3, bigDecimal4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal0, bigDecimal3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Some(Seq(Set.empty[BigDecimal]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.not(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Set(bigDecimal1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.==(Some(Seq(Set.empty[BigDecimal]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimals_?.==(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?.==(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Set(bigDecimal1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Set(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Set(bigDecimal1, bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3, bigDecimal4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq(Set(bigDecimal1, bigDecimal2), Set.empty[BigDecimal]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Set.empty[BigDecimal])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.!=(Some(Seq.empty[Set[BigDecimal]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.bigDecimals_?.==(Option.empty[Set[BigDecimal]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.bigDecimals_?.==(Option.empty[Seq[Set[BigDecimal]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
        val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
        val c = (3, None)
        for {
          _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

          _ <- Ns.i.a1.bigDecimals_?.<(Some(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.<(Some(bigDecimal1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.<(Some(bigDecimal2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.<(Some(bigDecimal3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals_?.<=(Some(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimals_?.<=(Some(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimals_?.<=(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.<=(Some(bigDecimal3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.bigDecimals_?.>(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>(Some(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>(Some(bigDecimal2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimals_?.>(Some(bigDecimal3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.bigDecimals_?.>=(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>=(Some(bigDecimal1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>=(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>=(Some(bigDecimal3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.bigDecimals_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimals_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}