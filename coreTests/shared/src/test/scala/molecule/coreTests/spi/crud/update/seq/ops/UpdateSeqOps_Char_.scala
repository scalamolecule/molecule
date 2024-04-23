// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).charSeq(List(char1, char2, char2)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).charSeq(List(char2, char3, char3)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char2, char3, char3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").charSeq(List(char3, char4, char4)).update.transact
        _ <- Ns.i.s.charSeq.query.get.map(_.head ==> (42, "foo", List(char3, char4, char4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).charSeq(List.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).charSeq(List(char1, char2, char2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).charSeq().update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).charSeq.add(char1).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).charSeq.add(char1).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1))

        // Add new value to end of Seq
        _ <- Ns(id).charSeq.add(char2).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1, char2))

        // Add multiple values with varargs
        _ <- Ns(id).charSeq.add(char3, char4).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1, char2, char3, char4))

        // Add multiple values with Iterable
        _ <- Ns(id).charSeq.add(List(char4, char5)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1, char2, char3, char4, char4, char5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).charSeq.add(Set.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1, char2, char3, char4, char4, char5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).charSeq.remove(char1).update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).charSeq.add(
          char1, char2, char3, char4, char5, char6, char7,
          char1, char2, char3, char4, char5, char6, char7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).charSeq.remove(char7).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2, char3, char4, char5, char6,
          char1, char2, char3, char4, char5, char6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).charSeq.remove(char9).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2, char3, char4, char5, char6,
          char1, char2, char3, char4, char5, char6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).charSeq.remove(char6, char6).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2, char3, char4, char5,
          char1, char2, char3, char4, char5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).charSeq.remove(char4, char5).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2, char3,
          char1, char2, char3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).charSeq.remove(List(char2, char3)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1,
          char1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).charSeq.remove(Vector.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).charSeq.remove(Set(char1)).update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
