// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime2)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).localDateTimeSeq(List(localDateTime2, localDateTime3, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime2, localDateTime3, localDateTime3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").localDateTimeSeq(List(localDateTime3, localDateTime4, localDateTime4)).update.transact
        _ <- Ns.i.s.localDateTimeSeq.query.get.map(_.head ==> (42, "foo", List(localDateTime3, localDateTime4, localDateTime4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).localDateTimeSeq(List.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localDateTimeSeq().update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeSeq.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).localDateTimeSeq.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1))

        // Add new value to end of Seq
        _ <- Ns(id).localDateTimeSeq.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1, localDateTime2))

        // Add multiple values with varargs
        _ <- Ns(id).localDateTimeSeq.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1, localDateTime2, localDateTime3, localDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).localDateTimeSeq.add(List(localDateTime4, localDateTime5)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime4, localDateTime5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateTimeSeq.add(Set.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime4, localDateTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime1).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localDateTimeSeq.add(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime9).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime6, localDateTime6).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime4, localDateTime5).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3,
          localDateTime1, localDateTime2, localDateTime3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).localDateTimeSeq.remove(List(localDateTime2, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1,
          localDateTime1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localDateTimeSeq.remove(Vector.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).localDateTimeSeq.remove(Set(localDateTime1)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
