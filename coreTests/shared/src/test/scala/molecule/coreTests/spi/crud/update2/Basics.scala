package molecule.coreTests.spi.crud.update2

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Basics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Default upsert semantics" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Attribute not yet asserted
        _ <- Ns.int.query.get.map(_ ==> Nil)

        // Add attribute value if no value exists already
        _ <- Ns(id).int(1).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Change existing attribute value
        _ <- Ns(id).int(2).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Force updating only existing value" - types { implicit conn =>
      for {
        ids <- Ns.i.int_?.insert(
          (0, None),
          (1, Some(1)),
        ).transact.map(_.ids)

        // Ensure attribute already has a value by adding tacit attribute
        _ <- Ns(ids).int_.int(2).update.transact

        // Only second entity updated
        _ <- Ns.i.int_?.query.get.map(_ ==> List(
          (0, None),
          (1, Some(2))
        ))

        // Without the tacit attribute, both entities are updated
        _ <- Ns(ids).int(3).update.transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List(
          (0, Some(3)),
          (1, Some(3))
        ))
      } yield ()
    }


    "Can't update optional values" - types { implicit conn =>
      for {
        _ <- Ns("42").int_?(Some(1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrOneOptInt("Ns", "int", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 8))"""
          }

        _ <- Ns("42").intSet_?(Some(Set(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrSetOptInt("Ns", "intSet", Eq, Some(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 32))"""
          }

        _ <- Ns("42").intSeq_?(Some(Seq(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrSeqOptInt("Ns", "intSeq", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 55))"""
          }

        _ <- Ns("42").intMap_?(Some(Map("a" -> 1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrMapOptInt("Ns", "intMap", Eq, Some(Map(("a", 1))), None, None, Nil, Nil, None, None, Seq(0, 77))"""
          }
      } yield ()
    }
  }
}
