// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).dateSeq(List(date1, date2, date2)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).dateSeq(List(date2, date3, date3)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date2, date3, date3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").dateSeq(List(date3, date4, date4)).update.transact
        _ <- Ns.i.s.dateSeq.query.get.map(_.head ==> (42, "foo", List(date3, date4, date4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).dateSeq(List.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).dateSeq(List(date1, date2, date2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).dateSeq().update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).dateSeq.add(date1).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).dateSeq.add(date1).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1))

        // Add new value to end of Seq
        _ <- Ns(id).dateSeq.add(date2).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1, date2))

        // Add multiple values with varargs
        _ <- Ns(id).dateSeq.add(date3, date4).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1, date2, date3, date4))

        // Add multiple values with Iterable
        _ <- Ns(id).dateSeq.add(List(date4, date5)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1, date2, date3, date4, date4, date5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).dateSeq.add(Set.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1, date2, date3, date4, date4, date5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).dateSeq.remove(date1).update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).dateSeq.add(
          date1, date2, date3, date4, date5, date6, date7,
          date1, date2, date3, date4, date5, date6, date7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).dateSeq.remove(date7).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1, date2, date3, date4, date5, date6,
          date1, date2, date3, date4, date5, date6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).dateSeq.remove(date9).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1, date2, date3, date4, date5, date6,
          date1, date2, date3, date4, date5, date6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).dateSeq.remove(date6, date6).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1, date2, date3, date4, date5,
          date1, date2, date3, date4, date5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).dateSeq.remove(date4, date5).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1, date2, date3,
          date1, date2, date3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).dateSeq.remove(List(date2, date3)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1,
          date1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).dateSeq.remove(Vector.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).dateSeq.remove(Set(date1)).update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
