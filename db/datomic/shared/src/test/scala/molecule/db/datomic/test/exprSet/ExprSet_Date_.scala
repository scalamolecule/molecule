// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import java.util.Date
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        NsSet.n.a1.dates.query.get ==> List(a, b)
      }


      "apply" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.dates(date0).query.get ==> List()
        NsSet.n.a1.dates(date1).query.get ==> List(a)
        NsSet.n.a1.dates(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates(date3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates(Seq(date0)).query.get ==> List()
        NsSet.n.a1.dates(Seq(date1)).query.get ==> List(a)
        NsSet.n.a1.dates(Seq(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(date3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.dates(date1, date2).query.get ==> List(a, b)
        NsSet.n.a1.dates(date1, date3).query.get ==> List(a, b)
        NsSet.n.a1.dates(date2, date3).query.get ==> List(a, b)
        NsSet.n.a1.dates(date1, date2, date3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates(Seq(date1, date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(date1, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(date1, date2, date3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.dates(Set(date1)).query.get ==> List(a)
        NsSet.n.a1.dates(Set(date1, date2)).query.get ==> List(a)
        NsSet.n.a1.dates(Set(date1, date2, date3)).query.get ==> List()
        NsSet.n.a1.dates(Set(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Set(date2, date3)).query.get ==> List(b)
        NsSet.n.a1.dates(Set(date2, date3, date4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates(Seq(Set(date1))).query.get ==> List(a)
        NsSet.n.a1.dates(Seq(Set(date1, date2))).query.get ==> List(a)
        NsSet.n.a1.dates(Seq(Set(date1, date2, date3))).query.get ==> List()
        NsSet.n.a1.dates(Seq(Set(date2))).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(Set(date2, date3))).query.get ==> List(b)
        NsSet.n.a1.dates(Seq(Set(date2, date3, date4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.dates(Set(date1, date2), Set(date0)).query.get ==> List(a)
        NsSet.n.a1.dates(Set(date1, date2), Set(date0, date3)).query.get ==> List(a)
        NsSet.n.a1.dates(Set(date1, date2), Set(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates(Seq(Set(date1, date2), Set(date0))).query.get ==> List(a)
        NsSet.n.a1.dates(Seq(Set(date1, date2), Set(date0, date3))).query.get ==> List(a)
        NsSet.n.a1.dates(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates(Set(date1, date2), Set.empty[Date]).query.get ==> List(a)
        NsSet.n.a1.dates(Seq.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates(Set.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates(Seq.empty[Set[Date]]).query.get ==> List()
      }


      "not" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.dates.not(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(date1).query.get ==> List(b)
        NsSet.n.a1.dates.not(date2).query.get ==> List()
        NsSet.n.a1.dates.not(date3).query.get ==> List(a)
        NsSet.n.a1.dates.not(date4).query.get ==> List(a)
        NsSet.n.a1.dates.not(date5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates.not(Seq(date0)).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Seq(date1)).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq(date2)).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(date3)).query.get ==> List(a)
        NsSet.n.a1.dates.not(Seq(date4)).query.get ==> List(a)
        NsSet.n.a1.dates.not(Seq(date5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.dates.not(date1, date2).query.get ==> List()
        NsSet.n.a1.dates.not(date1, date3).query.get ==> List()
        NsSet.n.a1.dates.not(date1, date4).query.get ==> List()
        NsSet.n.a1.dates.not(date1, date5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates.not(Seq(date1, date2)).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(date1, date3)).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(date1, date4)).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(date1, date5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.dates.not(Set(date1)).query.get ==> List(b)
        NsSet.n.a1.dates.not(Set(date1, date2)).query.get ==> List(b)
        NsSet.n.a1.dates.not(Set(date1, date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Set(date2)).query.get ==> List()
        NsSet.n.a1.dates.not(Set(date2, date3)).query.get ==> List(a)
        NsSet.n.a1.dates.not(Set(date2, date3, date4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.dates.not(Seq(Set(date1))).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq(Set(date1, date2))).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq(Set(date1, date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Seq(Set(date2))).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(Set(date2, date3))).query.get ==> List(a)
        NsSet.n.a1.dates.not(Seq(Set(date2, date3, date4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.dates.not(Set(date1, date2), Set(date0)).query.get ==> List(b)
        NsSet.n.a1.dates.not(Set(date1, date2), Set(date0, date3)).query.get ==> List(b)
        NsSet.n.a1.dates.not(Set(date1, date2), Set(date2, date3)).query.get ==> List()
        NsSet.n.a1.dates.not(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates.not(Seq(Set(date1, date2), Set(date0))).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq(Set(date1, date2), Set(date0, date3))).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List()
        NsSet.n.a1.dates.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.dates.not(Set(date1, date2), Set.empty[Date]).query.get ==> List(b)
        NsSet.n.a1.dates.not(Seq.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Set.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Seq.empty[Set[Date]]).query.get ==> List(a, b)
        NsSet.n.a1.dates.not(Seq(Set.empty[Date])).query.get ==> List(a, b)
      }


      "==" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.dates.==(Set(date1)).query.get ==> List()
        NsSet.n.a1.dates.==(Set(date1, date2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.dates.==(Set(date1, date2, date3)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates.==(Seq(Set(date1))).query.get ==> List()
        NsSet.n.a1.dates.==(Seq(Set(date1, date2))).query.get ==> List(a)
        NsSet.n.a1.dates.==(Seq(Set(date1, date2, date3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates.==(Set(date1), Set(date2, date3)).query.get ==> List()
        NsSet.n.a1.dates.==(Set(date1, date2), Set(date2, date3)).query.get ==> List(a)
        NsSet.n.a1.dates.==(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates.==(Seq(Set(date1), Set(date2, date3))).query.get ==> List()
        NsSet.n.a1.dates.==(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(a)
        NsSet.n.a1.dates.==(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates.==(Set(date1, date2), Set.empty[Date]).query.get ==> List(a)
        NsSet.n.a1.dates.==(Set.empty[Date], Set(date1, date2)).query.get ==> List(a)
        NsSet.n.a1.dates.==(Set.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates.==(Seq.empty[Set[Date]]).query.get ==> List()
        NsSet.n.a1.dates.==(Seq(Set.empty[Date])).query.get ==> List()
      }


      "!=" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.dates.!=(Set(date1)).query.get ==> List(a, b)
        NsSet.n.a1.dates.!=(Set(date1, date2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.dates.!=(Set(date1, date2, date3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates.!=(Seq(Set(date1))).query.get ==> List(a, b)
        NsSet.n.a1.dates.!=(Seq(Set(date1, date2))).query.get ==> List(b)
        NsSet.n.a1.dates.!=(Seq(Set(date1, date2, date3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates.!=(Set(date1), Set(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates.!=(Set(date1, date2), Set(date2, date3)).query.get ==> List(b)
        NsSet.n.a1.dates.!=(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates.!=(Seq(Set(date1), Set(date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates.!=(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(b)
        NsSet.n.a1.dates.!=(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.dates.!=(Seq(Set(date1, date2), Set.empty[Date])).query.get ==> List(b)
        NsSet.n.a1.dates.!=(Set.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates.!=(Seq.empty[Set[Date]]).query.get ==> List(a, b)
      }


      "compare" - cardSet { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        NsSet.n.dates.insert(List(a, b)).transact

        NsSet.n.a1.dates.<(date0).query.get ==> List()
        NsSet.n.a1.dates.<(date1).query.get ==> List()
        NsSet.n.a1.dates.<(date2).query.get ==> List(a)
        NsSet.n.a1.dates.<(date3).query.get ==> List(a, b)

        NsSet.n.a1.dates.<=(date0).query.get ==> List()
        NsSet.n.a1.dates.<=(date1).query.get ==> List(a)
        NsSet.n.a1.dates.<=(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates.<=(date3).query.get ==> List(a, b)

        NsSet.n.a1.dates.>(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates.>(date1).query.get ==> List(a, b)
        NsSet.n.a1.dates.>(date2).query.get ==> List(b)
        NsSet.n.a1.dates.>(date3).query.get ==> List(b)

        NsSet.n.a1.dates.>=(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates.>=(date1).query.get ==> List(a, b)
        NsSet.n.a1.dates.>=(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates.>=(date3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        NsSet.n.a1.dates_.query.get ==> List(a, b)
      }


      "apply" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.dates_(date0).query.get ==> List()
        NsSet.n.a1.dates_(date1).query.get ==> List(a)
        NsSet.n.a1.dates_(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates_(date3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates_(Seq(date0)).query.get ==> List()
        NsSet.n.a1.dates_(Seq(date1)).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(date3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.dates_(date1, date2).query.get ==> List(a, b)
        NsSet.n.a1.dates_(date1, date3).query.get ==> List(a, b)
        NsSet.n.a1.dates_(date2, date3).query.get ==> List(a, b)
        NsSet.n.a1.dates_(date1, date2, date3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_(Seq(date1, date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(date1, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(date1, date2, date3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.dates_(Set(date1)).query.get ==> List(a)
        NsSet.n.a1.dates_(Set(date1, date2)).query.get ==> List(a)
        NsSet.n.a1.dates_(Set(date1, date2, date3)).query.get ==> List()
        NsSet.n.a1.dates_(Set(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Set(date2, date3)).query.get ==> List(b)
        NsSet.n.a1.dates_(Set(date2, date3, date4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates_(Seq(Set(date1))).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq(Set(date1, date2))).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq(Set(date1, date2, date3))).query.get ==> List()
        NsSet.n.a1.dates_(Seq(Set(date2))).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(Set(date2, date3))).query.get ==> List(b)
        NsSet.n.a1.dates_(Seq(Set(date2, date3, date4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.dates_(Set(date1, date2), Set(date0)).query.get ==> List(a)
        NsSet.n.a1.dates_(Set(date1, date2), Set(date0, date3)).query.get ==> List(a)
        NsSet.n.a1.dates_(Set(date1, date2), Set(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_(Seq(Set(date1, date2), Set(date0))).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq(Set(date1, date2), Set(date0, date3))).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates_(Set(date1, date2), Set.empty[Date]).query.get ==> List(a)
        NsSet.n.a1.dates_(Seq.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates_(Set.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates_(Seq.empty[Set[Date]]).query.get ==> List()
      }


      "not" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.dates_.not(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(date1).query.get ==> List(b)
        NsSet.n.a1.dates_.not(date2).query.get ==> List()
        NsSet.n.a1.dates_.not(date3).query.get ==> List(a)
        NsSet.n.a1.dates_.not(date4).query.get ==> List(a)
        NsSet.n.a1.dates_.not(date5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_.not(Seq(date0)).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Seq(date1)).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq(date2)).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(date3)).query.get ==> List(a)
        NsSet.n.a1.dates_.not(Seq(date4)).query.get ==> List(a)
        NsSet.n.a1.dates_.not(Seq(date5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.dates_.not(date1, date2).query.get ==> List()
        NsSet.n.a1.dates_.not(date1, date3).query.get ==> List()
        NsSet.n.a1.dates_.not(date1, date4).query.get ==> List()
        NsSet.n.a1.dates_.not(date1, date5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates_.not(Seq(date1, date2)).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(date1, date3)).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(date1, date4)).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(date1, date5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.dates_.not(Set(date1)).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Set(date1, date2)).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Set(date1, date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Set(date2)).query.get ==> List()
        NsSet.n.a1.dates_.not(Set(date2, date3)).query.get ==> List(a)
        NsSet.n.a1.dates_.not(Set(date2, date3, date4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.dates_.not(Seq(Set(date1))).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2))).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Seq(Set(date2))).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(Set(date2, date3))).query.get ==> List(a)
        NsSet.n.a1.dates_.not(Seq(Set(date2, date3, date4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.dates_.not(Set(date1, date2), Set(date0)).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Set(date1, date2), Set(date0, date3)).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Set(date1, date2), Set(date2, date3)).query.get ==> List()
        NsSet.n.a1.dates_.not(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2), Set(date0))).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2), Set(date0, date3))).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List()
        NsSet.n.a1.dates_.not(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.dates_.not(Set(date1, date2), Set.empty[Date]).query.get ==> List(b)
        NsSet.n.a1.dates_.not(Seq.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Set.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Seq.empty[Set[Date]]).query.get ==> List(a, b)
        NsSet.n.a1.dates_.not(Seq(Set.empty[Date])).query.get ==> List(a, b)
      }


      "==" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.dates_.==(Set(date1)).query.get ==> List()
        NsSet.n.a1.dates_.==(Set(date1, date2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.dates_.==(Set(date1, date2, date3)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates_.==(Seq(Set(date1))).query.get ==> List()
        NsSet.n.a1.dates_.==(Seq(Set(date1, date2))).query.get ==> List(a)
        NsSet.n.a1.dates_.==(Seq(Set(date1, date2, date3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates_.==(Set(date1), Set(date2, date3)).query.get ==> List()
        NsSet.n.a1.dates_.==(Set(date1, date2), Set(date2, date3)).query.get ==> List(a)
        NsSet.n.a1.dates_.==(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_.==(Seq(Set(date1), Set(date2, date3))).query.get ==> List()
        NsSet.n.a1.dates_.==(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(a)
        NsSet.n.a1.dates_.==(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates_.==(Set(date1, date2), Set.empty[Date]).query.get ==> List(a)
        NsSet.n.a1.dates_.==(Set.empty[Date]).query.get ==> List()
        NsSet.n.a1.dates_.==(Seq.empty[Set[Date]]).query.get ==> List()
        NsSet.n.a1.dates_.==(Seq(Set.empty[Date])).query.get ==> List()
      }


      "!=" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.dates_.!=(Set(date1)).query.get ==> List(a, b)
        NsSet.n.a1.dates_.!=(Set(date1, date2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.dates_.!=(Set(date1, date2, date3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_.!=(Seq(Set(date1))).query.get ==> List(a, b)
        NsSet.n.a1.dates_.!=(Seq(Set(date1, date2))).query.get ==> List(b)
        NsSet.n.a1.dates_.!=(Seq(Set(date1, date2, date3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates_.!=(Set(date1), Set(date2, date3)).query.get ==> List(a, b)
        NsSet.n.a1.dates_.!=(Set(date1, date2), Set(date2, date3)).query.get ==> List(b)
        NsSet.n.a1.dates_.!=(Set(date1, date2), Set(date2, date3, date4)).query.get ==> List()
        // Same as
        NsSet.n.a1.dates_.!=(Seq(Set(date1), Set(date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_.!=(Seq(Set(date1, date2), Set(date2, date3))).query.get ==> List(b)
        NsSet.n.a1.dates_.!=(Seq(Set(date1, date2), Set(date2, date3, date4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.dates_.!=(Seq(Set(date1, date2), Set.empty[Date])).query.get ==> List(b)
        NsSet.n.a1.dates_.!=(Set.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates_.!=(Seq.empty[Set[Date]]).query.get ==> List(a, b)
      }


      "compare" - cardSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        NsSet.n.a1.dates_.<(date0).query.get ==> List()
        NsSet.n.a1.dates_.<(date1).query.get ==> List()
        NsSet.n.a1.dates_.<(date2).query.get ==> List(a)
        NsSet.n.a1.dates_.<(date3).query.get ==> List(a, b)

        NsSet.n.a1.dates_.<=(date0).query.get ==> List()
        NsSet.n.a1.dates_.<=(date1).query.get ==> List(a)
        NsSet.n.a1.dates_.<=(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates_.<=(date3).query.get ==> List(a, b)

        NsSet.n.a1.dates_.>(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates_.>(date1).query.get ==> List(a, b)
        NsSet.n.a1.dates_.>(date2).query.get ==> List(b)
        NsSet.n.a1.dates_.>(date3).query.get ==> List(b)

        NsSet.n.a1.dates_.>=(date0).query.get ==> List(a, b)
        NsSet.n.a1.dates_.>=(date1).query.get ==> List(a, b)
        NsSet.n.a1.dates_.>=(date2).query.get ==> List(a, b)
        NsSet.n.a1.dates_.>=(date3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        NsSet.n.a1.dates_?.query.get ==> List(a, b, c)
      }


      "apply" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.dates_?(Some(date0)).query.get ==> List()
        NsSet.n.a1.dates_?(Some(date1)).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(date3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates_?(Some(Seq(date0))).query.get ==> List()
        NsSet.n.a1.dates_?(Some(Seq(date1))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Seq(date2))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(date3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.dates_?(Some(Seq(date1, date2))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(date1, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(date1, date2, date3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.dates_?(Some(Set(date1))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Set(date1, date2))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Set(date1, date2, date3))).query.get ==> List()
        NsSet.n.a1.dates_?(Some(Set(date2))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Set(date2, date3))).query.get ==> List(b)
        NsSet.n.a1.dates_?(Some(Set(date2, date3, date4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.dates_?(Some(Seq(Set(date1)))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2)))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2, date3)))).query.get ==> List()
        NsSet.n.a1.dates_?(Some(Seq(Set(date2)))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(Set(date2, date3)))).query.get ==> List(b)
        NsSet.n.a1.dates_?(Some(Seq(Set(date2, date3, date4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2), Set(date0)))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get ==> List(a)
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates_?(Some(Seq.empty[Date])).query.get ==> List()
        NsSet.n.a1.dates_?(Some(Set.empty[Date])).query.get ==> List()
        NsSet.n.a1.dates_?(Some(Seq.empty[Set[Date]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.dates_?(Option.empty[Date]).query.get ==> List(c)
        NsSet.n.a1.dates_?(Option.empty[Seq[Date]]).query.get ==> List(c)
        NsSet.n.a1.dates_?(Option.empty[Set[Date]]).query.get ==> List(c)
        NsSet.n.a1.dates_?(Option.empty[Seq[Set[Date]]]).query.get ==> List(c)
      }


      "not" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.dates_?.not(Some(date0)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(date1)).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(date2)).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(date3)).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(date4)).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(date5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_?.not(Some(Seq(date0))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Seq(date1))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Seq(date2))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(date3))).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(Seq(date4))).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(Seq(date5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.dates_?.not(Some(Seq(date1, date2))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(date1, date3))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(date1, date4))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(date1, date5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.dates_?.not(Some(Set(date1))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Set(date1, date2))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Set(date1, date2, date3))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Set(date2))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Set(date2, date3))).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(Set(date2, date3, date4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2, date3)))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date2)))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date2, date3)))).query.get ==> List(a)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date2, date3, date4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date0)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date0, date3)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get ==> List()
        NsSet.n.a1.dates_?.not(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.dates_?.not(Some(Seq.empty[Date])).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Set.empty[Date])).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Seq.empty[Set[Date]])).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Some(Seq(Set.empty[Date]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.dates_?.not(Option.empty[Date]).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Option.empty[Seq[Date]]).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Option.empty[Set[Date]]).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.not(Option.empty[Seq[Set[Date]]]).query.get ==> List(a, b)
      }


      "==" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.dates_?.==(Some(Set(date1))).query.get ==> List()
        NsSet.n.a1.dates_?.==(Some(Set(date1, date2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.dates_?.==(Some(Set(date1, date2, date3))).query.get ==> List()
        // Same as
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1)))).query.get ==> List()
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1, date2)))).query.get ==> List(a)
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1, date2, date3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1), Set(date2, date3)))).query.get ==> List()
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get ==> List(a)
        NsSet.n.a1.dates_?.==(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.dates_?.==(Some(Set.empty[Date])).query.get ==> List()
        NsSet.n.a1.dates_?.==(Some(Seq.empty[Set[Date]])).query.get ==> List()
        NsSet.n.a1.dates_?.==(Some(Seq(Set.empty[Date]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.dates_?.==(Option.empty[Set[Date]]).query.get ==> List(c)
        NsSet.n.a1.dates_?.==(Option.empty[Seq[Set[Date]]]).query.get ==> List(c)
      }


      "!=" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.dates_?.!=(Some(Set(date1))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.!=(Some(Set(date1, date2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.dates_?.!=(Some(Set(date1, date2, date3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1)))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1, date2)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1, date2, date3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1), Set(date2, date3)))).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set(date2, date3)))).query.get ==> List(b)
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set(date2, date3, date4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.dates_?.!=(Some(Seq(Set(date1, date2), Set.empty[Date]))).query.get ==> List(b)
        NsSet.n.a1.dates_?.!=(Some(Set.empty[Date])).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.!=(Some(Seq.empty[Set[Date]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.dates_?.==(Option.empty[Set[Date]]).query.get ==> List(c)
        NsSet.n.a1.dates_?.==(Option.empty[Seq[Set[Date]]]).query.get ==> List(c)
      }


      "compare" - cardSet { implicit conn =>
        val a = (1, Some(Set(date1, date2)))
        val b = (2, Some(Set(date2, date3, date4)))
        val c = (3, None)
        NsSet.n.dates_?.insert(a, b, c).transact

        NsSet.n.a1.dates_?.<(Some(date0)).query.get ==> List()
        NsSet.n.a1.dates_?.<(Some(date1)).query.get ==> List()
        NsSet.n.a1.dates_?.<(Some(date2)).query.get ==> List(a)
        NsSet.n.a1.dates_?.<(Some(date3)).query.get ==> List(a, b)

        NsSet.n.a1.dates_?.<=(Some(date0)).query.get ==> List()
        NsSet.n.a1.dates_?.<=(Some(date1)).query.get ==> List(a)
        NsSet.n.a1.dates_?.<=(Some(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.<=(Some(date3)).query.get ==> List(a, b)

        NsSet.n.a1.dates_?.>(Some(date0)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>(Some(date1)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>(Some(date2)).query.get ==> List(b)
        NsSet.n.a1.dates_?.>(Some(date3)).query.get ==> List(b)

        NsSet.n.a1.dates_?.>=(Some(date0)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>=(Some(date1)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>=(Some(date2)).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>=(Some(date3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.dates_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.dates_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}