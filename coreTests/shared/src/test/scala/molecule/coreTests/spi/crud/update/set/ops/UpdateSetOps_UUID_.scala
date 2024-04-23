// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).uuidSet(Set(uuid1, uuid2)).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).uuidSet(Set(uuid2, uuid3)).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid2, uuid3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").uuidSet(Set(uuid3, uuid4)).update.transact
        _ <- Ns.i.s.uuidSet.query.get.map(_.head ==> (42, "foo", Set(uuid3, uuid4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).uuidSet(Set.empty[UUID]).update.transact
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).uuidSet(Set(uuid1, uuid2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).uuidSet().update.transact
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).uuidSet.add(uuid1).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).uuidSet.add(uuid1).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1))

        // Add new value
        _ <- Ns(id).uuidSet.add(uuid2).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))

        // Add multiple values with vararg
        _ <- Ns(id).uuidSet.add(uuid3, uuid4).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4))

        // Add multiple values with Iterable
        _ <- Ns(id).uuidSet.add(List(uuid5, uuid6)).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uuidSet.add(Set.empty[UUID]).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).uuidSet.remove(uuid1).update.transact
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).uuidSet.add(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7).update.transact

        // Remove value
        _ <- Ns(id).uuidSet.remove(uuid7).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6))

        // Removing non-existing value has no effect
        _ <- Ns(id).uuidSet.remove(uuid9).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).uuidSet.remove(uuid6, uuid6).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5))

        // Remove multiple values with varargs
        _ <- Ns(id).uuidSet.remove(uuid4, uuid5).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3))

        // Remove multiple values with Iterable
        _ <- Ns(id).uuidSet.remove(List(uuid2, uuid3)).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uuidSet.remove(Vector.empty[UUID]).update.transact
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).uuidSet.remove(Set(uuid1)).update.transact
        _ <- Ns.uuidSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
