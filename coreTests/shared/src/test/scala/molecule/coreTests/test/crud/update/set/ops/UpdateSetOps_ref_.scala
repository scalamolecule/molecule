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

trait UpdateSetOps_ref_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2)).save.transact.map(_.id)

        _ <- Ns(id).refs(Set(ref3, ref4)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref3, ref4))

        // Apply Seq of values
        _ <- Ns(id).refs(Set(ref4, ref5)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref4, ref5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).refs(Seq.empty[Long]).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)

        _ <- Ns(id).refs(Set(ref1, ref2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).refs().update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).refs.add(ref2).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Add existing value (no effect)
        _ <- Ns(id).refs.add(ref2).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Add multiple values (vararg)
        _ <- Ns(id).refs.add(ref3, ref4).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).refs.add(Seq(ref4, ref5)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))
        // Set
        _ <- Ns(id).refs.add(Set(ref6)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))
        // Iterable
        _ <- Ns(id).refs.add(Iterable(ref7)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).refs.add(Seq.empty[Long]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).refs.swap(ref6 -> ref8).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).refs.swap(ref5 -> ref8).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref8))

        // Replace multiple values (vararg)
        _ <- Ns(id).refs.swap(ref3 -> ref6, ref4 -> ref7).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref6, ref7, ref8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).refs.swap(ref4 -> ref9).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref6, ref7, ref8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).refs.swap(ref4 -> ref9).upsert.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref6, ref7, ref8, ref9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).refs.swap(Seq(ref2 -> ref5)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref5, ref6, ref7, ref8, ref9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).refs.swap(Seq.empty[(Long, Long)]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref5, ref6, ref7, ref8, ref9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).refs.swap(ref1 -> ref2, ref1 -> ref3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).refs.swap(ref1 -> ref3, ref2 -> ref3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).refs.remove(ref6).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))

        // Removing non-existing value has no effect
        _ <- Ns(id).refs.remove(ref7).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).refs.remove(ref5, ref5).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4))

        // Remove multiple values (vararg)
        _ <- Ns(id).refs.remove(ref3, ref4).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Remove Seq of values
        _ <- Ns(id).refs.remove(Seq(ref2)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).refs.remove(Seq.empty[Long]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).refs.remove(Seq(ref1)).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
