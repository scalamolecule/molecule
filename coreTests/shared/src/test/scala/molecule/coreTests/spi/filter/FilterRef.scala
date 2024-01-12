package molecule.coreTests.spi.filter

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import molecule.coreTests.dataModels.core.dsl.Refs._

trait FilterRef extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "B.i" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact

        _ <- A.i_.B.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i_.B.i(2).query.get.map(_ ==> List(2))
        _ <- A.i_.B.i.not(2).a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i_.B.i.<(2).query.get.map(_ ==> List(1))
        _ <- A.i_.B.i.>(2).query.get.map(_ ==> List(3))
        _ <- A.i_.B.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
        _ <- A.i_.B.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

        _ <- A.i.a1.B.i_.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.B.i_(2).query.get.map(_ ==> List(2))
        _ <- A.i.a1.B.i_.not(2).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.B.i_.<(2).query.get.map(_ ==> List(1))
        _ <- A.i.a1.B.i_.>(2).query.get.map(_ ==> List(3))
        _ <- A.i.a1.B.i_.<=(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.B.i_.>=(2).query.get.map(_ ==> List(2, 3))

        _ <- A.i_.B.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
        _ <- A.i_.B.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
        _ <- A.i_.B.i_?.not(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(3)))
        _ <- A.i_.B.i_?.<(Some(2)).query.get.map(_ ==> List(Some(1)))
        _ <- A.i_.B.i_?.>(Some(2)).query.get.map(_ ==> List(Some(3)))
        _ <- A.i_.B.i_?.<=(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(2)))
        _ <- A.i_.B.i_?.>=(Some(2)).a1.query.get.map(_ ==> List(Some(2), Some(3)))
      } yield ()
    }

    "B.ii" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact

        _ <- A.i_.B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
        _ <- A.i_.B.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.B.ii.not(Set(2)).query.get.map(_ ==> List(Set(1), Set(3)))
        _ <- A.i_.B.ii.has(2).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.B.ii.hasNo(2).query.get.map(_ ==> List(Set(1), Set(3)))

        _ <- A.i.a1.B.ii_.query.get.map(_            ==> List(1, 2, 3))
        _ <- A.i.a1.B.ii_(Set(2)).query.get.map(_    ==> List(2))
        _ <- A.i.a1.B.ii_.not(Set(2)).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.B.ii_.has(2).query.get.map(_      ==> List(2))
        _ <- A.i.a1.B.ii_.hasNo(2).query.get.map(_   ==> List(1, 3))

        _ <- A.i_.B.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
        _ <- A.i_.B.ii_?(Some(Set(2))).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.B.ii_?.not(Some(Set(2))).query.get.map(_ ==> List(Some(Set(1)),Some(Set(3))))
        _ <- A.i_.B.ii_?.has(Some(2)).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.B.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(Some(Set(1)), Some(Set(3))))
      } yield ()
    }


    "OwnB.i" - refs { implicit conn =>
      for {
        _ <- A.i.OwnB.i.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact

        _ <- A.i_.OwnB.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i_.OwnB.i(2).query.get.map(_ ==> List(2))
        _ <- A.i_.OwnB.i.not(2).a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i_.OwnB.i.<(2).query.get.map(_ ==> List(1))
        _ <- A.i_.OwnB.i.>(2).query.get.map(_ ==> List(3))
        _ <- A.i_.OwnB.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
        _ <- A.i_.OwnB.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

        _ <- A.i.a1.OwnB.i_.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.OwnB.i_(2).query.get.map(_ ==> List(2))
        _ <- A.i.a1.OwnB.i_.not(2).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.OwnB.i_.<(2).query.get.map(_ ==> List(1))
        _ <- A.i.a1.OwnB.i_.>(2).query.get.map(_ ==> List(3))
        _ <- A.i.a1.OwnB.i_.<=(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.OwnB.i_.>=(2).query.get.map(_ ==> List(2, 3))

        _ <- A.i_.OwnB.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
        _ <- A.i_.OwnB.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
        _ <- A.i_.OwnB.i_?.not(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(3)))
        _ <- A.i_.OwnB.i_?.<(Some(2)).query.get.map(_ ==> List(Some(1)))
        _ <- A.i_.OwnB.i_?.>(Some(2)).query.get.map(_ ==> List(Some(3)))
        _ <- A.i_.OwnB.i_?.<=(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(2)))
        _ <- A.i_.OwnB.i_?.>=(Some(2)).a1.query.get.map(_ ==> List(Some(2), Some(3)))
      } yield ()
    }

    "OwnB.ii" - refs { implicit conn =>
      for {
        _ <- A.i.OwnB.ii.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact

        _ <- A.i_.OwnB.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
        _ <- A.i_.OwnB.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.OwnB.ii.not(Set(2)).query.get.map(_ ==> List(Set(1), Set(3)))
        _ <- A.i_.OwnB.ii.has(2).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.OwnB.ii.hasNo(2).query.get.map(_ ==> List(Set(1), Set(3)))

        _ <- A.i.a1.OwnB.ii_.query.get.map(_            ==> List(1, 2, 3))
        _ <- A.i.a1.OwnB.ii_(Set(2)).query.get.map(_    ==> List(2))
        _ <- A.i.a1.OwnB.ii_.not(Set(2)).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.OwnB.ii_.has(2).query.get.map(_      ==> List(2))
        _ <- A.i.a1.OwnB.ii_.hasNo(2).query.get.map(_   ==> List(1, 3))

        _ <- A.i_.OwnB.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
        _ <- A.i_.OwnB.ii_?(Some(Set(2))).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.OwnB.ii_?.not(Some(Set(2))).query.get.map(_ ==> List(Some(Set(1)),Some(Set(3))))
        _ <- A.i_.OwnB.ii_?.has(Some(2)).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.OwnB.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(Some(Set(1)), Some(Set(3))))
      } yield ()
    }


    "Bb.i" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.i.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact

        _ <- A.i_.Bb.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i_.Bb.i(2).query.get.map(_ ==> List(2))
        _ <- A.i_.Bb.i.not(2).a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i_.Bb.i.<(2).query.get.map(_ ==> List(1))
        _ <- A.i_.Bb.i.>(2).query.get.map(_ ==> List(3))
        _ <- A.i_.Bb.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
        _ <- A.i_.Bb.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

        _ <- A.i.a1.Bb.i_.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.Bb.i_(2).query.get.map(_ ==> List(2))
        _ <- A.i.a1.Bb.i_.not(2).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.Bb.i_.<(2).query.get.map(_ ==> List(1))
        _ <- A.i.a1.Bb.i_.>(2).query.get.map(_ ==> List(3))
        _ <- A.i.a1.Bb.i_.<=(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.Bb.i_.>=(2).query.get.map(_ ==> List(2, 3))

        _ <- A.i_.Bb.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
        _ <- A.i_.Bb.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
        _ <- A.i_.Bb.i_?.not(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(3)))
        _ <- A.i_.Bb.i_?.<(Some(2)).query.get.map(_ ==> List(Some(1)))
        _ <- A.i_.Bb.i_?.>(Some(2)).query.get.map(_ ==> List(Some(3)))
        _ <- A.i_.Bb.i_?.<=(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(2)))
        _ <- A.i_.Bb.i_?.>=(Some(2)).a1.query.get.map(_ ==> List(Some(2), Some(3)))
      } yield ()
    }

    "Bb.ii" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.ii.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact

        _ <- A.i_.Bb.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
        _ <- A.i_.Bb.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.Bb.ii.not(Set(2)).query.get.map(_ ==> List(Set(1), Set(3)))
        _ <- A.i_.Bb.ii.has(2).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.Bb.ii.hasNo(2).query.get.map(_ ==> List(Set(1), Set(3)))

        _ <- A.i.a1.Bb.ii_.query.get.map(_            ==> List(1, 2, 3))
        _ <- A.i.a1.Bb.ii_(Set(2)).query.get.map(_    ==> List(2))
        _ <- A.i.a1.Bb.ii_.not(Set(2)).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.Bb.ii_.has(2).query.get.map(_      ==> List(2))
        _ <- A.i.a1.Bb.ii_.hasNo(2).query.get.map(_   ==> List(1, 3))

        _ <- A.i_.Bb.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
        _ <- A.i_.Bb.ii_?(Some(Set(2))).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.Bb.ii_?.not(Some(Set(2))).query.get.map(_ ==> List(Some(Set(1)),Some(Set(3))))
        _ <- A.i_.Bb.ii_?.has(Some(2)).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.Bb.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(Some(Set(1)), Some(Set(3))))
      } yield ()
    }


    "OwnBb.i" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.i.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact

        _ <- A.i_.OwnBb.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i_.OwnBb.i(2).query.get.map(_ ==> List(2))
        _ <- A.i_.OwnBb.i.not(2).a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i_.OwnBb.i.<(2).query.get.map(_ ==> List(1))
        _ <- A.i_.OwnBb.i.>(2).query.get.map(_ ==> List(3))
        _ <- A.i_.OwnBb.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
        _ <- A.i_.OwnBb.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

        _ <- A.i.a1.OwnBb.i_.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.OwnBb.i_(2).query.get.map(_ ==> List(2))
        _ <- A.i.a1.OwnBb.i_.not(2).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.OwnBb.i_.<(2).query.get.map(_ ==> List(1))
        _ <- A.i.a1.OwnBb.i_.>(2).query.get.map(_ ==> List(3))
        _ <- A.i.a1.OwnBb.i_.<=(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.OwnBb.i_.>=(2).query.get.map(_ ==> List(2, 3))

        _ <- A.i_.OwnBb.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
        _ <- A.i_.OwnBb.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
        _ <- A.i_.OwnBb.i_?.not(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(3)))
        _ <- A.i_.OwnBb.i_?.<(Some(2)).query.get.map(_ ==> List(Some(1)))
        _ <- A.i_.OwnBb.i_?.>(Some(2)).query.get.map(_ ==> List(Some(3)))
        _ <- A.i_.OwnBb.i_?.<=(Some(2)).a1.query.get.map(_ ==> List(Some(1), Some(2)))
        _ <- A.i_.OwnBb.i_?.>=(Some(2)).a1.query.get.map(_ ==> List(Some(2), Some(3)))
      } yield ()
    }

    "OwnBb.ii" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.ii.insert(
          (1, Set(1)),
          (2, Set(2)),
          (3, Set(3)),
        ).transact

        _ <- A.i_.OwnBb.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
        _ <- A.i_.OwnBb.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.OwnBb.ii.not(Set(2)).query.get.map(_ ==> List(Set(1), Set(3)))
        _ <- A.i_.OwnBb.ii.has(2).query.get.map(_ ==> List(Set(2)))
        _ <- A.i_.OwnBb.ii.hasNo(2).query.get.map(_ ==> List(Set(1), Set(3)))

        _ <- A.i.a1.OwnBb.ii_.query.get.map(_            ==> List(1, 2, 3))
        _ <- A.i.a1.OwnBb.ii_(Set(2)).query.get.map(_    ==> List(2))
        _ <- A.i.a1.OwnBb.ii_.not(Set(2)).query.get.map(_ ==> List(1, 3))
        _ <- A.i.a1.OwnBb.ii_.has(2).query.get.map(_      ==> List(2))
        _ <- A.i.a1.OwnBb.ii_.hasNo(2).query.get.map(_   ==> List(1, 3))

        _ <- A.i_.OwnBb.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
        _ <- A.i_.OwnBb.ii_?(Some(Set(2))).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.OwnBb.ii_?.not(Some(Set(2))).query.get.map(_ ==> List(Some(Set(1)),Some(Set(3))))
        _ <- A.i_.OwnBb.ii_?.has(Some(2)).query.get.map(_ ==> List(Some(Set(2))))
        _ <- A.i_.OwnBb.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(Some(Set(1)), Some(Set(3))))
      } yield ()
    }
  }
}
