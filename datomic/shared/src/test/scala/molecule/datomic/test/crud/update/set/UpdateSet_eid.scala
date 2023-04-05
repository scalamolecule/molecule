package molecule.datomic.test.crud.update.set

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSet_eid extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      for {
        eid <- Ns.ints.insert(Set(1)).transact.map(_.eids.head)
        _ <- Ns.ints.query.get.map(_ ==> List(Set(1)))

        _ <- Ns(eid).ints(Set(2)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> List(Set(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(eid).strings(Set("a")).update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(eid).strings(Set("a")).upsert.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), Some(Set("a")))))
      } yield ()
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.ints.insert(Set(1), Set(2), Set(3)).transact.map(_.eids)
        _ <- Ns.e.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(2)),
          (c, Set(3)),
        ))

        _ <- Ns(List(b, c)).ints(4).update.transact
        _ <- Ns.e.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(4)),
          (c, Set(4)),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        eid <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.eids.head)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(eid).strings().update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(1), None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        eid <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.eids.head)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(eid).ints(2).strings("b").update.transact
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(2), Set("b"))))
      } yield ()
    }


    "Referenced attributes" - types { implicit conn =>
      for {
        eid <- Ns.ints(Set(1)).Ref.ii(Set(2)).save.transact.map(_.eids.head)
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(1), Set(2))))

        _ <- Ns(eid).ints(Set(3)).Ref.ii(Set(4)).update.transact
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(3), Set(4))))

        _ <- Ns(eid).Ref.ii(5).update.transact
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(3), Set(5))))
      } yield ()
    }


    "Update composite attributes" - types { implicit conn =>
      for {
        eid <- (Ns.ints.strings + Ref.ii.ss)
          .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.map(_.eids.head)
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(1), Set("a")), (Set(2), Set("b"))))


        // Composite sub groups share the same entity id
        _ <- (Ns(eid).ints(Set(3)).strings(Set("c")) + Ref.ii(Set(4)).ss(Set("d"))).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(3), Set("c")), (Set(4), Set("d"))))

        // Updating sub group with same entity id
        _ <- Ref(eid).ii(Set(5)).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(3), Set("c")), (Set(5), Set("d"))))
      } yield ()
    }


    "Update tx meta data" - types { implicit conn =>
      for {
        eid <- Ns.ints.Tx(Other.ss_(Set("tx"))).insert(Set(1)).transact.map(_.eids.head)
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(1), Set("tx")))

        tx <- Ns(eid).ints(2).Tx(Other.ss(Set("tx2"))).update.transact.map(_.tx)
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(2), Set("tx2")))

        _ <- Ns(eid).Tx(Other.ss(Set("tx3"))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update tx meta data only."
        }

        // We can though update the tx entity itself
        _ <- Other(tx).ss(Set("tx3")).update.transact
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(2), Set("tx3")))
      } yield ()
    }


    "Composite + tx meta data" - types { implicit conn =>
      for {
        eid <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii_(Set(42)))
          .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.map(_.eids.head)
        _ <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.map(_.head ==>
          ((Set(1), Set("a")), (Set(2), Set("b")), Set(42)))

        // Composite sub groups share the same entity id
        _ <- (Ns(eid).ints(Set(3)).strings(Set("c")) +
          Ref.ii(Set(4)).ss(Set("d"))).Tx(Other.ii(Set(43))).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.map(_.head ==>
          ((Set(3), Set("c")), (Set(4), Set("d")), Set(43)))
      } yield ()
    }


    "Semantics" - {

      "e_(eid) not allowed" - types { implicit conn =>
        for {
          _ <- Ns.e_(42).ints(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update by applying entity ids to e_"
          }
        } yield ()
      }

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).ints(Seq(Set(1), Set(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

          // Same as
          _ <- Ns(42).ints(Set(1), Set(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

          // Same as
          _ <- Ns(42).ints(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns(42).ints_?(Some(Set(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              """AttrSetOptInt("Ns", "ints", Appl, Some(Seq(Set(1))), None, Nil, Nil, None, None)"""
          }
        } yield ()
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns(42).i(1).Refs.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update attributes in card-many referenced namespaces. Found `Refs`"
          }
        } yield ()
      }
    }
  }
}
