// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_LocalDate_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateArray(Array(localDate1, localDate2, localDate2)).save.transact.map(_.id)
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).localDateArray(Array(localDate3, localDate4, localDate4)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate3, localDate4, localDate4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).localDateArray(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDateArray.query.get.map(_ ==> Nil)

        id <- Ns.localDateArray(Array(localDate1, localDate2, localDate2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).localDateArray().update.transact
        _ <- Ns.localDateArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateArray(Array(localDate1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).localDateArray.add(localDate2).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2))

        // Add existing value
        _ <- Ns(id).localDateArray.add(localDate1).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateArray.add(localDate3, localDate4).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1, localDate3, localDate4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).localDateArray.add(Seq(localDate4, localDate5)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5))
        // Array
        _ <- Ns(id).localDateArray.add(Array(localDate6)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5, localDate6))
        // Iterable
        _ <- Ns(id).localDateArray.add(Iterable(localDate7)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5, localDate6, localDate7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateArray.add(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate2, localDate1, localDate3, localDate4, localDate4, localDate5, localDate6, localDate7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateArray(Array(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).localDateArray.remove(localDate7).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateArray.remove(localDate9).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateArray.remove(localDate6, localDate6).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1, localDate2, localDate3, localDate4, localDate5,
          localDate1, localDate2, localDate3, localDate4, localDate5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateArray.remove(localDate4, localDate5).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1, localDate2, localDate3,
          localDate1, localDate2, localDate3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).localDateArray.remove(Array(localDate3)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1, localDate2,
          localDate1, localDate2,
        ))

        _ <- Ns(id).localDateArray.remove(Seq(localDate2)).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(
          localDate1,
          localDate1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateArray.remove(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDateArray.query.get.map(_.head ==> Array(localDate1, localDate1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).localDateArray.remove(Seq(localDate1)).update.transact
        _ <- Ns.localDateArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
