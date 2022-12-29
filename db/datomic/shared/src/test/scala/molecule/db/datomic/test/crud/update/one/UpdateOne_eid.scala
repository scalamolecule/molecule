package molecule.db.datomic.test.crud.update.one

import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_eid extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      for {
        eid <- Ns.int.insert(1).transact.map(_.eids.head)
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Update existing value
        _ <- Ns(eid).int(2).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(2))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(eid).string("a").update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((2, None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(eid).string("a").upsert.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((2, Some("a"))))
      } yield ()
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.int.insert(1, 2, 3).transact.map(_.eids)
        _ <- Ns.e.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 2),
          (c, 3),
        ))

        // Explicitly add `multiple` to update multiple entities
        _ <- Ns(List(b, c)).int(4).update.multiple.transact
        _ <- Ns.e.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 4),
          (c, 4),
        ))

        // Not adding `multiple` prevents unintentional update of multiple (possible all!) entities

        _ <- Ns(List(b, c)).int(5).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Please provide explicit `update.multiple` to update " +
            "multiple entities (found 2 matching entities)."
        }
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        eid <- Ns.int.string.insert(1, "a").transact.map(_.eids.head)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(eid).string().update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        eid <- Ns.int.string.insert(1, "a").transact.map(_.eids.head)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(eid).int(2).string("b").update.transact
        _ <- Ns.int.string.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "Referenced attributes" - types { implicit conn =>
      for {
        eid <- Ns.i(1).Ref.i(2).save.transact.map(_.eids.head)
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((1, 2)))

        _ <- Ns(eid).i(3).Ref.i(4).update.transact
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 4)))

        _ <- Ns(eid).Ref.i(5).update.transact
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 5)))
      } yield ()
    }


    "Update composite attributes" - types { implicit conn =>
      for {
        eid <- (Ns.int.string + Ref.i.s).insert((1, "a"), (2, "b")).transact.map(_.eids.head)
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((1, "a"), (2, "b"))))

        // Composite sub groups share the same entity id
        _ <- (Ns(eid).int(3).string("c") + Ref.i(4).s("d")).update.transact
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((3, "c"), (4, "d"))))

        // Updating sub group with same entity id
        _ <- Ref(eid).i(5).update.transact
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((3, "c"), (5, "d"))))
      } yield ()
    }


    "Update tx meta data" - types { implicit conn =>
      for {
        eid <- Ns.int.Tx(Other.s_("tx")).insert(1).transact.map(_.eids.head)
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((1, "tx")))

        tx
          <- Ns(eid).int(2).Tx(Other.s("tx2")).update.transact.map(_.tx)
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((2, "tx2")))


        _ <- Ns(eid).Tx(Other.s("tx3")).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can't update tx meta data only."
        }

        // We can though update the tx entity itself
        _ <- Other(tx).s("tx3").update.transact
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((2, "tx3")))
      } yield ()
    }


    "Composite + tx meta data" - types { implicit conn =>
      for {
        eid <- (Ns.int.string + Ref.i.s).Tx(Other.i_(42)).insert((1, "a"), (2, "b")).transact.map(_.eids.head)
        _ <- (Ns.int.string + Ref.i.s).Tx(Other.i).query.get.map(_ ==> List(((1, "a"), (2, "b"), 42)))

        // Composite sub groups share the same entity id
        _ <- (Ns(eid).int(3).string("c") + Ref.i(4).s("d")).Tx(Other.i(43)).update.transact
        _ <- (Ns.int.string + Ref.i.s).Tx(Other.i).query.get.map(_ ==> List(((3, "c"), (4, "d"), 43)))
      } yield ()
    }


    "Semantics" - {

      "e_(eid) not allowed" - types { implicit conn =>
        for {
          _ <- Ns.e_(42).int(2).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can't update by applying entity ids to e_"
          }
        } yield ()
      }

      "Tacit generic attributes not allowed" - types { implicit conn =>
        for {
          _ <- Ns(42).a_("x").update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Generic attributes not allowed in update molecule. Found:\n" +
              "AttrOneTacString(_Generic,a,Appl,List(x),None,None,None)"
          }
        } yield ()
      }

      "Mandatory generic attributes not allowed" - types { implicit conn =>
        for {
          _ <- Ns(42).a("x").update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Generic attributes not allowed in update molecule. Found:\n" +
              "AttrOneManString(_Generic,a,Appl,List(x),None,None,None)"
          }
        } yield ()
      }

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).int(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(2, 3)"
          }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns(42).int_?(Some(1)).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can't update optional values. Found:\n" +
              "AttrOneOptInt(Ns,int,Appl,Some(List(1)),None,None,None)"
          }
        } yield ()
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns(42).i(1).Refs.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can't update attributes in card-many referenced namespaces. Found `Refs`"
          }
        } yield ()
      }

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).int(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(2, 3)"
          }
        } yield ()
      }
    }
  }
}
