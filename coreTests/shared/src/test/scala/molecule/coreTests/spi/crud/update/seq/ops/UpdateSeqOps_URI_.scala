// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uriSeq(List(uri1, uri2, uri2)).save.transact.map(_.id)
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).uriSeq(List(uri3, uri4, uri4)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri3, uri4, uri4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).uriSeq(List.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        id <- Ns.uriSeq(List(uri1, uri2, uri2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).uriSeq().update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uriSeq(List(uri1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).uriSeq.add(uri2).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2))

        // Add existing value
        _ <- Ns(id).uriSeq.add(uri1).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri1))

        // Add multiple values (vararg)
        _ <- Ns(id).uriSeq.add(uri3, uri4).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri1, uri3, uri4))

        // Add multiple values (Seq)
        _ <- Ns(id).uriSeq.add(List(uri4, uri5)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri1, uri3, uri4, uri4, uri5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).uriSeq.add(List.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri1, uri3, uri4, uri4, uri5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uriSeq(List(
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).uriSeq.remove(uri7).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1, uri2, uri3, uri4, uri5, uri6,
          uri1, uri2, uri3, uri4, uri5, uri6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).uriSeq.remove(uri9).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1, uri2, uri3, uri4, uri5, uri6,
          uri1, uri2, uri3, uri4, uri5, uri6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).uriSeq.remove(uri6, uri6).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1, uri2, uri3, uri4, uri5,
          uri1, uri2, uri3, uri4, uri5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).uriSeq.remove(uri4, uri5).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1, uri2, uri3,
          uri1, uri2, uri3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).uriSeq.remove(List(uri2, uri3)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1,
          uri1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uriSeq.remove(List.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).uriSeq.remove(Seq(uri1)).update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
