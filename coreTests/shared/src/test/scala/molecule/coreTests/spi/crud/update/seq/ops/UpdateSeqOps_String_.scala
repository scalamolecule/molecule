// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.stringSeq(List(string1, string2, string2)).save.transact.map(_.id)
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).stringSeq(List(string3, string4, string4)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string3, string4, string4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).stringSeq(List.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        id <- Ns.stringSeq(List(string1, string2, string2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).stringSeq().update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.stringSeq(List(string1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).stringSeq.add(string2).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2))

        // Add existing value
        _ <- Ns(id).stringSeq.add(string1).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1))

        // Add multiple values (vararg)
        _ <- Ns(id).stringSeq.add(string3, string4).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1, string3, string4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).stringSeq.add(Seq(string4, string5)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1, string3, string4, string4, string5))
        // Array
        _ <- Ns(id).stringSeq.add(List(string6)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1, string3, string4, string4, string5, string6))
        // Iterable
        _ <- Ns(id).stringSeq.add(Iterable(string7)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1, string3, string4, string4, string5, string6, string7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).stringSeq.add(List.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string1, string3, string4, string4, string5, string6, string7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.stringSeq(List(
          string1, string2, string3, string4, string5, string6, string7,
          string1, string2, string3, string4, string5, string6, string7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).stringSeq.remove(string7).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2, string3, string4, string5, string6,
          string1, string2, string3, string4, string5, string6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).stringSeq.remove(string9).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2, string3, string4, string5, string6,
          string1, string2, string3, string4, string5, string6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).stringSeq.remove(string6, string6).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2, string3, string4, string5,
          string1, string2, string3, string4, string5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).stringSeq.remove(string4, string5).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2, string3,
          string1, string2, string3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).stringSeq.remove(List(string3)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2,
          string1, string2,
        ))

        _ <- Ns(id).stringSeq.remove(Seq(string2)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1,
          string1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).stringSeq.remove(List.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).stringSeq.remove(Seq(string1)).update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
