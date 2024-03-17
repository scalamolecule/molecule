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
        id <- Ns.bigIntSet(Set(bigInt1, bigInt2)).save.transact.map(_.id)
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bigIntSet(Set(bigInt3, bigInt4)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt3, bigInt4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).bigIntSet(Set.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        id <- Ns.bigIntSet(Set(bigInt1, bigInt2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).bigIntSet().update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigIntSet(Set(bigInt1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).bigIntSet.add(bigInt2).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).bigIntSet.add(bigInt2).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add multiple values (vararg)
        _ <- Ns(id).bigIntSet.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Add multiple values (Seq)
        _ <- Ns(id).bigIntSet.add(Seq(bigInt5, bigInt6)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).bigIntSet.add(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigIntSet(Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).bigIntSet.remove(bigInt7).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigIntSet.remove(bigInt9).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).bigIntSet.remove(bigInt6, bigInt6).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigIntSet.remove(bigInt4, bigInt5).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3))

        // Remove multiple values (Seq)
        _ <- Ns(id).bigIntSet.remove(Seq(bigInt2, bigInt3)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigIntSet.remove(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).bigIntSet.remove(Seq(bigInt1)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
