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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.shortSeq(List(short1, short2, short2)).save.transact.map(_.id)
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).shortSeq(List(short3, short4, short4)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short3, short4, short4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).shortSeq(List.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)

        id <- Ns.shortSeq(List(short1, short2, short2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).shortSeq().update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.shortSeq(List(short1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).shortSeq.add(short2).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2))

        // Add existing value
        _ <- Ns(id).shortSeq.add(short1).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1))

        // Add multiple values (vararg)
        _ <- Ns(id).shortSeq.add(short3, short4).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1, short3, short4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).shortSeq.add(Seq(short4, short5)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1, short3, short4, short4, short5))
        // Array
        _ <- Ns(id).shortSeq.add(List(short6)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1, short3, short4, short4, short5, short6))
        // Iterable
        _ <- Ns(id).shortSeq.add(Iterable(short7)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1, short3, short4, short4, short5, short6, short7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).shortSeq.add(List.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short1, short3, short4, short4, short5, short6, short7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.shortSeq(List(
          short1, short2, short3, short4, short5, short6, short7,
          short1, short2, short3, short4, short5, short6, short7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).shortSeq.remove(short4, short5).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2, short3,
          short1, short2, short3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).shortSeq.remove(List(short3)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1, short2,
          short1, short2,
        ))

        _ <- Ns(id).shortSeq.remove(Seq(short2)).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(
          short1,
          short1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).shortSeq.remove(List.empty[Short]).update.transact
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).shortSeq.remove(Seq(short1)).update.transact
        _ <- Ns.shortSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
