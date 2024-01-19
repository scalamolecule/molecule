package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._
import scala.util.Random

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.i.query.get.map(_ ==> List(1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.Bb.i.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact

        _ <- A.i_.Bb.i.a1.query.get.map(_ ==> List(1, 2, 3))

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {

        _ <- Uniques.int.insert(1, 2, 3).transact

        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }

        // Turning around with first cursor leads nowhere
        _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }

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
