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

trait UpdateArrayOps_BigInt_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigIntArray(Array(bigInt1, bigInt2, bigInt2)).save.transact.map(_.id)
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).bigIntArray(Array(bigInt3, bigInt4, bigInt4)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt3, bigInt4, bigInt4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).bigIntArray(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigIntArray.query.get.map(_ ==> Nil)

        id <- Ns.bigIntArray(Array(bigInt1, bigInt2, bigInt2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).bigIntArray().update.transact
        _ <- Ns.bigIntArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigIntArray(Array(bigInt1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).bigIntArray.add(bigInt2).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2))

        // Add existing value
        _ <- Ns(id).bigIntArray.add(bigInt1).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1))

        // Add multiple values (vararg)
        _ <- Ns(id).bigIntArray.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).bigIntArray.add(Seq(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5))
        // Array
        _ <- Ns(id).bigIntArray.add(Array(bigInt6)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6))
        // Iterable
        _ <- Ns(id).bigIntArray.add(Iterable(bigInt7)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6, bigInt7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigIntArray.add(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6, bigInt7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigIntArray(Array(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).bigIntArray.remove(bigInt7).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigIntArray.remove(bigInt9).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).bigIntArray.remove(bigInt6, bigInt6).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigIntArray.remove(bigInt4, bigInt5).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1, bigInt2, bigInt3,
          bigInt1, bigInt2, bigInt3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).bigIntArray.remove(Array(bigInt3)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1, bigInt2,
          bigInt1, bigInt2,
        ))

        _ <- Ns(id).bigIntArray.remove(Seq(bigInt2)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(
          bigInt1,
          bigInt1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigIntArray.remove(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigIntArray.query.get.map(_.head ==> Array(bigInt1, bigInt1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigIntArray.remove(Seq(bigInt1)).update.transact
        _ <- Ns.bigIntArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
