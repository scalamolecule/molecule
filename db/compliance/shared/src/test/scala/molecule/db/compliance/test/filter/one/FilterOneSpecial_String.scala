package molecule.db.compliance.test.filter.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOneSpecial_String(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, "hello"),
        (2, "friends"),
      ).transact

      // Case sensitive
      _ <- Entity.string.startsWith("he").query.get.map(_ ==> List("hello"))

      _ <- Entity.string.endsWith("lo").query.get.map(_ ==> List("hello"))

      _ <- Entity.string.contains("ll").query.get.map(_ ==> List("hello"))
      _ <- Entity.string.contains("e").query.get.map(_.toSet ==> Set("hello", "friends"))

      // Regex matches
      _ <- Entity.string.matches("^[a-g].*").query.get.map(_ ==> List("friends"))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, "hello"),
        (2, "friends"),
      ).transact

      _ <- Entity.i.string_.startsWith("he").query.get.map(_ ==> List(1))

      _ <- Entity.i.string_.endsWith("lo").query.get.map(_ ==> List(1))

      _ <- Entity.i.string_.contains("ll").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.string_.contains("e").query.get.map(_ ==> List(1, 2))

      _ <- Entity.i.string_.matches("^[h-z].*").query.get.map(_ ==> List(1))
    } yield ()
  }


  "Empty string" - types {
    for {
      _ <- Entity.i.string.insert(
        (0, ""),
        (1, "hello"),
        (2, "friends"),
      ).transact

      _ <- Entity.string("").a1.query.get.map(_ ==> List(""))
      _ <- Entity.string.startsWith("").a1.query.get.map(_ ==> List("", "friends", "hello"))
      _ <- Entity.string.endsWith("").a1.query.get.map(_ ==> List("", "friends", "hello"))
      _ <- Entity.string.contains("").a1.query.get.map(_ ==> List("", "friends", "hello"))
      _ <- Entity.string.matches("").a1.query.get.map(_ ==> List("", "friends", "hello"))

      _ <- Entity.i.a1.string_("").query.get.map(_ ==> List(0))
      _ <- Entity.i.a1.string_.startsWith("").query.get.map(_ ==> List(0, 1, 2))
      _ <- Entity.i.a1.string_.endsWith("").query.get.map(_ ==> List(0, 1, 2))
      _ <- Entity.i.a1.string_.contains("").query.get.map(_ ==> List(0, 1, 2))
      _ <- Entity.i.a1.string_.matches("").query.get.map(_ ==> List(0, 1, 2))
    } yield ()
  }
}
