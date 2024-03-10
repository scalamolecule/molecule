// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Duration_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.durationArray(Array(duration1, duration2, duration2)).save.transact.map(_.id)
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).durationArray(Array(duration3, duration4, duration4)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration3, duration4, duration4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).durationArray(Seq.empty[Duration]).update.transact
        _ <- Ns.durationArray.query.get.map(_ ==> Nil)

        id <- Ns.durationArray(Array(duration1, duration2, duration2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).durationArray().update.transact
        _ <- Ns.durationArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.durationArray(Array(duration1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).durationArray.add(duration2).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2))

        // Add existing value
        _ <- Ns(id).durationArray.add(duration1).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1))

        // Add multiple values (vararg)
        _ <- Ns(id).durationArray.add(duration3, duration4).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1, duration3, duration4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).durationArray.add(Seq(duration4, duration5)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1, duration3, duration4, duration4, duration5))
        // Array
        _ <- Ns(id).durationArray.add(Array(duration6)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6))
        // Iterable
        _ <- Ns(id).durationArray.add(Iterable(duration7)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6, duration7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).durationArray.add(Seq.empty[Duration]).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6, duration7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durationArray(Array(
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).durationArray.remove(duration7).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1, duration2, duration3, duration4, duration5, duration6,
          duration1, duration2, duration3, duration4, duration5, duration6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).durationArray.remove(duration9).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1, duration2, duration3, duration4, duration5, duration6,
          duration1, duration2, duration3, duration4, duration5, duration6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).durationArray.remove(duration6, duration6).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1, duration2, duration3, duration4, duration5,
          duration1, duration2, duration3, duration4, duration5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).durationArray.remove(duration4, duration5).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1, duration2, duration3,
          duration1, duration2, duration3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).durationArray.remove(Array(duration3)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1, duration2,
          duration1, duration2,
        ))

        _ <- Ns(id).durationArray.remove(Seq(duration2)).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(
          duration1,
          duration1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).durationArray.remove(Seq.empty[Duration]).update.transact
        _ <- Ns.durationArray.query.get.map(_.head ==> Array(duration1, duration1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).durationArray.remove(Seq(duration1)).update.transact
        _ <- Ns.durationArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
