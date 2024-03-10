// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Instant_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.instantArray(Array(instant1, instant2, instant2)).save.transact.map(_.id)
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).instantArray(Array(instant3, instant4, instant4)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant3, instant4, instant4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).instantArray(Seq.empty[Instant]).update.transact
        _ <- Ns.instantArray.query.get.map(_ ==> Nil)

        id <- Ns.instantArray(Array(instant1, instant2, instant2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).instantArray().update.transact
        _ <- Ns.instantArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.instantArray(Array(instant1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).instantArray.add(instant2).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2))

        // Add existing value
        _ <- Ns(id).instantArray.add(instant1).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1))

        // Add multiple values (vararg)
        _ <- Ns(id).instantArray.add(instant3, instant4).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1, instant3, instant4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).instantArray.add(Seq(instant4, instant5)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1, instant3, instant4, instant4, instant5))
        // Array
        _ <- Ns(id).instantArray.add(Array(instant6)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1, instant3, instant4, instant4, instant5, instant6))
        // Iterable
        _ <- Ns(id).instantArray.add(Iterable(instant7)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1, instant3, instant4, instant4, instant5, instant6, instant7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).instantArray.add(Seq.empty[Instant]).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant2, instant1, instant3, instant4, instant4, instant5, instant6, instant7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.instantArray(Array(
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
          instant1, instant2, instant3, instant4, instant5, instant6, instant7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).instantArray.remove(instant7).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).instantArray.remove(instant9).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1, instant2, instant3, instant4, instant5, instant6,
          instant1, instant2, instant3, instant4, instant5, instant6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).instantArray.remove(instant6, instant6).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1, instant2, instant3, instant4, instant5,
          instant1, instant2, instant3, instant4, instant5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).instantArray.remove(instant4, instant5).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1, instant2, instant3,
          instant1, instant2, instant3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).instantArray.remove(Array(instant3)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1, instant2,
          instant1, instant2,
        ))

        _ <- Ns(id).instantArray.remove(Seq(instant2)).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(
          instant1,
          instant1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).instantArray.remove(Seq.empty[Instant]).update.transact
        _ <- Ns.instantArray.query.get.map(_.head ==> Array(instant1, instant1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).instantArray.remove(Seq(instant1)).update.transact
        _ <- Ns.instantArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
