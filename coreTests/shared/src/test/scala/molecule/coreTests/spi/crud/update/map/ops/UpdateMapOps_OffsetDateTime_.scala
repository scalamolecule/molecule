// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2)).save.transact.map(_.id)
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).offsetDateTimeMap(Map(poffsetDateTime3, poffsetDateTime4)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime3, poffsetDateTime4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).offsetDateTimeMap(Map.empty[String, OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        id <- Ns.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).offsetDateTimeMap().update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeMap(Map("a" -> offsetDateTime0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).offsetDateTimeMap.add("a" -> offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.offsetDateTimeMap.iMap_?.query.get.map(_ ==> List((Map(poffsetDateTime1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.offsetDateTimeMap.iMap_?.query.get.map(_ ==> List((Map(poffsetDateTime1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).offsetDateTimeMap.add(poffsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).offsetDateTimeMap.add(poffsetDateTime3, poffsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).offsetDateTimeMap.add(Seq(poffsetDateTime5, poffsetDateTime6)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).offsetDateTimeMap.add(Seq.empty[(String, OffsetDateTime)]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6, poffsetDateTime7, poffsetDateTime8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).offsetDateTimeMap.remove(string8).update.transact
        _ <- Ns(id).offsetDateTimeMap.remove(string7).upsert.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.offsetDateTimeMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.offsetDateTimeMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).offsetDateTimeMap.remove(string9).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).offsetDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).offsetDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).offsetDateTimeMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).offsetDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).offsetDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
