package molecule.coreTests.spi.crud.delete

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Delete_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1 entity" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(e1).delete.transact
        // or
        _ <- A.id_(e2).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }


    "n entities vararg" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(e1, e2).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "n entities iterable" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(Seq(e1, e2)).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "0 entities" - refs { implicit conn =>
      for {
        _ <- A.i.insert(1, 2, 3).transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        _ <- A(Seq.empty[String]).delete.transact

        // No entities deleted
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      } yield ()
    }
  }
}
