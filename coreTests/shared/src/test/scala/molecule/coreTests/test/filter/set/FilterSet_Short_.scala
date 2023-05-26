// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Short_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          _ <- Ns.i.a1.shorts.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts(Set(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Set(short1, short2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts(Set(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts(Seq(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts(Set(short1), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set.empty[Short], Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts(Seq(Set.empty[Short])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts.not(Set(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts.not(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts.not(Set(short1), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts.not(Seq(Set(short1, short2), Set.empty[Short])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.not(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.not(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts.has(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.has(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts.has(Set(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Set(short1, short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(Set(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.has(Set(short2, short3, short4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(Seq(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts.has(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.has(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts.has(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.has(Seq.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.has(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(short4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts.hasNo(Seq(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(Seq(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(Seq(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts.hasNo(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(short1, short5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts.hasNo(Set(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Set(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(Set(short2, short3, short4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts.hasNo(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasNo(Seq.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasNo(Seq(Set.empty[Short])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shorts.insert(List(a, b)).transact

          // <
          _ <- Ns.i.a1.shorts.hasLt(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasLt(short1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasLt(short2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasLt(short3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.shorts.hasLe(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts.hasLe(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts.hasLe(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasLe(short3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.shorts.hasGt(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasGt(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasGt(short2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts.hasGt(short3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.shorts.hasGe(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasGe(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasGe(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts.hasGe(short3).query.get.map(_ ==> List(b))
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


      "apply (equal)" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts_(Set(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Set(short1, short2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts_(Set(short1, short2, short3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_(Set(short1), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_(Seq(Set.empty[Short])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts_.not(Set(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_.not(Set(short1), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts_.not(Seq(Set(short1, short2), Set.empty[Short])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.not(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.not(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts_.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_.has(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts_.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.has(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts_.has(Set(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(Set(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Set(short2, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.has(Set(short2, short3, short4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.has(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_.has(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.has(Seq.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(Set.empty[Short]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.has(Seq.empty[Set[Short]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts_.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(short4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts_.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(short1, short5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Set(short2, short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short2, short3, short4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short2, short3, short4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2), Set(short0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2), Set(short0, short3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2), Set(short2, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2), Set(short2, short3, short4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2), Set(short0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2), Set(short0, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2), Set(short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set(short1, short2), Set(short2, short3, short4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts_.hasNo(Set(short1, short2), Set.empty[Short]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Set.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasNo(Seq(Set.empty[Short])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.shorts.insert(List(
            (a, Set(short1, short2)),
            (b, Set(short2, short3, short4))
          )).transact

          // <
          _ <- Ns.i.a1.shorts_.hasLt(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasLt(short1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasLt(short2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasLt(short3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.shorts_.hasLe(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_.hasLe(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_.hasLe(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasLe(short3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.shorts_.hasGt(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasGt(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasGt(short2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_.hasGt(short3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.shorts_.hasGe(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasGe(short1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasGe(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_.hasGe(short3).query.get.map(_ ==> List(b))
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


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.shorts_?(Some(Set(short1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Set(short1, short2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.shorts_?(Some(Set(short1, short2, short3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1), Set(short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_?(Some(Set.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?(Some(Seq(Set.empty[Short]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1, short2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.shorts_?.not(Some(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1), Set(short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.shorts_?.not(Some(Seq(Set(short1, short2), Set.empty[Short]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.not(Some(Set.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.not(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.shorts_?.has(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(short3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short1, short2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short1, short2, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short2, short3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.has(Some(Set(short2, short3, short4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short2, short3, short4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.has(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shorts_?.has(Some(Seq.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(Set.empty[Short])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.has(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.shorts_?.has(Option.empty[Short]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?.has(Option.empty[Seq[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?.has(Option.empty[Set[Short]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shorts_?.has(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(short5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short1, short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short1, short4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(short1, short5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short1, short2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short1, short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short2, short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set(short2, short3, short4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2, short3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short2, short3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short2, short3, short4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2), Set(short0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2), Set(short0, short3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2), Set(short2, short3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set(short1, short2), Set(short2, short3, short4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Set.empty[Short])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq.empty[Set[Short]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Some(Seq(Set.empty[Short]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.shorts_?.hasNo(Option.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Option.empty[Seq[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Option.empty[Set[Short]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasNo(Option.empty[Seq[Set[Short]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(short1, short2)))
        val b = (2, Some(Set(short2, short3, short4)))
        val c = (3, None)
        for {
          _ <- Ns.i.shorts_?.insert(a, b, c).transact

          // <
          _ <- Ns.i.a1.shorts_?.hasLt(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasLt(Some(short1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasLt(Some(short2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasLt(Some(short3)).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.shorts_?.hasLe(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shorts_?.hasLe(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shorts_?.hasLe(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasLe(Some(short3)).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.shorts_?.hasGt(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGt(Some(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGt(Some(short2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shorts_?.hasGt(Some(short3)).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.shorts_?.hasGe(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGe(Some(short1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGe(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGe(Some(short3)).query.get.map(_ ==> List(b))


          // None comparison matches any asserted values
          _ <- Ns.i.a1.shorts_?.hasLt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasLe(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shorts_?.hasGe(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}