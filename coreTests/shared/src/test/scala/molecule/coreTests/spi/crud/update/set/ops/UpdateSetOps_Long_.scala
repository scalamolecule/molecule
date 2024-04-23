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

trait UpdateSetOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).longSet(Set(long1, long2)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).longSet(Set(long2, long3)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long2, long3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").longSet(Set(long3, long4)).update.transact
        _ <- Ns.i.s.longSet.query.get.map(_.head ==> (42, "foo", Set(long3, long4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).longSet(Set.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).longSet(Set(long1, long2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).longSet().update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).longSet.add(long1).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).longSet.add(long1).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Add new value
        _ <- Ns(id).longSet.add(long2).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))

        // Add multiple values with vararg
        _ <- Ns(id).longSet.add(long3, long4).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Add multiple values with Iterable
        _ <- Ns(id).longSet.add(List(long5, long6)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).longSet.add(Set.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).longSet.remove(long1).update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).longSet.add(long1, long2, long3, long4, long5, long6, long7).update.transact

        // Remove value
        _ <- Ns(id).longSet.remove(long7).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Removing non-existing value has no effect
        _ <- Ns(id).longSet.remove(long9).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).longSet.remove(long6, long6).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Remove multiple values with varargs
        _ <- Ns(id).longSet.remove(long4, long5).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3))

        // Remove multiple values with Iterable
        _ <- Ns(id).longSet.remove(List(long2, long3)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).longSet.remove(Vector.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).longSet.remove(Set(long1)).update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
