// GENERATED CODE ********************************
package molecule.datalog.datomic.test.filter.set

import java.util.UUID
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object FilterSet_UUID_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuids.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set.empty[UUID], Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq(Set.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids.not(Set(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids.not(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids.has(Set(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(Set(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.has(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.has(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids.has(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.has(Seq.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.has(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids.hasNo(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasNo(Seq(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // <
          _ <- Ns.i.a1.uuids.hasLt(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasLt(uuid1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasLt(uuid2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasLt(uuid3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.uuids.hasLe(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.hasLe(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.hasLe(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasLe(uuid3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.uuids.hasGt(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasGt(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasGt(uuid2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.hasGt(uuid3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.uuids.hasGe(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasGe(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasGe(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.hasGe(uuid3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          _ <- Ns.i.a1.uuids_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids_(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq(Set.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids_.not(Set(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_.not(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids_.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids_.has(Set(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(Set(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.has(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.has(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_.has(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.has(Seq.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.has(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids_.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids_.hasNo(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasNo(Seq(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // <
          _ <- Ns.i.a1.uuids_.hasLt(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasLt(uuid1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasLt(uuid2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasLt(uuid3).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.uuids_.hasLe(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.hasLe(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.hasLe(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasLe(uuid3).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.uuids_.hasGt(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasGt(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasGt(uuid2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.hasGt(uuid3).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.uuids_.hasGe(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasGe(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasGe(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.hasGe(uuid3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuids_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no uuid value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.uuids_?.query.get.map(_ ==> List(
            (2, Some(Set(uuid2, uuid3, uuid4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_?(Some(Set.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set.empty[UUID]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set.empty[UUID]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids_?.has(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(uuid3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.has(Some(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.has(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_?.has(Some(Seq.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(Set.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.has(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?.has(Option.empty[UUID]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?.has(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?.has(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?.has(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(uuid5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid1, uuid4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(uuid1, uuid5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Some(Seq(Set.empty[UUID]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.uuids_?.hasNo(Option.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Option.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasNo(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // <
          _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid3)).query.get.map(_ ==> List(a, b))

          // <=
          _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid3)).query.get.map(_ ==> List(a, b))

          // >
          _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid3)).query.get.map(_ ==> List(b))

          // >=
          _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid3)).query.get.map(_ ==> List(b))


          // None comparison matches any asserted values
          _ <- Ns.i.a1.uuids_?.hasLt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGt(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasLe(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.hasGe(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}