// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal2)).save.transact.map(_.id)
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).bigDecimalSeq(List(bigDecimal3, bigDecimal4, bigDecimal4)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal3, bigDecimal4, bigDecimal4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).bigDecimalSeq(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).bigDecimalSeq().update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))

        // Add existing value
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1))

        // Add multiple values (vararg)
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4))

        // Add multiple values (Seq)
        _ <- Ns(id).bigDecimalSeq.add(List(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).bigDecimalSeq.add(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSeq(List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal9).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal6, bigDecimal6).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal4, bigDecimal5).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3,
          bigDecimal1, bigDecimal2, bigDecimal3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).bigDecimalSeq.remove(List(bigDecimal2, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1,
          bigDecimal1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigDecimalSeq.remove(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).bigDecimalSeq.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
