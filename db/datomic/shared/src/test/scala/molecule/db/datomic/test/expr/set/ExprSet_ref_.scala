// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          _ <- Ns.i.a1.refs.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(ref3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(ref3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs(ref1, ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(ref1, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(ref2, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs(Set(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Set(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs(Seq(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs.not(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(ref1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(ref3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(ref4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(ref5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(Seq(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(Seq(ref5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs.not(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(ref1, ref5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(ref1, ref5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs.not(Set(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Set(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs.==(Set(ref1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Set(ref1, ref2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs.==(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs.==(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.==(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.==(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs.==(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.==(Set.empty[Long], Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.==(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.==(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs.!=(Set(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.!=(Set(ref1, ref2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs.!=(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs.!=(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.!=(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.!=(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs.!=(Seq(Set(ref1, ref2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.!=(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.!=(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          _ <- Ns.i.a1.refs.<(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.<(ref1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.<(ref2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.<(ref3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs.<=(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.<=(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.<=(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.<=(ref3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs.>(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.>(ref1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.>(ref2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.>(ref3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.refs.>=(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.>=(ref1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.>=(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.>=(ref3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          _ <- Ns.i.a1.refs_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs_(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(ref3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(ref3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs_(ref1, ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(ref1, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(ref2, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs_(Set(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Set(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_(Seq(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs_.not(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(ref1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(ref3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(ref4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(ref5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(Seq(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(Seq(ref5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs_.not(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(ref1, ref5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(ref1, ref5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs_.not(Set(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Set(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs_.==(Set(ref1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Set(ref1, ref2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs_.==(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_.==(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.==(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.==(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_.==(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.==(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.==(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs_.!=(Set(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.!=(Set(ref1, ref2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs_.!=(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_.!=(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.!=(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.!=(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs_.!=(Seq(Set(ref1, ref2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.!=(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.!=(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          _ <- Ns.i.a1.refs_.<(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.<(ref1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.<(ref2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.<(ref3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs_.<=(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.<=(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.<=(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.<=(ref3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs_.>(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.>(ref1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.>(ref2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.>(ref3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.refs_.>=(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.>=(ref1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.>=(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.>=(ref3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          _ <- Ns.i.a1.refs_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no ref value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.refs_?.query.get.map(_ ==> List(
            (2, Some(Set(ref2, ref3, ref4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs_?(Some(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(ref3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_?(Some(Seq(ref0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(ref3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs_?(Some(Seq(ref1, ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(ref1, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs_?(Some(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?(Some(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_?(Some(Seq.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?(Option.empty[Long]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?(Option.empty[Seq[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs_?.not(Some(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(ref5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref1, ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref1, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref1, ref4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(ref1, ref5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref2, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs_?.not(Some(Seq.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.refs_?.not(Option.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Option.empty[Seq[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Option.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs_?.==(Some(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.==(Some(Set(ref1, ref2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs_?.==(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_?.==(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.==(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.==(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?.==(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?.==(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs_?.!=(Some(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.!=(Some(Set(ref1, ref2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs_?.!=(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs_?.!=(Some(Seq(Set(ref1, ref2), Set.empty[Long]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.!=(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.!=(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?.==(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?.==(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          _ <- Ns.i.a1.refs_?.<(Some(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.<(Some(ref1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.<(Some(ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.<(Some(ref3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs_?.<=(Some(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.<=(Some(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.<=(Some(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.<=(Some(ref3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.refs_?.>(Some(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>(Some(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>(Some(ref2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.>(Some(ref3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.refs_?.>=(Some(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>=(Some(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>=(Some(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>=(Some(ref3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.refs_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}