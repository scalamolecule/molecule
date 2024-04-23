// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).floatMap(Map(pfloat1, pfloat2)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).floatMap(Map(pfloat2, pfloat3)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat2, pfloat3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").floatMap(Map(pfloat3, pfloat4)).update.transact
        _ <- Ns.i.s.floatMap.query.get.map(_.head ==> (42, "foo", Map(pfloat3, pfloat4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).floatMap(Map.empty[String, Float]).update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).floatMap(Map(pfloat1, pfloat2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).floatMap().update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).floatMap.add("a" -> float0).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map("a" -> float0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).floatMap.add("a" -> float0).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map("a" -> float0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).floatMap.add("a" -> float1).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1)) // "a" -> float1

        // Add new pair
        _ <- Ns(id).floatMap.add(pfloat2).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))

        // Add multiple pairs with varargs
        _ <- Ns(id).floatMap.add(pfloat3, pfloat4).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).floatMap.add(List(pfloat5, pfloat6)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).floatMap.add(Vector.empty[(String, Float)]).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).floatMap.remove(string1).update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).floatMap.add(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6, pfloat7).update.transact

        // Remove pair by String key
        _ <- Ns(id).floatMap.remove(string7).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Removing non-existing key has no effect
        _ <- Ns(id).floatMap.remove(string9).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).floatMap.remove(string6, string6).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).floatMap.remove(string4, string5).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).floatMap.remove(List(string2, string3)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).floatMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).floatMap.remove(Seq(string1)).update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
