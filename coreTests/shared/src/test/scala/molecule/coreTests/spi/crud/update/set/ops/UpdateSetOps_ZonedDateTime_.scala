// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).save.transact.map(_.id)
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).zonedDateTimes(Set(zonedDateTime3, zonedDateTime4)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime3, zonedDateTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).zonedDateTimes(Set.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)

        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).zonedDateTimes().update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).zonedDateTimes.add(zonedDateTime3, zonedDateTime4).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).zonedDateTimes.add(Seq(zonedDateTime5, zonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).zonedDateTimes.add(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6, zonedDateTime7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime7).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime9).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5, zonedDateTime6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime6, zonedDateTime6).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4, zonedDateTime5))

        // Remove multiple values (vararg)
        _ <- Ns(id).zonedDateTimes.remove(zonedDateTime4, zonedDateTime5).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))

        // Remove multiple values (Seq)
        _ <- Ns(id).zonedDateTimes.remove(Seq(zonedDateTime2, zonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).zonedDateTimes.remove(Seq.empty[ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).zonedDateTimes.remove(Seq(zonedDateTime1)).update.transact
        _ <- Ns.zonedDateTimes.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
