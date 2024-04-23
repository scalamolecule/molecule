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

trait UpdateSetOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalSet(Set(bigDecimal1, bigDecimal2)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).bigDecimalSet(Set(bigDecimal2, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal2, bigDecimal3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").bigDecimalSet(Set(bigDecimal3, bigDecimal4)).update.transact
        _ <- Ns.i.s.bigDecimalSet.query.get.map(_.head ==> (42, "foo", Set(bigDecimal3, bigDecimal4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).bigDecimalSet(Set.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigDecimalSet(Set(bigDecimal1, bigDecimal2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).bigDecimalSet().update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalSet.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).bigDecimalSet.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Add new value
        _ <- Ns(id).bigDecimalSet.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add multiple values with vararg
        _ <- Ns(id).bigDecimalSet.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Add multiple values with Iterable
        _ <- Ns(id).bigDecimalSet.add(List(bigDecimal5, bigDecimal6)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalSet.add(Set.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).bigDecimalSet.add(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7).update.transact

        // Remove value
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal9).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal6, bigDecimal6).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Remove multiple values with varargs
        _ <- Ns(id).bigDecimalSet.remove(bigDecimal4, bigDecimal5).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3))

        // Remove multiple values with Iterable
        _ <- Ns(id).bigDecimalSet.remove(List(bigDecimal2, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalSet.remove(Vector.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).bigDecimalSet.remove(Set(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
