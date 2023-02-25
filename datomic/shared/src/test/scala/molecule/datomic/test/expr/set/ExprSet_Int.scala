package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object ExprSet_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          _ <- Ns.i.a1.ints.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(int1, int2, int3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints(Set(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set(int1, int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set(int1, int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Set(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints(Set(int2, int3, int4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints(Seq(Set(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq(Set(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints.not(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(int4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(Seq(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(Seq(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints.not(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(int1, int5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints.not(Set(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Set(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(Set(int2, int3, int4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(Set(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.not(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq(Set.empty[Int])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints.==(Set(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Set(int1, int2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints.==(Set(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints.==(Seq(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.==(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints.==(Set(int1), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.==(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.==(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.==(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints.==(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.==(Set.empty[Int], Set(int1, int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.==(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.==(Seq(Set.empty[Int])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints.!=(Set(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.!=(Set(int1, int2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints.!=(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints.!=(Set(int1), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.!=(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.!=(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set.empty[Int])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.!=(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.!=(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          _ <- Ns.i.a1.ints.<(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.<(int1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.<(int2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.<(int3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints.<=(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.<=(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.<=(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.<=(int3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints.>(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.>(int1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.>(int2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.>(int3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.ints.>=(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.>=(int1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.>=(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.>=(int3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          _ <- Ns.i.a1.ints_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints_(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints_(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(int1, int2, int3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints_(Set(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Set(int1, int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Set(int1, int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Set(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_(Set(int2, int3, int4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_(Seq(Set(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq(Set(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_(Seq.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints_.not(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(int4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Seq(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(Seq(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(Seq(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints_.not(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(int1, int5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints_.not(Set(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Set(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(Set(int2, int3, int4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.not(Seq.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.not(Seq(Set.empty[Int])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints_.==(Set(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Set(int1, int2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints_.==(Set(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_.==(Set(int1), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.==(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.==(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_.==(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.==(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.==(Seq(Set.empty[Int])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints_.!=(Set(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.!=(Set(int1, int2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints_.!=(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_.!=(Set(int1), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.!=(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.!=(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set.empty[Int])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.!=(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.!=(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.ints.insert(List(
            (a, Set(int1, int2)),
            (b, Set(int2, int3, int4))
          )).transact

          _ <- Ns.i.a1.ints_.<(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.<(int1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.<(int2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.<(int3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints_.<=(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.<=(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_.<=(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.<=(int3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints_.>(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.>(int1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.>(int2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_.>(int3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.ints_.>=(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.>=(int1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.>=(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_.>=(int3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          _ <- Ns.i.a1.ints_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no int value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.ints_?.query.get.map(_ ==> List(
            (2, Some(Set(int2, int3, int4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints_?(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(int3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_?(Some(Seq(int0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(int3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints_?(Some(Seq(int1, int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(int1, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints_?(Some(Set(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Set(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?(Some(Set(int2, int3, int4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int2, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int2, int3, int4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_?(Some(Seq.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Set.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?(Option.empty[Int]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?(Option.empty[Seq[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints_?.not(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(int5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int1, int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int1, int4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(int1, int5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(Set(int2, int3, int4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int3, int4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints_?.not(Some(Seq.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Set.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.ints_?.not(Option.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Option.empty[Seq[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Option.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints_?.==(Some(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.==(Some(Set(int1, int2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints_?.==(Some(Set(int1, int2, int3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1), Set(int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_?.==(Some(Set.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.==(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.==(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?.==(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?.==(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints_?.!=(Some(Set(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.!=(Some(Set(int1, int2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints_?.!=(Some(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1), Set(int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set.empty[Int]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.!=(Some(Set.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.!=(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?.==(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?.==(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          _ <- Ns.i.a1.ints_?.<(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.<(Some(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.<(Some(int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.<(Some(int3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints_?.<=(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.<=(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.<=(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.<=(Some(int3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.ints_?.>(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>(Some(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>(Some(int2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.>(Some(int3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.ints_?.>=(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>=(Some(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>=(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>=(Some(int3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.ints_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}