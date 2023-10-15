// GENERATED CODE ********************************
package molecule.coreTests.compliance.crud.update.set.ops

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.doubles(Set(double1, double2)).save.transact.map(_.id)

        _ <- Ns(id).doubles(Set(double3, double4)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double3, double4))

        // Apply Seq of values
        _ <- Ns(id).doubles(Set(double4, double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double4, double5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).doubles(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)

        _ <- Ns(id).doubles(Set(double1, double2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).doubles().update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.doubles(Set(double1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).doubles.add(double2).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Add existing value (no effect)
        _ <- Ns(id).doubles.add(double2).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Add multiple values (vararg)
        _ <- Ns(id).doubles.add(double3, double4).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).doubles.add(Seq(double4, double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))
        // Set
        _ <- Ns(id).doubles.add(Set(double6)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))
        // Iterable
        _ <- Ns(id).doubles.add(Iterable(double7)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6, double7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).doubles.add(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6, double7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).doubles.swap(double6 -> double8).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).doubles.swap(double5 -> double8).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double8))

        // Replace multiple values (vararg)
        _ <- Ns(id).doubles.swap(double3 -> double6, double4 -> double7).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double6, double7, double8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).doubles.swap(double4 -> double9).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double6, double7, double8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).doubles.swap(double4 -> double9).upsert.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double6, double7, double8, double9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).doubles.swap(Seq(double2 -> double5)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double5, double6, double7, double8, double9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).doubles.swap(Seq.empty[(Double, Double)]).update.transact
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
        id <- Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).doubles.remove(double6).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Removing non-existing value has no effect
        _ <- Ns(id).doubles.remove(double7).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).doubles.remove(double5, double5).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Remove multiple values (vararg)
        _ <- Ns(id).doubles.remove(double3, double4).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))

        // Remove Seq of values
        _ <- Ns(id).doubles.remove(Seq(double2)).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).doubles.remove(Seq.empty[Double]).update.transact
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).doubles.remove(Seq(double1)).update.transact
        _ <- Ns.doubles.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
