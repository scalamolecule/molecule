// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDate
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1, localDate2)).save.transact.map(_.id)

        _ <- Ns(id).localDates(Set(localDate3, localDate4)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate3, localDate4))

        // Apply Seq of values
        _ <- Ns(id).localDates(Set(localDate4, localDate5)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate4, localDate5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).localDates(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDates(Set(localDate1, localDate2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).localDates().update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localDates.add(localDate2).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Add existing value (no effect)
        _ <- Ns(id).localDates.add(localDate2).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Add multiple values (vararg)
        _ <- Ns(id).localDates.add(localDate3, localDate4).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).localDates.add(Seq(localDate4, localDate5)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))
        // Set
        _ <- Ns(id).localDates.add(Set(localDate6)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))
        // Iterable
        _ <- Ns(id).localDates.add(Iterable(localDate7)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).localDates.add(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).localDates.swap(localDate6 -> localDate8).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).localDates.swap(localDate5 -> localDate8).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate8))

        // Replace multiple values (vararg)
        _ <- Ns(id).localDates.swap(localDate3 -> localDate6, localDate4 -> localDate7).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate6, localDate7, localDate8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).localDates.swap(localDate4 -> localDate9).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate6, localDate7, localDate8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).localDates.swap(localDate4 -> localDate9).upsert.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate6, localDate7, localDate8, localDate9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).localDates.swap(Seq(localDate2 -> localDate5)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate5, localDate6, localDate7, localDate8, localDate9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).localDates.swap(Seq.empty[(LocalDate, LocalDate)]).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate5, localDate6, localDate7, localDate8, localDate9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).localDates.swap(localDate1 -> localDate2, localDate1 -> localDate3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).localDates.swap(localDate1 -> localDate3, localDate2 -> localDate3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localDates.remove(localDate6).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDates.remove(localDate7).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localDates.remove(localDate5, localDate5).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDates.remove(localDate3, localDate4).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Remove Seq of values
        _ <- Ns(id).localDates.remove(Seq(localDate2)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDates.remove(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).localDates.remove(Seq(localDate1)).update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
