// GENERATED CODE ********************************
package molecule.coreTests.test.crud.update.set.ops

import java.net.URI
import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_URI_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uris(Set(uri1, uri2)).save.transact.map(_.id)

        _ <- Ns(id).uris(Set(uri3, uri4)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri3, uri4))

        // Apply Seq of values
        _ <- Ns(id).uris(Set(uri4, uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri4, uri5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).uris(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)

        _ <- Ns(id).uris(Set(uri1, uri2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).uris().update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uris(Set(uri1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).uris.add(uri2).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Add existing value (no effect)
        _ <- Ns(id).uris.add(uri2).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Add multiple values (vararg)
        _ <- Ns(id).uris.add(uri3, uri4).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).uris.add(Seq(uri4, uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))
        // Set
        _ <- Ns(id).uris.add(Set(uri6)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))
        // Iterable
        _ <- Ns(id).uris.add(Iterable(uri7)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).uris.add(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).uris.swap(uri6 -> uri8).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).uris.swap(uri5 -> uri8).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri8))

        // Replace multiple values (vararg)
        _ <- Ns(id).uris.swap(uri3 -> uri6, uri4 -> uri7).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri6, uri7, uri8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(id).uris.swap(uri4 -> uri9).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri6, uri7, uri8, uri9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).uris.swap(Seq(uri2 -> uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).uris.swap(Seq.empty[(URI, URI)]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).uris.swap(uri1 -> uri2, uri1 -> uri3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).uris.swap(uri1 -> uri3, uri2 -> uri3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).uris.remove(uri6).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Removing non-existing value has no effect
        _ <- Ns(id).uris.remove(uri7).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).uris.remove(uri5, uri5).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Remove multiple values (vararg)
        _ <- Ns(id).uris.remove(uri3, uri4).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Remove Seq of values
        _ <- Ns(id).uris.remove(Seq(uri2)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uris.remove(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).uris.remove(Seq(uri1)).update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
