// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localTimes(Set(localTime1, localTime2)).save.transact.map(_.id)

        _ <- Ns(id).localTimes(Set(localTime3, localTime4)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime3, localTime4))

        // Apply Seq of values
        _ <- Ns(id).localTimes(Set(localTime4, localTime5)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime4, localTime5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).localTimes(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimes.query.get.map(_ ==> Nil)

        _ <- Ns(id).localTimes(Set(localTime1, localTime2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).localTimes().update.transact
        _ <- Ns.localTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localTimes(Set(localTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localTimes.add(localTime2).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Add existing value (no effect)
        _ <- Ns(id).localTimes.add(localTime2).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).localTimes.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).localTimes.add(Seq(localTime4, localTime5)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5))
        // Set
        _ <- Ns(id).localTimes.add(Set(localTime6)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))
        // Iterable
        _ <- Ns(id).localTimes.add(Iterable(localTime7)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).localTimes.add(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localTimes(Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localTimes.remove(localTime6).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5))

        // Removing non-existing value has no effect
        _ <- Ns(id).localTimes.remove(localTime7).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localTimes.remove(localTime5, localTime5).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4))

        // Remove multiple values (vararg)
        _ <- Ns(id).localTimes.remove(localTime3, localTime4).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Remove Seq of values
        _ <- Ns(id).localTimes.remove(Seq(localTime2)).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localTimes.remove(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).localTimes.remove(Seq(localTime1)).update.transact
        _ <- Ns.localTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
