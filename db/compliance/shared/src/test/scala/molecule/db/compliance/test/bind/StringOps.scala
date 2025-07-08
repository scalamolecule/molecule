package molecule.db.compliance.test.bind

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class StringOps(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.string.insert("hello", "friends").transact

      startsWith = Entity.string.startsWith(?).query
      _ <- startsWith("he").get.map(_ ==> List("hello"))
      _ <- startsWith("fr").get.map(_ ==> List("friends"))
      _ <- startsWith("x").get.map(_ ==> List())

      endsWith = Entity.string.endsWith(?).query
      _ <- endsWith("lo").get.map(_ ==> List("hello"))
      _ <- endsWith("ds").get.map(_ ==> List("friends"))

      contains = Entity.string.contains(?).d1.query
      _ <- contains("ll").get.map(_ ==> List("hello"))
      _ <- contains("e").get.map(_.toSet ==> Set("hello", "friends"))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.string.insert(
        (1, "hello"),
        (2, "friends")
      ).transact

      startsWith = Entity.i.string_.startsWith(?).query
      _ <- startsWith("he").get.map(_ ==> List(1))
      _ <- startsWith("fr").get.map(_ ==> List(2))
      _ <- startsWith("x").get.map(_ ==> List())

      endsWith = Entity.i.string_.endsWith(?).query
      _ <- endsWith("lo").get.map(_ ==> List(1))
      _ <- endsWith("ds").get.map(_ ==> List(2))

      contains = Entity.i.a1.string_.contains(?).query
      _ <- contains("ll").get.map(_ ==> List(1))
      _ <- contains("e").get.map(_.toSet ==> Set(1, 2))
    } yield ()
  }


  "Regex" - types { implicit conn =>
    for {
      _ <- Entity.i.string.insert(
        (1, "hello"),
        (2, "friends")
      ).transact

      // Regex expressions can be applied as bound parameters with SQL databases
      matches = Entity.string.matches(?).d1.query
      _ <- matches("^[a-g].*").get.map(_ ==> List("friends"))
      _ <- matches("^[d-s].*").get.map(_ ==> List("hello", "friends"))

      tacitMatches = Entity.i.a1.string_.matches(?).query
      _ <- tacitMatches("^[a-g].*").get.map(_ ==> List(2))
      _ <- tacitMatches("^[d-s].*").get.map(_ ==> List(1, 2))
    } yield ()
  }
}
