// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Long_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.longArray(Array(long1, long2, long2)).save.transact.map(_.id)
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).longArray(Array(long3, long4, long4)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long3, long4, long4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).longArray(Seq.empty[Long]).update.transact
        _ <- Ns.longArray.query.get.map(_ ==> Nil)

        id <- Ns.longArray(Array(long1, long2, long2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).longArray().update.transact
        _ <- Ns.longArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.longArray(Array(long1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).longArray.add(long2).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2))

        // Add existing value
        _ <- Ns(id).longArray.add(long1).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1))

        // Add multiple values (vararg)
        _ <- Ns(id).longArray.add(long3, long4).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1, long3, long4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).longArray.add(Seq(long4, long5)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1, long3, long4, long4, long5))
        // Array
        _ <- Ns(id).longArray.add(Array(long6)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1, long3, long4, long4, long5, long6))
        // Iterable
        _ <- Ns(id).longArray.add(Iterable(long7)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1, long3, long4, long4, long5, long6, long7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).longArray.add(Seq.empty[Long]).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long2, long1, long3, long4, long4, long5, long6, long7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.longArray(Array(
          long1, long2, long3, long4, long5, long6, long7,
          long1, long2, long3, long4, long5, long6, long7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).longArray.remove(long7).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).longArray.remove(long9).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).longArray.remove(long6, long6).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1, long2, long3, long4, long5,
          long1, long2, long3, long4, long5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).longArray.remove(long4, long5).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1, long2, long3,
          long1, long2, long3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).longArray.remove(Array(long3)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1, long2,
          long1, long2,
        ))

        _ <- Ns(id).longArray.remove(Seq(long2)).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(
          long1,
          long1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).longArray.remove(Seq.empty[Long]).update.transact
        _ <- Ns.longArray.query.get.map(_.head ==> Array(long1, long1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).longArray.remove(Seq(long1)).update.transact
        _ <- Ns.longArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
