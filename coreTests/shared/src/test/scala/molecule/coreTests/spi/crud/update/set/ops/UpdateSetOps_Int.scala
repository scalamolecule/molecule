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
        id <- Ns.ints(Set(int1, int2)).save.transact.map(_.id)
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).ints(Set(int3, int4)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int3, int4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).ints(Set.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)

        id <- Ns.ints(Set(int1, int2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).ints().update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).ints.add(int2).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values (vararg)
        _ <- Ns(id).ints.add(int3, int4).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add multiple values (Seq)
        _ <- Ns(id).ints.add(Seq(int5, int6)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).ints.add(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(int1, int2, int3, int4, int5, int6, int7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).ints.remove(int7).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing non-existing value has no effect
        _ <- Ns(id).ints.remove(int9).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).ints.remove(int6, int6).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Remove multiple values (vararg)
        _ <- Ns(id).ints.remove(int4, int5).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2, int3))

        // Remove multiple values (Seq)
        _ <- Ns(id).ints.remove(Seq(int2, int3)).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).ints.remove(Seq.empty[Int]).update.transact
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).ints.remove(Seq(int1)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
