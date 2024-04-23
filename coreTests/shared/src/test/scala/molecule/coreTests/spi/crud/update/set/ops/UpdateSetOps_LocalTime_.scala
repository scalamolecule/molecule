// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localTimeSet(Set(localTime1, localTime2)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localTimeSet(Set(localTime2, localTime3)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime2, localTime3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").localTimeSet(Set(localTime3, localTime4)).update.transact
        _ <- Ns.i.s.localTimeSet.query.get.map(_.head ==> (42, "foo", Set(localTime3, localTime4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).localTimeSet(Set.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).localTimeSet(Set(localTime1, localTime2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).localTimeSet().update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localTimeSet.add(localTime1).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).localTimeSet.add(localTime1).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Add new value
        _ <- Ns(id).localTimeSet.add(localTime2).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Add multiple values with vararg
        _ <- Ns(id).localTimeSet.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).localTimeSet.add(List(localTime5, localTime6)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localTimeSet.add(Set.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).localTimeSet.remove(localTime1).update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localTimeSet.add(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7).update.transact

        // Remove value
        _ <- Ns(id).localTimeSet.remove(localTime7).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localTimeSet.remove(localTime9).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).localTimeSet.remove(localTime6, localTime6).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5))

        // Remove multiple values with varargs
        _ <- Ns(id).localTimeSet.remove(localTime4, localTime5).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3))

        // Remove multiple values with Iterable
        _ <- Ns(id).localTimeSet.remove(List(localTime2, localTime3)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localTimeSet.remove(Vector.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localTimeSet.remove(Set(localTime1)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
