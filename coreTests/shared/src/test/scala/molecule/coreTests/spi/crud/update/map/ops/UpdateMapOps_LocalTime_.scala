// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localTimeMap(Map(plocalTime1, plocalTime2)).save.transact.map(_.id)
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).localTimeMap(Map(plocalTime3, plocalTime4)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime3, plocalTime4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localTimeMap(Map.empty[String, LocalTime]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        id <- Ns.localTimeMap(Map(plocalTime1, plocalTime2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).localTimeMap().update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localTimeMap(Map(plocalTime1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).localTimeMap.add(plocalTime2).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).localTimeMap.add(plocalTime2).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).localTimeMap.add(plocalTime3, plocalTime4).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).localTimeMap.add(Seq(plocalTime5, plocalTime6)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).localTimeMap.add(Seq.empty[(String, LocalTime)]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localTimeMap(Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6, plocalTime7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).localTimeMap.remove(string7).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).localTimeMap.remove(string9).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).localTimeMap.remove(string6, string6).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).localTimeMap.remove(string4, string5).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).localTimeMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).localTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).localTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
