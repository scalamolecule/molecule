// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2)).save.transact.map(_.id)
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).bigDecimalMap(Map(pbigDecimal3, pbigDecimal4)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal3, pbigDecimal4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).bigDecimalMap(Map.empty[String, BigDecimal]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        id <- Ns.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).bigDecimalMap().update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalMap(Map(pbigDecimal1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal2).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal2).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal3, pbigDecimal4).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).bigDecimalMap.add(Seq(pbigDecimal5, pbigDecimal6)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).bigDecimalMap.add(Seq.empty[(String, BigDecimal)]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6, pbigDecimal7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).bigDecimalMap.remove(string7).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Removing non-existing key has no effect
        _ <- Ns(id).bigDecimalMap.remove(string9).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).bigDecimalMap.remove(string6, string6).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).bigDecimalMap.remove(string4, string5).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).bigDecimalMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).bigDecimalMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).bigDecimalMap.remove(Seq(string1)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
