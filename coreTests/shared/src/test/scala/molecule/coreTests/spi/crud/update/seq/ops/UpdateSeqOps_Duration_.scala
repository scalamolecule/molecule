// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).durationSeq(List(duration1, duration2, duration2)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).durationSeq(List(duration2, duration3, duration3)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration2, duration3, duration3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").durationSeq(List(duration3, duration4, duration4)).update.transact
        _ <- Ns.i.s.durationSeq.query.get.map(_.head ==> (42, "foo", List(duration3, duration4, duration4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).durationSeq(List.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).durationSeq(List(duration1, duration2, duration2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).durationSeq().update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).durationSeq.add(duration1).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).durationSeq.add(duration1).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1))

        // Add new value to end of Seq
        _ <- Ns(id).durationSeq.add(duration2).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1, duration2))

        // Add multiple values with varargs
        _ <- Ns(id).durationSeq.add(duration3, duration4).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1, duration2, duration3, duration4))

        // Add multiple values with Iterable
        _ <- Ns(id).durationSeq.add(List(duration4, duration5)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1, duration2, duration3, duration4, duration4, duration5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).durationSeq.add(Set.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1, duration2, duration3, duration4, duration4, duration5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).durationSeq.remove(duration1).update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).durationSeq.add(
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).durationSeq.remove(duration7).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2, duration3, duration4, duration5, duration6,
          duration1, duration2, duration3, duration4, duration5, duration6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).durationSeq.remove(duration9).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2, duration3, duration4, duration5, duration6,
          duration1, duration2, duration3, duration4, duration5, duration6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).durationSeq.remove(duration6, duration6).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2, duration3, duration4, duration5,
          duration1, duration2, duration3, duration4, duration5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).durationSeq.remove(duration4, duration5).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2, duration3,
          duration1, duration2, duration3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).durationSeq.remove(List(duration2, duration3)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1,
          duration1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).durationSeq.remove(Vector.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).durationSeq.remove(Set(duration1)).update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
