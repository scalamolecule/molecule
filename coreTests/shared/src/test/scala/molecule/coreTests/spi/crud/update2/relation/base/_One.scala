package molecule.coreTests.spi.crud.update2.relation.base

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait _One extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - value" - refs { implicit conn =>
      for {
        a <- A.s("a").save.transact.map(_.id)
        b <- A.s("b").i(1).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b).i(2).update.transact

        _ <- A.s.a1.i.query.get.map(_ ==> List(
          ("a", 2), // B attribute added
          ("b", 2), // B attribute updated
        ))
      } yield ()
    }


    "filter - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).s("b").save.transact
        _ <- A.i(3).s("c").i(3).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.i(4).update.transact

        _ <- A.i.a1.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))
      } yield ()
    }


    "value - filter" - refs { implicit conn =>
      for {
        _ <- A.i(0).save.transact

        _ <- A.i(1).save.transact
        _ <- A.i(2).i(2).save.transact
        _ <- A.i(3).i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.i(4).i_.update.transact

        _ <- A.i.i.a1.query.get.map(_ ==> List(
          (4, 1), // A attribute added
          (4, 2), // A attribute updated
          (4, 3), // A attribute updated
        ))

        // Initial entity without ref was not updated
        _ <- A.i.a1.query.get.map(_ ==> List(0, 4))
      } yield ()
    }
  }
}
