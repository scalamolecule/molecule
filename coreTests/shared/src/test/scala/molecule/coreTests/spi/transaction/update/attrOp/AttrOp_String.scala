package molecule.coreTests.spi.transaction.update.attrOp

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOp_String extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "append" - types { implicit conn =>
      for {
        id <- Ns.string("a").save.transact.map(_.id)
        _ <- Ns(id).string.+("b").update.transact
        _ <- Ns.string.query.get.map(_.head ==> "ab")
      } yield ()
    }


    "prepend" - types { implicit conn =>
      for {
        id <- Ns.string("a").save.transact.map(_.id)
        _ <- Ns(id).string.prepend("b").update.transact
        _ <- Ns.string.query.get.map(_.head ==> "ba")
      } yield ()
    }


    "substring" - types { implicit conn =>
      for {
        ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)

        _ <- Ns(ids).string.substring(-2, 3).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
            error ==> "Start index should be 0 or more"
          }

        _ <- Ns(ids).string.substring(4, 3).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
            error ==> "Start index should be smaller than end index"
          }

        // Index after end to keep rest of string
        _ <- Ns(ids).string.substring(1, 100).update.transact
        _ <- Ns.string.a1.query.get.map(_ ==> List("ello", "orld"))

        // Middle part
        _ <- Ns(ids).string.substring(1, 3).update.transact
        _ <- Ns.string.a1.query.get.map(_ ==> List("ll", "rl"))

        // Empty string returned if start is after end
        // OBS: beware of saved empty string values!
        _ <- Ns(ids).string.substring(10, 11).update.transact
        _ <- Ns.string.a1.query.get.map(_ ==> List(""))
      } yield ()
    }


    "replaceAll" - types { implicit conn =>
      if (database == "SQlite") {
        for {
          ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)
          // replaceAll with SQlite matches static text ("W")
          _ <- Ns(ids).string.replaceAll("W", "X").update.transact
          _ <- Ns.string.a1.query.get.map(_ ==> List("Hello", "Xorld"))
        } yield ()
      } else {
        for {
          ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)
          // replaceAll with other database matches regex pattern ("[oW]")
          _ <- Ns(ids).string.replaceAll("[oW]", "X").update.transact
          _ <- Ns.string.a1.query.get.map(_ ==> List("HellX", "XXrld"))
        } yield ()
      }
    }


    "toLower" - types { implicit conn =>
      for {
        ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)
        _ <- Ns(ids).string.toLower.update.transact
        _ <- Ns.string.a1.query.get.map(_ ==> List("hello", "world"))
      } yield ()
    }


    "toUpper" - types { implicit conn =>
      for {
        ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)
        _ <- Ns(ids).string.toUpper.update.transact
        _ <- Ns.string.a1.query.get.map(_ ==> List("HELLO", "WORLD"))
      } yield ()
    }
  }
}
