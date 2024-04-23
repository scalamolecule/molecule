// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeMap(Map(plocalDateTime1, plocalDateTime2)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).localDateTimeMap(Map(plocalDateTime2, plocalDateTime3)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime2, plocalDateTime3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").localDateTimeMap(Map(plocalDateTime3, plocalDateTime4)).update.transact
        _ <- Ns.i.s.localDateTimeMap.query.get.map(_.head ==> (42, "foo", Map(plocalDateTime3, plocalDateTime4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localDateTimeMap(Map.empty[String, LocalDateTime]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateTimeMap(Map(plocalDateTime1, plocalDateTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localDateTimeMap().update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localDateTimeMap.add("a" -> localDateTime0).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map("a" -> localDateTime0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).localDateTimeMap.add("a" -> localDateTime0).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map("a" -> localDateTime0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).localDateTimeMap.add("a" -> localDateTime1).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1)) // "a" -> localDateTime1

        // Add new pair
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime2).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))

        // Add multiple pairs with varargs
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime3, plocalDateTime4).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).localDateTimeMap.add(List(plocalDateTime5, plocalDateTime6)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).localDateTimeMap.add(Vector.empty[(String, LocalDateTime)]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).localDateTimeMap.remove(string1).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6, plocalDateTime7).update.transact

        // Remove pair by String key
        _ <- Ns(id).localDateTimeMap.remove(string7).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).localDateTimeMap.remove(string9).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).localDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).localDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).localDateTimeMap.remove(List(string2, string3)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).localDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).localDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
