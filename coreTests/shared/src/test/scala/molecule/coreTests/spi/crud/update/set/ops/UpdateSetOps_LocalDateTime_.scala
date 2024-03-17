// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSet(Set(localDateTime1, localDateTime2)).save.transact.map(_.id)
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).localDateTimeSet(Set(localDateTime3, localDateTime4)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime3, localDateTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).localDateTimeSet(Set.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)

        id <- Ns.localDateTimeSet(Set(localDateTime1, localDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).localDateTimeSet().update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSet(Set(localDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).localDateTimeSet.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).localDateTimeSet.add(localDateTime2).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).localDateTimeSet.add(localDateTime3, localDateTime4).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).localDateTimeSet.add(Seq(localDateTime5, localDateTime6)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).localDateTimeSet.add(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeSet(Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6, localDateTime7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).localDateTimeSet.remove(localDateTime7).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).localDateTimeSet.remove(localDateTime9).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5, localDateTime6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).localDateTimeSet.remove(localDateTime6, localDateTime6).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4, localDateTime5))

        // Remove multiple values (vararg)
        _ <- Ns(id).localDateTimeSet.remove(localDateTime4, localDateTime5).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3))

        // Remove multiple values (Seq)
        _ <- Ns(id).localDateTimeSet.remove(Seq(localDateTime2, localDateTime3)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).localDateTimeSet.remove(Seq.empty[LocalDateTime]).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).localDateTimeSet.remove(Seq(localDateTime1)).update.transact
        _ <- Ns.localDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
