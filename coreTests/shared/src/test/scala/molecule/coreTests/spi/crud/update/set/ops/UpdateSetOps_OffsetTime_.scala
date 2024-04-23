// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeSet(Set(offsetTime1, offsetTime2)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).offsetTimeSet(Set(offsetTime2, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime2, offsetTime3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").offsetTimeSet(Set(offsetTime3, offsetTime4)).update.transact
        _ <- Ns.i.s.offsetTimeSet.query.get.map(_.head ==> (42, "foo", Set(offsetTime3, offsetTime4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).offsetTimeSet(Set.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetTimeSet(Set(offsetTime1, offsetTime2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).offsetTimeSet().update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeSet.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).offsetTimeSet.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Add new value
        _ <- Ns(id).offsetTimeSet.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Add multiple values with vararg
        _ <- Ns(id).offsetTimeSet.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).offsetTimeSet.add(List(offsetTime5, offsetTime6)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetTimeSet.add(Set.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).offsetTimeSet.remove(offsetTime1).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).offsetTimeSet.add(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7).update.transact

        // Remove value
        _ <- Ns(id).offsetTimeSet.remove(offsetTime7).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetTimeSet.remove(offsetTime9).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).offsetTimeSet.remove(offsetTime6, offsetTime6).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5))

        // Remove multiple values with varargs
        _ <- Ns(id).offsetTimeSet.remove(offsetTime4, offsetTime5).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3))

        // Remove multiple values with Iterable
        _ <- Ns(id).offsetTimeSet.remove(List(offsetTime2, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).offsetTimeSet.remove(Vector.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetTimeSet.remove(Set(offsetTime1)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
