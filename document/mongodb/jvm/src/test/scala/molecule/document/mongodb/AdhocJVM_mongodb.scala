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
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)
      for {

        List(x, y, z) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)

        _ <- A.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))
        _ <- A.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        // To get un-coalesced Sets, separate by ids
        _ <- A.id.a1.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
        _ <- A.id.a1.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
//        _ <- Uniques.int.insert(1, 2, 3).transact
//
//        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
//
//        // Turning around with first cursor leads nowhere
//        _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }


//
        _ <- Uniques.int.insert(1, 2).transact

        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, false) => c }

        // No following rows yet
        _ <- Uniques.int.a1.query.from(c1).limit(2).get.map { case (Nil, _, false) => () }

        // New row
        _ <- Uniques.int.insert(3).transact

        // Now there are new rows
        _ <- Uniques.int.a1.query.from(c1).limit(2).get.map { case (List(3), _, false) => () }
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
