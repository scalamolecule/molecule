// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.instantMap(Map(pinstant1, pinstant2)).save.transact.map(_.id)
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).instantMap(Map(pinstant3, pinstant4)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant3, pinstant4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).instantMap(Map.empty[String, Instant]).update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        id <- Ns.instantMap(Map(pinstant1, pinstant2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).instantMap().update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.instantMap(Map("a" -> instant0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).instantMap.add("a" -> instant1).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.instantMap.iMap_?.query.get.map(_ ==> List((Map(pinstant1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.instantMap.iMap_?.query.get.map(_ ==> List((Map(pinstant1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).instantMap.add(pinstant2).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).instantMap.add(pinstant3, pinstant4).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).instantMap.add(Seq(pinstant5, pinstant6)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).instantMap.add(Seq.empty[(String, Instant)]).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.instantMap(Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6, pinstant7, pinstant8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).instantMap.remove(string8).update.transact
        _ <- Ns(id).instantMap.remove(string7).upsert.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.instantMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.instantMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).instantMap.remove(string9).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).instantMap.remove(string6, string6).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).instantMap.remove(string4, string5).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).instantMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).instantMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).instantMap.remove(Seq(string1)).update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
