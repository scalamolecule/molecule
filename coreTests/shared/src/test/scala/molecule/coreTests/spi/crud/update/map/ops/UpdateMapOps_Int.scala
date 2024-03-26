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
        id <- Ns.intMap(Map(pint1, pint2)).save.transact.map(_.id)
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).intMap(Map(pint3, pint4)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint3, pint4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).intMap(Map.empty[String, Int]).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        id <- Ns.intMap(Map(pint1, pint2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).intMap().update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map(pint1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).intMap.add(pint2).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).intMap.add(pint2).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).intMap.add(pint3, pint4).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).intMap.add(Seq(pint5, pint6)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).intMap.add(Seq.empty[(String, Int)]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map(pint1, pint2, pint3, pint4, pint5, pint6, pint7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).intMap.remove(string7).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing non-existing key has no effect
        _ <- Ns(id).intMap.remove(string9).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).intMap.remove(string6, string6).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).intMap.remove(string4, string5).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).intMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).intMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).intMap.remove(Seq(string1)).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
