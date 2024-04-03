// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.shortMap(Map(pshort1, pshort2)).save.transact.map(_.id)
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).shortMap(Map(pshort3, pshort4)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort3, pshort4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).shortMap(Map.empty[String, Short]).update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        id <- Ns.shortMap(Map(pshort1, pshort2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).shortMap().update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.shortMap(Map("a" -> short0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).shortMap.add("a" -> short1).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.shortMap.iMap_?.query.get.map(_ ==> List((Map(pshort1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.shortMap.iMap_?.query.get.map(_ ==> List((Map(pshort1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).shortMap.add(pshort2).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).shortMap.add(pshort3, pshort4).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).shortMap.add(Seq(pshort5, pshort6)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).shortMap.add(Seq.empty[(String, Short)]).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.shortMap(Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6, pshort7, pshort8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).shortMap.remove(string8).update.transact
        _ <- Ns(id).shortMap.remove(string7).upsert.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.shortMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.shortMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).shortMap.remove(string9).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).shortMap.remove(string6, string6).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).shortMap.remove(string4, string5).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).shortMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).shortMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).shortMap.remove(Seq(string1)).update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
