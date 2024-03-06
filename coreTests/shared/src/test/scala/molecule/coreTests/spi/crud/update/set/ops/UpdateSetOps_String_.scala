// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.strings(Set(string1, string2)).save.transact.map(_.id)

        _ <- Ns(id).strings(Set(string3, string4)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string3, string4))

        // Apply Seq of values
        _ <- Ns(id).strings(Set(string4, string5)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string4, string5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).strings(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_ ==> Nil)

        _ <- Ns(id).strings(Set(string1, string2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).strings().update.transact
        _ <- Ns.strings.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.strings(Set(string1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).strings.add(string2).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Add existing value (no effect)
        _ <- Ns(id).strings.add(string2).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Add multiple values (vararg)
        _ <- Ns(id).strings.add(string3, string4).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).strings.add(Seq(string4, string5)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))
        // Set
        _ <- Ns(id).strings.add(Set(string6)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6))
        // Iterable
        _ <- Ns(id).strings.add(Iterable(string7)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6, string7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).strings.add(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5, string6, string7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.strings(Set(string1, string2, string3, string4, string5, string6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).strings.remove(string6).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))

        // Removing non-existing value has no effect
        _ <- Ns(id).strings.remove(string7).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4, string5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).strings.remove(string5, string5).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2, string3, string4))

        // Remove multiple values (vararg)
        _ <- Ns(id).strings.remove(string3, string4).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))

        // Remove Seq of values
        _ <- Ns(id).strings.remove(Seq(string2)).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).strings.remove(Seq.empty[String]).update.transact
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).strings.remove(Seq(string1)).update.transact
        _ <- Ns.strings.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
