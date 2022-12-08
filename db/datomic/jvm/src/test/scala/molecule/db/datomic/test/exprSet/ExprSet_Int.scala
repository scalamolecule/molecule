package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        Ns.i.a1.ints.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.ints(int0).query.get ==> List()
        Ns.i.a1.ints(int1).query.get ==> List(a)
        Ns.i.a1.ints(int2).query.get ==> List(a, b)
        Ns.i.a1.ints(int3).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints(Seq(int0)).query.get ==> List()
        Ns.i.a1.ints(Seq(int1)).query.get ==> List(a)
        Ns.i.a1.ints(Seq(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(int3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.ints(int1, int2).query.get ==> List(a, b)
        Ns.i.a1.ints(int1, int3).query.get ==> List(a, b)
        Ns.i.a1.ints(int2, int3).query.get ==> List(a, b)
        Ns.i.a1.ints(int1, int2, int3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints(Seq(int1, int2)).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(int1, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(int1, int2, int3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.ints(Set(int1)).query.get ==> List(a)
        Ns.i.a1.ints(Set(int1, int2)).query.get ==> List(a)
        Ns.i.a1.ints(Set(int1, int2, int3)).query.get ==> List()
        Ns.i.a1.ints(Set(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints(Set(int2, int3)).query.get ==> List(b)
        Ns.i.a1.ints(Set(int2, int3, int4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints(Seq(Set(int1))).query.get ==> List(a)
        Ns.i.a1.ints(Seq(Set(int1, int2))).query.get ==> List(a)
        Ns.i.a1.ints(Seq(Set(int1, int2, int3))).query.get ==> List()
        Ns.i.a1.ints(Seq(Set(int2))).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(Set(int2, int3))).query.get ==> List(b)
        Ns.i.a1.ints(Seq(Set(int2, int3, int4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.ints(Set(int1, int2), Set(int0)).query.get ==> List(a)
        Ns.i.a1.ints(Set(int1, int2), Set(int0, int3)).query.get ==> List(a)
        Ns.i.a1.ints(Set(int1, int2), Set(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints(Seq(Set(int1, int2), Set(int0))).query.get ==> List(a)
        Ns.i.a1.ints(Seq(Set(int1, int2), Set(int0, int3))).query.get ==> List(a)
        Ns.i.a1.ints(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints(Set(int1, int2), Set.empty[Int]).query.get ==> List(a)
        Ns.i.a1.ints(Seq.empty[Int]).query.get ==> List()
        Ns.i.a1.ints(Set.empty[Int]).query.get ==> List()
        Ns.i.a1.ints(Seq.empty[Set[Int]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.ints.not(int0).query.get ==> List(a, b)
        Ns.i.a1.ints.not(int1).query.get ==> List(b)
        Ns.i.a1.ints.not(int2).query.get ==> List()
        Ns.i.a1.ints.not(int3).query.get ==> List(a)
        Ns.i.a1.ints.not(int4).query.get ==> List(a)
        Ns.i.a1.ints.not(int5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints.not(Seq(int0)).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Seq(int1)).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq(int2)).query.get ==> List()
        Ns.i.a1.ints.not(Seq(int3)).query.get ==> List(a)
        Ns.i.a1.ints.not(Seq(int4)).query.get ==> List(a)
        Ns.i.a1.ints.not(Seq(int5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.ints.not(int1, int2).query.get ==> List()
        Ns.i.a1.ints.not(int1, int3).query.get ==> List()
        Ns.i.a1.ints.not(int1, int4).query.get ==> List()
        Ns.i.a1.ints.not(int1, int5).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints.not(Seq(int1, int2)).query.get ==> List()
        Ns.i.a1.ints.not(Seq(int1, int3)).query.get ==> List()
        Ns.i.a1.ints.not(Seq(int1, int4)).query.get ==> List()
        Ns.i.a1.ints.not(Seq(int1, int5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.ints.not(Set(int1)).query.get ==> List(b)
        Ns.i.a1.ints.not(Set(int1, int2)).query.get ==> List(b)
        Ns.i.a1.ints.not(Set(int1, int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Set(int2)).query.get ==> List()
        Ns.i.a1.ints.not(Set(int2, int3)).query.get ==> List(a)
        Ns.i.a1.ints.not(Set(int2, int3, int4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.ints.not(Seq(Set(int1))).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq(Set(int1, int2))).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq(Set(int1, int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Seq(Set(int2))).query.get ==> List()
        Ns.i.a1.ints.not(Seq(Set(int2, int3))).query.get ==> List(a)
        Ns.i.a1.ints.not(Seq(Set(int2, int3, int4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.ints.not(Set(int1, int2), Set(int0)).query.get ==> List(b)
        Ns.i.a1.ints.not(Set(int1, int2), Set(int0, int3)).query.get ==> List(b)
        Ns.i.a1.ints.not(Set(int1, int2), Set(int2, int3)).query.get ==> List()
        Ns.i.a1.ints.not(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List()
        // Same as
        Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int0))).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int0, int3))).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List()
        Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.ints.not(Set(int1, int2), Set.empty[Int]).query.get ==> List(b)
        Ns.i.a1.ints.not(Seq.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Set.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Seq.empty[Set[Int]]).query.get ==> List(a, b)
        Ns.i.a1.ints.not(Seq(Set.empty[Int])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.ints.==(Set(int1)).query.get ==> List()
        Ns.i.a1.ints.==(Set(int1, int2)).query.get ==> List(a) // include exact match
        Ns.i.a1.ints.==(Set(int1, int2, int3)).query.get ==> List()
        // Same as
        Ns.i.a1.ints.==(Seq(Set(int1))).query.get ==> List()
        Ns.i.a1.ints.==(Seq(Set(int1, int2))).query.get ==> List(a)
        Ns.i.a1.ints.==(Seq(Set(int1, int2, int3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints.==(Set(int1), Set(int2, int3)).query.get ==> List()
        Ns.i.a1.ints.==(Set(int1, int2), Set(int2, int3)).query.get ==> List(a)
        Ns.i.a1.ints.==(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints.==(Seq(Set(int1), Set(int2, int3))).query.get ==> List()
        Ns.i.a1.ints.==(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(a)
        Ns.i.a1.ints.==(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints.==(Set(int1, int2), Set.empty[Int]).query.get ==> List(a)
        Ns.i.a1.ints.==(Set.empty[Int], Set(int1, int2)).query.get ==> List(a)
        Ns.i.a1.ints.==(Set.empty[Int]).query.get ==> List()
        Ns.i.a1.ints.==(Seq.empty[Set[Int]]).query.get ==> List()
        Ns.i.a1.ints.==(Seq(Set.empty[Int])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.ints.!=(Set(int1)).query.get ==> List(a, b)
        Ns.i.a1.ints.!=(Set(int1, int2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.ints.!=(Set(int1, int2, int3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints.!=(Seq(Set(int1))).query.get ==> List(a, b)
        Ns.i.a1.ints.!=(Seq(Set(int1, int2))).query.get ==> List(b)
        Ns.i.a1.ints.!=(Seq(Set(int1, int2, int3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints.!=(Set(int1), Set(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints.!=(Set(int1, int2), Set(int2, int3)).query.get ==> List(b)
        Ns.i.a1.ints.!=(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List()
        // Same as
        Ns.i.a1.ints.!=(Seq(Set(int1), Set(int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(b)
        Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.ints.!=(Seq(Set(int1, int2), Set.empty[Int])).query.get ==> List(b)
        Ns.i.a1.ints.!=(Set.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints.!=(Seq.empty[Set[Int]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        Ns.i.ints.insert(List(a, b)).transact

        Ns.i.a1.ints.<(int0).query.get ==> List()
        Ns.i.a1.ints.<(int1).query.get ==> List()
        Ns.i.a1.ints.<(int2).query.get ==> List(a)
        Ns.i.a1.ints.<(int3).query.get ==> List(a, b)

        Ns.i.a1.ints.<=(int0).query.get ==> List()
        Ns.i.a1.ints.<=(int1).query.get ==> List(a)
        Ns.i.a1.ints.<=(int2).query.get ==> List(a, b)
        Ns.i.a1.ints.<=(int3).query.get ==> List(a, b)

        Ns.i.a1.ints.>(int0).query.get ==> List(a, b)
        Ns.i.a1.ints.>(int1).query.get ==> List(a, b)
        Ns.i.a1.ints.>(int2).query.get ==> List(b)
        Ns.i.a1.ints.>(int3).query.get ==> List(b)

        Ns.i.a1.ints.>=(int0).query.get ==> List(a, b)
        Ns.i.a1.ints.>=(int1).query.get ==> List(a, b)
        Ns.i.a1.ints.>=(int2).query.get ==> List(a, b)
        Ns.i.a1.ints.>=(int3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        Ns.i.a1.ints_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.ints_(int0).query.get ==> List()
        Ns.i.a1.ints_(int1).query.get ==> List(a)
        Ns.i.a1.ints_(int2).query.get ==> List(a, b)
        Ns.i.a1.ints_(int3).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints_(Seq(int0)).query.get ==> List()
        Ns.i.a1.ints_(Seq(int1)).query.get ==> List(a)
        Ns.i.a1.ints_(Seq(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(int3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.ints_(int1, int2).query.get ==> List(a, b)
        Ns.i.a1.ints_(int1, int3).query.get ==> List(a, b)
        Ns.i.a1.ints_(int2, int3).query.get ==> List(a, b)
        Ns.i.a1.ints_(int1, int2, int3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_(Seq(int1, int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(int1, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(int1, int2, int3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.ints_(Set(int1)).query.get ==> List(a)
        Ns.i.a1.ints_(Set(int1, int2)).query.get ==> List(a)
        Ns.i.a1.ints_(Set(int1, int2, int3)).query.get ==> List()
        Ns.i.a1.ints_(Set(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Set(int2, int3)).query.get ==> List(b)
        Ns.i.a1.ints_(Set(int2, int3, int4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints_(Seq(Set(int1))).query.get ==> List(a)
        Ns.i.a1.ints_(Seq(Set(int1, int2))).query.get ==> List(a)
        Ns.i.a1.ints_(Seq(Set(int1, int2, int3))).query.get ==> List()
        Ns.i.a1.ints_(Seq(Set(int2))).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(Set(int2, int3))).query.get ==> List(b)
        Ns.i.a1.ints_(Seq(Set(int2, int3, int4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.ints_(Set(int1, int2), Set(int0)).query.get ==> List(a)
        Ns.i.a1.ints_(Set(int1, int2), Set(int0, int3)).query.get ==> List(a)
        Ns.i.a1.ints_(Set(int1, int2), Set(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints_(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int0))).query.get ==> List(a)
        Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int0, int3))).query.get ==> List(a)
        Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints_(Set(int1, int2), Set.empty[Int]).query.get ==> List(a)
        Ns.i.a1.ints_(Seq.empty[Int]).query.get ==> List()
        Ns.i.a1.ints_(Set.empty[Int]).query.get ==> List()
        Ns.i.a1.ints_(Seq.empty[Set[Int]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.ints_.not(int0).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(int1).query.get ==> List(b)
        Ns.i.a1.ints_.not(int2).query.get ==> List()
        Ns.i.a1.ints_.not(int3).query.get ==> List(a)
        Ns.i.a1.ints_.not(int4).query.get ==> List(a)
        Ns.i.a1.ints_.not(int5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_.not(Seq(int0)).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Seq(int1)).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq(int2)).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(int3)).query.get ==> List(a)
        Ns.i.a1.ints_.not(Seq(int4)).query.get ==> List(a)
        Ns.i.a1.ints_.not(Seq(int5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.ints_.not(int1, int2).query.get ==> List()
        Ns.i.a1.ints_.not(int1, int3).query.get ==> List()
        Ns.i.a1.ints_.not(int1, int4).query.get ==> List()
        Ns.i.a1.ints_.not(int1, int5).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints_.not(Seq(int1, int2)).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(int1, int3)).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(int1, int4)).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(int1, int5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.ints_.not(Set(int1)).query.get ==> List(b)
        Ns.i.a1.ints_.not(Set(int1, int2)).query.get ==> List(b)
        Ns.i.a1.ints_.not(Set(int1, int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Set(int2)).query.get ==> List()
        Ns.i.a1.ints_.not(Set(int2, int3)).query.get ==> List(a)
        Ns.i.a1.ints_.not(Set(int2, int3, int4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.ints_.not(Seq(Set(int1))).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq(Set(int1, int2))).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq(Set(int1, int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Seq(Set(int2))).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(Set(int2, int3))).query.get ==> List(a)
        Ns.i.a1.ints_.not(Seq(Set(int2, int3, int4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.ints_.not(Set(int1, int2), Set(int0)).query.get ==> List(b)
        Ns.i.a1.ints_.not(Set(int1, int2), Set(int0, int3)).query.get ==> List(b)
        Ns.i.a1.ints_.not(Set(int1, int2), Set(int2, int3)).query.get ==> List()
        Ns.i.a1.ints_.not(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List()
        // Same as
        Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int0))).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int0, int3))).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List()
        Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.ints_.not(Set(int1, int2), Set.empty[Int]).query.get ==> List(b)
        Ns.i.a1.ints_.not(Seq.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Set.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Seq.empty[Set[Int]]).query.get ==> List(a, b)
        Ns.i.a1.ints_.not(Seq(Set.empty[Int])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.ints_.==(Set(int1)).query.get ==> List()
        Ns.i.a1.ints_.==(Set(int1, int2)).query.get ==> List(a) // include exact match
        Ns.i.a1.ints_.==(Set(int1, int2, int3)).query.get ==> List()
        // Same as
        Ns.i.a1.ints_.==(Seq(Set(int1))).query.get ==> List()
        Ns.i.a1.ints_.==(Seq(Set(int1, int2))).query.get ==> List(a)
        Ns.i.a1.ints_.==(Seq(Set(int1, int2, int3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints_.==(Set(int1), Set(int2, int3)).query.get ==> List()
        Ns.i.a1.ints_.==(Set(int1, int2), Set(int2, int3)).query.get ==> List(a)
        Ns.i.a1.ints_.==(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_.==(Seq(Set(int1), Set(int2, int3))).query.get ==> List()
        Ns.i.a1.ints_.==(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(a)
        Ns.i.a1.ints_.==(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints_.==(Set(int1, int2), Set.empty[Int]).query.get ==> List(a)
        Ns.i.a1.ints_.==(Set.empty[Int]).query.get ==> List()
        Ns.i.a1.ints_.==(Seq.empty[Set[Int]]).query.get ==> List()
        Ns.i.a1.ints_.==(Seq(Set.empty[Int])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.ints_.!=(Set(int1)).query.get ==> List(a, b)
        Ns.i.a1.ints_.!=(Set(int1, int2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.ints_.!=(Set(int1, int2, int3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_.!=(Seq(Set(int1))).query.get ==> List(a, b)
        Ns.i.a1.ints_.!=(Seq(Set(int1, int2))).query.get ==> List(b)
        Ns.i.a1.ints_.!=(Seq(Set(int1, int2, int3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints_.!=(Set(int1), Set(int2, int3)).query.get ==> List(a, b)
        Ns.i.a1.ints_.!=(Set(int1, int2), Set(int2, int3)).query.get ==> List(b)
        Ns.i.a1.ints_.!=(Set(int1, int2), Set(int2, int3, int4)).query.get ==> List()
        // Same as
        Ns.i.a1.ints_.!=(Seq(Set(int1), Set(int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set(int2, int3))).query.get ==> List(b)
        Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.ints_.!=(Seq(Set(int1, int2), Set.empty[Int])).query.get ==> List(b)
        Ns.i.a1.ints_.!=(Set.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints_.!=(Seq.empty[Set[Int]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        Ns.i.a1.ints_.<(int0).query.get ==> List()
        Ns.i.a1.ints_.<(int1).query.get ==> List()
        Ns.i.a1.ints_.<(int2).query.get ==> List(a)
        Ns.i.a1.ints_.<(int3).query.get ==> List(a, b)

        Ns.i.a1.ints_.<=(int0).query.get ==> List()
        Ns.i.a1.ints_.<=(int1).query.get ==> List(a)
        Ns.i.a1.ints_.<=(int2).query.get ==> List(a, b)
        Ns.i.a1.ints_.<=(int3).query.get ==> List(a, b)

        Ns.i.a1.ints_.>(int0).query.get ==> List(a, b)
        Ns.i.a1.ints_.>(int1).query.get ==> List(a, b)
        Ns.i.a1.ints_.>(int2).query.get ==> List(b)
        Ns.i.a1.ints_.>(int3).query.get ==> List(b)

        Ns.i.a1.ints_.>=(int0).query.get ==> List(a, b)
        Ns.i.a1.ints_.>=(int1).query.get ==> List(a, b)
        Ns.i.a1.ints_.>=(int2).query.get ==> List(a, b)
        Ns.i.a1.ints_.>=(int3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        Ns.i.a1.ints_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.ints_?(Some(int0)).query.get ==> List()
        Ns.i.a1.ints_?(Some(int1)).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(int3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints_?(Some(Seq(int0))).query.get ==> List()
        Ns.i.a1.ints_?(Some(Seq(int1))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Seq(int2))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(int3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.ints_?(Some(Seq(int1, int2))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(int1, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(int1, int2, int3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.ints_?(Some(Set(int1))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Set(int1, int2))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Set(int1, int2, int3))).query.get ==> List()
        Ns.i.a1.ints_?(Some(Set(int2))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Set(int2, int3))).query.get ==> List(b)
        Ns.i.a1.ints_?(Some(Set(int2, int3, int4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.ints_?(Some(Seq(Set(int1)))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2)))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2, int3)))).query.get ==> List()
        Ns.i.a1.ints_?(Some(Seq(Set(int2)))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(Set(int2, int3)))).query.get ==> List(b)
        Ns.i.a1.ints_?(Some(Seq(Set(int2, int3, int4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int0)))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get ==> List(a)
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get ==> List(a, b)
        Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints_?(Some(Seq.empty[Int])).query.get ==> List()
        Ns.i.a1.ints_?(Some(Set.empty[Int])).query.get ==> List()
        Ns.i.a1.ints_?(Some(Seq.empty[Set[Int]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.ints_?(Option.empty[Int]).query.get ==> List(c)
        Ns.i.a1.ints_?(Option.empty[Seq[Int]]).query.get ==> List(c)
        Ns.i.a1.ints_?(Option.empty[Set[Int]]).query.get ==> List(c)
        Ns.i.a1.ints_?(Option.empty[Seq[Set[Int]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.ints_?.not(Some(int0)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(int1)).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(int2)).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(int3)).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(int4)).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(int5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_?.not(Some(Seq(int0))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Seq(int1))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Seq(int2))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(int3))).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(Seq(int4))).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(Seq(int5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.ints_?.not(Some(Seq(int1, int2))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(int1, int3))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(int1, int4))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(int1, int5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.ints_?.not(Some(Set(int1))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Set(int1, int2))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Set(int1, int2, int3))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Set(int2))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Set(int2, int3))).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(Set(int2, int3, int4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1)))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2)))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2, int3)))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int2)))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int3)))).query.get ==> List(a)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int3, int4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int0)))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get ==> List(b)
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get ==> List()
        Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.ints_?.not(Some(Seq.empty[Int])).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Set.empty[Int])).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Seq.empty[Set[Int]])).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Some(Seq(Set.empty[Int]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.ints_?.not(Option.empty[Int]).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Option.empty[Seq[Int]]).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Option.empty[Set[Int]]).query.get ==> List(a, b)
        Ns.i.a1.ints_?.not(Option.empty[Seq[Set[Int]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.ints_?.==(Some(Set(int1))).query.get ==> List()
        Ns.i.a1.ints_?.==(Some(Set(int1, int2))).query.get ==> List(a) // include exact match
        Ns.i.a1.ints_?.==(Some(Set(int1, int2, int3))).query.get ==> List()
        // Same as
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1)))).query.get ==> List()
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2)))).query.get ==> List(a)
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2, int3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1), Set(int2, int3)))).query.get ==> List()
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get ==> List(a)
        Ns.i.a1.ints_?.==(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.ints_?.==(Some(Set.empty[Int])).query.get ==> List()
        Ns.i.a1.ints_?.==(Some(Seq.empty[Set[Int]])).query.get ==> List()
        Ns.i.a1.ints_?.==(Some(Seq(Set.empty[Int]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.ints_?.==(Option.empty[Set[Int]]).query.get ==> List(c)
        Ns.i.a1.ints_?.==(Option.empty[Seq[Set[Int]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.ints_?.!=(Some(Set(int1))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.!=(Some(Set(int1, int2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.ints_?.!=(Some(Set(int1, int2, int3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1)))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2)))).query.get ==> List(b)
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2, int3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1), Set(int2, int3)))).query.get ==> List(a, b)
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get ==> List(b)
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.ints_?.!=(Some(Seq(Set(int1, int2), Set.empty[Int]))).query.get ==> List(b)
        Ns.i.a1.ints_?.!=(Some(Set.empty[Int])).query.get ==> List(a, b)
        Ns.i.a1.ints_?.!=(Some(Seq.empty[Set[Int]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.ints_?.==(Option.empty[Set[Int]]).query.get ==> List(c)
        Ns.i.a1.ints_?.==(Option.empty[Seq[Set[Int]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        Ns.i.ints_?.insert(a, b, c).transact

        Ns.i.a1.ints_?.<(Some(int0)).query.get ==> List()
        Ns.i.a1.ints_?.<(Some(int1)).query.get ==> List()
        Ns.i.a1.ints_?.<(Some(int2)).query.get ==> List(a)
        Ns.i.a1.ints_?.<(Some(int3)).query.get ==> List(a, b)

        Ns.i.a1.ints_?.<=(Some(int0)).query.get ==> List()
        Ns.i.a1.ints_?.<=(Some(int1)).query.get ==> List(a)
        Ns.i.a1.ints_?.<=(Some(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.<=(Some(int3)).query.get ==> List(a, b)

        Ns.i.a1.ints_?.>(Some(int0)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>(Some(int1)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>(Some(int2)).query.get ==> List(b)
        Ns.i.a1.ints_?.>(Some(int3)).query.get ==> List(b)

        Ns.i.a1.ints_?.>=(Some(int0)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>=(Some(int1)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>=(Some(int2)).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>=(Some(int3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.ints_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.ints_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.ints_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}