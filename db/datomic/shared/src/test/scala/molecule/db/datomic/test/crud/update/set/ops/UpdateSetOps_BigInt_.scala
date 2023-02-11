// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.bigInts(Set(bigInt1, bigInt2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).bigInts(Set(bigInt3, bigInt4)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt3, bigInt4))

        // Apply Seq of values
        _ <- Ns(eid).bigInts(Set(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt4, bigInt5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).bigInts(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)

        _ <- Ns(eid).bigInts(Set(bigInt1, bigInt2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).bigInts().update.transact
        _ <- Ns.bigInts.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.bigInts(Set(bigInt1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).bigInts.add(bigInt2).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add existing value (no effect)
        _ <- Ns(eid).bigInts.add(bigInt2).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Add multiple values (vararg)
        _ <- Ns(eid).bigInts.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).bigInts.add(Seq(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))
        // Set
        _ <- Ns(eid).bigInts.add(Set(bigInt6)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6))
        // Iterable
        _ <- Ns(eid).bigInts.add(Iterable(bigInt7)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).bigInts.add(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.bigInts(Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).bigInts.swap(bigInt6 -> bigInt8).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).bigInts.swap(bigInt5 -> bigInt8).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).bigInts.swap(bigInt3 -> bigInt6, bigInt4 -> bigInt7).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt6, bigInt7, bigInt8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).bigInts.swap(bigInt4 -> bigInt9).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt6, bigInt7, bigInt8, bigInt9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).bigInts.swap(Seq(bigInt2 -> bigInt5)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt5, bigInt6, bigInt7, bigInt8, bigInt9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).bigInts.swap(Seq.empty[(BigInt, BigInt)]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt5, bigInt6, bigInt7, bigInt8, bigInt9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).bigInts.swap(bigInt1 -> bigInt2, bigInt1 -> bigInt3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).bigInts.swap(bigInt1 -> bigInt3, bigInt2 -> bigInt3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.bigInts(Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).bigInts.remove(bigInt6).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).bigInts.remove(bigInt7).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4, bigInt5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).bigInts.remove(bigInt5, bigInt5).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3, bigInt4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).bigInts.remove(bigInt3, bigInt4).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))

        // Retract Seq of values
        _ <- Ns(eid).bigInts.remove(Seq(bigInt2)).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).bigInts.remove(Seq.empty[BigInt]).update.transact
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1))
      } yield ()
    }
  }
}
