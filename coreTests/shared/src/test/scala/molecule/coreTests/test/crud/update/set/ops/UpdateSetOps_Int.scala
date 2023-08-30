package molecule.coreTests.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Int extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

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


    "swap" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).ints.swap(int6 -> int8).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).ints.swap(int5 -> int8).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int8))

        // Replace multiple values (vararg)
        _ <- Ns(id).ints.swap(int3 -> int6, int4 -> int7).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int6, int7, int8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).ints.swap(int4 -> int9).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int6, int7, int8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).ints.swap(int4 -> int9).upsert.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int6, int7, int8, int9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).ints.swap(Seq(int2 -> int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int5, int6, int7, int8, int9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).ints.swap(Seq.empty[(Int, Int)]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int5, int6, int7, int8, int9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).ints.swap(int1 -> int2, int1 -> int3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).ints.swap(int1 -> int3, int2 -> int3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
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

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).ints.remove(Seq(int1)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
