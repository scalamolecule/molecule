// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.dateMap(Map(pdate1, pdate2)).save.transact.map(_.id)
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).dateMap(Map(pdate3, pdate4)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate3, pdate4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).dateMap(Map.empty[String, Date]).update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        id <- Ns.dateMap(Map(pdate1, pdate2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).dateMap().update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.dateMap(Map("a" -> date0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).dateMap.add("a" -> date1).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.dateMap.iMap_?.query.get.map(_ ==> List((Map(pdate1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.dateMap.iMap_?.query.get.map(_ ==> List((Map(pdate1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).dateMap.add(pdate2).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).dateMap.add(pdate3, pdate4).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).dateMap.add(Seq(pdate5, pdate6)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).dateMap.add(Seq.empty[(String, Date)]).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.dateMap(Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6, pdate7, pdate8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).dateMap.remove(string8).update.transact
        _ <- Ns(id).dateMap.remove(string7).upsert.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.dateMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.dateMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).dateMap.remove(string9).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).dateMap.remove(string6, string6).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).dateMap.remove(string4, string5).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).dateMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).dateMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).dateMap.remove(Seq(string1)).update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
