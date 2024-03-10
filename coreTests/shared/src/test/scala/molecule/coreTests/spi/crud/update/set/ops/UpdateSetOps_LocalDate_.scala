// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1, localDate2)).save.transact.map(_.id)
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localDates(Set(localDate3, localDate4)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate3, localDate4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).localDates(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)

        id <- Ns.localDates(Set(localDate1, localDate2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).localDates().update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localDates.add(localDate2).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).localDates.add(localDate2).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Add multiple values (vararg)
        _ <- Ns(id).localDates.add(localDate3, localDate4).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).localDates.add(Seq(localDate4, localDate5)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))
        // Set
        _ <- Ns(id).localDates.add(Set(localDate6)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))
        // Iterable
        _ <- Ns(id).localDates.add(Iterable(localDate7)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDates.add(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDates(Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localDates.remove(localDate6).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDates.remove(localDate7).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localDates.remove(localDate5, localDate5).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDates.remove(localDate3, localDate4).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Remove Iterable of values
        _ <- Ns(id).localDates.remove(Seq(localDate2)).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localDates.remove(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).localDates.remove(Seq(localDate1)).update.transact
        _ <- Ns.localDates.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
