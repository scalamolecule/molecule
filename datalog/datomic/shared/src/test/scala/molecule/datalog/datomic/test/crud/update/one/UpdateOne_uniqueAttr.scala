package molecule.datalog.datomic.test.crud.update.one

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object UpdateOne_uniqueAttr extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Update by unique attribute value, check types" - unique { implicit conn =>
      for {
        // Initial values
        _ <- Unique.i(1).string(string1).save.transact
        _ <- Unique.i(1).int(int1).save.transact
        _ <- Unique.i(1).long(long1).save.transact
        _ <- Unique.i(1).float(float1).save.transact
        _ <- Unique.i(1).double(double1).save.transact
        _ <- Unique.i(1).boolean(boolean1).save.transact
        _ <- Unique.i(1).bigInt(bigInt1).save.transact
        _ <- Unique.i(1).bigDecimal(bigDecimal1).save.transact
        _ <- Unique.i(1).date(date1).save.transact
        _ <- Unique.i(1).uuid(uuid1).save.transact
        _ <- Unique.i(1).uri(uri1).save.transact
        _ <- Unique.i(1).byte(byte1).save.transact
        _ <- Unique.i(1).short(short1).save.transact
        _ <- Unique.i(1).char(char1).save.transact

        // Update with looked up unique value
        _ <- Unique.i(2).string_(string1).update.transact
        _ <- Unique.i(2).int_(int1).update.transact
        _ <- Unique.i(2).long_(long1).update.transact
        _ <- Unique.i(2).float_(float1).update.transact
        _ <- Unique.i(2).double_(double1).update.transact
        _ <- Unique.i(2).boolean_(boolean1).update.transact
        _ <- Unique.i(2).bigInt_(bigInt1).update.transact
        _ <- Unique.i(2).bigDecimal_(bigDecimal1).update.transact
        _ <- Unique.i(2).date_(date1).update.transact
        _ <- Unique.i(2).uuid_(uuid1).update.transact
        _ <- Unique.i(2).uri_(uri1).update.transact
        _ <- Unique.i(2).byte_(byte1).update.transact
        _ <- Unique.i(2).short_(short1).update.transact
        _ <- Unique.i(2).char_(char1).update.transact

        // i has been updated
        _ <- Unique.i.string_.query.get.map(_ ==> List(2))
        _ <- Unique.i.int_.query.get.map(_ ==> List(2))
        _ <- Unique.i.long_.query.get.map(_ ==> List(2))
        _ <- Unique.i.float_.query.get.map(_ ==> List(2))
        _ <- Unique.i.double_.query.get.map(_ ==> List(2))
        _ <- Unique.i.boolean_.query.get.map(_ ==> List(2))
        _ <- Unique.i.bigInt_.query.get.map(_ ==> List(2))
        _ <- Unique.i.bigDecimal_.query.get.map(_ ==> List(2))
        _ <- Unique.i.date_.query.get.map(_ ==> List(2))
        _ <- Unique.i.uuid_.query.get.map(_ ==> List(2))
        _ <- Unique.i.uri_.query.get.map(_ ==> List(2))
        _ <- Unique.i.byte_.query.get.map(_ ==> List(2))
        _ <- Unique.i.short_.query.get.map(_ ==> List(2))
        _ <- Unique.i.char_.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Update unique value itself" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1).transact
        _ <- Unique.int.query.get.map(_ ==> List(1))

        // Find by current value 1 and update to 2
        _ <- Unique.int_(1).int(2).update.transact
        _ <- Unique.int.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Multiple entities updated" - unique { implicit conn =>
      for {
        List(a, b, c) <- Unique.i.int.insert(
          (1, int1),
          (2, int2),
          (3, int3),
        ).transact.map(_.eids)
        _ <- Unique.eid.a1.i.int.query.get.map(_ ==> List(
          (a, 1, int1),
          (b, 2, int2),
          (c, 3, int3),
        ))

        _ <- Unique.i(4).int_(int2, int3).update.transact
        _ <- Unique.eid.a1.i.query.get.map(_ ==> List(
          (a, 1),
          (b, 4),
          (c, 4),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - unique { implicit conn =>
      for {
        _ <- Unique.int.i.s.insert(0, 1, "a").transact
        _ <- Unique.i.s.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Unique.int_(0).s().update.transact
        _ <- Unique.i.s_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Update multiple attributes" - unique { implicit conn =>
      for {
        _ <- Unique.int.i.s.insert(0, 1, "a").transact
        _ <- Unique.i.s.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Unique.int_(0).i(2).s("b").update.transact
        _ <- Unique.i.s.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "Referenced attributes" - unique { implicit conn =>
      for {
        _ <- Unique.int(0).i(1).Ref.i(2).save.transact
        _ <- Unique.i.Ref.i.query.get.map(_ ==> List((1, 2)))

        _ <- Unique.int_(0).i(3).Ref.i(4).update.transact
        _ <- Unique.i.Ref.i.query.get.map(_ ==> List((3, 4)))

        _ <- Unique.int_(0).Ref.i(5).update.transact
        _ <- Unique.i.Ref.i.query.get.map(_ ==> List((3, 5)))
      } yield ()
    }


    "Update composite attributes" - unique { implicit conn =>
      for {
        _ <- (Unique.int.i.s + Ref.i.s).insert((0, 1, "a"), (2, "b")).transact
        _ <- (Unique.i.s + Ref.i.s).query.get.map(_ ==> List(((1, "a"), (2, "b"))))

        // Composite sub groups share the same entity id
        _ <- (Unique.int_(0).i(3).s("c") + Ref.i(4).s("d")).update.transact
        _ <- (Unique.i.s + Ref.i.s).query.get.map(_ ==> List(((3, "c"), (4, "d"))))
      } yield ()
    }


    "Update tx meta data" - unique { implicit conn =>
      for {
        _ <- Unique.int.i.Tx(Other.i_(42).s_("tx")).insert(0, 1).transact
        _ <- Unique.i.Tx(Other.i.s).query.get.map(_ ==> List((1, 42, "tx")))

        _ <- Unique.int_(0).i(2).Tx(Other.i(43).s("tx2")).update.transact
        _ <- Unique.i.Tx(Other.i.s).query.get.map(_ ==> List((2, 43, "tx2")))

        _ <- Unique.int_(0).Tx(Other.s("tx3")).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please apply the tx id to the namespace of tx meta data to be updated."
        }

        // We can though update the tx entity itself if it has a unique value (i has here)
        _ <- Other.i_(43).i(44).s("tx3").update.transact
        _ <- Unique.i.Tx(Other.i.s).query.get.map(_ ==> List((2, 44, "tx3")))
      } yield ()
    }


    "Composite + tx meta data" - unique { implicit conn =>
      for {
        _ <- (Unique.int.i.s + Ref.i.s).Tx(Other.i_(42).s_("tx")).insert((0, 1, "a"), (2, "b")).transact
        _ <- (Unique.i.s + Ref.i.s).Tx(Other.i.s).query.get.map(_ ==> List(((1, "a"), (2, "b"), 42, "tx")))

        // Composite sub groups share the same entity id
        _ <- (Unique.int_(0).i(3).s("c") + Ref.i(4).s("d")).Tx(Other.i(43).s("tx2")).update.transact
        _ <- (Unique.i.s + Ref.i.s).Tx(Other.i.s).query.get.map(_ ==> List(((3, "c"), (4, "d"), 43, "tx2")))
      } yield ()
    }


    "Semantics" - unique { implicit conn =>
      for {
        _ <- Unique.i(1).i(2).int_(1).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute `Unique.i`."
        }

        _ <- Unique.i_(1).i(2).update.transact

        _ <- Unique.int_(1).string_("x").s("c").update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only apply one unique attribute value for update. Found:\n" +
            """AttrOneTacString("Unique", "string", Eq, Seq("x"), None, None, Nil, Nil, None, None)"""
        }

        _ <- Unique.ints_(1).s("b").update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            """AttrSetTacInt("Unique", "ints", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None)"""
        }
      } yield ()
    }
  }
}
