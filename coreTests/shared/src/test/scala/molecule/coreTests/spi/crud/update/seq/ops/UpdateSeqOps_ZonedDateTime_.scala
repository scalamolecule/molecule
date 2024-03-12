// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime2)).save.transact.map(_.id)
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).zonedDateTimeSeq(List(zonedDateTime3, zonedDateTime4, zonedDateTime4)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime3, zonedDateTime4, zonedDateTime4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).zonedDateTimeSeq(List.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)

        id <- Ns.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).zonedDateTimeSeq().update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeSeq(List(zonedDateTime1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))

        // Add existing value
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1))

        // Add multiple values (vararg)
        _ <- Ns(id).zonedDateTimeSeq.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).zonedDateTimeSeq.add(Seq(zonedDateTime4, zonedDateTime5)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5))
        // Array
        _ <- Ns(id).zonedDateTimeSeq.add(List(zonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6))
        // Iterable
        _ <- Ns(id).zonedDateTimeSeq.add(Iterable(zonedDateTime7)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).zonedDateTimeSeq.add(List.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime1, zonedDateTime3, zonedDateTime4, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeSeq(List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime6, zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
          zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).zonedDateTimeSeq.remove(zonedDateTime4, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
          zonedDateTime1, zonedDateTime2, zonedDateTime3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).zonedDateTimeSeq.remove(List(zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1, zonedDateTime2,
          zonedDateTime1, zonedDateTime2,
        ))

        _ <- Ns(id).zonedDateTimeSeq.remove(Seq(zonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(
          zonedDateTime1,
          zonedDateTime1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).zonedDateTimeSeq.remove(List.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).zonedDateTimeSeq.remove(Seq(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimeSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
