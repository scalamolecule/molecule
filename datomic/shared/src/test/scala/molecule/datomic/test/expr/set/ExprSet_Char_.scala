// GENERATED CODE ********************************
package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object ExprSet_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          _ <- Ns.i.a1.chars.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(char3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(char1, char2, char3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars(Set(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set(char1, char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Set(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars(Set(char2, char3, char4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars(Seq(Set(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq(Set(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars(Seq.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars.not(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(char4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(Seq(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(Seq(char5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars.not(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(char1, char5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars.not(Set(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Set(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(Set(char2, char3, char4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(Set(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.not(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars.not(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.not(Seq(Set.empty[Char])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars.==(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Set(char1, char2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars.==(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars.==(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.==(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars.==(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.==(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.==(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.==(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars.==(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.==(Set.empty[Char], Set(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.==(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.==(Seq(Set.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars.!=(Set(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.!=(Set(char1, char2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars.!=(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars.!=(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.!=(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.!=(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars.!=(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.!=(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.!=(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(char1, char2))
        val b = (2, Set(char2, char3, char4))
        for {
          _ <- Ns.i.chars.insert(List(a, b)).transact

          _ <- Ns.i.a1.chars.<(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.<(char1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.<(char2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.<(char3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars.<=(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars.<=(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars.<=(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.<=(char3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars.>(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.>(char1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.>(char2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars.>(char3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.chars.>=(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.>=(char1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.>=(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars.>=(char3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          _ <- Ns.i.a1.chars_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars_(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_(Seq(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(char3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars_(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(char1, char2, char3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars_(Set(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Set(char1, char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Set(char1, char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Set(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_(Set(char2, char3, char4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_(Seq(Set(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq(Set(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_(Seq.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars_.not(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(char4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Seq(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(Seq(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(Seq(char5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars_.not(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(char1, char4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(char1, char4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(char1, char5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars_.not(Set(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Set(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(Set(char2, char3, char4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char2, char3, char4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set(char0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set(char0, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char0, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.not(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars_.not(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.not(Seq(Set.empty[Char])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars_.==(Set(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Set(char1, char2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars_.==(Set(char1, char2, char3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_.==(Set(char1), Set(char2, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.==(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.==(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_.==(Set(char1, char2), Set.empty[Char]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.==(Set.empty[Char]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Seq.empty[Set[Char]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.==(Seq(Set.empty[Char])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars_.!=(Set(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.!=(Set(char1, char2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars_.!=(Set(char1, char2, char3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_.!=(Set(char1), Set(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.!=(Set(char1, char2), Set(char2, char3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.!=(Set(char1, char2), Set(char2, char3, char4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1), Set(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set(char2, char3, char4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars_.!=(Seq(Set(char1, char2), Set.empty[Char])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.!=(Set.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.!=(Seq.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.chars.insert(List(
            (a, Set(char1, char2)),
            (b, Set(char2, char3, char4))
          )).transact

          _ <- Ns.i.a1.chars_.<(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.<(char1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.<(char2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.<(char3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars_.<=(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_.<=(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_.<=(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.<=(char3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars_.>(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.>(char1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.>(char2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_.>(char3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.chars_.>=(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.>=(char1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.>=(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_.>=(char3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.chars_?(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(char3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_?(Some(Seq(char0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(char3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.chars_?(Some(Seq(char1, char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(char1, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(char1, char2, char3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.chars_?(Some(Set(char1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Set(char1, char2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Set(char1, char2, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Set(char2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Set(char2, char3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?(Some(Set(char2, char3, char4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char2, char3, char4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_?(Some(Seq.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Set.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?(Option.empty[Char]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?(Option.empty[Seq[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.chars_?.not(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(char4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(char5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char1, char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char1, char3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char1, char4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(char1, char5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1, char2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Set(char2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Set(char2, char3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(Set(char2, char3, char4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char2, char3, char4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char0, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.chars_?.not(Some(Seq.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Set.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Some(Seq(Set.empty[Char]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.chars_?.not(Option.empty[Char]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Option.empty[Seq[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Option.empty[Set[Char]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.not(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.chars_?.==(Some(Set(char1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.==(Some(Set(char1, char2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.chars_?.==(Some(Set(char1, char2, char3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.chars_?.==(Some(Set.empty[Char])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.==(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.==(Some(Seq(Set.empty[Char]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?.==(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?.==(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.chars_?.!=(Some(Set(char1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.!=(Some(Set(char1, char2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.chars_?.!=(Some(Set(char1, char2, char3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2, char3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1), Set(char2, char3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set(char2, char3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set(char2, char3, char4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.chars_?.!=(Some(Seq(Set(char1, char2), Set.empty[Char]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.!=(Some(Set.empty[Char])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.!=(Some(Seq.empty[Set[Char]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.chars_?.==(Option.empty[Set[Char]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.chars_?.==(Option.empty[Seq[Set[Char]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(char1, char2)))
        val b = (2, Some(Set(char2, char3, char4)))
        val c = (3, None)
        for {
          _ <- Ns.i.chars_?.insert(a, b, c).transact

          _ <- Ns.i.a1.chars_?.<(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.<(Some(char1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.<(Some(char2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.<(Some(char3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars_?.<=(Some(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.chars_?.<=(Some(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.chars_?.<=(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.<=(Some(char3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.chars_?.>(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>(Some(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>(Some(char2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.chars_?.>(Some(char3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.chars_?.>=(Some(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>=(Some(char1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>=(Some(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>=(Some(char3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.chars_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.chars_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}