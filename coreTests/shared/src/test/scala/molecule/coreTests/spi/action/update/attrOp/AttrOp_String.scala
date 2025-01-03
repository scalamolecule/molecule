package molecule.coreTests.spi.action.update.attrOp

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOp_String(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "append" - types { implicit conn =>
    for {
      id <- Entity.string("a").save.transact.map(_.id)
      _ <- Entity(id).string.+("b").update.transact
      _ <- Entity.string.query.get.map(_.head ==> "ab")
    } yield ()
  }


  "prepend" - types { implicit conn =>
    for {
      id <- Entity.string("a").save.transact.map(_.id)
      _ <- Entity(id).string.prepend("b").update.transact
      _ <- Entity.string.query.get.map(_.head ==> "ba")
    } yield ()
  }


  "substring" - types { implicit conn =>
    for {
      ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)

      _ <- Entity(ids).string.substring(-2, 3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
          error ==> "Start index should be 0 or more"
        }

      _ <- Entity(ids).string.substring(4, 3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
          error ==> "Start index should be smaller than end index"
        }

      // Index after end to keep rest of string
      _ <- Entity(ids).string.substring(1, 100).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("ello", "orld"))

      // Middle part
      _ <- Entity(ids).string.substring(1, 3).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("ll", "rl"))

      // Empty string returned if start is after end
      // OBS: beware of saved empty string values!
      _ <- Entity(ids).string.substring(10, 11).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List(""))
    } yield ()
  }


  "replaceAll" - types { implicit conn =>
    if (database == "sqlite") {
      for {
        ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)
        // replaceAll with SQlite matches static text ("W")
        _ <- Entity(ids).string.replaceAll("W", "X").update.transact
        _ <- Entity.string.a1.query.get.map(_ ==> List("Hello", "Xorld"))
      } yield ()
    } else {
      for {
        ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)
        // replaceAll with other database matches regex pattern ("[oW]")
        _ <- Entity(ids).string.replaceAll("[oW]", "X").update.transact
        _ <- Entity.string.a1.query.get.map(_ ==> List("HellX", "XXrld"))
      } yield ()
    }
  }


  "toLower" - types { implicit conn =>
    for {
      ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)
      _ <- Entity(ids).string.toLower.update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("hello", "world"))
    } yield ()
  }


  "toUpper" - types { implicit conn =>
    for {
      ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)
      _ <- Entity(ids).string.toUpper.update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("HELLO", "WORLD"))
    } yield ()
  }
}