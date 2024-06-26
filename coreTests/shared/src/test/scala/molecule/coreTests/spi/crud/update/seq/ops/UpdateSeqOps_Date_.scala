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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.dateSeq(List(date1, date2, date2)).save.transact.map(_.id)
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).dateSeq(List(date3, date4, date4)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date3, date4, date4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).dateSeq(List.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)

        id <- Ns.dateSeq(List(date1, date2, date2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).dateSeq().update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.dateSeq(List(date1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).dateSeq.add(date2).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2))

        // Add existing value
        _ <- Ns(id).dateSeq.add(date1).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date1))

        // Add multiple values (vararg)
        _ <- Ns(id).dateSeq.add(date3, date4).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date1, date3, date4))

        // Add multiple values (Seq)
        _ <- Ns(id).dateSeq.add(List(date4, date5)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date1, date3, date4, date4, date5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).dateSeq.add(List.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date1, date3, date4, date4, date5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.dateSeq(List(
          date1, date2, date3, date4, date5, date6, date7,
          date1, date2, date3, date4, date5, date6, date7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).dateSeq.remove(date4, date5).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1, date2, date3,
          date1, date2, date3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).dateSeq.remove(List(date2, date3)).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(
          date1,
          date1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).dateSeq.remove(List.empty[Date]).update.transact
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).dateSeq.remove(Seq(date1)).update.transact
        _ <- Ns.dateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
