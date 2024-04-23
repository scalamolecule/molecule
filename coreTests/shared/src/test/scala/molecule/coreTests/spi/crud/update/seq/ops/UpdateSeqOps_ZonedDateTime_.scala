// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).zonedDateTimeSeq(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime2, zonedDateTime3, zonedDateTime3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").zonedDateTimeSeq(List(zonedDateTime3, zonedDateTime4, zonedDateTime4)).update.transact
        _ <- Ns.i.s.zonedDateTimeSeq.query.get.map(_.head ==> (42, "foo", List(zonedDateTime3, zonedDateTime4, zonedDateTime4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).zonedDateTimeSeq(List.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).zonedDateTimeSeq().update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1))

        // Add new value to end of Seq
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1, zonedDateTime2))

        // Add multiple values with varargs
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).zonedDateTimeSeq.add(List(zonedDateTime4, zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeSeq.add(Set.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).zonedDateTimeSeq.add(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime6, zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime4, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).zonedDateTimeSeq.remove(List(zonedDateTime2, zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1,
          zonedDateTime1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeSeq.remove(Vector.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).zonedDateTimeSeq.remove(Set(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
