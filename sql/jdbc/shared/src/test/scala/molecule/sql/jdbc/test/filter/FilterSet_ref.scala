package molecule.sql.jdbc.test.filter

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, _}
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.{List, Seq, Set}
import scala.language.implicitConversions


object FilterSet_ref extends JdbcTestSuite {

  override val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          _ <- Ns.i.a1.refs.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs(Set(ref1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Set(ref1, ref2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs(Seq(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set.empty[Long], Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs.not(Set(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs.not(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs.not(Seq(Set(ref1, ref2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs.has(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Seq(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs.has(ref1, ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref1, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref2, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs.has(Set(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Set(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.has(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Seq(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs.has(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(ref1, ref2))
        val b = (2, Set(ref2, ref3, ref4))
        for {
          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs.hasNo(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(ref1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(ref4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(ref5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs.hasNo(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs.hasNo(Set(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Set(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs.hasNo(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
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


      "apply (equal)" - types { implicit conn =>
        val (a, b, x) = (1, 2, 3)
        for {
          _ <- Ns.i.refs_?.insert(List(
            (a, Some(Set(ref1, ref2))),
            (b, Some(Set(ref2, ref3, ref4))),
            (x, None),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs_(Set(ref1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Set(ref1, ref2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs_(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_(Seq(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs_.not(Set(ref1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_.not(Set(ref1), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs_.not(Seq(Set(ref1, ref2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs_.has(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(ref3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Seq(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(ref3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs_.has(ref1, ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(ref1, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(ref2, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs_.has(Set(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Set(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Set(ref2, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.has(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.has(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_.has(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.has(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.refs.insert(List(
            (a, Set(ref1, ref2)),
            (b, Set(ref2, ref3, ref4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs_.hasNo(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(ref1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(ref4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(ref5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Set(ref2, ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref2, ref3, ref4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2), Set(ref0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2), Set(ref0, ref3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2), Set(ref2, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2), Set(ref2, ref3, ref4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2), Set(ref0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2), Set(ref0, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2), Set(ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs_.hasNo(Set(ref1, ref2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_.hasNo(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
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


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.refs_?(Some(Set(ref1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_?(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1, ref2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.refs_?.not(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.refs_?.not(Some(Seq(Set(ref1, ref2), Set.empty[Long]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.not(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.not(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs_?.has(Some(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(ref3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref1, ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref1, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref1, ref2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref2, ref3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.has(Some(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref2, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.has(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_?.has(Some(Seq.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.has(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.refs_?.has(Option.empty[Long]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?.has(Option.empty[Seq[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?.has(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.refs_?.has(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(ref1, ref2)))
        val b = (2, Some(Set(ref2, ref3, ref4)))
        val c = (3, None)
        for {
          _ <- Ns.i.refs_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(ref5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref1, ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref1, ref3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref1, ref4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(ref1, ref5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref1, ref2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref2, ref3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set(ref2, ref3, ref4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref2, ref3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref2, ref3, ref4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2), Set(ref0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2), Set(ref0, ref3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set(ref1, ref2), Set(ref2, ref3, ref4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.refs_?.hasNo(Option.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Option.empty[Seq[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Option.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs_?.hasNo(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}
