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
        id <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.id)
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bigDecimalSet(Set(bigDecimal3, bigDecimal4)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal3, bigDecimal4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).bigDecimalSet(Set.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).bigDecimalSet().update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSet(Set(bigDecimal1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).bigDecimalSet.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).bigDecimalSet.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add multiple values (vararg)
        _ <- Ns(id).bigDecimalSet.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Add multiple values (Seq)
        _ <- Ns(id).bigDecimalSet.add(Seq(bigDecimal5, bigDecimal6)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).bigDecimalSet.add(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal9).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal6, bigDecimal6).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal4, bigDecimal5).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3))

        // Remove multiple values (Seq)
        _ <- Ns(id).bigDecimalSet.remove(Seq(bigDecimal2, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigDecimalSet.remove(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).bigDecimalSet.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
