// GENERATED CODE ********************************
package molecule.datomic.test.filter.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object FilterSet_String_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          _ <- Ns.i.a1.strings.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.strings(Set(string1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Set(string1, string2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.strings(Set(string1, string2, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings(Seq(Set(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Seq(Set(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings(Set(string1), Set(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings(Seq(Set(string1), Set(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings(Set.empty[String], Set(string1, string2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings(Set.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Seq.empty[Set[String]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings(Seq(Set.empty[String])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.strings.not(Set(string1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.not(Set(string1, string2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.strings.not(Set(string1, string2, string3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings.not(Seq(Set(string1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.not(Seq(Set(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.not(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings.not(Set(string1), Set(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.not(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.not(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings.not(Seq(Set(string1), Set(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.not(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.strings.not(Seq(Set(string1, string2), Set.empty[String])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.not(Set.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.not(Seq.empty[Set[String]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.strings.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(string3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings.has(Seq(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(Seq(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(string3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.strings.has(string1, string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(string1, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(string2, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings.has(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(string1, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(string1, string2, string3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.strings.has(Set(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Set(string1, string2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Set(string1, string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(Set(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Set(string2, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.has(Set(string2, string3, string4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings.has(Seq(Set(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(Seq(Set(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(Set(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.has(Seq(Set(string2, string3, string4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.strings.has(Set(string1, string2), Set(string0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Set(string1, string2), Set(string0, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2), Set(string0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2), Set(string0, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.has(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings.has(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.has(Seq.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(Set.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.has(Seq.empty[Set[String]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.strings.hasNo(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(string1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(string4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(string5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings.hasNo(Seq(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Seq(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(Seq(string4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(Seq(string5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.strings.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(string1, string4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(string1, string5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.strings.hasNo(Set(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Set(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Set(string2, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(Set(string2, string3, string4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string2, string3, string4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2), Set(string0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2), Set(string0, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2), Set(string0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2), Set(string0, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasNo(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.strings.hasNo(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Set.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Seq.empty[Set[String]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasNo(Seq(Set.empty[String])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.strings.insert(List(a, b)).transact

          // <
          _ <- Ns.i.a1.strings.hasLt(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasLt(string1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasLt(string2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasLt(string3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.strings.hasLe(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings.hasLe(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings.hasLe(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasLe(string3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.strings.hasGt(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasGt(string1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasGt(string2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings.hasGt(string3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.strings.hasGe(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasGe(string1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasGe(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings.hasGe(string3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          _ <- Ns.i.a1.strings_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.strings_(Set(string1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Set(string1, string2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.strings_(Set(string1, string2, string3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings_(Seq(Set(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Seq(Set(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings_(Set(string1), Set(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_(Seq(Set(string1), Set(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings_(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_(Set.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Seq.empty[Set[String]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_(Seq(Set.empty[String])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.strings_.not(Set(string1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.not(Set(string1, string2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.strings_.not(Set(string1, string2, string3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings_.not(Set(string1), Set(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.not(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.not(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1), Set(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.strings_.not(Seq(Set(string1, string2), Set.empty[String])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.not(Set.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.not(Seq.empty[Set[String]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.strings_.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(string3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings_.has(Seq(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(Seq(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(string3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.strings_.has(string1, string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(string1, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(string2, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_.has(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(string1, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(string1, string2, string3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.strings_.has(Set(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Set(string1, string2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Set(string1, string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(Set(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Set(string2, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.has(Set(string2, string3, string4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(Seq(Set(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string2, string3, string4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.strings_.has(Set(string1, string2), Set(string0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Set(string1, string2), Set(string0, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2), Set(string0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2), Set(string0, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.has(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings_.has(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.has(Seq.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(Set.empty[String]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.has(Seq.empty[Set[String]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.strings_.hasNo(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(string1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(string4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(string5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_.hasNo(Seq(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(Seq(string4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(Seq(string5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.strings_.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(string1, string4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(string1, string5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings_.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.strings_.hasNo(Set(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Set(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Set(string2, string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(Set(string2, string3, string4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string2, string3, string4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2), Set(string0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2), Set(string0, string3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2), Set(string2, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2), Set(string2, string3, string4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2), Set(string0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2), Set(string0, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2), Set(string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set(string1, string2), Set(string2, string3, string4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.strings_.hasNo(Set(string1, string2), Set.empty[String]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Set.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Seq.empty[Set[String]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasNo(Seq(Set.empty[String])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.strings.insert(List(
            (a, Set(string1, string2)),
            (b, Set(string2, string3, string4))
          )).transact

          // <
          _ <- Ns.i.a1.strings_.hasLt(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasLt(string1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasLt(string2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasLt(string3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.strings_.hasLe(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_.hasLe(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_.hasLe(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasLe(string3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.strings_.hasGt(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasGt(string1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasGt(string2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_.hasGt(string3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.strings_.hasGe(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasGe(string1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasGe(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_.hasGe(string3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          _ <- Ns.i.a1.strings_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no string value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.strings_?.query.get.map(_ ==> List(
            (2, Some(Set(string2, string3, string4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.strings_?(Some(Set(string1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?(Some(Set(string1, string2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.strings_?(Some(Set(string1, string2, string3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1, string2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1, string2, string3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1), Set(string2, string3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings_?(Some(Set.empty[String])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?(Some(Seq.empty[Set[String]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?(Some(Seq(Set.empty[String]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.strings_?(Option.empty[Set[String]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.strings_?(Option.empty[Seq[Set[String]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.strings_?.not(Some(Set(string1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.not(Some(Set(string1, string2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.strings_?.not(Some(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2, string3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1), Set(string2, string3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.strings_?.not(Some(Seq(Set(string1, string2), Set.empty[String]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.not(Some(Set.empty[String])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.not(Some(Seq.empty[Set[String]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.strings_?(Option.empty[Set[String]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.strings_?(Option.empty[Seq[Set[String]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.strings_?.has(Some(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(string3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string1, string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string1, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(string1, string2, string3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.strings_?.has(Some(Set(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Set(string1, string2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Set(string1, string2, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(Set(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Set(string2, string3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.has(Some(Set(string2, string3, string4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2, string3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string2, string3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string2, string3, string4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2), Set(string0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2), Set(string0, string3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.has(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.strings_?.has(Some(Seq.empty[String])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(Set.empty[String])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.has(Some(Seq.empty[Set[String]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.strings_?.has(Option.empty[String]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.strings_?.has(Option.empty[Seq[String]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.strings_?.has(Option.empty[Set[String]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.strings_?.has(Option.empty[Seq[Set[String]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.strings_?.hasNo(Some(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(string4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(string5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string1, string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string1, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string1, string4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(string1, string5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string1, string2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string1, string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string2, string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set(string2, string3, string4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2, string3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string2, string3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string2, string3, string4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2), Set(string0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2), Set(string0, string3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2), Set(string2, string3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set(string1, string2), Set(string2, string3, string4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq.empty[String])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Set.empty[String])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq.empty[Set[String]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Some(Seq(Set.empty[String]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.strings_?.hasNo(Option.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Option.empty[Seq[String]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Option.empty[Set[String]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasNo(Option.empty[Seq[Set[String]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.strings_?.insert(a, b, c).transact

          // <
          _ <- Ns.i.a1.strings_?.hasLt(Some(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasLt(Some(string1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasLt(Some(string2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasLt(Some(string3)).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.strings_?.hasLe(Some(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.strings_?.hasLe(Some(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.strings_?.hasLe(Some(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasLe(Some(string3)).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.strings_?.hasGt(Some(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGt(Some(string1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGt(Some(string2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.strings_?.hasGt(Some(string3)).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.strings_?.hasGe(Some(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGe(Some(string1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGe(Some(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGe(Some(string3)).query.get.map(_ ==> List(b))


          // None comparison matches any asserted values
          _ <- Ns.i.a1.strings_?.hasLt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasLe(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.strings_?.hasGe(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}