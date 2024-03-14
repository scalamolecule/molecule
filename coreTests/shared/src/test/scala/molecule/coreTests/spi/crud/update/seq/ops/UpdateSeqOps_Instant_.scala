// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.instantSeq(List(instant1, instant2, instant2)).save.transact.map(_.id)
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).instantSeq(List(instant3, instant4, instant4)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant3, instant4, instant4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).instantSeq(List.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        id <- Ns.instantSeq(List(instant1, instant2, instant2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).instantSeq().update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.instantSeq(List(instant1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).instantSeq.add(instant2).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2))

        // Add existing value
        _ <- Ns(id).instantSeq.add(instant1).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant1))

        // Add multiple values (vararg)
        _ <- Ns(id).instantSeq.add(instant3, instant4).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant1, instant3, instant4))

        // Add multiple values (Seq)
        _ <- Ns(id).instantSeq.add(List(instant4, instant5)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant1, instant3, instant4, instant4, instant5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).instantSeq.add(List.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant1, instant3, instant4, instant4, instant5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.instantSeq(List(
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).instantSeq.remove(instant7).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).instantSeq.remove(instant9).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).instantSeq.remove(instant6, instant6).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5,
          instant1, instant2, instant3, instant4, instant5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).instantSeq.remove(instant4, instant5).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3,
          instant1, instant2, instant3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).instantSeq.remove(List(instant2, instant3)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1,
          instant1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).instantSeq.remove(List.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).instantSeq.remove(Seq(instant1)).update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
