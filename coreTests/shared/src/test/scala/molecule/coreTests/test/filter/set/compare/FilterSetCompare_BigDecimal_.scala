// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_BigDecimal_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(bigDecimal1, bigDecimal2))
      val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
      for {
        _ <- Ns.i.bigDecimals.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.bigDecimals.hasLt(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals.hasLt(bigDecimal1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals.hasLt(bigDecimal2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals.hasLt(bigDecimal3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigDecimals.hasLe(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals.hasLe(bigDecimal1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals.hasLe(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasLe(bigDecimal3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigDecimals.hasGt(bigDecimal0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasGt(bigDecimal1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasGt(bigDecimal2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigDecimals.hasGt(bigDecimal3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigDecimals.hasGe(bigDecimal0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasGe(bigDecimal1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasGe(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals.hasGe(bigDecimal3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (a, Set(bigDecimal1, bigDecimal2)),
          (b, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        )).transact

        // <
        _ <- Ns.i.a1.bigDecimals_.hasLt(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_.hasLt(bigDecimal1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_.hasLt(bigDecimal2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals_.hasLt(bigDecimal3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigDecimals_.hasLe(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_.hasLe(bigDecimal1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals_.hasLe(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasLe(bigDecimal3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigDecimals_.hasGt(bigDecimal0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasGt(bigDecimal1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasGt(bigDecimal2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigDecimals_.hasGt(bigDecimal3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigDecimals_.hasGe(bigDecimal0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasGe(bigDecimal1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasGe(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_.hasGe(bigDecimal3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
      val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
      val c = (3, None)
      for {
        _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.bigDecimals_?.hasLt(Some(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_?.hasLt(Some(bigDecimal1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_?.hasLt(Some(bigDecimal2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals_?.hasLt(Some(bigDecimal3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigDecimals_?.hasLe(Some(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_?.hasLe(Some(bigDecimal1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimals_?.hasLe(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasLe(Some(bigDecimal3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigDecimals_?.hasGt(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGt(Some(bigDecimal1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGt(Some(bigDecimal2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigDecimals_?.hasGt(Some(bigDecimal3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigDecimals_?.hasGe(Some(bigDecimal0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGe(Some(bigDecimal1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGe(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGe(Some(bigDecimal3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.bigDecimals_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimals_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}