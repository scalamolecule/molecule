// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).offsetDateTimeSet(Set(offsetDateTime2, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime2, offsetDateTime3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").offsetDateTimeSet(Set(offsetDateTime3, offsetDateTime4)).update.transact
        _ <- Ns.i.s.offsetDateTimeSet.query.get.map(_.head ==> (42, "foo", Set(offsetDateTime3, offsetDateTime4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).offsetDateTimeSet(Set.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).offsetDateTimeSet().update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Add new value
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Add multiple values with vararg
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).offsetDateTimeSet.add(List(offsetDateTime5, offsetDateTime6)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimeSet.add(Set.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7).update.transact

        // Remove value
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime9).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime6, offsetDateTime6).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5))

        // Remove multiple values with varargs
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime4, offsetDateTime5).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))

        // Remove multiple values with Iterable
        _ <- Ns(id).offsetDateTimeSet.remove(List(offsetDateTime2, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimeSet.remove(Vector.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetDateTimeSet.remove(Set(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
