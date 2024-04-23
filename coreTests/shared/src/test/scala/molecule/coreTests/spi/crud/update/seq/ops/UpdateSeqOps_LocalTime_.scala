// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localTimeSeq(List(localTime1, localTime2, localTime2)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).localTimeSeq(List(localTime2, localTime3, localTime3)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime2, localTime3, localTime3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").localTimeSeq(List(localTime3, localTime4, localTime4)).update.transact
        _ <- Ns.i.s.localTimeSeq.query.get.map(_.head ==> (42, "foo", List(localTime3, localTime4, localTime4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).localTimeSeq(List.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).localTimeSeq(List(localTime1, localTime2, localTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localTimeSeq().update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localTimeSeq.add(localTime1).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).localTimeSeq.add(localTime1).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1))

        // Add new value to end of Seq
        _ <- Ns(id).localTimeSeq.add(localTime2).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1, localTime2))

        // Add multiple values with varargs
        _ <- Ns(id).localTimeSeq.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1, localTime2, localTime3, localTime4))

        // Add multiple values with Iterable
        _ <- Ns(id).localTimeSeq.add(List(localTime4, localTime5)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1, localTime2, localTime3, localTime4, localTime4, localTime5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localTimeSeq.add(Set.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1, localTime2, localTime3, localTime4, localTime4, localTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).localTimeSeq.remove(localTime1).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localTimeSeq.add(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).localTimeSeq.remove(localTime7).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localTimeSeq.remove(localTime9).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localTimeSeq.remove(localTime6, localTime6).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1, localTime2, localTime3, localTime4, localTime5,
          localTime1, localTime2, localTime3, localTime4, localTime5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).localTimeSeq.remove(localTime4, localTime5).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1, localTime2, localTime3,
          localTime1, localTime2, localTime3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).localTimeSeq.remove(List(localTime2, localTime3)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1,
          localTime1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localTimeSeq.remove(Vector.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).localTimeSeq.remove(Set(localTime1)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
