// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.stringMap(Map(pstring1, pstring2)).save.transact.map(_.id)
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).stringMap(Map(pstring3, pstring4)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring3, pstring4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).stringMap(Map.empty[String, String]).update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        id <- Ns.stringMap(Map(pstring1, pstring2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).stringMap().update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.stringMap(Map("a" -> string0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).stringMap.add("a" -> string1).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.stringMap.iMap_?.query.get.map(_ ==> List((Map(pstring1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.stringMap.iMap_?.query.get.map(_ ==> List((Map(pstring1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).stringMap.add(pstring2).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).stringMap.add(pstring3, pstring4).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).stringMap.add(Seq(pstring5, pstring6)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).stringMap.add(Seq.empty[(String, String)]).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.stringMap(Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6, pstring7, pstring8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).stringMap.remove(string8).update.transact
        _ <- Ns(id).stringMap.remove(string7).upsert.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.stringMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.stringMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).stringMap.remove(string9).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).stringMap.remove(string6, string6).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).stringMap.remove(string4, string5).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).stringMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).stringMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).stringMap.remove(Seq(string1)).update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
