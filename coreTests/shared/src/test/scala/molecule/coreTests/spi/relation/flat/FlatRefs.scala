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


    "ids with ref attributes" - refs { implicit conn =>
      for {
        // Card one ref attr
        List(a, e1) <- A.i.B.i.insert(1, 2).transact.map(_.ids)
        _ <- A.i.b.query.get.map(_ ==> List((1, e1)))

        // Card many ref attr (returned as Set)
        List(_, e2) <- A.i.Bb.i.insert(1, 2).transact.map(_.ids)
        _ <- A.i.bb.query.get.map(_ ==> List((1, Set(e2))))
      } yield ()
    }


    "ids with owned ref attributes" - refs { implicit conn =>
      if (database == "MongoDB") {
        // Can't query for non-existing ids of embedded documents in MongoDB
        for {
          // Card one ref attr
          _ <- A.i.ownB.query.get
            .map(_ ==> "Unexpected success")
            .recover { case ModelError(err) =>
              err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
            }

          // Card many ref attr
          _ <- A.i.ownBb.query.get
            .map(_ ==> "Unexpected success")
            .recover { case ModelError(err) =>
              err ==> "Can't query for non-existing set of ids of embedded documents in MongoDB."
            }
        } yield ()

      } else {

        // Other databases
        for {
          // Card one ref attr
          List(_, e1) <- A.i.B.i.insert(1, 2).transact.map(_.ids)
          _ <- A.i.b.query.get.map(_ ==> List((1, e1)))

          // Card many ref attr (returned as Set)
          List(_, e2) <- A.i.Bb.i.insert(1, 2).transact.map(_.ids)
          _ <- A.i.bb.query.get.map(_ ==> List((1, Set(e2))))
        } yield ()
      }
    }


    "multiple card-many refs" - refs { implicit conn =>
      // Can't query for non-existing ids of embedded documents in MongoDB
      if (database != "MongoDB") {

        for {
          // Two entities to be referenced
          List(b1, b2) <- B.i.insert(1, 2).transact.map(_.ids)

          // Reference Set of entities
          _ <- A.i(0).bb(Set(b1, b2)).save.transact

          // Saving individual ref ids (not in a Set) is not allowed
          _ <- A.i(0).bb(b1, b2).save.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only save one Set of values for Set attribute `A.bb`. " +
                s"Found: Set($b1), Set($b2)"
            }

          // Referencing namespace attributes repeat for each referenced entity
          _ <- A.i.Bb.i.a1.query.get.map(_ ==> List(
            (0, 1),
            (0, 2), // 0 is repeated
          ))

          // Card many ref attributes return Set of ref ids
          _ <- A.i.bb.query.get.map(_ ==> List((0, Set(b1, b2))))
        } yield ()
      }
    }


    // Owned relationships are relevant to database with different
    // computational model for owned relationships (like MongoDB).

    "Flat owned" - {

      "one-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnB.i.insert(2, 3).i.transact
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "one-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnB.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.OwnB.ii.query.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }

      "many-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.i.insert(2, 3).i.transact
          _ <- A.i.OwnBb.i.query.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "many-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.OwnBb.ii.query.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }
    }


    "Flat embedded backref" - {
      "ref-ref" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).B.i(2)._A.C.i(3).save.transact
          _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))
        } yield ()
      }


      "embed-ref" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact
          _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))
        } yield ()
      }

      "embed-ref 2" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).D.i(4).save.transact
          _ <- A.i.OwnB.i._A.OwnC.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

          _ <- A.i(1).B.i(2)._A.OwnC.i(3).D.i(4).save.transact
          _ <- A.i.B.i._A.OwnC.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

          _ <- A.i(1).OwnB.i(2)._A.C.i(3).D.i(4).save.transact
          _ <- A.i.OwnB.i._A.C.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))
        } yield ()
      }


      "ref-embed" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).B.i(2)._A.OwnC.i(3).save.transact
          _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
        } yield ()
      }

      "ref-embed 2" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).B.i(2)._A.C.i(3).OwnD.i(4).save.transact
          _ <- A.i.B.i._A.C.i.OwnD.i.query.get.map(_ ==> List((1, 2, 3, 4)))
        } yield ()
      }


      "embed-embed" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.i.transact
          _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
        } yield ()
      }
    }
  }
}
