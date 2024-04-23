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
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).shortMap(Map(pshort1, pshort2)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).shortMap(Map(pshort2, pshort3)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort2, pshort3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").shortMap(Map(pshort3, pshort4)).update.transact
        _ <- Ns.i.s.shortMap.query.get.map(_.head ==> (42, "foo", Map(pshort3, pshort4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).shortMap(Map.empty[String, Short]).update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).shortMap(Map(pshort1, pshort2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).shortMap().update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).shortMap.add("a" -> short0).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map("a" -> short0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).shortMap.add("a" -> short0).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map("a" -> short0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).shortMap.add("a" -> short1).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1)) // "a" -> short1

        // Add new pair
        _ <- Ns(id).shortMap.add(pshort2).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))

        // Add multiple pairs with varargs
        _ <- Ns(id).shortMap.add(pshort3, pshort4).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).shortMap.add(List(pshort5, pshort6)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).shortMap.add(Vector.empty[(String, Short)]).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).shortMap.remove(string1).update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).shortMap.add(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6, pshort7).update.transact

        // Remove pair by String key
        _ <- Ns(id).shortMap.remove(string7).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Removing non-existing key has no effect
        _ <- Ns(id).shortMap.remove(string9).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5, pshort6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).shortMap.remove(string6, string6).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3, pshort4, pshort5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).shortMap.remove(string4, string5).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).shortMap.remove(List(string2, string3)).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).shortMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).shortMap.remove(Seq(string1)).update.transact
        _ <- Ns.shortMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
