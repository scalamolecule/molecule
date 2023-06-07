// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Long_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(long1, long2))
      val b = (2, Set(long2, long3, long4))
      for {
        _ <- Ns.i.longs.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.longs.hasLt(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs.hasLt(long1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs.hasLt(long2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs.hasLt(long3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.longs.hasLe(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs.hasLe(long1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs.hasLe(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasLe(long3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.longs.hasGt(long0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasGt(long1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasGt(long2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.longs.hasGt(long3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.longs.hasGe(long0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasGe(long1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasGe(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs.hasGe(long3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.longs.insert(List(
          (a, Set(long1, long2)),
          (b, Set(long2, long3, long4))
        )).transact

        // <
        _ <- Ns.i.a1.longs_.hasLt(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_.hasLt(long1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_.hasLt(long2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs_.hasLt(long3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.longs_.hasLe(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_.hasLe(long1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs_.hasLe(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasLe(long3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.longs_.hasGt(long0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasGt(long1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasGt(long2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.longs_.hasGt(long3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.longs_.hasGe(long0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasGe(long1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasGe(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_.hasGe(long3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(long1, long2)))
      val b = (2, Some(Set(long2, long3, long4)))
      val c = (3, None)
      for {
        _ <- Ns.i.longs_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.longs_?.hasLt(Some(long0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_?.hasLt(Some(long1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_?.hasLt(Some(long2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs_?.hasLt(Some(long3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.longs_?.hasLe(Some(long0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.longs_?.hasLe(Some(long1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.longs_?.hasLe(Some(long2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasLe(Some(long3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.longs_?.hasGt(Some(long0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGt(Some(long1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGt(Some(long2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.longs_?.hasGt(Some(long3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.longs_?.hasGe(Some(long0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGe(Some(long1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGe(Some(long2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGe(Some(long3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.longs_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.longs_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}