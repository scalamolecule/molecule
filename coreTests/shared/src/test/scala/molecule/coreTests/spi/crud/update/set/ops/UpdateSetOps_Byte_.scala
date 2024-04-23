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

trait UpdateSetOps_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).byteSet(Set(byte1, byte2)).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).byteSet(Set(byte2, byte3)).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte2, byte3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").byteSet(Set(byte3, byte4)).update.transact
        _ <- Ns.i.s.byteSet.query.get.map(_.head ==> (42, "foo", Set(byte3, byte4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).byteSet(Set.empty[Byte]).update.transact
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).byteSet(Set(byte1, byte2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).byteSet().update.transact
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).byteSet.add(byte1).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).byteSet.add(byte1).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1))

        // Add new value
        _ <- Ns(id).byteSet.add(byte2).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2))

        // Add multiple values with vararg
        _ <- Ns(id).byteSet.add(byte3, byte4).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4))

        // Add multiple values with Iterable
        _ <- Ns(id).byteSet.add(List(byte5, byte6)).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).byteSet.add(Set.empty[Byte]).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).byteSet.remove(byte1).update.transact
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).byteSet.add(byte1, byte2, byte3, byte4, byte5, byte6, byte7).update.transact

        // Remove value
        _ <- Ns(id).byteSet.remove(byte7).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))

        // Removing non-existing value has no effect
        _ <- Ns(id).byteSet.remove(byte9).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).byteSet.remove(byte6, byte6).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))

        // Remove multiple values with varargs
        _ <- Ns(id).byteSet.remove(byte4, byte5).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3))

        // Remove multiple values with Iterable
        _ <- Ns(id).byteSet.remove(List(byte2, byte3)).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).byteSet.remove(Vector.empty[Byte]).update.transact
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).byteSet.remove(Set(byte1)).update.transact
        _ <- Ns.byteSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
