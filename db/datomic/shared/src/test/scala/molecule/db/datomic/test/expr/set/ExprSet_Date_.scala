// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import java.util.Date
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          _ <- Ns.i.a1.dates.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates(Set(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set(date1, date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Set(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates(Set(date2, date3, date4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates(Seq(Set(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq(Set(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates(Seq.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates.not(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(date4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(Seq(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(Seq(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates.not(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(date1, date5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates.not(Set(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Set(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(Set(date2, date3, date4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(Set(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.not(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates.not(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.not(Seq(Set.empty[Date])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates.==(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Set(date1, date2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates.==(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates.==(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.==(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates.==(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.==(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.==(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.==(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates.==(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.==(Set.empty[Date], Set(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.==(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.==(Seq(Set.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates.!=(Set(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.!=(Set(date1, date2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates.!=(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates.!=(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.!=(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.!=(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates.!=(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.!=(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.!=(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dates.insert(List(a, b)).transact

          _ <- Ns.i.a1.dates.<(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.<(date1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.<(date2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.<(date3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates.<=(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates.<=(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates.<=(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.<=(date3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates.>(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.>(date1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.>(date2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates.>(date3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.dates.>=(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.>=(date1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.>=(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates.>=(date3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          _ <- Ns.i.a1.dates_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates_(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates_(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates_(Set(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Set(date1, date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Set(date1, date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Set(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_(Set(date2, date3, date4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_(Seq(Set(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq(Set(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_(Seq.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates_.not(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(date4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Seq(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(Seq(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(Seq(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates_.not(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(date1, date5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates_.not(Set(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Set(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(Set(date2, date3, date4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date2, date3, date4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set(date0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set(date0, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set(date0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set(date0, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates_.not(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.not(Seq(Set.empty[Date])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates_.==(Set(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Set(date1, date2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates_.==(Set(date1, date2, date3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_.==(Set(date1), Set(date2, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.==(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.==(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_.==(Set(date1, date2), Set.empty[Date]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.==(Set.empty[Date]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Seq.empty[Set[Date]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.==(Seq(Set.empty[Date])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates_.!=(Set(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.!=(Set(date1, date2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates_.!=(Set(date1, date2, date3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_.!=(Set(date1), Set(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.!=(Set(date1, date2), Set(date2, date3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.!=(Set(date1, date2), Set(date2, date3, date4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1), Set(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1, date2), Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates_.!=(Seq(Set(date1, date2), Set.empty[Date])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.!=(Set.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.!=(Seq.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.dates.insert(List(
            (a, Set(date1, date2)),
            (b, Set(date2, date3, date4))
          )).transact

          _ <- Ns.i.a1.dates_.<(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.<(date1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.<(date2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.<(date3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates_.<=(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_.<=(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_.<=(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.<=(date3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates_.>(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.>(date1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.>(date2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_.>(date3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.dates_.>=(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.>=(date1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.>=(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_.>=(date3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.dates_?(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(date3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_?(Some(Seq(date0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(date3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dates_?(Some(Seq(date1, date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(date1, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(date1, date2, date3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.dates_?(Some(Set(date1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Set(date1, date2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Set(date1, date2, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Set(date2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Set(date2, date3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?(Some(Set(date2, date3, date4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date2, date3, date4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2), Set(date0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_?(Some(Seq.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Set.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?(Option.empty[Date]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?(Option.empty[Seq[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.dates_?.not(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(date5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date1, date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date1, date3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date1, date4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(date1, date5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1, date2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Set(date2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Set(date2, date3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(Set(date2, date3, date4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date2, date3, date4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dates_?.not(Some(Seq.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Set.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Some(Seq(Set.empty[Date]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.dates_?.not(Option.empty[Date]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Option.empty[Seq[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Option.empty[Set[Date]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.not(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.dates_?.==(Some(Set(date1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.==(Some(Set(date1, date2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.dates_?.==(Some(Set(date1, date2, date3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dates_?.==(Some(Set.empty[Date])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.==(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.==(Some(Seq(Set.empty[Date]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?.==(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?.==(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.dates_?.!=(Some(Set(date1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.!=(Some(Set(date1, date2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.dates_?.!=(Some(Set(date1, date2, date3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1, date2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1, date2, date3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1), Set(date2, date3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set.empty[Date]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.!=(Some(Set.empty[Date])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.!=(Some(Seq.empty[Set[Date]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.dates_?.==(Option.empty[Set[Date]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.dates_?.==(Option.empty[Seq[Set[Date]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        for {
          _ <- Ns.i.dates_?.insert(a, b, c).transact

          _ <- Ns.i.a1.dates_?.<(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.<(Some(date1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.<(Some(date2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.<(Some(date3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates_?.<=(Some(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dates_?.<=(Some(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dates_?.<=(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.<=(Some(date3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.dates_?.>(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>(Some(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>(Some(date2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dates_?.>(Some(date3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.dates_?.>=(Some(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>=(Some(date1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>=(Some(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>=(Some(date3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.dates_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dates_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}