// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_LocalDateTime_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeArray(Array(localDateTime1, localDateTime2, localDateTime2)).save.transact.map(_.id)
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).localDateTimeArray(Array(localDateTime3, localDateTime4, localDateTime4)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime3, localDateTime4, localDateTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).localDateTimeArray(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_ ==> Nil)

        id <- Ns.localDateTimeArray(Array(localDateTime1, localDateTime2, localDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).localDateTimeArray().update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeArray(Array(localDateTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).localDateTimeArray.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2))

        // Add existing value
        _ <- Ns(id).localDateTimeArray.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateTimeArray.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).localDateTimeArray.add(Seq(localDateTime4, localDateTime5)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5))
        // Array
        _ <- Ns(id).localDateTimeArray.add(Array(localDateTime6)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5, localDateTime6))
        // Iterable
        _ <- Ns(id).localDateTimeArray.add(Iterable(localDateTime7)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5, localDateTime6, localDateTime7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateTimeArray.add(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5, localDateTime6, localDateTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeArray(Array(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).localDateTimeArray.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimeArray.remove(localDateTime9).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateTimeArray.remove(localDateTime6, localDateTime6).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateTimeArray.remove(localDateTime4, localDateTime5).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1, localDateTime2, localDateTime3,
          localDateTime1, localDateTime2, localDateTime3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).localDateTimeArray.remove(Array(localDateTime3)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1, localDateTime2,
          localDateTime1, localDateTime2,
        ))

        _ <- Ns(id).localDateTimeArray.remove(Seq(localDateTime2)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(
          localDateTime1,
          localDateTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateTimeArray.remove(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_.head ==> Array(localDateTime1, localDateTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).localDateTimeArray.remove(Seq(localDateTime1)).update.transact
        _ <- Ns.localDateTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
