package molecule.coreTests.spi.crud.update.set

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSet_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intSet_?.insert(
          (1, None),
          (1, Some(Set(1))),
          (2, Some(Set(2))),
        ).transact.map(_.ids)

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intSet(Set(3)).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intSet_?.query.get.map(_ ==> List(
          (a, 1, Some(Set(3))), // 3 inserted
          (b, 1, Some(Set(3))), // 1 updated to 3
          (c, 2, Some(Set(2))),
        ))
      } yield ()
    }


//    "Update filter value itself" - types { implicit conn =>
//      for {
//        _ <- Ns.intSet_(Set(42)).intSet(Set(1)).update.transact
//          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
//              """AttrSetTacInt("Ns", "intSet", Eq, Set(42), None, None, Nil, Nil, None, None, Seq(0, 32))"""
//          }
//      } yield ()
//    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).intSet(Set(4)).update.transact

        _ <- Ns.i.a1.intSet.query.get.map(_ ==> List(
          (1, Set(4)), // updated
          (2, Set(4)), // updated
          (3, Set(3)),
        ))
      } yield ()
    }
  }
}
