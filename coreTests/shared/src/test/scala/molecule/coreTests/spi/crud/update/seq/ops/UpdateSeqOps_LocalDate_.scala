// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localDateSeq(List(localDate1, localDate2, localDate2)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).localDateSeq(List(localDate2, localDate3, localDate3)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate2, localDate3, localDate3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").localDateSeq(List(localDate3, localDate4, localDate4)).update.transact
        _ <- Ns.i.s.localDateSeq.query.get.map(_.head ==> (42, "foo", List(localDate3, localDate4, localDate4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).localDateSeq(List.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateSeq(List(localDate1, localDate2, localDate2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localDateSeq().update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).localDateSeq.add(localDate1).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).localDateSeq.add(localDate1).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1))

        // Add new value to end of Seq
        _ <- Ns(id).localDateSeq.add(localDate2).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1, localDate2))

        // Add multiple values with varargs
        _ <- Ns(id).localDateSeq.add(localDate3, localDate4).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1, localDate2, localDate3, localDate4))

        // Add multiple values with Iterable
        _ <- Ns(id).localDateSeq.add(List(localDate4, localDate5)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1, localDate2, localDate3, localDate4, localDate4, localDate5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateSeq.add(Set.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1, localDate2, localDate3, localDate4, localDate4, localDate5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).localDateSeq.remove(localDate1).update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localDateSeq.add(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).localDateSeq.remove(localDate7).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateSeq.remove(localDate9).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
          localDate1, localDate2, localDate3, localDate4, localDate5, localDate6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).localDateSeq.remove(localDate6, localDate6).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3, localDate4, localDate5,
          localDate1, localDate2, localDate3, localDate4, localDate5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).localDateSeq.remove(localDate4, localDate5).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1, localDate2, localDate3,
          localDate1, localDate2, localDate3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).localDateSeq.remove(List(localDate2, localDate3)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(
          localDate1,
          localDate1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localDateSeq.remove(Vector.empty[LocalDate]).update.transact
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).localDateSeq.remove(Set(localDate1)).update.transact
        _ <- Ns.localDateSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
