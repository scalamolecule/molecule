// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

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
          _ <- Ns.i.charSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.charSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSet(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Set(char1, char2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.charSet(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSet(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSet(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet(Set.empty[Char], Set(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet(Set.empty[Char], Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet(Seq(Set.empty[Char])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.charSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSet.not(Set(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.not(Set(char1, char2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.charSet.not(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSet.not(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.not(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.charSet.not(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.not(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.has(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.has(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet.has(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.charSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(char4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet.hasNo(Seq(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet.hasNo(Seq(char5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // charSet not asserted for i = 0
          _ <- Ns.i.a1.charSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.charSet_?.insert(List(
            (0, None),
            (1, Some(Set(char1, char2))),
            (2, Some(Set(char2, char3, char4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.charSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSet_(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Set(char1, char2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.charSet_(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSet_(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Seq(Set(char1, char2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSet_(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_(Seq(Set.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSet_.not(Set(char1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.not(Set(char1, char2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.charSet_.not(Set(char1, char2, char3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSet_.not(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.not(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.charSet_.not(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.not(Set.empty[Char]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSet_.has(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.has(Seq(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.has(Seq(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(char1, char2, char3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.has(Seq(char1, char2, char3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_.has(Seq.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.charSet.insert(List(
            (1, Set(char1, char2)),
            (2, Set(char2, char3, char4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(char4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(char5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(char1, char5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_.hasNo(Seq(char1, char5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet_.hasNo(Seq.empty[Char]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.charSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no char value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.charSet_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.charSet_?(Some(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?(Some(Set(char1, char2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.charSet_?(Some(Set(char1, char2, char3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set(char1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.apply(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_?(Some(Set.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?(Some(Seq(Set.empty[Char]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.charSet_?(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.charSet_?(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.charSet_?.not(Some(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.not(Some(Set(char1, char2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.charSet_?.not(Some(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.charSet_?.not(Some(Seq(Set(char1, char2), Set.empty[Char]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.not(Some(Set.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.not(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.charSet_?.not(Option.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.not(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSet_?.has(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.has(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.has(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(char3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.has(Some(Seq(char1, char2, char3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.charSet_?.has(Some(Seq.empty[Char])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.charSet_?.has(Option.empty[Char]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.charSet_?.has(Option.empty[Seq[Char]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.charSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(char5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq(char1, char5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.charSet_?.hasNo(Some(Seq.empty[Char])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.charSet_?.hasNo(Option.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSet_?.hasNo(Option.empty[Seq[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}