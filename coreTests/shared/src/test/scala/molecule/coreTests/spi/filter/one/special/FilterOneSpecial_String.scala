package molecule.coreTests.spi.filter.one.special

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneSpecial_String extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "hello"),
          (2, "friends"),
        ).transact

        // Case sensitive
        _ <- Ns.string.startsWith("he").query.get.map(_ ==> List("hello"))

        _ <- Ns.string.endsWith("lo").query.get.map(_ ==> List("hello"))

        _ <- Ns.string.contains("ll").query.get.map(_ ==> List("hello"))
        _ <- Ns.string.contains("e").query.get.map(_.toSet ==> Set("hello", "friends"))

        // Regex matches
        _ <- Ns.string.matches("^[a-g].*").query.get.map(_ ==> List("friends"))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, "hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.i.string_.startsWith("he").query.get.map(_ ==> List(1))

        _ <- Ns.i.string_.endsWith("lo").query.get.map(_ ==> List(1))

        _ <- Ns.i.string_.contains("ll").query.get.map(_ ==> List(1))
        _ <- Ns.i.a1.string_.contains("e").query.get.map(_ ==> List(1, 2))

        _ <- Ns.i.string_.matches("^[h-z].*").query.get.map(_ ==> List(1))
      } yield ()
    }


    "Empty string" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (0, ""),
          (1, "hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.string("").a1.query.get.map(_ ==> List(""))
        _ <- Ns.string.startsWith("").a1.query.get.map(_ ==> List("", "friends", "hello"))
        _ <- Ns.string.endsWith("").a1.query.get.map(_ ==> List("", "friends", "hello"))
        _ <- Ns.string.contains("").a1.query.get.map(_ ==> List("", "friends", "hello"))
        _ <- Ns.string.matches("").a1.query.get.map(_ ==> List("", "friends", "hello"))

        _ <- Ns.i.a1.string_("").query.get.map(_ ==> List(0))
        _ <- Ns.i.a1.string_.startsWith("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.endsWith("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.contains("").query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.string_.matches("").query.get.map(_ ==> List(0, 1, 2))
      } yield ()
    }
  }
}
