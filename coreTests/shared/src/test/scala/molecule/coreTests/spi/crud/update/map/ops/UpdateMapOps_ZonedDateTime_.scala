// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).zonedDateTimeMap(Map(pzonedDateTime2, pzonedDateTime3)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime2, pzonedDateTime3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").zonedDateTimeMap(Map(pzonedDateTime3, pzonedDateTime4)).update.transact
        _ <- Ns.i.s.zonedDateTimeMap.query.get.map(_.head ==> (42, "foo", Map(pzonedDateTime3, pzonedDateTime4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).zonedDateTimeMap(Map.empty[String, ZonedDateTime]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).zonedDateTimeMap().update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).zonedDateTimeMap.add("a" -> zonedDateTime0).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map("a" -> zonedDateTime0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).zonedDateTimeMap.add("a" -> zonedDateTime0).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map("a" -> zonedDateTime0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).zonedDateTimeMap.add("a" -> zonedDateTime1).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1)) // "a" -> zonedDateTime1

        // Add new pair
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime2).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))

        // Add multiple pairs with varargs
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime3, pzonedDateTime4).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).zonedDateTimeMap.add(List(pzonedDateTime5, pzonedDateTime6)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).zonedDateTimeMap.add(Vector.empty[(String, ZonedDateTime)]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).zonedDateTimeMap.remove(string1).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).zonedDateTimeMap.add(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6, pzonedDateTime7).update.transact

        // Remove pair by String key
        _ <- Ns(id).zonedDateTimeMap.remove(string7).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).zonedDateTimeMap.remove(string9).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).zonedDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).zonedDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).zonedDateTimeMap.remove(List(string2, string3)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).zonedDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).zonedDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.zonedDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
