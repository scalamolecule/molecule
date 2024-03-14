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
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).chars(Set(char3, char4)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char3, char4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).chars(Set.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)

        id <- Ns.chars(Set(char1, char2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).chars.add(char2).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))

        // Add multiple values (vararg)
        _ <- Ns(id).chars.add(char3, char4).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4))

        // Add multiple values (Seq)
        _ <- Ns(id).chars.add(Seq(char5, char6)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).chars.add(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.chars(Set(char1, char2, char3, char4, char5, char6, char7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).chars.remove(char7).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Removing non-existing value has no effect
        _ <- Ns(id).chars.remove(char9).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5, char6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).chars.remove(char6, char6).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3, char4, char5))

        // Remove multiple values (vararg)
        _ <- Ns(id).chars.remove(char4, char5).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2, char3))

        // Remove multiple values (Seq)
        _ <- Ns(id).chars.remove(Seq(char2, char3)).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).chars.remove(Seq.empty[Char]).update.transact
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).chars.remove(Seq(char1)).update.transact
        _ <- Ns.chars.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
