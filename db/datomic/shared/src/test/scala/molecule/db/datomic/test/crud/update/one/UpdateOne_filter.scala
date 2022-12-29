package molecule.db.datomic.test.crud.update.one

import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_filter extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).int(2).update.transact
        // (we don't need the `multiple` identifier here since only one entity matches)
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).int(2).upsert.transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, Some(2))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).int(3).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 3)))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).int(4).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 4)))
      } yield ()
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.int_?.insert(
          (1, None),
          (1, Some(2)),
          (2, Some(3)),
        ).transact.map(_.eids)

        _ <- Ns.i.int_?.query.get.map(_ ==> List(
          (1, None),
          (1, Some(2)),
          (2, Some(3)),
        ))

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).int(4).update.transact
        _ <- Ns.e.a1.i.int_?.query.get.map(_ ==> List(
          (a, 1, None), // not updated since there were no previous value
          (b, 1, Some(4)), // 2 updated to 4
          (c, 2, Some(3)),
        ))

        // Upsert all entities where non-unique attribute i is 1
        // `multiple` modifier needed since multiple entities match
        _ <- Ns.i_(1).int(5).upsert.multiple.transact
        _ <- Ns.e.a1.i.int_?.query.get.map(_ ==> List(
          (a, 1, Some(5)), // 5 inserted
          (b, 1, Some(5)), // 4 updated to 5
          (c, 2, Some(3)),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.query.get.map(_ ==> List(1))

        // Filter by tacit i_(1), update to new mandatory i(2)
        _ <- Ns.i_(1).i(2).update.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact.map(_.eids)

        _ <- Ns.i.a1.int.query.get.map(_ ==> List(
          (1, 1),
          (2, 2),
          (3, 3),
        ))

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).int(4).update.multiple.transact

        _ <- Ns.i.a1.int.query.get.map(_ ==> List(
          (1, 4), // updated
          (2, 4), // updated
          (3, 3),
        ))
      } yield ()
    }


    "Semantics" - {

      "Multiple values" - types { implicit conn =>
        for {
          _ <- Ns.i_(1).int(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"
          }

          _ <- Ns.i_(1).int(1, 2).upsert.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can only upsert one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"
          }
        } yield ()
      }

      "Guard against multiple updates" - types { implicit conn =>
        for {
          _ <- Ns.i.int.insert(
            (1, 1),
            (1, 2),
            (2, 3),
          ).transact

          _ <- Ns.i_(1).int(4).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Please provide explicit `update.multiple` to update " +
              "multiple entities (found 2 matching entities)."
          }

          _ <- Ns.i_(1).int(4).upsert.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Please provide explicit `upsert.multiple` to upsert " +
              "multiple entities (found 2 matching entities)."
          }

          // Ok to update 1 entity without `multiple` modifier
          _ <- Ns.i_(2).int(4).update.transact
          _ <- Ns.i_(2).int(4).upsert.transact

          // Ok to update multiple entities when `multiple` modifier is added
          _ <- Ns.i_(1).int(4).update.multiple.transact
          _ <- Ns.i_(1).int(4).upsert.multiple.transact
        } yield ()
      }
    }
  }
}
