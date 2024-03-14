// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          _ <- Ns.i.a1.uris.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris(Set(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Set(uri1, uri2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uris(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris(Seq(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set.empty[URI], Set(uri1, uri2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris(Set.empty[URI], Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris(Seq(Set.empty[URI])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris.not(Set(uri1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uris.not(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set.empty[URI])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.not(Set.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.not(Seq.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.has(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.has(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(uri3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris.has(Seq(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.has(Seq(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.has(Seq(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(Seq(uri3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris.has(uri1, uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(uri1, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(uri2, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.has(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(Seq(uri1, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(Seq(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.has(Seq(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


//          // AND semantics when multiple values in a _Set_
//
//          // "Has this AND that"
//          _ <- Ns.i.a1.uris.has(Set(uri1)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.uris.has(Set(uri2)).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.uris.has(Set(uri2, uri3)).query.get.map(_ ==> List(b))
//          _ <- Ns.i.a1.uris.has(Set(uri2, uri3, uri4)).query.get.map(_ ==> List(b))
//          // Same as
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri2))).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri2, uri3))).query.get.map(_ ==> List(b))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri2, uri3, uri4))).query.get.map(_ ==> List(b))
//
//
//          // AND/OR semantics with multiple Sets
//
//          // "(has this AND that) OR (has this AND that)"
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2), Set(uri0)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2), Set(uri0, uri3)).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(a, b))
//          // Same as
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2), Set(uri0))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get.map(_ ==> List(a))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(a, b))
//          _ <- Ns.i.a1.uris.has(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
//          _ <- Ns.i.a1.uris.has(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.has(Seq.empty[URI]).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.uris.has(Set.empty[URI]).query.get.map(_ ==> List())
//          _ <- Ns.i.a1.uris.has(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        for {
          _ <- Ns.i.uris.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris.hasNo(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.hasNo(uri1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.hasNo(uri4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.hasNo(uri5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris.hasNo(Seq(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris.hasNo(Seq(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris.hasNo(Seq(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(Seq(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.hasNo(Seq(uri4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris.hasNo(Seq(uri5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(uri1, uri4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris.hasNo(Seq(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(Seq(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(Seq(uri1, uri4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris.hasNo(Seq(uri1, uri5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris.hasNo(Seq.empty[URI]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uris.insert(List(
            (1, Set(uri1, uri2)),
            (2, Set(uri2, uri3, uri4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // uris not asserted for i = 0
          _ <- Ns.i.a1.uris_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.uris_?.insert(List(
            (0, None),
            (1, Some(Set(uri1, uri2))),
            (2, Some(Set(uri2, uri3, uri4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.uris_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris_(Set(uri1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Set(uri1, uri2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.uris_(Set(uri1, uri2, uri3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_(Seq(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uris_(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_(Set(uri1, uri2), Set.empty[URI]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_(Set.empty[URI]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq.empty[Set[URI]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_(Seq(Set.empty[URI])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uris.insert(List(
            (1, Set(uri1, uri2)),
            (2, Set(uri2, uri3, uri4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris_.not(Set(uri1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2, uri3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uris_.not(Set(uri1), Set(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1), Set(uri2, uri3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set.empty[URI])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.not(Set.empty[URI]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.not(Seq.empty[Set[URI]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uris.insert(List(
            (1, Set(uri1, uri2)),
            (2, Set(uri2, uri3, uri4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris_.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.has(uri1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.has(uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(uri3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uris_.has(Seq(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.has(Seq(uri1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.has(Seq(uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(Seq(uri3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(uri1, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(uri1, uri2, uri3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uris_.has(Seq(uri1, uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(Seq(uri1, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(Seq(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.has(Seq(uri1, uri2, uri3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_.has(Seq.empty[URI]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uris.insert(List(
            (1, Set(uri1, uri2)),
            (2, Set(uri2, uri3, uri4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.hasNo(uri1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.hasNo(uri4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.hasNo(uri5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris_.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(uri1, uri4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(uri1, uri5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri1, uri4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_.hasNo(Seq(uri1, uri5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris_.hasNo(Seq.empty[URI]).query.get.map(_ ==> List(1, 2))
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

          // Distinct result even with redundant None's (two i = 3 with no uri value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.uris_?.query.get.map(_ ==> List(
            (2, Some(Set(uri2, uri3, uri4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uris_?(Some(Set(uri1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Set(uri1, uri2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uris_?(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_?(Some(Set.empty[URI])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?(Some(Seq(Set.empty[URI]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uris_?(Option.empty[Set[URI]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1, uri2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uris_?.not(Some(Set(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2, uri3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set.empty[URI]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.not(Some(Set.empty[URI])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Some(Seq.empty[Set[URI]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.uris_?.not(Option.empty[Set[URI]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.not(Option.empty[Seq[Set[URI]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uris_?.has(Some(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.has(Some(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.has(Some(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.has(Some(uri3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri1, uri2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri1, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri2, uri3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.has(Some(Seq(uri1, uri2, uri3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uris_?.has(Some(Seq.empty[URI])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.uris_?.has(Option.empty[URI]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uris_?.has(Option.empty[Seq[URI]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uris_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.hasNo(Some(uri5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri1, uri2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri1, uri3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri1, uri4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq(uri1, uri5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uris_?.hasNo(Some(Seq.empty[URI])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.uris_?.hasNo(Option.empty[URI]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uris_?.hasNo(Option.empty[Seq[URI]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}