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

        // Applying Array of values replaces previous Array
        _ <- Ns(id).bigDecimalSeq(List(bigDecimal3, bigDecimal4, bigDecimal4)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal3, bigDecimal4, bigDecimal4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).bigDecimalSeq(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).bigDecimalSeq().update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))

        // Add existing value
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1))

        // Add multiple values (vararg)
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).bigDecimalSeq.add(Seq(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))
        // Array
        _ <- Ns(id).bigDecimalSeq.add(List(bigDecimal6)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6))
        // Iterable
        _ <- Ns(id).bigDecimalSeq.add(Iterable(bigDecimal7)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalSeq.add(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal1, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))
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

        // Remove Iterable of values
        _ <- Ns(id).bigDecimalSeq.remove(List(bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2,
          bigDecimal1, bigDecimal2,
        ))

        _ <- Ns(id).bigDecimalSeq.remove(Seq(bigDecimal2)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1,
          bigDecimal1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigDecimalSeq.remove(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigDecimalSeq.remove(Seq(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
