package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map(pint1, pint2)).save.transact.map(_.id)
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).intMap(Map(pint3, pint4)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint3, pint4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).intMap(Map.empty[String, Int]).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        id <- Ns.intMap(Map(pint1, pint2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).intMap().update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map("a" -> int0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).intMap.add("a" -> int1).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.intMap.iMap_?.query.get.map(_ ==> List((Map(pint1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.intMap.iMap_?.query.get.map(_ ==> List((Map(pint1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).intMap.add(pint2).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).intMap.add(pint3, pint4).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).intMap.add(Seq(pint5, pint6)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).intMap.add(Seq.empty[(String, Int)]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map(pint1, pint2, pint3, pint4, pint5, pint6, pint7, pint8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).intMap.remove(string8).update.transact
        _ <- Ns(id).intMap.remove(string7).upsert.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.intMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.intMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).intMap.remove(string9).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).intMap.remove(string6, string6).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).intMap.remove(string4, string5).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).intMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).intMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).intMap.remove(Seq(string1)).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
