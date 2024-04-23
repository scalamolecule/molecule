// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal2)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).bigDecimalSeq(List(bigDecimal2, bigDecimal3, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal2, bigDecimal3, bigDecimal3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").bigDecimalSeq(List(bigDecimal3, bigDecimal4, bigDecimal4)).update.transact
        _ <- Ns.i.s.bigDecimalSeq.query.get.map(_.head ==> (42, "foo", List(bigDecimal3, bigDecimal4, bigDecimal4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).bigDecimalSeq(List.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).bigDecimalSeq().update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1))

        // Add new value to end of Seq
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal2).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1, bigDecimal2))

        // Add multiple values with varargs
        _ <- Ns(id).bigDecimalSeq.add(bigDecimal3, bigDecimal4).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4))

        // Add multiple values with Iterable
        _ <- Ns(id).bigDecimalSeq.add(List(bigDecimal4, bigDecimal5)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalSeq.add(Set.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal4, bigDecimal5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal1).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).bigDecimalSeq.add(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal7).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal9).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal6, bigDecimal6).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
          bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).bigDecimalSeq.remove(bigDecimal4, bigDecimal5).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1, bigDecimal2, bigDecimal3,
          bigDecimal1, bigDecimal2, bigDecimal3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).bigDecimalSeq.remove(List(bigDecimal2, bigDecimal3)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(
          bigDecimal1,
          bigDecimal1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).bigDecimalSeq.remove(Vector.empty[BigDecimal]).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).bigDecimalSeq.remove(Set(bigDecimal1)).update.transact
        _ <- Ns.bigDecimalSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
