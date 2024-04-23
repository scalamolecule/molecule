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

trait UpdateSetOps_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).doubleSet(Set(double1, double2)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).doubleSet(Set(double2, double3)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double2, double3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").doubleSet(Set(double3, double4)).update.transact
        _ <- Ns.i.s.doubleSet.query.get.map(_.head ==> (42, "foo", Set(double3, double4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).doubleSet(Set.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).doubleSet(Set(double1, double2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).doubleSet().update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).doubleSet.add(double1).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).doubleSet.add(double1).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Add new value
        _ <- Ns(id).doubleSet.add(double2).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))

        // Add multiple values with vararg
        _ <- Ns(id).doubleSet.add(double3, double4).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Add multiple values with Iterable
        _ <- Ns(id).doubleSet.add(List(double5, double6)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).doubleSet.add(Set.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).doubleSet.remove(double1).update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).doubleSet.add(double1, double2, double3, double4, double5, double6, double7).update.transact

        // Remove value
        _ <- Ns(id).doubleSet.remove(double7).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Removing non-existing value has no effect
        _ <- Ns(id).doubleSet.remove(double9).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).doubleSet.remove(double6, double6).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Remove multiple values with varargs
        _ <- Ns(id).doubleSet.remove(double4, double5).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3))

        // Remove multiple values with Iterable
        _ <- Ns(id).doubleSet.remove(List(double2, double3)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).doubleSet.remove(Vector.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).doubleSet.remove(Set(double1)).update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
