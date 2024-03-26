// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.longSeq(List(long1, long2, long2)).save.transact.map(_.id)
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).longSeq(List(long3, long4, long4)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long3, long4, long4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).longSeq(List.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)

        id <- Ns.longSeq(List(long1, long2, long2)).save.transact.map(_.id)
        // Applying empty value deletes previous Seq
        _ <- Ns(id).longSeq().update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.longSeq(List(long1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).longSeq.add(long2).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2))

        // Add existing value
        _ <- Ns(id).longSeq.add(long1).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long1))

        // Add multiple values (vararg)
        _ <- Ns(id).longSeq.add(long3, long4).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long1, long3, long4))

        // Add multiple values (Seq)
        _ <- Ns(id).longSeq.add(List(long4, long5)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long1, long3, long4, long4, long5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).longSeq.add(List.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long1, long3, long4, long4, long5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.longSeq(List(
          long1, long2, long3, long4, long5, long6, long7,
          long1, long2, long3, long4, long5, long6, long7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).longSeq.remove(long7).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).longSeq.remove(long9).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5, long6,
          long1, long2, long3, long4, long5, long6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).longSeq.remove(long6, long6).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3, long4, long5,
          long1, long2, long3, long4, long5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).longSeq.remove(long4, long5).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1, long2, long3,
          long1, long2, long3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).longSeq.remove(List(long2, long3)).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(
          long1,
          long1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).longSeq.remove(List.empty[Long]).update.transact
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).longSeq.remove(Seq(long1)).update.transact
        _ <- Ns.longSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
