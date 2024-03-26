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
        id <- Ns.stringMap(Map(pstring1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).stringMap.add(pstring2).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
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
        id <- Ns.stringMap(Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6, pstring7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).stringMap.remove(string7).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing non-existing key has no effect
        _ <- Ns(id).stringMap.remove(string9).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing duplicate keys removes the distinct pair
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
