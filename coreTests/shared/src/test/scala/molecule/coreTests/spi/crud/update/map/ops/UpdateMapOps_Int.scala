package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).intMap(Map(pint1, pint2)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).intMap(Map(pint2, pint3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint2, pint3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").intMap(Map(pint3, pint4)).update.transact
        _ <- Ns.i.s.intMap.query.get.map(_.head ==> (42, "foo", Map(pint3, pint4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).intMap(Map.empty[String, Int]).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).intMap(Map(pint1, pint2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).intMap().update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).intMap.add("a" -> int0).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).intMap.add("a" -> int0).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).intMap.add("a" -> int1).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1)) // "a" -> int1

        // Add new pair
        _ <- Ns(id).intMap.add(pint2).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Add multiple pairs with varargs
        _ <- Ns(id).intMap.add(pint3, pint4).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).intMap.add(List(pint5, pint6)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).intMap.add(Vector.empty[(String, Int)]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).intMap.remove(string1).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).update.transact

        // Remove pair by String key
        _ <- Ns(id).intMap.remove(string7).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing non-existing key has no effect
        _ <- Ns(id).intMap.remove(string9).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).intMap.remove(string6, string6).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).intMap.remove(string4, string5).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).intMap.remove(List(string2, string3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).intMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).intMap.remove(Seq(string1)).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
