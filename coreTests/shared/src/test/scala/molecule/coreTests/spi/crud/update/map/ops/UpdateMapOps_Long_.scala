// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).longMap(Map(plong1, plong2)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).longMap(Map(plong2, plong3)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong2, plong3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").longMap(Map(plong3, plong4)).update.transact
        _ <- Ns.i.s.longMap.query.get.map(_.head ==> (42, "foo", Map(plong3, plong4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).longMap(Map.empty[String, Long]).update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).longMap(Map(plong1, plong2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).longMap().update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).longMap.add("a" -> long0).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map("a" -> long0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).longMap.add("a" -> long0).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map("a" -> long0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).longMap.add("a" -> long1).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1)) // "a" -> long1

        // Add new pair
        _ <- Ns(id).longMap.add(plong2).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))

        // Add multiple pairs with varargs
        _ <- Ns(id).longMap.add(plong3, plong4).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).longMap.add(List(plong5, plong6)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).longMap.add(Vector.empty[(String, Long)]).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).longMap.remove(string1).update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).longMap.add(plong1, plong2, plong3, plong4, plong5, plong6, plong7).update.transact

        // Remove pair by String key
        _ <- Ns(id).longMap.remove(string7).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Removing non-existing key has no effect
        _ <- Ns(id).longMap.remove(string9).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5, plong6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).longMap.remove(string6, string6).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3, plong4, plong5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).longMap.remove(string4, string5).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).longMap.remove(List(string2, string3)).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).longMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).longMap.remove(Seq(string1)).update.transact
        _ <- Ns.longMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
