// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import java.net.URI
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          _ <- Ns.i.a1.uris.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(uri3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris(Seq(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(uri3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris(uri1, uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(uri1, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(uri2, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(uri1, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uris(Set(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set(uri1, uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Set(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Set(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris(Set(uri2, uri3, uri4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris(Seq(Set(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq(Set(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris(Seq(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri0, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris.not(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(uri1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(uri4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(uri5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(Seq(uri4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(Seq(uri5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris.not(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(uri1, uri4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(uri1, uri5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(uri1, uri4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(uri1, uri5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uris.not(Set(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Set(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Set(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri0, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Set.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq(Set.empty[URI])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris.==(Set(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Set(uri1, uri2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uris.==(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris.==(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.==(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.==(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris.==(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.==(Set.empty[URI], Set(uri1, uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.==(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.==(Seq(Set.empty[URI])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris.!=(Set(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.!=(Set(uri1, uri2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uris.!=(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris.!=(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.!=(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.!=(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set.empty[URI])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.!=(Set.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.!=(Seq.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          _ <- Ns.i.a1.uris.<(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.<(uri1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.<(uri2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.<(uri3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris.<=(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.<=(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.<=(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.<=(uri3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris.>(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.>(uri1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.>(uri2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.>(uri3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uris.>=(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.>=(uri1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.>=(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.>=(uri3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          _ <- Ns.i.a1.uris_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris_(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(uri3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_(Seq(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(uri3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris_(uri1, uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(uri1, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(uri2, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(uri1, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uris_(Set(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Set(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Set(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_(Set(uri2, uri3, uri4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_(Seq(Set(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq(Set(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_(Seq(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri0, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_(Seq.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris_.not(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(uri1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(uri4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(uri5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Seq(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(Seq(uri4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(Seq(uri5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris_.not(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(uri1, uri4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(uri1, uri5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(uri1, uri4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(uri1, uri5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uris_.not(Set(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Set(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Set(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri0, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Set.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Seq.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.not(Seq(Set.empty[URI])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris_.==(Set(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Set(uri1, uri2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uris_.==(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_.==(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.==(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.==(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_.==(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.==(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.==(Seq(Set.empty[URI])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris_.!=(Set(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.!=(Set(uri1, uri2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uris_.!=(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_.!=(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.!=(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.!=(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set.empty[URI])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.!=(Set.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.!=(Seq.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uris.insert(List(
            (a, Set(uri1, uri2)),
            (b, Set(uri2, uri3, uri4))
          )).transact

          _ <- Ns.i.a1.uris_.<(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.<(uri1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.<(uri2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.<(uri3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris_.<=(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.<=(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_.<=(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.<=(uri3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris_.>(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.>(uri1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.>(uri2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_.>(uri3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uris_.>=(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.>=(uri1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.>=(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_.>=(uri3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uris_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris_?(Some(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(uri3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_?(Some(Seq(uri0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(uri3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris_?(Some(Seq(uri1, uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(uri1, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uris_?(Some(Set(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Set(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?(Some(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri2, uri3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri2, uri3, uri4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri0, uri3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_?(Some(Seq.empty[URI])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Set.empty[URI])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uris_?(Option.empty[URI]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?(Option.empty[Seq[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?(Option.empty[Set[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris_?.not(Some(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(uri4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(uri5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri1, uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri1, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri1, uri4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(uri1, uri5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri2, uri3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri2, uri3, uri4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri0, uri3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris_?.not(Some(Seq.empty[URI])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Set.empty[URI])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set.empty[URI]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.uris_?.not(Option.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Option.empty[Seq[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Option.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris_?.==(Some(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.==(Some(Set(uri1, uri2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uris_?.==(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_?.==(Some(Set.empty[URI])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.==(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.==(Some(Seq(Set.empty[URI]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uris_?.==(Option.empty[Set[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?.==(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris_?.!=(Some(Set(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.!=(Some(Set(uri1, uri2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uris_?.!=(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set.empty[URI]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.!=(Some(Set.empty[URI])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.!=(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.uris_?.==(Option.empty[Set[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?.==(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uris_?.<(Some(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.<(Some(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.<(Some(uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.<(Some(uri3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris_?.<=(Some(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.<=(Some(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.<=(Some(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.<=(Some(uri3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uris_?.>(Some(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>(Some(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>(Some(uri2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.>(Some(uri3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uris_?.>=(Some(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>=(Some(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>=(Some(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>=(Some(uri3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.uris_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}