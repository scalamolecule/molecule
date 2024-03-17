package molecule.sql.postgres

import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.TestSuite_postgres
import utest._
import scala.language.implicitConversions

object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
        ).transact


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {

        _ <- A.i.Bb.*(B.i.C.iSet).insert(
          (0, Nil),
          (1, List(
            (1, Set.empty[Int])
          )),
          (2, List(
            (1, Set.empty[Int]),
            (2, Set(0)),
            (3, Set(1, 2)),
          )),
        ).transact


        _ <- A.i.a1.Bb.*?(B.i.C.iSet).query.get.map(_ ==> List(
          (0, Nil),
          (1, Nil),
          (2, List(
            (2, Set(0)),
            (3, Set(1, 2)),
          )),
        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

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