// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigIntSeq(List(bigInt1, bigInt2, bigInt2)).save.transact.map(_.id)
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).bigIntSeq(List(bigInt3, bigInt4, bigInt4)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt3, bigInt4, bigInt4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).bigIntSeq(List.empty[BigInt]).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_ ==> Nil)

        id <- Ns.bigIntSeq(List(bigInt1, bigInt2, bigInt2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).bigIntSeq().update.transact
        _ <- Ns.bigIntSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigIntSeq(List(bigInt1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).bigIntSeq.add(bigInt2).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))

        // Add existing value
        _ <- Ns(id).bigIntSeq.add(bigInt1).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1))

        // Add multiple values (vararg)
        _ <- Ns(id).bigIntSeq.add(bigInt3, bigInt4).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).bigIntSeq.add(Seq(bigInt4, bigInt5)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5))
        // Array
        _ <- Ns(id).bigIntSeq.add(List(bigInt6)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6))
        // Iterable
        _ <- Ns(id).bigIntSeq.add(Iterable(bigInt7)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6, bigInt7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigIntSeq.add(List.empty[BigInt]).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt1, bigInt3, bigInt4, bigInt4, bigInt5, bigInt6, bigInt7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigIntSeq(List(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).bigIntSeq.remove(bigInt7).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigIntSeq.remove(bigInt9).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).bigIntSeq.remove(bigInt6, bigInt6).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5,
          bigInt1, bigInt2, bigInt3, bigInt4, bigInt5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).bigIntSeq.remove(bigInt4, bigInt5).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1, bigInt2, bigInt3,
          bigInt1, bigInt2, bigInt3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).bigIntSeq.remove(List(bigInt3)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1, bigInt2,
          bigInt1, bigInt2,
        ))

        _ <- Ns(id).bigIntSeq.remove(Seq(bigInt2)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(
          bigInt1,
          bigInt1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).bigIntSeq.remove(List.empty[BigInt]).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).bigIntSeq.remove(Seq(bigInt1)).update.transact
        _ <- Ns.bigIntSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
