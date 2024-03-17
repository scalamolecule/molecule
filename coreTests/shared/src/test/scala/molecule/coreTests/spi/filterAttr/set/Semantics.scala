package molecule.coreTests.spi.filterAttr.set

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

    "Optional attributes not allowed" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.iSet.apply(Ns.intSet_?)
      //      Ns.iSet_.apply(Ns.intSet_?)
      //      Ns.iSet_?.apply(Ns.intSet)
      //      Ns.iSet_?.apply(Ns.intSet_)
      //      Ns.iSet_?.apply(Ns.intSet_?)
      //
      //      Ns.iSet.not(Ns.intSet_?)
      //      Ns.iSet_.not(Ns.intSet_?)
      //      Ns.iSet_?.not(Ns.intSet)
      //      Ns.iSet_?.not(Ns.intSet_)
      //      Ns.iSet_?.not(Ns.intSet_?)

      compileError("Ns.iSet.apply(Ns.intSet_?)")
      compileError("Ns.iSet_.apply(Ns.intSet_?)")
      compileError("Ns.iSet_?.apply(Ns.intSet)")
      compileError("Ns.iSet_?.apply(Ns.intSet_)")
      compileError("Ns.iSet_?.apply(Ns.intSet_?)")

      compileError("Ns.iSet.not(Ns.intSet_?)")
      compileError("Ns.iSet_.not(Ns.intSet_?)")
      compileError("Ns.iSet_?.not(Ns.intSet)")
      compileError("Ns.iSet_?.not(Ns.intSet_)")
      compileError("Ns.iSet_?.not(Ns.intSet_?)")
    }


    "apply/not expects only cardinality set" - types { implicit conn =>
      // ok
      Ns.iSet.apply(Ns.intSet)
      Ns.iSet.not(Ns.intSet)

      // Prevented by type inference
      //      Ns.iSet.apply(Ns.int)
      //      Ns.iSet.apply(Ns.int_)
      //      Ns.iSet_.apply(Ns.int)
      //      Ns.iSet_.apply(Ns.int_)
      //
      //      Ns.iSet.not(Ns.int)
      //      Ns.iSet.not(Ns.int_)
      //      Ns.iSet_.not(Ns.int)
      //      Ns.iSet_.not(Ns.int_)

      compileError("Ns.iSet.apply(Ns.int)")
      compileError("Ns.iSet.apply(Ns.int_)")
      compileError("Ns.iSet_.apply(Ns.int)")
      compileError("Ns.iSet_.apply(Ns.int_)")

      compileError("Ns.iSet.not(Ns.int)")
      compileError("Ns.iSet.not(Ns.int_)")
      compileError("Ns.iSet_.not(Ns.int)")
      compileError("Ns.iSet_.not(Ns.int_)")
    }


    "has/hasNo accepts both cardinality one and set" - types { implicit conn =>
      // Card-one without/with expr ok
      Ns.iSet.has(Ns.int)
      Ns.iSet.has(Ns.int.<(2))
      Ns.iSet.hasNo(Ns.int)
      Ns.iSet.hasNo(Ns.int.<(2))

      // Card-set without expr ok
      Ns.iSet.has(Ns.intSet)
      Ns.iSet.hasNo(Ns.intSet)

      // Card-set with expr not allowed (prevented by type inference)
      //      Ns.iSet.has(Ns.intSet.not(Set(1)))
      //      Ns.iSet.has(Ns.intSet_.not(Set(1)))
      //      Ns.iSet_.has(Ns.intSet.not(Set(1)))
      //      Ns.iSet_.has(Ns.intSet_.not(Set(1)))
      //
      //      Ns.iSet.hasNo(Ns.intSet.not(Set(1)))
      //      Ns.iSet.hasNo(Ns.intSet_.not(Set(1)))
      //      Ns.iSet_.hasNo(Ns.intSet.not(Set(1)))
      //      Ns.iSet_.hasNo(Ns.intSet_.not(Set(1)))

      compileError("Ns.iSet.has(Ns.intSet.not(Set(1)))")
      compileError("Ns.iSet.has(Ns.intSet_.not(Set(1)))")
      compileError("Ns.iSet_.has(Ns.intSet.not(Set(1)))")
      compileError("Ns.iSet_.has(Ns.intSet_.not(Set(1)))")

      compileError("Ns.iSet.hasNo(Ns.intSet.not(Set(1)))")
      compileError("Ns.iSet.hasNo(Ns.intSet_.not(Set(1)))")
      compileError("Ns.iSet_.hasNo(Ns.intSet.not(Set(1)))")
      compileError("Ns.iSet_.hasNo(Ns.intSet_.not(Set(1)))")
    }

    "No expression inside cross-namespace definition" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.intSet(Ref.intSet_(Set(1))).Ref.intSet
      //      Ns.i.intSet(Ref.intSet_.not(Set(1))).Ref.intSet
      //      Ns.i.intSet(Ref.intSet_.has(Set(1))).Ref.intSet
      //      Ns.i.intSet(Ref.intSet_.has(1)).Ref.intSet
      //      Ns.i.intSet(Ref.intSet_.hasNo(Set(1))).Ref.intSet
      //      Ns.i.intSet(Ref.intSet_.hasNo(1)).Ref.intSet

      compileError("Ns.i.intSet(Ref.intSet_.not(Set(1))).Ref.intSet")
      compileError("Ns.i.intSet(Ref.intSet_.not(Set(1))).Ref.intSet")
      compileError("Ns.i.intSet(Ref.intSet_.has(Set(1))).Ref.intSet")
      compileError("Ns.i.intSet(Ref.intSet_.has(1)).Ref.intSet")
      compileError("Ns.i.intSet(Ref.intSet_.hasNo(Set(1))).Ref.intSet")
      compileError("Ns.i.intSet(Ref.intSet_.hasNo(1)).Ref.intSet")
    }


    "Missing expression attributes" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet(Ref.intSet_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add missing filter attribute Ref.intSet"
          }
      } yield ()
    }


    "Only tacit expression attribute for sets" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet(Ref.intSet).Ref.intSet.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attribute Ref.intSet pointing to other namespace should be tacit."
          }
      } yield ()
    }


    "No expressions on card-set target attributes" - types { implicit conn =>
      for {
        _ <- Ns.int.intSet(Ref.intSet_).Ref.intSet.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ref.intSet) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSet.not(Set(2, 3)).Ref.intSet(Ns.intSet_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ns.intSet) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSet.not(Ref.intSet_).Ref.intSet.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ref.intSet) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSet.not(Set(2, 3)).Ref.intSet.not(Ns.intSet_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ns.intSet) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSet.has(Ref.intSet_).Ref.intSet.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ref.intSet) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSet.not(Set(2, 3)).Ref.intSet.has(Ns.intSet_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ns.intSet) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSet.hasNo(Ref.intSet_).Ref.intSet.not(Set(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ref.intSet) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSet.not(Set(2, 3)).Ref.intSet.hasNo(Ns.intSet_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-set filter attributes (Ns.intSet) not allowed to do additional filtering."
          }
      } yield ()
    }
  }
}
