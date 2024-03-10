// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1, date2)).save.transact.map(_.id)
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).dates(Set(date3, date4)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date3, date4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).dates(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)

        id <- Ns.dates(Set(date1, date2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).dates().update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Add multiple values (vararg)
        _ <- Ns(id).dates.add(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).dates.add(Seq(date4, date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))
        // Set
        _ <- Ns(id).dates.add(Set(date6)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))
        // Iterable
        _ <- Ns(id).dates.add(Iterable(date7)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).dates.add(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).dates.remove(date6).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Removing non-existing value has no effect
        _ <- Ns(id).dates.remove(date7).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).dates.remove(date5, date5).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Remove multiple values (vararg)
        _ <- Ns(id).dates.remove(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Remove Iterable of values
        _ <- Ns(id).dates.remove(Seq(date2)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).dates.remove(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).dates.remove(Seq(date1)).update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
