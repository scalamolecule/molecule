package molecule.coreTests.spi.crud.update.seq

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateSeq_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update/upsert, 1 value" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.intSeq_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).intSeq(List(2)).update.transact
        // (we don't need the `multiple` identifier here since only one entity matches)
        _ <- Ns.i.intSeq_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).intSeq(List(2)).upsert.transact
        _ <- Ns.i.intSeq_?.query.get.map(_ ==> List((1, Some(List(2)))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).intSeq(List(3)).upsert.transact
        _ <- Ns.i.intSeq.query.get.map(_ ==> List((1, List(3))))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).intSeq(List(4)).upsert.transact
        _ <- Ns.i.intSeq.query.get.map(_ ==> List((1, List(4))))
      } yield ()
    }


    "Update/upsert, multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intSeq_?.insert(
          (1, None),
          (1, Some(List(2))),
          (2, Some(List(3))),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intSeq(List(4)).update.transact

        // Only matching entities with previous values updated
        _ <- Ns.id.a1.i.intSeq_?.query.get.map(_ ==> List(
          (a, 1, None), // not updated since there were no previous value
          (b, 1, Some(List(4))), // 2 updated to 4
          (c, 2, Some(List(3))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intSeq(List(5)).upsert.transact

        // All matching entities updated
        _ <- Ns.id.a1.i.intSeq_?.query.get.map(_ ==> List(
          (a, 1, Some(List(5))), // 5 inserted
          (b, 1, Some(List(5))), // 4 updated to 5
          (c, 2, Some(List(3))),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        _ <- Ns.intSeq_(Seq(42)).intSeq(Seq(1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              """AttrSeqTacInt("Ns", "intSeq", Eq, Seq(42), None, None, Nil, Nil, None, None, Seq(0, 54))"""
          }
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq.insert(
          (1, List(1)),
          (2, List(2)),
          (3, List(3)),
        ).transact.map(_.ids)

        _ <- Ns.i.a1.intSeq.query.get.map(_ ==> List(
          (1, List(1)),
          (2, List(2)),
          (3, List(3)),
        ))

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).intSeq(List(4)).update.transact

        _ <- Ns.i.a1.intSeq.query.get.map(_ ==> List(
          (1, List(4)), // updated
          (2, List(4)), // updated
          (3, List(3)),
        ))
      } yield ()
    }
  }
}
