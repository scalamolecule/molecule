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
        _ <- Ns(id).durations(Set.empty[Duration]).update.transact
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

        // Add multiple values (Seq)
        _ <- Ns(id).durations.add(Seq(duration5, duration6)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).durations.add(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durations(Set(duration1, duration2, duration3, duration4, duration5, duration6, duration7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).durations.remove(duration7).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Removing non-existing value has no effect
        _ <- Ns(id).durations.remove(duration9).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5, duration6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).durations.remove(duration6, duration6).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3, duration4, duration5))

        // Remove multiple values (vararg)
        _ <- Ns(id).durations.remove(duration4, duration5).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2, duration3))

        // Remove multiple values (Seq)
        _ <- Ns(id).durations.remove(Seq(duration2, duration3)).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).durations.remove(Seq.empty[Duration]).update.transact
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).durations.remove(Seq(duration1)).update.transact
        _ <- Ns.durations.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
