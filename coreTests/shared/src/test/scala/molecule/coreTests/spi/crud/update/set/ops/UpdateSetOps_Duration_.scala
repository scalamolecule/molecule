// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).durationSet(Set(duration1, duration2)).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).durationSet(Set(duration2, duration3)).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration2, duration3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").durationSet(Set(duration3, duration4)).update.transact
        _ <- Ns.i.s.durationSet.query.get.map(_.head ==> (42, "foo", Set(duration3, duration4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).durationSet(Set.empty[Duration]).update.transact
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).durationSet(Set(duration1, duration2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).durationSet().update.transact
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).durationSet.add(duration1).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).durationSet.add(duration1).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1))

        // Add new value
        _ <- Ns(id).durationSet.add(duration2).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2))

        // Add multiple values with vararg
        _ <- Ns(id).durationSet.add(duration3, duration4).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4))

        // Add multiple values with Iterable
        _ <- Ns(id).durationSet.add(List(duration5, duration6)).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).durationSet.add(Set.empty[Duration]).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).durationSet.remove(duration1).update.transact
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).durationSet.add(duration1, duration2, duration3, duration4, duration5, duration6, duration7).update.transact

        // Remove value
        _ <- Ns(id).durationSet.remove(duration7).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Removing non-existing value has no effect
        _ <- Ns(id).durationSet.remove(duration9).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).durationSet.remove(duration6, duration6).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Remove multiple values with varargs
        _ <- Ns(id).durationSet.remove(duration4, duration5).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3))

        // Remove multiple values with Iterable
        _ <- Ns(id).durationSet.remove(List(duration2, duration3)).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).durationSet.remove(Vector.empty[Duration]).update.transact
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).durationSet.remove(Set(duration1)).update.transact
        _ <- Ns.durationSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
