// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localTimeSet(Set(localTime1, localTime2)).save.transact.map(_.id)
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localTimeSet(Set(localTime3, localTime4)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime3, localTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).localTimeSet(Set.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)

        id <- Ns.localTimeSet(Set(localTime1, localTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).localTimeSet().update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localTimeSet(Set(localTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localTimeSet.add(localTime2).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).localTimeSet.add(localTime2).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).localTimeSet.add(localTime3, localTime4).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).localTimeSet.add(Seq(localTime5, localTime6)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localTimeSet.add(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localTimeSet(Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6, localTime7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localTimeSet.remove(localTime7).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localTimeSet.remove(localTime9).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5, localTime6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localTimeSet.remove(localTime6, localTime6).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3, localTime4, localTime5))

        // Remove multiple values (vararg)
        _ <- Ns(id).localTimeSet.remove(localTime4, localTime5).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3))

        // Remove multiple values (Seq)
        _ <- Ns(id).localTimeSet.remove(Seq(localTime2, localTime3)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localTimeSet.remove(Seq.empty[LocalTime]).update.transact
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localTimeSet.remove(Seq(localTime1)).update.transact
        _ <- Ns.localTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
