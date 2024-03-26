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
        id <- Ns.floatMap(Map(pfloat1, pfloat2)).save.transact.map(_.id)
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).floatMap(Map(pfloat3, pfloat4)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat3, pfloat4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).floatMap(Map.empty[String, Float]).update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)

        id <- Ns.floatMap(Map(pfloat1, pfloat2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).floatMap().update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.floatMap(Map(pfloat1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).floatMap.add(pfloat2).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).floatMap.add(pfloat2).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).floatMap.add(pfloat3, pfloat4).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).floatMap.add(Seq(pfloat5, pfloat6)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).floatMap.add(Seq.empty[(String, Float)]).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.floatMap(Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6, pfloat7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).floatMap.remove(string7).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Removing non-existing key has no effect
        _ <- Ns(id).floatMap.remove(string9).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).floatMap.remove(string6, string6).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3, pfloat4, pfloat5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).floatMap.remove(string4, string5).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).floatMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).floatMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).floatMap.remove(Seq(string1)).update.transact
        _ <- Ns.floatMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
