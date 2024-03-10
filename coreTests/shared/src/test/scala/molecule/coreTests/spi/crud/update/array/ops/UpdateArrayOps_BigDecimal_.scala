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

trait UpdateArrayOps_BigDecimal_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalArray(Array(bigDecimal1, bigDecimal2, bigDecimal2)).save.transact.map(_.id)
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).bigDecimalArray(Array(bigDecimal3, bigDecimal4, bigDecimal4)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal3, bigDecimal4, bigDecimal4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).bigDecimalArray(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimalArray(Array(bigDecimal1, bigDecimal2, bigDecimal2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).bigDecimalArray().update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalArray(Array(bigDecimal1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).bigDecimalArray.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2))

        // Add existing value
        _ <- Ns(id).bigDecimalArray.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1))

        // Add multiple values (vararg)
        _ <- Ns(id).bigDecimalArray.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).bigDecimalArray.add(Seq(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))
        // Array
        _ <- Ns(id).bigDecimalArray.add(Array(bigDecimal6)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6))
        // Iterable
        _ <- Ns(id).bigDecimalArray.add(Iterable(bigDecimal7)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalArray.add(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalArray(Array(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).bigDecimalArray.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimalArray.remove(bigDecimal9).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).bigDecimalArray.remove(bigDecimal6, bigDecimal6).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigDecimalArray.remove(bigDecimal4, bigDecimal5).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1, bigDecimal2, bigDecimal3,
          bigDecimal1, bigDecimal2, bigDecimal3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).bigDecimalArray.remove(Array(bigDecimal3)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1, bigDecimal2,
          bigDecimal1, bigDecimal2,
        ))

        _ <- Ns(id).bigDecimalArray.remove(Seq(bigDecimal2)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(
          bigDecimal1,
          bigDecimal1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigDecimalArray.remove(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_.head ==> Array(bigDecimal1, bigDecimal1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigDecimalArray.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
