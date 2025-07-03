package molecule.db.compliance.test.action.update.attrOp

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class AttrOp_Boolean(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "And" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.&&(true).update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, true), // true && true
        (b, false), // false && true
      ))

      case List(c, d) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(c, d).boolean.&&(false).update.transact
      _ <- Entity.id(c, d).a1.boolean.query.get.map(_ ==> List(
        (c, false), // true && false
        (d, false), // false && false
      ))
    } yield ()
  }


  "Or" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.||(true).update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, true), // true || true
        (b, true), // false || true
      ))

      case List(c, d) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(c, d).boolean.||(false).update.transact
      _ <- Entity.id(c, d).a1.boolean.query.get.map(_ ==> List(
        (c, true), // true || false
        (d, false), // false || false
      ))
    } yield ()
  }


  "Not" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.boolean.insert(true, false).transact.map(_.ids)
      _ <- Entity(a, b).boolean.!.update.transact
      _ <- Entity.id(a, b).a1.boolean.query.get.map(_ ==> List(
        (a, false), // !true
        (b, true), // !false
      ))
    } yield ()
  }
}
