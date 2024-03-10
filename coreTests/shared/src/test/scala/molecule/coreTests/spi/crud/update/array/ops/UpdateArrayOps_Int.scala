package molecule.coreTests.spi.crud.update.array.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Int extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.intArray(Array(int1, int2, int2)).save.transact.map(_.id)
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).intArray(Array(int3, int4, int4)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int3, int4, int4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).intArray(Seq.empty[Int]).update.transact
        _ <- Ns.intArray.query.get.map(_ ==> Nil)

        id <- Ns.intArray(Array(int1, int2, int2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).intArray().update.transact
        _ <- Ns.intArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.intArray(Array(int1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).intArray.add(int2).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2))

        // Add existing value
        _ <- Ns(id).intArray.add(int1).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1))

        // Add multiple values (vararg)
        _ <- Ns(id).intArray.add(int3, int4).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1, int3, int4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).intArray.add(Seq(int4, int5)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1, int3, int4, int4, int5))
        // Array
        _ <- Ns(id).intArray.add(Array(int6)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1, int3, int4, int4, int5, int6))
        // Iterable
        _ <- Ns(id).intArray.add(Iterable(int7)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1, int3, int4, int4, int5, int6, int7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).intArray.add(Seq.empty[Int]).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int2, int1, int3, int4, int4, int5, int6, int7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.intArray(Array(
          int1, int2, int3, int4, int5, int6, int7,
          int1, int2, int3, int4, int5, int6, int7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).intArray.remove(int7).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).intArray.remove(int9).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).intArray.remove(int6, int6).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1, int2, int3, int4, int5,
          int1, int2, int3, int4, int5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).intArray.remove(int4, int5).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1, int2, int3,
          int1, int2, int3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).intArray.remove(Array(int3)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1, int2,
          int1, int2,
        ))

        _ <- Ns(id).intArray.remove(Seq(int2)).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(
          int1,
          int1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).intArray.remove(Seq.empty[Int]).update.transact
        _ <- Ns.intArray.query.get.map(_.head ==> Array(int1, int1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).intArray.remove(Seq(int1)).update.transact
        _ <- Ns.intArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
