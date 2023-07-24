package molecule.coreTests.test.crud.update.one

import molecule.base.error._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

trait UpdateOne_id extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


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

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).int(2).string("b").update.transact
        _ <- Ns.int.string.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "Referenced attributes" - types { implicit conn =>
      for {
        id <- Ns.i(1).Ref.i(2).save.transact.map(_.id)
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((1, 2)))

        _ <- Ns(id).i(3).Ref.i(4).update.transact
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 4)))

        _ <- Ns(id).Ref.i(5).update.transact
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 5)))
      } yield ()
    }


    "Update composite attributes" - types { implicit conn =>
      for {
        id <- (Ns.int.string + Ref.i.s).insert((1, "a"), (2, "b")).transact.map(_.id)
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((1, "a"), (2, "b"))))

        // Composite sub groups share the same entity id
        _ <- (Ns(id).int(3).string("c") + Ref.i(4).s("d")).update.transact
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((3, "c"), (4, "d"))))

        // Updating sub group with same entity id
        _ <- Ref(id).i(5).update.transact
        _ <- (Ns.int.string + Ref.i.s).query.get.map(_ ==> List(((3, "c"), (5, "d"))))
      } yield ()
    }


    "Update tx meta data" - types { implicit conn =>
      for {
        id <- Ns.int.Tx(Other.s_("tx")).insert(1).transact.map(_.id)
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((1, "tx")))

        tx <- Ns(id).int(2).Tx(Other.s("tx2")).update.transact.map(_.tx)
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((2, "tx2")))


        _ <- Ns(id).Tx(Other.s("tx3")).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please apply the tx id to the namespace of tx meta data to be updated."
        }

        // We can though update the tx entity itself
        _ <- Other(tx).s("tx3").update.transact
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((2, "tx3")))
      } yield ()
    }


    "Composite + tx meta data" - types { implicit conn =>
      for {
        id <- (Ns.int.string + Ref.i.s).Tx(Other.i_(42)).insert((1, "a"), (2, "b")).transact.map(_.id)
        _ <- (Ns.int.string + Ref.i.s).Tx(Other.i).query.get.map(_ ==> List(((1, "a"), (2, "b"), 42)))

        // Composite sub groups share the same entity id
        _ <- (Ns(id).int(3).string("c") + Ref.i(4).s("d")).Tx(Other.i(43)).update.transact
        _ <- (Ns.int.string + Ref.i.s).Tx(Other.i).query.get.map(_ ==> List(((3, "c"), (4, "d"), 43)))
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
            err ==> "Can't update attributes in card-many referenced namespaces. Found `Refs`"
          }
        } yield ()
      }
    }
  }
}
