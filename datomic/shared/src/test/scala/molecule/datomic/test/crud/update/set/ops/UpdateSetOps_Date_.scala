// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import java.util.Date
import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.dates(Set(date1, date2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).dates(Set(date3, date4)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date3, date4))

        // Apply Seq of values
        _ <- Ns(eid).dates(Set(date4, date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date4, date5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).dates(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)

        _ <- Ns(eid).dates(Set(date1, date2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).dates().update.transact
        _ <- Ns.dates.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.dates(Set(date1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Add existing value (no effect)
        _ <- Ns(eid).dates.add(date2).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Add multiple values (vararg)
        _ <- Ns(eid).dates.add(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).dates.add(Seq(date4, date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))
        // Set
        _ <- Ns(eid).dates.add(Set(date6)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6))
        // Iterable
        _ <- Ns(eid).dates.add(Iterable(date7)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).dates.add(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date6, date7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).dates.swap(date6 -> date8).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5, date8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).dates.swap(date5 -> date8).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).dates.swap(date3 -> date6, date4 -> date7).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date6, date7, date8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).dates.swap(date4 -> date9).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date6, date7, date8, date9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).dates.swap(Seq(date2 -> date5)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date5, date6, date7, date8, date9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).dates.swap(Seq.empty[(Date, Date)]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date5, date6, date7, date8, date9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).dates.swap(date1 -> date2, date1 -> date3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).dates.swap(date1 -> date3, date2 -> date3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).dates.remove(date6).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).dates.remove(date7).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4, date5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).dates.remove(date5, date5).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2, date3, date4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).dates.remove(date3, date4).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))

        // Retract Seq of values
        _ <- Ns(eid).dates.remove(Seq(date2)).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).dates.remove(Seq.empty[Date]).update.transact
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1))
      } yield ()
    }
  }
}
