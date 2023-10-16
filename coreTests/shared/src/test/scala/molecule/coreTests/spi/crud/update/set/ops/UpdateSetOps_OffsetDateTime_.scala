// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetDateTime
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimes(Set(offsetDateTime1, offsetDateTime2)).save.transact.map(_.id)

        _ <- Ns(id).offsetDateTimes(Set(offsetDateTime3, offsetDateTime4)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime3, offsetDateTime4))

        // Apply Seq of values
        _ <- Ns(id).offsetDateTimes(Set(offsetDateTime4, offsetDateTime5)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime4, offsetDateTime5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).offsetDateTimes(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetDateTimes(Set(offsetDateTime1, offsetDateTime2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).offsetDateTimes().update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimes(Set(offsetDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).offsetDateTimes.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Add existing value (no effect)
        _ <- Ns(id).offsetDateTimes.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetDateTimes.add(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).offsetDateTimes.add(Seq(offsetDateTime4, offsetDateTime5)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5))
        // Set
        _ <- Ns(id).offsetDateTimes.add(Set(offsetDateTime6)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))
        // Iterable
        _ <- Ns(id).offsetDateTimes.add(Iterable(offsetDateTime7)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).offsetDateTimes.add(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimes(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).offsetDateTimes.swap(offsetDateTime6 -> offsetDateTime8).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(id).offsetDateTimes.swap(offsetDateTime5 -> offsetDateTime8).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime8))

        // Replace multiple values (vararg)
        _ <- Ns(id).offsetDateTimes.swap(offsetDateTime3 -> offsetDateTime6, offsetDateTime4 -> offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime6, offsetDateTime7, offsetDateTime8))

        // Updating missing old value (null) has no effect
        _ <- Ns(id).offsetDateTimes.swap(offsetDateTime4 -> offsetDateTime9).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime6, offsetDateTime7, offsetDateTime8))

        // Upserting missing old value (null) inserts the new value
        _ <- Ns(id).offsetDateTimes.swap(offsetDateTime4 -> offsetDateTime9).upsert.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime6, offsetDateTime7, offsetDateTime8, offsetDateTime9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(id).offsetDateTimes.swap(Seq(offsetDateTime2 -> offsetDateTime5)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime5, offsetDateTime6, offsetDateTime7, offsetDateTime8, offsetDateTime9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(id).offsetDateTimes.swap(Seq.empty[(OffsetDateTime, OffsetDateTime)]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime5, offsetDateTime6, offsetDateTime7, offsetDateTime8, offsetDateTime9))

        // Can't swap duplicate from/to values
        _ <- Ns(42).offsetDateTimes.swap(offsetDateTime1 -> offsetDateTime2, offsetDateTime1 -> offsetDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap from duplicate retract values."
          }

        _ <- Ns(42).offsetDateTimes.swap(offsetDateTime1 -> offsetDateTime3, offsetDateTime2 -> offsetDateTime3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can't swap to duplicate replacement values."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimes(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).offsetDateTimes.remove(offsetDateTime6).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetDateTimes.remove(offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).offsetDateTimes.remove(offsetDateTime5, offsetDateTime5).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetDateTimes.remove(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Remove Seq of values
        _ <- Ns(id).offsetDateTimes.remove(Seq(offsetDateTime2)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetDateTimes.remove(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing all elements is like deleting the attribute
        _ <- Ns(id).offsetDateTimes.remove(Seq(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
