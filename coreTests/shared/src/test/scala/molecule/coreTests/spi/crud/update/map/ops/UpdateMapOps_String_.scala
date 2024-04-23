// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).stringMap(Map(pstring1, pstring2)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).stringMap(Map(pstring2, pstring3)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring2, pstring3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").stringMap(Map(pstring3, pstring4)).update.transact
        _ <- Ns.i.s.stringMap.query.get.map(_.head ==> (42, "foo", Map(pstring3, pstring4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).stringMap(Map.empty[String, String]).update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).stringMap(Map(pstring1, pstring2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).stringMap().update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).stringMap.add("a" -> string0).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map("a" -> string0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).stringMap.add("a" -> string0).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map("a" -> string0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).stringMap.add("a" -> string1).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1)) // "a" -> string1

        // Add new pair
        _ <- Ns(id).stringMap.add(pstring2).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))

        // Add multiple pairs with varargs
        _ <- Ns(id).stringMap.add(pstring3, pstring4).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).stringMap.add(List(pstring5, pstring6)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).stringMap.add(Vector.empty[(String, String)]).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).stringMap.remove(string1).update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).stringMap.add(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6, pstring7).update.transact

        // Remove pair by String key
        _ <- Ns(id).stringMap.remove(string7).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing non-existing key has no effect
        _ <- Ns(id).stringMap.remove(string9).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5, pstring6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).stringMap.remove(string6, string6).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3, pstring4, pstring5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).stringMap.remove(string4, string5).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).stringMap.remove(List(string2, string3)).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).stringMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).stringMap.remove(Seq(string1)).update.transact
        _ <- Ns.stringMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
