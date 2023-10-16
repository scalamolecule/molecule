// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.ZonedDateTime
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).save.transact.map(_.id)

        _ <- Ns(id).zonedDateTimes(Set(zonedDateTime3, zonedDateTime4)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime3, zonedDateTime4))

        // Apply Seq of values
        _ <- Ns(id).zonedDateTimes(Set(zonedDateTime4, zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime4, zonedDateTime5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).zonedDateTimes(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)

        _ <- Ns(id).zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).zonedDateTimes().update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Add existing value (no effect)
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).zonedDateTimes.add(Seq(zonedDateTime4, zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5))
        // Set
        _ <- Ns(id).zonedDateTimes.add(Set(zonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))
        // Iterable
        _ <- Ns(id).zonedDateTimes.add(Iterable(zonedDateTime7)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).zonedDateTimes.add(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).zonedDateTimes.swap(zonedDateTime6 -> zonedDateTime8).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).zonedDateTimes.swap(zonedDateTime5 -> zonedDateTime8).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime8))

        // Replace multiple values (vararg)
        _ <- Ns(id).zonedDateTimes.swap(zonedDateTime3 -> zonedDateTime6, zonedDateTime4 -> zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime6, zonedDateTime7, zonedDateTime8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).zonedDateTimes.swap(zonedDateTime4 -> zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime6, zonedDateTime7, zonedDateTime8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).zonedDateTimes.swap(zonedDateTime4 -> zonedDateTime9).upsert.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime6, zonedDateTime7, zonedDateTime8, zonedDateTime9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).zonedDateTimes.swap(Seq(zonedDateTime2 -> zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime5, zonedDateTime6, zonedDateTime7, zonedDateTime8, zonedDateTime9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).zonedDateTimes.swap(Seq.empty[(ZonedDateTime, ZonedDateTime)]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime5, zonedDateTime6, zonedDateTime7, zonedDateTime8, zonedDateTime9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).zonedDateTimes.swap(zonedDateTime1 -> zonedDateTime2, zonedDateTime1 -> zonedDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).zonedDateTimes.swap(zonedDateTime1 -> zonedDateTime3, zonedDateTime2 -> zonedDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime5, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4))

        // Remove multiple values (vararg)
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Remove Seq of values
        _ <- Ns(id).zonedDateTimes.remove(Seq(zonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).zonedDateTimes.remove(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).zonedDateTimes.remove(Seq(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
