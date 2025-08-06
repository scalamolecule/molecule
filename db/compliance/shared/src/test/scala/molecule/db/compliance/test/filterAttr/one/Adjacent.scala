package molecule.db.compliance.test.filterAttr.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Adjacent(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "equal (apply)" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i(Entity.int_).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i_(Entity.int_).query.get.map(_ ==> List("b"))
    } yield ()
  }

  "not equal" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.not(Entity.int_).query.get.map(_ ==> List(("a", 1), ("c", 5)))
      _ <- Entity.s.a1.i_.not(Entity.int_).query.get.map(_ ==> List("a", "c"))
    } yield ()
  }


  "<" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i.<(Entity.int_).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i_.<(Entity.int_).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "<=" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.<=(Entity.int_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
      _ <- Entity.s.a1.i_.<=(Entity.int_).query.get.map(_ ==> List("a", "b"))
    } yield ()
  }


  ">" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i.>(Entity.int_).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i_.>(Entity.int_).query.get.map(_ ==> List("c"))
    } yield ()
  }


  ">=" - types {
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.>=(Entity.int_).query.get.map(_ ==> List(("b", 3), ("c", 5)))
      _ <- Entity.s.a1.i_.>=(Entity.int_).query.get.map(_ ==> List("b", "c"))
    } yield ()
  }
}
