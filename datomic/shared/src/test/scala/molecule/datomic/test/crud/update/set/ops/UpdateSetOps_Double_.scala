// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_Double_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.doubles(Set(double1, double2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).doubles(Set(double3, double4)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double3, double4))

        // Apply Seq of values
        _ <- Ns(eid).doubles(Set(double4, double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double4, double5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).doubles(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)

        _ <- Ns(eid).doubles(Set(double1, double2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).doubles().update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.doubles(Set(double1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).doubles.add(double2).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Add existing value (no effect)
        _ <- Ns(eid).doubles.add(double2).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Add multiple values (vararg)
        _ <- Ns(eid).doubles.add(double3, double4).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).doubles.add(Seq(double4, double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))
        // Set
        _ <- Ns(eid).doubles.add(Set(double6)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))
        // Iterable
        _ <- Ns(eid).doubles.add(Iterable(double7)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6, double7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).doubles.add(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6, double7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).doubles.swap(double6 -> double8).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).doubles.swap(double5 -> double8).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).doubles.swap(double3 -> double6, double4 -> double7).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double6, double7, double8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).doubles.swap(double4 -> double9).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double6, double7, double8, double9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).doubles.swap(Seq(double2 -> double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double5, double6, double7, double8, double9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).doubles.swap(Seq.empty[(Double, Double)]).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double5, double6, double7, double8, double9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).doubles.swap(double1 -> double2, double1 -> double3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).doubles.swap(double1 -> double3, double2 -> double3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.map(_.eids.head)

        // Remove value
        _ <- Ns(eid).doubles.remove(double6).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Removing non-existing value has no effect
        _ <- Ns(eid).doubles.remove(double7).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(eid).doubles.remove(double5, double5).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Remove multiple values (vararg)
        _ <- Ns(eid).doubles.remove(double3, double4).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Remove Seq of values
        _ <- Ns(eid).doubles.remove(Seq(double2)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1))

        // Removing empty Seq of values has no effect
        _ <- Ns(eid).doubles.remove(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(eid).doubles.remove(Seq(double1)).update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
