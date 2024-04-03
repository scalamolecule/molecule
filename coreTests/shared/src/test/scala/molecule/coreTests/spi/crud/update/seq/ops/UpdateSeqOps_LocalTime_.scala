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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localTimeSeq(List(localTime1, localTime2, localTime2)).save.transact.map(_.id)
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).localTimeSeq(List(localTime3, localTime4, localTime4)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime3, localTime4, localTime4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).localTimeSeq(List.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)

        id <- Ns.localTimeSeq(List(localTime1, localTime2, localTime2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).localTimeSeq().update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localTimeSeq(List(localTime1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).localTimeSeq.add(localTime2).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))

        // Add existing value
        _ <- Ns(id).localTimeSeq.add(localTime1).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).localTimeSeq.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime1, localTime3, localTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).localTimeSeq.add(List(localTime4, localTime5)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localTimeSeq.add(List.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime1, localTime3, localTime4, localTime4, localTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localTimeSeq(List(
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
          localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).localTimeSeq.remove(localTime4, localTime5).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1, localTime2, localTime3,
          localTime1, localTime2, localTime3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).localTimeSeq.remove(List(localTime2, localTime3)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(
          localTime1,
          localTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localTimeSeq.remove(List.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localTimeSeq.remove(Seq(localTime1)).update.transact
        _ <- Ns.localTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
