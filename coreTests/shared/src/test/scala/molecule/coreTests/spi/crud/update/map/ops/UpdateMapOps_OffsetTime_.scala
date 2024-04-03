// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeMap(Map(poffsetTime1, poffsetTime2)).save.transact.map(_.id)
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).offsetTimeMap(Map(poffsetTime3, poffsetTime4)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime3, poffsetTime4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).offsetTimeMap(Map.empty[String, OffsetTime]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        id <- Ns.offsetTimeMap(Map(poffsetTime1, poffsetTime2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).offsetTimeMap().update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeMap(Map("a" -> offsetTime0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).offsetTimeMap.add("a" -> offsetTime1).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.offsetTimeMap.iMap_?.query.get.map(_ ==> List((Map(poffsetTime1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.offsetTimeMap.iMap_?.query.get.map(_ ==> List((Map(poffsetTime1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).offsetTimeMap.add(poffsetTime2).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).offsetTimeMap.add(poffsetTime3, poffsetTime4).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).offsetTimeMap.add(Seq(poffsetTime5, poffsetTime6)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).offsetTimeMap.add(Seq.empty[(String, OffsetTime)]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.offsetTimeMap(Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6, poffsetTime7, poffsetTime8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).offsetTimeMap.remove(string8).update.transact
        _ <- Ns(id).offsetTimeMap.remove(string7).upsert.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.offsetTimeMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.offsetTimeMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).offsetTimeMap.remove(string9).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).offsetTimeMap.remove(string6, string6).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).offsetTimeMap.remove(string4, string5).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).offsetTimeMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).offsetTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).offsetTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
