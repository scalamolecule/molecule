// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        Ns.i.a1.strings.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.strings(string0).query.get ==> List()
        Ns.i.a1.strings(string1).query.get ==> List(a)
        Ns.i.a1.strings(string2).query.get ==> List(a, b)
        Ns.i.a1.strings(string3).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings(Seq(string0)).query.get ==> List()
        Ns.i.a1.strings(Seq(string1)).query.get ==> List(a)
        Ns.i.a1.strings(Seq(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(string3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.strings(string1, string2).query.get ==> List(a, b)
        Ns.i.a1.strings(string1, string3).query.get ==> List(a, b)
        Ns.i.a1.strings(string2, string3).query.get ==> List(a, b)
        Ns.i.a1.strings(string1, string2, string3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings(Seq(string1, string2)).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(string1, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(string1, string2, string3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.strings(Set(string1)).query.get ==> List(a)
        Ns.i.a1.strings(Set(string1, string2)).query.get ==> List(a)
        Ns.i.a1.strings(Set(string1, string2, string3)).query.get ==> List()
        Ns.i.a1.strings(Set(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings(Set(string2, string3)).query.get ==> List(b)
        Ns.i.a1.strings(Set(string2, string3, string4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings(Seq(Set(string1))).query.get ==> List(a)
        Ns.i.a1.strings(Seq(Set(string1, string2))).query.get ==> List(a)
        Ns.i.a1.strings(Seq(Set(string1, string2, string3))).query.get ==> List()
        Ns.i.a1.strings(Seq(Set(string2))).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(Set(string2, string3))).query.get ==> List(b)
        Ns.i.a1.strings(Seq(Set(string2, string3, string4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.strings(Set(string1, string2), Set(string0)).query.get ==> List(a)
        Ns.i.a1.strings(Set(string1, string2), Set(string0, string3)).query.get ==> List(a)
        Ns.i.a1.strings(Set(string1, string2), Set(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings(Seq(Set(string1, string2), Set(string0))).query.get ==> List(a)
        Ns.i.a1.strings(Seq(Set(string1, string2), Set(string0, string3))).query.get ==> List(a)
        Ns.i.a1.strings(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings(Set(string1, string2), Set.empty[String]).query.get ==> List(a)
        Ns.i.a1.strings(Seq.empty[String]).query.get ==> List()
        Ns.i.a1.strings(Set.empty[String]).query.get ==> List()
        Ns.i.a1.strings(Seq.empty[Set[String]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.strings.not(string0).query.get ==> List(a, b)
        Ns.i.a1.strings.not(string1).query.get ==> List(b)
        Ns.i.a1.strings.not(string2).query.get ==> List()
        Ns.i.a1.strings.not(string3).query.get ==> List(a)
        Ns.i.a1.strings.not(string4).query.get ==> List(a)
        Ns.i.a1.strings.not(string5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings.not(Seq(string0)).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Seq(string1)).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq(string2)).query.get ==> List()
        Ns.i.a1.strings.not(Seq(string3)).query.get ==> List(a)
        Ns.i.a1.strings.not(Seq(string4)).query.get ==> List(a)
        Ns.i.a1.strings.not(Seq(string5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.strings.not(string1, string2).query.get ==> List()
        Ns.i.a1.strings.not(string1, string3).query.get ==> List()
        Ns.i.a1.strings.not(string1, string4).query.get ==> List()
        Ns.i.a1.strings.not(string1, string5).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings.not(Seq(string1, string2)).query.get ==> List()
        Ns.i.a1.strings.not(Seq(string1, string3)).query.get ==> List()
        Ns.i.a1.strings.not(Seq(string1, string4)).query.get ==> List()
        Ns.i.a1.strings.not(Seq(string1, string5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.strings.not(Set(string1)).query.get ==> List(b)
        Ns.i.a1.strings.not(Set(string1, string2)).query.get ==> List(b)
        Ns.i.a1.strings.not(Set(string1, string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Set(string2)).query.get ==> List()
        Ns.i.a1.strings.not(Set(string2, string3)).query.get ==> List(a)
        Ns.i.a1.strings.not(Set(string2, string3, string4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.strings.not(Seq(Set(string1))).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq(Set(string1, string2))).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq(Set(string1, string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Seq(Set(string2))).query.get ==> List()
        Ns.i.a1.strings.not(Seq(Set(string2, string3))).query.get ==> List(a)
        Ns.i.a1.strings.not(Seq(Set(string2, string3, string4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.strings.not(Set(string1, string2), Set(string0)).query.get ==> List(b)
        Ns.i.a1.strings.not(Set(string1, string2), Set(string0, string3)).query.get ==> List(b)
        Ns.i.a1.strings.not(Set(string1, string2), Set(string2, string3)).query.get ==> List()
        Ns.i.a1.strings.not(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List()
        // Same as
        Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string0))).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string0, string3))).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List()
        Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.strings.not(Set(string1, string2), Set.empty[String]).query.get ==> List(b)
        Ns.i.a1.strings.not(Seq.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Set.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Seq.empty[Set[String]]).query.get ==> List(a, b)
        Ns.i.a1.strings.not(Seq(Set.empty[String])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.strings.==(Set(string1)).query.get ==> List()
        Ns.i.a1.strings.==(Set(string1, string2)).query.get ==> List(a) // include exact match
        Ns.i.a1.strings.==(Set(string1, string2, string3)).query.get ==> List()
        // Same as
        Ns.i.a1.strings.==(Seq(Set(string1))).query.get ==> List()
        Ns.i.a1.strings.==(Seq(Set(string1, string2))).query.get ==> List(a)
        Ns.i.a1.strings.==(Seq(Set(string1, string2, string3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings.==(Set(string1), Set(string2, string3)).query.get ==> List()
        Ns.i.a1.strings.==(Set(string1, string2), Set(string2, string3)).query.get ==> List(a)
        Ns.i.a1.strings.==(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings.==(Seq(Set(string1), Set(string2, string3))).query.get ==> List()
        Ns.i.a1.strings.==(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(a)
        Ns.i.a1.strings.==(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings.==(Set(string1, string2), Set.empty[String]).query.get ==> List(a)
        Ns.i.a1.strings.==(Set.empty[String], Set(string1, string2)).query.get ==> List(a)
        Ns.i.a1.strings.==(Set.empty[String]).query.get ==> List()
        Ns.i.a1.strings.==(Seq.empty[Set[String]]).query.get ==> List()
        Ns.i.a1.strings.==(Seq(Set.empty[String])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.strings.!=(Set(string1)).query.get ==> List(a, b)
        Ns.i.a1.strings.!=(Set(string1, string2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.strings.!=(Set(string1, string2, string3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings.!=(Seq(Set(string1))).query.get ==> List(a, b)
        Ns.i.a1.strings.!=(Seq(Set(string1, string2))).query.get ==> List(b)
        Ns.i.a1.strings.!=(Seq(Set(string1, string2, string3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings.!=(Set(string1), Set(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings.!=(Set(string1, string2), Set(string2, string3)).query.get ==> List(b)
        Ns.i.a1.strings.!=(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List()
        // Same as
        Ns.i.a1.strings.!=(Seq(Set(string1), Set(string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings.!=(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(b)
        Ns.i.a1.strings.!=(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.strings.!=(Seq(Set(string1, string2), Set.empty[String])).query.get ==> List(b)
        Ns.i.a1.strings.!=(Set.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings.!=(Seq.empty[Set[String]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        Ns.i.strings.insert(List(a, b)).transact

        Ns.i.a1.strings.<(string0).query.get ==> List()
        Ns.i.a1.strings.<(string1).query.get ==> List()
        Ns.i.a1.strings.<(string2).query.get ==> List(a)
        Ns.i.a1.strings.<(string3).query.get ==> List(a, b)

        Ns.i.a1.strings.<=(string0).query.get ==> List()
        Ns.i.a1.strings.<=(string1).query.get ==> List(a)
        Ns.i.a1.strings.<=(string2).query.get ==> List(a, b)
        Ns.i.a1.strings.<=(string3).query.get ==> List(a, b)

        Ns.i.a1.strings.>(string0).query.get ==> List(a, b)
        Ns.i.a1.strings.>(string1).query.get ==> List(a, b)
        Ns.i.a1.strings.>(string2).query.get ==> List(b)
        Ns.i.a1.strings.>(string3).query.get ==> List(b)

        Ns.i.a1.strings.>=(string0).query.get ==> List(a, b)
        Ns.i.a1.strings.>=(string1).query.get ==> List(a, b)
        Ns.i.a1.strings.>=(string2).query.get ==> List(a, b)
        Ns.i.a1.strings.>=(string3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        Ns.i.a1.strings_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.strings_(string0).query.get ==> List()
        Ns.i.a1.strings_(string1).query.get ==> List(a)
        Ns.i.a1.strings_(string2).query.get ==> List(a, b)
        Ns.i.a1.strings_(string3).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings_(Seq(string0)).query.get ==> List()
        Ns.i.a1.strings_(Seq(string1)).query.get ==> List(a)
        Ns.i.a1.strings_(Seq(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(string3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.strings_(string1, string2).query.get ==> List(a, b)
        Ns.i.a1.strings_(string1, string3).query.get ==> List(a, b)
        Ns.i.a1.strings_(string2, string3).query.get ==> List(a, b)
        Ns.i.a1.strings_(string1, string2, string3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_(Seq(string1, string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(string1, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(string1, string2, string3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.strings_(Set(string1)).query.get ==> List(a)
        Ns.i.a1.strings_(Set(string1, string2)).query.get ==> List(a)
        Ns.i.a1.strings_(Set(string1, string2, string3)).query.get ==> List()
        Ns.i.a1.strings_(Set(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Set(string2, string3)).query.get ==> List(b)
        Ns.i.a1.strings_(Set(string2, string3, string4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings_(Seq(Set(string1))).query.get ==> List(a)
        Ns.i.a1.strings_(Seq(Set(string1, string2))).query.get ==> List(a)
        Ns.i.a1.strings_(Seq(Set(string1, string2, string3))).query.get ==> List()
        Ns.i.a1.strings_(Seq(Set(string2))).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(Set(string2, string3))).query.get ==> List(b)
        Ns.i.a1.strings_(Seq(Set(string2, string3, string4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.strings_(Set(string1, string2), Set(string0)).query.get ==> List(a)
        Ns.i.a1.strings_(Set(string1, string2), Set(string0, string3)).query.get ==> List(a)
        Ns.i.a1.strings_(Set(string1, string2), Set(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings_(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string0))).query.get ==> List(a)
        Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string0, string3))).query.get ==> List(a)
        Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings_(Set(string1, string2), Set.empty[String]).query.get ==> List(a)
        Ns.i.a1.strings_(Seq.empty[String]).query.get ==> List()
        Ns.i.a1.strings_(Set.empty[String]).query.get ==> List()
        Ns.i.a1.strings_(Seq.empty[Set[String]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.strings_.not(string0).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(string1).query.get ==> List(b)
        Ns.i.a1.strings_.not(string2).query.get ==> List()
        Ns.i.a1.strings_.not(string3).query.get ==> List(a)
        Ns.i.a1.strings_.not(string4).query.get ==> List(a)
        Ns.i.a1.strings_.not(string5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_.not(Seq(string0)).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Seq(string1)).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq(string2)).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(string3)).query.get ==> List(a)
        Ns.i.a1.strings_.not(Seq(string4)).query.get ==> List(a)
        Ns.i.a1.strings_.not(Seq(string5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.strings_.not(string1, string2).query.get ==> List()
        Ns.i.a1.strings_.not(string1, string3).query.get ==> List()
        Ns.i.a1.strings_.not(string1, string4).query.get ==> List()
        Ns.i.a1.strings_.not(string1, string5).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings_.not(Seq(string1, string2)).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(string1, string3)).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(string1, string4)).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(string1, string5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.strings_.not(Set(string1)).query.get ==> List(b)
        Ns.i.a1.strings_.not(Set(string1, string2)).query.get ==> List(b)
        Ns.i.a1.strings_.not(Set(string1, string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Set(string2)).query.get ==> List()
        Ns.i.a1.strings_.not(Set(string2, string3)).query.get ==> List(a)
        Ns.i.a1.strings_.not(Set(string2, string3, string4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.strings_.not(Seq(Set(string1))).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq(Set(string1, string2))).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq(Set(string1, string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Seq(Set(string2))).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(Set(string2, string3))).query.get ==> List(a)
        Ns.i.a1.strings_.not(Seq(Set(string2, string3, string4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.strings_.not(Set(string1, string2), Set(string0)).query.get ==> List(b)
        Ns.i.a1.strings_.not(Set(string1, string2), Set(string0, string3)).query.get ==> List(b)
        Ns.i.a1.strings_.not(Set(string1, string2), Set(string2, string3)).query.get ==> List()
        Ns.i.a1.strings_.not(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List()
        // Same as
        Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string0))).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string0, string3))).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List()
        Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.strings_.not(Set(string1, string2), Set.empty[String]).query.get ==> List(b)
        Ns.i.a1.strings_.not(Seq.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Set.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Seq.empty[Set[String]]).query.get ==> List(a, b)
        Ns.i.a1.strings_.not(Seq(Set.empty[String])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.strings_.==(Set(string1)).query.get ==> List()
        Ns.i.a1.strings_.==(Set(string1, string2)).query.get ==> List(a) // include exact match
        Ns.i.a1.strings_.==(Set(string1, string2, string3)).query.get ==> List()
        // Same as
        Ns.i.a1.strings_.==(Seq(Set(string1))).query.get ==> List()
        Ns.i.a1.strings_.==(Seq(Set(string1, string2))).query.get ==> List(a)
        Ns.i.a1.strings_.==(Seq(Set(string1, string2, string3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings_.==(Set(string1), Set(string2, string3)).query.get ==> List()
        Ns.i.a1.strings_.==(Set(string1, string2), Set(string2, string3)).query.get ==> List(a)
        Ns.i.a1.strings_.==(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_.==(Seq(Set(string1), Set(string2, string3))).query.get ==> List()
        Ns.i.a1.strings_.==(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(a)
        Ns.i.a1.strings_.==(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings_.==(Set(string1, string2), Set.empty[String]).query.get ==> List(a)
        Ns.i.a1.strings_.==(Set.empty[String]).query.get ==> List()
        Ns.i.a1.strings_.==(Seq.empty[Set[String]]).query.get ==> List()
        Ns.i.a1.strings_.==(Seq(Set.empty[String])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.strings_.!=(Set(string1)).query.get ==> List(a, b)
        Ns.i.a1.strings_.!=(Set(string1, string2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.strings_.!=(Set(string1, string2, string3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_.!=(Seq(Set(string1))).query.get ==> List(a, b)
        Ns.i.a1.strings_.!=(Seq(Set(string1, string2))).query.get ==> List(b)
        Ns.i.a1.strings_.!=(Seq(Set(string1, string2, string3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings_.!=(Set(string1), Set(string2, string3)).query.get ==> List(a, b)
        Ns.i.a1.strings_.!=(Set(string1, string2), Set(string2, string3)).query.get ==> List(b)
        Ns.i.a1.strings_.!=(Set(string1, string2), Set(string2, string3, string4)).query.get ==> List()
        // Same as
        Ns.i.a1.strings_.!=(Seq(Set(string1), Set(string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_.!=(Seq(Set(string1, string2), Set(string2, string3))).query.get ==> List(b)
        Ns.i.a1.strings_.!=(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.strings_.!=(Seq(Set(string1, string2), Set.empty[String])).query.get ==> List(b)
        Ns.i.a1.strings_.!=(Set.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings_.!=(Seq.empty[Set[String]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        Ns.i.a1.strings_.<(string0).query.get ==> List()
        Ns.i.a1.strings_.<(string1).query.get ==> List()
        Ns.i.a1.strings_.<(string2).query.get ==> List(a)
        Ns.i.a1.strings_.<(string3).query.get ==> List(a, b)

        Ns.i.a1.strings_.<=(string0).query.get ==> List()
        Ns.i.a1.strings_.<=(string1).query.get ==> List(a)
        Ns.i.a1.strings_.<=(string2).query.get ==> List(a, b)
        Ns.i.a1.strings_.<=(string3).query.get ==> List(a, b)

        Ns.i.a1.strings_.>(string0).query.get ==> List(a, b)
        Ns.i.a1.strings_.>(string1).query.get ==> List(a, b)
        Ns.i.a1.strings_.>(string2).query.get ==> List(b)
        Ns.i.a1.strings_.>(string3).query.get ==> List(b)

        Ns.i.a1.strings_.>=(string0).query.get ==> List(a, b)
        Ns.i.a1.strings_.>=(string1).query.get ==> List(a, b)
        Ns.i.a1.strings_.>=(string2).query.get ==> List(a, b)
        Ns.i.a1.strings_.>=(string3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        Ns.i.a1.strings_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.strings_?(Some(string0)).query.get ==> List()
        Ns.i.a1.strings_?(Some(string1)).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(string3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings_?(Some(Seq(string0))).query.get ==> List()
        Ns.i.a1.strings_?(Some(Seq(string1))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Seq(string2))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(string3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.strings_?(Some(Seq(string1, string2))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(string1, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(string1, string2, string3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.strings_?(Some(Set(string1))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Set(string1, string2))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Set(string1, string2, string3))).query.get ==> List()
        Ns.i.a1.strings_?(Some(Set(string2))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Set(string2, string3))).query.get ==> List(b)
        Ns.i.a1.strings_?(Some(Set(string2, string3, string4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.strings_?(Some(Seq(Set(string1)))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2)))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2, string3)))).query.get ==> List()
        Ns.i.a1.strings_?(Some(Seq(Set(string2)))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(Set(string2, string3)))).query.get ==> List(b)
        Ns.i.a1.strings_?(Some(Seq(Set(string2, string3, string4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string0)))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string0, string3)))).query.get ==> List(a)
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get ==> List(a, b)
        Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings_?(Some(Seq.empty[String])).query.get ==> List()
        Ns.i.a1.strings_?(Some(Set.empty[String])).query.get ==> List()
        Ns.i.a1.strings_?(Some(Seq.empty[Set[String]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.strings_?(Option.empty[String]).query.get ==> List(c)
        Ns.i.a1.strings_?(Option.empty[Seq[String]]).query.get ==> List(c)
        Ns.i.a1.strings_?(Option.empty[Set[String]]).query.get ==> List(c)
        Ns.i.a1.strings_?(Option.empty[Seq[Set[String]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.strings_?.not(Some(string0)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(string1)).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(string2)).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(string3)).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(string4)).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(string5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_?.not(Some(Seq(string0))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Seq(string1))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Seq(string2))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(string3))).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(Seq(string4))).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(Seq(string5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.strings_?.not(Some(Seq(string1, string2))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(string1, string3))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(string1, string4))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(string1, string5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.strings_?.not(Some(Set(string1))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Set(string1, string2))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Set(string1, string2, string3))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Set(string2))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Set(string2, string3))).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(Set(string2, string3, string4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1)))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2)))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2, string3)))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string2)))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(Set(string2, string3)))).query.get ==> List(a)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string2, string3, string4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string0)))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string0, string3)))).query.get ==> List(b)
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get ==> List()
        Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.strings_?.not(Some(Seq.empty[String])).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Set.empty[String])).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Seq.empty[Set[String]])).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Some(Seq(Set.empty[String]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.strings_?.not(Option.empty[String]).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Option.empty[Seq[String]]).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Option.empty[Set[String]]).query.get ==> List(a, b)
        Ns.i.a1.strings_?.not(Option.empty[Seq[Set[String]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.strings_?.==(Some(Set(string1))).query.get ==> List()
        Ns.i.a1.strings_?.==(Some(Set(string1, string2))).query.get ==> List(a) // include exact match
        Ns.i.a1.strings_?.==(Some(Set(string1, string2, string3))).query.get ==> List()
        // Same as
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1)))).query.get ==> List()
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1, string2)))).query.get ==> List(a)
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1, string2, string3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1), Set(string2, string3)))).query.get ==> List()
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get ==> List(a)
        Ns.i.a1.strings_?.==(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.strings_?.==(Some(Set.empty[String])).query.get ==> List()
        Ns.i.a1.strings_?.==(Some(Seq.empty[Set[String]])).query.get ==> List()
        Ns.i.a1.strings_?.==(Some(Seq(Set.empty[String]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.strings_?.==(Option.empty[Set[String]]).query.get ==> List(c)
        Ns.i.a1.strings_?.==(Option.empty[Seq[Set[String]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.strings_?.!=(Some(Set(string1))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.!=(Some(Set(string1, string2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.strings_?.!=(Some(Set(string1, string2, string3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1)))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1, string2)))).query.get ==> List(b)
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1, string2, string3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1), Set(string2, string3)))).query.get ==> List(a, b)
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get ==> List(b)
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.strings_?.!=(Some(Seq(Set(string1, string2), Set.empty[String]))).query.get ==> List(b)
        Ns.i.a1.strings_?.!=(Some(Set.empty[String])).query.get ==> List(a, b)
        Ns.i.a1.strings_?.!=(Some(Seq.empty[Set[String]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.strings_?.==(Option.empty[Set[String]]).query.get ==> List(c)
        Ns.i.a1.strings_?.==(Option.empty[Seq[Set[String]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        Ns.i.strings_?.insert(a, b, c).transact

        Ns.i.a1.strings_?.<(Some(string0)).query.get ==> List()
        Ns.i.a1.strings_?.<(Some(string1)).query.get ==> List()
        Ns.i.a1.strings_?.<(Some(string2)).query.get ==> List(a)
        Ns.i.a1.strings_?.<(Some(string3)).query.get ==> List(a, b)

        Ns.i.a1.strings_?.<=(Some(string0)).query.get ==> List()
        Ns.i.a1.strings_?.<=(Some(string1)).query.get ==> List(a)
        Ns.i.a1.strings_?.<=(Some(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.<=(Some(string3)).query.get ==> List(a, b)

        Ns.i.a1.strings_?.>(Some(string0)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>(Some(string1)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>(Some(string2)).query.get ==> List(b)
        Ns.i.a1.strings_?.>(Some(string3)).query.get ==> List(b)

        Ns.i.a1.strings_?.>=(Some(string0)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>=(Some(string1)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>=(Some(string2)).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>=(Some(string3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.strings_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.strings_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.strings_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}