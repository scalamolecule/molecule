// GENERATED CODE ********************************
package molecule.datalog.datomic.test.crud.update.set.ops

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Char_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.chars(Set(char1, char2)).save.transact.map(_.eid)

        _ <- Ns(eid).chars(Set(char3, char4)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char3, char4))

        // Apply Seq of values
        _ <- Ns(eid).chars(Set(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char4, char5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).chars(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)

        _ <- Ns(eid).chars(Set(char1, char2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).chars().update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.chars(Set(char1)).save.transact.map(_.eid)

        // Add value
        _ <- Ns(eid).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add existing value (no effect)
        _ <- Ns(eid).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add multiple values (vararg)
        _ <- Ns(eid).chars.add(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).chars.add(Seq(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))
        // Set
        _ <- Ns(eid).chars.add(Set(char6)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))
        // Iterable
        _ <- Ns(eid).chars.add(Iterable(char7)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).chars.add(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.map(_.eid)

        // Replace value
        _ <- Ns(eid).chars.swap(char6 -> char8).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).chars.swap(char5 -> char8).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).chars.swap(char3 -> char6, char4 -> char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char6, char7, char8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).chars.swap(char4 -> char9).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char6, char7, char8, char9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).chars.swap(Seq(char2 -> char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char5, char6, char7, char8, char9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).chars.swap(Seq.empty[(Char, Char)]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char5, char6, char7, char8, char9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).chars.swap(char1 -> char2, char1 -> char3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).chars.swap(char1 -> char3, char2 -> char3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.map(_.eid)

        // Remove value
        _ <- Ns(eid).chars.remove(char6).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing non-existing value has no effect
        _ <- Ns(eid).chars.remove(char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(eid).chars.remove(char5, char5).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Remove multiple values (vararg)
        _ <- Ns(eid).chars.remove(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Remove Seq of values
        _ <- Ns(eid).chars.remove(Seq(char2)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing empty Seq of values has no effect
        _ <- Ns(eid).chars.remove(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(eid).chars.remove(Seq(char1)).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
