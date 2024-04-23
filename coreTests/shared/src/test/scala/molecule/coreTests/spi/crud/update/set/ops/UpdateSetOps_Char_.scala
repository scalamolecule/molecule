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

trait UpdateSetOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).charSet(Set(char1, char2)).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).charSet(Set(char2, char3)).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char2, char3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").charSet(Set(char3, char4)).update.transact
        _ <- Ns.i.s.charSet.query.get.map(_.head ==> (42, "foo", Set(char3, char4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).charSet(Set.empty[Char]).update.transact
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).charSet(Set(char1, char2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).charSet().update.transact
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).charSet.add(char1).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).charSet.add(char1).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1))

        // Add new value
        _ <- Ns(id).charSet.add(char2).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2))

        // Add multiple values with vararg
        _ <- Ns(id).charSet.add(char3, char4).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Add multiple values with Iterable
        _ <- Ns(id).charSet.add(List(char5, char6)).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).charSet.add(Set.empty[Char]).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).charSet.remove(char1).update.transact
        _ <- Ns.charSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).charSet.add(char1, char2, char3, char4, char5, char6, char7).update.transact

        // Remove value
        _ <- Ns(id).charSet.remove(char7).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Removing non-existing value has no effect
        _ <- Ns(id).charSet.remove(char9).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).charSet.remove(char6, char6).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Remove multiple values with varargs
        _ <- Ns(id).charSet.remove(char4, char5).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2, char3))

        // Remove multiple values with Iterable
        _ <- Ns(id).charSet.remove(List(char2, char3)).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).charSet.remove(Vector.empty[Char]).update.transact
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).charSet.remove(Set(char1)).update.transact
        _ <- Ns.charSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
