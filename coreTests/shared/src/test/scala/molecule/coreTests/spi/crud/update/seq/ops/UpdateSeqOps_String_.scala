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


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).stringSeq(List(string1, string2, string2)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).stringSeq(List(string2, string3, string3)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string2, string3, string3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").stringSeq(List(string3, string4, string4)).update.transact
        _ <- Ns.i.s.stringSeq.query.get.map(_.head ==> (42, "foo", List(string3, string4, string4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).stringSeq(List.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).stringSeq(List(string1, string2, string2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).stringSeq().update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).stringSeq.add(string1).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).stringSeq.add(string1).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1))

        // Add new value to end of Seq
        _ <- Ns(id).stringSeq.add(string2).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1, string2))

        // Add multiple values with varargs
        _ <- Ns(id).stringSeq.add(string3, string4).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1, string2, string3, string4))

        // Add multiple values with Iterable
        _ <- Ns(id).stringSeq.add(List(string4, string5)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1, string2, string3, string4, string4, string5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).stringSeq.add(Set.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1, string2, string3, string4, string4, string5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).stringSeq.remove(string1).update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).stringSeq.add(
          string1, string2, string3, string4, string5, string6, string7,
          string1, string2, string3, string4, string5, string6, string7,
        ).update.transact

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

        // Remove multiple values with vararg
        _ <- Ns(id).stringSeq.remove(string4, string5).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1, string2, string3,
          string1, string2, string3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).stringSeq.remove(List(string2, string3)).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(
          string1,
          string1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).stringSeq.remove(Vector.empty[String]).update.transact
        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).stringSeq.remove(Set(string1)).update.transact
        _ <- Ns.stringSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
