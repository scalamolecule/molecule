// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime2)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).offsetTimeSeq(List(offsetTime2, offsetTime3, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime2, offsetTime3, offsetTime3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").offsetTimeSeq(List(offsetTime3, offsetTime4, offsetTime4)).update.transact
        _ <- Ns.i.s.offsetTimeSeq.query.get.map(_.head ==> (42, "foo", List(offsetTime3, offsetTime4, offsetTime4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).offsetTimeSeq(List.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).offsetTimeSeq().update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeSeq.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).offsetTimeSeq.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1))

        // Add new value to end of Seq
        _ <- Ns(id).offsetTimeSeq.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1, offsetTime2))

        // Add multiple values with varargs
        _ <- Ns(id).offsetTimeSeq.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1, offsetTime2, offsetTime3, offsetTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).offsetTimeSeq.add(List(offsetTime4, offsetTime5)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime4, offsetTime5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetTimeSeq.add(Set.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime4, offsetTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime1).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).offsetTimeSeq.add(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime7).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime9).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime6, offsetTime6).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime4, offsetTime5).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1, offsetTime2, offsetTime3,
          offsetTime1, offsetTime2, offsetTime3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).offsetTimeSeq.remove(List(offsetTime2, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1,
          offsetTime1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).offsetTimeSeq.remove(Vector.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).offsetTimeSeq.remove(Set(offsetTime1)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
