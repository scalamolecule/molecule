// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).save.transact.map(_.id)
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).offsetDateTimeSet(Set(offsetDateTime3, offsetDateTime4)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime3, offsetDateTime4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).offsetDateTimeSet(Set.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)

        id <- Ns.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).offsetDateTimeSet().update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSet(Set(offsetDateTime1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))

        // Add multiple values (vararg)
        _ <- Ns(id).offsetDateTimeSet.add(offsetDateTime3, offsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4))

        // Add multiple values (Seq)
        _ <- Ns(id).offsetDateTimeSet.add(Seq(offsetDateTime5, offsetDateTime6)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).offsetDateTimeSet.add(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6, offsetDateTime7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime7).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Removing non-existing value has no effect
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime9).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5, offsetDateTime6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime6, offsetDateTime6).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4, offsetDateTime5))

        // Remove multiple values (vararg)
        _ <- Ns(id).offsetDateTimeSet.remove(offsetDateTime4, offsetDateTime5).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))

        // Remove multiple values (Seq)
        _ <- Ns(id).offsetDateTimeSet.remove(Seq(offsetDateTime2, offsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).offsetDateTimeSet.remove(Seq.empty[OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).offsetDateTimeSet.remove(Seq(offsetDateTime1)).update.transact
        _ <- Ns.offsetDateTimeSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
