package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).intSet(Set(int1, int2)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).intSet(Set(int2, int3)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int2, int3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").intSet(Set(int3, int4)).update.transact
        _ <- Ns.i.s.intSet.query.get.map(_.head ==> (42, "foo", Set(int3, int4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).intSet(Set.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).intSet(Set(int1, int2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).intSet().update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).intSet.add(int1).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).intSet.add(int1).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Add new value
        _ <- Ns(id).intSet.add(int2).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values with vararg
        _ <- Ns(id).intSet.add(int3, int4).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add multiple values with Iterable
        _ <- Ns(id).intSet.add(List(int5, int6)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).intSet.add(Set.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).intSet.remove(int1).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).intSet.add(int1, int2, int3, int4, int5, int6, int7).update.transact

        // Remove value
        _ <- Ns(id).intSet.remove(int7).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing non-existing value has no effect
        _ <- Ns(id).intSet.remove(int9).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).intSet.remove(int6, int6).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Remove multiple values with varargs
        _ <- Ns(id).intSet.remove(int4, int5).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3))

        // Remove multiple values with Iterable
        _ <- Ns(id).intSet.remove(List(int2, int3)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).intSet.remove(Vector.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).intSet.remove(Set(int1)).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
