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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.charSeq(List(char1, char2, char2)).save.transact.map(_.id)
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).charSeq(List(char3, char4, char4)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char3, char4, char4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).charSeq(List.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)

        id <- Ns.charSeq(List(char1, char2, char2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).charSeq().update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.charSeq(List(char1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).charSeq.add(char2).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2))

        // Add existing value
        _ <- Ns(id).charSeq.add(char1).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1))

        // Add multiple values (vararg)
        _ <- Ns(id).charSeq.add(char3, char4).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1, char3, char4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).charSeq.add(Seq(char4, char5)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1, char3, char4, char4, char5))
        // Array
        _ <- Ns(id).charSeq.add(List(char6)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1, char3, char4, char4, char5, char6))
        // Iterable
        _ <- Ns(id).charSeq.add(Iterable(char7)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1, char3, char4, char4, char5, char6, char7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).charSeq.add(List.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char1, char3, char4, char4, char5, char6, char7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.charSeq(List(
          char1, char2, char3, char4, char5, char6, char7,
          char1, char2, char3, char4, char5, char6, char7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).charSeq.remove(char4, char5).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2, char3,
          char1, char2, char3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).charSeq.remove(List(char3)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1, char2,
          char1, char2,
        ))

        _ <- Ns(id).charSeq.remove(Seq(char2)).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(
          char1,
          char1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).charSeq.remove(List.empty[Char]).update.transact
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).charSeq.remove(Seq(char1)).update.transact
        _ <- Ns.charSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
