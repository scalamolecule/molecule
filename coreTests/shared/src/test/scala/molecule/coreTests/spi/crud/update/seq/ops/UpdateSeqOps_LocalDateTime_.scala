// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime2)).save.transact.map(_.id)
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).localDateTimeSeq(List(localDateTime3, localDateTime4, localDateTime4)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime3, localDateTime4, localDateTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).localDateTimeSeq(List.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)

        id <- Ns.localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).localDateTimeSeq().update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSeq(List(localDateTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).localDateTimeSeq.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))

        // Add existing value
        _ <- Ns(id).localDateTimeSeq.add(localDateTime1).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateTimeSeq.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).localDateTimeSeq.add(List(localDateTime4, localDateTime5)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localDateTimeSeq.add(List.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime1, localDateTime3, localDateTime4, localDateTime4, localDateTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSeq(List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime9).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime6, localDateTime6).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
          localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateTimeSeq.remove(localDateTime4, localDateTime5).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1, localDateTime2, localDateTime3,
          localDateTime1, localDateTime2, localDateTime3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).localDateTimeSeq.remove(List(localDateTime2, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(
          localDateTime1,
          localDateTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateTimeSeq.remove(List.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateTimeSeq.remove(Seq(localDateTime1)).update.transact
        _ <- Ns.localDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
