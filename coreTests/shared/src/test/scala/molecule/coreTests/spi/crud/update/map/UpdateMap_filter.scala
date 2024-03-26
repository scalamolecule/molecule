package molecule.coreTests.spi.crud.update.map

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMap_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.intMap_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).intMap(Map(pint2)).update.transact
        // (we don't need the `multiple` identifier here since only one entity matches)
        _ <- Ns.i.intMap_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).intMap(Map(pint2)).upsert.transact
        _ <- Ns.i.intMap_?.query.get.map(_ ==> List((1, Some(Map(pint2)))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).intMap(Map(pint3)).upsert.transact
        _ <- Ns.i.intMap.query.get.map(_ ==> List((1, Map(pint3))))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).intMap(Map(pint4)).upsert.transact
        _ <- Ns.i.intMap.query.get.map(_ ==> List((1, Map(pint4))))
      } yield ()
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intMap_?.insert(
          (1, None),
          (1, Some(Map(pint2))),
          (2, Some(Map(pint3))),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intMap(Map(pint4)).update.transact

        // Only matching entities with previous values updated
        _ <- Ns.id.a1.i.intMap_?.query.get.map(_ ==> List(
          (a, 1, None), // not updated since there were no previous value
          (b, 1, Some(Map(pint4))), // 2 updated to 4
          (c, 2, Some(Map(pint3))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intMap(Map(pint5)).upsert.transact

        // All matching entities updated
        _ <- Ns.id.a1.i.intMap_?.query.get.map(_ ==> List(
          (a, 1, Some(Map(pint5))), // 5 inserted
          (b, 1, Some(Map(pint5))), // 4 updated to 5
          (c, 2, Some(Map(pint3))),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        _ <- Ns.intMap_(Map(pint1)).intMap(Map(pint1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              s"""AttrMapTacInt("Ns", "intMap", Eq, Map($pint1), None, None, Nil, Nil, None, None, Seq(0, 76))"""
          }
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.intMap.insert(
          (1, Map(pint1)),
          (2, Map(pint2)),
          (3, Map(pint3)),
        ).transact.map(_.ids)

        _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(
          (1, Map(pint1)),
          (2, Map(pint2)),
          (3, Map(pint3)),
        ))

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).intMap(Map(pint4)).update.transact

        _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(
          (1, Map(pint4)), // updated
          (2, Map(pint4)), // updated
          (3, Map(pint3)),
        ))
      } yield ()
    }
  }
}
