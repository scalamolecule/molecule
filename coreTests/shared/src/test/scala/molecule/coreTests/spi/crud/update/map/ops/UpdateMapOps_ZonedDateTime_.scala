// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2)).save.transact.map(_.id)
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).zonedDateTimeMap(Map(pzonedDateTime3, pzonedDateTime4)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime3, pzonedDateTime4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).zonedDateTimeMap(Map.empty[String, ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        id <- Ns.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).zonedDateTimeMap().update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeMap(Map(pzonedDateTime1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime3, pzonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).zonedDateTimeMap.add(Seq(pzonedDateTime5, pzonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).zonedDateTimeMap.add(Seq.empty[(String, ZonedDateTime)]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6, pzonedDateTime7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).zonedDateTimeMap.remove(string7).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).zonedDateTimeMap.remove(string9).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).zonedDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).zonedDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).zonedDateTimeMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).zonedDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).zonedDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
