// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_Long_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.longs(Set(long1, long2)).save.transact.map(_.eid)

        _ <- Ns(eid).longs(Set(long3, long4)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long3, long4))

        // Apply Seq of values
        _ <- Ns(eid).longs(Set(long4, long5)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long4, long5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).longs(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)

        _ <- Ns(eid).longs(Set(long1, long2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).longs().update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.longs(Set(long1)).save.transact.map(_.eid)

        // Add value
        _ <- Ns(eid).longs.add(long2).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Add existing value (no effect)
        _ <- Ns(eid).longs.add(long2).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Add multiple values (vararg)
        _ <- Ns(eid).longs.add(long3, long4).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).longs.add(Seq(long4, long5)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))
        // Set
        _ <- Ns(eid).longs.add(Set(long6)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))
        // Iterable
        _ <- Ns(eid).longs.add(Iterable(long7)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6, long7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).longs.add(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6, long7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.longs(Set(long1, long2, long3, long4, long5, long6)).save.transact.map(_.eid)

        // Replace value
        _ <- Ns(eid).longs.swap(long6 -> long8).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).longs.swap(long5 -> long8).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).longs.swap(long3 -> long6, long4 -> long7).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long6, long7, long8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).longs.swap(long4 -> long9).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long6, long7, long8, long9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).longs.swap(Seq(long2 -> long5)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long5, long6, long7, long8, long9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).longs.swap(Seq.empty[(Long, Long)]).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long5, long6, long7, long8, long9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).longs.swap(long1 -> long2, long1 -> long3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).longs.swap(long1 -> long3, long2 -> long3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.longs(Set(long1, long2, long3, long4, long5, long6)).save.transact.map(_.eid)

        // Remove value
        _ <- Ns(eid).longs.remove(long6).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Removing non-existing value has no effect
        _ <- Ns(eid).longs.remove(long7).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(eid).longs.remove(long5, long5).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Remove multiple values (vararg)
        _ <- Ns(eid).longs.remove(long3, long4).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Remove Seq of values
        _ <- Ns(eid).longs.remove(Seq(long2)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1))

        // Removing empty Seq of values has no effect
        _ <- Ns(eid).longs.remove(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(eid).longs.remove(Seq(long1)).update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
