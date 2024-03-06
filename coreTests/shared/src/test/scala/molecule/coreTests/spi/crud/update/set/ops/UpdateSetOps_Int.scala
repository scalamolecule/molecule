package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1, int2)).save.transact.map(_.id)

        _ <- Ns(id).ints(Set(int3, int4)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int3, int4))

        // Apply Seq of values
        _ <- Ns(id).ints(Set(int4, int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int4, int5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).ints(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)

        _ <- Ns(id).ints(Set(int1, int2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).ints().update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add existing value (no effect)
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values (vararg)
        _ <- Ns(id).ints.add(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).ints.add(Seq(int4, int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))
        // Set
        _ <- Ns(id).ints.add(Set(int6)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
        // Iterable
        _ <- Ns(id).ints.add(Iterable(int7)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).ints.add(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).ints.remove(int6).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Removing non-existing value has no effect
        _ <- Ns(id).ints.remove(int7).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).ints.remove(int5, int5).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Remove multiple values (vararg)
        _ <- Ns(id).ints.remove(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Remove Seq of values
        _ <- Ns(id).ints.remove(Seq(int2)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).ints.remove(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).ints.remove(Seq(int1)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
