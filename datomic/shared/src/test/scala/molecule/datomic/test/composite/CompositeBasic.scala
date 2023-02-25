package molecule.datomic.test.composite

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object CompositeBasic extends DatomicTestSuite {

  // For more complex structures, see:
  // molecule.datomic.test.crud.save.SaveTxMetaData
  // molecule.datomic.test.crud.insert.InsertTxMetaData

  lazy val tests = Tests {

    "basic" - refs { implicit conn =>
      for {
        _ <- (Ns.i.s + R2.s.i).insert((1, "a"), ("b", 2)).transact

        _ <- (Ns.i.s + R2.s.i).query.get.map(_ ==> List(((1, "a"), ("b", 2))))
        _ <- (Ns.i.s + R2.s).query.get.map(_ ==> List(((1, "a"), "b")))
        _ <- (Ns.i + R2.s.i).query.get.map(_ ==> List((1, ("b", 2))))
        _ <- (Ns.i + R2.s).query.get.map(_ ==> List((1, "b")))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        // Ns.i with and without associated data Rs.s
        _ <- (Ns.i + R2.s).insert(1, "x").transact
        _ <- Ns.i.insert(2).transact

        // Ns.i asserted twice
        _ <- Ns.i.query.get.map(_ ==> List(1, 2))

        // Ns.i only asserted once with associated R2.s
        _ <- (Ns.i + R2.s_).query.get.map(_ ==> List(1))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        _ <- (R3.i + Ns.i.R1.i).insert(0, (1, 2)).transact

        _ <- (R3.i + Ns.i.R1.i).query.get.map(_ ==> List((0, (1, 2))))
        _ <- (R3.i + Ns.i.R1.i_).query.get.map(_ ==> List((0, 1)))
        _ <- (R3.i + Ns.i_.R1.i).query.get.map(_ ==> List((0, 2)))
        _ <- (R3.i + Ns.R1.i).query.get.map(_ ==> List((0, 2)))
        _ <- (R3.i + Ns.R1.i_).query.get.map(_ ==> List(0))

        _ <- (R3.i_ + Ns.i.R1.i).query.get.map(_ ==> List((1, 2)))
        _ <- (R3.i_ + Ns.i.R1.i_).query.get.map(_ ==> List(1))
        _ <- (R3.i_ + Ns.i_.R1.i).query.get.map(_ ==> List(2))
        _ <- (R3.i_ + Ns.R1.i).query.get.map(_ ==> List(2))


        _ <- (Ns.i.R1.i + R2.i).insert((1, 2), 3).transact

        _ <- (Ns.i.R1.i + R2.i).query.get.map(_ ==> List(((1, 2), 3)))
        _ <- (Ns.i.R1.i_ + R2.i).query.get.map(_ ==> List((1, 3)))
        _ <- (Ns.i_.R1.i + R2.i).query.get.map(_ ==> List((2, 3)))
        _ <- (Ns.R1.i + R2.i).query.get.map(_ ==> List((2, 3)))
        _ <- (Ns.R1.i_ + R2.i).query.get.map(_ ==> List(3))

        _ <- (Ns.i.R1.i + R2.i_).query.get.map(_ ==> List((1, 2)))
        _ <- (Ns.i.R1.i_ + R2.i_).query.get.map(_ ==> List(1))
        _ <- (Ns.i_.R1.i + R2.i_).query.get.map(_ ==> List(2))
        _ <- (Ns.R1.i + R2.i_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "Expr" - refs { implicit conn =>
      for {
        _ <- (R3.i.s + Ns.i.s).insert(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
          ((3, "c"), (6, "z")),
        ).transact

        _ <- (R3.i.a1.s + Ns.i.s).query.get.map(_ ==> List(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
          ((3, "c"), (6, "z")),
        ))
        _ <- (R3.i.<(3).a1.s + Ns.i.s).query.get.map(_ ==> List(
          ((1, "a"), (4, "x")),
          ((2, "b"), (5, "y")),
        ))
        _ <- (R3.i.<(3).a1.s + Ns.i.>(4).s).query.get.map(_ ==> List(
          ((2, "b"), (5, "y")),
        ))
      } yield ()
    }
  }
}
