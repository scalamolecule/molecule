// GENERATED CODE ********************************
package molecule.coreTests.compliance.crud.update.set.ops

import java.time.Duration
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2)).save.transact.map(_.id)

        _ <- Ns(id).durations(Set(duration3, duration4)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration3, duration4))

        // Apply Seq of values
        _ <- Ns(id).durations(Set(duration4, duration5)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration4, duration5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).durations(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)

        _ <- Ns(id).durations(Set(duration1, duration2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).durations().update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).durations.add(duration2).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Add existing value (no effect)
        _ <- Ns(id).durations.add(duration2).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Add multiple values (vararg)
        _ <- Ns(id).durations.add(duration3, duration4).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).durations.add(Seq(duration4, duration5)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))
        // Set
        _ <- Ns(id).durations.add(Set(duration6)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))
        // Iterable
        _ <- Ns(id).durations.add(Iterable(duration7)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6, duration7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).durations.add(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6, duration7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2, duration3, duration4, duration5, duration6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).durations.swap(duration6 -> duration8).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).durations.swap(duration5 -> duration8).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration8))

        // Replace multiple values (vararg)
        _ <- Ns(id).durations.swap(duration3 -> duration6, duration4 -> duration7).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration6, duration7, duration8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).durations.swap(duration4 -> duration9).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration6, duration7, duration8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).durations.swap(duration4 -> duration9).upsert.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration6, duration7, duration8, duration9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).durations.swap(Seq(duration2 -> duration5)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration5, duration6, duration7, duration8, duration9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).durations.swap(Seq.empty[(Duration, Duration)]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration5, duration6, duration7, duration8, duration9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).durations.swap(duration1 -> duration2, duration1 -> duration3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).durations.swap(duration1 -> duration3, duration2 -> duration3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2, duration3, duration4, duration5, duration6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).durations.remove(duration6).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Removing non-existing value has no effect
        _ <- Ns(id).durations.remove(duration7).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).durations.remove(duration5, duration5).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4))

        // Remove multiple values (vararg)
        _ <- Ns(id).durations.remove(duration3, duration4).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Remove Seq of values
        _ <- Ns(id).durations.remove(Seq(duration2)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).durations.remove(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).durations.remove(Seq(duration1)).update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
