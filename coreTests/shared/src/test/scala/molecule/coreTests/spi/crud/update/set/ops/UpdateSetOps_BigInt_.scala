// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

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

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigInts.remove(Seq(bigInt1)).update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
