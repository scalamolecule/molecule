// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.longSet(Set(long1, long2)).save.transact.map(_.id)
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).longSet(Set(long3, long4)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long3, long4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).longSet(Set.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)

        id <- Ns.longSet(Set(long1, long2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).longSet().update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.longSet(Set(long1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).longSet.add(long2).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).longSet.add(long2).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))

        // Add multiple values (vararg)
        _ <- Ns(id).longSet.add(long3, long4).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Add multiple values (Seq)
        _ <- Ns(id).longSet.add(Seq(long5, long6)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).longSet.add(Seq.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.longSet(Set(long1, long2, long3, long4, long5, long6, long7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).longSet.remove(long7).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Removing non-existing value has no effect
        _ <- Ns(id).longSet.remove(long9).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).longSet.remove(long6, long6).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Remove multiple values (vararg)
        _ <- Ns(id).longSet.remove(long4, long5).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2, long3))

        // Remove multiple values (Seq)
        _ <- Ns(id).longSet.remove(Seq(long2, long3)).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).longSet.remove(Seq.empty[Long]).update.transact
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).longSet.remove(Seq(long1)).update.transact
        _ <- Ns.longSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
