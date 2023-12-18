package molecule.sql.h2

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.i.get.map(_ ==> List(1))


        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
          (2, int5),
          (2, int6),
        ).transact

        //        _ <- Ns.int(min).query.i.get.map(_ ==> List(int1))
        //        _ <- Ns.int(max).query.get.map(_ ==> List(int6))
        //        _ <- Ns.int(min).int(max).query.get.map(_ ==> List((int1, int6)))

        _ <- Ns.i.a1.int(min).query.i.get.map(_ ==> List(
          (1, int1),
          (2, int4)
        ))
        //
        //        _ <- Ns.i.a1.int(max).query.get.map(_ ==> List(
        //          (1, int3),
        //          (2, int6)
        //        ))
        //
        //        _ <- Ns.i.a1.int(min).int(max).query.get.map(_ ==> List(
        //          (1, int1, int3),
        //          (2, int4, int6)
        //        ))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.s.Bb.*(B.i_?).insert(
          ("a", List(Some(1))),
          ("b", List(None)),
          ("c", Nil),
        ).i.transact

        _ <- A.s.a1.Bb.*?(B.i_?).query.i.get.map(_ ==> List(
          ("a", List(Some(1))),
          ("b", Nil),
          ("c", Nil),
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
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //
    //      } yield ()
    //    }
  }
}
