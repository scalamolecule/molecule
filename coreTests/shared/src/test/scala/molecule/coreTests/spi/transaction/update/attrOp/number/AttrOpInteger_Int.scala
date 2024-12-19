package molecule.coreTests.spi.transaction.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpInteger_Int extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "plus" - types { implicit conn =>
      for {
        id <- Ns.int(int1).save.transact.map(_.id)
        _ <- Ns(id).int.+(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.int(int3).save.transact.map(_.id)
        _ <- Ns(id).int.-(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.int(int2).save.transact.map(_.id)
        _ <- Ns(id).int.*(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int4)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.int(int4).save.transact.map(_.id)
        _ <- Ns(id).int./(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int2)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.int.insert(-int1, int2).transact.map(_.ids)
        _ <- Ns(ids).int.negate.update.transact
        _ <- Ns.int.d1.query.get.map(_ ==> List(int1, -int2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.int.insert(-int1, int2).transact.map(_.ids)
        _ <- Ns(ids).int.abs.update.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.int.insert(-int1, int2).transact.map(_.ids)
        _ <- Ns(ids).int.absNeg.update.transact
        _ <- Ns.int.d1.query.get.map(_ ==> List(-int1, -int2))
      } yield ()
    }


    // even/odd/modulo not allowed for updates

    "No even" - types { implicit conn =>
      for {
        id <- Ns.int(int4).save.transact.map(_.id)
        _ <- Ns(id).int.even.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.int.even and Ns.int.odd can't be used with updates."
          }
      } yield ()
    }

    "No odd" - types { implicit conn =>
      for {
        id <- Ns.int(int4).save.transact.map(_.id)
        _ <- Ns(id).int.odd.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.int.even and Ns.int.odd can't be used with updates."
          }
      } yield ()
    }

    "No modulo" - types { implicit conn =>
      for {
        id <- Ns.int(int4).save.transact.map(_.id)
        _ <- Ns(id).int.%(int3, int2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Modulo operations like Ns.int.%(3) can't be used with updates."
          }
      } yield ()
    }
  }
}
