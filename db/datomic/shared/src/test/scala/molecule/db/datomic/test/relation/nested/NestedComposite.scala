package molecule.db.datomic.test.relation.nested

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedComposite extends DatomicTestSuite {


  lazy val tests = Tests {

    "1 level" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R3.s.i).insert(0, List(((1, "a"), ("b", 2)))).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R3.s.i).query.get.map(_ ==> List((0, List(((1, "a"), ("b", 2))))))
        _ <- Ns.i.Rs1.*(R1.i.s + R3.s).query.get.map(_ ==> List((0, List(((1, "a"), "b")))))
        _ <- Ns.i.Rs1.*(R1.i + R3.s.i).query.get.map(_ ==> List((0, List((1, ("b", 2))))))
        _ <- Ns.i.Rs1.*(R1.i + R3.i).query.get.map(_ ==> List((0, List((1, 2)))))
      } yield ()
    }


    "2 levels" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).insert(
          0, List(
            (1, List(
              ((2, "a"), ("b", 3)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), ("b", 3))))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), "b")))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, ("b", 3))))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, 3)))))
        ))
      } yield ()
    }
  }
}
