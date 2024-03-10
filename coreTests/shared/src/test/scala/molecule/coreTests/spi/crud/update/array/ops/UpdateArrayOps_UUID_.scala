// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_UUID_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uuidArray(Array(uuid1, uuid2, uuid2)).save.transact.map(_.id)
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).uuidArray(Array(uuid3, uuid4, uuid4)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid3, uuid4, uuid4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).uuidArray(Seq.empty[UUID]).update.transact
        _ <- Ns.uuidArray.query.get.map(_ ==> Nil)

        id <- Ns.uuidArray(Array(uuid1, uuid2, uuid2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).uuidArray().update.transact
        _ <- Ns.uuidArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uuidArray(Array(uuid1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).uuidArray.add(uuid2).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2))

        // Add existing value
        _ <- Ns(id).uuidArray.add(uuid1).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1))

        // Add multiple values (vararg)
        _ <- Ns(id).uuidArray.add(uuid3, uuid4).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1, uuid3, uuid4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).uuidArray.add(Seq(uuid4, uuid5)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5))
        // Array
        _ <- Ns(id).uuidArray.add(Array(uuid6)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5, uuid6))
        // Iterable
        _ <- Ns(id).uuidArray.add(Iterable(uuid7)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5, uuid6, uuid7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).uuidArray.add(Seq.empty[UUID]).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid2, uuid1, uuid3, uuid4, uuid4, uuid5, uuid6, uuid7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uuidArray(Array(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).uuidArray.remove(uuid7).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).uuidArray.remove(uuid9).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
          uuid1, uuid2, uuid3, uuid4, uuid5, uuid6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).uuidArray.remove(uuid6, uuid6).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1, uuid2, uuid3, uuid4, uuid5,
          uuid1, uuid2, uuid3, uuid4, uuid5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).uuidArray.remove(uuid4, uuid5).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1, uuid2, uuid3,
          uuid1, uuid2, uuid3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).uuidArray.remove(Array(uuid3)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1, uuid2,
          uuid1, uuid2,
        ))

        _ <- Ns(id).uuidArray.remove(Seq(uuid2)).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(
          uuid1,
          uuid1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).uuidArray.remove(Seq.empty[UUID]).update.transact
        _ <- Ns.uuidArray.query.get.map(_.head ==> Array(uuid1, uuid1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).uuidArray.remove(Seq(uuid1)).update.transact
        _ <- Ns.uuidArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
