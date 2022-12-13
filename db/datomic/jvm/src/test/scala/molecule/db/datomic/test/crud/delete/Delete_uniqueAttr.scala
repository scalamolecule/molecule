package molecule.db.datomic.test.crud.delete

import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Delete_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Delete by unique attribute value, check types" - unique { implicit conn =>
      // Initial values
      Unique.i.string.insert((1, string1), (2, string2)).transact
      Unique.i.int.insert((1, int1), (2, int2)).transact
      Unique.i.long.insert((1, long1), (2, long2)).transact
      Unique.i.float.insert((1, float1), (2, float2)).transact
      Unique.i.double.insert((1, double1), (2, double2)).transact
      Unique.i.boolean.insert((1, boolean1), (2, boolean2)).transact
      Unique.i.bigInt.insert((1, bigInt1), (2, bigInt2)).transact
      Unique.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2)).transact
      Unique.i.date.insert((1, date1), (2, date2)).transact
      Unique.i.uuid.insert((1, uuid1), (2, uuid2)).transact
      Unique.i.uri.insert((1, uri1), (2, uri2)).transact
      Unique.i.byte.insert((1, byte1), (2, byte2)).transact
      Unique.i.short.insert((1, short1), (2, short2)).transact
      Unique.i.char.insert((1, char1), (2, char2)).transact

      // Delete with looked up unique value
      Unique.string_(string1).delete.transact
      Unique.int_(int1).delete.transact
      Unique.long_(long1).delete.transact
      Unique.float_(float1).delete.transact
      Unique.double_(double1).delete.transact
      Unique.boolean_(boolean1).delete.transact
      Unique.bigInt_(bigInt1).delete.transact
      Unique.bigDecimal_(bigDecimal1).delete.transact
      Unique.date_(date1).delete.transact
      Unique.uuid_(uuid1).delete.transact
      Unique.uri_(uri1).delete.transact
      Unique.byte_(byte1).delete.transact
      Unique.short_(short1).delete.transact
      Unique.char_(char1).delete.transact

      // i == 1 has been deleted
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


    "Multiple entities deleted" - unique { implicit conn =>
      Unique.int.insert(1, 2, 3).transact

      // Explicitly add `multiple` to update multiple entities
      Unique.int_(1, 2).delete.multiple.transact

      Unique.int.query.get ==> List(3)
    }


    "Ref" - unique { implicit conn =>
      Unique.int.insert(1).transact
      Unique.int.Ref.int.insert((2, 20), (3, 30)).transact

      Unique.int.query.get ==> List(1, 2, 3)
      Unique.int.Ref.int.query.get ==> List((2, 20), (3, 30))

      // Nothing deleted since entity 1 doesn't have a ref
      Unique.int_(1).Ref.int_.delete.transact
      Unique.int.query.get ==> List(1, 2, 3)

      // Second entity has a ref and will be deleted
      Unique.int_(2).Ref.int_.delete.transact
      Unique.int.query.get ==> List(1, 3)
      Unique.int.Ref.int.query.get ==> List((3, 30))

      // Note that Ref.int entity is a separate entity and is not deleted
      // Only the entity of the initial namespace is deleted
      Ref.int.query.get ==> List(20, 30)

      // Unique.int entity has no ref to Ref.int_(42) so nothing is deleted
      Unique.int_.Ref.int_(42).delete.transact
      Unique.int.query.get ==> List(1, 3)
      Unique.int.Ref.int.query.get ==> List((3, 30))

      // Unique.int entity has a ref to Ref.int_(30) so it will be deleted
      Unique.int_.Ref.int_(30).delete.transact
      Unique.int.query.get ==> List(1)
      Unique.int.Ref.int.query.get ==> List()
    }


    "Composite" - unique { implicit conn =>
      Unique.int.insert(1).transact
      (Unique.int + Ref.int).insert((2, 20), (3, 30)).transact

      Unique.int.query.get ==> List(1, 2, 3)
      (Unique.int + Ref.int).query.get ==> List((2, 20), (3, 30))

      // Nothing deleted since entity 1 doesn't have a ref
      (Unique.int_(1) + Ref.int_).delete.transact
      Unique.int.query.get ==> List(1, 2, 3)

      // Second entity has a ref and will be deleted
      (Unique.int_(2) + Ref.int_).delete.transact
      Unique.int.query.get ==> List(1, 3)
      (Unique.int + Ref.int).query.get ==> List((3, 30))

      // Note that Ref.int belongs to the same entity as Unique.int, and is therefore deleted together
      Ref.int.query.get ==> List(30)

      // Unique.int entity has no ref to Ref.int_(42) so nothing is deleted
      (Unique.int_ + Ref.int_(42)).delete.transact
      Unique.int.query.get ==> List(1, 3)
      (Unique.int + Ref.int).query.get ==> List((3, 30))

      // Unique.int entity has a ref to Ref.int_(30) so it will be deleted
      (Unique.int_ + Ref.int_(30)).delete.transact
      Unique.int.query.get ==> List(1)
      (Unique.int + Ref.int).query.get ==> List()
    }
  }
}
