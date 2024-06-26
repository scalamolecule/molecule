// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime2)).save.transact.map(_.id)
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).offsetDateTimeSeq(List(offsetDateTime3, offsetDateTime4, offsetDateTime4)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime3, offsetDateTime4, offsetDateTime4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).offsetDateTimeSeq(List.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)

        id <- Ns.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).offsetDateTimeSeq().update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSeq(List(offsetDateTime1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))

        // Add existing value
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetDateTimeSeq.add(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime1, offsetDateTime3, offsetDateTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).offsetDateTimeSeq.add(List(offsetDateTime4, offsetDateTime5)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime1, offsetDateTime3, offsetDateTime4, offsetDateTime4, offsetDateTime5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).offsetDateTimeSeq.add(List.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime1, offsetDateTime3, offsetDateTime4, offsetDateTime4, offsetDateTime5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSeq(List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime9).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime6, offsetDateTime6).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5,
          offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetDateTimeSeq.remove(offsetDateTime4, offsetDateTime5).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1, offsetDateTime2, offsetDateTime3,
          offsetDateTime1, offsetDateTime2, offsetDateTime3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).offsetDateTimeSeq.remove(List(offsetDateTime2, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(
          offsetDateTime1,
          offsetDateTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetDateTimeSeq.remove(List.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetDateTimeSeq.remove(Seq(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
