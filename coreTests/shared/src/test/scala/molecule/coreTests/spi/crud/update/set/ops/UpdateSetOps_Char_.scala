// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1, char2)).save.transact.map(_.id)

        _ <- Ns(id).chars(Set(char3, char4)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char3, char4))

        // Apply Seq of values
        _ <- Ns(id).chars(Set(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char4, char5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).chars(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)

        _ <- Ns(id).chars(Set(char1, char2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).chars().update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add existing value (no effect)
        _ <- Ns(id).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add multiple values (vararg)
        _ <- Ns(id).chars.add(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).chars.add(Seq(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))
        // Set
        _ <- Ns(id).chars.add(Set(char6)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))
        // Iterable
        _ <- Ns(id).chars.add(Iterable(char7)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).chars.add(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).chars.swap(char6 -> char8).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).chars.swap(char5 -> char8).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char8))

        // Replace multiple values (vararg)
        _ <- Ns(id).chars.swap(char3 -> char6, char4 -> char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char6, char7, char8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).chars.swap(char4 -> char9).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char6, char7, char8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).chars.swap(char4 -> char9).upsert.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char6, char7, char8, char9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).chars.swap(Seq(char2 -> char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char5, char6, char7, char8, char9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).chars.swap(Seq.empty[(Char, Char)]).update.transact
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
        id <- Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).chars.remove(char6).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing non-existing value has no effect
        _ <- Ns(id).chars.remove(char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).chars.remove(char5, char5).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Remove multiple values (vararg)
        _ <- Ns(id).chars.remove(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Remove Seq of values
        _ <- Ns(id).chars.remove(Seq(char2)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).chars.remove(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).chars.remove(Seq(char1)).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
