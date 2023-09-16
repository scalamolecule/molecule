// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          _ <- Ns.i.a1.chars.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Set(char1, char2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars(Set(char2, char1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char2, char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set(char2, char1), Set(char4, char3, char2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char2, char1), Set(char4, char3, char2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set.empty[Char], Set(char2, char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set.empty[Char], Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq(Set.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars.not(Set(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars.not(Set(char2, char1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars.not(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char2, char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.chars.not(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set(char2, char1), Set(char4, char3, char2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char2, char1), Set(char4, char3, char2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(char3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.has(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars.has(Set(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Set(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Set(char1, char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(Set(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.has(Set(char2, char3, char4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars.has(Seq(Set(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(Seq(Set(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.has(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars.has(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.has(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars.has(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.has(Seq.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.has(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(char4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.hasNo(Seq(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Seq(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(Seq(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(Seq(char5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars.hasNo(Set(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Set(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(Set(char2, char3, char4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.hasNo(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars.hasNo(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.hasNo(Seq(Set.empty[Char])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.chars.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.chars_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.chars_?.insert(List(
            (0, None),
            (1, Some(Set(char1, char2))),
            (2, Some(Set(char2, char3, char4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars_(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Set(char1, char2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.chars_(Set(char2, char1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.chars_(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_(Seq(Set(char2, char1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_(Set(char2, char1), Set(char4, char3, char2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.chars_(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_(Seq(Set(char2, char1), Set(char4, char3, char2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq(Set.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.chars.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars_.not(Set(char1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.chars_.not(Set(char2, char1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.chars_.not(Set(char1, char2, char3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char2, char1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.chars_.not(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.not(Set(char2, char1), Set(char4, char3, char2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char2, char1), Set(char4, char3, char2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.not(Set.empty[Char]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.chars.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(char3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.chars_.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(Seq(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(char3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(char1, char2, char3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.chars_.has(Seq(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars_.has(Set(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Set(char1, char2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Set(char1, char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(Set(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Set(char2, char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.has(Set(char2, char3, char4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(Seq(Set(char2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char2, char3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars_.has(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.has(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_.has(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.has(Seq.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.has(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.chars.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(char5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.chars_.hasNo(Seq(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(Seq(char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(Seq(char5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(char1, char5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.chars_.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars_.hasNo(Set(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Set(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Set(char2, char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(Set(char2, char3, char4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char2, char3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars_.hasNo(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.chars_.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Set.empty[Char]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Seq.empty[Set[Char]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.chars_.hasNo(Seq(Set.empty[Char])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          _ <- Ns.i.a1.chars_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no char value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.chars_?.query.get.map(_ ==> List(
            (2, Some(Set(char2, char3, char4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars_?(Some(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Set(char1, char2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars_?(Some(Set(char2, char1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars_?(Some(Set(char1, char2, char3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char2, char1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char2, char1), Set(char4, char3, char2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_?(Some(Set.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq(Set.empty[Char]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1, char2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars_?.not(Some(Set(char2, char1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char1), Set(char4, char3, char2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set.empty[Char]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Set.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars_?.has(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(char3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char1, char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char1, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars_?.has(Some(Set(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(Set(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.has(Some(Set(char2, char3, char4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char2, char3, char4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2), Set(char0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.has(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_?.has(Some(Seq.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(Set.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.has(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?.has(Option.empty[Char]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?.has(Option.empty[Seq[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?.has(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?.has(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars_?.hasNo(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(char5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char1, char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char1, char4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(char1, char5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set(char2, char3, char4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char2, char3, char4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2), Set(char0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Set.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Some(Seq(Set.empty[Char]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.chars_?.hasNo(Option.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Option.empty[Seq[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Option.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.hasNo(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}