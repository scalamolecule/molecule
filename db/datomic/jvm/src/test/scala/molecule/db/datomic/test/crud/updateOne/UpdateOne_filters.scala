package molecule.db.datomic.test.crud.updateOne

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_filters extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      Ns.i.insert(1).transact
      Ns.i.int_?.query.get ==> List((1, None))

      // Update entities with non-unique `i` attribute with value 1
      // Updating a non-asserted value doesn't insert it (nothing is updated)
      Ns.i_(1).int(2).update.transact
      // (we don't need the `multiple` identifier here since only one entity matches)
      Ns.i.int_?.query.get ==> List((1, None))

      // Upserting a non-asserted value inserts it
      Ns.i_(1).int(2).upsert.transact
      Ns.i.int_?.query.get ==> List((1, Some(2)))

      // Updating an asserted value updates it
      Ns.i_(1).int(3).upsert.transact
      Ns.i.int.query.get ==> List((1, 3))

      // Upserting an asserted value updates it
      Ns.i_(1).int(4).upsert.transact
      Ns.i.int.query.get ==> List((1, 4))
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      val List(a, b, c) = Ns.i.int_?.insert(
        (1, None),
        (1, Some(2)),
        (2, Some(3)),
      ).transact.eids

      Ns.i.int_?.query.get ==> List(
        (1, None),
        (1, Some(2)),
        (2, Some(3)),
      )

      // Update all entities where non-unique attribute i is 1
      Ns.i_(1).int(4).update.transact
      Ns.e.a1.i.int_?.query.get ==> List(
        (a, 1, None), // not updated since there were no previous value
        (b, 1, Some(4)), // 2 updated to 4
        (c, 2, Some(3)),
      )

      // Upsert all entities where non-unique attribute i is 1
      // `multiple` modifier needed since multiple entities match
      Ns.i_(1).int(5).upsert.multiple.transact
      Ns.e.a1.i.int_?.query.get ==> List(
        (a, 1, Some(5)), // 5 inserted
        (b, 1, Some(5)), // 4 updated to 5
        (c, 2, Some(3)),
      )
    }


    "Update filter value itself" - types { implicit conn =>
      Ns.i.insert(1).transact
      Ns.i.query.get ==> List(1)

      // Filter by tacit i_(1), update to new mandatory i(2)
      Ns.i_(1).i(2).update.transact
      Ns.i.query.get ==> List(2)
    }


    "Expression" - types { implicit conn =>
      Ns.i.int.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact.eids

      Ns.i.a1.int.query.get ==> List(
        (1, 1),
        (2, 2),
        (3, 3),
      )

      // Update all entities where non-unique attribute i <= 2
      Ns.i_.<=(2).int(4).update.multiple.transact

      Ns.i.a1.int.query.get ==> List(
        (1, 4), // updated
        (2, 4), // updated
        (3, 3),
      )
    }


    "Semantics" - {

      "Multiple values" - types { implicit conn =>
        intercept[MoleculeException](
          Ns.i_(1).int(1, 2).update.transact
        ).message ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"

        intercept[MoleculeException](
          Ns.i_(1).int(1, 2).upsert.transact
        ).message ==> "Can only upsert one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"
      }

      "Guard against multiple updates" - types { implicit conn =>
        Ns.i.int.insert(
          (1, 1),
          (1, 2),
          (2, 3),
        ).transact

        intercept[MoleculeException](
          Ns.i_(1).int(4).update.transact
        ).message ==> "Please provide explicit `update.multiple` to update " +
          "multiple entities (found 2 matching entities)."

        intercept[MoleculeException](
          Ns.i_(1).int(4).upsert.transact
        ).message ==> "Please provide explicit `upsert.multiple` to upsert " +
          "multiple entities (found 2 matching entities)."

        // Ok to update 1 entity without `multiple` modifier
        Ns.i_(2).int(4).update.transact
        Ns.i_(2).int(4).upsert.transact

        // Ok to update multiple entities when `multiple` modifier is added
        Ns.i_(1).int(4).update.multiple.transact
        Ns.i_(1).int(4).upsert.multiple.transact
      }

    }
  }
}
