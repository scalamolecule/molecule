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


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).doubleSeq(List(double1, double2, double2)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).doubleSeq(List(double2, double3, double3)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double2, double3, double3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").doubleSeq(List(double3, double4, double4)).update.transact
        _ <- Ns.i.s.doubleSeq.query.get.map(_.head ==> (42, "foo", List(double3, double4, double4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).doubleSeq(List.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).doubleSeq(List(double1, double2, double2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).doubleSeq().update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).doubleSeq.add(double1).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).doubleSeq.add(double1).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1))

        // Add new value to end of Seq
        _ <- Ns(id).doubleSeq.add(double2).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1, double2))

        // Add multiple values with varargs
        _ <- Ns(id).doubleSeq.add(double3, double4).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1, double2, double3, double4))

        // Add multiple values with Iterable
        _ <- Ns(id).doubleSeq.add(List(double4, double5)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1, double2, double3, double4, double4, double5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).doubleSeq.add(Set.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1, double2, double3, double4, double4, double5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).doubleSeq.remove(double1).update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).doubleSeq.add(
          double1, double2, double3, double4, double5, double6, double7,
          double1, double2, double3, double4, double5, double6, double7,
        ).update.transact

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

        // Remove multiple values with vararg
        _ <- Ns(id).doubleSeq.remove(double4, double5).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1, double2, double3,
          double1, double2, double3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).doubleSeq.remove(List(double2, double3)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(
          double1,
          double1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).doubleSeq.remove(Vector.empty[Double]).update.transact
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).doubleSeq.remove(Set(double1)).update.transact
        _ <- Ns.doubleSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
