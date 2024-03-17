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
        id <- Ns.uriSet(Set(uri1, uri2)).save.transact.map(_.id)
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).uriSet(Set(uri3, uri4)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri3, uri4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).uriSet(Set.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)

        id <- Ns.uriSet(Set(uri1, uri2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).uriSet().update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uriSet(Set(uri1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).uriSet.add(uri2).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).uriSet.add(uri2).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))

        // Add multiple values (vararg)
        _ <- Ns(id).uriSet.add(uri3, uri4).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Add multiple values (Seq)
        _ <- Ns(id).uriSet.add(Seq(uri5, uri6)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).uriSet.add(Seq.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uriSet(Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).uriSet.remove(uri7).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Removing non-existing value has no effect
        _ <- Ns(id).uriSet.remove(uri9).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).uriSet.remove(uri6, uri6).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Remove multiple values (vararg)
        _ <- Ns(id).uriSet.remove(uri4, uri5).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3))

        // Remove multiple values (Seq)
        _ <- Ns(id).uriSet.remove(Seq(uri2, uri3)).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uriSet.remove(Seq.empty[URI]).update.transact
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).uriSet.remove(Seq(uri1)).update.transact
        _ <- Ns.uriSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
