// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.instantSet(Set(instant1, instant2)).save.transact.map(_.id)
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).instantSet(Set(instant3, instant4)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant3, instant4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).instantSet(Set.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)

        id <- Ns.instantSet(Set(instant1, instant2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).instantSet().update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.instantSet(Set(instant1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).instantSet.add(instant2).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).instantSet.add(instant2).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))

        // Add multiple values (vararg)
        _ <- Ns(id).instantSet.add(instant3, instant4).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4))

        // Add multiple values (Seq)
        _ <- Ns(id).instantSet.add(Seq(instant5, instant6)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).instantSet.add(Seq.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.instantSet(Set(instant1, instant2, instant3, instant4, instant5, instant6, instant7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).instantSet.remove(instant7).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Removing non-existing value has no effect
        _ <- Ns(id).instantSet.remove(instant9).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5, instant6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).instantSet.remove(instant6, instant6).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3, instant4, instant5))

        // Remove multiple values (vararg)
        _ <- Ns(id).instantSet.remove(instant4, instant5).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3))

        // Remove multiple values (Seq)
        _ <- Ns(id).instantSet.remove(Seq(instant2, instant3)).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).instantSet.remove(Seq.empty[Instant]).update.transact
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).instantSet.remove(Seq(instant1)).update.transact
        _ <- Ns.instantSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
