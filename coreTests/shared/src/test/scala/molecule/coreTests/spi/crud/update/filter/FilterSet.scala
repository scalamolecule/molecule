package molecule.coreTests.spi.crud.update.filter

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    // OBS: Updates by filters will update _all_ matching entities!

    // Use tacit attributes as filters to match entities to be updated.
    // Apply new values to mandatory attributes.

    "Set asserted" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.iSet_?.int.insert(
          (None, 0),
          (Some(Set(1, 2)), 1),
          (Some(Set(2, 3)), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSet` is asserted
        _ <- Ns
          .iSet_ // filter by tacit attribute
          .int(3) // apply new value to mandatory attribute
          .update.transact

        // 2 entities updated
        _ <- Ns.id.a1.iSet_?.int.query.get.map(_ ==> List(
          (a, None, 0),
          (b, Some(Set(1, 2)), 3), // updated
          (c, Some(Set(2, 3)), 3), // updated
        ))

        // Order of attributes in update molecule makes no difference
        _ <- Ns.int(4).iSet_.update.transact
        _ <- Ns.id.a1.iSet_?.int.query.get.map(_ ==> List(
          (a, None, 0),
          (b, Some(Set(1, 2)), 4), // updated
          (c, Some(Set(2, 3)), 4), // updated
        ))
      } yield ()
    }


    "Set not asserted" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.iSet_?.int.insert(
          (None, 0),
          (Some(Set(1, 2)), 1),
          (Some(Set(2, 3)), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSet` is not asserted (null)
        _ <- Ns.iSet_().int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSet_?.int.query.get.map(_ ==> List(
          (a, None, 3), // updated
          (b, Some(Set(1, 2)), 1),
          (c, Some(Set(2, 3)), 2),
        ))
      } yield ()
    }


    "Set equality" - types { implicit conn =>
      // Filtering updates by equality of collections is not supported by molecule.
      // Instead, use has/hasNo.
      for {
        _ <- Ns.iSet_(Set(1)).int(3).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Filtering by collection equality (Ns.iSet) not supported in updates."
          }
      } yield ()
    }


    "Has value" - types { implicit conn =>
      for {
        List(a, b) <- Ns.iSet.int.insert(
          (Set(0, 1, 2), 1),
          (Set(2, 3, 4), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSet` has a value 1
        _ <- Ns.iSet_.has(1).int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(0, 1, 2), 3), // updated
          (b, Set(2, 3, 4), 2),
        ))

        // Update all entities where iSet has any values of Iterable
        _ <- Ns.iSet_.has(Set(3, 7)).int(4).update.transact
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(0, 1, 2), 3),
          (b, Set(2, 3, 4), 4), // updated
        ))

        // Nothing updated since no iSet has any values of Iterable
        _ <- Ns.iSet_.has(List(5, 6)).int(5).update.transact
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(0, 1, 2), 3),
          (b, Set(2, 3, 4), 4),
        ))
      } yield ()
    }


    "Doesn't have value" - types { implicit conn =>
      for {
        List(a, b) <- Ns.iSet.int.insert(
          (Set(1, 2), 1),
          (Set(2, 3), 2),
        ).transact.map(_.ids)

        // Update all entities where `iSet` has no value 1
        _ <- Ns.iSet_.hasNo(1).int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(1, 2), 1),
          (b, Set(2, 3), 3), // updated
        ))

        // Update all entities where iSet has neither 3 or 4
        _ <- Ns.iSet_.hasNo(3, 4).int(4).update.transact
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(1, 2), 4), // updated
          (b, Set(2, 3), 3),
        ))

        // Nothing updated since all iSet has neither 1, 2 or 3
        _ <- Ns.iSet_.hasNo(1, 2, 3).int(5).update.transact
        _ <- Ns.id.a1.iSet.int.query.get.map(_ ==> List(
          (a, Set(1, 2), 4),
          (b, Set(2, 3), 3),
        ))
      } yield ()
    }


    "Types, filter asserted" - types { implicit conn =>
      for {
        ref1 <- Ref.i(1).save.transact.map(_.id)

        // Initial values
        _ <- Ns.i(1).stringSet(Set(string1)).save.transact
        _ <- Ns.i(1).intSet(Set(int1)).save.transact
        _ <- Ns.i(1).longSet(Set(long1)).save.transact
        _ <- Ns.i(1).floatSet(Set(float1)).save.transact
        _ <- Ns.i(1).doubleSet(Set(double1)).save.transact
        _ <- Ns.i(1).booleanSet(Set(boolean1)).save.transact
        _ <- Ns.i(1).bigIntSet(Set(bigInt1)).save.transact
        _ <- Ns.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
        _ <- Ns.i(1).dateSet(Set(date1)).save.transact
        _ <- Ns.i(1).durationSet(Set(duration1)).save.transact
        _ <- Ns.i(1).instantSet(Set(instant1)).save.transact
        _ <- Ns.i(1).localDateSet(Set(localDate1)).save.transact
        _ <- Ns.i(1).localTimeSet(Set(localTime1)).save.transact
        _ <- Ns.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
        _ <- Ns.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
        _ <- Ns.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
        _ <- Ns.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
        _ <- Ns.i(1).uuidSet(Set(uuid1)).save.transact
        _ <- Ns.i(1).uriSet(Set(uri1)).save.transact
        _ <- Ns.i(1).byteSet(Set(byte1)).save.transact
        _ <- Ns.i(1).shortSet(Set(short1)).save.transact
        _ <- Ns.i(1).charSet(Set(char1)).save.transact
        _ <- Ns.i(1).refs(Set(ref1)).save.transact

        // Update i using asserted filter
        _ <- Ns.i(2).stringSet_.update.transact
        _ <- Ns.i(2).intSet_.update.transact
        _ <- Ns.i(2).longSet_.update.transact
        _ <- Ns.i(2).floatSet_.update.transact
        _ <- Ns.i(2).doubleSet_.update.transact
        _ <- Ns.i(2).booleanSet_.update.transact
        _ <- Ns.i(2).bigIntSet_.update.transact
        _ <- Ns.i(2).bigDecimalSet_.update.transact
        _ <- Ns.i(2).dateSet_.update.transact
        _ <- Ns.i(2).durationSet_.update.transact
        _ <- Ns.i(2).instantSet_.update.transact
        _ <- Ns.i(2).localDateSet_.update.transact
        _ <- Ns.i(2).localTimeSet_.update.transact
        _ <- Ns.i(2).localDateTimeSet_.update.transact
        _ <- Ns.i(2).offsetTimeSet_.update.transact
        _ <- Ns.i(2).offsetDateTimeSet_.update.transact
        _ <- Ns.i(2).zonedDateTimeSet_.update.transact
        _ <- Ns.i(2).uuidSet_.update.transact
        _ <- Ns.i(2).uriSet_.update.transact
        _ <- Ns.i(2).byteSet_.update.transact
        _ <- Ns.i(2).shortSet_.update.transact
        _ <- Ns.i(2).charSet_.update.transact
        _ <- Ns.i(2).refs_.update.transact

        // i has been updated
        _ <- Ns.i.stringSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.intSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.floatSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.doubleSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.booleanSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigIntSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigDecimalSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.dateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.durationSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.instantSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uuidSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uriSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.byteSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.shortSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.charSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.refs_.query.get.map(_.head ==> 2)
      } yield ()
    }


    "Types, filter has" - types { implicit conn =>
      for {
        ref1 <- Ref.i(1).save.transact.map(_.id)

        // Initial values
        _ <- Ns.i(1).stringSet(Set(string1)).save.transact
        _ <- Ns.i(1).intSet(Set(int1)).save.transact
        _ <- Ns.i(1).longSet(Set(long1)).save.transact
        _ <- Ns.i(1).floatSet(Set(float1)).save.transact
        _ <- Ns.i(1).doubleSet(Set(double1)).save.transact
        _ <- Ns.i(1).booleanSet(Set(boolean1)).save.transact
        _ <- Ns.i(1).bigIntSet(Set(bigInt1)).save.transact
        _ <- Ns.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
        _ <- Ns.i(1).dateSet(Set(date1)).save.transact
        _ <- Ns.i(1).durationSet(Set(duration1)).save.transact
        _ <- Ns.i(1).instantSet(Set(instant1)).save.transact
        _ <- Ns.i(1).localDateSet(Set(localDate1)).save.transact
        _ <- Ns.i(1).localTimeSet(Set(localTime1)).save.transact
        _ <- Ns.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
        _ <- Ns.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
        _ <- Ns.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
        _ <- Ns.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
        _ <- Ns.i(1).uuidSet(Set(uuid1)).save.transact
        _ <- Ns.i(1).uriSet(Set(uri1)).save.transact
        _ <- Ns.i(1).byteSet(Set(byte1)).save.transact
        _ <- Ns.i(1).shortSet(Set(short1)).save.transact
        _ <- Ns.i(1).charSet(Set(char1)).save.transact
        _ <- Ns.i(1).refs(Set(ref1)).save.transact

        // Update i using has filter
        _ <- Ns.i(2).stringSet_.has(string1).update.transact
        _ <- Ns.i(2).intSet_.has(int1).update.transact
        _ <- Ns.i(2).longSet_.has(long1).update.transact
        _ <- Ns.i(2).floatSet_.has(float1).update.transact
        _ <- Ns.i(2).doubleSet_.has(double1).update.transact
        _ <- Ns.i(2).booleanSet_.has(boolean1).update.transact
        _ <- Ns.i(2).bigIntSet_.has(bigInt1).update.transact
        _ <- Ns.i(2).bigDecimalSet_.has(bigDecimal1).update.transact
        _ <- Ns.i(2).dateSet_.has(date1).update.transact
        _ <- Ns.i(2).durationSet_.has(duration1).update.transact
        _ <- Ns.i(2).instantSet_.has(instant1).update.transact
        _ <- Ns.i(2).localDateSet_.has(localDate1).update.transact
        _ <- Ns.i(2).localTimeSet_.has(localTime1).update.transact
        _ <- Ns.i(2).localDateTimeSet_.has(localDateTime1).update.transact
        _ <- Ns.i(2).offsetTimeSet_.has(offsetTime1).update.transact
        _ <- Ns.i(2).offsetDateTimeSet_.has(offsetDateTime1).update.transact
        _ <- Ns.i(2).zonedDateTimeSet_.has(zonedDateTime1).update.transact
        _ <- Ns.i(2).uuidSet_.has(uuid1).update.transact
        _ <- Ns.i(2).uriSet_.has(uri1).update.transact
        _ <- Ns.i(2).byteSet_.has(byte1).update.transact
        _ <- Ns.i(2).shortSet_.has(short1).update.transact
        _ <- Ns.i(2).charSet_.has(char1).update.transact
        _ <- Ns.i(2).refs_.has(ref1).update.transact

        // i has been updated
        _ <- Ns.i.stringSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.intSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.floatSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.doubleSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.booleanSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigIntSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigDecimalSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.dateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.durationSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.instantSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uuidSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uriSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.byteSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.shortSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.charSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.refs_.query.get.map(_.head ==> 2)
      } yield ()
    }


    "Types, filter hasNo" - types { implicit conn =>
      for {
        List(ref1, ref2) <- Ref.i.insert(1, 2).transact.map(_.ids)

        // Initial values
        _ <- Ns.i(1).stringSet(Set(string1)).save.transact
        _ <- Ns.i(1).intSet(Set(int1)).save.transact
        _ <- Ns.i(1).longSet(Set(long1)).save.transact
        _ <- Ns.i(1).floatSet(Set(float1)).save.transact
        _ <- Ns.i(1).doubleSet(Set(double1)).save.transact
        _ <- Ns.i(1).booleanSet(Set(boolean1)).save.transact
        _ <- Ns.i(1).bigIntSet(Set(bigInt1)).save.transact
        _ <- Ns.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
        _ <- Ns.i(1).dateSet(Set(date1)).save.transact
        _ <- Ns.i(1).durationSet(Set(duration1)).save.transact
        _ <- Ns.i(1).instantSet(Set(instant1)).save.transact
        _ <- Ns.i(1).localDateSet(Set(localDate1)).save.transact
        _ <- Ns.i(1).localTimeSet(Set(localTime1)).save.transact
        _ <- Ns.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
        _ <- Ns.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
        _ <- Ns.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
        _ <- Ns.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
        _ <- Ns.i(1).uuidSet(Set(uuid1)).save.transact
        _ <- Ns.i(1).uriSet(Set(uri1)).save.transact
        _ <- Ns.i(1).byteSet(Set(byte1)).save.transact
        _ <- Ns.i(1).shortSet(Set(short1)).save.transact
        _ <- Ns.i(1).charSet(Set(char1)).save.transact
        _ <- Ns.i(1).refs(Set(ref1)).save.transact

        // Update i using hasNo filter
        _ <- Ns.i(2).stringSet_.hasNo(string2).update.transact
        _ <- Ns.i(2).intSet_.hasNo(int2).update.transact
        _ <- Ns.i(2).longSet_.hasNo(long2).update.transact
        _ <- Ns.i(2).floatSet_.hasNo(float2).update.transact
        _ <- Ns.i(2).doubleSet_.hasNo(double2).update.transact
        _ <- Ns.i(2).booleanSet_.hasNo(boolean2).update.transact
        _ <- Ns.i(2).bigIntSet_.hasNo(bigInt2).update.transact
        _ <- Ns.i(2).bigDecimalSet_.hasNo(bigDecimal2).update.transact
        _ <- Ns.i(2).dateSet_.hasNo(date2).update.transact
        _ <- Ns.i(2).durationSet_.hasNo(duration2).update.transact
        _ <- Ns.i(2).instantSet_.hasNo(instant2).update.transact
        _ <- Ns.i(2).localDateSet_.hasNo(localDate2).update.transact
        _ <- Ns.i(2).localTimeSet_.hasNo(localTime2).update.transact
        _ <- Ns.i(2).localDateTimeSet_.hasNo(localDateTime2).update.transact
        _ <- Ns.i(2).offsetTimeSet_.hasNo(offsetTime2).update.transact
        _ <- Ns.i(2).offsetDateTimeSet_.hasNo(offsetDateTime2).update.transact
        _ <- Ns.i(2).zonedDateTimeSet_.hasNo(zonedDateTime2).update.transact
        _ <- Ns.i(2).uuidSet_.hasNo(uuid2).update.transact
        _ <- Ns.i(2).uriSet_.hasNo(uri2).update.transact
        _ <- Ns.i(2).byteSet_.hasNo(byte2).update.transact
        _ <- Ns.i(2).shortSet_.hasNo(short2).update.transact
        _ <- Ns.i(2).charSet_.hasNo(char2).update.transact
        _ <- Ns.i(2).refs_.hasNo(ref2).update.transact

        // i has been updated
        _ <- Ns.i.stringSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.intSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.floatSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.doubleSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.booleanSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigIntSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.bigDecimalSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.dateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.durationSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.instantSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.localDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uuidSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.uriSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.byteSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.shortSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.charSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.refs_.query.get.map(_.head ==> 2)
      } yield ()
    }
  }
}
