// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_OffsetTime_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeArray(Array(offsetTime1, offsetTime2, offsetTime2)).save.transact.map(_.id)
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).offsetTimeArray(Array(offsetTime3, offsetTime4, offsetTime4)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime3, offsetTime4, offsetTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).offsetTimeArray(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_ ==> Nil)

        id <- Ns.offsetTimeArray(Array(offsetTime1, offsetTime2, offsetTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).offsetTimeArray().update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeArray(Array(offsetTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).offsetTimeArray.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2))

        // Add existing value
        _ <- Ns(id).offsetTimeArray.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetTimeArray.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).offsetTimeArray.add(Seq(offsetTime4, offsetTime5)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5))
        // Array
        _ <- Ns(id).offsetTimeArray.add(Array(offsetTime6)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5, offsetTime6))
        // Iterable
        _ <- Ns(id).offsetTimeArray.add(Iterable(offsetTime7)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5, offsetTime6, offsetTime7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetTimeArray.add(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5, offsetTime6, offsetTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeArray(Array(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).offsetTimeArray.remove(offsetTime7).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetTimeArray.remove(offsetTime9).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).offsetTimeArray.remove(offsetTime6, offsetTime6).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetTimeArray.remove(offsetTime4, offsetTime5).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1, offsetTime2, offsetTime3,
          offsetTime1, offsetTime2, offsetTime3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).offsetTimeArray.remove(Array(offsetTime3)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1, offsetTime2,
          offsetTime1, offsetTime2,
        ))

        _ <- Ns(id).offsetTimeArray.remove(Seq(offsetTime2)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(
          offsetTime1,
          offsetTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetTimeArray.remove(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_.head ==> Array(offsetTime1, offsetTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).offsetTimeArray.remove(Seq(offsetTime1)).update.transact
        _ <- Ns.offsetTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
