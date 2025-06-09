package molecule.db.compliance.test.bind

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

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
    if (database == "datomic") {
      // In Datomic regex expressions need to be pre-comiled which Molecule doesn't handle.
      // So here you'll need to apply the regex directly
      for {
        _ <- Entity.i.string.insert(
          (1, "hello"),
          (2, "friends")
        ).transact

        _ <- Entity.string.matches("^[a-g].*").d1.query.get.map(_ ==> List("friends"))
        _ <- Entity.string.matches("^[d-s].*").d1.query.get.map(_ ==> List("hello", "friends"))

        _ <- Entity.i.a1.string_.matches("^[a-g].*").query.get.map(_ ==> List(2))
        _ <- Entity.i.a1.string_.matches("^[d-s].*").query.get.map(_ ==> List(1, 2))
      } yield ()

    } else {
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
}
