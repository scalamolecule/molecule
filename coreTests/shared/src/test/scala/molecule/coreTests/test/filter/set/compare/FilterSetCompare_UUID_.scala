// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_UUID_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(uuid1, uuid2))
      val b = (2, Set(uuid2, uuid3, uuid4))
      for {
        _ <- Ns.i.uuids.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.uuids.hasLt(uuid0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids.hasLt(uuid1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids.hasLt(uuid2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids.hasLt(uuid3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uuids.hasLe(uuid0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids.hasLe(uuid1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids.hasLe(uuid2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasLe(uuid3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uuids.hasGt(uuid0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasGt(uuid1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasGt(uuid2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uuids.hasGt(uuid3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uuids.hasGe(uuid0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasGe(uuid1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasGe(uuid2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids.hasGe(uuid3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.uuids.insert(List(
          (a, Set(uuid1, uuid2)),
          (b, Set(uuid2, uuid3, uuid4))
        )).transact

        // <
        _ <- Ns.i.a1.uuids_.hasLt(uuid0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_.hasLt(uuid1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_.hasLt(uuid2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids_.hasLt(uuid3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uuids_.hasLe(uuid0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_.hasLe(uuid1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids_.hasLe(uuid2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasLe(uuid3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uuids_.hasGt(uuid0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasGt(uuid1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasGt(uuid2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uuids_.hasGt(uuid3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uuids_.hasGe(uuid0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasGe(uuid1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasGe(uuid2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_.hasGe(uuid3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(uuid1, uuid2)))
      val b = (2, Some(Set(uuid2, uuid3, uuid4)))
      val c = (3, None)
      for {
        _ <- Ns.i.uuids_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids_?.hasLt(Some(uuid3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasLe(Some(uuid3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uuids_?.hasGt(Some(uuid3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGe(Some(uuid3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.uuids_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uuids_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}