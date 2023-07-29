package molecule.coreTests.test.composite

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CompositeBasic extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  // For more complex structures, see:
  // crud.save.SaveTxMetaData
  // crud.insert.InsertTxMetaData

  override lazy val tests = Tests {

    "basic" - refs { implicit conn =>
      for {
        _ <- (A.i.s + C.s.i).insert((1, "a"), ("b", 2)).transact

        _ <- (A.i.s + C.s.i).query.get.map(_ ==> List(((1, "a"), ("b", 2))))
        _ <- (A.i.s + C.s).query.get.map(_ ==> List(((1, "a"), "b")))
        _ <- (A.i + C.s.i).query.get.map(_ ==> List((1, ("b", 2))))
        _ <- (A.i + C.s).query.get.map(_ ==> List((1, "b")))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        // A.i with and without associated data Rs.s
        _ <- (A.i + C.s).insert(1, "x").transact
        _ <- A.i.insert(2).transact

        // A.i asserted twice
        _ <- A.i.query.get.map(_ ==> List(1, 2))

        // A.i only asserted once with associated C.s
        _ <- (A.i + C.s_).query.get.map(_ ==> List(1))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        _ <- (D.i + A.i.B.i).insert(0, (1, 2)).transact

        _ <- (D.i + A.i.B.i).query.get.map(_ ==> List((0, (1, 2))))
        _ <- (D.i + A.i.B.i_).query.get.map(_ ==> List((0, 1)))
        _ <- (D.i + A.i_.B.i).query.get.map(_ ==> List((0, 2)))
        _ <- (D.i + A.B.i).query.get.map(_ ==> List((0, 2)))
        _ <- (D.i + A.B.i_).query.get.map(_ ==> List(0))

        _ <- (D.i_ + A.i.B.i).query.get.map(_ ==> List((1, 2)))
        _ <- (D.i_ + A.i.B.i_).query.get.map(_ ==> List(1))
        _ <- (D.i_ + A.i_.B.i).query.get.map(_ ==> List(2))
        _ <- (D.i_ + A.B.i).query.get.map(_ ==> List(2))


        _ <- (A.i.B.i + C.i).insert((1, 2), 3).transact

        _ <- (A.i.B.i + C.i).query.get.map(_ ==> List(((1, 2), 3)))
        _ <- (A.i.B.i_ + C.i).query.get.map(_ ==> List((1, 3)))
        _ <- (A.i_.B.i + C.i).query.get.map(_ ==> List((2, 3)))
        _ <- (A.B.i + C.i).query.get.map(_ ==> List((2, 3)))
        _ <- (A.B.i_ + C.i).query.get.map(_ ==> List(3))

        _ <- (A.i.B.i + C.i_).query.get.map(_ ==> List((1, 2)))
        _ <- (A.i.B.i_ + C.i_).query.get.map(_ ==> List(1))
        _ <- (A.i_.B.i + C.i_).query.get.map(_ ==> List(2))
        _ <- (A.B.i + C.i_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "Expr" - refs { implicit conn =>
      for {
        _ <- (D.i.s + A.i.s).insert(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
          ((3, "c"), (6, "z")),
        ).transact

        _ <- (D.i.a1.s + A.i.s).query.get.map(_ ==> List(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
          ((3, "c"), (6, "z")),
        ))
        _ <- (D.i.<(3).a1.s + A.i.s).query.get.map(_ ==> List(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
        ))
        _ <- (D.i.<(3).a1.s + A.i.>(4).s).query.get.map(_ ==> List(
          ((2, "b"), (5, "y")),
        ))
      } yield ()
    }
  }
}
