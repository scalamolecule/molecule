// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2)).save.transact.map(_.id)
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).durations(Set(duration3, duration4)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration3, duration4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).durations(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)

        id <- Ns.durations(Set(duration1, duration2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).durations().update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).durations.add(duration2).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).durations.add(duration2).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Add multiple values (vararg)
        _ <- Ns(id).durations.add(duration3, duration4).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).durations.add(Seq(duration4, duration5)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))
        // Set
        _ <- Ns(id).durations.add(Set(duration6)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))
        // Iterable
        _ <- Ns(id).durations.add(Iterable(duration7)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6, duration7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).durations.add(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6, duration7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2, duration3, duration4, duration5, duration6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).durations.remove(duration6).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Removing non-existing value has no effect
        _ <- Ns(id).durations.remove(duration7).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).durations.remove(duration5, duration5).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4))

        // Remove multiple values (vararg)
        _ <- Ns(id).durations.remove(duration3, duration4).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))

        // Remove Iterable of values
        _ <- Ns(id).durations.remove(Seq(duration2)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).durations.remove(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).durations.remove(Seq(duration1)).update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
