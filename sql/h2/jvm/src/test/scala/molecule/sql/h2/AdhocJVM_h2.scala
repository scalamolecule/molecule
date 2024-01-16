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

        _ <- Ns.s.i.Refs.*(Ref.int).insert(
          ("a", 1, List(2, 3)),
          ("b", 4, List(4, 5)),
          ("c", 7, List(5, 6)),
          ("d", 9, Nil),
        ).transact

        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int).query.i.get.map(_ ==> List(("b", 4, List(4))))

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
