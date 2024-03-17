package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, Set(1, 2), Set(1, 2, 3), 3)
  val b = (2, Set(2, 3), Set(2, 3), 3)
  val c = (2, Set(4), Set(4), 4)

  val d = (2, Set(4), Set(3), 4)

  override lazy val tests = Tests {

    "equal (apply) - Sets that match other Sets" - refs { implicit conn =>
      for {
        List(_, a2, a3) <- A.i.iSet.OwnB.iSet.i.insert(a, b, c).transact.map(_.ids)
//        { result =>
//          if (database == "MongoDB") {
//            // Mongo has no separate ids for embedded/owned documents
//            result.ids
//          } else {
//            val List(a1, a2, a3) = result.ids
//            List(a1, a2, a3)
//          }
//        }

        _ <- A.i.iSet_(B.iSet_).OwnB.iSet.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))
        _ <- A.i.iSet_.OwnB.iSet(A.iSet_).query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        // To get un-coalesced Sets, separate by ids
        _ <- A.id.a1.i.iSet_(B.iSet_).OwnB.iSet.query.get.map(_ ==> List(
          (a2, 2, Set(2, 3)),
          (a3, 2, Set(4))
        ))
        _ <- A.id.a1.i.iSet_.OwnB.iSet(A.iSet_).query.get.map(_ ==> List(
          (a2, 2, Set(2, 3)),
          (a3, 2, Set(4))
        ))
      } yield ()
    }


    "not equal - Sets that don't match other Sets" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.OwnB.iSet.i.insert(a, b, c).transact

        _ <- A.i.iSet_.not(B.iSet_).OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(1, 2, 3))
        ))
        _ <- A.i.iSet.not(B.iSet_).OwnB.iSet_.query.get.map(_ ==> List(
          (1, Set(1, 2))
        ))

        _ <- A.i.iSet_.OwnB.iSet.not(A.iSet_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3))
        ))
        _ <- A.i.iSet.OwnB.iSet_.not(A.iSet_).query.get.map(_ ==> List(
          (1, Set(1, 2))
        ))
      } yield ()
    }


    "has - Sets that contain all values of other Set" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.OwnB.iSet.i.insert(a, b, c).transact

        _ <- A.i.iSet_.has(B.iSet_).OwnB.iSet.query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))
        _ <- A.i.iSet.has(B.iSet_).OwnB.iSet_.query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        _ <- A.i.a1.iSet_.OwnB.iSet.has(A.iSet_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3)),
          (2, Set(2, 3, 4))
        ))
        _ <- A.i.a1.iSet.OwnB.iSet_.has(A.iSet_).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4))
        ))
      } yield ()
    }

    "has - Sets that contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.OwnB.iSet.i.insert(a, b, c).transact

        _ <- A.i.iSet_.has(B.i_).OwnB.iSet.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))
        _ <- A.i.iSet.has(B.i_).OwnB.iSet_.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))

        _ <- A.i.a1.iSet_.OwnB.iSet.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3)),
          (2, Set(2, 3)),
        ))
        _ <- A.i.a1.iSet.OwnB.iSet_.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))
      } yield ()
    }


    "hasNo - Sets that don't contain any values of other Set" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.OwnB.iSet.i.insert(a, b, c, d).transact

        _ <- A.i.iSet_.hasNo(B.iSet_).OwnB.iSet.query.get.map(_ ==> List(
          (2, Set(3))
        ))
        _ <- A.i.iSet.hasNo(B.iSet_).OwnB.iSet_.query.get.map(_ ==> List(
          (2, Set(4))
        ))

        _ <- A.i.a1.iSet_.OwnB.iSet.hasNo(A.iSet_).query.get.map(_ ==> List(
          (2, Set(3)),
        ))
        _ <- A.i.a1.iSet.OwnB.iSet_.hasNo(A.iSet_).query.get.map(_ ==> List(
          (2, Set(4))
        ))
      } yield ()
    }

    "hasNo - Sets that don't contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.iSet.OwnB.iSet.i.insert(a, b, c, d).transact

        _ <- A.i.iSet_.hasNo(B.i_).OwnB.iSet.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2, 3), 3),
        ))
        _ <- A.i.iSet.hasNo(B.i_).OwnB.iSet_.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2), 3),
        ))

        _ <- A.i.a1.iSet_.OwnB.iSet.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(3, 4)),
        ))
        _ <- A.i.a1.iSet.OwnB.iSet_.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(4)),
        ))
      } yield ()
    }
  }
}
