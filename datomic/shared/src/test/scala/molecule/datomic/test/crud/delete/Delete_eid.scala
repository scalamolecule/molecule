package molecule.datomic.test.crud.delete

import molecule.base.error._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.async._
import utest._
import molecule.core.util.Executor._
import molecule.datomic.setup.DatomicTestSuite

object Delete_eid extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 entity" - refs { implicit conn =>
      for {
        List(e1, _) <- Ns.i.insert(1, 2).transact.map(_.eids)
        _ <- Ns.i.query.get.map(_ ==> List(1, 2))
        _ <- Ns(e1).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "n entities vararg" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- Ns.i.insert(1, 2, 3).transact.map(_.eids)
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(e1, e2).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "n entities iterable" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- Ns.i.insert(1, 2, 3).transact.map(_.eids)
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(Seq(e1, e2)).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "0 entities" - refs { implicit conn =>
      for {
        _ <- Ns.i.insert(1, 2, 3).transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))

        _ <- Ns(Nil).delete.transact

        // No entities deleted
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
      } yield ()
    }


    "Referenced entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- Ns.i.R1.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.eid)

          // 2 entities, each referencing another entity
          _ <- Ns.i.a1.R1.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 other entities
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- Ns(e1).delete.transact

          // 1 entity with a referenced to another entity left
          _ <- Ns.i.R1.i.query.get.map(_ ==> List((2, 20)))

          // 2 other entities remain
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 20))
        } yield ()
      }


      "Card-many" - refs { implicit conn =>
        for {
          e1 <- Ns.i.Rs1.*(R1.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.eid)

          // 2 entities, each with 2 owned sub-entities
          _ <- Ns.i.a1.Rs1.*(R1.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))
          // 4 referenced entities
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- Ns(e1).delete.transact

          // 1 entity with 2 owned sub-entities left
          _ <- Ns.i.Rs1.*(R1.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))
          // 2 other entities remain
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))
        } yield ()
      }
    }


    "Owned entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- Ns.i.Owned1.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.eid)

          // 2 entities, each with an owned sub-entity
          _ <- Ns.i.a1.Owned1.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 sub-entities
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- Ns(e1).delete.transact

          // 1 entity with 1 owned sub-entity left
          _ <- Ns.i.Owned1.i.query.get.map(_ ==> List((2, 20)))
          _ <- R1.i.query.get.map(_ ==> List(20))
        } yield ()
      }


      "Card-many" - refs { implicit conn =>
        for {
          e1 <- Ns.i.OwnedMany1.*(R1.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.eid)

          // 2 entities, each with 2 owned sub-entities
          _ <- Ns.i.a1.OwnedMany1.*(R1.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))
          // 4 sub-entities
          _ <- R1.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- Ns(e1).delete.transact

          // 1 entity with 2 owned sub-entities left
          _ <- Ns.i.OwnedMany1.*(R1.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))
          _ <- R1.i.a1.query.get.map(_ ==> List(20, 21))
        } yield ()
      }
    }


    "Composite" - refs { implicit conn =>
      for {
        List(e1, e2, _) <- (Ns.i + R1.i).insert(
          (1, 10),
          (2, 20),
          (3, 30),
        ).transact.map(_.eids)

        // 3 composite entities, each with 2 attributes from 2 namespaces
        _ <- (Ns.i.a1 + R1.i).query.get.map(_ ==> List(
          (1, 10),
          (2, 20),
          (3, 30),
        ))
        // 3 composite sub-groups
        _ <- R1.i.a1.query.get.map(_ ==> List(10, 20, 30))

        // Delete entity using namespace of first group
        _ <- Ns(e1).delete.transact
        // Delete entity using namespace of second group
        _ <- R1(e2).delete.transact

        // 1 composite entity with 2 attributes from 2 namespaces left
        _ <- (Ns.i + R1.i).query.get.map(_ ==> List((3, 30)))
        _ <- R1.i.query.get.map(_ ==> List(30))

      } yield ()
    }


    "Semantics" - {

      "e_(eid) not allowed" - refs { implicit conn =>
        for {
          _ <- Ns.e_(42).i(2).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't delete by applying entity ids to e_"
          }

          _ <- Ns.e_(42).i(2).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't delete by applying entity ids to e_"
          }
        } yield ()
      }

      "Tacit generic attributes not allowed" - refs { implicit conn =>
        for {
          _ <- Ns(42).a_("x").delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Generic attributes not allowed in update molecule. Found:\n" +
              """AttrOneTacString("_Generic", "a", Eq, Seq("x"), None, Nil, Nil, None, None)"""
          }
        } yield ()
      }

      "Mandatory generic attributes not allowed" - refs { implicit conn =>
        for {
          _ <- Ns(42).a("x").delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Generic attributes not allowed in update molecule. Found:\n" +
              """AttrOneManString("_Generic", "a", Eq, Seq("x"), None, Nil, Nil, None, None)"""
          }
        } yield ()
      }

      "Can't update multiple values for one card-one attribute" - refs { implicit conn =>
        for {
          _ <- Ns(42).i(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one value for attribute `Ns.i`. Found: 2, 3"
          }
        } yield ()
      }

      "Can't update optional values" - refs { implicit conn =>
        for {
          _ <- Ns(42).i_?(Some(1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              """AttrOneOptInt("Ns", "i", Eq, Some(Seq(1)), None, Nil, Nil, None, None)"""
          }
        } yield ()
      }

      "Can't update card-many referenced attributes" - refs { implicit conn =>
        for {
          _ <- Ns(42).i(1).Rs1.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update attributes in card-many referenced namespaces. Found `Rs1`"
          }
        } yield ()
      }
    }
  }
}
