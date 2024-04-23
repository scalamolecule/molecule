// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeSet(Set(localDateTime1, localDateTime2)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localDateTimeSet(Set(localDateTime2, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime2, localDateTime3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").localDateTimeSet(Set(localDateTime3, localDateTime4)).update.transact
        _ <- Ns.i.s.localDateTimeSet.query.get.map(_.head ==> (42, "foo", Set(localDateTime3, localDateTime4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).localDateTimeSet(Set.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateTimeSet(Set(localDateTime1, localDateTime2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).localDateTimeSet().update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeSet.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).localDateTimeSet.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Add new value
        _ <- Ns(id).localDateTimeSet.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Add multiple values with vararg
        _ <- Ns(id).localDateTimeSet.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).localDateTimeSet.add(List(localDateTime5, localDateTime6)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateTimeSet.add(Set.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).localDateTimeSet.remove(localDateTime1).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localDateTimeSet.add(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7).update.transact

        // Remove value
        _ <- Ns(id).localDateTimeSet.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimeSet.remove(localDateTime9).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).localDateTimeSet.remove(localDateTime6, localDateTime6).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5))

        // Remove multiple values with varargs
        _ <- Ns(id).localDateTimeSet.remove(localDateTime4, localDateTime5).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3))

        // Remove multiple values with Iterable
        _ <- Ns(id).localDateTimeSet.remove(List(localDateTime2, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localDateTimeSet.remove(Vector.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateTimeSet.remove(Set(localDateTime1)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
