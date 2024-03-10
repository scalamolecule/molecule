// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Double_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.doubleArray(Array(double1, double2, double2)).save.transact.map(_.id)
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).doubleArray(Array(double3, double4, double4)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double3, double4, double4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).doubleArray(Seq.empty[Double]).update.transact
        _ <- Ns.doubleArray.query.get.map(_ ==> Nil)

        id <- Ns.doubleArray(Array(double1, double2, double2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).doubleArray().update.transact
        _ <- Ns.doubleArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.doubleArray(Array(double1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).doubleArray.add(double2).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2))

        // Add existing value
        _ <- Ns(id).doubleArray.add(double1).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1))

        // Add multiple values (vararg)
        _ <- Ns(id).doubleArray.add(double3, double4).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1, double3, double4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).doubleArray.add(Seq(double4, double5)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1, double3, double4, double4, double5))
        // Array
        _ <- Ns(id).doubleArray.add(Array(double6)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1, double3, double4, double4, double5, double6))
        // Iterable
        _ <- Ns(id).doubleArray.add(Iterable(double7)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1, double3, double4, double4, double5, double6, double7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).doubleArray.add(Seq.empty[Double]).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double2, double1, double3, double4, double4, double5, double6, double7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.doubleArray(Array(
          double1, double2, double3, double4, double5, double6, double7,
          double1, double2, double3, double4, double5, double6, double7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).doubleArray.remove(double7).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1, double2, double3, double4, double5, double6,
          double1, double2, double3, double4, double5, double6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).doubleArray.remove(double9).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1, double2, double3, double4, double5, double6,
          double1, double2, double3, double4, double5, double6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).doubleArray.remove(double6, double6).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1, double2, double3, double4, double5,
          double1, double2, double3, double4, double5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).doubleArray.remove(double4, double5).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1, double2, double3,
          double1, double2, double3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).doubleArray.remove(Array(double3)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1, double2,
          double1, double2,
        ))

        _ <- Ns(id).doubleArray.remove(Seq(double2)).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(
          double1,
          double1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).doubleArray.remove(Seq.empty[Double]).update.transact
        _ <- Ns.doubleArray.query.get.map(_.head ==> Array(double1, double1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).doubleArray.remove(Seq(double1)).update.transact
        _ <- Ns.doubleArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
