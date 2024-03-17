// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSet(Set(offsetTime1, offsetTime2)).save.transact.map(_.id)
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).offsetTimeSet(Set(offsetTime3, offsetTime4)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime3, offsetTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).offsetTimeSet(Set.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)

        id <- Ns.offsetTimeSet(Set(offsetTime1, offsetTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).offsetTimeSet().update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSet(Set(offsetTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).offsetTimeSet.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).offsetTimeSet.add(offsetTime2).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetTimeSet.add(offsetTime3, offsetTime4).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).offsetTimeSet.add(Seq(offsetTime5, offsetTime6)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).offsetTimeSet.add(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeSet(Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6, offsetTime7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).offsetTimeSet.remove(offsetTime7).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetTimeSet.remove(offsetTime9).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5, offsetTime6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).offsetTimeSet.remove(offsetTime6, offsetTime6).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4, offsetTime5))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetTimeSet.remove(offsetTime4, offsetTime5).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3))

        // Remove multiple values (Seq)
        _ <- Ns(id).offsetTimeSet.remove(Seq(offsetTime2, offsetTime3)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetTimeSet.remove(Seq.empty[OffsetTime]).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetTimeSet.remove(Seq(offsetTime1)).update.transact
        _ <- Ns.offsetTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
