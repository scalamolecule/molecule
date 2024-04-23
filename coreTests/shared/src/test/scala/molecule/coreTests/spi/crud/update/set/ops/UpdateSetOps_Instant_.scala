// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).instantSet(Set(instant1, instant2)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).instantSet(Set(instant2, instant3)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant2, instant3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").instantSet(Set(instant3, instant4)).update.transact
        _ <- Ns.i.s.instantSet.query.get.map(_.head ==> (42, "foo", Set(instant3, instant4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).instantSet(Set.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).instantSet(Set(instant1, instant2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).instantSet().update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).instantSet.add(instant1).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).instantSet.add(instant1).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Add new value
        _ <- Ns(id).instantSet.add(instant2).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))

        // Add multiple values with vararg
        _ <- Ns(id).instantSet.add(instant3, instant4).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4))

        // Add multiple values with Iterable
        _ <- Ns(id).instantSet.add(List(instant5, instant6)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).instantSet.add(Set.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).instantSet.remove(instant1).update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).instantSet.add(instant1, instant2, instant3, instant4, instant5, instant6, instant7).update.transact

        // Remove value
        _ <- Ns(id).instantSet.remove(instant7).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Removing non-existing value has no effect
        _ <- Ns(id).instantSet.remove(instant9).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).instantSet.remove(instant6, instant6).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5))

        // Remove multiple values with varargs
        _ <- Ns(id).instantSet.remove(instant4, instant5).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3))

        // Remove multiple values with Iterable
        _ <- Ns(id).instantSet.remove(List(instant2, instant3)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).instantSet.remove(Vector.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).instantSet.remove(Set(instant1)).update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
