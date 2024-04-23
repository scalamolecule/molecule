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
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).uriMap(Map(puri1, puri2)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).uriMap(Map(puri2, puri3)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri2, puri3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").uriMap(Map(puri3, puri4)).update.transact
        _ <- Ns.i.s.uriMap.query.get.map(_.head ==> (42, "foo", Map(puri3, puri4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).uriMap(Map.empty[String, URI]).update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).uriMap(Map(puri1, puri2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).uriMap().update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).uriMap.add("a" -> uri0).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map("a" -> uri0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).uriMap.add("a" -> uri0).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map("a" -> uri0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).uriMap.add("a" -> uri1).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1)) // "a" -> uri1

        // Add new pair
        _ <- Ns(id).uriMap.add(puri2).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))

        // Add multiple pairs with varargs
        _ <- Ns(id).uriMap.add(puri3, puri4).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).uriMap.add(List(puri5, puri6)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).uriMap.add(Vector.empty[(String, URI)]).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).uriMap.remove(string1).update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).uriMap.add(puri1, puri2, puri3, puri4, puri5, puri6, puri7).update.transact

        // Remove pair by String key
        _ <- Ns(id).uriMap.remove(string7).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Removing non-existing key has no effect
        _ <- Ns(id).uriMap.remove(string9).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5, puri6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).uriMap.remove(string6, string6).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3, puri4, puri5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).uriMap.remove(string4, string5).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).uriMap.remove(List(string2, string3)).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).uriMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).uriMap.remove(Seq(string1)).update.transact
        _ <- Ns.uriMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
