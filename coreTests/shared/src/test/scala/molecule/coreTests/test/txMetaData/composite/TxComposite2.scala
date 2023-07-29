package molecule.coreTests.test.txMetaData.composite

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait TxComposite2 extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "is" - refs { implicit conn =>
      for {
        _ <- (B.i(1).s("a") + C.i(2).s("b").Tx(D.i(3).s("c"))).save.transact

        _ <- (B.i.s + C.i.s.Tx(D.i.s)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3, "c")))
        _ <- (B.i.s + C.i.s.Tx(D.i.s_)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3)))
        _ <- (B.i.s + C.i.s.Tx(D.i)).query.get.map(_ ==> List(((1, "a"), (2, "b"), 3)))
        _ <- (B.i.s + C.i.s.Tx(D.i_)).query.get.map(_ ==> List(((1, "a"), (2, "b"))))

        _ <- (B.i.s + C.i.s_.Tx(D.i.s)).query.get.map(_ ==> List(((1, "a"), 2, 3, "c")))
        _ <- (B.i.s + C.i.s_.Tx(D.i.s_)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (B.i.s + C.i.s_.Tx(D.i)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (B.i.s + C.i.s_.Tx(D.i_)).query.get.map(_ ==> List(((1, "a"), 2)))

        _ <- (B.i.s + C.i.Tx(D.i.s)).query.get.map(_ ==> List(((1, "a"), 2, 3, "c")))
        _ <- (B.i.s + C.i.Tx(D.i.s_)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (B.i.s + C.i.Tx(D.i)).query.get.map(_ ==> List(((1, "a"), 2, 3)))
        _ <- (B.i.s + C.i.Tx(D.i_)).query.get.map(_ ==> List(((1, "a"), 2)))

        _ <- (B.i.s + C.i_.Tx(D.i.s)).query.get.map(_ ==> List(((1, "a"), 3, "c")))
        _ <- (B.i.s + C.i_.Tx(D.i.s_)).query.get.map(_ ==> List(((1, "a"), 3)))
        _ <- (B.i.s + C.i_.Tx(D.i)).query.get.map(_ ==> List(((1, "a"), 3)))
        _ <- (B.i.s + C.i_.Tx(D.i_)).query.get.map(_ ==> List((1, "a")))

        _ <- B.i.s.Tx(D.i.s).query.get.map(_ ==> List((1, "a", 3, "c")))
        _ <- B.i.s.Tx(D.i.s_).query.get.map(_ ==> List((1, "a", 3)))
        _ <- B.i.s.Tx(D.i).query.get.map(_ ==> List((1, "a", 3)))
        _ <- B.i.s.Tx(D.i_).query.get.map(_ ==> List((1, "a")))
      } yield ()
    }


    "is_" - refs { implicit conn =>
      for {
        _ <- (B.i(1).s("a") + C.i(2).s("b").Tx(D.i(3).s("c"))).save.transact

        _ <- (B.i.s_ + C.i.s.Tx(D.i.s)).query.get.map(_ ==> List((1, (2, "b"), 3, "c")))
        _ <- (B.i.s_ + C.i.s.Tx(D.i.s_)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (B.i.s_ + C.i.s.Tx(D.i)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (B.i.s_ + C.i.s.Tx(D.i_)).query.get.map(_ ==> List((1, (2, "b"))))

        _ <- (B.i.s_ + C.i.s_.Tx(D.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (B.i.s_ + C.i.s_.Tx(D.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i.s_ + C.i.s_.Tx(D.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i.s_ + C.i.s_.Tx(D.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (B.i.s_ + C.i.Tx(D.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (B.i.s_ + C.i.Tx(D.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i.s_ + C.i.Tx(D.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i.s_ + C.i.Tx(D.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (B.i.s_ + C.i_.Tx(D.i.s)).query.get.map(_ ==> List((1, 3, "c")))
        _ <- (B.i.s_ + C.i_.Tx(D.i.s_)).query.get.map(_ ==> List((1, 3)))
        _ <- (B.i.s_ + C.i_.Tx(D.i)).query.get.map(_ ==> List((1, 3)))
        _ <- (B.i.s_ + C.i_.Tx(D.i_)).query.get.map(_ ==> List(1))

        _ <- B.i.s_.Tx(D.i.s).query.get.map(_ ==> List((1, 3, "c")))
        _ <- B.i.s_.Tx(D.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.s_.Tx(D.i).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.s_.Tx(D.i_).query.get.map(_ ==> List(1))
      } yield ()
    }


    "i" - refs { implicit conn =>
      for {
        _ <- (B.i(1).s("a") + C.i(2).s("b").Tx(D.i(3).s("c"))).save.transact

        _ <- (B.i + C.i.s.Tx(D.i.s)).query.get.map(_ ==> List((1, (2, "b"), 3, "c")))
        _ <- (B.i + C.i.s.Tx(D.i.s_)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (B.i + C.i.s.Tx(D.i)).query.get.map(_ ==> List((1, (2, "b"), 3)))
        _ <- (B.i + C.i.s.Tx(D.i_)).query.get.map(_ ==> List((1, (2, "b"))))

        _ <- (B.i + C.i.s_.Tx(D.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (B.i + C.i.s_.Tx(D.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i + C.i.s_.Tx(D.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i + C.i.s_.Tx(D.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (B.i + C.i.Tx(D.i.s)).query.get.map(_ ==> List((1, 2, 3, "c")))
        _ <- (B.i + C.i.Tx(D.i.s_)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i + C.i.Tx(D.i)).query.get.map(_ ==> List((1, 2, 3)))
        _ <- (B.i + C.i.Tx(D.i_)).query.get.map(_ ==> List((1, 2)))

        _ <- (B.i + C.i_.Tx(D.i.s)).query.get.map(_ ==> List((1, 3, "c")))
        _ <- (B.i + C.i_.Tx(D.i.s_)).query.get.map(_ ==> List((1, 3)))
        _ <- (B.i + C.i_.Tx(D.i)).query.get.map(_ ==> List((1, 3)))
        _ <- (B.i + C.i_.Tx(D.i_)).query.get.map(_ ==> List(1))

        _ <- B.i.Tx(D.i.s).query.get.map(_ ==> List((1, 3, "c")))
        _ <- B.i.Tx(D.i.s_).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.Tx(D.i).query.get.map(_ ==> List((1, 3)))
        _ <- B.i.Tx(D.i_).query.get.map(_ ==> List(1))

      } yield ()
    }


    "i_" - refs { implicit conn =>
      for {
        _ <- (B.i(1).s("a") + C.i(2).s("b").Tx(D.i(3).s("c"))).save.transact

        _ <- (B.i_ + C.i.s.Tx(D.i.s)).query.get.map(_ ==> List(((2, "b"), 3, "c")))
        _ <- (B.i_ + C.i.s.Tx(D.i.s_)).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- (B.i_ + C.i.s.Tx(D.i)).query.get.map(_ ==> List(((2, "b"), 3)))
        _ <- (B.i_ + C.i.s.Tx(D.i_)).query.get.map(_ ==> List((2, "b")))

        _ <- (B.i_ + C.i.s_.Tx(D.i.s)).query.get.map(_ ==> List((2, 3, "c")))
        _ <- (B.i_ + C.i.s_.Tx(D.i.s_)).query.get.map(_ ==> List((2, 3)))
        _ <- (B.i_ + C.i.s_.Tx(D.i)).query.get.map(_ ==> List((2, 3)))
        _ <- (B.i_ + C.i.s_.Tx(D.i_)).query.get.map(_ ==> List(2))

        _ <- (B.i_ + C.i.Tx(D.i.s)).query.get.map(_ ==> List((2, 3, "c")))
        _ <- (B.i_ + C.i.Tx(D.i.s_)).query.get.map(_ ==> List((2, 3)))
        _ <- (B.i_ + C.i.Tx(D.i)).query.get.map(_ ==> List((2, 3)))
        _ <- (B.i_ + C.i.Tx(D.i_)).query.get.map(_ ==> List(2))

        _ <- (B.i_ + C.i_.Tx(D.i.s)).query.get.map(_ ==> List((3, "c")))
        _ <- (B.i_ + C.i_.Tx(D.i.s_)).query.get.map(_ ==> List(3))
        _ <- (B.i_ + C.i_.Tx(D.i)).query.get.map(_ ==> List(3))

        _ <- B.i_.Tx(D.i.s).query.get.map(_ ==> List((3, "c")))
        _ <- B.i_.Tx(D.i.s_).query.get.map(_ ==> List(3))
        _ <- B.i_.Tx(D.i).query.get.map(_ ==> List(3))
      } yield ()
    }
  }
}