package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, Ref}
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


        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.s.i.OwnBb.*(B.i).insert(
          ("a", 1, List(2, 3)),
          ("b", 4, List(4, 5)),
          ("c", 7, List(5, 6)),
          ("d", 9, Nil),
        ).transact

        // Pointing forward

        _ <- A.s.a1.i(B.i_).OwnBb.*(B.i.a1).query.get.map(_ ==> List(
          ("b", 4, List(4)) // Note that only B.i values matching A.i are returned
        ))

        // Pointing backwards

        _ <- A.s.a1.i.OwnBb.*(B.i(A.i_)).query.get.map(_ ==> List(
          ("b", 4, List(4))
        ))


        //
        //        _ <- A.s.i_.<(2).i_.not(0).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))

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
