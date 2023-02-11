// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.strings(Set(string1, string2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).strings(Set(string3, string4)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string3, string4))

        // Apply Seq of values
        _ <- Ns(eid).strings(Set(string4, string5)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string4, string5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).strings(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_ ==> Nil)

        _ <- Ns(eid).strings(Set(string1, string2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).strings().update.transact
        _ <- Ns.strings.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.strings(Set(string1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).strings.add(string2).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Add existing value (no effect)
        _ <- Ns(eid).strings.add(string2).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Add multiple values (vararg)
        _ <- Ns(eid).strings.add(string3, string4).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).strings.add(Seq(string4, string5)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))
        // Set
        _ <- Ns(eid).strings.add(Set(string6)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))
        // Iterable
        _ <- Ns(eid).strings.add(Iterable(string7)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6, string7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).strings.add(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6, string7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.strings(Set(string1, string2, string3, string4, string5, string6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).strings.swap(string6 -> string8).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).strings.swap(string5 -> string8).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).strings.swap(string3 -> string6, string4 -> string7).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string6, string7, string8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).strings.swap(string4 -> string9).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string6, string7, string8, string9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).strings.swap(Seq(string2 -> string5)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string5, string6, string7, string8, string9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).strings.swap(Seq.empty[(String, String)]).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string5, string6, string7, string8, string9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).strings.swap(string1 -> string2, string1 -> string3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).strings.swap(string1 -> string3, string2 -> string3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.strings(Set(string1, string2, string3, string4, string5, string6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).strings.remove(string6).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).strings.remove(string7).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).strings.remove(string5, string5).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).strings.remove(string3, string4).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Retract Seq of values
        _ <- Ns(eid).strings.remove(Seq(string2)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).strings.remove(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1))
      } yield ()
    }
  }
}
