package molecule.db.datomic.test.crud.updateOne

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object UpdateOne_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update by unique attribute value, check types" - unique { implicit conn =>
      // Initial values
      Unique.i(1).string(string1).save.transact
      Unique.i(1).int(int1).save.transact
      Unique.i(1).long(long1).save.transact
      Unique.i(1).float(float1).save.transact
      Unique.i(1).double(double1).save.transact
      Unique.i(1).boolean(boolean1).save.transact
      Unique.i(1).bigInt(bigInt1).save.transact
      Unique.i(1).bigDecimal(bigDecimal1).save.transact
      Unique.i(1).date(date1).save.transact
      Unique.i(1).uuid(uuid1).save.transact
      Unique.i(1).uri(uri1).save.transact
      Unique.i(1).byte(byte1).save.transact
      Unique.i(1).short(short1).save.transact
      Unique.i(1).char(char1).save.transact

      // Update with looked up unique value
      Unique.i(2).string_(string1).update.transact
      Unique.i(2).int_(int1).update.transact
      Unique.i(2).long_(long1).update.transact
      Unique.i(2).float_(float1).update.transact
      Unique.i(2).double_(double1).update.transact
      Unique.i(2).boolean_(boolean1).update.transact
      Unique.i(2).bigInt_(bigInt1).update.transact
      Unique.i(2).bigDecimal_(bigDecimal1).update.transact
      Unique.i(2).date_(date1).update.transact
      Unique.i(2).uuid_(uuid1).update.transact
      Unique.i(2).uri_(uri1).update.transact
      Unique.i(2).byte_(byte1).update.transact
      Unique.i(2).short_(short1).update.transact
      Unique.i(2).char_(char1).update.transact

      // i has been updated
      Unique.i.string_.query.get ==> List(2)
      Unique.i.int_.query.get ==> List(2)
      Unique.i.long_.query.get ==> List(2)
      Unique.i.float_.query.get ==> List(2)
      Unique.i.double_.query.get ==> List(2)
      Unique.i.boolean_.query.get ==> List(2)
      Unique.i.bigInt_.query.get ==> List(2)
      Unique.i.bigDecimal_.query.get ==> List(2)
      Unique.i.date_.query.get ==> List(2)
      Unique.i.uuid_.query.get ==> List(2)
      Unique.i.uri_.query.get ==> List(2)
      Unique.i.byte_.query.get ==> List(2)
      Unique.i.short_.query.get ==> List(2)
      Unique.i.char_.query.get ==> List(2)
    }


    "Update unique value itself" - unique { implicit conn =>
      Unique.int.insert(1).transact
      Unique.int.query.get ==> List(1)

      // Find by current value 1 and update to 2
      Unique.int_(1).int(2).update.transact
      Unique.int.query.get ==> List(2)
    }


    "Multiple entities updated" - unique { implicit conn =>
      val List(a, b, c) = Unique.i.int.insert(
        (1, int1),
        (2, int2),
        (3, int3),
      ).transact.eids
      Unique.e.a1.i.int.query.get ==> List(
        (a, 1, int1),
        (b, 2, int2),
        (c, 3, int3),
      )

      // Explicitly add `multiple` to update multiple entities
      Unique.i(4).int_(int2, int3).update.multiple.transact
      Unique.e.a1.i.query.get ==> List(
        (a, 1),
        (b, 4),
        (c, 4),
      )
    }


    "Delete individual attribute value(s) with update" - unique { implicit conn =>
      Unique.int.i.s.insert(0, 1, "a").transact
      Unique.i.s.query.get ==> List((1, "a"))

      // Apply empty value to delete attribute of entity (entity remains)
      Unique.int_(0).s().update.transact
      Unique.i.s_?.query.get ==> List((1, None))
    }


    "Update multiple attributes" - unique { implicit conn =>
      Unique.int.i.s.insert(0, 1, "a").transact
      Unique.i.s.query.get ==> List((1, "a"))

      // Apply empty value to delete attribute of entity (entity remains)
      Unique.int_(0).i(2).s("b").update.transact
      Unique.i.s.query.get ==> List((2, "b"))
    }


    "Referenced attributes" - unique { implicit conn =>
      Unique.int(0).i(1).Ref.i(2).save.transact
      Unique.i.Ref.i.query.get ==> List((1, 2))

      Unique.int_(0).i(3).Ref.i(4).update.transact
      Unique.i.Ref.i.query.get ==> List((3, 4))

      Unique.int_(0).Ref.i(5).update.transact
      Unique.i.Ref.i.query.get ==> List((3, 5))
    }


    "Update composite attributes" - unique { implicit conn =>
      (Unique.int.i.s + Ref.i.s).insert((0, 1, "a"), (2, "b")).transact
      (Unique.i.s + Ref.i.s).query.get ==> List(((1, "a"), (2, "b")))

      // Composite sub groups share the same entity id
      (Unique.int_(0).i(3).s("c") + Ref.i(4).s("d")).update.transact
      (Unique.i.s + Ref.i.s).query.get ==> List(((3, "c"), (4, "d")))
    }


    "Update tx meta data" - unique { implicit conn =>
      Unique.int.i.Tx(Other.i_(42).s_("tx")).insert(0, 1).transact
      Unique.i.Tx(Other.i.s).query.get ==> List((1, 42, "tx"))

      Unique.int_(0).i(2).Tx(Other.i(43).s("tx2")).update.transact
      Unique.i.Tx(Other.i.s).query.get ==> List((2, 43, "tx2"))

      intercept[MoleculeException](
        Unique.int_(0).Tx(Other.s("tx3")).update.transact
      ).message ==> "Can't update tx meta data only."

      // We can though update the tx entity itself if it has a unique value (i has here)
      Other.i_(43).i(44).s("tx3").update.transact
      Unique.i.Tx(Other.i.s).query.get ==> List((2, 44, "tx3"))
    }


    "Composite + tx meta data" - unique { implicit conn =>
      (Unique.int.i.s + Ref.i.s).Tx(Other.i_(42).s_("tx")).insert((0, 1, "a"), (2, "b")).transact
      (Unique.i.s + Ref.i.s).Tx(Other.i.s).query.get ==> List(((1, "a"), (2, "b"), 42, "tx"))

      // Composite sub groups share the same entity id
      (Unique.int_(0).i(3).s("c") + Ref.i(4).s("d")).Tx(Other.i(43).s("tx2")).update.transact
      (Unique.i.s + Ref.i.s).Tx(Other.i.s).query.get ==> List(((3, "c"), (4, "d"), 43, "tx2"))
    }


    "Semantics" - unique { implicit conn =>
      intercept[MoleculeException](
        Unique.i(1).i(2).int_(1).update.transact
      ).message ==> "Can't transact duplicate attribute `Unique.i`."

      // One tacit (filter), one mandatory (new value) is ok
      Unique.i_(1).i(2).update.transact

      // Not adding `multiple` prevents unintentional update of multiple (possible all!) entities
      intercept[MoleculeException](
        Unique.i(1).int_(1, 2).update.transact
      ).message ==> "Please provide explicit `update.multiple` to update multiple entities."

      intercept[MoleculeException](
        Unique.int_(1).string_("x").s("c").update.transact
      ).message ==> "Can only apply one unique attribute value for update. Found:\n" +
        "AttrOneTacString(Unique,string,Appl,List(x),None,None,None)"

      intercept[MoleculeException](
        Unique.ints_(1).s("b").update.transact
      ).message ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        "AttrSetTacInt(Unique,ints,Appl,List(Set(1)),None,None,None)"
    }
  }
}
