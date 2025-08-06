package molecule.db.compliance.test.validation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class Enums(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Enum type - value" - validation {
    for {
      // Typed enum value saved
      _ <- Person.name("Bob").favoriteColor(Color.BLUE).save.transact

      // String value returned
      _ <- Person.name.favoriteColor.query.get.map(_ ==> List(("Bob", "BLUE")))
    } yield ()
  }


  "Comparing with typed enum" - validation {
    for {
      _ <- Person.name("Bob").favoriteColor(Color.BLUE).save.transact

      // Can't resolve Color type generically at runtime
      // as long as we want to have a serializable DataModel for RPC
      // _ <- Person.name.favoriteColor.query.get.map(_ ==> List(("Bob", Color.BLUE)))

      // But we can simply compare with a String representation of the enum
      _ <- Person.name.favoriteColor.query.get.map(_ ==> List(("Bob", Color.BLUE.toString)))

      // Or generate enums from the result
      _ <- Person.name.favoriteColor.query.get.map(_.map {
        case (name, eStr) => (name, Color.valueOf(eStr))
      } ==> List(("Bob", Color.BLUE)))
    } yield ()
  }
}
