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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime2)).save.transact.map(_.id)
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).offsetTimeSeq(List(offsetTime3, offsetTime4, offsetTime4)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime3, offsetTime4, offsetTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).offsetTimeSeq(List.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)

        id <- Ns.offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).offsetTimeSeq().update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSeq(List(offsetTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).offsetTimeSeq.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))

        // Add existing value
        _ <- Ns(id).offsetTimeSeq.add(offsetTime1).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetTimeSeq.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).offsetTimeSeq.add(List(offsetTime4, offsetTime5)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).offsetTimeSeq.add(List.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime1, offsetTime3, offsetTime4, offsetTime4, offsetTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSeq(List(
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
          offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetTimeSeq.remove(offsetTime4, offsetTime5).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1, offsetTime2, offsetTime3,
          offsetTime1, offsetTime2, offsetTime3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).offsetTimeSeq.remove(List(offsetTime2, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(
          offsetTime1,
          offsetTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetTimeSeq.remove(List.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetTimeSeq.remove(Seq(offsetTime1)).update.transact
        _ <- Ns.offsetTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
