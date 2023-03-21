package molecule.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.ints(Set(int1, int2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).ints(Set(int3, int4)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int3, int4))

        // Apply Seq of values
        _ <- Ns(eid).ints(Set(int4, int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int4, int5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).ints(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)

        _ <- Ns(eid).ints(Set(int1, int2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).ints().update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.ints(Set(int1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add existing value (no effect)
        _ <- Ns(eid).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values (vararg)
        _ <- Ns(eid).ints.add(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).ints.add(Seq(int4, int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))
        // Set
        _ <- Ns(eid).ints.add(Set(int6)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
        // Iterable
        _ <- Ns(eid).ints.add(Iterable(int7)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).ints.add(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6, int7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).ints.swap(int6 -> int8).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).ints.swap(int5 -> int8).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).ints.swap(int3 -> int6, int4 -> int7).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int6, int7, int8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).ints.swap(int4 -> int9).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int6, int7, int8, int9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).ints.swap(Seq(int2 -> int5)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int5, int6, int7, int8, int9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).ints.swap(Seq.empty[(Int, Int)]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int5, int6, int7, int8, int9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).ints.swap(int1 -> int2, int1 -> int3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).ints.swap(int1 -> int3, int2 -> int3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).ints.remove(int6).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).ints.remove(int7).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).ints.remove(int5, int5).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).ints.remove(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Retract Seq of values
        _ <- Ns(eid).ints.remove(Seq(int2)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).ints.remove(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))
      } yield ()
    }
  }
}
