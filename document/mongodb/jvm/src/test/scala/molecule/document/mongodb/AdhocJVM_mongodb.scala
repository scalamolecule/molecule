package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  val ambiguous =
    """Ambiguous filter attribute path: A.i
      |Please qualify the filter attribute by appending the full path of namespaces.
      |Or make sure that the target attribute is not appearing multiple times.""".stripMargin

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

//        _ <- Ns.s.id(Ns.string).query.get
//          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//            err ==> "Filter attributes not allowed to involve entity ids."
//          }
//
//        _ <- Ns.s.string(Ns.id).query.get
//          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//            err ==> "Filter attributes not allowed to involve entity ids."
//          }

        // Cross reference filter attributes not allowed either
        _ <- Ns.s.string(Ref.id_).Ref.id.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filter attributes not allowed to involve entity ids."
          }
//        _ <- Ns.s.string_(Ref.id_).Ref.id.query.get
//          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//            err ==> "Filter attributes not allowed to involve entity ids."
//          }

        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.s.i
          .B.i.OwnC.i._B._A
          .OwnB.i.C.i
          .insert(
            ("a", 1, 1, 0, 0, 0),
            ("b", 1, 0, 1, 0, 0),
            ("c", 1, 0, 0, 1, 0),
            ("d", 1, 0, 0, 0, 1),
          ).transact

        // A  ---------------------------

        //        // Forwards
        //        _ <- A.s.i_(A.B.i_)
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("a"))
        //
        //        _ <- A.s.i_(A.B.OwnC.i_)
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("b"))
        //
        //        _ <- A.s.i_(A.OwnB.i_)
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("c"))
        //
        //        _ <- A.s.i_(A.OwnB.C.i_)
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("d"))
        //
        //        // Backwards
        //        _ <- A.s.i_
        //          .B.i_(A.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("a"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_(A.i_)._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("b"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_(A.i_).C.i_
        //          .query.get.map(_ ==> List("c"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_(A.i_)
        //          .query.get.map(_ ==> List("d"))
        //
        //
        //        // A.B  ---------------------------
        //
        //        // Forwards
        //        _ <- A.s.i_
        //          .B.i_(A.B.OwnC.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("c", "d"))

        //        _ <- A.s.i_
        //          .B.i_(C.i_).OwnC.i_
        //          .query.get.map(_ ==> List("b", "d"))

        _ <- A.s.i_
          .B.i_(A.i_)._A
          //          .B.i_(B.i_)._A
          //          .B.i_(A.B.i)._A
          //          .B.i_(A.B.i_)._A
          //          .B.i_(A.OwnB.i_)._A
          .OwnB.i_.C.i_
          .query.get.map(_ ==> List("b", "d"))

        //        _ <- A.s.i_
        //          .B.i_(C.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("b", "d"))

        //        _ <- A.s.i_
        //          .B.i_(A.OwnB.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("b", "d"))

        //        _ <- A.s.i_
        //          .B.i_(A.OwnB.C.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("b", "c"))
        //
        //        // Backwards
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_(A.B.i_)._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("c", "d"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_(A.B.i_).C.i_
        //          .query.get.map(_ ==> List("b", "d"))
        //
        //        _ <- A.s.i_
        //          .B.i_(A.OwnB.C.i_).OwnC.i_._B._A
        //          .OwnB.i_.C.i_(A.B.i_)
        //          .query.get.map(_ ==> List("b", "c"))
        //
        //
        //        // A.B.OwnC  ---------------------------
        //
        //        // Forwards
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_(A.OwnB.i_)._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("a", "d"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_(A.OwnB.C.i_)._B._A
        //          .OwnB.i_.C.i_
        //          .query.get.map(_ ==> List("a", "c"))
        //
        //        // Backwards
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_(A.B.OwnC.i_).C.i_
        //          .query.get.map(_ ==> List("a", "d"))
        //
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_(A.B.OwnC.i_)
        //          .query.get.map(_ ==> List("a", "c"))
        //
        //
        //        // A.OwnB  ---------------------------
        //
        //        // Forwards
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_(A.OwnB.C.i_).C.i_
        //          .query.get.map(_ ==> List("a", "b"))
        //
        //        // Backwards
        //        _ <- A.s.i_
        //          .B.i_.OwnC.i_._B._A
        //          .OwnB.i_.C.i_(A.OwnB.i_)
        //          .query.get.map(_ ==> List("a", "b"))

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
        //            _ <- Uniques.i(1).save.transact


        _ <- Uniques.i(1).i(2).int_(1).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute Uniques.i"
          }

      } yield ()
    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
