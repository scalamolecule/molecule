package molecule.coreTests.spi.transaction.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import org.scalactic.Equality

case class AttrOpDecimal_Double(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  implicit val tolerance: Equality[Double] = tolerantDoubleEquality(toleranceDouble)

  "plus" - types { implicit conn =>
    for {
      id <- Entity.double(double1).save.transact.map(_.id)
      _ <- Entity(id).double.+(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.-(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.double(double2).save.transact.map(_.id)
      _ <- Entity(id).double.*(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double2 * double2)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.double(double4).save.transact.map(_.id)
      _ <- Entity(id).double./(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double4 / double2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.negate.update.transact
      _ <- Entity.double.d1.query.get.map(_ ==> List(double1, -double2))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.abs.update.transact
      _ <- Entity.double.a1.query.get.map(_ ==> List(double1, double2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.absNeg.update.transact
      _ <- Entity.double.d1.query.get.map(_ ==> List(-double1, -double2))
    } yield ()
  }


  // ceil/floor only available for decimal numbers

  "ceil" - types { implicit conn =>
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.ceil.update.transact
      _ <- Entity.double.query.get.map(_.head ==> int4)
    } yield ()
  }

  "floor" - types { implicit conn =>
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.floor.update.transact
      _ <- Entity.double.query.get.map(_.head ==> int3)
    } yield ()
  }
}
