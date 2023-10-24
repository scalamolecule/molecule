// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.Date
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1, date2)).save.transact.map(_.id)

        _ <- Ns(id).dates(Set(date3, date4)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date3, date4))

        // Apply Seq of values
        _ <- Ns(id).dates(Set(date4, date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date4, date5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).dates(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)

        _ <- Ns(id).dates(Set(date1, date2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).dates().update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Add existing value (no effect)
        _ <- Ns(id).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Add multiple values (vararg)
        _ <- Ns(id).dates.add(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).dates.add(Seq(date4, date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))
        // Set
        _ <- Ns(id).dates.add(Set(date6)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))
        // Iterable
        _ <- Ns(id).dates.add(Iterable(date7)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).dates.add(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).dates.swap(date6 -> date8).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).dates.swap(date5 -> date8).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date8))

        // Replace multiple values (vararg)
        _ <- Ns(id).dates.swap(date3 -> date6, date4 -> date7).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date6, date7, date8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).dates.swap(date4 -> date9).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date6, date7, date8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).dates.swap(date4 -> date9).upsert.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date6, date7, date8, date9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).dates.swap(Seq(date2 -> date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date5, date6, date7, date8, date9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).dates.swap(Seq.empty[(Date, Date)]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date5, date6, date7, date8, date9))

        // Can't swap duplicate from/to values
        _ <- Ns("42").dates.swap(date1 -> date2, date1 -> date3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns("42").dates.swap(date1 -> date3, date2 -> date3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).dates.remove(date6).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Removing non-existing value has no effect
        _ <- Ns(id).dates.remove(date7).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).dates.remove(date5, date5).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Remove multiple values (vararg)
        _ <- Ns(id).dates.remove(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Remove Seq of values
        _ <- Ns(id).dates.remove(Seq(date2)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).dates.remove(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).dates.remove(Seq(date1)).update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
