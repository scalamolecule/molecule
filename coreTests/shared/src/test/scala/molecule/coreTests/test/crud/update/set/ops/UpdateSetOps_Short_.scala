// GENERATED CODE ********************************
package molecule.coreTests.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Short_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.shorts(Set(short1, short2)).save.transact.map(_.eid)

        _ <- Ns(eid).shorts(Set(short3, short4)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short3, short4))

        // Apply Seq of values
        _ <- Ns(eid).shorts(Set(short4, short5)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short4, short5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).shorts(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)

        _ <- Ns(eid).shorts(Set(short1, short2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).shorts().update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.shorts(Set(short1)).save.transact.map(_.eid)

        // Add value
        _ <- Ns(eid).shorts.add(short2).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Add existing value (no effect)
        _ <- Ns(eid).shorts.add(short2).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Add multiple values (vararg)
        _ <- Ns(eid).shorts.add(short3, short4).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).shorts.add(Seq(short4, short5)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))
        // Set
        _ <- Ns(eid).shorts.add(Set(short6)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))
        // Iterable
        _ <- Ns(eid).shorts.add(Iterable(short7)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6, short7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).shorts.add(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6, short7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.shorts(Set(short1, short2, short3, short4, short5, short6)).save.transact.map(_.eid)

        // Replace value
        _ <- Ns(eid).shorts.swap(short6 -> short8).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).shorts.swap(short5 -> short8).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).shorts.swap(short3 -> short6, short4 -> short7).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short6, short7, short8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).shorts.swap(short4 -> short9).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short6, short7, short8, short9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).shorts.swap(Seq(short2 -> short5)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short5, short6, short7, short8, short9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).shorts.swap(Seq.empty[(Short, Short)]).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short5, short6, short7, short8, short9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).shorts.swap(short1 -> short2, short1 -> short3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).shorts.swap(short1 -> short3, short2 -> short3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.shorts(Set(short1, short2, short3, short4, short5, short6)).save.transact.map(_.eid)

        // Remove value
        _ <- Ns(eid).shorts.remove(short6).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Removing non-existing value has no effect
        _ <- Ns(eid).shorts.remove(short7).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(eid).shorts.remove(short5, short5).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Remove multiple values (vararg)
        _ <- Ns(eid).shorts.remove(short3, short4).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Remove Seq of values
        _ <- Ns(eid).shorts.remove(Seq(short2)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1))

        // Removing empty Seq of values has no effect
        _ <- Ns(eid).shorts.remove(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(eid).shorts.remove(Seq(short1)).update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
