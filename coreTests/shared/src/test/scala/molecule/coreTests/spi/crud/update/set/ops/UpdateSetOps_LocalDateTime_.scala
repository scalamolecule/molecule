// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDateTime
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateTimes(Set(localDateTime1, localDateTime2)).save.transact.map(_.id)

        _ <- Ns(id).localDateTimes(Set(localDateTime3, localDateTime4)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime3, localDateTime4))

        // Apply Seq of values
        _ <- Ns(id).localDateTimes(Set(localDateTime4, localDateTime5)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime4, localDateTime5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).localDateTimes(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimes.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateTimes(Set(localDateTime1, localDateTime2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).localDateTimes().update.transact
        _ <- Ns.localDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateTimes(Set(localDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localDateTimes.add(localDateTime2).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Add existing value (no effect)
        _ <- Ns(id).localDateTimes.add(localDateTime2).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateTimes.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).localDateTimes.add(Seq(localDateTime4, localDateTime5)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5))
        // Set
        _ <- Ns(id).localDateTimes.add(Set(localDateTime6)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))
        // Iterable
        _ <- Ns(id).localDateTimes.add(Iterable(localDateTime7)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).localDateTimes.add(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.localDateTimes(Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).localDateTimes.swap(localDateTime6 -> localDateTime8).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).localDateTimes.swap(localDateTime5 -> localDateTime8).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime8))

        // Replace multiple values (vararg)
        _ <- Ns(id).localDateTimes.swap(localDateTime3 -> localDateTime6, localDateTime4 -> localDateTime7).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime6, localDateTime7, localDateTime8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).localDateTimes.swap(localDateTime4 -> localDateTime9).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime6, localDateTime7, localDateTime8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).localDateTimes.swap(localDateTime4 -> localDateTime9).upsert.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime6, localDateTime7, localDateTime8, localDateTime9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).localDateTimes.swap(Seq(localDateTime2 -> localDateTime5)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime5, localDateTime6, localDateTime7, localDateTime8, localDateTime9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).localDateTimes.swap(Seq.empty[(LocalDateTime, LocalDateTime)]).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime5, localDateTime6, localDateTime7, localDateTime8, localDateTime9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).localDateTimes.swap(localDateTime1 -> localDateTime2, localDateTime1 -> localDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).localDateTimes.swap(localDateTime1 -> localDateTime3, localDateTime2 -> localDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateTimes(Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localDateTimes.remove(localDateTime6).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimes.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localDateTimes.remove(localDateTime5, localDateTime5).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateTimes.remove(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Remove Seq of values
        _ <- Ns(id).localDateTimes.remove(Seq(localDateTime2)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateTimes.remove(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).localDateTimes.remove(Seq(localDateTime1)).update.transact
        _ <- Ns.localDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
