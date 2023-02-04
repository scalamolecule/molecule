// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import java.util.UUID
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuids.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids(Set(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Set(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids(Seq.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids.not(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids.not(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids.not(Set(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Set(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.not(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.not(Seq(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids.==(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Set(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids.==(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids.==(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.==(Set.empty[UUID], Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.==(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.==(Seq(Set.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids.!=(Set(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.!=(Set(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids.!=(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids.!=(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.!=(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.!=(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.!=(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.!=(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuids.insert(List(a, b)).transact

          _ <- Ns.i.a1.uuids.<(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.<(uuid1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.<(uuid2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.<(uuid3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids.<=(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids.<=(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids.<=(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.<=(uuid3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids.>(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.>(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.>(uuid2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids.>(uuid3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uuids.>=(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.>=(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.>=(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids.>=(uuid3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids_(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids_(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids_(Set(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Set(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_(Seq.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids_.not(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids_.not(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids_.not(Set(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.not(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.not(Seq(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids_.==(Set(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Set(uuid1, uuid2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids_.==(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_.==(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set.empty[UUID]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.==(Set.empty[UUID]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Seq.empty[Set[UUID]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.==(Seq(Set.empty[UUID])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1, uuid2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1), Set(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.!=(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.!=(Set.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.!=(Seq.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.uuids.insert(List(
            (a, Set(uuid1, uuid2)),
            (b, Set(uuid2, uuid3, uuid4))
          )).transact

          _ <- Ns.i.a1.uuids_.<(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.<(uuid1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.<(uuid2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.<(uuid3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids_.<=(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_.<=(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_.<=(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.<=(uuid3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids_.>(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.>(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.>(uuid2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_.>(uuid3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uuids_.>=(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.>=(uuid1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.>=(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_.>=(uuid3).query.get.map(_ ==> List(b))
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


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.uuids_?(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(uuid3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid2, uuid3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?(Some(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_?(Some(Seq.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Set.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?(Option.empty[UUID]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.uuids_?.not(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(uuid5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid2, uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(Set(uuid2, uuid3, uuid4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuids_?.not(Some(Seq.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Some(Seq(Set.empty[UUID]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.uuids_?.not(Option.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Option.empty[Set[UUID]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.not(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.uuids_?.==(Some(Set(uuid1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.==(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.uuids_?.==(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuids_?.==(Some(Set.empty[UUID])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.==(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.==(Some(Seq(Set.empty[UUID]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?.==(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?.==(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?.!=(Some(Set(uuid1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Set(uuid1, uuid2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.uuids_?.!=(Some(Set(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set.empty[UUID]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Set.empty[UUID])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.!=(Some(Seq.empty[Set[UUID]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.uuids_?.==(Option.empty[Set[UUID]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuids_?.==(Option.empty[Seq[Set[UUID]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuids_?.insert(a, b, c).transact

          _ <- Ns.i.a1.uuids_?.<(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.<(Some(uuid1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.<(Some(uuid2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.<(Some(uuid3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids_?.<=(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuids_?.<=(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuids_?.<=(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.<=(Some(uuid3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.uuids_?.>(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>(Some(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>(Some(uuid2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuids_?.>(Some(uuid3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.uuids_?.>=(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>=(Some(uuid1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>=(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>=(Some(uuid3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.uuids_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuids_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}