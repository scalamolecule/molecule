// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          _ <- Ns.i.a1.shorts.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts(Set(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set(short1, short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Set(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts(Set(short2, short3, short4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts(Seq(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts.not(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(short4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(Seq(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(Seq(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts.not(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(short1, short5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts.not(Set(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Set(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(Set(short2, short3, short4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq(Set.empty[Short])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts.==(Set(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Set(short1, short2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts.==(Set(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts.==(Set(short1), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.==(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.==(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts.==(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.==(Set.empty[Short], Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.==(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.==(Seq(Set.empty[Short])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts.!=(Set(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.!=(Set(short1, short2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts.!=(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts.!=(Set(short1), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.!=(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.!=(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts.!=(Seq(Set(short1, short2), Set.empty[Short])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.!=(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.!=(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          _ <- Ns.i.a1.shorts.<(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.<(short1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.<(short2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.<(short3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts.<=(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.<=(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.<=(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.<=(short3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts.>(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.>(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.>(short2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.>(short3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.shorts.>=(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.>=(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.>=(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.>=(short3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          _ <- Ns.i.a1.shorts_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts_(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts_(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts_(Set(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set(short1, short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Set(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_(Set(short2, short3, short4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts_.not(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(short4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(Seq(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(Seq(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts_.not(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(short1, short5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts_.not(Set(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Set(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(Set(short2, short3, short4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set.empty[Short])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts_.==(Set(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Set(short1, short2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts_.==(Set(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_.==(Set(short1), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.==(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.==(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_.==(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.==(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.==(Seq(Set.empty[Short])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts_.!=(Set(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.!=(Set(short1, short2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts_.!=(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_.!=(Set(short1), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.!=(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.!=(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts_.!=(Seq(Set(short1, short2), Set.empty[Short])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.!=(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.!=(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          _ <- Ns.i.a1.shorts_.<(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.<(short1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.<(short2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.<(short3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts_.<=(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.<=(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.<=(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.<=(short3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts_.>(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.>(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.>(short2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.>(short3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.shorts_.>=(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.>=(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.>=(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.>=(short3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          _ <- Ns.i.a1.shorts_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no short value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.shorts_?.query.get.map(_ ==> List(
            (2, Some(Set(short2, short3, short4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts_?(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(short3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_?(Some(Seq(short0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(short3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts_?(Some(Seq(short1, short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(short1, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts_?(Some(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?(Some(Set(short2, short3, short4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short2, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short2, short3, short4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_?(Some(Seq.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Set.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?(Option.empty[Short]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?(Option.empty[Seq[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts_?.not(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(short5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short1, short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short1, short4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(short1, short5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short2, short3, short4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short2, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short2, short3, short4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts_?.not(Some(Seq.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set.empty[Short]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.shorts_?.not(Option.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Option.empty[Seq[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Option.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts_?.==(Some(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.==(Some(Set(short1, short2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts_?.==(Some(Set(short1, short2, short3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1), Set(short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_?.==(Some(Set.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.==(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.==(Some(Seq(Set.empty[Short]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?.==(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?.==(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?.!=(Some(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Set(short1, short2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts_?.!=(Some(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1), Set(short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set.empty[Short]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Set.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.!=(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?.==(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?.==(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          _ <- Ns.i.a1.shorts_?.<(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.<(Some(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.<(Some(short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.<(Some(short3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts_?.<=(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.<=(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.<=(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.<=(Some(short3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.shorts_?.>(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>(Some(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>(Some(short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.>(Some(short3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.shorts_?.>=(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>=(Some(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>=(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>=(Some(short3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.shorts_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}