// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

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
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bigDecimals(Set(bigDecimal3, bigDecimal4)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal3, bigDecimal4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).bigDecimals(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
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

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigDecimals.add(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))
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

        // Remove Iterable of values
        _ <- Ns(id).bigDecimals.remove(Seq(bigDecimal2)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).bigDecimals.remove(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigDecimals.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
