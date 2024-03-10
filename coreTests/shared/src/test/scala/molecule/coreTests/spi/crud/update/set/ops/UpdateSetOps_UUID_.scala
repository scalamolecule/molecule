// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uuids(Set(uuid1, uuid2)).save.transact.map(_.id)
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).uuids(Set(uuid3, uuid4)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid3, uuid4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).uuids(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_ ==> Nil)

        id <- Ns.uuids(Set(uuid1, uuid2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).uuids().update.transact
        _ <- Ns.uuids.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uuids(Set(uuid1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).uuids.add(uuid2).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).uuids.add(uuid2).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Add multiple values (vararg)
        _ <- Ns(id).uuids.add(uuid3, uuid4).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).uuids.add(Seq(uuid4, uuid5)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5))
        // Set
        _ <- Ns(id).uuids.add(Set(uuid6)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6))
        // Iterable
        _ <- Ns(id).uuids.add(Iterable(uuid7)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uuids.add(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uuids(Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).uuids.remove(uuid6).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5))

        // Removing non-existing value has no effect
        _ <- Ns(id).uuids.remove(uuid7).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).uuids.remove(uuid5, uuid5).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4))

        // Remove multiple values (vararg)
        _ <- Ns(id).uuids.remove(uuid3, uuid4).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Remove Iterable of values
        _ <- Ns(id).uuids.remove(Seq(uuid2)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uuids.remove(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).uuids.remove(Seq(uuid1)).update.transact
        _ <- Ns.uuids.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
