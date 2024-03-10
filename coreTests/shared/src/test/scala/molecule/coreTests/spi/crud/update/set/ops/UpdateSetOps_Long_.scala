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
        id <- Ns.longs(Set(long1, long2)).save.transact.map(_.id)
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).longs(Set(long3, long4)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long3, long4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).longs(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)

        id <- Ns.longs(Set(long1, long2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).longs().update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.longs(Set(long1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).longs.add(long2).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).longs.add(long2).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Add multiple values (vararg)
        _ <- Ns(id).longs.add(long3, long4).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).longs.add(Seq(long4, long5)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))
        // Set
        _ <- Ns(id).longs.add(Set(long6)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6))
        // Iterable
        _ <- Ns(id).longs.add(Iterable(long7)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6, long7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).longs.add(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5, long6, long7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.longs(Set(long1, long2, long3, long4, long5, long6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).longs.remove(long6).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Removing non-existing value has no effect
        _ <- Ns(id).longs.remove(long7).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4, long5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).longs.remove(long5, long5).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2, long3, long4))

        // Remove multiple values (vararg)
        _ <- Ns(id).longs.remove(long3, long4).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))

        // Remove Iterable of values
        _ <- Ns(id).longs.remove(Seq(long2)).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).longs.remove(Seq.empty[Long]).update.transact
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).longs.remove(Seq(long1)).update.transact
        _ <- Ns.longs.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
