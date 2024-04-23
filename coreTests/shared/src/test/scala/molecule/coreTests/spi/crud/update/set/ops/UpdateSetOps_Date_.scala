// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).dateSet(Set(date1, date2)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).dateSet(Set(date2, date3)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date2, date3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").dateSet(Set(date3, date4)).update.transact
        _ <- Ns.i.s.dateSet.query.get.map(_.head ==> (42, "foo", Set(date3, date4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).dateSet(Set.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).dateSet(Set(date1, date2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).dateSet().update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).dateSet.add(date1).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).dateSet.add(date1).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Add new value
        _ <- Ns(id).dateSet.add(date2).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))

        // Add multiple values with vararg
        _ <- Ns(id).dateSet.add(date3, date4).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Add multiple values with Iterable
        _ <- Ns(id).dateSet.add(List(date5, date6)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).dateSet.add(Set.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).dateSet.remove(date1).update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).dateSet.add(date1, date2, date3, date4, date5, date6, date7).update.transact

        // Remove value
        _ <- Ns(id).dateSet.remove(date7).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Removing non-existing value has no effect
        _ <- Ns(id).dateSet.remove(date9).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).dateSet.remove(date6, date6).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Remove multiple values with varargs
        _ <- Ns(id).dateSet.remove(date4, date5).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3))

        // Remove multiple values with Iterable
        _ <- Ns(id).dateSet.remove(List(date2, date3)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).dateSet.remove(Vector.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).dateSet.remove(Set(date1)).update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
