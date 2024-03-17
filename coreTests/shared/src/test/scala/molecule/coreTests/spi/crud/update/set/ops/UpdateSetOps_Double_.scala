// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

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
        id <- Ns.doubleSet(Set(double1, double2)).save.transact.map(_.id)
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).doubleSet(Set(double3, double4)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double3, double4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).doubleSet(Set.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)

        id <- Ns.doubleSet(Set(double1, double2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).doubleSet().update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.doubleSet(Set(double1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).doubleSet.add(double2).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).doubleSet.add(double2).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))

        // Add multiple values (vararg)
        _ <- Ns(id).doubleSet.add(double3, double4).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4))

        // Add multiple values (Seq)
        _ <- Ns(id).doubleSet.add(Seq(double5, double6)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).doubleSet.add(Seq.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.doubleSet(Set(double1, double2, double3, double4, double5, double6, double7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).doubleSet.remove(double7).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Removing non-existing value has no effect
        _ <- Ns(id).doubleSet.remove(double9).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5, double6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).doubleSet.remove(double6, double6).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3, double4, double5))

        // Remove multiple values (vararg)
        _ <- Ns(id).doubleSet.remove(double4, double5).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3))

        // Remove multiple values (Seq)
        _ <- Ns(id).doubleSet.remove(Seq(double2, double3)).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).doubleSet.remove(Seq.empty[Double]).update.transact
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).doubleSet.remove(Seq(double1)).update.transact
        _ <- Ns.doubleSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
