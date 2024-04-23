// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).charMap(Map(pchar1, pchar2)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).charMap(Map(pchar2, pchar3)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar2, pchar3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").charMap(Map(pchar3, pchar4)).update.transact
        _ <- Ns.i.s.charMap.query.get.map(_.head ==> (42, "foo", Map(pchar3, pchar4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).charMap(Map.empty[String, Char]).update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).charMap(Map(pchar1, pchar2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).charMap().update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).charMap.add("a" -> char0).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map("a" -> char0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).charMap.add("a" -> char0).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map("a" -> char0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).charMap.add("a" -> char1).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1)) // "a" -> char1

        // Add new pair
        _ <- Ns(id).charMap.add(pchar2).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Add multiple pairs with varargs
        _ <- Ns(id).charMap.add(pchar3, pchar4).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).charMap.add(List(pchar5, pchar6)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).charMap.add(Vector.empty[(String, Char)]).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).charMap.remove(string1).update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).charMap.add(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6, pchar7).update.transact

        // Remove pair by String key
        _ <- Ns(id).charMap.remove(string7).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Removing non-existing key has no effect
        _ <- Ns(id).charMap.remove(string9).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).charMap.remove(string6, string6).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).charMap.remove(string4, string5).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).charMap.remove(List(string2, string3)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).charMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).charMap.remove(Seq(string1)).update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
