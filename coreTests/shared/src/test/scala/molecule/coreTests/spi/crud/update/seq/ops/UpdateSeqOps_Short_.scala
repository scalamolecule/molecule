// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).shortSeq(List(short1, short2, short2)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).shortSeq(List(short2, short3, short3)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short2, short3, short3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").shortSeq(List(short3, short4, short4)).update.transact
        _ <- Ns.i.s.shortSeq.query.get.map(_.head ==> (42, "foo", List(short3, short4, short4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).shortSeq(List.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).shortSeq(List(short1, short2, short2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).shortSeq().update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).shortSeq.add(short1).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).shortSeq.add(short1).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1))

        // Add new value to end of Seq
        _ <- Ns(id).shortSeq.add(short2).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1, short2))

        // Add multiple values with varargs
        _ <- Ns(id).shortSeq.add(short3, short4).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1, short2, short3, short4))

        // Add multiple values with Iterable
        _ <- Ns(id).shortSeq.add(List(short4, short5)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1, short2, short3, short4, short4, short5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).shortSeq.add(Set.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1, short2, short3, short4, short4, short5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).shortSeq.remove(short1).update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).shortSeq.add(
          short1, short2, short3, short4, short5, short6, short7,
          short1, short2, short3, short4, short5, short6, short7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).shortSeq.remove(short7).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2, short3, short4, short5, short6,
          short1, short2, short3, short4, short5, short6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).shortSeq.remove(short9).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2, short3, short4, short5, short6,
          short1, short2, short3, short4, short5, short6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).shortSeq.remove(short6, short6).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2, short3, short4, short5,
          short1, short2, short3, short4, short5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).shortSeq.remove(short4, short5).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2, short3,
          short1, short2, short3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).shortSeq.remove(List(short2, short3)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1,
          short1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).shortSeq.remove(Vector.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).shortSeq.remove(Set(short1)).update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
