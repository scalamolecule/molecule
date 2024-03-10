// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

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
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bytes(Set(byte3, byte4)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte3, byte4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).bytes(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)

        id <- Ns.bytes(Set(byte1, byte2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
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

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bytes.add(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7))
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

        // Remove Iterable of values
        _ <- Ns(id).bytes.remove(Seq(byte2)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).bytes.remove(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bytes.remove(Seq(byte1)).update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
