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


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).uriSeq(List(uri1, uri2, uri2)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).uriSeq(List(uri2, uri3, uri3)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri2, uri3, uri3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").uriSeq(List(uri3, uri4, uri4)).update.transact
        _ <- Ns.i.s.uriSeq.query.get.map(_.head ==> (42, "foo", List(uri3, uri4, uri4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).uriSeq(List.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).uriSeq(List(uri1, uri2, uri2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).uriSeq().update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).uriSeq.add(uri1).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).uriSeq.add(uri1).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1))

        // Add new value to end of Seq
        _ <- Ns(id).uriSeq.add(uri2).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1, uri2))

        // Add multiple values with varargs
        _ <- Ns(id).uriSeq.add(uri3, uri4).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1, uri2, uri3, uri4))

        // Add multiple values with Iterable
        _ <- Ns(id).uriSeq.add(List(uri4, uri5)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1, uri2, uri3, uri4, uri4, uri5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uriSeq.add(Set.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1, uri2, uri3, uri4, uri4, uri5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).uriSeq.remove(uri1).update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).uriSeq.add(
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
          uri1, uri2, uri3, uri4, uri5, uri6, uri7,
        ).update.transact

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

        // Remove multiple values with vararg
        _ <- Ns(id).uriSeq.remove(uri4, uri5).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1, uri2, uri3,
          uri1, uri2, uri3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).uriSeq.remove(List(uri2, uri3)).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(
          uri1,
          uri1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uriSeq.remove(Vector.empty[URI]).update.transact
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).uriSeq.remove(Set(uri1)).update.transact
        _ <- Ns.uriSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
