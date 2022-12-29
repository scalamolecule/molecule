package molecule.db.datomic.test.crud.update.set

import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSet_filter extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.ints_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).ints(Set(2)).update.transact
        // (we don't need the `multiple` identifier here since only one entity matches)
        _ <- Ns.i.ints_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).ints(Set(2)).upsert.transact
        _ <- Ns.i.ints_?.query.get.map(_ ==> List((1, Some(Set(2)))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).ints(Set(3)).upsert.transact
        _ <- Ns.i.ints.query.get.map(_ ==> List((1, Set(3))))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).ints(Set(4)).upsert.transact
        _ <- Ns.i.ints.query.get.map(_ ==> List((1, Set(4))))
      } yield ()
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.ints_?.insert(
          (1, None),
          (1, Some(Set(2))),
          (2, Some(Set(3))),
        ).transact.map(_.eids)

        _ <- Ns.i.ints_?.query.get.map(_ ==> List(
          (1, None),
          (1, Some(Set(2))),
          (2, Some(Set(3))),
        ))

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).ints(Set(4)).update.transact
        _ <- Ns.e.a1.i.ints_?.query.get.map(_ ==> List(
          (a, 1, None), // not updated since there were no previous value
          (b, 1, Some(Set(4))), // 2 updated to 4
          (c, 2, Some(Set(3))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        // `multiple` modifier needed since multiple entities match
        _ <- Ns.i_(1).ints(Set(5)).upsert.multiple.transact
        _ <- Ns.e.a1.i.ints_?.query.get.map(_ ==> List(
          (a, 1, Some(Set(5))), // 5 inserted
          (b, 1, Some(Set(5))), // 4 updated to 5
          (c, 2, Some(Set(3))),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        _ <- Ns.ints_(42).ints(1).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            "AttrSetTacInt(Ns,ints,Appl,List(Set(42)),None,None,None)"
        }
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.ints.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact.map(_.eids)

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ))

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).ints(Set(4)).update.multiple.transact

        _ <- Ns.i.a1.ints.query.get.map(_ ==> List(
          (1, Set(4)), // updated
          (2, Set(4)), // updated
          (3, Set(3)),
        ))
      } yield ()
    }


    "Semantics" - {

      "Guard against multiple updates" - types { implicit conn =>
        for {
          _ <- Ns.i.ints.insert(
            (1, Set(1)),
            (1, Set(2)),
            (2, Set(3)),
          ).transact

          _ <- Ns.i_(1).ints(4).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Please provide explicit `update.multiple` to update " +
              "multiple entities (found 2 matching entities)."
          }

          _ <- Ns.i_(1).ints(Set(4)).upsert.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Please provide explicit `upsert.multiple` to upsert " +
              "multiple entities (found 2 matching entities)."
          }

          // Ok to update 1 entity without `multiple` modifier
          _ <- Ns.i_(2).ints(Set(4)).update.transact
          _ <- Ns.i_(2).ints(Set(4)).upsert.transact

          // Ok to update multiple entities when `multiple` modifier is added
          _ <- Ns.i_(1).ints(Set(4)).update.multiple.transact
          _ <- Ns.i_(1).ints(Set(4)).upsert.multiple.transact
        } yield ()
      }
    }
  }
}
