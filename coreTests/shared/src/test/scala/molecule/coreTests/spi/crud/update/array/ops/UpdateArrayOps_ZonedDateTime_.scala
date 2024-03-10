// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_ZonedDateTime_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeArray(Array(zonedDateTime1, zonedDateTime2, zonedDateTime2)).save.transact.map(_.id)
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).zonedDateTimeArray(Array(zonedDateTime3, zonedDateTime4, zonedDateTime4)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime3, zonedDateTime4, zonedDateTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).zonedDateTimeArray(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_ ==> Nil)

        id <- Ns.zonedDateTimeArray(Array(zonedDateTime1, zonedDateTime2, zonedDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).zonedDateTimeArray().update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeArray(Array(zonedDateTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).zonedDateTimeArray.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2))

        // Add existing value
        _ <- Ns(id).zonedDateTimeArray.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).zonedDateTimeArray.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).zonedDateTimeArray.add(Seq(zonedDateTime4, zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5))
        // Array
        _ <- Ns(id).zonedDateTimeArray.add(Array(zonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6))
        // Iterable
        _ <- Ns(id).zonedDateTimeArray.add(Iterable(zonedDateTime7)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeArray.add(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeArray(Array(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).zonedDateTimeArray.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimeArray.remove(zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).zonedDateTimeArray.remove(zonedDateTime6, zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).zonedDateTimeArray.remove(zonedDateTime4, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).zonedDateTimeArray.remove(Array(zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1, zonedDateTime2,
          zonedDateTime1, zonedDateTime2,
        ))

        _ <- Ns(id).zonedDateTimeArray.remove(Seq(zonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(
          zonedDateTime1,
          zonedDateTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).zonedDateTimeArray.remove(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_.head ==> Array(zonedDateTime1, zonedDateTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).zonedDateTimeArray.remove(Seq(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimeArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
