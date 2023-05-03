package molecule.datomic.test.relation

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object BidirectionalRef extends DatomicTestSuite {

  override lazy val tests = Tests {

    "self" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Self.i(2).save.transact

        // Directional
        // 1 knows of 2, but 2 doesn't know of 1
        _ <- Ns.i.Self.i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(1).Self.i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(2).Self.i.query.get.map(_ ==> List())

        // Bidirectional
        // if 1 is friend with 2, 2 is also friend with 1
        _ <- Ns.i.a1.Self(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
        _ <- Ns.i(1).Self(bi).i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(2).Self(bi).i.query.get.map(_ ==> List((2, 1)))

        // My friends' friends include me (1)
        _ <- Ns.i(1).Self(bi).i.Self(bi).i.query.get.map(_ ==> List((1, 2, 1)))
        _ <- Ns.i_(1).Self(bi).Self(bi).i.query.inspect
        _ <- Ns.i_(1).Self(bi).Self(bi).i.query.get.map(_ ==> List(1))
      } yield ()
    }

    "other" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).R1.i(2).save.transact

        // Directional
        _ <- Ns.i.R1.i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(1).R1.i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(2).R1.i.query.get.map(_ ==> List())

        // Bidirectional
        _ <- Ns.i.a1.R1.apply(bi).i.query.inspect
        _ <- Ns.i.a1.R1(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
        _ <- Ns.i(1).R1(bi).i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i(2).R1(bi).i.query.get.map(_ ==> List((2, 1)))
      } yield ()
    }

    "edge" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).R1.i(2).Ns1.i(3).save.transact

        // Directional
        _ <- Ns.i.R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
        _ <- Ns.i(1).R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
        _ <- Ns.i(3).R1.i.Ns1.i.query.get.map(_ ==> List())

        // Bidirectional
        _ <- Ns.i.a1.R1(bi).i.Ns1(bi).i.query.inspect
//        _ <- Ns.i.a1.R1(bi).i.Ns1.i.query.get.map(_ ==> List((1, 2, 3), (3, 2, 1)))
//        _ <- Ns.i.a1.R1.i.Ns1(bi).i.query.get.map(_ ==> List((1, 2, 3), (3, 2, 1)))
        _ <- Ns.i.a1.R1(bi).i.Ns1(bi).i.query.get.map(_ ==> List((1, 2, 3), (3, 2, 1)))
        _ <- Ns.i(1).R1(bi).i.Ns1(bi).i.query.get.map(_ ==> List((1, 2, 3)))
        _ <- Ns.i(3).R1(bi).i.Ns1(bi).i.query.get.map(_ ==> List((3, 2, 1)))
      } yield ()
    }
  }
}
