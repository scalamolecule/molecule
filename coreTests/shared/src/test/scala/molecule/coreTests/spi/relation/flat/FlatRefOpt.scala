package molecule.coreTests.spi.relation.flat

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefOpt extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Basic optional ref" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact

        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
        ))


        _ <- A.i(2).B.i(3).save.transact

        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(3)),
        ))


        _ <- A.i.B.i.query.get.map(_ ==> List(
          (2, 3),
        ))
      } yield ()
    }

    // same as:
    "Basic optional ref with opt ref insert" - refs { implicit conn =>
      for {
        _ <- A.i.B.?(B.i).insert(List(
          (1, None),
          (2, Some(20)),
        )).transact

        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(20)),
        ))

        _ <- A.i.B.i.query.get.map(_ ==> List(
          (2, 20),
        ))
      } yield ()
    }


    "Only optional attributes in optional ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.?(B.i_?).insert(List(
          (1, None), // no relationship created
          (2, Some(None)), // relationship to empty row created
          (3, Some(Some(30))),
        )).transact

        // Confirming only 2 relationships created
        _ <- A.b(count).query.get.map(_.head ==> 2)

        // No relationship and empty row are indistinguishable
        // when all optional ref attributes (B.i_?) are optional
        _ <- A.i.a1.B.?(B.i_?).query.get.map(_ ==> List(
          (1, Some(None)), // no relationship
          (2, Some(None)), // relationship to empty ref row
          (3, Some(Some(30))),
        ))

        // Mandatory B.i makes result more clear
        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(30)),
        ))
      } yield ()
    }


    "Mix man/opt attributes in opt ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.?(B.s.i_?).insert(List(
          (1, None),
          (2, Some(("a", None))),
          (3, Some(("b", Some(4)))),
        )).transact


        _ <- A.i.a1.B.?(B.s.i_?).query.get.map(_ ==> List(
          (1, None),
          (2, Some(("a", None))),
          (3, Some(("b", Some(4)))),
        ))

        _ <- A.i.a1.B.?(B.s.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(("b", 4))),
        ))


        _ <- A.i.a1.B.s.i_?.query.get.map(_ ==> List(
          (2, "a", None),
          (3, "b", Some(4)),
        ))

        _ <- A.i.B.s.i.query.get.map(_ ==> List(
          (3, "b", 4),
        ))
      } yield ()
    }


    "Ref after opt ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.?(B.i.s).C.i.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only further optional refs allowed after optional ref."
          }

        // Do this instead if C is a ref from B
        _ <- A.i.B.?(B.i.s.C.i).query.get

        // or this, if both B and C are refs from A
        _ <- A.i.C.i._A.B.?(B.i.s).query.get

        _ <- A.i.B.?(B.i.s).Cc.*(C.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only further optional refs allowed after optional ref."
          }
      } yield ()
    }


    "Ref inside opt ref" - refs { implicit conn =>
      for {
        _ <- A.i.insert(
          1
        ).transact

        _ <- A.i.B.i.insert(
          2, 2
        ).transact

        _ <- A.i.B.i.C.i.insert(
          3, 3, 3
        ).transact


        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(2)),
          (3, Some(3)),
        ))

        _ <- A.i.a1.B.?(B.i.C.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some((3, 3))),
        ))

        _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
          (3, 3, 3),
        ))
      } yield ()
    }


    "Card-many ref inside opt ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.s.i.Cc.*(C.s).insert(List(
          (1, "a", 1, Nil),
          (2, "b", 2, List("x", "y"))
        )).transact

        // Mandatory ref from B to C excludes first row
        _ <- A.i.B.?(B.s.i.Cc.s).query.get.map(_ ==> List(
          // (1, None),
          (2, Some(("b", 2, "x"))),
          (2, Some(("b", 2, "y"))), // (A and B values repeated)
        ))

        // As with card-one ref, a normal flat card-many ref would be preferred
        _ <- A.i.B.s.i.Cc.s.query.get.map(_ ==> List(
          (2, "b", 2, "x"),
          (2, "b", 2, "y"),
        ))
        // or better, a nested query
        _ <- A.i.B.s.i.Cc.*(C.s).query.get.map(_ ==> List(
          (2, "b", 2, List("x", "y")),
        ))

        _ <- A.i.B.?(B.i.s.Cc.*(C.s)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many nesting not allowed inside optional ref."
          }
      } yield ()
    }
  }
}
