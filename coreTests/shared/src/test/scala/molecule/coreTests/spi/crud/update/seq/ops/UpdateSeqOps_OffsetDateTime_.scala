// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime2)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).offsetDateTimeSeq(List(offsetDateTime2, offsetDateTime3, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime2, offsetDateTime3, offsetDateTime3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").offsetDateTimeSeq(List(offsetDateTime3, offsetDateTime4, offsetDateTime4)).update.transact
        _ <- Ns.i.s.offsetDateTimeSeq.query.get.map(_.head ==> (42, "foo", List(offsetDateTime3, offsetDateTime4, offsetDateTime4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).offsetDateTimeSeq(List.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).offsetDateTimeSeq().update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1))

        // Add new value to end of Seq
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1, offsetDateTime2))

        // Add multiple values with varargs
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).offsetDateTimeSeq.add(List(offsetDateTime4, offsetDateTime5)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime4, offsetDateTime5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimeSeq.add(Set.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime4, offsetDateTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).offsetDateTimeSeq.add(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime9).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime6, offsetDateTime6).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime4, offsetDateTime5).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3,
          offsetDateTime1, offsetDateTime2, offsetDateTime3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).offsetDateTimeSeq.remove(List(offsetDateTime2, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1,
          offsetDateTime1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimeSeq.remove(Vector.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).offsetDateTimeSeq.remove(Set(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
