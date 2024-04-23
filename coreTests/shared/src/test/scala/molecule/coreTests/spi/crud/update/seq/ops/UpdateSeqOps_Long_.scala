// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).longSeq(List(long1, long2, long2)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).longSeq(List(long2, long3, long3)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long2, long3, long3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").longSeq(List(long3, long4, long4)).update.transact
        _ <- Ns.i.s.longSeq.query.get.map(_.head ==> (42, "foo", List(long3, long4, long4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).longSeq(List.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).longSeq(List(long1, long2, long2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).longSeq().update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).longSeq.add(long1).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).longSeq.add(long1).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1))

        // Add new value to end of Seq
        _ <- Ns(id).longSeq.add(long2).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1, long2))

        // Add multiple values with varargs
        _ <- Ns(id).longSeq.add(long3, long4).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1, long2, long3, long4))

        // Add multiple values with Iterable
        _ <- Ns(id).longSeq.add(List(long4, long5)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1, long2, long3, long4, long4, long5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).longSeq.add(Set.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1, long2, long3, long4, long4, long5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).longSeq.remove(long1).update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).longSeq.add(
          long1, long2, long3, long4, long5, long6, long7,
          long1, long2, long3, long4, long5, long6, long7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).longSeq.remove(long7).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).longSeq.remove(long9).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).longSeq.remove(long6, long6).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5,
          long1, long2, long3, long4, long5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).longSeq.remove(long4, long5).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3,
          long1, long2, long3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).longSeq.remove(List(long2, long3)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1,
          long1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).longSeq.remove(Vector.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).longSeq.remove(Set(long1)).update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
