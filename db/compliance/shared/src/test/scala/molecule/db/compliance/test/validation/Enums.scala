package molecule.db.compliance.test.validation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import scala.concurrent.Future


case class Enums(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Aliased attribute name" - validation { implicit conn =>
//    val x: Future[List[(String, Color)]] = Person.name.favoriteColor.query.get

    val y = Color

    val z = y.valueOf("BLUE")
    z ==> Color.BLUE

    for {
      _ <- Person.name("Bob").favoriteColor(Color.BLUE).save.transact
      _ <- Person.name.favoriteColor.query.get.map(_ ==> List(("Bob", "BLUE")))
      _ <- Person.name.favoriteColor.query.get.map(_ ==> List(("Bob", Color.BLUE.toString)))


      // Enum as string returned
//      _ <- Person.favoriteColor.query.get ==> List("BLUE")
//      _ <- Person.favoriteColor.query.get ==> List(Color.BLUE.toString)
    } yield ()
  }
}
