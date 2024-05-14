package molecule.coreTests.spi.relation.flat

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(1, 2).transact
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
      } yield ()
    }


    "ref with Set attribute" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.iSet.insert(
          (1, 2, Set.empty[Int]),
          (3, 4, Set(5, 6)),
        ).transact

        // A.i was inserted
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- B.i.a1.query.get.map(_ ==> List(2, 4))

        _ <- A.i.a1.B.i.iSet_?.query.get.map(_ ==> List(
          (1, 2, None), // Relationship to B exists since B.i has value 2
          (3, 4, Some(Set(5, 6)))
        ))

        _ <- A.i.a1.B.iSet_?.query.get.map(_ ==> List(
          (1, None),
          (3, Some(Set(5, 6))),
        ))
        _ <- A.i.B.iSet.query.get.map(_ ==> List(
          (3, Set(5, 6))
        ))
      } yield ()
    }


    "ref with only Set attribute" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(
          (1, Set.empty[Int]),
          (3, Set(5, 6)),
        ).transact

        // A.i was inserted
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))

        _ <- A.i.a1.B.iSet_?.query.get.map(_ ==> List(
          (1, None),
          (3, Some(Set(5, 6)))
        ))

        _ <- A.i.B.iSet.query.get.map(_ ==> List(
          (3, Set(5, 6))
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.Bb.i.insert(1, 2, 3).transact
        _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(1, 2, 3).transact
        _ <- A.i.B.C.i.query.get.map(_ ==> List((1, 3)))
      } yield ()
    }


    "complex" - refs { implicit conn =>
      for {
        _ <- A.i(0).s("a").B.i(1).s("b").Cc.i(22)
          ._B.C.i(2).s("c")
          ._B._A.Bb.i(11)
          .save.transact

        _ <- A.i.B.i.query.get.map(_ ==> List((0, 1)))
        _ <- A.i.B.i._A.s.query.get.map(_ ==> List((0, 1, "a")))
        _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((0, 1, 11)))
        _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((0, 1, 2, "b")))
        _ <- A.i.B.C.i.query.get.map(_ ==> List((0, 2)))
        _ <- A.i.B.C.i._B.i.query.get.map(_ ==> List((0, 2, 1)))
        _ <- A.i.B.i.C.i._B.Cc.i.query.get.map(_ ==> List((0, 1, 2, 22)))
        _ <- A.i.B.C.i._B.Cc.i.query.get.map(_ ==> List((0, 2, 22)))
        _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
        _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((0, 1, 2, "a")))
        _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((0, 2, "a")))
        _ <- A.i.B.i.C.i._B.s._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
        _ <- A.i.B.i.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, 11)))
        _ <- A.i.B.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 2, 11)))
        _ <- A.B.C.s._B._A.Bb.i.query.get.map(_ ==> List(("c", 11)))
      } yield ()
    }


    "Flattened nested data" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i).insert(
          (1, List(1, 2)),
          (2, List(3, 4)),
        ).transact

        // Nested
        _ <- A.i.Bb.*(B.i).query.get.map(_ ==> List(
          (1, List(1, 2)),
          (2, List(3, 4)),
        ))

        // Flat (A.i values repeated)
        _ <- A.i.Bb.i.query.get.map(_ ==> List(
          (1, 1),
          (1, 2),
          (2, 3),
          (2, 4),
        ))
      } yield ()
    }

    "Flattened nested data, 2 levels" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*(C.i)).insert(
          (1, List(
            (1, List(1, 2)),
            (2, List(2, 3)))),
          (2, List(
            (3, List(3, 4)),
            (4, List(4, 5)))),
        ).transact

        // Nested
        _ <- A.i.Bb.*(B.i.Cc.*(C.i)).query.get.map(_ ==> List(
          (1, List(
            (1, List(1, 2)),
            (2, List(2, 3)))),
          (2, List(
            (3, List(3, 4)),
            (4, List(4, 5)))),
        ))

        // Flat
        _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List(
          (1, 1, 1),
          (1, 1, 2),
          (1, 2, 2),
          (1, 2, 3),

          (2, 3, 3),
          (2, 3, 4),
          (2, 4, 4),
          (2, 4, 5),
        ))
      } yield ()
    }
  }
}
