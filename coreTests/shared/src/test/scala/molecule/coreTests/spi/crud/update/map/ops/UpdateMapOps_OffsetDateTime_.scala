// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).offsetDateTimeMap(Map(poffsetDateTime2, poffsetDateTime3)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime2, poffsetDateTime3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").offsetDateTimeMap(Map(poffsetDateTime3, poffsetDateTime4)).update.transact
        _ <- Ns.i.s.offsetDateTimeMap.query.get.map(_.head ==> (42, "foo", Map(poffsetDateTime3, poffsetDateTime4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).offsetDateTimeMap(Map.empty[String, OffsetDateTime]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).offsetDateTimeMap().update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).offsetDateTimeMap.add("a" -> offsetDateTime0).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map("a" -> offsetDateTime0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).offsetDateTimeMap.add("a" -> offsetDateTime0).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map("a" -> offsetDateTime0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).offsetDateTimeMap.add("a" -> offsetDateTime1).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1)) // "a" -> offsetDateTime1

        // Add new pair
        _ <- Ns(id).offsetDateTimeMap.add(poffsetDateTime2).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))

        // Add multiple pairs with varargs
        _ <- Ns(id).offsetDateTimeMap.add(poffsetDateTime3, poffsetDateTime4).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).offsetDateTimeMap.add(List(poffsetDateTime5, poffsetDateTime6)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).offsetDateTimeMap.add(Vector.empty[(String, OffsetDateTime)]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).offsetDateTimeMap.remove(string1).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).offsetDateTimeMap.add(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6, poffsetDateTime7).update.transact

        // Remove pair by String key
        _ <- Ns(id).offsetDateTimeMap.remove(string7).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).offsetDateTimeMap.remove(string9).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).offsetDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).offsetDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).offsetDateTimeMap.remove(List(string2, string3)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).offsetDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).offsetDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.offsetDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
