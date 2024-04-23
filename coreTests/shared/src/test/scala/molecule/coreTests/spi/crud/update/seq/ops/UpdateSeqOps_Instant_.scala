// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).instantSeq(List(instant1, instant2, instant2)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).instantSeq(List(instant2, instant3, instant3)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant2, instant3, instant3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").instantSeq(List(instant3, instant4, instant4)).update.transact
        _ <- Ns.i.s.instantSeq.query.get.map(_.head ==> (42, "foo", List(instant3, instant4, instant4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).instantSeq(List.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).instantSeq(List(instant1, instant2, instant2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).instantSeq().update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).instantSeq.add(instant1).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).instantSeq.add(instant1).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1))

        // Add new value to end of Seq
        _ <- Ns(id).instantSeq.add(instant2).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1, instant2))

        // Add multiple values with varargs
        _ <- Ns(id).instantSeq.add(instant3, instant4).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1, instant2, instant3, instant4))

        // Add multiple values with Iterable
        _ <- Ns(id).instantSeq.add(List(instant4, instant5)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1, instant2, instant3, instant4, instant4, instant5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).instantSeq.add(Set.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1, instant2, instant3, instant4, instant4, instant5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).instantSeq.remove(instant1).update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).instantSeq.add(
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).instantSeq.remove(instant7).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).instantSeq.remove(instant9).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).instantSeq.remove(instant6, instant6).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3, instant4, instant5,
          instant1, instant2, instant3, instant4, instant5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).instantSeq.remove(instant4, instant5).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1, instant2, instant3,
          instant1, instant2, instant3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).instantSeq.remove(List(instant2, instant3)).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(
          instant1,
          instant1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).instantSeq.remove(Vector.empty[Instant]).update.transact
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).instantSeq.remove(Set(instant1)).update.transact
        _ <- Ns.instantSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
