// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        NsSet.n.a1.shorts.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.shorts(short0).query.get ==> List()
        NsSet.n.a1.shorts(short1).query.get ==> List(a)
        NsSet.n.a1.shorts(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts(short3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts(Seq(short0)).query.get ==> List()
        NsSet.n.a1.shorts(Seq(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(short3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.shorts(short1, short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts(short1, short3).query.get ==> List(a, b)
        NsSet.n.a1.shorts(short2, short3).query.get ==> List(a, b)
        NsSet.n.a1.shorts(short1, short2, short3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts(Seq(short1, short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(short1, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(short1, short2, short3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.shorts(Set(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts(Set(short1, short2)).query.get ==> List(a)
        NsSet.n.a1.shorts(Set(short1, short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts(Set(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Set(short2, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts(Set(short2, short3, short4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts(Seq(Set(short1))).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq(Set(short1, short2))).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq(Set(short1, short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts(Seq(Set(short2))).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(Set(short2, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts(Seq(Set(short2, short3, short4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.shorts(Set(short1, short2), Set(short0)).query.get ==> List(a)
        NsSet.n.a1.shorts(Set(short1, short2), Set(short0, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts(Set(short1, short2), Set(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts(Seq(Set(short1, short2), Set(short0))).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq(Set(short1, short2), Set(short0, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts(Set(short1, short2), Set.empty[Short]).query.get ==> List(a)
        NsSet.n.a1.shorts(Seq.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts(Set.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts(Seq.empty[Set[Short]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.shorts.not(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(short1).query.get ==> List(b)
        NsSet.n.a1.shorts.not(short2).query.get ==> List()
        NsSet.n.a1.shorts.not(short3).query.get ==> List(a)
        NsSet.n.a1.shorts.not(short4).query.get ==> List(a)
        NsSet.n.a1.shorts.not(short5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts.not(Seq(short0)).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Seq(short1)).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq(short2)).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(short3)).query.get ==> List(a)
        NsSet.n.a1.shorts.not(Seq(short4)).query.get ==> List(a)
        NsSet.n.a1.shorts.not(Seq(short5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.shorts.not(short1, short2).query.get ==> List()
        NsSet.n.a1.shorts.not(short1, short3).query.get ==> List()
        NsSet.n.a1.shorts.not(short1, short4).query.get ==> List()
        NsSet.n.a1.shorts.not(short1, short5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts.not(Seq(short1, short2)).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(short1, short3)).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(short1, short4)).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(short1, short5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.shorts.not(Set(short1)).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Set(short1, short2)).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Set(short1, short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Set(short2)).query.get ==> List()
        NsSet.n.a1.shorts.not(Set(short2, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts.not(Set(short2, short3, short4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.shorts.not(Seq(Set(short1))).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2))).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Seq(Set(short2))).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(Set(short2, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts.not(Seq(Set(short2, short3, short4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.shorts.not(Set(short1, short2), Set(short0)).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Set(short1, short2), Set(short0, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Set(short1, short2), Set(short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts.not(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2), Set(short0))).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2), Set(short0, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.shorts.not(Set(short1, short2), Set.empty[Short]).query.get ==> List(b)
        NsSet.n.a1.shorts.not(Seq.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Set.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Seq.empty[Set[Short]]).query.get ==> List(a, b)
        NsSet.n.a1.shorts.not(Seq(Set.empty[Short])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.shorts.==(Set(short1)).query.get ==> List()
        NsSet.n.a1.shorts.==(Set(short1, short2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.shorts.==(Set(short1, short2, short3)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts.==(Seq(Set(short1))).query.get ==> List()
        NsSet.n.a1.shorts.==(Seq(Set(short1, short2))).query.get ==> List(a)
        NsSet.n.a1.shorts.==(Seq(Set(short1, short2, short3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts.==(Set(short1), Set(short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts.==(Set(short1, short2), Set(short2, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts.==(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts.==(Seq(Set(short1), Set(short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts.==(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts.==(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts.==(Set(short1, short2), Set.empty[Short]).query.get ==> List(a)
        NsSet.n.a1.shorts.==(Set.empty[Short], Set(short1, short2)).query.get ==> List(a)
        NsSet.n.a1.shorts.==(Set.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts.==(Seq.empty[Set[Short]]).query.get ==> List()
        NsSet.n.a1.shorts.==(Seq(Set.empty[Short])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.shorts.!=(Set(short1)).query.get ==> List(a, b)
        NsSet.n.a1.shorts.!=(Set(short1, short2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.shorts.!=(Set(short1, short2, short3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts.!=(Seq(Set(short1))).query.get ==> List(a, b)
        NsSet.n.a1.shorts.!=(Seq(Set(short1, short2))).query.get ==> List(b)
        NsSet.n.a1.shorts.!=(Seq(Set(short1, short2, short3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts.!=(Set(short1), Set(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts.!=(Set(short1, short2), Set(short2, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts.!=(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts.!=(Seq(Set(short1), Set(short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts.!=(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts.!=(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.shorts.!=(Seq(Set(short1, short2), Set.empty[Short])).query.get ==> List(b)
        NsSet.n.a1.shorts.!=(Set.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts.!=(Seq.empty[Set[Short]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        NsSet.n.shorts.insert(List(a, b)).transact

        NsSet.n.a1.shorts.<(short0).query.get ==> List()
        NsSet.n.a1.shorts.<(short1).query.get ==> List()
        NsSet.n.a1.shorts.<(short2).query.get ==> List(a)
        NsSet.n.a1.shorts.<(short3).query.get ==> List(a, b)

        NsSet.n.a1.shorts.<=(short0).query.get ==> List()
        NsSet.n.a1.shorts.<=(short1).query.get ==> List(a)
        NsSet.n.a1.shorts.<=(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts.<=(short3).query.get ==> List(a, b)

        NsSet.n.a1.shorts.>(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts.>(short1).query.get ==> List(a, b)
        NsSet.n.a1.shorts.>(short2).query.get ==> List(b)
        NsSet.n.a1.shorts.>(short3).query.get ==> List(b)

        NsSet.n.a1.shorts.>=(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts.>=(short1).query.get ==> List(a, b)
        NsSet.n.a1.shorts.>=(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts.>=(short3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        NsSet.n.a1.shorts_.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.shorts_(short0).query.get ==> List()
        NsSet.n.a1.shorts_(short1).query.get ==> List(a)
        NsSet.n.a1.shorts_(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(short3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts_(Seq(short0)).query.get ==> List()
        NsSet.n.a1.shorts_(Seq(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(short3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.shorts_(short1, short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(short1, short3).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(short2, short3).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(short1, short2, short3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_(Seq(short1, short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(short1, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(short1, short2, short3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.shorts_(Set(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts_(Set(short1, short2)).query.get ==> List(a)
        NsSet.n.a1.shorts_(Set(short1, short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts_(Set(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Set(short2, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts_(Set(short2, short3, short4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts_(Seq(Set(short1))).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq(Set(short1, short2))).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq(Set(short1, short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts_(Seq(Set(short2))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(Set(short2, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts_(Seq(Set(short2, short3, short4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.shorts_(Set(short1, short2), Set(short0)).query.get ==> List(a)
        NsSet.n.a1.shorts_(Set(short1, short2), Set(short0, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts_(Set(short1, short2), Set(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_(Seq(Set(short1, short2), Set(short0))).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq(Set(short1, short2), Set(short0, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts_(Set(short1, short2), Set.empty[Short]).query.get ==> List(a)
        NsSet.n.a1.shorts_(Seq.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts_(Set.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts_(Seq.empty[Set[Short]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.shorts_.not(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(short1).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(short2).query.get ==> List()
        NsSet.n.a1.shorts_.not(short3).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(short4).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(short5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_.not(Seq(short0)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Seq(short1)).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq(short2)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(short3)).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(Seq(short4)).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(Seq(short5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.shorts_.not(short1, short2).query.get ==> List()
        NsSet.n.a1.shorts_.not(short1, short3).query.get ==> List()
        NsSet.n.a1.shorts_.not(short1, short4).query.get ==> List()
        NsSet.n.a1.shorts_.not(short1, short5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts_.not(Seq(short1, short2)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(short1, short3)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(short1, short4)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(short1, short5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.shorts_.not(Set(short1)).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Set(short1, short2)).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Set(short1, short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Set(short2)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Set(short2, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(Set(short2, short3, short4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.shorts_.not(Seq(Set(short1))).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2))).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Seq(Set(short2))).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(Set(short2, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts_.not(Seq(Set(short2, short3, short4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.shorts_.not(Set(short1, short2), Set(short0)).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Set(short1, short2), Set(short0, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Set(short1, short2), Set(short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts_.not(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2), Set(short0))).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2), Set(short0, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.shorts_.not(Set(short1, short2), Set.empty[Short]).query.get ==> List(b)
        NsSet.n.a1.shorts_.not(Seq.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Set.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Seq.empty[Set[Short]]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.not(Seq(Set.empty[Short])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.shorts_.==(Set(short1)).query.get ==> List()
        NsSet.n.a1.shorts_.==(Set(short1, short2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.shorts_.==(Set(short1, short2, short3)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts_.==(Seq(Set(short1))).query.get ==> List()
        NsSet.n.a1.shorts_.==(Seq(Set(short1, short2))).query.get ==> List(a)
        NsSet.n.a1.shorts_.==(Seq(Set(short1, short2, short3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts_.==(Set(short1), Set(short2, short3)).query.get ==> List()
        NsSet.n.a1.shorts_.==(Set(short1, short2), Set(short2, short3)).query.get ==> List(a)
        NsSet.n.a1.shorts_.==(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_.==(Seq(Set(short1), Set(short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts_.==(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts_.==(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts_.==(Set(short1, short2), Set.empty[Short]).query.get ==> List(a)
        NsSet.n.a1.shorts_.==(Set.empty[Short]).query.get ==> List()
        NsSet.n.a1.shorts_.==(Seq.empty[Set[Short]]).query.get ==> List()
        NsSet.n.a1.shorts_.==(Seq(Set.empty[Short])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.shorts_.!=(Set(short1)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.!=(Set(short1, short2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.shorts_.!=(Set(short1, short2, short3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_.!=(Seq(Set(short1))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.!=(Seq(Set(short1, short2))).query.get ==> List(b)
        NsSet.n.a1.shorts_.!=(Seq(Set(short1, short2, short3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts_.!=(Set(short1), Set(short2, short3)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.!=(Set(short1, short2), Set(short2, short3)).query.get ==> List(b)
        NsSet.n.a1.shorts_.!=(Set(short1, short2), Set(short2, short3, short4)).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts_.!=(Seq(Set(short1), Set(short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.!=(Seq(Set(short1, short2), Set(short2, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts_.!=(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.shorts_.!=(Seq(Set(short1, short2), Set.empty[Short])).query.get ==> List(b)
        NsSet.n.a1.shorts_.!=(Set.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.!=(Seq.empty[Set[Short]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        NsSet.n.a1.shorts_.<(short0).query.get ==> List()
        NsSet.n.a1.shorts_.<(short1).query.get ==> List()
        NsSet.n.a1.shorts_.<(short2).query.get ==> List(a)
        NsSet.n.a1.shorts_.<(short3).query.get ==> List(a, b)

        NsSet.n.a1.shorts_.<=(short0).query.get ==> List()
        NsSet.n.a1.shorts_.<=(short1).query.get ==> List(a)
        NsSet.n.a1.shorts_.<=(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.<=(short3).query.get ==> List(a, b)

        NsSet.n.a1.shorts_.>(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.>(short1).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.>(short2).query.get ==> List(b)
        NsSet.n.a1.shorts_.>(short3).query.get ==> List(b)

        NsSet.n.a1.shorts_.>=(short0).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.>=(short1).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.>=(short2).query.get ==> List(a, b)
        NsSet.n.a1.shorts_.>=(short3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        NsSet.n.a1.shorts_?.query.get ==> List(a, b, c)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.shorts_?(Some(short0)).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(short3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts_?(Some(Seq(short0))).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(Seq(short1))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Seq(short2))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(short3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.shorts_?(Some(Seq(short1, short2))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(short1, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(short1, short2, short3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.shorts_?(Some(Set(short1))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Set(short1, short2))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Set(short1, short2, short3))).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(Set(short2))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Set(short2, short3))).query.get ==> List(b)
        NsSet.n.a1.shorts_?(Some(Set(short2, short3, short4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2, short3)))).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(Seq(Set(short2)))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short2, short3)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short2, short3, short4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short0)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts_?(Some(Seq.empty[Short])).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(Set.empty[Short])).query.get ==> List()
        NsSet.n.a1.shorts_?(Some(Seq.empty[Set[Short]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.shorts_?(Option.empty[Short]).query.get ==> List(c)
        NsSet.n.a1.shorts_?(Option.empty[Seq[Short]]).query.get ==> List(c)
        NsSet.n.a1.shorts_?(Option.empty[Set[Short]]).query.get ==> List(c)
        NsSet.n.a1.shorts_?(Option.empty[Seq[Set[Short]]]).query.get ==> List(c)
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.shorts_?.not(Some(short0)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(short1)).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(short2)).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(short3)).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(short4)).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(short5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_?.not(Some(Seq(short0))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Seq(short1))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Seq(short2))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(short3))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(Seq(short4))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(Seq(short5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.shorts_?.not(Some(Seq(short1, short2))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(short1, short3))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(short1, short4))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(short1, short5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.shorts_?.not(Some(Set(short1))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Set(short1, short2))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Set(short1, short2, short3))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Set(short2))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Set(short2, short3))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(Set(short2, short3, short4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2, short3)))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short2)))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short2, short3)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short2, short3, short4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short0)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get ==> List()
        NsSet.n.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.shorts_?.not(Some(Seq.empty[Short])).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Set.empty[Short])).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Seq.empty[Set[Short]])).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Some(Seq(Set.empty[Short]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.shorts_?.not(Option.empty[Short]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Option.empty[Seq[Short]]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Option.empty[Set[Short]]).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.not(Option.empty[Seq[Set[Short]]]).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.shorts_?.==(Some(Set(short1))).query.get ==> List()
        NsSet.n.a1.shorts_?.==(Some(Set(short1, short2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.shorts_?.==(Some(Set(short1, short2, short3))).query.get ==> List()
        // Same as
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1)))).query.get ==> List()
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1, short2)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1, short2, short3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1), Set(short2, short3)))).query.get ==> List()
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get ==> List(a)
        NsSet.n.a1.shorts_?.==(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.shorts_?.==(Some(Set.empty[Short])).query.get ==> List()
        NsSet.n.a1.shorts_?.==(Some(Seq.empty[Set[Short]])).query.get ==> List()
        NsSet.n.a1.shorts_?.==(Some(Seq(Set.empty[Short]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.shorts_?.==(Option.empty[Set[Short]]).query.get ==> List(c)
        NsSet.n.a1.shorts_?.==(Option.empty[Seq[Set[Short]]]).query.get ==> List(c)
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.shorts_?.!=(Some(Set(short1))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.!=(Some(Set(short1, short2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.shorts_?.!=(Some(Set(short1, short2, short3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1)))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1, short2)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1, short2, short3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1), Set(short2, short3)))).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.shorts_?.!=(Some(Seq(Set(short1, short2), Set.empty[Short]))).query.get ==> List(b)
        NsSet.n.a1.shorts_?.!=(Some(Set.empty[Short])).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.!=(Some(Seq.empty[Set[Short]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.shorts_?.==(Option.empty[Set[Short]]).query.get ==> List(c)
        NsSet.n.a1.shorts_?.==(Option.empty[Seq[Set[Short]]]).query.get ==> List(c)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        NsSet.n.shorts_?.insert(a, b, c).transact

        NsSet.n.a1.shorts_?.<(Some(short0)).query.get ==> List()
        NsSet.n.a1.shorts_?.<(Some(short1)).query.get ==> List()
        NsSet.n.a1.shorts_?.<(Some(short2)).query.get ==> List(a)
        NsSet.n.a1.shorts_?.<(Some(short3)).query.get ==> List(a, b)

        NsSet.n.a1.shorts_?.<=(Some(short0)).query.get ==> List()
        NsSet.n.a1.shorts_?.<=(Some(short1)).query.get ==> List(a)
        NsSet.n.a1.shorts_?.<=(Some(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.<=(Some(short3)).query.get ==> List(a, b)

        NsSet.n.a1.shorts_?.>(Some(short0)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>(Some(short1)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>(Some(short2)).query.get ==> List(b)
        NsSet.n.a1.shorts_?.>(Some(short3)).query.get ==> List(b)

        NsSet.n.a1.shorts_?.>=(Some(short0)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>=(Some(short1)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>=(Some(short2)).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>=(Some(short3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.shorts_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.shorts_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}