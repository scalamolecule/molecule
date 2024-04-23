// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).zonedDateTimeSet(Set(zonedDateTime2, zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime2, zonedDateTime3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").zonedDateTimeSet(Set(zonedDateTime3, zonedDateTime4)).update.transact
        _ <- Ns.i.s.zonedDateTimeSet.query.get.map(_.head ==> (42, "foo", Set(zonedDateTime3, zonedDateTime4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).zonedDateTimeSet(Set.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).zonedDateTimeSet().update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeSet.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).zonedDateTimeSet.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))

        // Add new value
        _ <- Ns(id).zonedDateTimeSet.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Add multiple values with vararg
        _ <- Ns(id).zonedDateTimeSet.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).zonedDateTimeSet.add(List(zonedDateTime5, zonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeSet.add(Set.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).zonedDateTimeSet.remove(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).zonedDateTimeSet.add(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7).update.transact

        // Remove value
        _ <- Ns(id).zonedDateTimeSet.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimeSet.remove(zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).zonedDateTimeSet.remove(zonedDateTime6, zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5))

        // Remove multiple values with varargs
        _ <- Ns(id).zonedDateTimeSet.remove(zonedDateTime4, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))

        // Remove multiple values with Iterable
        _ <- Ns(id).zonedDateTimeSet.remove(List(zonedDateTime2, zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeSet.remove(Vector.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).zonedDateTimeSet.remove(Set(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
