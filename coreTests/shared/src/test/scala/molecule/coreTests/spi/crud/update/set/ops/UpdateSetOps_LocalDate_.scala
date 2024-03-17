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

trait UpdateSetOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateSet(Set(localDate1, localDate2)).save.transact.map(_.id)
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localDateSet(Set(localDate3, localDate4)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate3, localDate4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).localDateSet(Set.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)

        id <- Ns.localDateSet(Set(localDate1, localDate2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).localDateSet().update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateSet(Set(localDate1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localDateSet.add(localDate2).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).localDateSet.add(localDate2).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateSet.add(localDate3, localDate4).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4))

        // Add multiple values (Seq)
        _ <- Ns(id).localDateSet.add(Seq(localDate5, localDate6)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localDateSet.add(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateSet(Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6, localDate7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localDateSet.remove(localDate7).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateSet.remove(localDate9).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5, localDate6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localDateSet.remove(localDate6, localDate6).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3, localDate4, localDate5))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateSet.remove(localDate4, localDate5).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3))

        // Remove multiple values (Seq)
        _ <- Ns(id).localDateSet.remove(Seq(localDate2, localDate3)).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateSet.remove(Seq.empty[LocalDate]).update.transact
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateSet.remove(Seq(localDate1)).update.transact
        _ <- Ns.localDateSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
