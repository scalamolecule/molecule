// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        Ns.i.a1.refs.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.refs(ref0).query.get ==> List()
        Ns.i.a1.refs(ref1).query.get ==> List(a)
        Ns.i.a1.refs(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs(ref3).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs(Seq(ref0)).query.get ==> List()
        Ns.i.a1.refs(Seq(ref1)).query.get ==> List(a)
        Ns.i.a1.refs(Seq(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(ref3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.refs(ref1, ref2).query.get ==> List(a, b)
        Ns.i.a1.refs(ref1, ref3).query.get ==> List(a, b)
        Ns.i.a1.refs(ref2, ref3).query.get ==> List(a, b)
        Ns.i.a1.refs(ref1, ref2, ref3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs(Seq(ref1, ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(ref1, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(ref1, ref2, ref3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.refs(Set(ref1)).query.get ==> List(a)
        Ns.i.a1.refs(Set(ref1, ref2)).query.get ==> List(a)
        Ns.i.a1.refs(Set(ref1, ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs(Set(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs(Set(ref2, ref3)).query.get ==> List(b)
        Ns.i.a1.refs(Set(ref2, ref3, ref4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs(Seq(Set(ref1))).query.get ==> List(a)
        Ns.i.a1.refs(Seq(Set(ref1, ref2))).query.get ==> List(a)
        Ns.i.a1.refs(Seq(Set(ref1, ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs(Seq(Set(ref2))).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(Set(ref2, ref3))).query.get ==> List(b)
        Ns.i.a1.refs(Seq(Set(ref2, ref3, ref4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.refs(Set(ref1, ref2), Set(ref0)).query.get ==> List(a)
        Ns.i.a1.refs(Set(ref1, ref2), Set(ref0, ref3)).query.get ==> List(a)
        Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref0))).query.get ==> List(a)
        Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get ==> List(a)
        Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(a)
        Ns.i.a1.refs(Seq.empty[Long]).query.get ==> List()
        Ns.i.a1.refs(Set.empty[Long]).query.get ==> List()
        Ns.i.a1.refs(Seq.empty[Set[Long]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.refs.not(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs.not(ref1).query.get ==> List(b)
        Ns.i.a1.refs.not(ref2).query.get ==> List()
        Ns.i.a1.refs.not(ref3).query.get ==> List(a)
        Ns.i.a1.refs.not(ref4).query.get ==> List(a)
        Ns.i.a1.refs.not(ref5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs.not(Seq(ref0)).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Seq(ref1)).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq(ref2)).query.get ==> List()
        Ns.i.a1.refs.not(Seq(ref3)).query.get ==> List(a)
        Ns.i.a1.refs.not(Seq(ref4)).query.get ==> List(a)
        Ns.i.a1.refs.not(Seq(ref5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.refs.not(ref1, ref2).query.get ==> List()
        Ns.i.a1.refs.not(ref1, ref3).query.get ==> List()
        Ns.i.a1.refs.not(ref1, ref4).query.get ==> List()
        Ns.i.a1.refs.not(ref1, ref5).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs.not(Seq(ref1, ref2)).query.get ==> List()
        Ns.i.a1.refs.not(Seq(ref1, ref3)).query.get ==> List()
        Ns.i.a1.refs.not(Seq(ref1, ref4)).query.get ==> List()
        Ns.i.a1.refs.not(Seq(ref1, ref5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.refs.not(Set(ref1)).query.get ==> List(b)
        Ns.i.a1.refs.not(Set(ref1, ref2)).query.get ==> List(b)
        Ns.i.a1.refs.not(Set(ref1, ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Set(ref2)).query.get ==> List()
        Ns.i.a1.refs.not(Set(ref2, ref3)).query.get ==> List(a)
        Ns.i.a1.refs.not(Set(ref2, ref3, ref4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.refs.not(Seq(Set(ref1))).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2))).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Seq(Set(ref2))).query.get ==> List()
        Ns.i.a1.refs.not(Seq(Set(ref2, ref3))).query.get ==> List(a)
        Ns.i.a1.refs.not(Seq(Set(ref2, ref3, ref4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref0)).query.get ==> List(b)
        Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref0, ref3)).query.get ==> List(b)
        Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List()
        // Same as
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref0))).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.refs.not(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(b)
        Ns.i.a1.refs.not(Seq.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Set.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Seq.empty[Set[Long]]).query.get ==> List(a, b)
        Ns.i.a1.refs.not(Seq(Set.empty[Long])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.refs.==(Set(ref1)).query.get ==> List()
        Ns.i.a1.refs.==(Set(ref1, ref2)).query.get ==> List(a) // include exact match
        Ns.i.a1.refs.==(Set(ref1, ref2, ref3)).query.get ==> List()
        // Same as
        Ns.i.a1.refs.==(Seq(Set(ref1))).query.get ==> List()
        Ns.i.a1.refs.==(Seq(Set(ref1, ref2))).query.get ==> List(a)
        Ns.i.a1.refs.==(Seq(Set(ref1, ref2, ref3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs.==(Set(ref1), Set(ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs.==(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(a)
        Ns.i.a1.refs.==(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs.==(Seq(Set(ref1), Set(ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs.==(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(a)
        Ns.i.a1.refs.==(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs.==(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(a)
        Ns.i.a1.refs.==(Set.empty[Long], Set(ref1, ref2)).query.get ==> List(a)
        Ns.i.a1.refs.==(Set.empty[Long]).query.get ==> List()
        Ns.i.a1.refs.==(Seq.empty[Set[Long]]).query.get ==> List()
        Ns.i.a1.refs.==(Seq(Set.empty[Long])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.refs.!=(Set(ref1)).query.get ==> List(a, b)
        Ns.i.a1.refs.!=(Set(ref1, ref2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.refs.!=(Set(ref1, ref2, ref3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs.!=(Seq(Set(ref1))).query.get ==> List(a, b)
        Ns.i.a1.refs.!=(Seq(Set(ref1, ref2))).query.get ==> List(b)
        Ns.i.a1.refs.!=(Seq(Set(ref1, ref2, ref3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs.!=(Set(ref1), Set(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs.!=(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(b)
        Ns.i.a1.refs.!=(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List()
        // Same as
        Ns.i.a1.refs.!=(Seq(Set(ref1), Set(ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(b)
        Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set.empty[Long])).query.get ==> List(b)
        Ns.i.a1.refs.!=(Set.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs.!=(Seq.empty[Set[Long]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        Ns.i.refs.insert(List(a, b)).transact

        Ns.i.a1.refs.<(ref0).query.get ==> List()
        Ns.i.a1.refs.<(ref1).query.get ==> List()
        Ns.i.a1.refs.<(ref2).query.get ==> List(a)
        Ns.i.a1.refs.<(ref3).query.get ==> List(a, b)

        Ns.i.a1.refs.<=(ref0).query.get ==> List()
        Ns.i.a1.refs.<=(ref1).query.get ==> List(a)
        Ns.i.a1.refs.<=(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs.<=(ref3).query.get ==> List(a, b)

        Ns.i.a1.refs.>(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs.>(ref1).query.get ==> List(a, b)
        Ns.i.a1.refs.>(ref2).query.get ==> List(b)
        Ns.i.a1.refs.>(ref3).query.get ==> List(b)

        Ns.i.a1.refs.>=(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs.>=(ref1).query.get ==> List(a, b)
        Ns.i.a1.refs.>=(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs.>=(ref3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        Ns.i.a1.refs_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.refs_(ref0).query.get ==> List()
        Ns.i.a1.refs_(ref1).query.get ==> List(a)
        Ns.i.a1.refs_(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs_(ref3).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs_(Seq(ref0)).query.get ==> List()
        Ns.i.a1.refs_(Seq(ref1)).query.get ==> List(a)
        Ns.i.a1.refs_(Seq(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(ref3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.refs_(ref1, ref2).query.get ==> List(a, b)
        Ns.i.a1.refs_(ref1, ref3).query.get ==> List(a, b)
        Ns.i.a1.refs_(ref2, ref3).query.get ==> List(a, b)
        Ns.i.a1.refs_(ref1, ref2, ref3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_(Seq(ref1, ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(ref1, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(ref1, ref2, ref3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.refs_(Set(ref1)).query.get ==> List(a)
        Ns.i.a1.refs_(Set(ref1, ref2)).query.get ==> List(a)
        Ns.i.a1.refs_(Set(ref1, ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs_(Set(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Set(ref2, ref3)).query.get ==> List(b)
        Ns.i.a1.refs_(Set(ref2, ref3, ref4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs_(Seq(Set(ref1))).query.get ==> List(a)
        Ns.i.a1.refs_(Seq(Set(ref1, ref2))).query.get ==> List(a)
        Ns.i.a1.refs_(Seq(Set(ref1, ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs_(Seq(Set(ref2))).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(Set(ref2, ref3))).query.get ==> List(b)
        Ns.i.a1.refs_(Seq(Set(ref2, ref3, ref4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.refs_(Set(ref1, ref2), Set(ref0)).query.get ==> List(a)
        Ns.i.a1.refs_(Set(ref1, ref2), Set(ref0, ref3)).query.get ==> List(a)
        Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref0))).query.get ==> List(a)
        Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get ==> List(a)
        Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs_(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(a)
        Ns.i.a1.refs_(Seq.empty[Long]).query.get ==> List()
        Ns.i.a1.refs_(Set.empty[Long]).query.get ==> List()
        Ns.i.a1.refs_(Seq.empty[Set[Long]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.refs_.not(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(ref1).query.get ==> List(b)
        Ns.i.a1.refs_.not(ref2).query.get ==> List()
        Ns.i.a1.refs_.not(ref3).query.get ==> List(a)
        Ns.i.a1.refs_.not(ref4).query.get ==> List(a)
        Ns.i.a1.refs_.not(ref5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_.not(Seq(ref0)).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Seq(ref1)).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq(ref2)).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(ref3)).query.get ==> List(a)
        Ns.i.a1.refs_.not(Seq(ref4)).query.get ==> List(a)
        Ns.i.a1.refs_.not(Seq(ref5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.refs_.not(ref1, ref2).query.get ==> List()
        Ns.i.a1.refs_.not(ref1, ref3).query.get ==> List()
        Ns.i.a1.refs_.not(ref1, ref4).query.get ==> List()
        Ns.i.a1.refs_.not(ref1, ref5).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs_.not(Seq(ref1, ref2)).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(ref1, ref3)).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(ref1, ref4)).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(ref1, ref5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.refs_.not(Set(ref1)).query.get ==> List(b)
        Ns.i.a1.refs_.not(Set(ref1, ref2)).query.get ==> List(b)
        Ns.i.a1.refs_.not(Set(ref1, ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Set(ref2)).query.get ==> List()
        Ns.i.a1.refs_.not(Set(ref2, ref3)).query.get ==> List(a)
        Ns.i.a1.refs_.not(Set(ref2, ref3, ref4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.refs_.not(Seq(Set(ref1))).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2))).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Seq(Set(ref2))).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(Set(ref2, ref3))).query.get ==> List(a)
        Ns.i.a1.refs_.not(Seq(Set(ref2, ref3, ref4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref0)).query.get ==> List(b)
        Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref0, ref3)).query.get ==> List(b)
        Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List()
        // Same as
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref0))).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.refs_.not(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(b)
        Ns.i.a1.refs_.not(Seq.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Set.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Seq.empty[Set[Long]]).query.get ==> List(a, b)
        Ns.i.a1.refs_.not(Seq(Set.empty[Long])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.refs_.==(Set(ref1)).query.get ==> List()
        Ns.i.a1.refs_.==(Set(ref1, ref2)).query.get ==> List(a) // include exact match
        Ns.i.a1.refs_.==(Set(ref1, ref2, ref3)).query.get ==> List()
        // Same as
        Ns.i.a1.refs_.==(Seq(Set(ref1))).query.get ==> List()
        Ns.i.a1.refs_.==(Seq(Set(ref1, ref2))).query.get ==> List(a)
        Ns.i.a1.refs_.==(Seq(Set(ref1, ref2, ref3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs_.==(Set(ref1), Set(ref2, ref3)).query.get ==> List()
        Ns.i.a1.refs_.==(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(a)
        Ns.i.a1.refs_.==(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_.==(Seq(Set(ref1), Set(ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs_.==(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(a)
        Ns.i.a1.refs_.==(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs_.==(Set(ref1, ref2), Set.empty[Long]).query.get ==> List(a)
        Ns.i.a1.refs_.==(Set.empty[Long]).query.get ==> List()
        Ns.i.a1.refs_.==(Seq.empty[Set[Long]]).query.get ==> List()
        Ns.i.a1.refs_.==(Seq(Set.empty[Long])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.refs_.!=(Set(ref1)).query.get ==> List(a, b)
        Ns.i.a1.refs_.!=(Set(ref1, ref2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.refs_.!=(Set(ref1, ref2, ref3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_.!=(Seq(Set(ref1))).query.get ==> List(a, b)
        Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2))).query.get ==> List(b)
        Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2, ref3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs_.!=(Set(ref1), Set(ref2, ref3)).query.get ==> List(a, b)
        Ns.i.a1.refs_.!=(Set(ref1, ref2), Set(ref2, ref3)).query.get ==> List(b)
        Ns.i.a1.refs_.!=(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get ==> List()
        // Same as
        Ns.i.a1.refs_.!=(Seq(Set(ref1), Set(ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get ==> List(b)
        Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set.empty[Long])).query.get ==> List(b)
        Ns.i.a1.refs_.!=(Set.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs_.!=(Seq.empty[Set[Long]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        Ns.i.a1.refs_.<(ref0).query.get ==> List()
        Ns.i.a1.refs_.<(ref1).query.get ==> List()
        Ns.i.a1.refs_.<(ref2).query.get ==> List(a)
        Ns.i.a1.refs_.<(ref3).query.get ==> List(a, b)

        Ns.i.a1.refs_.<=(ref0).query.get ==> List()
        Ns.i.a1.refs_.<=(ref1).query.get ==> List(a)
        Ns.i.a1.refs_.<=(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs_.<=(ref3).query.get ==> List(a, b)

        Ns.i.a1.refs_.>(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs_.>(ref1).query.get ==> List(a, b)
        Ns.i.a1.refs_.>(ref2).query.get ==> List(b)
        Ns.i.a1.refs_.>(ref3).query.get ==> List(b)

        Ns.i.a1.refs_.>=(ref0).query.get ==> List(a, b)
        Ns.i.a1.refs_.>=(ref1).query.get ==> List(a, b)
        Ns.i.a1.refs_.>=(ref2).query.get ==> List(a, b)
        Ns.i.a1.refs_.>=(ref3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        Ns.i.a1.refs_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.refs_?(Some(ref0)).query.get ==> List()
        Ns.i.a1.refs_?(Some(ref1)).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(ref3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs_?(Some(Seq(ref0))).query.get ==> List()
        Ns.i.a1.refs_?(Some(Seq(ref1))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Seq(ref2))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(ref3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.refs_?(Some(Seq(ref1, ref2))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(ref1, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(ref1, ref2, ref3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.refs_?(Some(Set(ref1))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Set(ref1, ref2))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Set(ref1, ref2, ref3))).query.get ==> List()
        Ns.i.a1.refs_?(Some(Set(ref2))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Set(ref2, ref3))).query.get ==> List(b)
        Ns.i.a1.refs_?(Some(Set(ref2, ref3, ref4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.refs_?(Some(Seq(Set(ref1)))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2)))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2, ref3)))).query.get ==> List()
        Ns.i.a1.refs_?(Some(Seq(Set(ref2)))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref3)))).query.get ==> List(b)
        Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref3, ref4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get ==> List(a)
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get ==> List(a, b)
        Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs_?(Some(Seq.empty[Long])).query.get ==> List()
        Ns.i.a1.refs_?(Some(Set.empty[Long])).query.get ==> List()
        Ns.i.a1.refs_?(Some(Seq.empty[Set[Long]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.refs_?(Option.empty[Long]).query.get ==> List(c)
        Ns.i.a1.refs_?(Option.empty[Seq[Long]]).query.get ==> List(c)
        Ns.i.a1.refs_?(Option.empty[Set[Long]]).query.get ==> List(c)
        Ns.i.a1.refs_?(Option.empty[Seq[Set[Long]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.refs_?.not(Some(ref0)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(ref1)).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(ref2)).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(ref3)).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(ref4)).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(ref5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_?.not(Some(Seq(ref0))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Seq(ref1))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Seq(ref2))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(ref3))).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(Seq(ref4))).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(Seq(ref5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.refs_?.not(Some(Seq(ref1, ref2))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(ref1, ref3))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(ref1, ref4))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(ref1, ref5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.refs_?.not(Some(Set(ref1))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Set(ref1, ref2))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Set(ref1, ref2, ref3))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Set(ref2))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Set(ref2, ref3))).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(Set(ref2, ref3, ref4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1)))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2)))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2, ref3)))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref2)))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref2, ref3)))).query.get ==> List(a)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref2, ref3, ref4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get ==> List(b)
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get ==> List()
        Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.refs_?.not(Some(Seq.empty[Long])).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Set.empty[Long])).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Seq.empty[Set[Long]])).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Some(Seq(Set.empty[Long]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.refs_?.not(Option.empty[Long]).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Option.empty[Seq[Long]]).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Option.empty[Set[Long]]).query.get ==> List(a, b)
        Ns.i.a1.refs_?.not(Option.empty[Seq[Set[Long]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.refs_?.==(Some(Set(ref1))).query.get ==> List()
        Ns.i.a1.refs_?.==(Some(Set(ref1, ref2))).query.get ==> List(a) // include exact match
        Ns.i.a1.refs_?.==(Some(Set(ref1, ref2, ref3))).query.get ==> List()
        // Same as
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1)))).query.get ==> List()
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2)))).query.get ==> List(a)
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2, ref3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get ==> List()
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get ==> List(a)
        Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.refs_?.==(Some(Set.empty[Long])).query.get ==> List()
        Ns.i.a1.refs_?.==(Some(Seq.empty[Set[Long]])).query.get ==> List()
        Ns.i.a1.refs_?.==(Some(Seq(Set.empty[Long]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.refs_?.==(Option.empty[Set[Long]]).query.get ==> List(c)
        Ns.i.a1.refs_?.==(Option.empty[Seq[Set[Long]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.refs_?.!=(Some(Set(ref1))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.!=(Some(Set(ref1, ref2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.refs_?.!=(Some(Set(ref1, ref2, ref3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1)))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2)))).query.get ==> List(b)
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2, ref3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get ==> List(a, b)
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get ==> List(b)
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set.empty[Long]))).query.get ==> List(b)
        Ns.i.a1.refs_?.!=(Some(Set.empty[Long])).query.get ==> List(a, b)
        Ns.i.a1.refs_?.!=(Some(Seq.empty[Set[Long]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.refs_?.==(Option.empty[Set[Long]]).query.get ==> List(c)
        Ns.i.a1.refs_?.==(Option.empty[Seq[Set[Long]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        Ns.i.refs_?.insert(a, b, c).transact

        Ns.i.a1.refs_?.<(Some(ref0)).query.get ==> List()
        Ns.i.a1.refs_?.<(Some(ref1)).query.get ==> List()
        Ns.i.a1.refs_?.<(Some(ref2)).query.get ==> List(a)
        Ns.i.a1.refs_?.<(Some(ref3)).query.get ==> List(a, b)

        Ns.i.a1.refs_?.<=(Some(ref0)).query.get ==> List()
        Ns.i.a1.refs_?.<=(Some(ref1)).query.get ==> List(a)
        Ns.i.a1.refs_?.<=(Some(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.<=(Some(ref3)).query.get ==> List(a, b)

        Ns.i.a1.refs_?.>(Some(ref0)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>(Some(ref1)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>(Some(ref2)).query.get ==> List(b)
        Ns.i.a1.refs_?.>(Some(ref3)).query.get ==> List(b)

        Ns.i.a1.refs_?.>=(Some(ref0)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>=(Some(ref1)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>=(Some(ref2)).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>=(Some(ref3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.refs_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.refs_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.refs_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}