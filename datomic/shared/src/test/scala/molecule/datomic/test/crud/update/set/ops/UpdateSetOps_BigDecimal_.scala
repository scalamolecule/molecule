// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).bigDecimals(Set(bigDecimal3, bigDecimal4)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal3, bigDecimal4))

        // Apply Seq of values
        _ <- Ns(eid).bigDecimals(Set(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal4, bigDecimal5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).bigDecimals(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)

        _ <- Ns(eid).bigDecimals(Set(bigDecimal1, bigDecimal2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).bigDecimals().update.transact
        _ <- Ns.bigDecimals.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.bigDecimals(Set(bigDecimal1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).bigDecimals.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add existing value (no effect)
        _ <- Ns(eid).bigDecimals.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Add multiple values (vararg)
        _ <- Ns(eid).bigDecimals.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).bigDecimals.add(Seq(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))
        // Set
        _ <- Ns(eid).bigDecimals.add(Set(bigDecimal6)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6))
        // Iterable
        _ <- Ns(eid).bigDecimals.add(Iterable(bigDecimal7)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).bigDecimals.add(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).bigDecimals.swap(bigDecimal6 -> bigDecimal8).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).bigDecimals.swap(bigDecimal5 -> bigDecimal8).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).bigDecimals.swap(bigDecimal3 -> bigDecimal6, bigDecimal4 -> bigDecimal7).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).bigDecimals.swap(bigDecimal4 -> bigDecimal9).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).bigDecimals.swap(Seq(bigDecimal2 -> bigDecimal5)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).bigDecimals.swap(Seq.empty[(BigDecimal, BigDecimal)]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).bigDecimals.swap(bigDecimal1 -> bigDecimal2, bigDecimal1 -> bigDecimal3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).bigDecimals.swap(bigDecimal1 -> bigDecimal3, bigDecimal2 -> bigDecimal3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).bigDecimals.remove(bigDecimal6).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).bigDecimals.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).bigDecimals.remove(bigDecimal5, bigDecimal5).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).bigDecimals.remove(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        // Retract Seq of values
        _ <- Ns(eid).bigDecimals.remove(Seq(bigDecimal2)).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).bigDecimals.remove(Seq.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1))
      } yield ()
    }
  }
}
