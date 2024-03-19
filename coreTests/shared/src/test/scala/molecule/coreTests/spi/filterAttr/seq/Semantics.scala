package molecule.coreTests.spi.filterAttr.seq

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
      //      Ns.iSeq.apply(Ns.Seq._?)
      //      Ns.iSeq_.apply(Ns.intSeq_?)
      //      Ns.iSeq_?.apply(Ns.intSeq)
      //      Ns.iSeq_?.apply(Ns.intSeq_)
      //      Ns.iSeq_?.apply(Ns.intSeq_?)
      //
      //      Ns.iSeq.not(Ns.intSeq_?)
      //      Ns.iSeq_.not(Ns.intSeq_?)
      //      Ns.iSeq_?.not(Ns.intSeq)
      //      Ns.iSeq_?.not(Ns.intSeq_)
      //      Ns.iSeq_?.not(Ns.intSeq_?)

      compileError("Ns.iSeq.apply(Ns.intSeq_?)")
      compileError("Ns.iSeq_.apply(Ns.intSeq_?)")
      compileError("Ns.iSeq_?.apply(Ns.intSeq)")
      compileError("Ns.iSeq_?.apply(Ns.intSeq_)")
      compileError("Ns.iSeq_?.apply(Ns.intSeq_?)")

      compileError("Ns.iSeq.not(Ns.intSeq_?)")
      compileError("Ns.iSeq_.not(Ns.intSeq_?)")
      compileError("Ns.iSeq_?.not(Ns.intSeq)")
      compileError("Ns.iSeq_?.not(Ns.intSeq_)")
      compileError("Ns.iSeq_?.not(Ns.intSeq_?)")
    }


    "apply/not expects only cardinality seq" - types { implicit conn =>
      // ok
      Ns.iSeq.apply(Ns.intSeq)
      Ns.iSeq.not(Ns.intSeq)

      // Prevented by type inference
      //      Ns.iSeq.apply(Ns.int)
      //      Ns.iSeq.apply(Ns.int_)
      //      Ns.iSeq_.apply(Ns.int)
      //      Ns.iSeq_.apply(Ns.int_)
      //
      //      Ns.iSeq.not(Ns.int)
      //      Ns.iSeq.not(Ns.int_)
      //      Ns.iSeq_.not(Ns.int)
      //      Ns.iSeq_.not(Ns.int_)

      compileError("Ns.iSeq.apply(Ns.int)")
      compileError("Ns.iSeq.apply(Ns.int_)")
      compileError("Ns.iSeq_.apply(Ns.int)")
      compileError("Ns.iSeq_.apply(Ns.int_)")

      compileError("Ns.iSeq.not(Ns.int)")
      compileError("Ns.iSeq.not(Ns.int_)")
      compileError("Ns.iSeq_.not(Ns.int)")
      compileError("Ns.iSeq_.not(Ns.int_)")
    }


    "has/hasNo accepts both cardinality one and seq" - types { implicit conn =>
      // Card-one without/with expr ok
      Ns.iSeq.has(Ns.int)
      Ns.iSeq.has(Ns.int.<(2))
      Ns.iSeq.hasNo(Ns.int)
      Ns.iSeq.hasNo(Ns.int.<(2))

      // Card-seq without expr ok
      Ns.iSeq.has(Ns.intSeq)
      Ns.iSeq.hasNo(Ns.intSeq)

      // Card-seq with expr not allowed (prevented by type inference)
      //      Ns.iSeq.has(Ns.intSeq.not(List(1)))
      //      Ns.iSeq.has(Ns.intSeq_.not(List(1)))
      //      Ns.iSeq_.has(Ns.intSeq.not(List(1)))
      //      Ns.iSeq_.has(Ns.intSeq_.not(List(1)))
      //
      //      Ns.iSeq.hasNo(Ns.intSeq.not(List(1)))
      //      Ns.iSeq.hasNo(Ns.intSeq_.not(List(1)))
      //      Ns.iSeq_.hasNo(Ns.intSeq.not(List(1)))
      //      Ns.iSeq_.hasNo(Ns.intSeq_.not(List(1)))

      compileError("Ns.iSeq.has(Ns.intSeq.not(List(1)))")
      compileError("Ns.iSeq.has(Ns.intSeq_.not(List(1)))")
      compileError("Ns.iSeq_.has(Ns.intSeq.not(List(1)))")
      compileError("Ns.iSeq_.has(Ns.intSeq_.not(List(1)))")

      compileError("Ns.iSeq.hasNo(Ns.intSeq.not(List(1)))")
      compileError("Ns.iSeq.hasNo(Ns.intSeq_.not(List(1)))")
      compileError("Ns.iSeq_.hasNo(Ns.intSeq.not(List(1)))")
      compileError("Ns.iSeq_.hasNo(Ns.intSeq_.not(List(1)))")
    }

    "No expression inside cross-namespace definition" - types { implicit conn =>
      // Prevented by type inference
      //      Ns.i.intSeq(Ref.iSeq_(List(1))).Ref.iSeq
      //      Ns.i.intSeq(Ref.iSeq_.not(List(1))).Ref.iSeq
      //      Ns.i.intSeq(Ref.iSeq_.has(List(1))).Ref.iSeq
      //      Ns.i.intSeq(Ref.iSeq_.has(1)).Ref.iSeq
      //      Ns.i.intSeq(Ref.iSeq_.hasNo(List(1))).Ref.iSeq
      //      Ns.i.intSeq(Ref.iSeq_.hasNo(1)).Ref.iSeq

      compileError("Ns.i.intSeq(Ref.iSeq_.not(List(1))).Ref.iSeq")
      compileError("Ns.i.intSeq(Ref.iSeq_.not(List(1))).Ref.iSeq")
      compileError("Ns.i.intSeq(Ref.iSeq_.has(List(1))).Ref.iSeq")
      compileError("Ns.i.intSeq(Ref.iSeq_.has(1)).Ref.iSeq")
      compileError("Ns.i.intSeq(Ref.iSeq_.hasNo(List(1))).Ref.iSeq")
      compileError("Ns.i.intSeq(Ref.iSeq_.hasNo(1)).Ref.iSeq")
    }


    "Missing expression attributes" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq(Ref.iSeq_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add missing filter attribute Ref.iSeq"
          }
      } yield ()
    }


    "Only tacit expression attribute for seqs" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq(Ref.iSeq).Ref.iSeq.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attribute Ref.iSeq pointing to other namespace should be tacit."
          }
      } yield ()
    }


    "No expressions on card-seq target attributes" - types { implicit conn =>
      for {
        _ <- Ns.int.intSeq(Ref.iSeq_).Ref.iSeq.not(List(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ref.iSeq) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSeq.not(List(2, 3)).Ref.iSeq(Ns.intSeq_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ns.intSeq) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSeq.not(Ref.iSeq_).Ref.iSeq.not(List(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ref.iSeq) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSeq.not(List(2, 3)).Ref.iSeq.not(Ns.intSeq_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ns.intSeq) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSeq.has(Ref.iSeq_).Ref.iSeq.not(List(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ref.iSeq) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSeq.not(List(2, 3)).Ref.iSeq.has(Ns.intSeq_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ns.intSeq) not allowed to do additional filtering."
          }

        _ <- Ns.int.intSeq.hasNo(Ref.iSeq_).Ref.iSeq.not(List(2, 3)).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ref.iSeq) not allowed to do additional filtering."
          }
        _ <- Ns.int.intSeq.not(List(2, 3)).Ref.iSeq.hasNo(Ns.intSeq_).int.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Cardinality-many filter attributes (Ns.intSeq) not allowed to do additional filtering."
          }
      } yield ()
    }
  }
}
