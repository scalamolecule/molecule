// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.UUID
import molecule.base.error._
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

        _ <- Ns(id).uuids(Set(uuid3, uuid4)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid3, uuid4))

        // Apply Seq of values
        _ <- Ns(id).uuids(Set(uuid4, uuid5)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid4, uuid5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).uuids(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_ ==> Nil)

        _ <- Ns(id).uuids(Set(uuid1, uuid2)).update.transact

        // Delete all (apply no values)
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

        // Add existing value (no effect)
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

        // Add empty Seq of values (no effect)
        _ <- Ns(id).uuids.add(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.uuids(Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).uuids.swap(uuid6 -> uuid8).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).uuids.swap(uuid5 -> uuid8).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid8))

        // Replace multiple values (vararg)
        _ <- Ns(id).uuids.swap(uuid3 -> uuid6, uuid4 -> uuid7).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid6, uuid7, uuid8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).uuids.swap(uuid4 -> uuid9).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid6, uuid7, uuid8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).uuids.swap(uuid4 -> uuid9).upsert.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2, uuid6, uuid7, uuid8, uuid9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).uuids.swap(Seq(uuid2 -> uuid5)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid5, uuid6, uuid7, uuid8, uuid9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).uuids.swap(Seq.empty[(UUID, UUID)]).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid5, uuid6, uuid7, uuid8, uuid9))

        // Can't swap duplicate from/to values
        _ <- Ns("42").uuids.swap(uuid1 -> uuid2, uuid1 -> uuid3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns("42").uuids.swap(uuid1 -> uuid3, uuid2 -> uuid3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
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

        // Remove Seq of values
        _ <- Ns(id).uuids.remove(Seq(uuid2)).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uuids.remove(Seq.empty[UUID]).update.transact
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).uuids.remove(Seq(uuid1)).update.transact
        _ <- Ns.uuids.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
