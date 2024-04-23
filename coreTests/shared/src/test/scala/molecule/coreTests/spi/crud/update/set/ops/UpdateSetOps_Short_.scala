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

trait UpdateSetOps_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).shortSet(Set(short1, short2)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).shortSet(Set(short2, short3)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short2, short3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").shortSet(Set(short3, short4)).update.transact
        _ <- Ns.i.s.shortSet.query.get.map(_.head ==> (42, "foo", Set(short3, short4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).shortSet(Set.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).shortSet(Set(short1, short2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).shortSet().update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).shortSet.add(short1).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).shortSet.add(short1).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Add new value
        _ <- Ns(id).shortSet.add(short2).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))

        // Add multiple values with vararg
        _ <- Ns(id).shortSet.add(short3, short4).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Add multiple values with Iterable
        _ <- Ns(id).shortSet.add(List(short5, short6)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).shortSet.add(Set.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).shortSet.remove(short1).update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).shortSet.add(short1, short2, short3, short4, short5, short6, short7).update.transact

        // Remove value
        _ <- Ns(id).shortSet.remove(short7).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Removing non-existing value has no effect
        _ <- Ns(id).shortSet.remove(short9).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).shortSet.remove(short6, short6).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Remove multiple values with varargs
        _ <- Ns(id).shortSet.remove(short4, short5).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3))

        // Remove multiple values with Iterable
        _ <- Ns(id).shortSet.remove(List(short2, short3)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).shortSet.remove(Vector.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).shortSet.remove(Set(short1)).update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
