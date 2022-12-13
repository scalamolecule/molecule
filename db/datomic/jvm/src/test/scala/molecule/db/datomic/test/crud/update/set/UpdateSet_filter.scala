package molecule.db.datomic.test.crud.update.set

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSet_filter extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      Ns.i.insert(1).transact
      Ns.i.ints_?.query.get ==> List((1, None))

      // Update entities with non-unique `i` attribute with value 1
      // Updating a non-asserted value doesn't insert it (nothing is updated)
      Ns.i_(1).ints(Set(2)).update.transact
      // (we don't need the `multiple` identifier here since only one entity matches)
      Ns.i.ints_?.query.get ==> List((1, None))

      // Upserting a non-asserted value inserts it
      Ns.i_(1).ints(Set(2)).upsert.transact
      Ns.i.ints_?.query.get ==> List((1, Some(Set(2))))

      // Updating an asserted value updates it
      Ns.i_(1).ints(Set(3)).upsert.transact
      Ns.i.ints.query.get ==> List((1, Set(3)))

      // Upserting an asserted value updates it
      Ns.i_(1).ints(Set(4)).upsert.transact
      Ns.i.ints.query.get ==> List((1, Set(4)))
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      val List(a, b, c) = Ns.i.ints_?.insert(
        (1, None),
        (1, Some(Set(2))),
        (2, Some(Set(3))),
      ).transact.eids

      Ns.i.ints_?.query.get ==> List(
        (1, None),
        (1, Some(Set(2))),
        (2, Some(Set(3))),
      )

      // Update all entities where non-unique attribute i is 1
      Ns.i_(1).ints(Set(4)).update.transact
      Ns.e.a1.i.ints_?.query.get ==> List(
        (a, 1, None), // not updated since there were no previous value
        (b, 1, Some(Set(4))), // 2 updated to 4
        (c, 2, Some(Set(3))),
      )

      // Upsert all entities where non-unique attribute i is 1
      // `multiple` modifier needed since multiple entities match
      Ns.i_(1).ints(Set(5)).upsert.multiple.transact
      Ns.e.a1.i.ints_?.query.get ==> List(
        (a, 1, Some(Set(5))), // 5 inserted
        (b, 1, Some(Set(5))), // 4 updated to 5
        (c, 2, Some(Set(3))),
      )
    }


    "Update filter value itself" - types { implicit conn =>
      intercept[MoleculeException](
        Ns.ints_(42).ints(1).update.transact
      ).message ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        "AttrSetTacInt(Ns,ints,Appl,List(Set(42)),None,None,None)"
    }


    "Expression" - types { implicit conn =>
      Ns.i.ints.insert(
        (1, Set(1)),
        (2, Set(2)),
        (3, Set(3)),
      ).transact.eids

      Ns.i.a1.ints.query.get ==> List(
        (1, Set(1)),
        (2, Set(2)),
        (3, Set(3)),
      )

      // Update all entities where non-unique attribute i <= 2
      Ns.i_.<=(2).ints(Set(4)).update.multiple.transact

      Ns.i.a1.ints.query.get ==> List(
        (1, Set(4)), // updated
        (2, Set(4)), // updated
        (3, Set(3)),
      )
    }


    "Semantics" - {

      "Guard against multiple updates" - types { implicit conn =>
        Ns.i.ints.insert(
          (1, Set(1)),
          (1, Set(2)),
          (2, Set(3)),
        ).transact

        intercept[MoleculeException](
          Ns.i_(1).ints(4).update.transact
        ).message ==> "Please provide explicit `update.multiple` to update " +
          "multiple entities (found 2 matching entities)."

        intercept[MoleculeException](
          Ns.i_(1).ints(Set(4)).upsert.transact
        ).message ==> "Please provide explicit `upsert.multiple` to upsert " +
          "multiple entities (found 2 matching entities)."

        // Ok to update 1 entity without `multiple` modifier
        Ns.i_(2).ints(Set(4)).update.transact
        Ns.i_(2).ints(Set(4)).upsert.transact

        // Ok to update multiple entities when `multiple` modifier is added
        Ns.i_(1).ints(Set(4)).update.multiple.transact
        Ns.i_(1).ints(Set(4)).upsert.multiple.transact
      }
    }
  }
}
