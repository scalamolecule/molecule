package molecule.datalog.datomic.test.txMetaData.composite

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object TxComposite2 extends DatomicTestSuite {

  override lazy val tests = Tests {

    "is" - refs { implicit conn =>
      for {
        _ <- (R1.i(1).s("a") + R2.i(2).s("b").Tx(R3.i(3).s("c"))).save.transact

        _ <- (R1.i.s + R2.i.s.Tx(R3.i.s)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3, "c")))
        _ <- (R1.i.s + R2.i.s.Tx(R3.i.s_)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3)))
        _ <- (R1.i.s + R2.i.s.Tx(R3.i)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3)))
        _ <- (R1.i.s + R2.i.s.Tx(R3.i_)).query.get.map(_ ==> List(((1, "a"), (2, "b"))))

        _ <- (R1.i.s + R2.i.s_.Tx(R3.i.s)).query.get.map(_ ==> List(((1, "a"), 2, 3, "c")))
        _ <- (R1.i.s + R2.i.s_.Tx(R3.i.s_)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (R1.i.s + R2.i.s_.Tx(R3.i)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (R1.i.s + R2.i.s_.Tx(R3.i_)).query.get.map(_ ==> List(((1, "a"), 2)))

        _ <- (R1.i.s + R2.i.Tx(R3.i.s)).query.get.map(_ ==> List(((1, "a"), 2, 3, "c")))
        _ <- (R1.i.s + R2.i.Tx(R3.i.s_)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (R1.i.s + R2.i.Tx(R3.i)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (R1.i.s + R2.i.Tx(R3.i_)).query.get.map(_ ==> List(((1, "a"), 2)))

        _ <- (R1.i.s + R2.i_.Tx(R3.i.s)).query.get.map(_ ==> List(((1, "a"), 3, "c")))
        _ <- (R1.i.s + R2.i_.Tx(R3.i.s_)).query.get.map(_ ==> List(((1, "a"), 3)))
        _ <- (R1.i.s + R2.i_.Tx(R3.i)).query.get.map(_ ==> List(((1, "a"), 3)))
        _ <- (R1.i.s + R2.i_.Tx(R3.i_)).query.get.map(_ ==> List((1, "a")))

        _ <- R1.i.s.Tx(R3.i.s).query.get.map(_ ==> List((1, "a", 3, "c")))
        _ <- R1.i.s.Tx(R3.i.s_).query.get.map(_ ==> List((1, "a", 3)))
        _ <- R1.i.s.Tx(R3.i).query.get.map(_ ==> List((1, "a", 3)))
        _ <- R1.i.s.Tx(R3.i_).query.get.map(_ ==> List((1, "a")))
      } yield ()
    }


    "is_" - refs { implicit conn =>
      for {
        _ <- (R1.i(1).s("a") + R2.i(2).s("b").Tx(R3.i(3).s("c"))).save.transact

        _ <- (R1.i.s_ + R2.i.s.Tx(R3.i.s)).query.get.map(_ ==> List((1, (2, "b"), 3, "c")))
        _ <- (R1.i.s_ + R2.i.s.Tx(R3.i.s_)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (R1.i.s_ + R2.i.s.Tx(R3.i)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (R1.i.s_ + R2.i.s.Tx(R3.i_)).query.get.map(_ ==> List((1, (2, "b"))))

        _ <- (R1.i.s_ + R2.i.s_.Tx(R3.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (R1.i.s_ + R2.i.s_.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i.s_ + R2.i.s_.Tx(R3.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i.s_ + R2.i.s_.Tx(R3.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (R1.i.s_ + R2.i.Tx(R3.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (R1.i.s_ + R2.i.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i.s_ + R2.i.Tx(R3.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i.s_ + R2.i.Tx(R3.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (R1.i.s_ + R2.i_.Tx(R3.i.s)).query.get.map(_ ==> List((1, 3, "c")))
        _ <- (R1.i.s_ + R2.i_.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 3)))
        _ <- (R1.i.s_ + R2.i_.Tx(R3.i)).query.get.map(_ ==> List((1, 3)))
        _ <- (R1.i.s_ + R2.i_.Tx(R3.i_)).query.get.map(_ ==> List(1))

        _ <- R1.i.s_.Tx(R3.i.s).query.get.map(_ ==> List((1, 3, "c")))
        _ <- R1.i.s_.Tx(R3.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- R1.i.s_.Tx(R3.i).query.get.map(_ ==> List((1, 3)))
        _ <- R1.i.s_.Tx(R3.i_).query.get.map(_ ==> List(1))
      } yield ()
    }


    "i" - refs { implicit conn =>
      for {
        _ <- (R1.i(1).s("a") + R2.i(2).s("b").Tx(R3.i(3).s("c"))).save.transact

        _ <- (R1.i + R2.i.s.Tx(R3.i.s)).query.get.map(_ ==> List((1, (2, "b"), 3, "c")))
        _ <- (R1.i + R2.i.s.Tx(R3.i.s_)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (R1.i + R2.i.s.Tx(R3.i)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (R1.i + R2.i.s.Tx(R3.i_)).query.get.map(_ ==> List((1, (2, "b"))))

        _ <- (R1.i + R2.i.s_.Tx(R3.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (R1.i + R2.i.s_.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i + R2.i.s_.Tx(R3.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i + R2.i.s_.Tx(R3.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (R1.i + R2.i.Tx(R3.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (R1.i + R2.i.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i + R2.i.Tx(R3.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (R1.i + R2.i.Tx(R3.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (R1.i + R2.i_.Tx(R3.i.s)).query.get.map(_ ==> List((1, 3, "c")))
        _ <- (R1.i + R2.i_.Tx(R3.i.s_)).query.get.map(_ ==> List((1, 3)))
        _ <- (R1.i + R2.i_.Tx(R3.i)).query.get.map(_ ==> List((1, 3)))
        _ <- (R1.i + R2.i_.Tx(R3.i_)).query.get.map(_ ==> List(1))

        _ <- R1.i.Tx(R3.i.s).query.get.map(_ ==> List((1, 3, "c")))
        _ <- R1.i.Tx(R3.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- R1.i.Tx(R3.i).query.get.map(_ ==> List((1, 3)))
        _ <- R1.i.Tx(R3.i_).query.get.map(_ ==> List(1))

      } yield ()
    }


    "i_" - refs { implicit conn =>
      for {
        _ <- (R1.i(1).s("a") + R2.i(2).s("b").Tx(R3.i(3).s("c"))).save.transact

        _ <- (R1.i_ + R2.i.s.Tx(R3.i.s)).query.get.map(_ ==> List(((2, "b"), 3, "c")))
        _ <- (R1.i_ + R2.i.s.Tx(R3.i.s_)).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- (R1.i_ + R2.i.s.Tx(R3.i)).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- (R1.i_ + R2.i.s.Tx(R3.i_)).query.get.map(_ ==> List((2, "b")))

        _ <- (R1.i_ + R2.i.s_.Tx(R3.i.s)).query.get.map(_ ==> List((2, 3, "c")))
        _ <- (R1.i_ + R2.i.s_.Tx(R3.i.s_)).query.get.map(_ ==> List((2, 3)))
        _ <- (R1.i_ + R2.i.s_.Tx(R3.i)).query.get.map(_ ==> List((2, 3)))
        _ <- (R1.i_ + R2.i.s_.Tx(R3.i_)).query.get.map(_ ==> List(2))

        _ <- (R1.i_ + R2.i.Tx(R3.i.s)).query.get.map(_ ==> List((2, 3, "c")))
        _ <- (R1.i_ + R2.i.Tx(R3.i.s_)).query.get.map(_ ==> List((2, 3)))
        _ <- (R1.i_ + R2.i.Tx(R3.i)).query.get.map(_ ==> List((2, 3)))
        _ <- (R1.i_ + R2.i.Tx(R3.i_)).query.get.map(_ ==> List(2))

        _ <- (R1.i_ + R2.i_.Tx(R3.i.s)).query.get.map(_ ==> List((3, "c")))
        _ <- (R1.i_ + R2.i_.Tx(R3.i.s_)).query.get.map(_ ==> List(3))
        _ <- (R1.i_ + R2.i_.Tx(R3.i)).query.get.map(_ ==> List(3))

        _ <- R1.i_.Tx(R3.i.s).query.get.map(_ ==> List((3, "c")))
        _ <- R1.i_.Tx(R3.i.s_).query.get.map(_ ==> List(3))
        _ <- R1.i_.Tx(R3.i).query.get.map(_ ==> List(3))
      } yield ()
    }
  }
}