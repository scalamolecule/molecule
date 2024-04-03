// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uuidMap(Map(puuid1, puuid2)).save.transact.map(_.id)
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).uuidMap(Map(puuid3, puuid4)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid3, puuid4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).uuidMap(Map.empty[String, UUID]).update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        id <- Ns.uuidMap(Map(puuid1, puuid2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).uuidMap().update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uuidMap(Map("a" -> uuid0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).uuidMap.add("a" -> uuid1).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.uuidMap.iMap_?.query.get.map(_ ==> List((Map(puuid1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.uuidMap.iMap_?.query.get.map(_ ==> List((Map(puuid1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).uuidMap.add(puuid2).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).uuidMap.add(puuid3, puuid4).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).uuidMap.add(Seq(puuid5, puuid6)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).uuidMap.add(Seq.empty[(String, UUID)]).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uuidMap(Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6, puuid7, puuid8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).uuidMap.remove(string8).update.transact
        _ <- Ns(id).uuidMap.remove(string7).upsert.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.uuidMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.uuidMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).uuidMap.remove(string9).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).uuidMap.remove(string6, string6).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).uuidMap.remove(string4, string5).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).uuidMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).uuidMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).uuidMap.remove(Seq(string1)).update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
