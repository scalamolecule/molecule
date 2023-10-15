// GENERATED CODE ********************************
package molecule.coreTests.compliance.crud.update.set.ops

import java.time.OffsetTime
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetTimes(Set(offsetTime1, offsetTime2)).save.transact.map(_.id)

        _ <- Ns(id).offsetTimes(Set(offsetTime3, offsetTime4)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime3, offsetTime4))

        // Apply Seq of values
        _ <- Ns(id).offsetTimes(Set(offsetTime4, offsetTime5)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime4, offsetTime5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).offsetTimes(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimes.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetTimes(Set(offsetTime1, offsetTime2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).offsetTimes().update.transact
        _ <- Ns.offsetTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetTimes(Set(offsetTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).offsetTimes.add(offsetTime2).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Add existing value (no effect)
        _ <- Ns(id).offsetTimes.add(offsetTime2).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetTimes.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).offsetTimes.add(Seq(offsetTime4, offsetTime5)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5))
        // Set
        _ <- Ns(id).offsetTimes.add(Set(offsetTime6)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))
        // Iterable
        _ <- Ns(id).offsetTimes.add(Iterable(offsetTime7)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).offsetTimes.add(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.offsetTimes(Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).offsetTimes.swap(offsetTime6 -> offsetTime8).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).offsetTimes.swap(offsetTime5 -> offsetTime8).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime8))

        // Replace multiple values (vararg)
        _ <- Ns(id).offsetTimes.swap(offsetTime3 -> offsetTime6, offsetTime4 -> offsetTime7).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime6, offsetTime7, offsetTime8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).offsetTimes.swap(offsetTime4 -> offsetTime9).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime6, offsetTime7, offsetTime8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).offsetTimes.swap(offsetTime4 -> offsetTime9).upsert.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime6, offsetTime7, offsetTime8, offsetTime9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).offsetTimes.swap(Seq(offsetTime2 -> offsetTime5)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime5, offsetTime6, offsetTime7, offsetTime8, offsetTime9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).offsetTimes.swap(Seq.empty[(OffsetTime, OffsetTime)]).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime5, offsetTime6, offsetTime7, offsetTime8, offsetTime9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).offsetTimes.swap(offsetTime1 -> offsetTime2, offsetTime1 -> offsetTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).offsetTimes.swap(offsetTime1 -> offsetTime3, offsetTime2 -> offsetTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetTimes(Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).offsetTimes.remove(offsetTime6).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetTimes.remove(offsetTime7).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).offsetTimes.remove(offsetTime5, offsetTime5).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetTimes.remove(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Remove Seq of values
        _ <- Ns(id).offsetTimes.remove(Seq(offsetTime2)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetTimes.remove(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).offsetTimes.remove(Seq(offsetTime1)).update.transact
        _ <- Ns.offsetTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
