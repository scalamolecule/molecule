package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.intSet(Set(int1, int2)).save.transact.map(_.id)
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).intSet(Set(int3, int4)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int3, int4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).intSet(Set.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        id <- Ns.intSet(Set(int1, int2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).intSet().update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.intSet(Set(int1)).save.transact.map(_.id)

        // Adding existing value changes nothing
        _ <- Ns(id).intSet.add(int1).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

//        // Update doesn't add value if no Set attribute already exists
//        _ <- Ns(id).iSet.add(1).update.transact
//        _ <- Ns.intSet.iSet_?.query.get.map(_ ==> List((Set(int1), None)))
//
//        // Upsert adds value to new attribute if it didn't already exist
//        _ <- Ns(id).iSet.add(1).upsert.transact
//        _ <- Ns.intSet.iSet_?.query.get.map(_ ==> List((Set(int1), Some(Set(1)))))

        // Add value
        _ <- Ns(id).intSet.add(int2).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).intSet.add(int2).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values (vararg)
        _ <- Ns(id).intSet.add(int3, int4).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add multiple values (Seq)
        _ <- Ns(id).intSet.add(Seq(int5, int6)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).intSet.add(Seq.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.intSet(Set(int1, int2, int3, int4, int5, int6, int7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).intSet.remove(int7).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing non-existing value has no effect
        _ <- Ns(id).intSet.remove(int9).update.transact
        _ <- Ns(id).intSet.remove(int9).upsert.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).intSet.remove(int6, int6).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Remove multiple values (vararg)
        _ <- Ns(id).intSet.remove(int4, int5).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3))

        // Remove multiple values (Seq)
        _ <- Ns(id).intSet.remove(Seq(int2, int3)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).intSet.remove(Seq.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).intSet.remove(Seq(int1)).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
