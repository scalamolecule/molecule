// GENERATED CODE ********************************
package molecule.coreTests.spi.action.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import org.scalactic.Equality

case class AttrOpDecimal_BigDecimal(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  implicit val tolerance: Equality[BigDecimal] = tolerantBigDecimalEquality(toleranceBigDecimal)

  "plus" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal1).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal.+(bigDecimal2).update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==~ bigDecimal3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal3).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal.-(bigDecimal2).update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==~ bigDecimal1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal2).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal.*(bigDecimal2).update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==~ bigDecimal2 * bigDecimal2)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal4).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal./(bigDecimal2).update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==~ bigDecimal4 / bigDecimal2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
      _ <- Entity(ids).bigDecimal.negate.update.transact
      _ <- Entity.bigDecimal.d1.query.get.map(_ ==> List(bigDecimal1, -bigDecimal2))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
      _ <- Entity(ids).bigDecimal.abs.update.transact
      _ <- Entity.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
      _ <- Entity(ids).bigDecimal.absNeg.update.transact

      _ <- if (database == "sqlite") {
        // Sort output instead since BigDecimals saved as Text in SQlite sort lexicographically
        Entity.bigDecimal.query.get.map(_.sorted.reverse ==> List(-bigDecimal1, -bigDecimal2))
      } else {
        Entity.bigDecimal.d1.query.get.map(_ ==> List(-bigDecimal1, -bigDecimal2))
      }
    } yield ()
  }


  // ceil/floor only available for decimal numbers

  "ceil" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal3).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal.ceil.update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==> int4)
    } yield ()
  }

  "floor" - types { implicit conn =>
    for {
      id <- Entity.bigDecimal(bigDecimal3).save.transact.map(_.id)
      _ <- Entity(id).bigDecimal.floor.update.transact
      _ <- Entity.bigDecimal.query.get.map(_.head ==> int3)
    } yield ()
  }
}