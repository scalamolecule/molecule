package molecule.coreTests.test.crud.update.set

import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSet_id extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      for {
        id <- Ns.ints.insert(Set(1)).transact.map(_.id)
        _ <- Ns.ints.query.get.map(_ ==> List(Set(1)))

        _ <- Ns(id).ints(Set(2)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> List(Set(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).strings(Set("a")).update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).strings(Set("a")).upsert.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), Some(Set("a")))))
      } yield ()
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.ints.insert(Set(1), Set(2), Set(3)).transact.map(_.ids)
        _ <- Ns.id.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(2)),
          (c, Set(3)),
        ))

        _ <- Ns(List(b, c)).ints(4).update.transact
        _ <- Ns.id.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(4)),
          (c, Set(4)),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).strings().update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(1), None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).ints(2).strings("b").update.transact
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(2), Set("b"))))
      } yield ()
    }


    "Referenced attributes" - types { implicit conn =>
      for {
        id <- Ns.ints(Set(1)).Ref.ii(Set(2)).save.transact.map(_.id)
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(1), Set(2))))

        _ <- Ns(id).ints(Set(3)).Ref.ii(Set(4)).update.transact
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(3), Set(4))))

        _ <- Ns(id).Ref.ii(5).update.transact
        _ <- Ns.ints.Ref.ii.query.get.map(_ ==> List((Set(3), Set(5))))
      } yield ()
    }


    "Update composite attributes" - types { implicit conn =>
      for {
        id <- (Ns.ints.strings + Ref.ii.ss)
          .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.map(_.id)
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(1), Set("a")), (Set(2), Set("b"))))


        // Composite sub groups share the same entity id
        _ <- (Ns(id).ints(Set(3)).strings(Set("c")) + Ref.ii(Set(4)).ss(Set("d"))).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(3), Set("c")), (Set(4), Set("d"))))

        // Updating sub group with same entity id
        _ <- Ref(id).ii(Set(5)).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).query.get.map(_.head ==> ((Set(3), Set("c")), (Set(5), Set("d"))))
      } yield ()
    }


    "Update tx meta data" - types { implicit conn =>
      for {
        id <- Ns.ints.Tx(Other.ss_(Set("tx"))).insert(Set(1)).transact.map(_.id)
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(1), Set("tx")))

        tx <- Ns(id).ints(2).Tx(Other.ss(Set("tx2"))).update.transact.map(_.tx)
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(2), Set("tx2")))

        _ <- Ns(id).Tx(Other.ss(Set("tx3"))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please apply the tx id to the namespace of tx meta data to be updated."
        }

        // We can though update the tx entity itself
        _ <- Other(tx).ss(Set("tx3")).update.transact
        _ <- Ns.ints.Tx(Other.ss).query.get.map(_.head ==> (Set(2), Set("tx3")))
      } yield ()
    }


    "Composite + tx meta data" - types { implicit conn =>
      for {
        id <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii_(Set(42)))
          .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.map(_.id)
        _ <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.map(_.head ==>
          ((Set(1), Set("a")), (Set(2), Set("b")), Set(42)))

        // Composite sub groups share the same entity id
        _ <- (Ns(id).ints(Set(3)).strings(Set("c")) +
          Ref.ii(Set(4)).ss(Set("d"))).Tx(Other.ii(Set(43))).update.transact
        _ <- (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.map(_.head ==>
          ((Set(3), Set("c")), (Set(4), Set("d")), Set(43)))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).ints(Seq(Set(1), Set(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

          // Same as
          _ <- Ns(42).ints(Set(1), Set(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

          // Same as
          _ <- Ns(42).ints(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns(42).ints_?(Some(Set(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              """AttrSetOptInt("Ns", "ints", Eq, Some(Seq(Set(1))), None, None, Nil, Nil, None, None)"""
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
    }
  }
}
