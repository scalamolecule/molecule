// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateSeq(List(localDate1, localDate2, localDate2)).save.transact.map(_.id)
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).localDateSeq(List(localDate3, localDate4, localDate4)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate3, localDate4, localDate4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).localDateSeq(List.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        id <- Ns.localDateSeq(List(localDate1, localDate2, localDate2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).localDateSeq().update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateSeq(List(localDate1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).localDateSeq.add(localDate2).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))

        // Add existing value
        _ <- Ns(id).localDateSeq.add(localDate1).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate1))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateSeq.add(localDate3, localDate4).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate1, localDate3, localDate4))

        // Add multiple values (Seq)
        _ <- Ns(id).localDateSeq.add(List(localDate4, localDate5)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localDateSeq.add(List.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateSeq(List(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).localDateSeq.remove(localDate7).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateSeq.remove(localDate9).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateSeq.remove(localDate6, localDate6).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5,
          localDate1, localDate2, localDate3, localDate4, localDate5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateSeq.remove(localDate4, localDate5).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3,
          localDate1, localDate2, localDate3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).localDateSeq.remove(List(localDate2, localDate3)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1,
          localDate1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateSeq.remove(List.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateSeq.remove(Seq(localDate1)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
