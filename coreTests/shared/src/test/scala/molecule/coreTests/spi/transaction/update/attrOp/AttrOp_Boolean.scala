package molecule.coreTests.spi.transaction.update.attrOp

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOp_Boolean(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "And" - types { implicit conn =>
    for {
      List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.&&(true).update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, true), // true && true
        (b, false), // false && true
      ))

      List(c, d) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(c, d).boolean.&&(false).update.transact
      _ <- Entity.id(c, d).a1.boolean.query.get.map(_ ==> List(
        (c, false), // true && false
        (d, false), // false && false
      ))
    } yield ()
  }


  "Or" - types { implicit conn =>
    for {
      List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.||(true).update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, true), // true || true
        (b, true), // false || true
      ))

      List(c, d) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(c, d).boolean.||(false).update.transact
      _ <- Entity.id(c, d).a1.boolean.query.get.map(_ ==> List(
        (c, true), // true || false
        (d, false), // false || false
      ))
    } yield ()
  }


  "Not" - types { implicit conn =>
    for {
      List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.!.update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, false), // !true
        (b, true), // !false
      ))
    } yield ()
  }
}
