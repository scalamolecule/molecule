package molecule.coreTests.test.crud.update.one

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      for {
        id <- Ns.int.insert(1).transact.map(_.id)
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Update existing value
        _ <- Ns(id).int(2).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(2))

        // Or update using id_
        _ <- Ns.id_(id).int(3).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(3))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).string("a").update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((3, None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).string("a").upsert.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((3, Some("a"))))
      } yield ()
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.int.insert(1, 2, 3).transact.map(_.ids)
        _ <- Ns.id.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 2),
          (c, 3),
        ))

        _ <- Ns(List(b, c)).int(4).update.transact
        _ <- Ns.id.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 4),
          (c, 4),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.int.string.insert(1, "a").transact.map(_.id)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).string().update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Delete individual ref value(s) with update" - types { implicit conn =>
      for {
        refId <- Ref.int(7).save.transact.map(_.id)
        id <- Ns.int.ref.insert(1, refId).transact.map(_.id)
        _ <- Ns.int.ref.query.get.map(_ ==> List((1, refId)))

        // Apply empty value to delete ref id of entity (entity remains)
        _ <- Ns(id).ref().update.transact
        _ <- Ns.int.ref_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.int.string.insert(1, "a").transact.map(_.id)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        _ <- Ns(id).int(2).string("b").update.transact
        _ <- Ns.int.string.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "Referenced attributes" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- A(id).i(11).B.i(20).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 20, 3)))

        // B
        _ <- A(id).B.i(21).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).B.i(22).C.i(30).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).B.C.i(31).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).B.i(23).C.i(32).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).B.C.i(33).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 33)))
      } yield ()
    }


    "Referenced attributes with backref" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.C.i
        _ <- A(id).i(10).B.i(20)._A.C.i(30).update.transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).int(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one value for attribute `Ns.int`. Found: 2, 3"
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns(42).int_?(Some(1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                """AttrOneOptInt("Ns", "int", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None)"""
            }
        } yield ()
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns(42).i(1).Refs.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update attributes in card-many referenced namespace `Refs`"
            }
        } yield ()
      }

      "Can't upsert referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns(42).i(1).Ref.i(2).upsert.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't upsert referenced attributes. Please update instead."
            }
        } yield ()
      }
    }
  }
}
