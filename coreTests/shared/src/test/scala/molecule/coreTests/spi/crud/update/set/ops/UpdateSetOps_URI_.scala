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

trait UpdateSetOps_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uris(Set(uri1, uri2)).save.transact.map(_.id)
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).uris(Set(uri3, uri4)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri3, uri4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).uris(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)

        id <- Ns.uris(Set(uri1, uri2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
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

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uris.add(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7))
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

        // Remove Iterable of values
        _ <- Ns(id).uris.remove(Seq(uri2)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uris.remove(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).uris.remove(Seq(uri1)).update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
