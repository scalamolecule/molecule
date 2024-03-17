package molecule.coreTests.spi.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Semantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Optional filter attributes not allowed" - types { implicit conn =>
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


    "No card one attr with Set filter attribute" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.apply(Ns.intSet)
      //      Ns.i.apply(Ns.intSet_)
      //      Ns.i_.apply(Ns.intSet)
      //      Ns.i_.apply(Ns.intSet_)
      //
      //      Ns.i.not(Ns.intSet)
      //      Ns.i.not(Ns.intSet_)
      //      Ns.i_.not(Ns.intSet)
      //      Ns.i_.not(Ns.intSet_)
      //
      //      Ns.i.<(Ns.intSet)
      //      Ns.i.<(Ns.intSet_)
      //      Ns.i_.<(Ns.intSet)
      //      Ns.i_.<(Ns.intSet_)
      //
      //      Ns.i.<=(Ns.intSet)
      //      Ns.i.<=(Ns.intSet_)
      //      Ns.i_.<=(Ns.intSet)
      //      Ns.i_.<=(Ns.intSet_)
      //
      //      Ns.i.>(Ns.intSet)
      //      Ns.i.>(Ns.intSet_)
      //      Ns.i_.>(Ns.intSet)
      //      Ns.i_.>(Ns.intSet_)
      //
      //      Ns.i.>=(Ns.intSet)
      //      Ns.i.>=(Ns.intSet_)
      //      Ns.i_.>=(Ns.intSet)
      //      Ns.i_.>=(Ns.intSet_)

      compileError("Ns.i.apply(Ns.intSet)")
      compileError("Ns.i.apply(Ns.intSet_)")
      compileError("Ns.i_.apply(Ns.intSet)")
      compileError("Ns.i_.apply(Ns.intSet_)")

      compileError("Ns.i.not(Ns.intSet)")
      compileError("Ns.i.not(Ns.intSet_)")
      compileError("Ns.i_.not(Ns.intSet)")
      compileError("Ns.i_.not(Ns.intSet_)")

      compileError("Ns.i.<(Ns.intSet)")
      compileError("Ns.i.<(Ns.intSet_)")
      compileError("Ns.i_.<(Ns.intSet)")
      compileError("Ns.i_.<(Ns.intSet_)")

      compileError("Ns.i.<=(Ns.intSet)")
      compileError("Ns.i.<=(Ns.intSet_)")
      compileError("Ns.i_.<=(Ns.intSet)")
      compileError("Ns.i_.<=(Ns.intSet_)")

      compileError("Ns.i.>(Ns.intSet)")
      compileError("Ns.i.>(Ns.intSet_)")
      compileError("Ns.i_.>(Ns.intSet)")
      compileError("Ns.i_.>(Ns.intSet_)")

      compileError("Ns.i.>=(Ns.intSet)")
      compileError("Ns.i.>=(Ns.intSet_)")
      compileError("Ns.i_.>=(Ns.intSet)")
      compileError("Ns.i_.>=(Ns.intSet_)")
    }


    "Missing filter attributes" - types { implicit conn =>
      for {
        _ <- Ns.int(Ref.int_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add missing filter attribute Ref.int"
          }
      } yield ()
    }


    "No expression in cross-ns definition" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ref.int_.not(3)).Ref.int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filtering inside cross-namespace attribute filter not allowed."
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
