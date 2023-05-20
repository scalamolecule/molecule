package molecule.datalog.datomic.test.relation.nested

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object NestedComposite extends DatomicTestSuite {


  override lazy val tests = Tests {

    "1 level" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + D.s.i).insert(0, List(((1, "a"), ("b", 2)))).transact

        _ <- A.i.Bb.*(B.i.s + D.s.i).query.get.map(_ ==> List((0, List(((1, "a"), ("b", 2))))))
        _ <- A.i.Bb.*(B.i.s + D.s).query.get.map(_ ==> List((0, List(((1, "a"), "b")))))
        _ <- A.i.Bb.*(B.i + D.s.i).query.get.map(_ ==> List((0, List((1, ("b", 2))))))
        _ <- A.i.Bb.*(B.i + D.i).query.get.map(_ ==> List((0, List((1, 2)))))
      } yield ()
    }


    "2 levels" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s.i)).insert(
          0, List(
            (1, List(
              ((2, "a"), ("b", 3)))))
        ).transact

        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), ("b", 3))))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), "b")))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i + E.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, ("b", 3))))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i + E.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, 3)))))
        ))
      } yield ()
    }
  }
}
