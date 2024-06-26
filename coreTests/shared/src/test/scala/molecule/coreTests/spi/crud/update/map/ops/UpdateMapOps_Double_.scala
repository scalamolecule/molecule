// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.doubleMap(Map(pdouble1, pdouble2)).save.transact.map(_.id)
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).doubleMap(Map(pdouble3, pdouble4)).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble3, pdouble4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).doubleMap(Map.empty[String, Double]).update.transact
        _ <- Ns.doubleMap.query.get.map(_ ==> Nil)

        id <- Ns.doubleMap(Map(pdouble1, pdouble2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).doubleMap().update.transact
        _ <- Ns.doubleMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.doubleMap(Map("a" -> double0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).doubleMap.add("a" -> double1).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.doubleMap.iMap_?.query.get.map(_ ==> List((Map(pdouble1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.doubleMap.iMap_?.query.get.map(_ ==> List((Map(pdouble1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).doubleMap.add(pdouble2).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).doubleMap.add(pdouble3, pdouble4).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).doubleMap.add(Seq(pdouble5, pdouble6)).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).doubleMap.add(Seq.empty[(String, Double)]).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.doubleMap(Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6, pdouble7, pdouble8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).doubleMap.remove(string8).update.transact
        _ <- Ns(id).doubleMap.remove(string7).upsert.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.doubleMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.doubleMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).doubleMap.remove(string9).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).doubleMap.remove(string6, string6).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3, pdouble4, pdouble5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).doubleMap.remove(string4, string5).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).doubleMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).doubleMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).doubleMap.remove(Seq(string1)).update.transact
        _ <- Ns.doubleMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
