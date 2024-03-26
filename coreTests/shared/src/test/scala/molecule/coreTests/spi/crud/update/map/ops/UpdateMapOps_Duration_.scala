// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.durationMap(Map(pduration1, pduration2)).save.transact.map(_.id)
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).durationMap(Map(pduration3, pduration4)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration3, pduration4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).durationMap(Map.empty[String, Duration]).update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        id <- Ns.durationMap(Map(pduration1, pduration2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).durationMap().update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.durationMap(Map(pduration1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).durationMap.add(pduration2).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).durationMap.add(pduration2).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).durationMap.add(pduration3, pduration4).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).durationMap.add(Seq(pduration5, pduration6)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).durationMap.add(Seq.empty[(String, Duration)]).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.durationMap(Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6, pduration7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).durationMap.remove(string7).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Removing non-existing key has no effect
        _ <- Ns(id).durationMap.remove(string9).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).durationMap.remove(string6, string6).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).durationMap.remove(string4, string5).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).durationMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).durationMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).durationMap.remove(Seq(string1)).update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
