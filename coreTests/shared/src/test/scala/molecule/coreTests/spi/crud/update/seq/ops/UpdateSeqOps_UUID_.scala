// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).uuidSeq(List(uuid1, uuid2, uuid2)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).uuidSeq(List(uuid2, uuid3, uuid3)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid2, uuid3, uuid3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").uuidSeq(List(uuid3, uuid4, uuid4)).update.transact
        _ <- Ns.i.s.uuidSeq.query.get.map(_.head ==> (42, "foo", List(uuid3, uuid4, uuid4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).uuidSeq(List.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).uuidSeq(List(uuid1, uuid2, uuid2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).uuidSeq().update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).uuidSeq.add(uuid1).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).uuidSeq.add(uuid1).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1))

        // Add new value to end of Seq
        _ <- Ns(id).uuidSeq.add(uuid2).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1, uuid2))

        // Add multiple values with varargs
        _ <- Ns(id).uuidSeq.add(uuid3, uuid4).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1, uuid2, uuid3, uuid4))

        // Add multiple values with Iterable
        _ <- Ns(id).uuidSeq.add(List(uuid4, uuid5)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1, uuid2, uuid3, uuid4, uuid4, uuid5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uuidSeq.add(Set.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1, uuid2, uuid3, uuid4, uuid4, uuid5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).uuidSeq.remove(uuid1).update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).uuidSeq.add(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).uuidSeq.remove(uuid7).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).uuidSeq.remove(uuid9).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).uuidSeq.remove(uuid6, uuid6).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3, uuid4, uuid5,
          uuid1, uuid2, uuid3, uuid4, uuid5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).uuidSeq.remove(uuid4, uuid5).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1, uuid2, uuid3,
          uuid1, uuid2, uuid3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).uuidSeq.remove(List(uuid2, uuid3)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(
          uuid1,
          uuid1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).uuidSeq.remove(Vector.empty[UUID]).update.transact
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).uuidSeq.remove(Set(uuid1)).update.transact
        _ <- Ns.uuidSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
