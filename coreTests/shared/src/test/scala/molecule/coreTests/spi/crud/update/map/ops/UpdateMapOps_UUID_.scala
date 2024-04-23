// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).uuidMap(Map(puuid1, puuid2)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).uuidMap(Map(puuid2, puuid3)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid2, puuid3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").uuidMap(Map(puuid3, puuid4)).update.transact
        _ <- Ns.i.s.uuidMap.query.get.map(_.head ==> (42, "foo", Map(puuid3, puuid4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).uuidMap(Map.empty[String, UUID]).update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).uuidMap(Map(puuid1, puuid2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).uuidMap().update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).uuidMap.add("a" -> uuid0).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map("a" -> uuid0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).uuidMap.add("a" -> uuid0).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map("a" -> uuid0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).uuidMap.add("a" -> uuid1).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1)) // "a" -> uuid1

        // Add new pair
        _ <- Ns(id).uuidMap.add(puuid2).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))

        // Add multiple pairs with varargs
        _ <- Ns(id).uuidMap.add(puuid3, puuid4).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).uuidMap.add(List(puuid5, puuid6)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).uuidMap.add(Vector.empty[(String, UUID)]).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).uuidMap.remove(string1).update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).uuidMap.add(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6, puuid7).update.transact

        // Remove pair by String key
        _ <- Ns(id).uuidMap.remove(string7).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Removing non-existing key has no effect
        _ <- Ns(id).uuidMap.remove(string9).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5, puuid6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).uuidMap.remove(string6, string6).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3, puuid4, puuid5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).uuidMap.remove(string4, string5).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).uuidMap.remove(List(string2, string3)).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).uuidMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).uuidMap.remove(Seq(string1)).update.transact
        _ <- Ns.uuidMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
