package molecule.datalog.datomic.test.filterAttr.set

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object Semantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Optional attributes not allowed" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.ii.apply(Ns.ints_?)
      //      Ns.ii_.apply(Ns.ints_?)
      //      Ns.ii_?.apply(Ns.ints)
      //      Ns.ii_?.apply(Ns.ints_)
      //      Ns.ii_?.apply(Ns.ints_?)
      //
      //      Ns.ii.not(Ns.ints_?)
      //      Ns.ii_.not(Ns.ints_?)
      //      Ns.ii_?.not(Ns.ints)
      //      Ns.ii_?.not(Ns.ints_)
      //      Ns.ii_?.not(Ns.ints_?)
      //
      //      Ns.ii.hasLt(Ns.ints_?)
      //      Ns.ii_.hasLt(Ns.ints_?)
      //      Ns.ii_?.hasLt(Ns.ints)
      //      Ns.ii_?.hasLt(Ns.ints_)
      //      Ns.ii_?.hasLt(Ns.ints_?)
      //
      //      Ns.ii.hasLe(Ns.ints_?)
      //      Ns.ii_.hasLe(Ns.ints_?)
      //      Ns.ii_?.hasLe(Ns.ints)
      //      Ns.ii_?.hasLe(Ns.ints_)
      //      Ns.ii_?.hasLe(Ns.ints_?)
      //
      //      Ns.ii.hasGt(Ns.ints_?)
      //      Ns.ii_.hasGt(Ns.ints_?)
      //      Ns.ii_?.hasGt(Ns.ints)
      //      Ns.ii_?.hasGt(Ns.ints_)
      //      Ns.ii_?.hasGt(Ns.ints_?)
      //
      //      Ns.ii.hasGe(Ns.ints_?)
      //      Ns.ii_.hasGe(Ns.ints_?)
      //      Ns.ii_?.hasGe(Ns.ints)
      //      Ns.ii_?.hasGe(Ns.ints_)
      //      Ns.ii_?.hasGe(Ns.ints_?)

      compileError("Ns.ii.apply(Ns.ints_?)")
      compileError("Ns.ii_.apply(Ns.ints_?)")
      compileError("Ns.ii_?.apply(Ns.ints)")
      compileError("Ns.ii_?.apply(Ns.ints_)")
      compileError("Ns.ii_?.apply(Ns.ints_?)")

      compileError("Ns.ii.not(Ns.ints_?)")
      compileError("Ns.ii_.not(Ns.ints_?)")
      compileError("Ns.ii_?.not(Ns.ints)")
      compileError("Ns.ii_?.not(Ns.ints_)")
      compileError("Ns.ii_?.not(Ns.ints_?)")

      compileError("Ns.ii.hasLt(Ns.ints_?)")
      compileError("Ns.ii_.hasLt(Ns.ints_?)")
      compileError("Ns.ii_?.hasLt(Ns.ints)")
      compileError("Ns.ii_?.hasLt(Ns.ints_)")
      compileError("Ns.ii_?.hasLt(Ns.ints_?)")

      compileError("Ns.ii.hasLe(Ns.ints_?)")
      compileError("Ns.ii_.hasLe(Ns.ints_?)")
      compileError("Ns.ii_?.hasLe(Ns.ints)")
      compileError("Ns.ii_?.hasLe(Ns.ints_)")
      compileError("Ns.ii_?.hasLe(Ns.ints_?)")

      compileError("Ns.ii.hasGt(Ns.ints_?)")
      compileError("Ns.ii_.hasGt(Ns.ints_?)")
      compileError("Ns.ii_?.hasGt(Ns.ints)")
      compileError("Ns.ii_?.hasGt(Ns.ints_)")
      compileError("Ns.ii_?.hasGt(Ns.ints_?)")

      compileError("Ns.ii.hasGe(Ns.ints_?)")
      compileError("Ns.ii_.hasGe(Ns.ints_?)")
      compileError("Ns.ii_?.hasGe(Ns.ints)")
      compileError("Ns.ii_?.hasGe(Ns.ints_)")
      compileError("Ns.ii_?.hasGe(Ns.ints_?)")
    }


    "apply/not expects only cardinality set" - types { implicit conn =>
      // ok
      Ns.ii.apply(Ns.ints)
      Ns.ii.not(Ns.ints)

      // Prevented by type inference
      //      Ns.ii.apply(Ns.int)
      //      Ns.ii.apply(Ns.int_)
      //      Ns.ii_.apply(Ns.int)
      //      Ns.ii_.apply(Ns.int_)
      //
      //      Ns.ii.not(Ns.int)
      //      Ns.ii.not(Ns.int_)
      //      Ns.ii_.not(Ns.int)
      //      Ns.ii_.not(Ns.int_)

      compileError("Ns.ii.apply(Ns.int)")
      compileError("Ns.ii.apply(Ns.int_)")
      compileError("Ns.ii_.apply(Ns.int)")
      compileError("Ns.ii_.apply(Ns.int_)")

      compileError("Ns.ii.not(Ns.int)")
      compileError("Ns.ii.not(Ns.int_)")
      compileError("Ns.ii_.not(Ns.int)")
      compileError("Ns.ii_.not(Ns.int_)")
    }


    "has/hasNo accepts both cardinality one and set" - types { implicit conn =>
      // Card-one without/with expr ok
      Ns.ii.has(Ns.int)
      Ns.ii.has(Ns.int.<(2))
      Ns.ii.hasNo(Ns.int)
      Ns.ii.hasNo(Ns.int.<(2))

      // Card-set without expr ok
      Ns.ii.has(Ns.ints)
      Ns.ii.hasNo(Ns.ints)

      // Card-set with expr not allowed (prevented by type inference)
      //      Ns.ii.has(Ns.ints.not(Set(1)))
      //      Ns.ii.has(Ns.ints_.not(Set(1)))
      //      Ns.ii_.has(Ns.ints.not(Set(1)))
      //      Ns.ii_.has(Ns.ints_.not(Set(1)))
      //
      //      Ns.ii.hasNo(Ns.ints.not(Set(1)))
      //      Ns.ii.hasNo(Ns.ints_.not(Set(1)))
      //      Ns.ii_.hasNo(Ns.ints.not(Set(1)))
      //      Ns.ii_.hasNo(Ns.ints_.not(Set(1)))

      compileError("Ns.ii.has(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.has(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.has(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.has(Ns.ints_.not(Set(1)))")

      compileError("Ns.ii.hasNo(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.hasNo(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.hasNo(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.hasNo(Ns.ints_.not(Set(1)))")
    }

    "Comparisons accepts only cardinality one" - types { implicit conn =>
      // Card-one without/with expr ok
      Ns.ii.hasLt(Ns.int)
      Ns.ii.hasLt(Ns.int.<(2))

      // Card-set not allowed (prevented by type inference)

      //      Ns.ii.hasLt(Ns.ints)
      //
      //      Ns.ii.hasLt(Ns.ints.not(Set(1)))
      //      Ns.ii.hasLt(Ns.ints_.not(Set(1)))
      //      Ns.ii_.hasLt(Ns.ints.not(Set(1)))
      //      Ns.ii_.hasLt(Ns.ints_.not(Set(1)))
      //
      //      Ns.ii.hasLe(Ns.ints.not(Set(1)))
      //      Ns.ii.hasLe(Ns.ints_.not(Set(1)))
      //      Ns.ii_.hasLe(Ns.ints.not(Set(1)))
      //      Ns.ii_.hasLe(Ns.ints_.not(Set(1)))
      //
      //      Ns.ii.hasGt(Ns.ints.not(Set(1)))
      //      Ns.ii.hasGt(Ns.ints_.not(Set(1)))
      //      Ns.ii_.hasGt(Ns.ints.not(Set(1)))
      //      Ns.ii_.hasGt(Ns.ints_.not(Set(1)))
      //
      //      Ns.ii.hasGe(Ns.ints.not(Set(1)))
      //      Ns.ii.hasGe(Ns.ints_.not(Set(1)))
      //      Ns.ii_.hasGe(Ns.ints.not(Set(1)))
      //      Ns.ii_.hasGe(Ns.ints_.not(Set(1)))

      compileError("Ns.ii.hasLt(Ns.ints)")

      compileError("Ns.ii.hasLt(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.hasLt(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.hasLt(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.hasLt(Ns.ints_.not(Set(1)))")

      compileError("Ns.ii.hasLe(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.hasLe(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.hasLe(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.hasLe(Ns.ints_.not(Set(1)))")

      compileError("Ns.ii.hasGt(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.hasGt(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.hasGt(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.hasGt(Ns.ints_.not(Set(1)))")

      compileError("Ns.ii.hasGe(Ns.ints.not(Set(1)))")
      compileError("Ns.ii.hasGe(Ns.ints_.not(Set(1)))")
      compileError("Ns.ii_.hasGe(Ns.ints.not(Set(1)))")
      compileError("Ns.ii_.hasGe(Ns.ints_.not(Set(1)))")
    }


    "No expression inside cross-namespace definition" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.ints(Ref.ints_(Set(1))).Ref.ints
      //      Ns.i.ints(Ref.ints_.not(Set(1))).Ref.ints
      //      Ns.i.ints(Ref.ints_.has(Set(1))).Ref.ints
      //      Ns.i.ints(Ref.ints_.has(1)).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasNo(Set(1))).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasNo(1)).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasLt(1)).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasLe(1)).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasGt(1)).Ref.ints
      //      Ns.i.ints(Ref.ints_.hasGe(1)).Ref.ints

      compileError("Ns.i.ints(Ref.ints_.not(Set(1))).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.not(Set(1))).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.has(Set(1))).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.has(1)).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasNo(Set(1))).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasNo(1)).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasLt(1)).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasLe(1)).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasGt(1)).Ref.ints")
      compileError("Ns.i.ints(Ref.ints_.hasGe(1)).Ref.ints")
    }


    "Missing expression attributes" - types { implicit conn =>
      for {
        _ <- Ns.i.ints(Ref.ints_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Please add missing filter attributes:
              |  Ref.ints""".stripMargin
        }
      } yield ()
    }


    "Only tacit expression attribute for sets" - types { implicit conn =>
      for {
        _ <- Ns.i.ints(Ref.ints).Ref.ints.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attribute Ref.ints pointing to other namespace should be tacit."
        }
      } yield ()
    }


    "No expressions on card-set target attributes" - types { implicit conn =>
      for {
        _ <- Ns.int.ints(Ref.ints_).Ref.ints.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ref", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }
        _ <- Ns.int.ints.not(Set(2, 3)).Ref.ints(Ns.ints_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ns", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }

        _ <- Ns.int.ints.not(Ref.ints_).Ref.ints.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ref", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }
        _ <- Ns.int.ints.not(Set(2, 3)).Ref.ints.not(Ns.ints_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ns", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }

        _ <- Ns.int.ints.has(Ref.ints_).Ref.ints.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ref", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }
        _ <- Ns.int.ints.not(Set(2, 3)).Ref.ints.has(Ns.ints_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ns", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }

        _ <- Ns.int.ints.hasNo(Ref.ints_).Ref.ints.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ref", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }
        _ <- Ns.int.ints.not(Set(2, 3)).Ref.ints.hasNo(Ns.ints_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Cardinality-set filter attributes not allowed to do additional filtering. Found:
              |  AttrSetManInt("Ns", "ints", Neq, Seq(Set(2, 3)), None, None, Nil, Nil, None, None)""".stripMargin
        }
      } yield ()
    }
  }
}
