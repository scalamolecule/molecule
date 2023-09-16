// GENERATED CODE ********************************
package molecule.coreTests.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigInts(Set(bigInt1, bigInt2)).save.transact.map(_.id)

        _ <- Ns(id).bigInts(Set(bigInt3, bigInt4)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt3, bigInt4))

        // Apply Seq of values
        _ <- Ns(id).bigInts(Set(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt4, bigInt5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).bigInts(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigInts(Set(bigInt1, bigInt2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).bigInts().update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigInts(Set(bigInt1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).bigInts.add(bigInt2).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add existing value (no effect)
        _ <- Ns(id).bigInts.add(bigInt2).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add multiple values (vararg)
        _ <- Ns(id).bigInts.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).bigInts.add(Seq(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))
        // Set
        _ <- Ns(id).bigInts.add(Set(bigInt6)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))
        // Iterable
        _ <- Ns(id).bigInts.add(Iterable(bigInt7)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).bigInts.add(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.bigInts(Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).bigInts.swap(bigInt6 -> bigInt8).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).bigInts.swap(bigInt5 -> bigInt8).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt8))

        // Replace multiple values (vararg)
        _ <- Ns(id).bigInts.swap(bigInt3 -> bigInt6, bigInt4 -> bigInt7).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt6, bigInt7, bigInt8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).bigInts.swap(bigInt4 -> bigInt9).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt6, bigInt7, bigInt8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).bigInts.swap(bigInt4 -> bigInt9).upsert.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt6, bigInt7, bigInt8, bigInt9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).bigInts.swap(Seq(bigInt2 -> bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt5, bigInt6, bigInt7, bigInt8, bigInt9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).bigInts.swap(Seq.empty[(BigInt, BigInt)]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt5, bigInt6, bigInt7, bigInt8, bigInt9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).bigInts.swap(bigInt1 -> bigInt2, bigInt1 -> bigInt3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).bigInts.swap(bigInt1 -> bigInt3, bigInt2 -> bigInt3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigInts(Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).bigInts.remove(bigInt6).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigInts.remove(bigInt7).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).bigInts.remove(bigInt5, bigInt5).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigInts.remove(bigInt3, bigInt4).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Remove Seq of values
        _ <- Ns(id).bigInts.remove(Seq(bigInt2)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigInts.remove(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).bigInts.remove(Seq(bigInt1)).update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
