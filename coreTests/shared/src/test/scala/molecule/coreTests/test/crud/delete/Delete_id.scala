package molecule.coreTests.test.crud.delete

import molecule.base.error._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import utest._
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync

trait Delete_id extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "1 entity" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(e1).delete.transact
        // or
        _ <- A.id_(e2).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }


    "n entities vararg" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(e1, e2).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "n entities iterable" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(Seq(e1, e2)).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "0 entities" - refs { implicit conn =>
      for {
        _ <- A.i.insert(1, 2, 3).transact
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))

        _ <- A(Nil).delete.transact

        // No entities deleted
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))
      } yield ()
    }


    "Referenced entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- A.i.B.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.id)

          // 2 entities, each referencing another entity
          _ <- A.i.a1.B.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 other entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- A(e1).delete.transact

          // 1 entity with a referenced to another entity left
          _ <- A.i.B.i.query.get.map(_ ==> List((2, 20)))

          // 2 other entities remain
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        } yield ()
      }


      "Card-many" - refs { implicit conn =>
        for {
          e1 <- A.i.Bb.*(B.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.id)

          // 2 entities, each with 2 owned sub-entities
          _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))
          // 4 referenced entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- A(e1).delete.transact

          // 1 entity with 2 owned sub-entities left
          _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))
          // 2 other entities remain
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))
        } yield ()
      }
    }


    "Owned entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnB.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.id)

          // 2 entities, each with an owned sub-entity
          _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 sub-entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- A(e1).delete.transact

          // 1 entity with 1 owned sub-entity left
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((2, 20)))
          _ <- B.i.query.get.map(_ ==> List(20))
        } yield ()
      }


      "Card-many" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnBb.*(B.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.id)

          // 2 entities, each with 2 owned sub-entities
          _ <- A.i.a1.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))
          // 4 sub-entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- A(e1).delete.transact

          // 1 entity with 2 owned sub-entities left
          _ <- A.i.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(20, 21))
        } yield ()
      }
    }


    "Composite" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- (A.i + B.i).insert(
          (1, 10),
          (2, 20),
          (3, 30),
        ).transact.map(_.ids)

        // 3 composite entities, each with 2 attributes from 2 namespaces
        _ <- (A.i.a1 + B.i).query.get.map(_ ==> List(
          (1, 10),
          (2, 20),
          (3, 30),
        ))
        // 3 composite sub-groups
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20, 30))

        // Delete entity using namespace of first group
        _ <- A(e1).delete.transact
        // Delete entity using namespace of second group
        _ <- B(e2).delete.transact

        // 1 composite entity with 2 attributes from 2 namespaces left
        _ <- (A.i + B.i).query.get.map(_ ==> List((3, 30)))
        _ <- B.i.query.get.map(_ ==> List(30))

      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - refs { implicit conn =>
        for {
          _ <- A(42).i(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one value for attribute `A.i`. Found: 2, 3"
          }
        } yield ()
      }

      "Can't update optional values" - refs { implicit conn =>
        for {
          _ <- A(42).i_?(Some(1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              """AttrOneOptInt("A", "i", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None)"""
          }
        } yield ()
      }

      "Can't update card-many referenced attributes" - refs { implicit conn =>
        for {
          _ <- A(42).i(1).Bb.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update attributes in card-many referenced namespaces. Found `Bb`"
          }
        } yield ()
      }
    }
  }
}
