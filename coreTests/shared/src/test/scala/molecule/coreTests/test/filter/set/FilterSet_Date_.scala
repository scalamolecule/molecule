// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set

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
          _ <- Ns.i.dates.insert(List(a, b)).transact

          _ <- Ns.i.a1.dates.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Set(date1, date2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates(Set(date2, date1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date2, date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set(date2, date1), Set(date4, date3, date2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date2, date1), Set(date4, date3, date2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set.empty[Date], Set(date2, date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set.empty[Date], Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq(Set.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates.not(Set(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates.not(Set(date2, date1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates.not(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date2, date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dates.not(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set(date2, date1), Set(date4, date3, date2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date2, date1), Set(date4, date3, date2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(Seq(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates.has(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.has(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates.has(Set(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Set(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Set(date1, date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(Set(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.has(Set(date2, date3, date4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates.has(Seq(Set(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(Seq(Set(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.has(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates.has(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.has(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates.has(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.has(Seq.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.has(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates.hasNo(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(date4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.hasNo(Seq(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Seq(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(Seq(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(Seq(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates.hasNo(Set(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Set(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(Set(date2, date3, date4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.hasNo(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates.hasNo(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.hasNo(Seq(Set.empty[Date])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dates.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.dates_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.dates_?.insert(List(
            (0, None),
            (1, Some(Set(date1, date2))),
            (2, Some(Set(date2, date3, date4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates_(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Set(date1, date2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.dates_(Set(date2, date1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.dates_(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_(Seq(Set(date2, date1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_(Set(date2, date1), Set(date4, date3, date2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dates_(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_(Seq(Set(date2, date1), Set(date4, date3, date2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq(Set.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dates.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates_.not(Set(date1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.dates_.not(Set(date2, date1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.dates_.not(Set(date1, date2, date3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date2, date1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dates_.not(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.not(Set(date2, date1), Set(date4, date3, date2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date2, date1), Set(date4, date3, date2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.not(Set.empty[Date]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dates.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates_.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(date3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dates_.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(Seq(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq(date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(date3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates_.has(date1, date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(date1, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(date2, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(date1, date2, date3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dates_.has(Seq(date1, date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(date1, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates_.has(Set(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Set(date1, date2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Set(date1, date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(Set(date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Set(date2, date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.has(Set(date2, date3, date4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(Seq(Set(date2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date2, date3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates_.has(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.has(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_.has(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.has(Seq.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.has(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dates.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates_.hasNo(date0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(date4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(date5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dates_.hasNo(Seq(date0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(Seq(date4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(Seq(date5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates_.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(date1, date5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dates_.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates_.hasNo(Set(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Set(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Set(date2, date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(Set(date2, date3, date4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date2, date3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates_.hasNo(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dates_.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Set.empty[Date]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Seq.empty[Set[Date]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dates_.hasNo(Seq(Set.empty[Date])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dates_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no date value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.dates_?.query.get.map(_ ==> List(
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
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates_?(Some(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Set(date1, date2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates_?(Some(Set(date2, date1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates_?(Some(Set(date1, date2, date3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date2, date1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date2, date1), Set(date4, date3, date2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_?(Some(Set.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq(Set.empty[Date]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1, date2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates_?.not(Some(Set(date2, date1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date2, date1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date2, date1), Set(date4, date3, date2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set.empty[Date]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Set.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates_?.has(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(date3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date1, date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date1, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates_?.has(Some(Set(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(Set(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.has(Some(Set(date2, date3, date4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date2, date3, date4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2), Set(date0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.has(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_?.has(Some(Seq.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(Set.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.has(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?.has(Option.empty[Date]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?.has(Option.empty[Seq[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?.has(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?.has(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates_?.hasNo(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(date5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date1, date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date1, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date1, date4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(date1, date5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set(date2, date3, date4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date2, date3, date4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2), Set(date0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Set.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Some(Seq(Set.empty[Date]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.dates_?.hasNo(Option.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Option.empty[Seq[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Option.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.hasNo(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}