package molecule.datomic.test.crud.save

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object SaveRef extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Card one" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).R1.i(2).save.transact
        _ <- Ns.i.R1.i.query.get.map(_ ==> List((1, 2)))
      } yield ()
    }


    "Card many" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Rs1.i(2).save.transact
        _ <- Ns.i.Rs1.i.query.get.map(_ ==> List((1, 2)))
      } yield ()
    }


    "Backref" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Rs1.i(2)._Ns.s("a").save.transact
        _ <- Ns.i.Rs1.i._Ns.s.query.get.map(_ ==> List((1, 2, "a")))
      } yield ()
    }


    "Composite" - refs { implicit conn =>
      for {
        _ <- (Ns.i(1) + R2.i(2)).save.transact
        _ <- (Ns.i + R2.i).query.get.map(_ ==> List((1, 2)))

        _ <- (Ns.R1.i(1) + R2.i(2)).save.transact
        _ <- (Ns.R1.i + R2.i).query.get.map(_ ==> List((1, 2)))
      } yield ()
    }
  }
}
