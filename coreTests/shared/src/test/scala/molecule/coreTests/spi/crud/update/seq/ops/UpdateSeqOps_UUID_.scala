// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uuidSeq(List(uuid1, uuid2, uuid2)).save.transact.map(_.id)
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).uuidSeq(List(uuid3, uuid4, uuid4)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid3, uuid4, uuid4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).uuidSeq(List.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        id <- Ns.uuidSeq(List(uuid1, uuid2, uuid2)).save.transact.map(_.id)
        // Applying empty value deletes previous Seq
        _ <- Ns(id).uuidSeq().update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uuidSeq(List(uuid1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).uuidSeq.add(uuid2).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))

        // Add existing value
        _ <- Ns(id).uuidSeq.add(uuid1).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid1))

        // Add multiple values (vararg)
        _ <- Ns(id).uuidSeq.add(uuid3, uuid4).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid1, uuid3, uuid4))

        // Add multiple values (Seq)
        _ <- Ns(id).uuidSeq.add(List(uuid4, uuid5)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).uuidSeq.add(List.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uuidSeq(List(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).uuidSeq.remove(uuid7).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).uuidSeq.remove(uuid9).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).uuidSeq.remove(uuid6, uuid6).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5,
          uuid1, uuid2, uuid3, uuid4, uuid5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).uuidSeq.remove(uuid4, uuid5).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3,
          uuid1, uuid2, uuid3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).uuidSeq.remove(List(uuid2, uuid3)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1,
          uuid1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uuidSeq.remove(List.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).uuidSeq.remove(Seq(uuid1)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
