// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import java.util.UUID
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
        Ns.i.uuids.insert(List(a, b)).transact

        Ns.i.a1.uuids.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        Ns.i.uuids.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uuids(uuid0).query.get ==> List()
        Ns.i.a1.uuids(uuid1).query.get ==> List(a)
        Ns.i.a1.uuids(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids(uuid3).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids(Seq(uuid0)).query.get ==> List()
        Ns.i.a1.uuids(Seq(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids(Seq(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(uuid3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uuids(uuid1, uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids(uuid1, uuid3).query.get ==> List(a, b)
        Ns.i.a1.uuids(uuid2, uuid3).query.get ==> List(a, b)
        Ns.i.a1.uuids(uuid1, uuid2, uuid3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids(Seq(uuid1, uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(uuid1, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(uuid1, uuid2, uuid3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uuids(Set(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids(Set(uuid1, uuid2)).query.get ==> List(a)
        Ns.i.a1.uuids(Set(uuid1, uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids(Set(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Set(uuid2, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids(Set(uuid2, uuid3, uuid4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids(Seq(Set(uuid1))).query.get ==> List(a)
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2))).query.get ==> List(a)
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids(Seq(Set(uuid2))).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(Set(uuid2, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids(Seq(Set(uuid2, uuid3, uuid4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid0)).query.get ==> List(a)
        Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get ==> List(a)
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(a)
        Ns.i.a1.uuids(Seq.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids(Set.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids(Seq.empty[Set[UUID]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        Ns.i.uuids.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uuids.not(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(uuid1).query.get ==> List(b)
        Ns.i.a1.uuids.not(uuid2).query.get ==> List()
        Ns.i.a1.uuids.not(uuid3).query.get ==> List(a)
        Ns.i.a1.uuids.not(uuid4).query.get ==> List(a)
        Ns.i.a1.uuids.not(uuid5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids.not(Seq(uuid0)).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Seq(uuid1)).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq(uuid2)).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids.not(Seq(uuid4)).query.get ==> List(a)
        Ns.i.a1.uuids.not(Seq(uuid5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uuids.not(uuid1, uuid2).query.get ==> List()
        Ns.i.a1.uuids.not(uuid1, uuid3).query.get ==> List()
        Ns.i.a1.uuids.not(uuid1, uuid4).query.get ==> List()
        Ns.i.a1.uuids.not(uuid1, uuid5).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids.not(Seq(uuid1, uuid2)).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(uuid1, uuid3)).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(uuid1, uuid4)).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(uuid1, uuid5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uuids.not(Set(uuid1)).query.get ==> List(b)
        Ns.i.a1.uuids.not(Set(uuid1, uuid2)).query.get ==> List(b)
        Ns.i.a1.uuids.not(Set(uuid1, uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Set(uuid2)).query.get ==> List()
        Ns.i.a1.uuids.not(Set(uuid2, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids.not(Set(uuid2, uuid3, uuid4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.uuids.not(Seq(Set(uuid1))).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2))).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Seq(Set(uuid2))).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(Set(uuid2, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids.not(Seq(Set(uuid2, uuid3, uuid4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid0)).query.get ==> List(b)
        Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uuids.not(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(b)
        Ns.i.a1.uuids.not(Seq.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Set.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Seq.empty[Set[UUID]]).query.get ==> List(a, b)
        Ns.i.a1.uuids.not(Seq(Set.empty[UUID])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        Ns.i.uuids.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uuids.==(Set(uuid1)).query.get ==> List()
        Ns.i.a1.uuids.==(Set(uuid1, uuid2)).query.get ==> List(a) // include exact match
        Ns.i.a1.uuids.==(Set(uuid1, uuid2, uuid3)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids.==(Seq(Set(uuid1))).query.get ==> List()
        Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2))).query.get ==> List(a)
        Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids.==(Set(uuid1), Set(uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids.==(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids.==(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(a)
        Ns.i.a1.uuids.==(Set.empty[UUID], Set(uuid1, uuid2)).query.get ==> List(a)
        Ns.i.a1.uuids.==(Set.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids.==(Seq.empty[Set[UUID]]).query.get ==> List()
        Ns.i.a1.uuids.==(Seq(Set.empty[UUID])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        Ns.i.uuids.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uuids.!=(Set(uuid1)).query.get ==> List(a, b)
        Ns.i.a1.uuids.!=(Set(uuid1, uuid2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uuids.!=(Set(uuid1, uuid2, uuid3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids.!=(Seq(Set(uuid1))).query.get ==> List(a, b)
        Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2))).query.get ==> List(b)
        Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids.!=(Set(uuid1), Set(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids.!=(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids.!=(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids.!=(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uuids.!=(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get ==> List(b)
        Ns.i.a1.uuids.!=(Set.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids.!=(Seq.empty[Set[UUID]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        Ns.i.uuids.insert(List(a, b)).transact

        Ns.i.a1.uuids.<(uuid0).query.get ==> List()
        Ns.i.a1.uuids.<(uuid1).query.get ==> List()
        Ns.i.a1.uuids.<(uuid2).query.get ==> List(a)
        Ns.i.a1.uuids.<(uuid3).query.get ==> List(a, b)

        Ns.i.a1.uuids.<=(uuid0).query.get ==> List()
        Ns.i.a1.uuids.<=(uuid1).query.get ==> List(a)
        Ns.i.a1.uuids.<=(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids.<=(uuid3).query.get ==> List(a, b)

        Ns.i.a1.uuids.>(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids.>(uuid1).query.get ==> List(a, b)
        Ns.i.a1.uuids.>(uuid2).query.get ==> List(b)
        Ns.i.a1.uuids.>(uuid3).query.get ==> List(b)

        Ns.i.a1.uuids.>=(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids.>=(uuid1).query.get ==> List(a, b)
        Ns.i.a1.uuids.>=(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids.>=(uuid3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        Ns.i.a1.uuids_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uuids_(uuid0).query.get ==> List()
        Ns.i.a1.uuids_(uuid1).query.get ==> List(a)
        Ns.i.a1.uuids_(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids_(uuid3).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids_(Seq(uuid0)).query.get ==> List()
        Ns.i.a1.uuids_(Seq(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(uuid3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uuids_(uuid1, uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids_(uuid1, uuid3).query.get ==> List(a, b)
        Ns.i.a1.uuids_(uuid2, uuid3).query.get ==> List(a, b)
        Ns.i.a1.uuids_(uuid1, uuid2, uuid3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_(Seq(uuid1, uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(uuid1, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(uuid1, uuid2, uuid3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uuids_(Set(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids_(Set(uuid1, uuid2)).query.get ==> List(a)
        Ns.i.a1.uuids_(Set(uuid1, uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids_(Set(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Set(uuid2, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids_(Set(uuid2, uuid3, uuid4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids_(Seq(Set(uuid1))).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2))).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids_(Seq(Set(uuid2))).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(Set(uuid2, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids_(Seq(Set(uuid2, uuid3, uuid4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid0)).query.get ==> List(a)
        Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids_(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(a)
        Ns.i.a1.uuids_(Seq.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids_(Set.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids_(Seq.empty[Set[UUID]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uuids_.not(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(uuid1).query.get ==> List(b)
        Ns.i.a1.uuids_.not(uuid2).query.get ==> List()
        Ns.i.a1.uuids_.not(uuid3).query.get ==> List(a)
        Ns.i.a1.uuids_.not(uuid4).query.get ==> List(a)
        Ns.i.a1.uuids_.not(uuid5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_.not(Seq(uuid0)).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Seq(uuid1)).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq(uuid2)).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids_.not(Seq(uuid4)).query.get ==> List(a)
        Ns.i.a1.uuids_.not(Seq(uuid5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uuids_.not(uuid1, uuid2).query.get ==> List()
        Ns.i.a1.uuids_.not(uuid1, uuid3).query.get ==> List()
        Ns.i.a1.uuids_.not(uuid1, uuid4).query.get ==> List()
        Ns.i.a1.uuids_.not(uuid1, uuid5).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids_.not(Seq(uuid1, uuid2)).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(uuid1, uuid3)).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(uuid1, uuid4)).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(uuid1, uuid5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uuids_.not(Set(uuid1)).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2)).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Set(uuid2)).query.get ==> List()
        Ns.i.a1.uuids_.not(Set(uuid2, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids_.not(Set(uuid2, uuid3, uuid4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.uuids_.not(Seq(Set(uuid1))).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2))).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Seq(Set(uuid2))).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(Set(uuid2, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids_.not(Seq(Set(uuid2, uuid3, uuid4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid0)).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid0, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid0))).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids_.not(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uuids_.not(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(b)
        Ns.i.a1.uuids_.not(Seq.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Set.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Seq.empty[Set[UUID]]).query.get ==> List(a, b)
        Ns.i.a1.uuids_.not(Seq(Set.empty[UUID])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uuids_.==(Set(uuid1)).query.get ==> List()
        Ns.i.a1.uuids_.==(Set(uuid1, uuid2)).query.get ==> List(a) // include exact match
        Ns.i.a1.uuids_.==(Set(uuid1, uuid2, uuid3)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids_.==(Seq(Set(uuid1))).query.get ==> List()
        Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2))).query.get ==> List(a)
        Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids_.==(Set(uuid1), Set(uuid2, uuid3)).query.get ==> List()
        Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_.==(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids_.==(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids_.==(Set(uuid1, uuid2), Set.empty[UUID]).query.get ==> List(a)
        Ns.i.a1.uuids_.==(Set.empty[UUID]).query.get ==> List()
        Ns.i.a1.uuids_.==(Seq.empty[Set[UUID]]).query.get ==> List()
        Ns.i.a1.uuids_.==(Seq(Set.empty[UUID])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uuids_.!=(Set(uuid1)).query.get ==> List(a, b)
        Ns.i.a1.uuids_.!=(Set(uuid1, uuid2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uuids_.!=(Set(uuid1, uuid2, uuid3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1))).query.get ==> List(a, b)
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2))).query.get ==> List(b)
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids_.!=(Set(uuid1), Set(uuid2, uuid3)).query.get ==> List(a, b)
        Ns.i.a1.uuids_.!=(Set(uuid1, uuid2), Set(uuid2, uuid3)).query.get ==> List(b)
        Ns.i.a1.uuids_.!=(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)).query.get ==> List()
        // Same as
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1), Set(uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uuids_.!=(Seq(Set(uuid1, uuid2), Set.empty[UUID])).query.get ==> List(b)
        Ns.i.a1.uuids_.!=(Set.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids_.!=(Seq.empty[Set[UUID]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        Ns.i.a1.uuids_.<(uuid0).query.get ==> List()
        Ns.i.a1.uuids_.<(uuid1).query.get ==> List()
        Ns.i.a1.uuids_.<(uuid2).query.get ==> List(a)
        Ns.i.a1.uuids_.<(uuid3).query.get ==> List(a, b)

        Ns.i.a1.uuids_.<=(uuid0).query.get ==> List()
        Ns.i.a1.uuids_.<=(uuid1).query.get ==> List(a)
        Ns.i.a1.uuids_.<=(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids_.<=(uuid3).query.get ==> List(a, b)

        Ns.i.a1.uuids_.>(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids_.>(uuid1).query.get ==> List(a, b)
        Ns.i.a1.uuids_.>(uuid2).query.get ==> List(b)
        Ns.i.a1.uuids_.>(uuid3).query.get ==> List(b)

        Ns.i.a1.uuids_.>=(uuid0).query.get ==> List(a, b)
        Ns.i.a1.uuids_.>=(uuid1).query.get ==> List(a, b)
        Ns.i.a1.uuids_.>=(uuid2).query.get ==> List(a, b)
        Ns.i.a1.uuids_.>=(uuid3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        Ns.i.a1.uuids_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uuids_?(Some(uuid0)).query.get ==> List()
        Ns.i.a1.uuids_?(Some(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(uuid3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids_?(Some(Seq(uuid0))).query.get ==> List()
        Ns.i.a1.uuids_?(Some(Seq(uuid1))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Seq(uuid2))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(uuid3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid2))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(uuid1, uuid2, uuid3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uuids_?(Some(Set(uuid1))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Set(uuid1, uuid2, uuid3))).query.get ==> List()
        Ns.i.a1.uuids_?(Some(Set(uuid2))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Set(uuid2, uuid3))).query.get ==> List(b)
        Ns.i.a1.uuids_?(Some(Set(uuid2, uuid3, uuid4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1)))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2)))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get ==> List()
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid2)))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid2, uuid3)))).query.get ==> List(b)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get ==> List(a)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids_?(Some(Seq.empty[UUID])).query.get ==> List()
        Ns.i.a1.uuids_?(Some(Set.empty[UUID])).query.get ==> List()
        Ns.i.a1.uuids_?(Some(Seq.empty[Set[UUID]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.uuids_?(Option.empty[UUID]).query.get ==> List(c)
        Ns.i.a1.uuids_?(Option.empty[Seq[UUID]]).query.get ==> List(c)
        Ns.i.a1.uuids_?(Option.empty[Set[UUID]]).query.get ==> List(c)
        Ns.i.a1.uuids_?(Option.empty[Seq[Set[UUID]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uuids_?.not(Some(uuid0)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(uuid1)).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(uuid2)).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(uuid3)).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(uuid4)).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(uuid5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_?.not(Some(Seq(uuid0))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Seq(uuid1))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Seq(uuid2))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(Seq(uuid4))).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(Seq(uuid5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid2))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid3))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid4))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(uuid1, uuid5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uuids_?.not(Some(Set(uuid1))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Set(uuid2))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Set(uuid2, uuid3))).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(Set(uuid2, uuid3, uuid4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2)))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2, uuid3)))).query.get ==> List(a)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid2, uuid3, uuid4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid0)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid0, uuid3)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get ==> List()
        Ns.i.a1.uuids_?.not(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uuids_?.not(Some(Seq.empty[UUID])).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Set.empty[UUID])).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Seq.empty[Set[UUID]])).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Some(Seq(Set.empty[UUID]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.uuids_?.not(Option.empty[UUID]).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Option.empty[Seq[UUID]]).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Option.empty[Set[UUID]]).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.not(Option.empty[Seq[Set[UUID]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uuids_?.==(Some(Set(uuid1))).query.get ==> List()
        Ns.i.a1.uuids_?.==(Some(Set(uuid1, uuid2))).query.get ==> List(a) // include exact match
        Ns.i.a1.uuids_?.==(Some(Set(uuid1, uuid2, uuid3))).query.get ==> List()
        // Same as
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1)))).query.get ==> List()
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2)))).query.get ==> List(a)
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get ==> List()
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get ==> List(a)
        Ns.i.a1.uuids_?.==(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uuids_?.==(Some(Set.empty[UUID])).query.get ==> List()
        Ns.i.a1.uuids_?.==(Some(Seq.empty[Set[UUID]])).query.get ==> List()
        Ns.i.a1.uuids_?.==(Some(Seq(Set.empty[UUID]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.uuids_?.==(Option.empty[Set[UUID]]).query.get ==> List(c)
        Ns.i.a1.uuids_?.==(Option.empty[Seq[Set[UUID]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uuids_?.!=(Some(Set(uuid1))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.!=(Some(Set(uuid1, uuid2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uuids_?.!=(Some(Set(uuid1, uuid2, uuid3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1)))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2, uuid3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1), Set(uuid2, uuid3)))).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3)))).query.get ==> List(b)
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set(uuid2, uuid3, uuid4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uuids_?.!=(Some(Seq(Set(uuid1, uuid2), Set.empty[UUID]))).query.get ==> List(b)
        Ns.i.a1.uuids_?.!=(Some(Set.empty[UUID])).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.!=(Some(Seq.empty[Set[UUID]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.uuids_?.==(Option.empty[Set[UUID]]).query.get ==> List(c)
        Ns.i.a1.uuids_?.==(Option.empty[Seq[Set[UUID]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        Ns.i.uuids_?.insert(a, b, c).transact

        Ns.i.a1.uuids_?.<(Some(uuid0)).query.get ==> List()
        Ns.i.a1.uuids_?.<(Some(uuid1)).query.get ==> List()
        Ns.i.a1.uuids_?.<(Some(uuid2)).query.get ==> List(a)
        Ns.i.a1.uuids_?.<(Some(uuid3)).query.get ==> List(a, b)

        Ns.i.a1.uuids_?.<=(Some(uuid0)).query.get ==> List()
        Ns.i.a1.uuids_?.<=(Some(uuid1)).query.get ==> List(a)
        Ns.i.a1.uuids_?.<=(Some(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.<=(Some(uuid3)).query.get ==> List(a, b)

        Ns.i.a1.uuids_?.>(Some(uuid0)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>(Some(uuid1)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>(Some(uuid2)).query.get ==> List(b)
        Ns.i.a1.uuids_?.>(Some(uuid3)).query.get ==> List(b)

        Ns.i.a1.uuids_?.>=(Some(uuid0)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>=(Some(uuid1)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>=(Some(uuid2)).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>=(Some(uuid3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.uuids_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.uuids_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}