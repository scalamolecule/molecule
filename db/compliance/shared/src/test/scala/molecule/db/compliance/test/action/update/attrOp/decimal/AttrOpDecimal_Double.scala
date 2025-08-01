package molecule.db.compliance.test.action.update.attrOp.decimal

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AttrOpDecimal_Double(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  given Equality[Double] = tolerantDoubleEquality(toleranceDouble)

  "plus" - types {
    for {
      id <- Entity.double(double1).save.transact.map(_.id)
      _ <- Entity(id).double.+(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double3)
    } yield ()
  }

  "minus" - types {
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.-(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double1)
    } yield ()
  }

  "times" - types {
    for {
      id <- Entity.double(double2).save.transact.map(_.id)
      _ <- Entity(id).double.*(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double2 * double2)
    } yield ()
  }

  "divide" - types {
    for {
      id <- Entity.double(double4).save.transact.map(_.id)
      _ <- Entity(id).double./(double2).update.transact
      _ <- Entity.double.query.get.map(_.head ==~ double4 / double2)
    } yield ()
  }

  "negate" - types {
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.negate.update.transact
      _ <- Entity.double.d1.query.get.map(_ ==> List(double1, -double2))
    } yield ()
  }

  "absolute" - types {
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.abs.update.transact
      _ <- Entity.double.a1.query.get.map(_ ==> List(double1, double2))
    } yield ()
  }

  "absolute negative" - types {
    for {
      ids <- Entity.double.insert(-double1, double2).transact.map(_.ids)
      _ <- Entity(ids).double.absNeg.update.transact
      _ <- Entity.double.d1.query.get.map(_ ==> List(-double1, -double2))
    } yield ()
  }


  // ceil/floor only available for decimal numbers

  "ceil" - types {
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.ceil.update.transact
      _ <- Entity.double.query.get.map(_.head ==> int4)
    } yield ()
  }

  "floor" - types {
    for {
      id <- Entity.double(double3).save.transact.map(_.id)
      _ <- Entity(id).double.floor.update.transact
      _ <- Entity.double.query.get.map(_.head ==> int3)
    } yield ()
  }
}
