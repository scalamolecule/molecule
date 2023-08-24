package molecule.coreTests.test.crud.delete

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Delete_uniqueAttr extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Delete by unique attribute value - check types" - unique { implicit conn =>
      for {
        // Initial values
        _ <- Uniques.i.string.insert((1, string1), (2, string2)).transact
        _ <- Uniques.i.int.insert((1, int1), (2, int2)).transact
        _ <- Uniques.i.long.insert((1, long1), (2, long2)).transact
        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
        _ <- Uniques.i.boolean.insert((1, boolean1), (2, boolean2)).transact
        _ <- Uniques.i.bigInt.insert((1, bigInt1), (2, bigInt2)).transact
        _ <- Uniques.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2)).transact
        _ <- Uniques.i.date.insert((1, date1), (2, date2)).transact
        _ <- Uniques.i.uuid.insert((1, uuid1), (2, uuid2)).transact
        _ <- Uniques.i.uri.insert((1, uri1), (2, uri2)).transact
        _ <- Uniques.i.byte.insert((1, byte1), (2, byte2)).transact
        _ <- Uniques.i.short.insert((1, short1), (2, short2)).transact
        _ <- Uniques.i.char.insert((1, char1), (2, char2)).transact

        // Delete with looked up unique value
        _ <- Uniques.string_(string1).delete.transact
        _ <- Uniques.int_(int1).delete.transact
        _ <- Uniques.long_(long1).delete.transact
        _ <- Uniques.float_(float1).delete.transact
        _ <- Uniques.double_(double1).delete.transact
        _ <- Uniques.boolean_(boolean1).delete.transact
        _ <- Uniques.bigInt_(bigInt1).delete.transact
        _ <- Uniques.bigDecimal_(bigDecimal1).delete.transact
        _ <- Uniques.date_(date1).delete.transact
        _ <- Uniques.uuid_(uuid1).delete.transact
        _ <- Uniques.uri_(uri1).delete.transact
        _ <- Uniques.byte_(byte1).delete.transact
        _ <- Uniques.short_(short1).delete.transact
        _ <- Uniques.char_(char1).delete.transact

        // i == 1 has been deleted
        _ <- Uniques.i.string_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.int_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.long_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.float_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.double_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.boolean_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.bigInt_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.bigDecimal_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.date_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.uuid_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.uri_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.byte_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.short_.query.get.map(_ ==> List(2))
        _ <- Uniques.i.char_.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Multiple entities deleted" - unique { implicit conn =>
      for {
        _ <- Uniques.int.insert(1, 2, 3).transact
        _ <- Uniques.int_(1, 2).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Ref" - unique { implicit conn =>
      for {
        _ <- Uniques.int.insert(1).transact
        _ <- Uniques.int.Ref.int.insert((2, 20), (3, 30)).transact

        _ <- Uniques.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- Uniques.int.Ref.int.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- Uniques.int_(1).Ref.int_.delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- Uniques.int_(2).Ref.int_.delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 3))
        _ <- Uniques.int.Ref.int.query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- Ref.int.query.get.map(_ ==> List(20, 30))

        // Uniques.int entity has no ref to Ref.int_(42) so nothing is deleted
        _ <- Uniques.int_.Ref.int_(42).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 3))
        _ <- Uniques.int.Ref.int.query.get.map(_ ==> List((3, 30)))

        // Uniques.int entity has a ref to Ref.int_(30) so it will be deleted
        _ <- Uniques.int_.Ref.int_(30).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1))
        _ <- Uniques.int.Ref.int.query.get.map(_ ==> List())
      } yield ()
    }


    "Composite" - unique { implicit conn =>
      for {
        _ <- Uniques.int.insert(1).transact
        _ <- (Uniques.int + Ref.int).insert((2, 20), (3, 30)).transact

        _ <- Uniques.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- (Uniques.int + Ref.int).query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- (Uniques.int_(1) + Ref.int_).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- (Uniques.int_(2) + Ref.int_).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 3))
        _ <- (Uniques.int + Ref.int).query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int belongs to the same entity as Uniques.int, and is therefore deleted together
        _ <- Ref.int.query.get.map(_ ==> List(30))

        // Uniques.int entity has no ref to Ref.int_(42) so nothing is deleted
        _ <- (Uniques.int_ + Ref.int_(42)).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1, 3))
        _ <- (Uniques.int + Ref.int).query.get.map(_ ==> List((3, 30)))

        // Uniques.int entity has a ref to Ref.int_(30) so it will be deleted
        _ <- (Uniques.int_ + Ref.int_(30)).delete.transact
        _ <- Uniques.int.query.get.map(_ ==> List(1))
        _ <- (Uniques.int + Ref.int).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
