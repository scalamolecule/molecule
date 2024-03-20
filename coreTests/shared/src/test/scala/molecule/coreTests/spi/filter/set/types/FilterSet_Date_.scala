// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.dateSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSet(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Set(date1, date2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dateSet(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSet(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet(Set.empty[Date], Set(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet(Set.empty[Date], Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet(Seq(Set.empty[Date])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.dateSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSet.not(Set(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.not(Set(date1, date2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dateSet.not(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet.not(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.not(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dateSet.not(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.not(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.dateSet.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.has(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.has(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSet.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.has(Seq(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.has(Seq(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSet.has(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet.has(Seq.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.dateSet.hasNo(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.hasNo(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(date4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.dateSet.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dateSet.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // dateSet not asserted for i = 0
          _ <- Ns.i.a1.dateSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.dateSet_?.insert(List(
            (0, None),
            (1, Some(Set(date1, date2))),
            (2, Some(Set(date2, date3, date4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.dateSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSet_(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Set(date1, date2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.dateSet_(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1, date2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet_(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_(Seq(Set.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_.not(Set(date1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.not(Set(date1, date2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.dateSet_.not(Set(date1, date2, date3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_.not(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.not(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dateSet_.not(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.not(Set.empty[Date]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.dateSet_.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.has(date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.has(date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSet_.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.has(Seq(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.has(Seq(date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSet_.has(date1, date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date1, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date2, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date1, date2, date3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet_.has(Seq.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.dateSet_.hasNo(date0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.hasNo(date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(date4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(date5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dateSet_.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dateSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no date value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.dateSet_?.query.get.map(_ ==> List(
            (2, Some(Set(date2, date3, date4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dateSet_?(Some(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?(Some(Set(date1, date2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dateSet_?(Some(Set(date1, date2, date3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set(date1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.apply(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet_?(Some(Set.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?(Some(Seq(Set.empty[Date]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dateSet_?(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dateSet_?(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_?.not(Some(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Set(date1, date2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dateSet_?.not(Some(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq(Set(date1, date2), Set.empty[Date]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Set.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.not(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.dateSet_?.not(Option.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.not(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.dateSet_?.has(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.has(Some(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.has(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.has(Some(date3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date1, date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date1, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq(date1, date2, date3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet_?.has(Some(Seq.empty[Date])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.dateSet_?.has(Option.empty[Date]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dateSet_?.has(Option.empty[Seq[Date]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dateSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(date5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date1, date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date1, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date1, date4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq(date1, date5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dateSet_?.hasNo(Some(Seq.empty[Date])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.dateSet_?.hasNo(Option.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet_?.hasNo(Option.empty[Seq[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}