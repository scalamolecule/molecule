package molecule.coreTests.spi.crud.update.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OpsSeq extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intSeq(Seq(int1)).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // To insert the attribute value if not already asserted, use `upsert`
        _ <- Ns(id).intSeq(Seq(int1)).upsert.transact
        // Now the attribute value was inserted
        _ <- Ns.intSeq.query.get.map(_.head ==> Seq(int1))

        // Update value to current value doesn't change anything
        _ <- Ns(id).intSeq(Seq(int1)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> Seq(int1))

        // Update existing value
        _ <- Ns(id).intSeq(Seq(int2)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> Seq(int2))

        // Upsert existing value - same effect as update
        _ <- Ns(id).intSeq(Seq(int3)).upsert.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> Seq(int3))

        // OBS: all attributes have to be asserted for any value to be updated!
        _ <- Ns(id).s("foo").intSeq(Seq(int4)).update.transact
        // Nothing is updated
        _ <- Ns.s_?.intSeq.query.get.map(_.head ==> (None, Seq(int3)))

        // Use upsert to guarantee that all values are updated/inserted
        _ <- Ns(id).s("foo").intSeq(Seq(int4)).upsert.transact
        _ <- Ns.s_?.intSeq.query.get.map(_.head ==> (Some("foo"), Seq(int4)))

        // Apply nothing to delete value
        _ <- Ns(id).intSeq().update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Seq attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intSeq.add(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // To add values to the attribute if not already asserted, use `upsert`
        _ <- Ns(id).intSeq.add(int1).upsert.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).intSeq.add(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1))

        // Add new value to end of Seq
        _ <- Ns(id).intSeq.add(int2).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2))

        // Add multiple values with varargs
        _ <- Ns(id).intSeq.add(int3, int4).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4))

        // Add multiple values with Iterable
        _ <- Ns(id).intSeq.add(List(int4, int5)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).intSeq.add(Set.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1, int2, int3, int4, int4, int5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      // No semantic difference between update/upsert when removing
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).intSeq.remove(int1).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).intSeq.add(
          int1, int2, int3, int4, int5, int6, int7,
          int1, int2, int3, int4, int5, int6, int7,
        ).upsert.transact

        // Remove all instances of a value
        _ <- Ns(id).intSeq.remove(int7).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).intSeq.remove(int9).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5, int6,
          int1, int2, int3, int4, int5, int6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).intSeq.remove(int6, int6).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3, int4, int5,
          int1, int2, int3, int4, int5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).intSeq.remove(int4, int5).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1, int2, int3,
          int1, int2, int3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).intSeq.remove(List(int2, int3)).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(
          int1,
          int1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).intSeq.remove(Vector.empty[Int]).update.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).intSeq.remove(Set(int1)).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intSeq_?.insert(
          (1, None),
          (1, Some(Seq(1))),
          (2, Some(Seq(2))),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intSeq(Seq(3)).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intSeq_?.query.get.map(_ ==> List(
          (a, 1, None), //         no value to update
          (b, 1, Some(Seq(3))), // value updated
          (c, 2, Some(Seq(2))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intSeq(Seq(4)).upsert.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intSeq_?.query.get.map(_ ==> List(
          (a, 1, Some(Seq(4))), // attribute inserted
          (b, 1, Some(Seq(4))), // value updated
          (c, 2, Some(Seq(2))),
        ))
      } yield ()
    }


    "Types apply" - types { implicit conn =>
      for {
        id1 <- Ns.stringSeq(List(string1)).save.transact.map(_.id)
        id2 <- Ns.intSeq(List(int1)).save.transact.map(_.id)
        id3 <- Ns.longSeq(List(long1)).save.transact.map(_.id)
        id4 <- Ns.floatSeq(List(float1)).save.transact.map(_.id)
        id5 <- Ns.doubleSeq(List(double1)).save.transact.map(_.id)
        id6 <- Ns.booleanSeq(List(boolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntSeq(List(bigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateSeq(List(date1)).save.transact.map(_.id)
        id10 <- Ns.durationSeq(List(duration1)).save.transact.map(_.id)
        id11 <- Ns.instantSeq(List(instant1)).save.transact.map(_.id)
        id12 <- Ns.localDateSeq(List(localDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeSeq(List(localTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSeq(List(localDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSeq(List(offsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSeq(List(offsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSeq(List(zonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidSeq(List(uuid1)).save.transact.map(_.id)
        id19 <- Ns.uriSeq(List(uri1)).save.transact.map(_.id)
        id20 <- Ns.byteArray(Array(byte1)).save.transact.map(_.id)
        id21 <- Ns.shortSeq(List(short1)).save.transact.map(_.id)
        id22 <- Ns.charSeq(List(char1)).save.transact.map(_.id)

        // Update
        _ <- Ns(id1).stringSeq(List(string2)).update.transact
        _ <- Ns(id2).intSeq(List(int2)).update.transact
        _ <- Ns(id3).longSeq(List(long2)).update.transact
        _ <- Ns(id4).floatSeq(List(float2)).update.transact
        _ <- Ns(id5).doubleSeq(List(double2)).update.transact
        _ <- Ns(id6).booleanSeq(List(boolean2)).update.transact
        _ <- Ns(id7).bigIntSeq(List(bigInt2)).update.transact
        _ <- Ns(id8).bigDecimalSeq(List(bigDecimal2)).update.transact
        _ <- Ns(id9).dateSeq(List(date2)).update.transact
        _ <- Ns(id10).durationSeq(List(duration2)).update.transact
        _ <- Ns(id11).instantSeq(List(instant2)).update.transact
        _ <- Ns(id12).localDateSeq(List(localDate2)).update.transact
        _ <- Ns(id13).localTimeSeq(List(localTime2)).update.transact
        _ <- Ns(id14).localDateTimeSeq(List(localDateTime2)).update.transact
        _ <- Ns(id15).offsetTimeSeq(List(offsetTime2)).update.transact
        _ <- Ns(id16).offsetDateTimeSeq(List(offsetDateTime2)).update.transact
        _ <- Ns(id17).zonedDateTimeSeq(List(zonedDateTime2)).update.transact
        _ <- Ns(id18).uuidSeq(List(uuid2)).update.transact
        _ <- Ns(id19).uriSeq(List(uri2)).update.transact
        _ <- Ns(id20).byteArray(Array(byte2)).update.transact
        _ <- Ns(id21).shortSeq(List(short2)).update.transact
        _ <- Ns(id22).charSeq(List(char2)).update.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string2))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int2))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long2))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float2))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double2))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(boolean2))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt2))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal2))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date2))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration2))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant2))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate2))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime2))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime2))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime2))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime2))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime2))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid2))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri2))
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte2))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short2))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char2))

        // Upsert
        _ <- Ns(id1).stringSeq(List(string3)).upsert.transact
        _ <- Ns(id2).intSeq(List(int3)).upsert.transact
        _ <- Ns(id3).longSeq(List(long3)).upsert.transact
        _ <- Ns(id4).floatSeq(List(float3)).upsert.transact
        _ <- Ns(id5).doubleSeq(List(double3)).upsert.transact
        _ <- Ns(id6).booleanSeq(List(boolean3)).upsert.transact
        _ <- Ns(id7).bigIntSeq(List(bigInt3)).upsert.transact
        _ <- Ns(id8).bigDecimalSeq(List(bigDecimal3)).upsert.transact
        _ <- Ns(id9).dateSeq(List(date3)).upsert.transact
        _ <- Ns(id10).durationSeq(List(duration3)).upsert.transact
        _ <- Ns(id11).instantSeq(List(instant3)).upsert.transact
        _ <- Ns(id12).localDateSeq(List(localDate3)).upsert.transact
        _ <- Ns(id13).localTimeSeq(List(localTime3)).upsert.transact
        _ <- Ns(id14).localDateTimeSeq(List(localDateTime3)).upsert.transact
        _ <- Ns(id15).offsetTimeSeq(List(offsetTime3)).upsert.transact
        _ <- Ns(id16).offsetDateTimeSeq(List(offsetDateTime3)).upsert.transact
        _ <- Ns(id17).zonedDateTimeSeq(List(zonedDateTime3)).upsert.transact
        _ <- Ns(id18).uuidSeq(List(uuid3)).upsert.transact
        _ <- Ns(id19).uriSeq(List(uri3)).upsert.transact
        _ <- Ns(id20).byteArray(Array(byte3)).upsert.transact
        _ <- Ns(id21).shortSeq(List(short3)).upsert.transact
        _ <- Ns(id22).charSeq(List(char3)).upsert.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string3))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int3))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long3))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float3))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double3))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(boolean3))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt3))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal3))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date3))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration3))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant3))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate3))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime3))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime3))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime3))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime3))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime3))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid3))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri3))
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte3))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short3))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char3))
      } yield ()
    }


    "Types add" - types { implicit conn =>
      for {
        id1 <- Ns.stringSeq(List(string1)).save.transact.map(_.id)
        id2 <- Ns.intSeq(List(int1)).save.transact.map(_.id)
        id3 <- Ns.longSeq(List(long1)).save.transact.map(_.id)
        id4 <- Ns.floatSeq(List(float1)).save.transact.map(_.id)
        id5 <- Ns.doubleSeq(List(double1)).save.transact.map(_.id)
        id6 <- Ns.booleanSeq(List(boolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntSeq(List(bigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSeq(List(bigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateSeq(List(date1)).save.transact.map(_.id)
        id10 <- Ns.durationSeq(List(duration1)).save.transact.map(_.id)
        id11 <- Ns.instantSeq(List(instant1)).save.transact.map(_.id)
        id12 <- Ns.localDateSeq(List(localDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeSeq(List(localTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSeq(List(localDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSeq(List(offsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSeq(List(offsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSeq(List(zonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidSeq(List(uuid1)).save.transact.map(_.id)
        id19 <- Ns.uriSeq(List(uri1)).save.transact.map(_.id)
        // Byte arrays have no add
        id21 <- Ns.shortSeq(List(short1)).save.transact.map(_.id)
        id22 <- Ns.charSeq(List(char1)).save.transact.map(_.id)

        // Update
        _ <- Ns(id1).stringSeq.add(string2).update.transact
        _ <- Ns(id2).intSeq.add(int2).update.transact
        _ <- Ns(id3).longSeq.add(long2).update.transact
        _ <- Ns(id4).floatSeq.add(float2).update.transact
        _ <- Ns(id5).doubleSeq.add(double2).update.transact
        _ <- Ns(id6).booleanSeq.add(boolean2).update.transact
        _ <- Ns(id7).bigIntSeq.add(bigInt2).update.transact
        _ <- Ns(id8).bigDecimalSeq.add(bigDecimal2).update.transact
        _ <- Ns(id9).dateSeq.add(date2).update.transact
        _ <- Ns(id10).durationSeq.add(duration2).update.transact
        _ <- Ns(id11).instantSeq.add(instant2).update.transact
        _ <- Ns(id12).localDateSeq.add(localDate2).update.transact
        _ <- Ns(id13).localTimeSeq.add(localTime2).update.transact
        _ <- Ns(id14).localDateTimeSeq.add(localDateTime2).update.transact
        _ <- Ns(id15).offsetTimeSeq.add(offsetTime2).update.transact
        _ <- Ns(id16).offsetDateTimeSeq.add(offsetDateTime2).update.transact
        _ <- Ns(id17).zonedDateTimeSeq.add(zonedDateTime2).update.transact
        _ <- Ns(id18).uuidSeq.add(uuid2).update.transact
        _ <- Ns(id19).uriSeq.add(uri2).update.transact
        _ <- Ns(id21).shortSeq.add(short2).update.transact
        _ <- Ns(id22).charSeq.add(char2).update.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(boolean1, boolean2))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2))

        // Upsert
        _ <- Ns(id1).stringSeq.add(string3).upsert.transact
        _ <- Ns(id2).intSeq.add(int3).upsert.transact
        _ <- Ns(id3).longSeq.add(long3).upsert.transact
        _ <- Ns(id4).floatSeq.add(float3).upsert.transact
        _ <- Ns(id5).doubleSeq.add(double3).upsert.transact
        _ <- Ns(id6).booleanSeq.add(boolean3).upsert.transact
        _ <- Ns(id7).bigIntSeq.add(bigInt3).upsert.transact
        _ <- Ns(id8).bigDecimalSeq.add(bigDecimal3).upsert.transact
        _ <- Ns(id9).dateSeq.add(date3).upsert.transact
        _ <- Ns(id10).durationSeq.add(duration3).upsert.transact
        _ <- Ns(id11).instantSeq.add(instant3).upsert.transact
        _ <- Ns(id12).localDateSeq.add(localDate3).upsert.transact
        _ <- Ns(id13).localTimeSeq.add(localTime3).upsert.transact
        _ <- Ns(id14).localDateTimeSeq.add(localDateTime3).upsert.transact
        _ <- Ns(id15).offsetTimeSeq.add(offsetTime3).upsert.transact
        _ <- Ns(id16).offsetDateTimeSeq.add(offsetDateTime3).upsert.transact
        _ <- Ns(id17).zonedDateTimeSeq.add(zonedDateTime3).upsert.transact
        _ <- Ns(id18).uuidSeq.add(uuid3).upsert.transact
        _ <- Ns(id19).uriSeq.add(uri3).upsert.transact
        _ <- Ns(id21).shortSeq.add(short3).upsert.transact
        _ <- Ns(id22).charSeq.add(char3).upsert.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2, string3))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2, int3))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2, long3))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float3))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2, double3))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(boolean1, boolean2, boolean3))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2, bigInt3))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2, bigDecimal3))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2, date3))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2, duration3))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2, instant3))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2, localDate3))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2, localTime3))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2, localDateTime3))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2, offsetTime3))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime3))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime3))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2, uuid3))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2, uri3))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2, short3))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2, char3))
      } yield ()
    }


    "Types remove" - types { implicit conn =>
      for {
        id1 <- Ns.stringSeq(List(string1, string2, string3)).save.transact.map(_.id)
        id2 <- Ns.intSeq(List(int1, int2, int3)).save.transact.map(_.id)
        id3 <- Ns.longSeq(List(long1, long2, long3)).save.transact.map(_.id)
        id4 <- Ns.floatSeq(List(float1, float2, float3)).save.transact.map(_.id)
        id5 <- Ns.doubleSeq(List(double1, double2, double3)).save.transact.map(_.id)
        id6 <- Ns.booleanSeq(List(true, false, true)).save.transact.map(_.id)
        id7 <- Ns.bigIntSeq(List(bigInt1, bigInt2, bigInt3)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSeq(List(bigDecimal1, bigDecimal2, bigDecimal3)).save.transact.map(_.id)
        id9 <- Ns.dateSeq(List(date1, date2, date3)).save.transact.map(_.id)
        id10 <- Ns.durationSeq(List(duration1, duration2, duration3)).save.transact.map(_.id)
        id11 <- Ns.instantSeq(List(instant1, instant2, instant3)).save.transact.map(_.id)
        id12 <- Ns.localDateSeq(List(localDate1, localDate2, localDate3)).save.transact.map(_.id)
        id13 <- Ns.localTimeSeq(List(localTime1, localTime2, localTime3)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSeq(List(localDateTime1, localDateTime2, localDateTime3)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSeq(List(offsetTime1, offsetTime2, offsetTime3)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).save.transact.map(_.id)
        id18 <- Ns.uuidSeq(List(uuid1, uuid2, uuid3)).save.transact.map(_.id)
        id19 <- Ns.uriSeq(List(uri1, uri2, uri3)).save.transact.map(_.id)
        // Byte arrays have no remove
        id21 <- Ns.shortSeq(List(short1, short2)).save.transact.map(_.id)
        id22 <- Ns.charSeq(List(char1, char2)).save.transact.map(_.id)

        // Update
        _ <- Ns(id1).stringSeq.remove(string3).update.transact
        _ <- Ns(id2).intSeq.remove(int3).update.transact
        _ <- Ns(id3).longSeq.remove(long3).update.transact
        _ <- Ns(id4).floatSeq.remove(float3).update.transact
        _ <- Ns(id5).doubleSeq.remove(double3).update.transact
        _ <- Ns(id6).booleanSeq.remove(true).update.transact
        _ <- Ns(id7).bigIntSeq.remove(bigInt3).update.transact
        _ <- Ns(id8).bigDecimalSeq.remove(bigDecimal3).update.transact
        _ <- Ns(id9).dateSeq.remove(date3).update.transact
        _ <- Ns(id10).durationSeq.remove(duration3).update.transact
        _ <- Ns(id11).instantSeq.remove(instant3).update.transact
        _ <- Ns(id12).localDateSeq.remove(localDate3).update.transact
        _ <- Ns(id13).localTimeSeq.remove(localTime3).update.transact
        _ <- Ns(id14).localDateTimeSeq.remove(localDateTime3).update.transact
        _ <- Ns(id15).offsetTimeSeq.remove(offsetTime3).update.transact
        _ <- Ns(id16).offsetDateTimeSeq.remove(offsetDateTime3).update.transact
        _ <- Ns(id17).zonedDateTimeSeq.remove(zonedDateTime3).update.transact
        _ <- Ns(id18).uuidSeq.remove(uuid3).update.transact
        _ <- Ns(id19).uriSeq.remove(uri3).update.transact
        _ <- Ns(id21).shortSeq.remove(short3).update.transact
        _ <- Ns(id22).charSeq.remove(char3).update.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(false)) // OBS: All true values removed
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2))

        // Add true again so we can upsert
        _ <- Ns(id6).booleanSeq.add(true).update.transact

        // Upsert
        _ <- Ns(id1).stringSeq.remove(string2).upsert.transact
        _ <- Ns(id2).intSeq.remove(int2).upsert.transact
        _ <- Ns(id3).longSeq.remove(long2).upsert.transact
        _ <- Ns(id4).floatSeq.remove(float2).upsert.transact
        _ <- Ns(id5).doubleSeq.remove(double2).upsert.transact
        _ <- Ns(id6).booleanSeq.remove(false).upsert.transact
        _ <- Ns(id7).bigIntSeq.remove(bigInt2).upsert.transact
        _ <- Ns(id8).bigDecimalSeq.remove(bigDecimal2).upsert.transact
        _ <- Ns(id9).dateSeq.remove(date2).upsert.transact
        _ <- Ns(id10).durationSeq.remove(duration2).upsert.transact
        _ <- Ns(id11).instantSeq.remove(instant2).upsert.transact
        _ <- Ns(id12).localDateSeq.remove(localDate2).upsert.transact
        _ <- Ns(id13).localTimeSeq.remove(localTime2).upsert.transact
        _ <- Ns(id14).localDateTimeSeq.remove(localDateTime2).upsert.transact
        _ <- Ns(id15).offsetTimeSeq.remove(offsetTime2).upsert.transact
        _ <- Ns(id16).offsetDateTimeSeq.remove(offsetDateTime2).upsert.transact
        _ <- Ns(id17).zonedDateTimeSeq.remove(zonedDateTime2).upsert.transact
        _ <- Ns(id18).uuidSeq.remove(uuid2).upsert.transact
        _ <- Ns(id19).uriSeq.remove(uri2).upsert.transact
        _ <- Ns(id21).shortSeq.remove(short2).upsert.transact
        _ <- Ns(id22).charSeq.remove(char2).upsert.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(true))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1))
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1))
      } yield ()
    }
  }
}
