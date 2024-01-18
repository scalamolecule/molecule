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
        List(x, y, z) <- A.i.ii.OwnB.ii.i.insert(a, b, c).transact.map(_.ids)

        _ <- A.i.ii_(B.ii_).OwnB.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))
        _ <- A.i.ii_.OwnB.ii(A.ii_).query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        // To get un-coalesced Sets, separate by ids
        _ <- A.id.a1.i.ii_(B.ii_).OwnB.ii.query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
        _ <- A.id.a1.i.ii_.OwnB.ii(A.ii_).query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
      } yield ()
    }


    "not equal - Sets that don't match other Sets" - refs { implicit conn =>
      for {
        _ <- A.i.ii.OwnB.ii.i.insert(a, b, c).transact

        _ <- A.i.ii_.not(B.ii_).OwnB.ii.query.get.map(_ ==> List(
          (1, Set(1, 2, 3))
        ))
        _ <- A.i.ii.not(B.ii_).OwnB.ii_.query.get.map(_ ==> List(
          (1, Set(1, 2))
        ))

        _ <- A.i.ii_.OwnB.ii.not(A.ii_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3))
        ))
        _ <- A.i.ii.OwnB.ii_.not(A.ii_).query.get.map(_ ==> List(
          (1, Set(1, 2))
        ))
      } yield ()
    }


    "has - Sets that contain all values of other Set" - refs { implicit conn =>
      for {
        _ <- A.i.ii.OwnB.ii.i.insert(a, b, c).transact

        _ <- A.i.ii_.has(B.ii_).OwnB.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))
        _ <- A.i.ii.has(B.ii_).OwnB.ii_.query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        _ <- A.i.a1.ii_.OwnB.ii.has(A.ii_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3)),
          (2, Set(2, 3, 4))
        ))
        _ <- A.i.a1.ii.OwnB.ii_.has(A.ii_).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4))
        ))
      } yield ()
    }

    "has - Sets that contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.ii.OwnB.ii.i.insert(a, b, c).transact

        _ <- A.i.ii_.has(B.i_).OwnB.ii.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))
        _ <- A.i.ii.has(B.i_).OwnB.ii_.i.a1.query.get.map(_ ==> List(
          (2, Set(2, 3), 3),
          (2, Set(4), 4),
        ))

        _ <- A.i.a1.ii_.OwnB.ii.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2, 3)),
          (2, Set(2, 3)),
        ))
        _ <- A.i.a1.ii.OwnB.ii_.has(A.i_).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))
      } yield ()
    }


    "hasNo - Sets that don't contain any values of other Set" - refs { implicit conn =>
      for {
        _ <- A.i.ii.OwnB.ii.i.insert(a, b, c, d).transact

        _ <- A.i.ii_.hasNo(B.ii_).OwnB.ii.query.get.map(_ ==> List(
          (2, Set(3))
        ))
        _ <- A.i.ii.hasNo(B.ii_).OwnB.ii_.query.get.map(_ ==> List(
          (2, Set(4))
        ))

        _ <- A.i.a1.ii_.OwnB.ii.hasNo(A.ii_).query.get.map(_ ==> List(
          (2, Set(3)),
        ))
        _ <- A.i.a1.ii.OwnB.ii_.hasNo(A.ii_).query.get.map(_ ==> List(
          (2, Set(4))
        ))
      } yield ()
    }

    "hasNo - Sets that don't contain value of other attribute" - refs { implicit conn =>
      for {
        _ <- A.i.ii.OwnB.ii.i.insert(a, b, c, d).transact

        _ <- A.i.ii_.hasNo(B.i_).OwnB.ii.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2, 3), 3),
        ))
        _ <- A.i.ii.hasNo(B.i_).OwnB.ii_.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2), 3),
        ))

        _ <- A.i.a1.ii_.OwnB.ii.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(3, 4)),
        ))
        _ <- A.i.a1.ii.OwnB.ii_.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(4)),
        ))
      } yield ()
    }
  }
}
