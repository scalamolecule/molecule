// GENERATED CODE ********************************
package molecule.coreTests.compliance.crud.update.set.ops

import java.time.Instant
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.instants(Set(instant1, instant2)).save.transact.map(_.id)

        _ <- Ns(id).instants(Set(instant3, instant4)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant3, instant4))

        // Apply Seq of values
        _ <- Ns(id).instants(Set(instant4, instant5)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant4, instant5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).instants(Seq.empty[Instant]).update.transact
        _ <- Ns.instants.query.get.map(_ ==> Nil)

        _ <- Ns(id).instants(Set(instant1, instant2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).instants().update.transact
        _ <- Ns.instants.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.instants(Set(instant1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).instants.add(instant2).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2))

        // Add existing value (no effect)
        _ <- Ns(id).instants.add(instant2).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2))

        // Add multiple values (vararg)
        _ <- Ns(id).instants.add(instant3, instant4).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).instants.add(Seq(instant4, instant5)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5))
        // Set
        _ <- Ns(id).instants.add(Set(instant6)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))
        // Iterable
        _ <- Ns(id).instants.add(Iterable(instant7)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6, instant7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).instants.add(Seq.empty[Instant]).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6, instant7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.instants(Set(instant1, instant2, instant3, instant4, instant5, instant6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).instants.swap(instant6 -> instant8).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).instants.swap(instant5 -> instant8).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant8))

        // Replace multiple values (vararg)
        _ <- Ns(id).instants.swap(instant3 -> instant6, instant4 -> instant7).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant6, instant7, instant8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).instants.swap(instant4 -> instant9).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant6, instant7, instant8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).instants.swap(instant4 -> instant9).upsert.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant6, instant7, instant8, instant9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).instants.swap(Seq(instant2 -> instant5)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant5, instant6, instant7, instant8, instant9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).instants.swap(Seq.empty[(Instant, Instant)]).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant5, instant6, instant7, instant8, instant9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).instants.swap(instant1 -> instant2, instant1 -> instant3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).instants.swap(instant1 -> instant3, instant2 -> instant3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.instants(Set(instant1, instant2, instant3, instant4, instant5, instant6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).instants.remove(instant6).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5))

        // Removing non-existing value has no effect
        _ <- Ns(id).instants.remove(instant7).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).instants.remove(instant5, instant5).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4))

        // Remove multiple values (vararg)
        _ <- Ns(id).instants.remove(instant3, instant4).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2))

        // Remove Seq of values
        _ <- Ns(id).instants.remove(Seq(instant2)).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).instants.remove(Seq.empty[Instant]).update.transact
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).instants.remove(Seq(instant1)).update.transact
        _ <- Ns.instants.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
