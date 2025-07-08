package molecule.db.compliance.test.action.update.ops

import molecule.core.setup.{MUnit_arrays, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class OpsSeq(
  suite: MUnit_arrays,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "apply" - types { implicit conn =>
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Attribute not yet asserted
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intSeq(Seq(int1)).update.transact
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // To insert the attribute value if not already asserted, use `upsert`
      _ <- Entity(id).intSeq(Seq(int1)).upsert.transact
      // Now the attribute value was inserted
      _ <- Entity.intSeq.query.get.map(_.head ==> Seq(int1))

      // Update value to current value doesn't change anything
      _ <- Entity(id).intSeq(Seq(int1)).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> Seq(int1))

      // Update existing value
      _ <- Entity(id).intSeq(Seq(int2)).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> Seq(int2))

      // Upsert existing value - same effect as update
      _ <- Entity(id).intSeq(Seq(int3)).upsert.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> Seq(int3))

      // OBS: all attributes have to be asserted for any value to be updated!
      _ <- Entity(id).s("foo").intSeq(Seq(int4)).update.transact
      // Nothing is updated
      _ <- Entity.s_?.intSeq.query.get.map(_.head ==> (None, Seq(int3)))

      // Use upsert to guarantee that all values are updated/inserted
      _ <- Entity(id).s("foo").intSeq(Seq(int4)).upsert.transact
      _ <- Entity.s_?.intSeq.query.get.map(_.head ==> (Some("foo"), Seq(int4)))

      // Apply nothing to delete value
      _ <- Entity(id).intSeq().update.transact
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // Entity still has other attributes
      _ <- Entity.i.s.query.get.map(_.head ==> (42, "foo"))
    } yield ()
  }


  "add" - types { implicit conn =>
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Seq attribute not yet asserted
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intSeq.add(int1).update.transact
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // To add values to the attribute if not already asserted, use `upsert`
      _ <- Entity(id).intSeq.add(int1).upsert.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1))

      // Adding existing value to Seq adds it to the end
      _ <- Entity(id).intSeq.add(int1).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1))

      // Add new value to end of Seq
      _ <- Entity(id).intSeq.add(int2).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1, int2))

      // Add multiple values with varargs
      _ <- Entity(id).intSeq.add(int3, int4).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4))

      // Add multiple values with Seq
      _ <- Entity(id).intSeq.add(List(int4, int5)).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))

      // Adding empty Seq of values has no effect
      _ <- Entity(id).intSeq.add(List.empty[Int]).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))
    } yield ()
  }


  "remove" - types { implicit conn =>
    // No semantic difference between update/upsert when removing
    for {
      id <- Entity.i(42).save.transact.map(_.id)
      // Seq attribute not yet asserted
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // Removing value from non-asserted Seq has no effect
      _ <- Entity(id).intSeq.remove(int1).update.transact
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)

      // Start with some values
      _ <- Entity(id).intSeq.add(
        int1, int2, int3, int4, int5, int6, int7,
        int1, int2, int3, int4, int5, int6, int7,
      ).upsert.transact

      // Remove all instances of a value
      _ <- Entity(id).intSeq.remove(int7).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(
        int1, int2, int3, int4, int5, int6,
        int1, int2, int3, int4, int5, int6,
      ))

      // Removing non-existing value has no effect
      _ <- Entity(id).intSeq.remove(int9).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(
        int1, int2, int3, int4, int5, int6,
        int1, int2, int3, int4, int5, int6,
      ))

      // Removing duplicate values has same effect as applying the value once
      _ <- Entity(id).intSeq.remove(int6, int6).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(
        int1, int2, int3, int4, int5,
        int1, int2, int3, int4, int5,
      ))

      // Remove multiple values with vararg
      _ <- Entity(id).intSeq.remove(int4, int5).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(
        int1, int2, int3,
        int1, int2, int3,
      ))

      // Remove multiple values with Seq
      _ <- Entity(id).intSeq.remove(List(int2, int3)).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(
        int1,
        int1
      ))

      // Removing empty Seq of values has no effect
      _ <- Entity(id).intSeq.remove(List.empty[Int]).update.transact
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int1))

      // Removing all remaining values deletes the attribute
      _ <- Entity(id).intSeq.remove(List(int1)).update.transact
      _ <- Entity.intSeq.query.get.map(_ ==> Nil)
    } yield ()
  }


  "Update multiple values" - types { implicit conn =>
    for {
      case List(a, b, c) <- Entity.i.intSeq_?.insert(
        (1, None),
        (1, Some(Seq(1))),
        (2, Some(Seq(2))),
      ).transact.map(_.ids)

      // Update all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intSeq(Seq(3)).update.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intSeq_?.query.get.map(_ ==> List(
        (a, 1, None), //         no value to update
        (b, 1, Some(Seq(3))), // value updated
        (c, 2, Some(Seq(2))),
      ))

      // Upsert all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intSeq(Seq(4)).upsert.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intSeq_?.query.get.map(_ ==> List(
        (a, 1, Some(Seq(4))), // attribute inserted
        (b, 1, Some(Seq(4))), // value updated
        (c, 2, Some(Seq(2))),
      ))
    } yield ()
  }


  "Types apply" - types { implicit conn =>
    for {
      id1 <- Entity.stringSeq(List(string1)).save.transact.map(_.id)
      id2 <- Entity.intSeq(List(int1)).save.transact.map(_.id)
      id3 <- Entity.longSeq(List(long1)).save.transact.map(_.id)
      id4 <- Entity.floatSeq(List(float1)).save.transact.map(_.id)
      id5 <- Entity.doubleSeq(List(double1)).save.transact.map(_.id)
      id6 <- Entity.booleanSeq(List(boolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntSeq(List(bigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateSeq(List(date1)).save.transact.map(_.id)
      id10 <- Entity.durationSeq(List(duration1)).save.transact.map(_.id)
      id11 <- Entity.instantSeq(List(instant1)).save.transact.map(_.id)
      id12 <- Entity.localDateSeq(List(localDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeSeq(List(localTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSeq(List(localDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSeq(List(offsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSeq(List(offsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSeq(List(zonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidSeq(List(uuid1)).save.transact.map(_.id)
      id19 <- Entity.uriSeq(List(uri1)).save.transact.map(_.id)
      id20 <- Entity.byteArray(Array(byte1)).save.transact.map(_.id)
      id21 <- Entity.shortSeq(List(short1)).save.transact.map(_.id)
      id22 <- Entity.charSeq(List(char1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSeq(List(string2)).update.transact
      _ <- Entity(id2).intSeq(List(int2)).update.transact
      _ <- Entity(id3).longSeq(List(long2)).update.transact
      _ <- Entity(id4).floatSeq(List(float2)).update.transact
      _ <- Entity(id5).doubleSeq(List(double2)).update.transact
      _ <- Entity(id6).booleanSeq(List(boolean2)).update.transact
      _ <- Entity(id7).bigIntSeq(List(bigInt2)).update.transact
      _ <- Entity(id8).bigDecimalSeq(List(bigDecimal2)).update.transact
      _ <- Entity(id9).dateSeq(List(date2)).update.transact
      _ <- Entity(id10).durationSeq(List(duration2)).update.transact
      _ <- Entity(id11).instantSeq(List(instant2)).update.transact
      _ <- Entity(id12).localDateSeq(List(localDate2)).update.transact
      _ <- Entity(id13).localTimeSeq(List(localTime2)).update.transact
      _ <- Entity(id14).localDateTimeSeq(List(localDateTime2)).update.transact
      _ <- Entity(id15).offsetTimeSeq(List(offsetTime2)).update.transact
      _ <- Entity(id16).offsetDateTimeSeq(List(offsetDateTime2)).update.transact
      _ <- Entity(id17).zonedDateTimeSeq(List(zonedDateTime2)).update.transact
      _ <- Entity(id18).uuidSeq(List(uuid2)).update.transact
      _ <- Entity(id19).uriSeq(List(uri2)).update.transact
      _ <- Entity(id20).byteArray(Array(byte2)).update.transact
      _ <- Entity(id21).shortSeq(List(short2)).update.transact
      _ <- Entity(id22).charSeq(List(char2)).update.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string2))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int2))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long2))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float2))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double2))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(boolean2))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt2))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal2))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date2))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration2))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant2))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate2))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime2))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime2))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime2))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime2))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime2))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid2))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri2))
      _ <- Entity.byteArray.query.get.map(_.head ==> Array(byte2))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short2))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char2))

      // Upsert
      _ <- Entity(id1).stringSeq(List(string3)).upsert.transact
      _ <- Entity(id2).intSeq(List(int3)).upsert.transact
      _ <- Entity(id3).longSeq(List(long3)).upsert.transact
      _ <- Entity(id4).floatSeq(List(float3)).upsert.transact
      _ <- Entity(id5).doubleSeq(List(double3)).upsert.transact
      _ <- Entity(id6).booleanSeq(List(boolean3)).upsert.transact
      _ <- Entity(id7).bigIntSeq(List(bigInt3)).upsert.transact
      _ <- Entity(id8).bigDecimalSeq(List(bigDecimal3)).upsert.transact
      _ <- Entity(id9).dateSeq(List(date3)).upsert.transact
      _ <- Entity(id10).durationSeq(List(duration3)).upsert.transact
      _ <- Entity(id11).instantSeq(List(instant3)).upsert.transact
      _ <- Entity(id12).localDateSeq(List(localDate3)).upsert.transact
      _ <- Entity(id13).localTimeSeq(List(localTime3)).upsert.transact
      _ <- Entity(id14).localDateTimeSeq(List(localDateTime3)).upsert.transact
      _ <- Entity(id15).offsetTimeSeq(List(offsetTime3)).upsert.transact
      _ <- Entity(id16).offsetDateTimeSeq(List(offsetDateTime3)).upsert.transact
      _ <- Entity(id17).zonedDateTimeSeq(List(zonedDateTime3)).upsert.transact
      _ <- Entity(id18).uuidSeq(List(uuid3)).upsert.transact
      _ <- Entity(id19).uriSeq(List(uri3)).upsert.transact
      _ <- Entity(id20).byteArray(Array(byte3)).upsert.transact
      _ <- Entity(id21).shortSeq(List(short3)).upsert.transact
      _ <- Entity(id22).charSeq(List(char3)).upsert.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string3))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int3))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long3))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float3))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double3))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(boolean3))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt3))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal3))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date3))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration3))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant3))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate3))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime3))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime3))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime3))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime3))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime3))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid3))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri3))
      _ <- Entity.byteArray.query.get.map(_.head ==> Array(byte3))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short3))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char3))
    } yield ()
  }


  "Types add" - types { implicit conn =>
    for {
      id1 <- Entity.stringSeq(List(string1)).save.transact.map(_.id)
      id2 <- Entity.intSeq(List(int1)).save.transact.map(_.id)
      id3 <- Entity.longSeq(List(long1)).save.transact.map(_.id)
      id4 <- Entity.floatSeq(List(float1)).save.transact.map(_.id)
      id5 <- Entity.doubleSeq(List(double1)).save.transact.map(_.id)
      id6 <- Entity.booleanSeq(List(boolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntSeq(List(bigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateSeq(List(date1)).save.transact.map(_.id)
      id10 <- Entity.durationSeq(List(duration1)).save.transact.map(_.id)
      id11 <- Entity.instantSeq(List(instant1)).save.transact.map(_.id)
      id12 <- Entity.localDateSeq(List(localDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeSeq(List(localTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSeq(List(localDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSeq(List(offsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSeq(List(offsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSeq(List(zonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidSeq(List(uuid1)).save.transact.map(_.id)
      id19 <- Entity.uriSeq(List(uri1)).save.transact.map(_.id)
      // Byte arrays have no add
      id21 <- Entity.shortSeq(List(short1)).save.transact.map(_.id)
      id22 <- Entity.charSeq(List(char1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSeq.add(string2).update.transact
      _ <- Entity(id2).intSeq.add(int2).update.transact
      _ <- Entity(id3).longSeq.add(long2).update.transact
      _ <- Entity(id4).floatSeq.add(float2).update.transact
      _ <- Entity(id5).doubleSeq.add(double2).update.transact
      _ <- Entity(id6).booleanSeq.add(boolean2).update.transact
      _ <- Entity(id7).bigIntSeq.add(bigInt2).update.transact
      _ <- Entity(id8).bigDecimalSeq.add(bigDecimal2).update.transact
      _ <- Entity(id9).dateSeq.add(date2).update.transact
      _ <- Entity(id10).durationSeq.add(duration2).update.transact
      _ <- Entity(id11).instantSeq.add(instant2).update.transact
      _ <- Entity(id12).localDateSeq.add(localDate2).update.transact
      _ <- Entity(id13).localTimeSeq.add(localTime2).update.transact
      _ <- Entity(id14).localDateTimeSeq.add(localDateTime2).update.transact
      _ <- Entity(id15).offsetTimeSeq.add(offsetTime2).update.transact
      _ <- Entity(id16).offsetDateTimeSeq.add(offsetDateTime2).update.transact
      _ <- Entity(id17).zonedDateTimeSeq.add(zonedDateTime2).update.transact
      _ <- Entity(id18).uuidSeq.add(uuid2).update.transact
      _ <- Entity(id19).uriSeq.add(uri2).update.transact
      _ <- Entity(id21).shortSeq.add(short2).update.transact
      _ <- Entity(id22).charSeq.add(char2).update.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string1, string2))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int2))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long1, long2))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float1, float2))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double1, double2))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(boolean1, boolean2))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date1, date2))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short1, short2))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char1, char2))

      // Upsert
      _ <- Entity(id1).stringSeq.add(string3).upsert.transact
      _ <- Entity(id2).intSeq.add(int3).upsert.transact
      _ <- Entity(id3).longSeq.add(long3).upsert.transact
      _ <- Entity(id4).floatSeq.add(float3).upsert.transact
      _ <- Entity(id5).doubleSeq.add(double3).upsert.transact
      _ <- Entity(id6).booleanSeq.add(boolean3).upsert.transact
      _ <- Entity(id7).bigIntSeq.add(bigInt3).upsert.transact
      _ <- Entity(id8).bigDecimalSeq.add(bigDecimal3).upsert.transact
      _ <- Entity(id9).dateSeq.add(date3).upsert.transact
      _ <- Entity(id10).durationSeq.add(duration3).upsert.transact
      _ <- Entity(id11).instantSeq.add(instant3).upsert.transact
      _ <- Entity(id12).localDateSeq.add(localDate3).upsert.transact
      _ <- Entity(id13).localTimeSeq.add(localTime3).upsert.transact
      _ <- Entity(id14).localDateTimeSeq.add(localDateTime3).upsert.transact
      _ <- Entity(id15).offsetTimeSeq.add(offsetTime3).upsert.transact
      _ <- Entity(id16).offsetDateTimeSeq.add(offsetDateTime3).upsert.transact
      _ <- Entity(id17).zonedDateTimeSeq.add(zonedDateTime3).upsert.transact
      _ <- Entity(id18).uuidSeq.add(uuid3).upsert.transact
      _ <- Entity(id19).uriSeq.add(uri3).upsert.transact
      _ <- Entity(id21).shortSeq.add(short3).upsert.transact
      _ <- Entity(id22).charSeq.add(char3).upsert.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string1, string2, string3))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int2, int3))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long1, long2, long3))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float1, float2, float3))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double1, double2, double3))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(boolean1, boolean2, boolean3))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt3))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal3))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date1, date2, date3))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration3))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant3))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate3))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime3))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime3))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime3))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime3))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime3))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid3))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri3))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short1, short2, short3))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char1, char2, char3))
    } yield ()
  }


  "Types remove" - types { implicit conn =>
    for {
      id1 <- Entity.stringSeq(List(string1, string2, string3)).save.transact.map(_.id)
      id2 <- Entity.intSeq(List(int1, int2, int3)).save.transact.map(_.id)
      id3 <- Entity.longSeq(List(long1, long2, long3)).save.transact.map(_.id)
      id4 <- Entity.floatSeq(List(float1, float2, float3)).save.transact.map(_.id)
      id5 <- Entity.doubleSeq(List(double1, double2, double3)).save.transact.map(_.id)
      id6 <- Entity.booleanSeq(List(true, false, true)).save.transact.map(_.id)
      id7 <- Entity.bigIntSeq(List(bigInt1, bigInt2, bigInt3)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal3)).save.transact.map(_.id)
      id9 <- Entity.dateSeq(List(date1, date2, date3)).save.transact.map(_.id)
      id10 <- Entity.durationSeq(List(duration1, duration2, duration3)).save.transact.map(_.id)
      id11 <- Entity.instantSeq(List(instant1, instant2, instant3)).save.transact.map(_.id)
      id12 <- Entity.localDateSeq(List(localDate1, localDate2, localDate3)).save.transact.map(_.id)
      id13 <- Entity.localTimeSeq(List(localTime1, localTime2, localTime3)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime3)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime3)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).save.transact.map(_.id)
      id18 <- Entity.uuidSeq(List(uuid1, uuid2, uuid3)).save.transact.map(_.id)
      id19 <- Entity.uriSeq(List(uri1, uri2, uri3)).save.transact.map(_.id)
      // Byte arrays have no remove
      id21 <- Entity.shortSeq(List(short1, short2)).save.transact.map(_.id)
      id22 <- Entity.charSeq(List(char1, char2)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringSeq.remove(string3).update.transact
      _ <- Entity(id2).intSeq.remove(int3).update.transact
      _ <- Entity(id3).longSeq.remove(long3).update.transact
      _ <- Entity(id4).floatSeq.remove(float3).update.transact
      _ <- Entity(id5).doubleSeq.remove(double3).update.transact
      _ <- Entity(id6).booleanSeq.remove(true).update.transact
      _ <- Entity(id7).bigIntSeq.remove(bigInt3).update.transact
      _ <- Entity(id8).bigDecimalSeq.remove(bigDecimal3).update.transact
      _ <- Entity(id9).dateSeq.remove(date3).update.transact
      _ <- Entity(id10).durationSeq.remove(duration3).update.transact
      _ <- Entity(id11).instantSeq.remove(instant3).update.transact
      _ <- Entity(id12).localDateSeq.remove(localDate3).update.transact
      _ <- Entity(id13).localTimeSeq.remove(localTime3).update.transact
      _ <- Entity(id14).localDateTimeSeq.remove(localDateTime3).update.transact
      _ <- Entity(id15).offsetTimeSeq.remove(offsetTime3).update.transact
      _ <- Entity(id16).offsetDateTimeSeq.remove(offsetDateTime3).update.transact
      _ <- Entity(id17).zonedDateTimeSeq.remove(zonedDateTime3).update.transact
      _ <- Entity(id18).uuidSeq.remove(uuid3).update.transact
      _ <- Entity(id19).uriSeq.remove(uri3).update.transact
      _ <- Entity(id21).shortSeq.remove(short3).update.transact
      _ <- Entity(id22).charSeq.remove(char3).update.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string1, string2))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int2))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long1, long2))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float1, float2))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double1, double2))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(false)) // OBS: All true values removed
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date1, date2))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short1, short2))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char1, char2))

      // Add true again so we can upsert
      _ <- Entity(id6).booleanSeq.add(true).update.transact

      // Upsert
      _ <- Entity(id1).stringSeq.remove(string2).upsert.transact
      _ <- Entity(id2).intSeq.remove(int2).upsert.transact
      _ <- Entity(id3).longSeq.remove(long2).upsert.transact
      _ <- Entity(id4).floatSeq.remove(float2).upsert.transact
      _ <- Entity(id5).doubleSeq.remove(double2).upsert.transact
      _ <- Entity(id6).booleanSeq.remove(false).upsert.transact
      _ <- Entity(id7).bigIntSeq.remove(bigInt2).upsert.transact
      _ <- Entity(id8).bigDecimalSeq.remove(bigDecimal2).upsert.transact
      _ <- Entity(id9).dateSeq.remove(date2).upsert.transact
      _ <- Entity(id10).durationSeq.remove(duration2).upsert.transact
      _ <- Entity(id11).instantSeq.remove(instant2).upsert.transact
      _ <- Entity(id12).localDateSeq.remove(localDate2).upsert.transact
      _ <- Entity(id13).localTimeSeq.remove(localTime2).upsert.transact
      _ <- Entity(id14).localDateTimeSeq.remove(localDateTime2).upsert.transact
      _ <- Entity(id15).offsetTimeSeq.remove(offsetTime2).upsert.transact
      _ <- Entity(id16).offsetDateTimeSeq.remove(offsetDateTime2).upsert.transact
      _ <- Entity(id17).zonedDateTimeSeq.remove(zonedDateTime2).upsert.transact
      _ <- Entity(id18).uuidSeq.remove(uuid2).upsert.transact
      _ <- Entity(id19).uriSeq.remove(uri2).upsert.transact
      _ <- Entity(id21).shortSeq.remove(short2).upsert.transact
      _ <- Entity(id22).charSeq.remove(char2).upsert.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string1))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long1))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float1))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double1))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(true))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt1))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date1))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration1))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant1))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate1))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime1))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid1))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri1))
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short1))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char1))
    } yield ()
  }
}
