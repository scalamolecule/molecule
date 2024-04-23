// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localDateSet(Set(localDate1, localDate2)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localDateSet(Set(localDate2, localDate3)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate2, localDate3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").localDateSet(Set(localDate3, localDate4)).update.transact
        _ <- Ns.i.s.localDateSet.query.get.map(_.head ==> (42, "foo", Set(localDate3, localDate4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).localDateSet(Set.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateSet(Set(localDate1, localDate2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).localDateSet().update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).localDateSet.add(localDate1).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).localDateSet.add(localDate1).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Add new value
        _ <- Ns(id).localDateSet.add(localDate2).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Add multiple values with vararg
        _ <- Ns(id).localDateSet.add(localDate3, localDate4).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Add multiple values with Iterable
        _ <- Ns(id).localDateSet.add(List(localDate5, localDate6)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).localDateSet.add(Set.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).localDateSet.remove(localDate1).update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).localDateSet.add(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7).update.transact

        // Remove value
        _ <- Ns(id).localDateSet.remove(localDate7).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateSet.remove(localDate9).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).localDateSet.remove(localDate6, localDate6).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Remove multiple values with varargs
        _ <- Ns(id).localDateSet.remove(localDate4, localDate5).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3))

        // Remove multiple values with Iterable
        _ <- Ns(id).localDateSet.remove(List(localDate2, localDate3)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).localDateSet.remove(Vector.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateSet.remove(Set(localDate1)).update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
