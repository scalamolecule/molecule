package molecule.db.compliance.test.action.update.filter

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSet(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  // OBS: Updates by filters will update _all_ matching entities!

  // Use tacit attributes as filters to match entities to be updated.
  // Apply new values to mandatory attributes.

  "Set asserted" - types {
    for {
      case List(a, b, c) <- Entity.iSet_?.int.insert(
        (None, 0),
        (Some(Set(1, 2)), 1),
        (Some(Set(2, 3)), 2),
      ).transact.map(_.ids)

      // Update all entities where `iSet` is asserted
      _ <- Entity
        .iSet_ // filter by tacit attribute
        .int(3) // apply new value to mandatory attribute
        .update.transact

      // 2 entities updated
      _ <- Entity.id.a1.iSet_?.int.query.get.map(_ ==> List(
        (a, None, 0),
        (b, Some(Set(1, 2)), 3), // updated
        (c, Some(Set(2, 3)), 3), // updated
      ))

      // Order of attributes in update molecule makes no difference
      _ <- Entity.int(4).iSet_.update.transact
      _ <- Entity.id.a1.iSet_?.int.query.get.map(_ ==> List(
        (a, None, 0),
        (b, Some(Set(1, 2)), 4), // updated
        (c, Some(Set(2, 3)), 4), // updated
      ))
    } yield ()
  }


  "Set not asserted" - types {
    for {
      case List(a, b, c) <- Entity.iSet_?.int.insert(
        (None, 0),
        (Some(Set(1, 2)), 1),
        (Some(Set(2, 3)), 2),
      ).transact.map(_.ids)

      // Update all entities where `iSet` is not asserted (null)
      _ <- Entity.iSet_().int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iSet_?.int.query.get.map(_ ==> List(
        (a, None, 3), // updated
        (b, Some(Set(1, 2)), 1),
        (c, Some(Set(2, 3)), 2),
      ))
    } yield ()
  }


  "Set equality" - types {
    // Filtering updates by equality of collections is not supported by molecule.
    // Instead, use has/hasNo.
    for {
      _ <- Entity.iSet_(Set(1)).int(3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filtering by collection equality (Entity.iSet) not supported in updates."
        }
    } yield ()
  }


  "Has value" - types {
    for {
      List(a, b) <- Entity.iSet.int.insert(
        (Set(0, 1, 2), 1),
        (Set(2, 3, 4), 2),
      ).transact.map(_.ids)

      // Update all entities where `iSet` has a value 1
      _ <- Entity.iSet_.has(1).int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(0, 1, 2), 3), // updated
        (b, Set(2, 3, 4), 2),
      ))

      // Update all entities where iSet has any values of Iterable
      _ <- Entity.iSet_.has(Set(3, 7)).int(4).update.transact
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(0, 1, 2), 3),
        (b, Set(2, 3, 4), 4), // updated
      ))

      // Nothing updated since no iSet has any values of Iterable
      _ <- Entity.iSet_.has(List(5, 6)).int(5).update.transact
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(0, 1, 2), 3),
        (b, Set(2, 3, 4), 4),
      ))
    } yield ()
  }


  "Doesn't have value" - types {
    for {
      List(a, b) <- Entity.iSet.int.insert(
        (Set(1, 2), 1),
        (Set(2, 3), 2),
      ).transact.map(_.ids)

      // Update all entities where `iSet` has no value 1
      _ <- Entity.iSet_.hasNo(1).int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(1, 2), 1),
        (b, Set(2, 3), 3), // updated
      ))

      // Update all entities where iSet has neither 3 or 4
      _ <- Entity.iSet_.hasNo(3, 4).int(4).update.transact
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(1, 2), 4), // updated
        (b, Set(2, 3), 3),
      ))

      // Nothing updated since all iSet has neither 1, 2 or 3
      _ <- Entity.iSet_.hasNo(1, 2, 3).int(5).update.transact
      _ <- Entity.id.a1.iSet.int.query.get.map(_ ==> List(
        (a, Set(1, 2), 4),
        (b, Set(2, 3), 3),
      ))
    } yield ()
  }


  "Types, filter asserted" - types {
    for {
      ref1 <- Ref.i(1).save.transact.map(_.id)

      // Initial values
      _ <- Entity.i(1).stringSet(Set(string1)).save.transact
      _ <- Entity.i(1).intSet(Set(int1)).save.transact
      _ <- Entity.i(1).longSet(Set(long1)).save.transact
      _ <- Entity.i(1).floatSet(Set(float1)).save.transact
      _ <- Entity.i(1).doubleSet(Set(double1)).save.transact
      _ <- Entity.i(1).booleanSet(Set(boolean1)).save.transact
      _ <- Entity.i(1).bigIntSet(Set(bigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
      _ <- Entity.i(1).dateSet(Set(date1)).save.transact
      _ <- Entity.i(1).durationSet(Set(duration1)).save.transact
      _ <- Entity.i(1).instantSet(Set(instant1)).save.transact
      _ <- Entity.i(1).localDateSet(Set(localDate1)).save.transact
      _ <- Entity.i(1).localTimeSet(Set(localTime1)).save.transact
      _ <- Entity.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidSet(Set(uuid1)).save.transact
      _ <- Entity.i(1).uriSet(Set(uri1)).save.transact
      _ <- Entity.i(1).byteSet(Set(byte1)).save.transact
      _ <- Entity.i(1).shortSet(Set(short1)).save.transact
      _ <- Entity.i(1).charSet(Set(char1)).save.transact
      _ <- Entity.i(1).refs(Set(ref1)).save.transact

      // Update i using asserted filter
      _ <- Entity.i(2).stringSet_.update.transact
      _ <- Entity.i(2).intSet_.update.transact
      _ <- Entity.i(2).longSet_.update.transact
      _ <- Entity.i(2).floatSet_.update.transact
      _ <- Entity.i(2).doubleSet_.update.transact
      _ <- Entity.i(2).booleanSet_.update.transact
      _ <- Entity.i(2).bigIntSet_.update.transact
      _ <- Entity.i(2).bigDecimalSet_.update.transact
      _ <- Entity.i(2).dateSet_.update.transact
      _ <- Entity.i(2).durationSet_.update.transact
      _ <- Entity.i(2).instantSet_.update.transact
      _ <- Entity.i(2).localDateSet_.update.transact
      _ <- Entity.i(2).localTimeSet_.update.transact
      _ <- Entity.i(2).localDateTimeSet_.update.transact
      _ <- Entity.i(2).offsetTimeSet_.update.transact
      _ <- Entity.i(2).offsetDateTimeSet_.update.transact
      _ <- Entity.i(2).zonedDateTimeSet_.update.transact
      _ <- Entity.i(2).uuidSet_.update.transact
      _ <- Entity.i(2).uriSet_.update.transact
      _ <- Entity.i(2).byteSet_.update.transact
      _ <- Entity.i(2).shortSet_.update.transact
      _ <- Entity.i(2).charSet_.update.transact
      _ <- Entity.i(2).refs_.update.transact

      // i has been updated
      _ <- Entity.i.stringSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.refs_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter has" - types {
    for {
      ref1 <- Ref.i(1).save.transact.map(_.id)

      // Initial values
      _ <- Entity.i(1).stringSet(Set(string1)).save.transact
      _ <- Entity.i(1).intSet(Set(int1)).save.transact
      _ <- Entity.i(1).longSet(Set(long1)).save.transact
      _ <- Entity.i(1).floatSet(Set(float1)).save.transact
      _ <- Entity.i(1).doubleSet(Set(double1)).save.transact
      _ <- Entity.i(1).booleanSet(Set(boolean1)).save.transact
      _ <- Entity.i(1).bigIntSet(Set(bigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
      _ <- Entity.i(1).dateSet(Set(date1)).save.transact
      _ <- Entity.i(1).durationSet(Set(duration1)).save.transact
      _ <- Entity.i(1).instantSet(Set(instant1)).save.transact
      _ <- Entity.i(1).localDateSet(Set(localDate1)).save.transact
      _ <- Entity.i(1).localTimeSet(Set(localTime1)).save.transact
      _ <- Entity.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidSet(Set(uuid1)).save.transact
      _ <- Entity.i(1).uriSet(Set(uri1)).save.transact
      _ <- Entity.i(1).byteSet(Set(byte1)).save.transact
      _ <- Entity.i(1).shortSet(Set(short1)).save.transact
      _ <- Entity.i(1).charSet(Set(char1)).save.transact
      _ <- Entity.i(1).refs(Set(ref1)).save.transact

      // Update i using has filter
      _ <- Entity.i(2).stringSet_.has(string1).update.transact
      _ <- Entity.i(2).intSet_.has(int1).update.transact
      _ <- Entity.i(2).longSet_.has(long1).update.transact
      _ <- Entity.i(2).floatSet_.has(float1).update.transact
      _ <- Entity.i(2).doubleSet_.has(double1).update.transact
      _ <- Entity.i(2).booleanSet_.has(boolean1).update.transact
      _ <- Entity.i(2).bigIntSet_.has(bigInt1).update.transact
      _ <- Entity.i(2).bigDecimalSet_.has(bigDecimal1).update.transact
      _ <- Entity.i(2).dateSet_.has(date1).update.transact
      _ <- Entity.i(2).durationSet_.has(duration1).update.transact
      _ <- Entity.i(2).instantSet_.has(instant1).update.transact
      _ <- Entity.i(2).localDateSet_.has(localDate1).update.transact
      _ <- Entity.i(2).localTimeSet_.has(localTime1).update.transact
      _ <- Entity.i(2).localDateTimeSet_.has(localDateTime1).update.transact
      _ <- Entity.i(2).offsetTimeSet_.has(offsetTime1).update.transact
      _ <- Entity.i(2).offsetDateTimeSet_.has(offsetDateTime1).update.transact
      _ <- Entity.i(2).zonedDateTimeSet_.has(zonedDateTime1).update.transact
      _ <- Entity.i(2).uuidSet_.has(uuid1).update.transact
      _ <- Entity.i(2).uriSet_.has(uri1).update.transact
      _ <- Entity.i(2).byteSet_.has(byte1).update.transact
      _ <- Entity.i(2).shortSet_.has(short1).update.transact
      _ <- Entity.i(2).charSet_.has(char1).update.transact
      _ <- Entity.i(2).refs_.has(ref1).update.transact

      // i has been updated
      _ <- Entity.i.stringSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.refs_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter hasNo" - types {
    for {
      List(ref1, ref2) <- Ref.i.insert(1, 2).transact.map(_.ids)

      // Initial values
      _ <- Entity.i(1).stringSet(Set(string1)).save.transact
      _ <- Entity.i(1).intSet(Set(int1)).save.transact
      _ <- Entity.i(1).longSet(Set(long1)).save.transact
      _ <- Entity.i(1).floatSet(Set(float1)).save.transact
      _ <- Entity.i(1).doubleSet(Set(double1)).save.transact
      _ <- Entity.i(1).booleanSet(Set(boolean1)).save.transact
      _ <- Entity.i(1).bigIntSet(Set(bigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalSet(Set(bigDecimal1)).save.transact
      _ <- Entity.i(1).dateSet(Set(date1)).save.transact
      _ <- Entity.i(1).durationSet(Set(duration1)).save.transact
      _ <- Entity.i(1).instantSet(Set(instant1)).save.transact
      _ <- Entity.i(1).localDateSet(Set(localDate1)).save.transact
      _ <- Entity.i(1).localTimeSet(Set(localTime1)).save.transact
      _ <- Entity.i(1).localDateTimeSet(Set(localDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeSet(Set(offsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeSet(Set(offsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeSet(Set(zonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidSet(Set(uuid1)).save.transact
      _ <- Entity.i(1).uriSet(Set(uri1)).save.transact
      _ <- Entity.i(1).byteSet(Set(byte1)).save.transact
      _ <- Entity.i(1).shortSet(Set(short1)).save.transact
      _ <- Entity.i(1).charSet(Set(char1)).save.transact
      _ <- Entity.i(1).refs(Set(ref1)).save.transact

      // Update i using hasNo filter
      _ <- Entity.i(2).stringSet_.hasNo(string2).update.transact
      _ <- Entity.i(2).intSet_.hasNo(int2).update.transact
      _ <- Entity.i(2).longSet_.hasNo(long2).update.transact
      _ <- Entity.i(2).floatSet_.hasNo(float2).update.transact
      _ <- Entity.i(2).doubleSet_.hasNo(double2).update.transact
      _ <- Entity.i(2).booleanSet_.hasNo(boolean2).update.transact
      _ <- Entity.i(2).bigIntSet_.hasNo(bigInt2).update.transact
      _ <- Entity.i(2).bigDecimalSet_.hasNo(bigDecimal2).update.transact
      _ <- Entity.i(2).dateSet_.hasNo(date2).update.transact
      _ <- Entity.i(2).durationSet_.hasNo(duration2).update.transact
      _ <- Entity.i(2).instantSet_.hasNo(instant2).update.transact
      _ <- Entity.i(2).localDateSet_.hasNo(localDate2).update.transact
      _ <- Entity.i(2).localTimeSet_.hasNo(localTime2).update.transact
      _ <- Entity.i(2).localDateTimeSet_.hasNo(localDateTime2).update.transact
      _ <- Entity.i(2).offsetTimeSet_.hasNo(offsetTime2).update.transact
      _ <- Entity.i(2).offsetDateTimeSet_.hasNo(offsetDateTime2).update.transact
      _ <- Entity.i(2).zonedDateTimeSet_.hasNo(zonedDateTime2).update.transact
      _ <- Entity.i(2).uuidSet_.hasNo(uuid2).update.transact
      _ <- Entity.i(2).uriSet_.hasNo(uri2).update.transact
      _ <- Entity.i(2).byteSet_.hasNo(byte2).update.transact
      _ <- Entity.i(2).shortSet_.hasNo(short2).update.transact
      _ <- Entity.i(2).charSet_.hasNo(char2).update.transact
      _ <- Entity.i(2).refs_.hasNo(ref2).update.transact

      // i has been updated
      _ <- Entity.i.stringSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charSet_.query.get.map(_.head ==> 2)
      _ <- Entity.i.refs_.query.get.map(_.head ==> 2)
    } yield ()
  }
}
