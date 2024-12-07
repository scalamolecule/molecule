package molecule.coreTests.spi.crud.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpDecimal_Double extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {
    implicit val tolerance = tolerantDoubleEquality(toleranceDouble)

    "plus" - types { implicit conn =>
      for {
        id <- Ns.double(double1).save.transact.map(_.id)
        _ <- Ns(id).double.+(double2).update.transact
        _ <- Ns.double.query.get.map(_.head ==~ double3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.double(double3).save.transact.map(_.id)
        _ <- Ns(id).double.-(double2).update.transact
        _ <- Ns.double.query.get.map(_.head ==~ double1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.double(double2).save.transact.map(_.id)
        _ <- Ns(id).double.*(double2).update.transact
        _ <- Ns.double.query.get.map(_.head ==~ double2 * double2)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.double(double4).save.transact.map(_.id)
        _ <- Ns(id).double./(double2).update.transact
        _ <- Ns.double.query.get.map(_.head ==~ double4 / double2)
      } yield ()
    }

    "modulo" - types { implicit conn =>
      for {
        id <- Ns.double(double4).save.transact.map(_.id)
        _ <- Ns(id).double.%(double3).update.transact
        _ <- Ns.double.query.get.map(_.head ==~ double1)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.double.insert(-double1, double2).transact.map(_.ids)
        _ <- Ns(ids).double.negate.update.transact
        _ <- Ns.double.d1.query.get.map(_ ==> List(double1, -double2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.double.insert(-double1, double2).transact.map(_.ids)
        _ <- Ns(ids).double.abs.update.transact
        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.double.insert(-double1, double2).transact.map(_.ids)
        _ <- Ns(ids).double.absNeg.update.transact
        _ <- Ns.double.d1.query.get.map(_ ==> List(-double1, -double2))
      } yield ()
    }

    "ceil" - types { implicit conn =>
      for {
        id <- Ns.double(double3).save.transact.map(_.id)
        _ <- Ns(id).double.ceil.update.transact
        _ <- Ns.double.query.get.map(_.head ==> int4)
      } yield ()
    }

    "floor" - types { implicit conn =>
      for {
        id <- Ns.double(double3).save.transact.map(_.id)
        _ <- Ns(id).double.floor.update.transact
        _ <- Ns.double.query.get.map(_.head ==> int3)
      } yield ()
    }
  }
}
