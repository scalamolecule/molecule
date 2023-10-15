// GENERATED CODE ********************************
package molecule.coreTests.compliance.crud.update.set.ops

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bytes(Set(byte1, byte2)).save.transact.map(_.id)

        _ <- Ns(id).bytes(Set(byte3, byte4)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte3, byte4))

        // Apply Seq of values
        _ <- Ns(id).bytes(Set(byte4, byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte4, byte5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).bytes(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)

        _ <- Ns(id).bytes(Set(byte1, byte2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).bytes().update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bytes(Set(byte1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).bytes.add(byte2).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Add existing value (no effect)
        _ <- Ns(id).bytes.add(byte2).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Add multiple values (vararg)
        _ <- Ns(id).bytes.add(byte3, byte4).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).bytes.add(Seq(byte4, byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))
        // Set
        _ <- Ns(id).bytes.add(Set(byte6)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))
        // Iterable
        _ <- Ns(id).bytes.add(Iterable(byte7)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).bytes.add(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).bytes.swap(byte6 -> byte8).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).bytes.swap(byte5 -> byte8).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte8))

        // Replace multiple values (vararg)
        _ <- Ns(id).bytes.swap(byte3 -> byte6, byte4 -> byte7).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte6, byte7, byte8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).bytes.swap(byte4 -> byte9).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte6, byte7, byte8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).bytes.swap(byte4 -> byte9).upsert.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte6, byte7, byte8, byte9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).bytes.swap(Seq(byte2 -> byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).bytes.swap(Seq.empty[(Byte, Byte)]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).bytes.swap(byte1 -> byte2, byte1 -> byte3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).bytes.swap(byte1 -> byte3, byte2 -> byte3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).bytes.remove(byte6).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))

        // Removing non-existing value has no effect
        _ <- Ns(id).bytes.remove(byte7).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).bytes.remove(byte5, byte5).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4))

        // Remove multiple values (vararg)
        _ <- Ns(id).bytes.remove(byte3, byte4).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Remove Seq of values
        _ <- Ns(id).bytes.remove(Seq(byte2)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bytes.remove(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).bytes.remove(Seq(byte1)).update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
