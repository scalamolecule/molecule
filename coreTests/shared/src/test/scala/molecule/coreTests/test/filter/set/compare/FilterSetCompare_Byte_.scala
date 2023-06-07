// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Byte_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(byte1, byte2))
      val b = (2, Set(byte2, byte3, byte4))
      for {
        _ <- Ns.i.bytes.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.bytes.hasLt(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes.hasLt(byte1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes.hasLt(byte2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes.hasLt(byte3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bytes.hasLe(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes.hasLe(byte1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes.hasLe(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasLe(byte3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bytes.hasGt(byte0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasGt(byte1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasGt(byte2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bytes.hasGt(byte3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bytes.hasGe(byte0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasGe(byte1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasGe(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes.hasGe(byte3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.bytes.insert(List(
          (a, Set(byte1, byte2)),
          (b, Set(byte2, byte3, byte4))
        )).transact

        // <
        _ <- Ns.i.a1.bytes_.hasLt(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_.hasLt(byte1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_.hasLt(byte2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes_.hasLt(byte3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bytes_.hasLe(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_.hasLe(byte1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes_.hasLe(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasLe(byte3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bytes_.hasGt(byte0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasGt(byte1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasGt(byte2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bytes_.hasGt(byte3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bytes_.hasGe(byte0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasGe(byte1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasGe(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_.hasGe(byte3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(byte1, byte2)))
      val b = (2, Some(Set(byte2, byte3, byte4)))
      val c = (3, None)
      for {
        _ <- Ns.i.bytes_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.bytes_?.hasLt(Some(byte0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_?.hasLt(Some(byte1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_?.hasLt(Some(byte2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes_?.hasLt(Some(byte3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.bytes_?.hasLe(Some(byte0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bytes_?.hasLe(Some(byte1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bytes_?.hasLe(Some(byte2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasLe(Some(byte3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.bytes_?.hasGt(Some(byte0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGt(Some(byte1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGt(Some(byte2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.bytes_?.hasGt(Some(byte3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.bytes_?.hasGe(Some(byte0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGe(Some(byte1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGe(Some(byte2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGe(Some(byte3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.bytes_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bytes_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}