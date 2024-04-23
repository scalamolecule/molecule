// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).uriSet(Set(uri1, uri2)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).uriSet(Set(uri2, uri3)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri2, uri3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").uriSet(Set(uri3, uri4)).update.transact
        _ <- Ns.i.s.uriSet.query.get.map(_.head ==> (42, "foo", Set(uri3, uri4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).uriSet(Set.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).uriSet(Set(uri1, uri2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).uriSet().update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).uriSet.add(uri1).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).uriSet.add(uri1).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Add new value
        _ <- Ns(id).uriSet.add(uri2).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))

        // Add multiple values with vararg
        _ <- Ns(id).uriSet.add(uri3, uri4).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Add multiple values with Iterable
        _ <- Ns(id).uriSet.add(List(uri5, uri6)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uriSet.add(Set.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).uriSet.remove(uri1).update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).uriSet.add(uri1, uri2, uri3, uri4, uri5, uri6, uri7).update.transact

        // Remove value
        _ <- Ns(id).uriSet.remove(uri7).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Removing non-existing value has no effect
        _ <- Ns(id).uriSet.remove(uri9).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).uriSet.remove(uri6, uri6).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Remove multiple values with varargs
        _ <- Ns(id).uriSet.remove(uri4, uri5).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3))

        // Remove multiple values with Iterable
        _ <- Ns(id).uriSet.remove(List(uri2, uri3)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uriSet.remove(Vector.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).uriSet.remove(Set(uri1)).update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
