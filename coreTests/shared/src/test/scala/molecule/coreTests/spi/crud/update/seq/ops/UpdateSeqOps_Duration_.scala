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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.durationSeq(List(duration1, duration2, duration2)).save.transact.map(_.id)
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).durationSeq(List(duration3, duration4, duration4)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration3, duration4, duration4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).durationSeq(List.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)

        id <- Ns.durationSeq(List(duration1, duration2, duration2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).durationSeq().update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.durationSeq(List(duration1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).durationSeq.add(duration2).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2))

        // Add existing value
        _ <- Ns(id).durationSeq.add(duration1).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1))

        // Add multiple values (vararg)
        _ <- Ns(id).durationSeq.add(duration3, duration4).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1, duration3, duration4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).durationSeq.add(Seq(duration4, duration5)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1, duration3, duration4, duration4, duration5))
        // Array
        _ <- Ns(id).durationSeq.add(List(duration6)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6))
        // Iterable
        _ <- Ns(id).durationSeq.add(Iterable(duration7)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6, duration7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).durationSeq.add(List.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration1, duration3, duration4, duration4, duration5, duration6, duration7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durationSeq(List(
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
          duration1, duration2, duration3, duration4, duration5, duration6, duration7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).durationSeq.remove(duration4, duration5).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2, duration3,
          duration1, duration2, duration3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).durationSeq.remove(List(duration3)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1, duration2,
          duration1, duration2,
        ))

        _ <- Ns(id).durationSeq.remove(Seq(duration2)).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(
          duration1,
          duration1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).durationSeq.remove(List.empty[Duration]).update.transact
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).durationSeq.remove(Seq(duration1)).update.transact
        _ <- Ns.durationSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
