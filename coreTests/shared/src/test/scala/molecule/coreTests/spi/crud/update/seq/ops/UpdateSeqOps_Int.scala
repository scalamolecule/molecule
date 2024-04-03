package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.intSeq(List(int1, int2, int2)).save.transact.map(_.id)
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).intSeq(List(int3, int4, int4)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int3, int4, int4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).intSeq(List.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        id <- Ns.intSeq(List(int1, int2, int2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).intSeq().update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.intSeq(List(int1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).intSeq.add(int2).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2))

        // Add existing value
        _ <- Ns(id).intSeq.add(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int1))

        // Add multiple values (vararg)
        _ <- Ns(id).intSeq.add(int3, int4).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int1, int3, int4))

        // Add multiple values (Seq)
        _ <- Ns(id).intSeq.add(List(int4, int5)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int1, int3, int4, int4, int5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).intSeq.add(List.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int1, int3, int4, int4, int5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.intSeq(List(
          int1, int2, int3, int4, int5, int6, int7,
          int1, int2, int3, int4, int5, int6, int7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).intSeq.remove(int7).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).intSeq.remove(int9).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).intSeq.remove(int6, int6).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5,
          int1, int2, int3, int4, int5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).intSeq.remove(int4, int5).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3,
          int1, int2, int3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).intSeq.remove(List(int2, int3)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1,
          int1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).intSeq.remove(List.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).intSeq.remove(Seq(int1)).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
