// GENERATED CODE ********************************
package molecule.coreTests.compliance.filter.set

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instants.insert(List(a, b)).transact

          _ <- Ns.i.a1.instants.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instants.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instants(Set(instant1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Set(instant1, instant2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instants(Set(instant2, instant1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instants(Set(instant1, instant2, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants(Seq(Set(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Seq(Set(instant2, instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instants(Set(instant1), Set(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Set(instant2, instant1), Set(instant4, instant3, instant2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants(Seq(Set(instant1), Set(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Set.empty[Instant], Set(instant2, instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants(Set.empty[Instant], Set.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Set.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Seq.empty[Set[Instant]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants(Seq(Set.empty[Instant])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instants.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instants.not(Set(instant1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.not(Set(instant1, instant2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instants.not(Set(instant2, instant1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instants.not(Set(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.not(Seq(Set(instant2, instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instants.not(Set(instant1), Set(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.not(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.not(Set(instant2, instant1), Set(instant4, instant3, instant2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1), Set(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.not(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.instants.not(Seq(Set(instant1, instant2), Set.empty[Instant])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.not(Set.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.not(Seq.empty[Set[Instant]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instants.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instants.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(instant1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(instant3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instants.has(Seq(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(Seq(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(instant3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instants.has(instant1, instant2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(instant1, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(instant2, instant3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(instant1, instant2, instant3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants.has(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(instant1, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.instants.has(Set(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(Set(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Set(instant2, instant3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.has(Set(instant2, instant3, instant4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(Seq(Set(instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant2, instant3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2), Set(instant0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2), Set(instant0, instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2), Set(instant2, instant3, instant4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2), Set(instant0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2), Set(instant0, instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.has(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants.has(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.has(Seq.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(Set.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.has(Seq.empty[Set[Instant]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(instant1, instant2))
        val b = (2, Set(instant2, instant3, instant4))
        for {
          _ <- Ns.i.instants.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instants.hasNo(instant0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(instant1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(instant3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(instant4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(instant5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants.hasNo(Seq(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Seq(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(Seq(instant4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(Seq(instant5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instants.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(instant1, instant4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(instant1, instant5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instants.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.instants.hasNo(Set(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2, instant3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Set(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Set(instant2, instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(Set(instant2, instant3, instant4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant2, instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2), Set(instant0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2), Set(instant0, instant3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2), Set(instant2, instant3, instant4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2), Set(instant0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2), Set(instant0, instant3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants.hasNo(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instants.hasNo(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Set.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Seq.empty[Set[Instant]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants.hasNo(Seq(Set.empty[Instant])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instants.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.instants_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.instants_?.insert(List(
            (0, None),
            (1, Some(Set(instant1, instant2))),
            (2, Some(Set(instant2, instant3, instant4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instants_(Set(instant1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Set(instant1, instant2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.instants_(Set(instant2, instant1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.instants_(Set(instant1, instant2, instant3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants_(Seq(Set(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_(Seq(Set(instant2, instant1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instants_(Set(instant1), Set(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_(Set(instant2, instant1), Set(instant4, instant3, instant2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instants_(Seq(Set(instant1), Set(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants_(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_(Set.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Seq.empty[Set[Instant]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_(Seq(Set.empty[Instant])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instants.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instants_.not(Set(instant1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.not(Set(instant1, instant2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.instants_.not(Set(instant2, instant1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.instants_.not(Set(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant2, instant1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instants_.not(Set(instant1), Set(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.not(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.not(Set(instant2, instant1), Set(instant4, instant3, instant2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1), Set(instant2, instant3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.instants_.not(Seq(Set(instant1, instant2), Set.empty[Instant])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.not(Set.empty[Instant]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.not(Seq.empty[Set[Instant]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instants.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instants_.has(instant0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(instant1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(instant3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instants_.has(Seq(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(Seq(instant1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq(instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(instant3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instants_.has(instant1, instant2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(instant1, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(instant2, instant3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(instant1, instant2, instant3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instants_.has(Seq(instant1, instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(instant1, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.instants_.has(Set(instant1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(Set(instant2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Set(instant2, instant3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.has(Set(instant2, instant3, instant4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant2, instant3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2), Set(instant0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2), Set(instant0, instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2), Set(instant2, instant3, instant4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2), Set(instant0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2), Set(instant0, instant3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.has(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants_.has(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.has(Seq.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(Set.empty[Instant]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.has(Seq.empty[Set[Instant]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.instants.insert(List(
            (1, Set(instant1, instant2)),
            (2, Set(instant2, instant3, instant4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instants_.hasNo(instant0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(instant1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(instant3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(instant4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(instant5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instants_.hasNo(instant1, instant2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(instant1, instant3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(instant1, instant4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(instant1, instant5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant1, instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant1, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant1, instant4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(instant1, instant5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2, instant3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Set(instant2, instant3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant2, instant3, instant4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant2, instant3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2), Set(instant0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2), Set(instant0, instant3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2), Set(instant2, instant3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2), Set(instant2, instant3, instant4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2), Set(instant0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2), Set(instant0, instant3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2), Set(instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instants_.hasNo(Set(instant1, instant2), Set.empty[Instant]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.instants_.hasNo(Seq.empty[Instant]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Set.empty[Instant]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Seq.empty[Set[Instant]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.instants_.hasNo(Seq(Set.empty[Instant])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instants_?.insert(a, b, c).transact

          _ <- Ns.i.a1.instants_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no instant value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.instants_?.query.get.map(_ ==> List(
            (2, Some(Set(instant2, instant3, instant4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instants_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.instants_?(Some(Set(instant1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?(Some(Set(instant1, instant2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instants_?(Some(Set(instant2, instant1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.instants_?(Some(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant1, instant2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant2, instant1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant1, instant2, instant3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant1), Set(instant2, instant3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant1, instant2), Set(instant2, instant3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?(Some(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants_?(Some(Set.empty[Instant])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?(Some(Seq.empty[Set[Instant]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?(Some(Seq(Set.empty[Instant]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.instants_?(Option.empty[Set[Instant]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instants_?(Option.empty[Seq[Set[Instant]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instants_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.instants_?.not(Some(Set(instant1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.not(Some(Set(instant1, instant2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instants_?.not(Some(Set(instant2, instant1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.instants_?.not(Some(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1, instant2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant2, instant1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1, instant2, instant3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1), Set(instant2, instant3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1, instant2), Set(instant2, instant3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant2, instant1), Set(instant4, instant3, instant2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.instants_?.not(Some(Seq(Set(instant1, instant2), Set.empty[Instant]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.not(Some(Set.empty[Instant])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.not(Some(Seq.empty[Set[Instant]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.instants_?(Option.empty[Set[Instant]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instants_?(Option.empty[Seq[Set[Instant]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instants_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.instants_?.has(Some(instant0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(instant1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(instant2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(instant3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant1, instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant1, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant1, instant2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant1, instant2, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant2, instant3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.has(Some(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2, instant3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant2, instant3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant2, instant3, instant4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2), Set(instant0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2), Set(instant0, instant3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2), Set(instant2, instant3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.has(Some(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.instants_?.has(Some(Seq.empty[Instant])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(Set.empty[Instant])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.has(Some(Seq.empty[Set[Instant]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.instants_?.has(Option.empty[Instant]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instants_?.has(Option.empty[Seq[Instant]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instants_?.has(Option.empty[Set[Instant]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.instants_?.has(Option.empty[Seq[Set[Instant]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(instant1, instant2)))
        val b = (2, Some(Set(instant2, instant3, instant4)))
        val c = (3, None)
        for {
          _ <- Ns.i.instants_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(instant5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant1, instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant1, instant3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant1, instant4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(instant1, instant5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant1, instant2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant1, instant2, instant3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant2, instant3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set(instant2, instant3, instant4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2, instant3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant2, instant3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant2, instant3, instant4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2), Set(instant0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2), Set(instant0, instant3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2), Set(instant2, instant3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set(instant1, instant2), Set(instant2, instant3, instant4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq.empty[Instant])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Set.empty[Instant])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq.empty[Set[Instant]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Some(Seq(Set.empty[Instant]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.instants_?.hasNo(Option.empty[Instant]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Option.empty[Seq[Instant]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Option.empty[Set[Instant]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.instants_?.hasNo(Option.empty[Seq[Set[Instant]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}