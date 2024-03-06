// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1, char2)).save.transact.map(_.id)

        _ <- Ns(id).chars(Set(char3, char4)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char3, char4))

        // Apply Seq of values
        _ <- Ns(id).chars(Set(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char4, char5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).chars(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)

        _ <- Ns(id).chars(Set(char1, char2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).chars().update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add existing value (no effect)
        _ <- Ns(id).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add multiple values (vararg)
        _ <- Ns(id).chars.add(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).chars.add(Seq(char4, char5)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))
        // Set
        _ <- Ns(id).chars.add(Set(char6)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))
        // Iterable
        _ <- Ns(id).chars.add(Iterable(char7)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).chars.add(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6, char7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).chars.remove(char6).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing non-existing value has no effect
        _ <- Ns(id).chars.remove(char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).chars.remove(char5, char5).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Remove multiple values (vararg)
        _ <- Ns(id).chars.remove(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Remove Seq of values
        _ <- Ns(id).chars.remove(Seq(char2)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).chars.remove(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).chars.remove(Seq(char1)).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
