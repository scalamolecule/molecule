// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_BigInt_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(bigInt1, bigInt2))
      val b = (2, Set(bigInt2, bigInt3, bigInt4))
      for {
        _ <- Ns.i.bigInts.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.bigInts.hasLt(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts.hasLt(bigInt1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts.hasLt(bigInt2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts.hasLt(bigInt3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigInts.hasLe(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts.hasLe(bigInt1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts.hasLe(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasLe(bigInt3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigInts.hasGt(bigInt0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasGt(bigInt1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasGt(bigInt2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigInts.hasGt(bigInt3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigInts.hasGe(bigInt0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasGe(bigInt1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasGe(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts.hasGe(bigInt3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.bigInts.insert(List(
          (a, Set(bigInt1, bigInt2)),
          (b, Set(bigInt2, bigInt3, bigInt4))
        )).transact

        // <
        _ <- Ns.i.a1.bigInts_.hasLt(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_.hasLt(bigInt1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_.hasLt(bigInt2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts_.hasLt(bigInt3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigInts_.hasLe(bigInt0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_.hasLe(bigInt1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts_.hasLe(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasLe(bigInt3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigInts_.hasGt(bigInt0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasGt(bigInt1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasGt(bigInt2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigInts_.hasGt(bigInt3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigInts_.hasGe(bigInt0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasGe(bigInt1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasGe(bigInt2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_.hasGe(bigInt3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(bigInt1, bigInt2)))
      val b = (2, Some(Set(bigInt2, bigInt3, bigInt4)))
      val c = (3, None)
      for {
        _ <- Ns.i.bigInts_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts_?.hasLt(Some(bigInt3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasLe(Some(bigInt3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bigInts_?.hasGt(Some(bigInt3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGe(Some(bigInt3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.bigInts_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigInts_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}