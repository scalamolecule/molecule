package molecule.coreTests.spi.crud.update.map

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait UpdateMap_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intMap_?.insert(
          (1, None),
          (1, Some(Map(pint1))),
          (2, Some(Map(pint2))),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intMap(Map(pint3)).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intMap_?.query.get.map(_ ==> List(
          (a, 1, Some(Map(pint3))), // 3 inserted
          (b, 1, Some(Map(pint3))), // 1 updated to 3
          (c, 2, Some(Map(pint2))),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        // Can't use map attributes as filter attributes
        _ <- Future(compileError("Ns.intMap_(Map(pint1)).intMap(Map(pint1)).update.transact"))
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.intMap.insert(
          (1, Map(pint1)),
          (2, Map(pint2)),
          (3, Map(pint3)),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).intMap(Map(pint4)).update.transact

        _ <- Ns.i.a1.intMap.query.get.map(_ ==> List(
          (1, Map(pint4)), // updated
          (2, Map(pint4)), // updated
          (3, Map(pint3)),
        ))
      } yield ()
    }
  }
}
