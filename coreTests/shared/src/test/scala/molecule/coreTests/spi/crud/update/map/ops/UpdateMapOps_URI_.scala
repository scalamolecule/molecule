// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.uriMap(Map(puri1, puri2)).save.transact.map(_.id)
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).uriMap(Map(puri3, puri4)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri3, puri4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).uriMap(Map.empty[String, URI]).update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        id <- Ns.uriMap(Map(puri1, puri2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).uriMap().update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.uriMap(Map(puri1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).uriMap.add(puri2).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).uriMap.add(puri2).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).uriMap.add(puri3, puri4).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).uriMap.add(Seq(puri5, puri6)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).uriMap.add(Seq.empty[(String, URI)]).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.uriMap(Map(puri1, puri2, puri3, puri4, puri5, puri6, puri7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).uriMap.remove(string7).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Removing non-existing key has no effect
        _ <- Ns(id).uriMap.remove(string9).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).uriMap.remove(string6, string6).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).uriMap.remove(string4, string5).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).uriMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).uriMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).uriMap.remove(Seq(string1)).update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
