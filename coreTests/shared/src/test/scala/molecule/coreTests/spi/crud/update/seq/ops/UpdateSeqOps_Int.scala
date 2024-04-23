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


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        //
//        // Applying Seq of values to non-asserted Seq attribute adds the attribute (upsert semantics)
//        _ <- Ns(id).intSeq(List(int1, int2, int2)).intSeq_.update.transact
//        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int2))

        // Applying Seq of values to non-asserted Seq attribute adds the attribute (upsert semantics)
//        _ <- Ns(id).intSeq(List(int1, int2, int2)).update.transact
        _ <- Ns(id).intSeq(List(int1, int2, int2)).upsert.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).intSeq(List(int2, int3, int3)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int2, int3, int3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").intSeq(List(int3, int4, int4)).update.transact
        _ <- Ns.i.s.intSeq.query.get.map(_.head ==> (42, "foo", List(int3, int4, int4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).intSeq(List.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).intSeq(List(int1, int2, int2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).intSeq().update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).intSeq.add(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).intSeq.add(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1))

        // Add new value to end of Seq
        _ <- Ns(id).intSeq.add(int2).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2))

        // Add multiple values with varargs
        _ <- Ns(id).intSeq.add(int3, int4).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4))

        // Add multiple values with Iterable
        _ <- Ns(id).intSeq.add(List(int4, int5)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).intSeq.add(Set.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).intSeq.remove(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).intSeq.add(
          int1, int2, int3, int4, int5, int6, int7,
          int1, int2, int3, int4, int5, int6, int7,
        ).update.transact

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

        // Remove multiple values with vararg
        _ <- Ns(id).intSeq.remove(int4, int5).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3,
          int1, int2, int3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).intSeq.remove(List(int2, int3)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1,
          int1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).intSeq.remove(Vector.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).intSeq.remove(Set(int1)).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
