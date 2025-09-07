package molecule.db.compliance.test.action.update.ops

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class OpsSet(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "apply" - types {
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Attribute not yet asserted
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intSet(Set(int1)).update.transact
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // To insert the attribute value if not already asserted, use `upsert`
      _ <- Entity(id).intSet(Set(int1)).upsert.transact
      // Now the attribute value was inserted
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Update value to current value doesn't change anything
      _ <- Entity(id).intSet(Set(int1)).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Update existing value
      _ <- Entity(id).intSet(Set(int2)).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int2))

      // Upsert existing value - same effect as update
      _ <- Entity(id).intSet(Set(int3)).upsert.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int3))

      // OBS: all attributes have to be asserted for any value to be updated!
      _ <- Entity(id).s("foo").intSet(Set(int4)).update.transact
      // Nothing is updated
      _ <- Entity.s_?.intSet.query.get.map(_.head ==> (None, Set(int3)))

      // Use upsert to guarantee that all values are updated/inserted
      _ <- Entity(id).s("foo").intSet(Set(int4)).upsert.transact
      _ <- Entity.s_?.intSet.query.get.map(_.head ==> (Some("foo"), Set(int4)))

      // Apply nothing to delete value
      _ <- Entity(id).intSet().update.transact
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // Entity still has other attributes
      _ <- Entity.i.s.query.get.map(_.head ==> (42, "foo"))
    } yield ()
  }


  "add" - types {
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Attribute not asserted yet
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intSet.add(int1).update.transact
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // To add values to the attribute if not already asserted, use `upsert`
      _ <- Entity(id).intSet.add(int1).upsert.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Adding existing value to Set changes nothing
      _ <- Entity(id).intSet.add(int1).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Add new value
      _ <- Entity(id).intSet.add(int2).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2))

      // Add multiple values with vararg
      _ <- Entity(id).intSet.add(int3, int4).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4))

      // Add multiple values with Set
      _ <- Entity(id).intSet.add(Set(int5, int6)).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

      // Adding empty Set of values has no effect
      _ <- Entity(id).intSet.add(Set.empty[Int]).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
    } yield ()
  }


  "remove" - types {
    // No semantic difference between update/upsert when removing
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Set attribute not yet asserted
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // Removing value from non-asserted Set has no effect
      _ <- Entity(id).intSet.remove(int1).update.transact
      _ <- Entity.intSet.query.get.map(_ ==> Nil)

      // Start with some values
      _ <- Entity(id).intSet.add(int1, int2, int3, int4, int5, int6, int7).upsert.transact

      // Remove value
      _ <- Entity(id).intSet.remove(int7).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

      // Removing non-existing value has no effect
      _ <- Entity(id).intSet.remove(int9).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

      // Removing duplicate values removes the distinct value (Set semantics)
      _ <- Entity(id).intSet.remove(int6, int6).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

      // Remove multiple values with varargs
      _ <- Entity(id).intSet.remove(int4, int5).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3))

      // Remove multiple values with Set
      _ <- Entity(id).intSet.remove(Set(int2, int3)).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Removing empty Set of values has no effect
      _ <- Entity(id).intSet.remove(Set.empty[Int]).update.transact
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))

      // Removing all remaining elements deletes the attribute
      _ <- Entity(id).intSet.remove(Set(int1)).update.transact
      _ <- Entity.intSet.query.get.map(_ ==> Nil)
    } yield ()
  }


  "Update multiple values" - types {
    for {
      case List(a, b, c) <- Entity.i.intSet_?.insert(
        (1, None),
        (1, Some(Set(1))),
        (2, Some(Set(2))),
      ).transact.map(_.ids)

      // Update all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intSet(Set(3)).update.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intSet_?.query.get.map(_ ==> List(
        (a, 1, None), //         no value to update
        (b, 1, Some(Set(3))), // value updated
        (c, 2, Some(Set(2))),
      ))

      // Upsert all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intSet(Set(4)).upsert.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intSet_?.query.get.map(_ ==> List(
        (a, 1, Some(Set(4))), // attribute inserted
        (b, 1, Some(Set(4))), // value updated
        (c, 2, Some(Set(2))),
      ))
    } yield ()
  }


  "Types apply" - types {
    for {
      case List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

      id1 <- Entity.stringSet(Set(string1)).save.transact.map(_.id)
      id2 <- Entity.intSet(Set(int1)).save.transact.map(_.id)
      id3 <- Entity.longSet(Set(long1)).save.transact.map(_.id)
      id4 <- Entity.floatSet(Set(float1)).save.transact.map(_.id)
      id5 <- Entity.doubleSet(Set(double1)).save.transact.map(_.id)
      id6 <- Entity.booleanSet(Set(boolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntSet(Set(bigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSet(Set(bigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateSet(Set(date1)).save.transact.map(_.id)
      id10 <- Entity.durationSet(Set(duration1)).save.transact.map(_.id)
      id11 <- Entity.instantSet(Set(instant1)).save.transact.map(_.id)
      id12 <- Entity.localDateSet(Set(localDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeSet(Set(localTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSet(Set(localDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSet(Set(offsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSet(Set(offsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSet(Set(zonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidSet(Set(uuid1)).save.transact.map(_.id)
      id19 <- Entity.uriSet(Set(uri1)).save.transact.map(_.id)
      id20 <- Entity.byteSet(Set(byte1)).save.transact.map(_.id)
      id21 <- Entity.shortSet(Set(short1)).save.transact.map(_.id)
      id22 <- Entity.charSet(Set(char1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSet(Set(string2)).update.transact
      _ <- Entity(id2).intSet(Set(int2)).update.transact
      _ <- Entity(id3).longSet(Set(long2)).update.transact
      _ <- Entity(id4).floatSet(Set(float2)).update.transact
      _ <- Entity(id5).doubleSet(Set(double2)).update.transact
      _ <- Entity(id6).booleanSet(Set(boolean2)).update.transact
      _ <- Entity(id7).bigIntSet(Set(bigInt2)).update.transact
      _ <- Entity(id8).bigDecimalSet(Set(bigDecimal2)).update.transact
      _ <- Entity(id9).dateSet(Set(date2)).update.transact
      _ <- Entity(id10).durationSet(Set(duration2)).update.transact
      _ <- Entity(id11).instantSet(Set(instant2)).update.transact
      _ <- Entity(id12).localDateSet(Set(localDate2)).update.transact
      _ <- Entity(id13).localTimeSet(Set(localTime2)).update.transact
      _ <- Entity(id14).localDateTimeSet(Set(localDateTime2)).update.transact
      _ <- Entity(id15).offsetTimeSet(Set(offsetTime2)).update.transact
      _ <- Entity(id16).offsetDateTimeSet(Set(offsetDateTime2)).update.transact
      _ <- Entity(id17).zonedDateTimeSet(Set(zonedDateTime2)).update.transact
      _ <- Entity(id18).uuidSet(Set(uuid2)).update.transact
      _ <- Entity(id19).uriSet(Set(uri2)).update.transact
      _ <- Entity(id20).byteSet(Set(byte2)).update.transact
      _ <- Entity(id21).shortSet(Set(short2)).update.transact
      _ <- Entity(id22).charSet(Set(char2)).update.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string2))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int2))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long2))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float2))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double2))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(boolean2))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt2))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal2))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date2))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration2))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant2))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate2))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime2))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime2))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime2))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime2))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime2))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid2))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri2))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte2))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short2))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char2))

      // Upsert
      _ <- Entity(id1).stringSet(Set(string3)).upsert.transact
      _ <- Entity(id2).intSet(Set(int3)).upsert.transact
      _ <- Entity(id3).longSet(Set(long3)).upsert.transact
      _ <- Entity(id4).floatSet(Set(float3)).upsert.transact
      _ <- Entity(id5).doubleSet(Set(double3)).upsert.transact
      _ <- Entity(id6).booleanSet(Set(boolean3)).upsert.transact
      _ <- Entity(id7).bigIntSet(Set(bigInt3)).upsert.transact
      _ <- Entity(id8).bigDecimalSet(Set(bigDecimal3)).upsert.transact
      _ <- Entity(id9).dateSet(Set(date3)).upsert.transact
      _ <- Entity(id10).durationSet(Set(duration3)).upsert.transact
      _ <- Entity(id11).instantSet(Set(instant3)).upsert.transact
      _ <- Entity(id12).localDateSet(Set(localDate3)).upsert.transact
      _ <- Entity(id13).localTimeSet(Set(localTime3)).upsert.transact
      _ <- Entity(id14).localDateTimeSet(Set(localDateTime3)).upsert.transact
      _ <- Entity(id15).offsetTimeSet(Set(offsetTime3)).upsert.transact
      _ <- Entity(id16).offsetDateTimeSet(Set(offsetDateTime3)).upsert.transact
      _ <- Entity(id17).zonedDateTimeSet(Set(zonedDateTime3)).upsert.transact
      _ <- Entity(id18).uuidSet(Set(uuid3)).upsert.transact
      _ <- Entity(id19).uriSet(Set(uri3)).upsert.transact
      _ <- Entity(id20).byteSet(Set(byte3)).upsert.transact
      _ <- Entity(id21).shortSet(Set(short3)).upsert.transact
      _ <- Entity(id22).charSet(Set(char3)).upsert.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string3))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int3))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long3))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float3))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double3))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(boolean3))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt3))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal3))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date3))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration3))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant3))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate3))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime3))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime3))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime3))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime3))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime3))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid3))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri3))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte3))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short3))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char3))
    } yield ()
  }


  "Types add" - types {
    for {
      case List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

      id1 <- Entity.stringSet(Set(string1)).save.transact.map(_.id)
      id2 <- Entity.intSet(Set(int1)).save.transact.map(_.id)
      id3 <- Entity.longSet(Set(long1)).save.transact.map(_.id)
      id4 <- Entity.floatSet(Set(float1)).save.transact.map(_.id)
      id5 <- Entity.doubleSet(Set(double1)).save.transact.map(_.id)
      id6 <- Entity.booleanSet(Set(boolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntSet(Set(bigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSet(Set(bigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateSet(Set(date1)).save.transact.map(_.id)
      id10 <- Entity.durationSet(Set(duration1)).save.transact.map(_.id)
      id11 <- Entity.instantSet(Set(instant1)).save.transact.map(_.id)
      id12 <- Entity.localDateSet(Set(localDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeSet(Set(localTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSet(Set(localDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSet(Set(offsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSet(Set(offsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSet(Set(zonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidSet(Set(uuid1)).save.transact.map(_.id)
      id19 <- Entity.uriSet(Set(uri1)).save.transact.map(_.id)
      id20 <- Entity.byteSet(Set(byte1)).save.transact.map(_.id)
      id21 <- Entity.shortSet(Set(short1)).save.transact.map(_.id)
      id22 <- Entity.charSet(Set(char1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSet.add(string2).update.transact
      _ <- Entity(id2).intSet.add(int2).update.transact
      _ <- Entity(id3).longSet.add(long2).update.transact
      _ <- Entity(id4).floatSet.add(float2).update.transact
      _ <- Entity(id5).doubleSet.add(double2).update.transact
      _ <- Entity(id6).booleanSet.add(boolean2).update.transact
      _ <- Entity(id7).bigIntSet.add(bigInt2).update.transact
      _ <- Entity(id8).bigDecimalSet.add(bigDecimal2).update.transact
      _ <- Entity(id9).dateSet.add(date2).update.transact
      _ <- Entity(id10).durationSet.add(duration2).update.transact
      _ <- Entity(id11).instantSet.add(instant2).update.transact
      _ <- Entity(id12).localDateSet.add(localDate2).update.transact
      _ <- Entity(id13).localTimeSet.add(localTime2).update.transact
      _ <- Entity(id14).localDateTimeSet.add(localDateTime2).update.transact
      _ <- Entity(id15).offsetTimeSet.add(offsetTime2).update.transact
      _ <- Entity(id16).offsetDateTimeSet.add(offsetDateTime2).update.transact
      _ <- Entity(id17).zonedDateTimeSet.add(zonedDateTime2).update.transact
      _ <- Entity(id18).uuidSet.add(uuid2).update.transact
      _ <- Entity(id19).uriSet.add(uri2).update.transact
      _ <- Entity(id20).byteSet.add(byte2).update.transact
      _ <- Entity(id21).shortSet.add(short2).update.transact
      _ <- Entity(id22).charSet.add(char2).update.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string1, string2))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long1, long2))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float1, float2))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double1, double2))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(boolean1, boolean2))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date1, date2))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration1, duration2))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant1, instant2))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short1, short2))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char1, char2))

      // Upsert
      _ <- Entity(id1).stringSet.add(string3).upsert.transact
      _ <- Entity(id2).intSet.add(int3).upsert.transact
      _ <- Entity(id3).longSet.add(long3).upsert.transact
      _ <- Entity(id4).floatSet.add(float3).upsert.transact
      _ <- Entity(id5).doubleSet.add(double3).upsert.transact
      _ <- Entity(id6).booleanSet.add(boolean3).upsert.transact
      _ <- Entity(id7).bigIntSet.add(bigInt3).upsert.transact
      _ <- Entity(id8).bigDecimalSet.add(bigDecimal3).upsert.transact
      _ <- Entity(id9).dateSet.add(date3).upsert.transact
      _ <- Entity(id10).durationSet.add(duration3).upsert.transact
      _ <- Entity(id11).instantSet.add(instant3).upsert.transact
      _ <- Entity(id12).localDateSet.add(localDate3).upsert.transact
      _ <- Entity(id13).localTimeSet.add(localTime3).upsert.transact
      _ <- Entity(id14).localDateTimeSet.add(localDateTime3).upsert.transact
      _ <- Entity(id15).offsetTimeSet.add(offsetTime3).upsert.transact
      _ <- Entity(id16).offsetDateTimeSet.add(offsetDateTime3).upsert.transact
      _ <- Entity(id17).zonedDateTimeSet.add(zonedDateTime3).upsert.transact
      _ <- Entity(id18).uuidSet.add(uuid3).upsert.transact
      _ <- Entity(id19).uriSet.add(uri3).upsert.transact
      _ <- Entity(id20).byteSet.add(byte3).upsert.transact
      _ <- Entity(id21).shortSet.add(short3).upsert.transact
      _ <- Entity(id22).charSet.add(char3).upsert.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string1, string2, string3))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2, int3))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long1, long2, long3))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float1, float2, float3))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double1, double2, double3))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(boolean1, boolean2, boolean3))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2, bigInt3))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date1, date2, date3))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration1, duration2, duration3))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant1, instant2, instant3))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2, localDate3))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2, localTime3))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2, localDateTime3))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2, offsetTime3))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2, offsetDateTime3))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2, uuid3))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri1, uri2, uri3))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte1, byte2, byte3))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short1, short2, short3))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char1, char2, char3))
    } yield ()
  }


  "Types remove" - types {
    for {
      List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

      id1 <- Entity.stringSet(Set(string1, string2, string3)).save.transact.map(_.id)
      id2 <- Entity.intSet(Set(int1, int2, int3)).save.transact.map(_.id)
      id3 <- Entity.longSet(Set(long1, long2, long3)).save.transact.map(_.id)
      id4 <- Entity.floatSet(Set(float1, float2, float3)).save.transact.map(_.id)
      id5 <- Entity.doubleSet(Set(double1, double2, double3)).save.transact.map(_.id)
      id6 <- Entity.booleanSet(Set(true, false)).save.transact.map(_.id)
      id7 <- Entity.bigIntSet(Set(bigInt1, bigInt2, bigInt3)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSet(Set(bigDecimal1, bigDecimal2, bigDecimal3)).save.transact.map(_.id)
      id9 <- Entity.dateSet(Set(date1, date2, date3)).save.transact.map(_.id)
      id10 <- Entity.durationSet(Set(duration1, duration2, duration3)).save.transact.map(_.id)
      id11 <- Entity.instantSet(Set(instant1, instant2, instant3)).save.transact.map(_.id)
      id12 <- Entity.localDateSet(Set(localDate1, localDate2, localDate3)).save.transact.map(_.id)
      id13 <- Entity.localTimeSet(Set(localTime1, localTime2, localTime3)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSet(Set(localDateTime1, localDateTime2, localDateTime3)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSet(Set(offsetTime1, offsetTime2, offsetTime3)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).save.transact.map(_.id)
      id18 <- Entity.uuidSet(Set(uuid1, uuid2, uuid3)).save.transact.map(_.id)
      id19 <- Entity.uriSet(Set(uri1, uri2, uri3)).save.transact.map(_.id)
      id20 <- Entity.byteSet(Set(byte1, byte2, byte3)).save.transact.map(_.id)
      id21 <- Entity.shortSet(Set(short1, short2, short3)).save.transact.map(_.id)
      id22 <- Entity.charSet(Set(char1, char2, char3)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSet.remove(string3).update.transact
      _ <- Entity(id2).intSet.remove(int3).update.transact
      _ <- Entity(id3).longSet.remove(long3).update.transact
      _ <- Entity(id4).floatSet.remove(float3).update.transact
      _ <- Entity(id5).doubleSet.remove(double3).update.transact
      _ <- Entity(id6).booleanSet.remove(true).update.transact
      _ <- Entity(id7).bigIntSet.remove(bigInt3).update.transact
      _ <- Entity(id8).bigDecimalSet.remove(bigDecimal3).update.transact
      _ <- Entity(id9).dateSet.remove(date3).update.transact
      _ <- Entity(id10).durationSet.remove(duration3).update.transact
      _ <- Entity(id11).instantSet.remove(instant3).update.transact
      _ <- Entity(id12).localDateSet.remove(localDate3).update.transact
      _ <- Entity(id13).localTimeSet.remove(localTime3).update.transact
      _ <- Entity(id14).localDateTimeSet.remove(localDateTime3).update.transact
      _ <- Entity(id15).offsetTimeSet.remove(offsetTime3).update.transact
      _ <- Entity(id16).offsetDateTimeSet.remove(offsetDateTime3).update.transact
      _ <- Entity(id17).zonedDateTimeSet.remove(zonedDateTime3).update.transact
      _ <- Entity(id18).uuidSet.remove(uuid3).update.transact
      _ <- Entity(id19).uriSet.remove(uri3).update.transact
      _ <- Entity(id20).byteSet.remove(byte3).update.transact
      _ <- Entity(id21).shortSet.remove(short3).update.transact
      _ <- Entity(id22).charSet.remove(char3).update.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string1, string2))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long1, long2))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float1, float2))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double1, double2))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(false)) // OBS: true value removed
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date1, date2))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration1, duration2))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant1, instant2))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short1, short2))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char1, char2))

      // Add true again so we can upsert
      _ <- Entity(id6).booleanSet.add(true).update.transact

      // Upsert
      _ <- Entity(id1).stringSet.remove(string2).upsert.transact
      _ <- Entity(id2).intSet.remove(int2).upsert.transact
      _ <- Entity(id3).longSet.remove(long2).upsert.transact
      _ <- Entity(id4).floatSet.remove(float2).upsert.transact
      _ <- Entity(id5).doubleSet.remove(double2).upsert.transact
      _ <- Entity(id6).booleanSet.remove(false).upsert.transact
      _ <- Entity(id7).bigIntSet.remove(bigInt2).upsert.transact
      _ <- Entity(id8).bigDecimalSet.remove(bigDecimal2).upsert.transact
      _ <- Entity(id9).dateSet.remove(date2).upsert.transact
      _ <- Entity(id10).durationSet.remove(duration2).upsert.transact
      _ <- Entity(id11).instantSet.remove(instant2).upsert.transact
      _ <- Entity(id12).localDateSet.remove(localDate2).upsert.transact
      _ <- Entity(id13).localTimeSet.remove(localTime2).upsert.transact
      _ <- Entity(id14).localDateTimeSet.remove(localDateTime2).upsert.transact
      _ <- Entity(id15).offsetTimeSet.remove(offsetTime2).upsert.transact
      _ <- Entity(id16).offsetDateTimeSet.remove(offsetDateTime2).upsert.transact
      _ <- Entity(id17).zonedDateTimeSet.remove(zonedDateTime2).upsert.transact
      _ <- Entity(id18).uuidSet.remove(uuid2).upsert.transact
      _ <- Entity(id19).uriSet.remove(uri2).upsert.transact
      _ <- Entity(id20).byteSet.remove(byte2).upsert.transact
      _ <- Entity(id21).shortSet.remove(short2).upsert.transact
      _ <- Entity(id22).charSet.remove(char2).upsert.transact

      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string1))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long1))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float1))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double1))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(true))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt1))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date1))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration1))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant1))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate1))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime1))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid1))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri1))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte1))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short1))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char1))
    } yield ()
  }
}
