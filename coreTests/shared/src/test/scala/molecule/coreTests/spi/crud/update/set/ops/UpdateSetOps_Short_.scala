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
        id <- Ns.shorts(Set(short1, short2)).save.transact.map(_.id)

        _ <- Ns(id).shorts(Set(short3, short4)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short3, short4))

        // Apply Seq of values
        _ <- Ns(id).shorts(Set(short4, short5)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short4, short5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).shorts(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)

        _ <- Ns(id).shorts(Set(short1, short2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).shorts().update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.shorts(Set(short1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).shorts.add(short2).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Add existing value (no effect)
        _ <- Ns(id).shorts.add(short2).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Add multiple values (vararg)
        _ <- Ns(id).shorts.add(short3, short4).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).shorts.add(Seq(short4, short5)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))
        // Set
        _ <- Ns(id).shorts.add(Set(short6)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6))
        // Iterable
        _ <- Ns(id).shorts.add(Iterable(short7)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6, short7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).shorts.add(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5, short6, short7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.shorts(Set(short1, short2, short3, short4, short5, short6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).shorts.remove(short6).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Removing non-existing value has no effect
        _ <- Ns(id).shorts.remove(short7).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4, short5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).shorts.remove(short5, short5).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2, short3, short4))

        // Remove multiple values (vararg)
        _ <- Ns(id).shorts.remove(short3, short4).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))

        // Remove Seq of values
        _ <- Ns(id).shorts.remove(Seq(short2)).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).shorts.remove(Seq.empty[Short]).update.transact
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).shorts.remove(Seq(short1)).update.transact
        _ <- Ns.shorts.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
