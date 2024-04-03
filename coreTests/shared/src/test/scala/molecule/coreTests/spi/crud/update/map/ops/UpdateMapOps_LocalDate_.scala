// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateMap(Map(plocalDate1, plocalDate2)).save.transact.map(_.id)
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).localDateMap(Map(plocalDate3, plocalDate4)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate3, plocalDate4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localDateMap(Map.empty[String, LocalDate]).update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        id <- Ns.localDateMap(Map(plocalDate1, plocalDate2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).localDateMap().update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateMap(Map("a" -> localDate0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).localDateMap.add("a" -> localDate1).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.localDateMap.iMap_?.query.get.map(_ ==> List((Map(plocalDate1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.localDateMap.iMap_?.query.get.map(_ ==> List((Map(plocalDate1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).localDateMap.add(plocalDate2).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).localDateMap.add(plocalDate3, plocalDate4).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).localDateMap.add(Seq(plocalDate5, plocalDate6)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).localDateMap.add(Seq.empty[(String, LocalDate)]).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateMap(Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6, plocalDate7, plocalDate8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).localDateMap.remove(string8).update.transact
        _ <- Ns(id).localDateMap.remove(string7).upsert.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.localDateMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.localDateMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).localDateMap.remove(string9).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).localDateMap.remove(string6, string6).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).localDateMap.remove(string4, string5).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).localDateMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).localDateMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).localDateMap.remove(Seq(string1)).update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
