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
        id <- Ns.dateSet(Set(date1, date2)).save.transact.map(_.id)
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).dateSet(Set(date3, date4)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date3, date4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).dateSet(Set.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)

        id <- Ns.dateSet(Set(date1, date2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).dateSet().update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.dateSet(Set(date1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).dateSet.add(date2).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).dateSet.add(date2).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))

        // Add multiple values (vararg)
        _ <- Ns(id).dateSet.add(date3, date4).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Add multiple values (Seq)
        _ <- Ns(id).dateSet.add(Seq(date5, date6)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).dateSet.add(Seq.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.dateSet(Set(date1, date2, date3, date4, date5, date6, date7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).dateSet.remove(date7).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Removing non-existing value has no effect
        _ <- Ns(id).dateSet.remove(date9).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).dateSet.remove(date6, date6).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Remove multiple values (vararg)
        _ <- Ns(id).dateSet.remove(date4, date5).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2, date3))

        // Remove multiple values (Seq)
        _ <- Ns(id).dateSet.remove(Seq(date2, date3)).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).dateSet.remove(Seq.empty[Date]).update.transact
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).dateSet.remove(Seq(date1)).update.transact
        _ <- Ns.dateSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
