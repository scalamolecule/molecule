// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.doubleSeq(List(double1, double2, double2)).save.transact.map(_.id)
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).doubleSeq(List(double3, double4, double4)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double3, double4, double4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).doubleSeq(List.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        id <- Ns.doubleSeq(List(double1, double2, double2)).save.transact.map(_.id)
        // Applying empty value deletes previous Seq
        _ <- Ns(id).doubleSeq().update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.doubleSeq(List(double1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).doubleSeq.add(double2).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2))

        // Add existing value
        _ <- Ns(id).doubleSeq.add(double1).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double1))

        // Add multiple values (vararg)
        _ <- Ns(id).doubleSeq.add(double3, double4).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double1, double3, double4))

        // Add multiple values (Seq)
        _ <- Ns(id).doubleSeq.add(List(double4, double5)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double1, double3, double4, double4, double5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).doubleSeq.add(List.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double1, double3, double4, double4, double5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.doubleSeq(List(
          double1, double2, double3, double4, double5, double6, double7,
          double1, double2, double3, double4, double5, double6, double7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).doubleSeq.remove(double7).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1, double2, double3, double4, double5, double6,
          double1, double2, double3, double4, double5, double6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).doubleSeq.remove(double9).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1, double2, double3, double4, double5, double6,
          double1, double2, double3, double4, double5, double6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).doubleSeq.remove(double6, double6).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1, double2, double3, double4, double5,
          double1, double2, double3, double4, double5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).doubleSeq.remove(double4, double5).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1, double2, double3,
          double1, double2, double3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).doubleSeq.remove(List(double2, double3)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1,
          double1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).doubleSeq.remove(List.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).doubleSeq.remove(Seq(double1)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
