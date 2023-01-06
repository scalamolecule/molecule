package molecule.db.datomic.test.crud.delete

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Delete_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Delete by unique attribute value - check types" - unique { implicit conn =>
      for {
        // Initial values
        _ <- Unique.i.string.insert((1, string1), (2, string2)).transact
        _ <- Unique.i.int.insert((1, int1), (2, int2)).transact
        _ <- Unique.i.long.insert((1, long1), (2, long2)).transact
        _ <- Unique.i.float.insert((1, float1), (2, float2)).transact
        _ <- Unique.i.double.insert((1, double1), (2, double2)).transact
        _ <- Unique.i.boolean.insert((1, boolean1), (2, boolean2)).transact
        _ <- Unique.i.bigInt.insert((1, bigInt1), (2, bigInt2)).transact
        _ <- Unique.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2)).transact
        _ <- Unique.i.date.insert((1, date1), (2, date2)).transact
        _ <- Unique.i.uuid.insert((1, uuid1), (2, uuid2)).transact
        _ <- Unique.i.uri.insert((1, uri1), (2, uri2)).transact
        _ <- Unique.i.byte.insert((1, byte1), (2, byte2)).transact
        _ <- Unique.i.short.insert((1, short1), (2, short2)).transact
        _ <- Unique.i.char.insert((1, char1), (2, char2)).transact

        // Delete with looked up unique value
        _ <- Unique.string_(string1).delete.transact
        _ <- Unique.int_(int1).delete.transact
        _ <- Unique.long_(long1).delete.transact
        _ <- Unique.float_(float1).delete.transact
        _ <- Unique.double_(double1).delete.transact
        _ <- Unique.boolean_(boolean1).delete.transact
        _ <- Unique.bigInt_(bigInt1).delete.transact
        _ <- Unique.bigDecimal_(bigDecimal1).delete.transact
        _ <- Unique.date_(date1).delete.transact
        _ <- Unique.uuid_(uuid1).delete.transact
        _ <- Unique.uri_(uri1).delete.transact
        _ <- Unique.byte_(byte1).delete.transact
        _ <- Unique.short_(short1).delete.transact
        _ <- Unique.char_(char1).delete.transact

        // i == 1 has been deleted
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


    "Multiple entities deleted" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1, 2, 3).transact

        // Explicitly add `multiple` to update multiple entities
        _ <- Unique.int_(1, 2).delete.multiple.transact

        _ <- Unique.int.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Ref" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1).transact
        _ <- Unique.int.Ref.int.insert((2, 20), (3, 30)).transact

        _ <- Unique.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- Unique.int.Ref.int.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- Unique.int_(1).Ref.int_.delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- Unique.int_(2).Ref.int_.delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 3))
        _ <- Unique.int.Ref.int.query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- Ref.int.query.get.map(_ ==> List(20, 30))

        // Unique.int entity has no ref to Ref.int_(42) so nothing is deleted
        _ <- Unique.int_.Ref.int_(42).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 3))
        _ <- Unique.int.Ref.int.query.get.map(_ ==> List((3, 30)))

        // Unique.int entity has a ref to Ref.int_(30) so it will be deleted
        _ <- Unique.int_.Ref.int_(30).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1))
        _ <- Unique.int.Ref.int.query.get.map(_ ==> List())
      } yield ()
    }


    "Composite" - unique { implicit conn =>
      for {
        _ <- Unique.int.insert(1).transact
        _ <- (Unique.int + Ref.int).insert((2, 20), (3, 30)).transact

        _ <- Unique.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- (Unique.int + Ref.int).query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- (Unique.int_(1) + Ref.int_).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- (Unique.int_(2) + Ref.int_).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 3))
        _ <- (Unique.int + Ref.int).query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int belongs to the same entity as Unique.int, and is therefore deleted together
        _ <- Ref.int.query.get.map(_ ==> List(30))

        // Unique.int entity has no ref to Ref.int_(42) so nothing is deleted
        _ <- (Unique.int_ + Ref.int_(42)).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1, 3))
        _ <- (Unique.int + Ref.int).query.get.map(_ ==> List((3, 30)))

        // Unique.int entity has a ref to Ref.int_(30) so it will be deleted
        _ <- (Unique.int_ + Ref.int_(30)).delete.transact
        _ <- Unique.int.query.get.map(_ ==> List(1))
        _ <- (Unique.int + Ref.int).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
