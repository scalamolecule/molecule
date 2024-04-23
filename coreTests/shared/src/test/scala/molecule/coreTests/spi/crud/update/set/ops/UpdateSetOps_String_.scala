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

trait UpdateSetOps_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).stringSet(Set(string1, string2)).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).stringSet(Set(string2, string3)).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string2, string3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").stringSet(Set(string3, string4)).update.transact
        _ <- Ns.i.s.stringSet.query.get.map(_.head ==> (42, "foo", Set(string3, string4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).stringSet(Set.empty[String]).update.transact
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).stringSet(Set(string1, string2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).stringSet().update.transact
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).stringSet.add(string1).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).stringSet.add(string1).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1))

        // Add new value
        _ <- Ns(id).stringSet.add(string2).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2))

        // Add multiple values with vararg
        _ <- Ns(id).stringSet.add(string3, string4).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4))

        // Add multiple values with Iterable
        _ <- Ns(id).stringSet.add(List(string5, string6)).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).stringSet.add(Set.empty[String]).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).stringSet.remove(string1).update.transact
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).stringSet.add(string1, string2, string3, string4, string5, string6, string7).update.transact

        // Remove value
        _ <- Ns(id).stringSet.remove(string7).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))

        // Removing non-existing value has no effect
        _ <- Ns(id).stringSet.remove(string9).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).stringSet.remove(string6, string6).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))

        // Remove multiple values with varargs
        _ <- Ns(id).stringSet.remove(string4, string5).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2, string3))

        // Remove multiple values with Iterable
        _ <- Ns(id).stringSet.remove(List(string2, string3)).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).stringSet.remove(Vector.empty[String]).update.transact
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).stringSet.remove(Set(string1)).update.transact
        _ <- Ns.stringSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
