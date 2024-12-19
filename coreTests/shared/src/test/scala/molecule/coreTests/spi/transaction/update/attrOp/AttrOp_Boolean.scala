package molecule.coreTests.spi.transaction.update.attrOp

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOp_Boolean extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "And" - types { implicit conn =>
      for {
        List(a, b) <- Ns.boolean.insert(true, false).transact.map(_.ids)
        _ <- Ns(a, b).boolean.&&(true).update.transact
        _ <- Ns.id(a, b).a1.boolean.query.get.map(_ ==> List(
          (a, true), // true && true
          (b, false), // false && true
        ))

        List(c, d) <- Ns.boolean.insert(true, false).transact.map(_.ids)
        _ <- Ns(c, d).boolean.&&(false).update.transact
        _ <- Ns.id(c, d).a1.boolean.query.get.map(_ ==> List(
          (c, false), // true && false
          (d, false), // false && false
        ))
      } yield ()
    }


    "Or" - types { implicit conn =>
      for {
        List(a, b) <- Ns.boolean.insert(true, false).transact.map(_.ids)
        _ <- Ns(a, b).boolean.||(true).update.transact
        _ <- Ns.id(a, b).a1.boolean.query.get.map(_ ==> List(
          (a, true), // true || true
          (b, true), // false || true
        ))

        List(c, d) <- Ns.boolean.insert(true, false).transact.map(_.ids)
        _ <- Ns(c, d).boolean.||(false).update.transact
        _ <- Ns.id(c, d).a1.boolean.query.get.map(_ ==> List(
          (c, true), // true || false
          (d, false), // false || false
        ))
      } yield ()
    }


    "Not" - types { implicit conn =>
      for {
        List(a, b) <- Ns.boolean.insert(true, false).transact.map(_.ids)
        _ <- Ns(a, b).boolean.!.update.transact
        _ <- Ns.id(a, b).a1.boolean.query.get.map(_ ==> List(
          (a, false), // !true
          (b, true), // !false
        ))
      } yield ()
    }
  }
}
