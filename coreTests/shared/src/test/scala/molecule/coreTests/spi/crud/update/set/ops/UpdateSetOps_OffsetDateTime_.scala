// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetDateTime
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
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).offsetDateTimes(Set(offsetDateTime3, offsetDateTime4)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime3, offsetDateTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).offsetDateTimes(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_ ==> Nil)

        id <- Ns.offsetDateTimes(Set(offsetDateTime1, offsetDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
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

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimes.add(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7))
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

        // Remove Iterable of values
        _ <- Ns(id).offsetDateTimes.remove(Seq(offsetDateTime2)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).offsetDateTimes.remove(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).offsetDateTimes.remove(Seq(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
