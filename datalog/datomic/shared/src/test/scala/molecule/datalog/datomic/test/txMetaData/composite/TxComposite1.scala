package molecule.datalog.datomic.test.txMetaData.composite

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object TxComposite1 extends DatomicTestSuite {

  override lazy val tests = Tests {

    "is" - refs { implicit conn =>
      for {
        _ <- B.i(1).s("a").Tx(C.i(2).s("b") + D.i(3).s("c")).save.transact

        _ <- B.i.s.Tx(C.i.s + D.i.s).query.get.map(_ ==> List((1, "a", (2, "b"), (3, "c"))))
        _ <- B.i.s.Tx(C.i.s + D.i.s_).query.get.map(_ ==> List((1, "a", (2, "b"), 3)))
        _ <- B.i.s.Tx(C.i.s + D.i).query.get.map(_ ==> List((1, "a", (2, "b"), 3)))
        _ <- B.i.s.Tx(C.i.s + D.i_).query.get.map(_ ==> List((1, "a", (2, "b"))))
        _ <- B.i.s.Tx(C.i.s).query.get.map(_ ==> List((1, "a", 2, "b")))

        _ <- B.i.s.Tx(C.i.s_ + D.i.s).query.get.map(_ ==> List((1, "a", 2, (3, "c"))))
        _ <- B.i.s.Tx(C.i.s_ + D.i.s_).query.get.map(_ ==> List((1, "a", 2, 3)))
        _ <- B.i.s.Tx(C.i.s_ + D.i).query.get.map(_ ==> List((1, "a", 2, 3)))
        _ <- B.i.s.Tx(C.i.s_ + D.i_).query.get.map(_ ==> List((1, "a", 2)))
        _ <- B.i.s.Tx(C.i.s_).query.get.map(_ ==> List((1, "a", 2)))

        _ <- B.i.s.Tx(C.i + D.i.s).query.get.map(_ ==> List((1, "a", 2, (3, "c"))))
        _ <- B.i.s.Tx(C.i + D.i.s_).query.get.map(_ ==> List((1, "a", 2, 3)))
        _ <- B.i.s.Tx(C.i + D.i).query.get.map(_ ==> List((1, "a", 2, 3)))
        _ <- B.i.s.Tx(C.i + D.i_).query.get.map(_ ==> List((1, "a", 2)))
        _ <- B.i.s.Tx(C.i).query.get.map(_ ==> List((1, "a", 2)))

        _ <- B.i.s.Tx(C.i_ + D.i.s).query.get.map(_ ==> List((1, "a", (3, "c"))))
        _ <- B.i.s.Tx(C.i_ + D.i.s_).query.get.map(_ ==> List((1, "a", 3)))
        _ <- B.i.s.Tx(C.i_ + D.i).query.get.map(_ ==> List((1, "a", 3)))
        _ <- B.i.s.Tx(C.i_ + D.i_).query.get.map(_ ==> List((1, "a")))
        _ <- B.i.s.Tx(C.i_).query.get.map(_ ==> List((1, "a")))
        _ <- B.i.s.query.get.map(_ ==> List((1, "a")))
      } yield ()
    }


    "is_" - refs { implicit conn =>
      for {
        _ <- B.i(1).s("a").Tx(C.i(2).s("b") + D.i(3).s("c")).save.transact

        _ <- B.i.s_.Tx(C.i.s + D.i.s).query.get.map(_ ==> List((1, (2, "b"), (3, "c"))))
        _ <- B.i.s_.Tx(C.i.s + D.i.s_).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- B.i.s_.Tx(C.i.s + D.i).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- B.i.s_.Tx(C.i.s + D.i_).query.get.map(_ ==> List((1, (2, "b"))))
        _ <- B.i.s_.Tx(C.i.s).query.get.map(_ ==> List((1, 2, "b")))

        _ <- B.i.s_.Tx(C.i.s_ + D.i.s).query.get.map(_ ==> List((1, 2, (3, "c"))))
        _ <- B.i.s_.Tx(C.i.s_ + D.i.s_).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.s_.Tx(C.i.s_ + D.i).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.s_.Tx(C.i.s_ + D.i_).query.get.map(_ ==> List((1, 2)))
        _ <- B.i.s_.Tx(C.i.s_).query.get.map(_ ==> List((1, 2)))

        _ <- B.i.s_.Tx(C.i + D.i.s).query.get.map(_ ==> List((1, 2, (3, "c"))))
        _ <- B.i.s_.Tx(C.i + D.i.s_).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.s_.Tx(C.i + D.i).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.s_.Tx(C.i + D.i_).query.get.map(_ ==> List((1, 2)))
        _ <- B.i.s_.Tx(C.i).query.get.map(_ ==> List((1, 2)))

        _ <- B.i.s_.Tx(C.i_ + D.i.s).query.get.map(_ ==> List((1, (3, "c"))))
        _ <- B.i.s_.Tx(C.i_ + D.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.s_.Tx(C.i_ + D.i).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.s_.Tx(C.i_ + D.i_).query.get.map(_ ==> List(1))
        _ <- B.i.s_.Tx(C.i_).query.get.map(_ ==> List(1))
        _ <- B.i.s_.query.get.map(_ ==> List(1))
      } yield ()
    }


    "i" - refs { implicit conn =>
      for {
        _ <- B.i(1).s("a").Tx(C.i(2).s("b") + D.i(3).s("c")).save.transact

        _ <- B.i.Tx(C.i.s + D.i.s).query.get.map(_ ==> List((1, (2, "b"), (3, "c"))))
        _ <- B.i.Tx(C.i.s + D.i.s_).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- B.i.Tx(C.i.s + D.i).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- B.i.Tx(C.i.s + D.i_).query.get.map(_ ==> List((1, (2, "b"))))
        _ <- B.i.Tx(C.i.s).query.get.map(_ ==> List((1, 2, "b")))

        _ <- B.i.Tx(C.i.s_ + D.i.s).query.get.map(_ ==> List((1, 2, (3, "c"))))
        _ <- B.i.Tx(C.i.s_ + D.i.s_).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.Tx(C.i.s_ + D.i).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.Tx(C.i.s_ + D.i_).query.get.map(_ ==> List((1, 2)))
        _ <- B.i.Tx(C.i.s_).query.get.map(_ ==> List((1, 2)))

        _ <- B.i.Tx(C.i + D.i.s).query.get.map(_ ==> List((1, 2, (3, "c"))))
        _ <- B.i.Tx(C.i + D.i.s_).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.Tx(C.i + D.i).query.get.map(_ ==> List((1, 2, 3)))
        _ <- B.i.Tx(C.i + D.i_).query.get.map(_ ==> List((1, 2)))
        _ <- B.i.Tx(C.i).query.get.map(_ ==> List((1, 2)))

        _ <- B.i.Tx(C.i_ + D.i.s).query.get.map(_ ==> List((1, (3, "c"))))
        _ <- B.i.Tx(C.i_ + D.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.Tx(C.i_ + D.i).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.Tx(C.i_ + D.i_).query.get.map(_ ==> List(1))
        _ <- B.i.Tx(C.i_).query.get.map(_ ==> List(1))
        _ <- B.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "i_" - refs { implicit conn =>
      for {
        _ <- B.i(1).s("a").Tx(C.i(2).s("b") + D.i(3).s("c")).save.transact

        _ <- B.i_.Tx(C.i.s + D.i.s).query.get.map(_ ==> List(((2, "b"), (3, "c"))))
        _ <- B.i_.Tx(C.i.s + D.i.s_).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- B.i_.Tx(C.i.s + D.i).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- B.i_.Tx(C.i.s + D.i_).query.get.map(_ ==> List((2, "b")))
        _ <- B.i_.Tx(C.i.s).query.get.map(_ ==> List((2, "b")))

        _ <- B.i_.Tx(C.i.s_ + D.i.s).query.get.map(_ ==> List((2, (3, "c"))))
        _ <- B.i_.Tx(C.i.s_ + D.i.s_).query.get.map(_ ==> List((2, 3)))
        _ <- B.i_.Tx(C.i.s_ + D.i).query.get.map(_ ==> List((2, 3)))
        _ <- B.i_.Tx(C.i.s_ + D.i_).query.get.map(_ ==> List(2))
        _ <- B.i_.Tx(C.i.s_).query.get.map(_ ==> List(2))

        _ <- B.i_.Tx(C.i + D.i.s).query.get.map(_ ==> List((2, (3, "c"))))
        _ <- B.i_.Tx(C.i + D.i.s_).query.get.map(_ ==> List((2, 3)))
        _ <- B.i_.Tx(C.i + D.i).query.get.map(_ ==> List((2, 3)))
        _ <- B.i_.Tx(C.i + D.i_).query.get.map(_ ==> List(2))
        _ <- B.i_.Tx(C.i).query.get.map(_ ==> List(2))

        _ <- B.i_.Tx(C.i_ + D.i.s).query.get.map(_ ==> List((3, "c")))
        _ <- B.i_.Tx(C.i_ + D.i.s_).query.get.map(_ ==> List(3))
        _ <- B.i_.Tx(C.i_ + D.i).query.get.map(_ ==> List(3))
      } yield ()
    }
  }
}