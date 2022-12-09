package molecule.db.datomic.test.crud.updateOne

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "With unique attribute value" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Unique._

      Unique.int(1).s("a").save.transact

      // Find entity by applying value to unique attribute
      Unique.int_(1).s("b").update.transact
      Unique.s.query.get ==> List("b")

      intercept[MoleculeException](
        Unique.int_(1, 2).s("c").update.transact
      ).message ==> "Can only update with one applied value for tacit attribute `Unique.int_`. Found:\n" +
        "AttrOneTacInt(Unique,int,Appl,ArraySeq(1, 2),None,None,None)"

      intercept[MoleculeException](
        Unique.int_(1).string_("x").s("c").update.transact
      ).message ==> "Can only apply one unique attribute value for update. Found:\n" +
        "AttrOneTacString(Unique,string,Appl,List(x),None,None,None)"
    }


    "Unique attribute value types" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Unique._
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

      // Lookup by unique value
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


    "Unique attribute value cardinality-one only" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Unique._

      Unique.ints(Set(1, 2)).s("a").save.transact

      intercept[MoleculeException](
        Unique.ints_(1).s("b").update.transact
      ).message ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        "AttrSetTacInt(Unique,ints,Appl,List(Set(1)),None,None,None)"
    }
  }
}
