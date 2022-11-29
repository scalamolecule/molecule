// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        Ns.i.a1.chars.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.chars(char0).query.get ==> List()
        Ns.i.a1.chars(char1).query.get ==> List(a)
        Ns.i.a1.chars(char2).query.get ==> List(a, b)
        Ns.i.a1.chars(char3).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars(Seq(char0)).query.get ==> List()
        Ns.i.a1.chars(Seq(char1)).query.get ==> List(a)
        Ns.i.a1.chars(Seq(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(char3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.chars(char1, char2).query.get ==> List(a, b)
        Ns.i.a1.chars(char1, char3).query.get ==> List(a, b)
        Ns.i.a1.chars(char2, char3).query.get ==> List(a, b)
        Ns.i.a1.chars(char1, char2, char3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars(Seq(char1, char2)).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(char1, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(char1, char2, char3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.chars(Set(char1)).query.get ==> List(a)
        Ns.i.a1.chars(Set(char1, char2)).query.get ==> List(a)
        Ns.i.a1.chars(Set(char1, char2, char3)).query.get ==> List()
        Ns.i.a1.chars(Set(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars(Set(char2, char3)).query.get ==> List(b)
        Ns.i.a1.chars(Set(char2, char3, char4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars(Seq(Set(char1))).query.get ==> List(a)
        Ns.i.a1.chars(Seq(Set(char1, char2))).query.get ==> List(a)
        Ns.i.a1.chars(Seq(Set(char1, char2, char3))).query.get ==> List()
        Ns.i.a1.chars(Seq(Set(char2))).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(Set(char2, char3))).query.get ==> List(b)
        Ns.i.a1.chars(Seq(Set(char2, char3, char4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.chars(Set(char1, char2), Set(char0)).query.get ==> List(a)
        Ns.i.a1.chars(Set(char1, char2), Set(char0, char3)).query.get ==> List(a)
        Ns.i.a1.chars(Set(char1, char2), Set(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars(Seq(Set(char1, char2), Set(char0))).query.get ==> List(a)
        Ns.i.a1.chars(Seq(Set(char1, char2), Set(char0, char3))).query.get ==> List(a)
        Ns.i.a1.chars(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars(Set(char1, char2), Set.empty[Char]).query.get ==> List(a)
        Ns.i.a1.chars(Seq.empty[Char]).query.get ==> List()
        Ns.i.a1.chars(Set.empty[Char]).query.get ==> List()
        Ns.i.a1.chars(Seq.empty[Set[Char]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.chars.not(char0).query.get ==> List(a, b)
        Ns.i.a1.chars.not(char1).query.get ==> List(b)
        Ns.i.a1.chars.not(char2).query.get ==> List()
        Ns.i.a1.chars.not(char3).query.get ==> List(a)
        Ns.i.a1.chars.not(char4).query.get ==> List(a)
        Ns.i.a1.chars.not(char5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars.not(Seq(char0)).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Seq(char1)).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq(char2)).query.get ==> List()
        Ns.i.a1.chars.not(Seq(char3)).query.get ==> List(a)
        Ns.i.a1.chars.not(Seq(char4)).query.get ==> List(a)
        Ns.i.a1.chars.not(Seq(char5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.chars.not(char1, char2).query.get ==> List()
        Ns.i.a1.chars.not(char1, char3).query.get ==> List()
        Ns.i.a1.chars.not(char1, char4).query.get ==> List()
        Ns.i.a1.chars.not(char1, char5).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars.not(Seq(char1, char2)).query.get ==> List()
        Ns.i.a1.chars.not(Seq(char1, char3)).query.get ==> List()
        Ns.i.a1.chars.not(Seq(char1, char4)).query.get ==> List()
        Ns.i.a1.chars.not(Seq(char1, char5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.chars.not(Set(char1)).query.get ==> List(b)
        Ns.i.a1.chars.not(Set(char1, char2)).query.get ==> List(b)
        Ns.i.a1.chars.not(Set(char1, char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Set(char2)).query.get ==> List()
        Ns.i.a1.chars.not(Set(char2, char3)).query.get ==> List(a)
        Ns.i.a1.chars.not(Set(char2, char3, char4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.chars.not(Seq(Set(char1))).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq(Set(char1, char2))).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq(Set(char1, char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Seq(Set(char2))).query.get ==> List()
        Ns.i.a1.chars.not(Seq(Set(char2, char3))).query.get ==> List(a)
        Ns.i.a1.chars.not(Seq(Set(char2, char3, char4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.chars.not(Set(char1, char2), Set(char0)).query.get ==> List(b)
        Ns.i.a1.chars.not(Set(char1, char2), Set(char0, char3)).query.get ==> List(b)
        Ns.i.a1.chars.not(Set(char1, char2), Set(char2, char3)).query.get ==> List()
        Ns.i.a1.chars.not(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List()
        // Same as
        Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char0))).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char0, char3))).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List()
        Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.chars.not(Set(char1, char2), Set.empty[Char]).query.get ==> List(b)
        Ns.i.a1.chars.not(Seq.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Set.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Seq.empty[Set[Char]]).query.get ==> List(a, b)
        Ns.i.a1.chars.not(Seq(Set.empty[Char])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.chars.==(Set(char1)).query.get ==> List()
        Ns.i.a1.chars.==(Set(char1, char2)).query.get ==> List(a) // include exact match
        Ns.i.a1.chars.==(Set(char1, char2, char3)).query.get ==> List()
        // Same as
        Ns.i.a1.chars.==(Seq(Set(char1))).query.get ==> List()
        Ns.i.a1.chars.==(Seq(Set(char1, char2))).query.get ==> List(a)
        Ns.i.a1.chars.==(Seq(Set(char1, char2, char3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars.==(Set(char1), Set(char2, char3)).query.get ==> List()
        Ns.i.a1.chars.==(Set(char1, char2), Set(char2, char3)).query.get ==> List(a)
        Ns.i.a1.chars.==(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars.==(Seq(Set(char1), Set(char2, char3))).query.get ==> List()
        Ns.i.a1.chars.==(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(a)
        Ns.i.a1.chars.==(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars.==(Set(char1, char2), Set.empty[Char]).query.get ==> List(a)
        Ns.i.a1.chars.==(Set.empty[Char], Set(char1, char2)).query.get ==> List(a)
        Ns.i.a1.chars.==(Set.empty[Char]).query.get ==> List()
        Ns.i.a1.chars.==(Seq.empty[Set[Char]]).query.get ==> List()
        Ns.i.a1.chars.==(Seq(Set.empty[Char])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.chars.!=(Set(char1)).query.get ==> List(a, b)
        Ns.i.a1.chars.!=(Set(char1, char2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.chars.!=(Set(char1, char2, char3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars.!=(Seq(Set(char1))).query.get ==> List(a, b)
        Ns.i.a1.chars.!=(Seq(Set(char1, char2))).query.get ==> List(b)
        Ns.i.a1.chars.!=(Seq(Set(char1, char2, char3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars.!=(Set(char1), Set(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars.!=(Set(char1, char2), Set(char2, char3)).query.get ==> List(b)
        Ns.i.a1.chars.!=(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List()
        // Same as
        Ns.i.a1.chars.!=(Seq(Set(char1), Set(char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(b)
        Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set.empty[Char])).query.get ==> List(b)
        Ns.i.a1.chars.!=(Set.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars.!=(Seq.empty[Set[Char]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        Ns.i.chars.insert(List(a, b)).transact

        Ns.i.a1.chars.<(char0).query.get ==> List()
        Ns.i.a1.chars.<(char1).query.get ==> List()
        Ns.i.a1.chars.<(char2).query.get ==> List(a)
        Ns.i.a1.chars.<(char3).query.get ==> List(a, b)

        Ns.i.a1.chars.<=(char0).query.get ==> List()
        Ns.i.a1.chars.<=(char1).query.get ==> List(a)
        Ns.i.a1.chars.<=(char2).query.get ==> List(a, b)
        Ns.i.a1.chars.<=(char3).query.get ==> List(a, b)

        Ns.i.a1.chars.>(char0).query.get ==> List(a, b)
        Ns.i.a1.chars.>(char1).query.get ==> List(a, b)
        Ns.i.a1.chars.>(char2).query.get ==> List(b)
        Ns.i.a1.chars.>(char3).query.get ==> List(b)

        Ns.i.a1.chars.>=(char0).query.get ==> List(a, b)
        Ns.i.a1.chars.>=(char1).query.get ==> List(a, b)
        Ns.i.a1.chars.>=(char2).query.get ==> List(a, b)
        Ns.i.a1.chars.>=(char3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        Ns.i.a1.chars_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.chars_(char0).query.get ==> List()
        Ns.i.a1.chars_(char1).query.get ==> List(a)
        Ns.i.a1.chars_(char2).query.get ==> List(a, b)
        Ns.i.a1.chars_(char3).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars_(Seq(char0)).query.get ==> List()
        Ns.i.a1.chars_(Seq(char1)).query.get ==> List(a)
        Ns.i.a1.chars_(Seq(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(char3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.chars_(char1, char2).query.get ==> List(a, b)
        Ns.i.a1.chars_(char1, char3).query.get ==> List(a, b)
        Ns.i.a1.chars_(char2, char3).query.get ==> List(a, b)
        Ns.i.a1.chars_(char1, char2, char3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_(Seq(char1, char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(char1, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(char1, char2, char3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.chars_(Set(char1)).query.get ==> List(a)
        Ns.i.a1.chars_(Set(char1, char2)).query.get ==> List(a)
        Ns.i.a1.chars_(Set(char1, char2, char3)).query.get ==> List()
        Ns.i.a1.chars_(Set(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Set(char2, char3)).query.get ==> List(b)
        Ns.i.a1.chars_(Set(char2, char3, char4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars_(Seq(Set(char1))).query.get ==> List(a)
        Ns.i.a1.chars_(Seq(Set(char1, char2))).query.get ==> List(a)
        Ns.i.a1.chars_(Seq(Set(char1, char2, char3))).query.get ==> List()
        Ns.i.a1.chars_(Seq(Set(char2))).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(Set(char2, char3))).query.get ==> List(b)
        Ns.i.a1.chars_(Seq(Set(char2, char3, char4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.chars_(Set(char1, char2), Set(char0)).query.get ==> List(a)
        Ns.i.a1.chars_(Set(char1, char2), Set(char0, char3)).query.get ==> List(a)
        Ns.i.a1.chars_(Set(char1, char2), Set(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars_(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char0))).query.get ==> List(a)
        Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char0, char3))).query.get ==> List(a)
        Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars_(Set(char1, char2), Set.empty[Char]).query.get ==> List(a)
        Ns.i.a1.chars_(Seq.empty[Char]).query.get ==> List()
        Ns.i.a1.chars_(Set.empty[Char]).query.get ==> List()
        Ns.i.a1.chars_(Seq.empty[Set[Char]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.chars_.not(char0).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(char1).query.get ==> List(b)
        Ns.i.a1.chars_.not(char2).query.get ==> List()
        Ns.i.a1.chars_.not(char3).query.get ==> List(a)
        Ns.i.a1.chars_.not(char4).query.get ==> List(a)
        Ns.i.a1.chars_.not(char5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_.not(Seq(char0)).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Seq(char1)).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq(char2)).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(char3)).query.get ==> List(a)
        Ns.i.a1.chars_.not(Seq(char4)).query.get ==> List(a)
        Ns.i.a1.chars_.not(Seq(char5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.chars_.not(char1, char2).query.get ==> List()
        Ns.i.a1.chars_.not(char1, char3).query.get ==> List()
        Ns.i.a1.chars_.not(char1, char4).query.get ==> List()
        Ns.i.a1.chars_.not(char1, char5).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars_.not(Seq(char1, char2)).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(char1, char3)).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(char1, char4)).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(char1, char5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.chars_.not(Set(char1)).query.get ==> List(b)
        Ns.i.a1.chars_.not(Set(char1, char2)).query.get ==> List(b)
        Ns.i.a1.chars_.not(Set(char1, char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Set(char2)).query.get ==> List()
        Ns.i.a1.chars_.not(Set(char2, char3)).query.get ==> List(a)
        Ns.i.a1.chars_.not(Set(char2, char3, char4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.chars_.not(Seq(Set(char1))).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq(Set(char1, char2))).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq(Set(char1, char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Seq(Set(char2))).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(Set(char2, char3))).query.get ==> List(a)
        Ns.i.a1.chars_.not(Seq(Set(char2, char3, char4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.chars_.not(Set(char1, char2), Set(char0)).query.get ==> List(b)
        Ns.i.a1.chars_.not(Set(char1, char2), Set(char0, char3)).query.get ==> List(b)
        Ns.i.a1.chars_.not(Set(char1, char2), Set(char2, char3)).query.get ==> List()
        Ns.i.a1.chars_.not(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List()
        // Same as
        Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char0))).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char0, char3))).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List()
        Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.chars_.not(Set(char1, char2), Set.empty[Char]).query.get ==> List(b)
        Ns.i.a1.chars_.not(Seq.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Set.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Seq.empty[Set[Char]]).query.get ==> List(a, b)
        Ns.i.a1.chars_.not(Seq(Set.empty[Char])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.chars_.==(Set(char1)).query.get ==> List()
        Ns.i.a1.chars_.==(Set(char1, char2)).query.get ==> List(a) // include exact match
        Ns.i.a1.chars_.==(Set(char1, char2, char3)).query.get ==> List()
        // Same as
        Ns.i.a1.chars_.==(Seq(Set(char1))).query.get ==> List()
        Ns.i.a1.chars_.==(Seq(Set(char1, char2))).query.get ==> List(a)
        Ns.i.a1.chars_.==(Seq(Set(char1, char2, char3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars_.==(Set(char1), Set(char2, char3)).query.get ==> List()
        Ns.i.a1.chars_.==(Set(char1, char2), Set(char2, char3)).query.get ==> List(a)
        Ns.i.a1.chars_.==(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_.==(Seq(Set(char1), Set(char2, char3))).query.get ==> List()
        Ns.i.a1.chars_.==(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(a)
        Ns.i.a1.chars_.==(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars_.==(Set(char1, char2), Set.empty[Char]).query.get ==> List(a)
        Ns.i.a1.chars_.==(Set.empty[Char]).query.get ==> List()
        Ns.i.a1.chars_.==(Seq.empty[Set[Char]]).query.get ==> List()
        Ns.i.a1.chars_.==(Seq(Set.empty[Char])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.chars_.!=(Set(char1)).query.get ==> List(a, b)
        Ns.i.a1.chars_.!=(Set(char1, char2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.chars_.!=(Set(char1, char2, char3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_.!=(Seq(Set(char1))).query.get ==> List(a, b)
        Ns.i.a1.chars_.!=(Seq(Set(char1, char2))).query.get ==> List(b)
        Ns.i.a1.chars_.!=(Seq(Set(char1, char2, char3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars_.!=(Set(char1), Set(char2, char3)).query.get ==> List(a, b)
        Ns.i.a1.chars_.!=(Set(char1, char2), Set(char2, char3)).query.get ==> List(b)
        Ns.i.a1.chars_.!=(Set(char1, char2), Set(char2, char3, char4)).query.get ==> List()
        // Same as
        Ns.i.a1.chars_.!=(Seq(Set(char1), Set(char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set(char2, char3))).query.get ==> List(b)
        Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set.empty[Char])).query.get ==> List(b)
        Ns.i.a1.chars_.!=(Set.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars_.!=(Seq.empty[Set[Char]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        Ns.i.a1.chars_.<(char0).query.get ==> List()
        Ns.i.a1.chars_.<(char1).query.get ==> List()
        Ns.i.a1.chars_.<(char2).query.get ==> List(a)
        Ns.i.a1.chars_.<(char3).query.get ==> List(a, b)

        Ns.i.a1.chars_.<=(char0).query.get ==> List()
        Ns.i.a1.chars_.<=(char1).query.get ==> List(a)
        Ns.i.a1.chars_.<=(char2).query.get ==> List(a, b)
        Ns.i.a1.chars_.<=(char3).query.get ==> List(a, b)

        Ns.i.a1.chars_.>(char0).query.get ==> List(a, b)
        Ns.i.a1.chars_.>(char1).query.get ==> List(a, b)
        Ns.i.a1.chars_.>(char2).query.get ==> List(b)
        Ns.i.a1.chars_.>(char3).query.get ==> List(b)

        Ns.i.a1.chars_.>=(char0).query.get ==> List(a, b)
        Ns.i.a1.chars_.>=(char1).query.get ==> List(a, b)
        Ns.i.a1.chars_.>=(char2).query.get ==> List(a, b)
        Ns.i.a1.chars_.>=(char3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        Ns.i.a1.chars_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.chars_?(Some(char0)).query.get ==> List()
        Ns.i.a1.chars_?(Some(char1)).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(char3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars_?(Some(Seq(char0))).query.get ==> List()
        Ns.i.a1.chars_?(Some(Seq(char1))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Seq(char2))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(char3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.chars_?(Some(Seq(char1, char2))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(char1, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(char1, char2, char3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.chars_?(Some(Set(char1))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Set(char1, char2))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Set(char1, char2, char3))).query.get ==> List()
        Ns.i.a1.chars_?(Some(Set(char2))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Set(char2, char3))).query.get ==> List(b)
        Ns.i.a1.chars_?(Some(Set(char2, char3, char4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.chars_?(Some(Seq(Set(char1)))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2)))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2, char3)))).query.get ==> List()
        Ns.i.a1.chars_?(Some(Seq(Set(char2)))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(Set(char2, char3)))).query.get ==> List(b)
        Ns.i.a1.chars_?(Some(Seq(Set(char2, char3, char4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char0)))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get ==> List(a)
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get ==> List(a, b)
        Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars_?(Some(Seq.empty[Char])).query.get ==> List()
        Ns.i.a1.chars_?(Some(Set.empty[Char])).query.get ==> List()
        Ns.i.a1.chars_?(Some(Seq.empty[Set[Char]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.chars_?(Option.empty[Char]).query.get ==> List(c)
        Ns.i.a1.chars_?(Option.empty[Seq[Char]]).query.get ==> List(c)
        Ns.i.a1.chars_?(Option.empty[Set[Char]]).query.get ==> List(c)
        Ns.i.a1.chars_?(Option.empty[Seq[Set[Char]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.chars_?.not(Some(char0)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(char1)).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(char2)).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(char3)).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(char4)).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(char5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_?.not(Some(Seq(char0))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Seq(char1))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Seq(char2))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(char3))).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(Seq(char4))).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(Seq(char5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.chars_?.not(Some(Seq(char1, char2))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(char1, char3))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(char1, char4))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(char1, char5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.chars_?.not(Some(Set(char1))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Set(char1, char2))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Set(char1, char2, char3))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Set(char2))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Set(char2, char3))).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(Set(char2, char3, char4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1)))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2)))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2, char3)))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char2)))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char3)))).query.get ==> List(a)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char3, char4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char0)))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get ==> List(b)
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get ==> List()
        Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.chars_?.not(Some(Seq.empty[Char])).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Set.empty[Char])).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Seq.empty[Set[Char]])).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Some(Seq(Set.empty[Char]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.chars_?.not(Option.empty[Char]).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Option.empty[Seq[Char]]).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Option.empty[Set[Char]]).query.get ==> List(a, b)
        Ns.i.a1.chars_?.not(Option.empty[Seq[Set[Char]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.chars_?.==(Some(Set(char1))).query.get ==> List()
        Ns.i.a1.chars_?.==(Some(Set(char1, char2))).query.get ==> List(a) // include exact match
        Ns.i.a1.chars_?.==(Some(Set(char1, char2, char3))).query.get ==> List()
        // Same as
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1)))).query.get ==> List()
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2)))).query.get ==> List(a)
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2, char3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1), Set(char2, char3)))).query.get ==> List()
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get ==> List(a)
        Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.chars_?.==(Some(Set.empty[Char])).query.get ==> List()
        Ns.i.a1.chars_?.==(Some(Seq.empty[Set[Char]])).query.get ==> List()
        Ns.i.a1.chars_?.==(Some(Seq(Set.empty[Char]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.chars_?.==(Option.empty[Set[Char]]).query.get ==> List(c)
        Ns.i.a1.chars_?.==(Option.empty[Seq[Set[Char]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.chars_?.!=(Some(Set(char1))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.!=(Some(Set(char1, char2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.chars_?.!=(Some(Set(char1, char2, char3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1)))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2)))).query.get ==> List(b)
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2, char3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1), Set(char2, char3)))).query.get ==> List(a, b)
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get ==> List(b)
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set.empty[Char]))).query.get ==> List(b)
        Ns.i.a1.chars_?.!=(Some(Set.empty[Char])).query.get ==> List(a, b)
        Ns.i.a1.chars_?.!=(Some(Seq.empty[Set[Char]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.chars_?.==(Option.empty[Set[Char]]).query.get ==> List(c)
        Ns.i.a1.chars_?.==(Option.empty[Seq[Set[Char]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        Ns.i.chars_?.insert(a, b, c).transact

        Ns.i.a1.chars_?.<(Some(char0)).query.get ==> List()
        Ns.i.a1.chars_?.<(Some(char1)).query.get ==> List()
        Ns.i.a1.chars_?.<(Some(char2)).query.get ==> List(a)
        Ns.i.a1.chars_?.<(Some(char3)).query.get ==> List(a, b)

        Ns.i.a1.chars_?.<=(Some(char0)).query.get ==> List()
        Ns.i.a1.chars_?.<=(Some(char1)).query.get ==> List(a)
        Ns.i.a1.chars_?.<=(Some(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.<=(Some(char3)).query.get ==> List(a, b)

        Ns.i.a1.chars_?.>(Some(char0)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>(Some(char1)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>(Some(char2)).query.get ==> List(b)
        Ns.i.a1.chars_?.>(Some(char3)).query.get ==> List(b)

        Ns.i.a1.chars_?.>=(Some(char0)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>=(Some(char1)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>=(Some(char2)).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>=(Some(char3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.chars_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.chars_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.chars_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}