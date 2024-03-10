// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_URI_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uriArray(Array(uri1, uri2, uri2)).save.transact.map(_.id)
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).uriArray(Array(uri3, uri4, uri4)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri3, uri4, uri4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).uriArray(Seq.empty[URI]).update.transact
        _ <- Ns.uriArray.query.get.map(_ ==> Nil)

        id <- Ns.uriArray(Array(uri1, uri2, uri2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).uriArray().update.transact
        _ <- Ns.uriArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uriArray(Array(uri1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).uriArray.add(uri2).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2))

        // Add existing value
        _ <- Ns(id).uriArray.add(uri1).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1))

        // Add multiple values (vararg)
        _ <- Ns(id).uriArray.add(uri3, uri4).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1, uri3, uri4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).uriArray.add(Seq(uri4, uri5)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1, uri3, uri4, uri4, uri5))
        // Array
        _ <- Ns(id).uriArray.add(Array(uri6)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1, uri3, uri4, uri4, uri5, uri6))
        // Iterable
        _ <- Ns(id).uriArray.add(Iterable(uri7)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1, uri3, uri4, uri4, uri5, uri6, uri7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uriArray.add(Seq.empty[URI]).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri2, uri1, uri3, uri4, uri4, uri5, uri6, uri7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uriArray(Array(
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).uriArray.remove(uri7).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1, uri2, uri3, uri4, uri5, uri6,
          uri1, uri2, uri3, uri4, uri5, uri6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).uriArray.remove(uri9).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1, uri2, uri3, uri4, uri5, uri6,
          uri1, uri2, uri3, uri4, uri5, uri6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).uriArray.remove(uri6, uri6).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1, uri2, uri3, uri4, uri5,
          uri1, uri2, uri3, uri4, uri5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).uriArray.remove(uri4, uri5).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1, uri2, uri3,
          uri1, uri2, uri3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).uriArray.remove(Array(uri3)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1, uri2,
          uri1, uri2,
        ))

        _ <- Ns(id).uriArray.remove(Seq(uri2)).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(
          uri1,
          uri1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uriArray.remove(Seq.empty[URI]).update.transact
        _ <- Ns.uriArray.query.get.map(_.head ==> Array(uri1, uri1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).uriArray.remove(Seq(uri1)).update.transact
        _ <- Ns.uriArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
