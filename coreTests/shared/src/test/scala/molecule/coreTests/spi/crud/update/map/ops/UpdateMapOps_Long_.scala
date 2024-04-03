// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.longMap(Map(plong1, plong2)).save.transact.map(_.id)
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).longMap(Map(plong3, plong4)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong3, plong4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).longMap(Map.empty[String, Long]).update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        id <- Ns.longMap(Map(plong1, plong2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).longMap().update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.longMap(Map("a" -> long0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).longMap.add("a" -> long1).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.longMap.iMap_?.query.get.map(_ ==> List((Map(plong1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.longMap.iMap_?.query.get.map(_ ==> List((Map(plong1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).longMap.add(plong2).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).longMap.add(plong3, plong4).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).longMap.add(Seq(plong5, plong6)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).longMap.add(Seq.empty[(String, Long)]).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.longMap(Map(plong1, plong2, plong3, plong4, plong5, plong6, plong7, plong8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).longMap.remove(string8).update.transact
        _ <- Ns(id).longMap.remove(string7).upsert.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.longMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.longMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).longMap.remove(string9).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).longMap.remove(string6, string6).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).longMap.remove(string4, string5).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).longMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).longMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).longMap.remove(Seq(string1)).update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
