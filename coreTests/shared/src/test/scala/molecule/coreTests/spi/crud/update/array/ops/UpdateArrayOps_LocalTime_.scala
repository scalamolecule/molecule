// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_LocalTime_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localTimeArray(Array(localTime1, localTime2, localTime2)).save.transact.map(_.id)
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).localTimeArray(Array(localTime3, localTime4, localTime4)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime3, localTime4, localTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).localTimeArray(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimeArray.query.get.map(_ ==> Nil)

        id <- Ns.localTimeArray(Array(localTime1, localTime2, localTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).localTimeArray().update.transact
        _ <- Ns.localTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localTimeArray(Array(localTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).localTimeArray.add(localTime2).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2))

        // Add existing value
        _ <- Ns(id).localTimeArray.add(localTime1).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).localTimeArray.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1, localTime3, localTime4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).localTimeArray.add(Seq(localTime4, localTime5)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5))
        // Array
        _ <- Ns(id).localTimeArray.add(Array(localTime6)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5, localTime6))
        // Iterable
        _ <- Ns(id).localTimeArray.add(Iterable(localTime7)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5, localTime6, localTime7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localTimeArray.add(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5, localTime6, localTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localTimeArray(Array(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).localTimeArray.remove(localTime7).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localTimeArray.remove(localTime9).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localTimeArray.remove(localTime6, localTime6).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1, localTime2, localTime3, localTime4, localTime5,
          localTime1, localTime2, localTime3, localTime4, localTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).localTimeArray.remove(localTime4, localTime5).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1, localTime2, localTime3,
          localTime1, localTime2, localTime3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).localTimeArray.remove(Array(localTime3)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1, localTime2,
          localTime1, localTime2,
        ))

        _ <- Ns(id).localTimeArray.remove(Seq(localTime2)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(
          localTime1,
          localTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localTimeArray.remove(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimeArray.query.get.map(_.head ==> Array(localTime1, localTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).localTimeArray.remove(Seq(localTime1)).update.transact
        _ <- Ns.localTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
