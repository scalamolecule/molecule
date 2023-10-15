package molecule.coreTests.compliance.filter.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          _ <- Ns.i.a1.ints.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints(Set(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Set(int1, int2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints(Set(int2, int1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints(Set(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints(Seq(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int2, int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints(Set(int1), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set(int2, int1), Set(int4, int3, int2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Seq(Set(int2, int1), Set(int4, int3, int2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set.empty[Int], Set(int2, int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints(Set.empty[Int], Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints(Seq(Set.empty[Int])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints.not(Set(int1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints.not(Set(int2, int1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints.not(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(Set(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int2, int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.ints.not(Set(int1), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set(int2, int1), Set(int4, int3, int2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints.not(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Seq(Set(int2, int1), Set(int4, int3, int2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints.not(Seq(Set(int1, int2), Set.empty[Int])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.not(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.not(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints.has(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(Seq(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints.has(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.has(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(int1, int2, int3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints.has(Set(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Set(int1, int2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Set(int1, int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(Set(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Set(int2, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.has(Set(int2, int3, int4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints.has(Seq(Set(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(Seq(Set(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.has(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints.has(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.has(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints.has(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.has(Seq.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.has(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.ints.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints.hasNo(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(int4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints.hasNo(Seq(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Seq(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(Seq(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(Seq(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints.hasNo(Set(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Set(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Set(int2, int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(Set(int2, int3, int4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints.hasNo(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints.hasNo(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Set.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Seq.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints.hasNo(Seq(Set.empty[Int])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.ints.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.ints_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.ints_?.insert(List(
            (0, None),
            (1, Some(Set(int1, int2))),
            (2, Some(Set(int2, int3, int4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints_(Set(int1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Set(int1, int2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.ints_(Set(int2, int1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.ints_(Set(int1, int2, int3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_(Seq(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_(Seq(Set(int2, int1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_(Set(int1), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_(Set(int2, int1), Set(int4, int3, int2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.ints_(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_(Seq(Set(int2, int1), Set(int4, int3, int2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_(Seq(Set.empty[Int])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.ints.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints_.not(Set(int1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.ints_.not(Set(int2, int1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.ints_.not(Set(int1, int2, int3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int2, int1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.ints_.not(Set(int1), Set(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.not(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.not(Set(int2, int1), Set(int4, int3, int2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1), Set(int2, int3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.not(Seq(Set(int2, int1), Set(int4, int3, int2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints_.not(Seq(Set(int1, int2), Set.empty[Int])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.not(Set.empty[Int]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.not(Seq.empty[Set[Int]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.ints.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints_.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(int3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.ints_.has(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(Seq(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(int3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints_.has(int1, int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(int1, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(int1, int2, int3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.ints_.has(Seq(int1, int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(int1, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(int1, int2, int3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints_.has(Set(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Set(int1, int2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Set(int1, int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(Set(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Set(int2, int3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.has(Set(int2, int3, int4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(Seq(Set(int2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int2, int3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints_.has(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.has(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_.has(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.has(Seq.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(Set.empty[Int]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.has(Seq.empty[Set[Int]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.ints.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(int4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(int5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.ints_.hasNo(Seq(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(Seq(int4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(Seq(int5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints_.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(int1, int5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.ints_.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints_.hasNo(Set(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Set(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Set(int2, int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(Set(int2, int3, int4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2, int3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int2, int3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int2, int3, int4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2), Set(int0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2), Set(int0, int3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2), Set(int2, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2), Set(int2, int3, int4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2), Set(int0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2), Set(int0, int3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2), Set(int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set(int1, int2), Set(int2, int3, int4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints_.hasNo(Set(int1, int2), Set.empty[Int]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.ints_.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Set.empty[Int]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Seq.empty[Set[Int]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.ints_.hasNo(Seq(Set.empty[Int])).query.get.map(_ ==> List(1, 2))
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


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.ints_?(Some(Set(int1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Set(int1, int2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints_?(Some(Set(int2, int1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.ints_?(Some(Set(int1, int2, int3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int2, int1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1), Set(int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?(Some(Seq(Set(int2, int1), Set(int4, int3, int2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_?(Some(Set.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1, int2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints_?.not(Some(Set(int2, int1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.ints_?.not(Some(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1), Set(int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int2, int1), Set(int4, int3, int2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.ints_?.not(Some(Seq(Set(int1, int2), Set.empty[Int]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.not(Some(Set.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.not(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.ints_?.has(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(int3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int1, int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int1, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.ints_?.has(Some(Set(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Set(int1, int2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Set(int1, int2, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(Set(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Set(int2, int3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.has(Some(Set(int2, int3, int4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int2, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int2, int3, int4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2), Set(int0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.has(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.ints_?.has(Some(Seq.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(Set.empty[Int])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.has(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.ints_?.has(Option.empty[Int]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?.has(Option.empty[Seq[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?.has(Option.empty[Set[Int]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.ints_?.has(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.ints_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.ints_?.hasNo(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(int5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1, int2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2, int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2, int3, int4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2, int3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2, int3, int4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Set.empty[Int])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Seq[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}