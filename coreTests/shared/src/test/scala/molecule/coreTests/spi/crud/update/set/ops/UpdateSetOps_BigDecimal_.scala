// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.id)

        _ <- Ns(id).bigDecimals(Set(bigDecimal3, bigDecimal4)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal3, bigDecimal4))

        // Apply Seq of values
        _ <- Ns(id).bigDecimals(Set(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal4, bigDecimal5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).bigDecimals(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigDecimals(Set(bigDecimal1, bigDecimal2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).bigDecimals().update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimals(Set(bigDecimal1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).bigDecimals.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add existing value (no effect)
        _ <- Ns(id).bigDecimals.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add multiple values (vararg)
        _ <- Ns(id).bigDecimals.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).bigDecimals.add(Seq(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))
        // Set
        _ <- Ns(id).bigDecimals.add(Set(bigDecimal6)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))
        // Iterable
        _ <- Ns(id).bigDecimals.add(Iterable(bigDecimal7)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).bigDecimals.add(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).bigDecimals.swap(bigDecimal6 -> bigDecimal8).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).bigDecimals.swap(bigDecimal5 -> bigDecimal8).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal8))

        // Replace multiple values (vararg)
        _ <- Ns(id).bigDecimals.swap(bigDecimal3 -> bigDecimal6, bigDecimal4 -> bigDecimal7).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).bigDecimals.swap(bigDecimal4 -> bigDecimal9).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).bigDecimals.swap(bigDecimal4 -> bigDecimal9).upsert.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).bigDecimals.swap(Seq(bigDecimal2 -> bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).bigDecimals.swap(Seq.empty[(BigDecimal, BigDecimal)]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))

        // Can't swap duplicate from/to values
        _ <- Ns("42").bigDecimals.swap(bigDecimal1 -> bigDecimal2, bigDecimal1 -> bigDecimal3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns("42").bigDecimals.swap(bigDecimal1 -> bigDecimal3, bigDecimal2 -> bigDecimal3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).bigDecimals.remove(bigDecimal6).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimals.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).bigDecimals.remove(bigDecimal5, bigDecimal5).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigDecimals.remove(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Remove Seq of values
        _ <- Ns(id).bigDecimals.remove(Seq(bigDecimal2)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigDecimals.remove(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).bigDecimals.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
