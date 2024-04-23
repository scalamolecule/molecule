// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).bigIntSet(Set(bigInt1, bigInt2)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bigIntSet(Set(bigInt2, bigInt3)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt2, bigInt3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").bigIntSet(Set(bigInt3, bigInt4)).update.transact
        _ <- Ns.i.s.bigIntSet.query.get.map(_.head ==> (42, "foo", Set(bigInt3, bigInt4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).bigIntSet(Set.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigIntSet(Set(bigInt1, bigInt2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).bigIntSet().update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).bigIntSet.add(bigInt1).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).bigIntSet.add(bigInt1).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Add new value
        _ <- Ns(id).bigIntSet.add(bigInt2).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add multiple values with vararg
        _ <- Ns(id).bigIntSet.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Add multiple values with Iterable
        _ <- Ns(id).bigIntSet.add(List(bigInt5, bigInt6)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigIntSet.add(Set.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).bigIntSet.remove(bigInt1).update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).bigIntSet.add(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7).update.transact

        // Remove value
        _ <- Ns(id).bigIntSet.remove(bigInt7).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigIntSet.remove(bigInt9).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).bigIntSet.remove(bigInt6, bigInt6).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Remove multiple values with varargs
        _ <- Ns(id).bigIntSet.remove(bigInt4, bigInt5).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3))

        // Remove multiple values with Iterable
        _ <- Ns(id).bigIntSet.remove(List(bigInt2, bigInt3)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).bigIntSet.remove(Vector.empty[BigInt]).update.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).bigIntSet.remove(Set(bigInt1)).update.transact
        _ <- Ns.bigIntSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
