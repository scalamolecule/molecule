// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import molecule.base.error.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_Byte_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.bytes(Set(byte1, byte2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).bytes(Set(byte3, byte4)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte3, byte4))

        // Apply Seq of values
        _ <- Ns(eid).bytes(Set(byte4, byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte4, byte5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).bytes(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)

        _ <- Ns(eid).bytes(Set(byte1, byte2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).bytes().update.transact
        _ <- Ns.bytes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.bytes(Set(byte1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).bytes.add(byte2).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Add existing value (no effect)
        _ <- Ns(eid).bytes.add(byte2).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Add multiple values (vararg)
        _ <- Ns(eid).bytes.add(byte3, byte4).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).bytes.add(Seq(byte4, byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))
        // Set
        _ <- Ns(eid).bytes.add(Set(byte6)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6))
        // Iterable
        _ <- Ns(eid).bytes.add(Iterable(byte7)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).bytes.add(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).bytes.swap(byte6 -> byte8).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).bytes.swap(byte5 -> byte8).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).bytes.swap(byte3 -> byte6, byte4 -> byte7).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte6, byte7, byte8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).bytes.swap(byte4 -> byte9).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte6, byte7, byte8, byte9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).bytes.swap(Seq(byte2 -> byte5)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).bytes.swap(Seq.empty[(Byte, Byte)]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).bytes.swap(byte1 -> byte2, byte1 -> byte3).update.transact.expect { case ExecutionError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).bytes.swap(byte1 -> byte3, byte2 -> byte3).update.transact.expect { case ExecutionError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).bytes.remove(byte6).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).bytes.remove(byte7).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4, byte5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).bytes.remove(byte5, byte5).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2, byte3, byte4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).bytes.remove(byte3, byte4).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))

        // Retract Seq of values
        _ <- Ns(eid).bytes.remove(Seq(byte2)).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).bytes.remove(Seq.empty[Byte]).update.transact
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1))
      } yield ()
    }
  }
}
