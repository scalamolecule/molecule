package molecule.datomic.test.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._

object Semantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Optional attributes not allowed" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.apply(Ns.int_?)
      //      Ns.i_.apply(Ns.int_?)
      //      Ns.i_?.apply(Ns.int)
      //      Ns.i_?.apply(Ns.int_)
      //      Ns.i_?.apply(Ns.int_?)
      //
      //      Ns.i.not(Ns.int_?)
      //      Ns.i_.not(Ns.int_?)
      //      Ns.i_?.not(Ns.int)
      //      Ns.i_?.not(Ns.int_)
      //      Ns.i_?.not(Ns.int_?)
      //
      //      Ns.i.<(Ns.int_?)
      //      Ns.i_.<(Ns.int_?)
      //      Ns.i_?.<(Ns.int)
      //      Ns.i_?.<(Ns.int_)
      //      Ns.i_?.<(Ns.int_?)
      //
      //      Ns.i.<=(Ns.int_?)
      //      Ns.i_.<=(Ns.int_?)
      //      Ns.i_?.<=(Ns.int)
      //      Ns.i_?.<=(Ns.int_)
      //      Ns.i_?.<=(Ns.int_?)
      //
      //      Ns.i.>(Ns.int_?)
      //      Ns.i_.>(Ns.int_?)
      //      Ns.i_?.>(Ns.int)
      //      Ns.i_?.>(Ns.int_)
      //      Ns.i_?.>(Ns.int_?)
      //
      //      Ns.i.>=(Ns.int_?)
      //      Ns.i_.>=(Ns.int_?)
      //      Ns.i_?.>=(Ns.int)
      //      Ns.i_?.>=(Ns.int_)
      //      Ns.i_?.>=(Ns.int_?)

      compileError("Ns.i.apply(Ns.int_?)")
      compileError("Ns.i_.apply(Ns.int_?)")
      compileError("Ns.i_?.apply(Ns.int)")
      compileError("Ns.i_?.apply(Ns.int_)")
      compileError("Ns.i_?.apply(Ns.int_?)")

      compileError("Ns.i.not(Ns.int_?)")
      compileError("Ns.i_.not(Ns.int_?)")
      compileError("Ns.i_?.not(Ns.int)")
      compileError("Ns.i_?.not(Ns.int_)")
      compileError("Ns.i_?.not(Ns.int_?)")

      compileError("Ns.i.<(Ns.int_?)")
      compileError("Ns.i_.<(Ns.int_?)")
      compileError("Ns.i_?.<(Ns.int)")
      compileError("Ns.i_?.<(Ns.int_)")
      compileError("Ns.i_?.<(Ns.int_?)")

      compileError("Ns.i.<=(Ns.int_?)")
      compileError("Ns.i_.<=(Ns.int_?)")
      compileError("Ns.i_?.<=(Ns.int)")
      compileError("Ns.i_?.<=(Ns.int_)")
      compileError("Ns.i_?.<=(Ns.int_?)")

      compileError("Ns.i.>(Ns.int_?)")
      compileError("Ns.i_.>(Ns.int_?)")
      compileError("Ns.i_?.>(Ns.int)")
      compileError("Ns.i_?.>(Ns.int_)")
      compileError("Ns.i_?.>(Ns.int_?)")

      compileError("Ns.i.>=(Ns.int_?)")
      compileError("Ns.i_.>=(Ns.int_?)")
      compileError("Ns.i_?.>=(Ns.int)")
      compileError("Ns.i_?.>=(Ns.int_)")
      compileError("Ns.i_?.>=(Ns.int_?)")
    }


    "No card-Set expression attributes" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.apply(Ns.ints)
      //      Ns.i.apply(Ns.ints_)
      //      Ns.i_.apply(Ns.ints)
      //      Ns.i_.apply(Ns.ints_)
      //
      //      Ns.i.not(Ns.ints)
      //      Ns.i.not(Ns.ints_)
      //      Ns.i_.not(Ns.ints)
      //      Ns.i_.not(Ns.ints_)
      //
      //      Ns.i.<(Ns.ints)
      //      Ns.i.<(Ns.ints_)
      //      Ns.i_.<(Ns.ints)
      //      Ns.i_.<(Ns.ints_)
      //
      //      Ns.i.<=(Ns.ints)
      //      Ns.i.<=(Ns.ints_)
      //      Ns.i_.<=(Ns.ints)
      //      Ns.i_.<=(Ns.ints_)
      //
      //      Ns.i.>(Ns.ints)
      //      Ns.i.>(Ns.ints_)
      //      Ns.i_.>(Ns.ints)
      //      Ns.i_.>(Ns.ints_)
      //
      //      Ns.i.>=(Ns.ints)
      //      Ns.i.>=(Ns.ints_)
      //      Ns.i_.>=(Ns.ints)
      //      Ns.i_.>=(Ns.ints_)

      compileError("Ns.i.apply(Ns.ints)")
      compileError("Ns.i.apply(Ns.ints_)")
      compileError("Ns.i_.apply(Ns.ints)")
      compileError("Ns.i_.apply(Ns.ints_)")

      compileError("Ns.i.not(Ns.ints)")
      compileError("Ns.i.not(Ns.ints_)")
      compileError("Ns.i_.not(Ns.ints)")
      compileError("Ns.i_.not(Ns.ints_)")

      compileError("Ns.i.<(Ns.ints)")
      compileError("Ns.i.<(Ns.ints_)")
      compileError("Ns.i_.<(Ns.ints)")
      compileError("Ns.i_.<(Ns.ints_)")

      compileError("Ns.i.<=(Ns.ints)")
      compileError("Ns.i.<=(Ns.ints_)")
      compileError("Ns.i_.<=(Ns.ints)")
      compileError("Ns.i_.<=(Ns.ints_)")

      compileError("Ns.i.>(Ns.ints)")
      compileError("Ns.i.>(Ns.ints_)")
      compileError("Ns.i_.>(Ns.ints)")
      compileError("Ns.i_.>(Ns.ints_)")

      compileError("Ns.i.>=(Ns.ints)")
      compileError("Ns.i.>=(Ns.ints_)")
      compileError("Ns.i_.>=(Ns.ints)")
      compileError("Ns.i_.>=(Ns.ints_)")
    }


    "Missing expression attributes" - types { implicit conn =>
      for {
        _ <- Ns.int(Ref.int_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Please add missing filter attributes:
              |  Ref.int""".stripMargin
        }
      } yield ()
    }


    "No expression in cross-ns definition" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ref.int_.not(3)).Ref.int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Filtering inside cross-namespace attribute filter not allowed. Found:
              |  AttrOneTacInt("Ref", "int", Neq, Seq(3), None, None, Nil, Nil, None, None)""".stripMargin
        }
      } yield ()
    }


    "Can't filter by same attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ns.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't filter by the same attribute `Ns.i`"
        }
      } yield ()
    }
  }
}
