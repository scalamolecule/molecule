// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.shortSet(Set(short1, short2)).save.transact.map(_.id)
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).shortSet(Set(short3, short4)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short3, short4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).shortSet(Set.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)

        id <- Ns.shortSet(Set(short1, short2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).shortSet().update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.shortSet(Set(short1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).shortSet.add(short2).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).shortSet.add(short2).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))

        // Add multiple values (vararg)
        _ <- Ns(id).shortSet.add(short3, short4).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Add multiple values (Seq)
        _ <- Ns(id).shortSet.add(Seq(short5, short6)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).shortSet.add(Seq.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.shortSet(Set(short1, short2, short3, short4, short5, short6, short7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).shortSet.remove(short7).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Removing non-existing value has no effect
        _ <- Ns(id).shortSet.remove(short9).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).shortSet.remove(short6, short6).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Remove multiple values (vararg)
        _ <- Ns(id).shortSet.remove(short4, short5).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2, short3))

        // Remove multiple values (Seq)
        _ <- Ns(id).shortSet.remove(Seq(short2, short3)).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).shortSet.remove(Seq.empty[Short]).update.transact
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).shortSet.remove(Seq(short1)).update.transact
        _ <- Ns.shortSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
