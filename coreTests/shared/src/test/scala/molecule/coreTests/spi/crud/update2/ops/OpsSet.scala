package molecule.coreTests.spi.crud.update2.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OpsSet extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).intSet(Set(int1, int2)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).intSet(Set(int2, int3)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int2, int3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").intSet(Set(int3, int4)).update.transact
        _ <- Ns.i.s.intSet.query.get.map(_.head ==> (42, "foo", Set(int3, int4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).intSet(Set.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).intSet(Set(int1, int2)).update.transact
        // Apply nothing deletes attribute
        _ <- Ns(id).intSet().update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).intSet.add(int1).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).intSet.add(int1).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Add new value
        _ <- Ns(id).intSet.add(int2).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))

        // Add multiple values with vararg
        _ <- Ns(id).intSet.add(int3, int4).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4))

        // Add multiple values with Iterable
        _ <- Ns(id).intSet.add(List(int5, int6)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).intSet.add(Set.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).intSet.remove(int1).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).intSet.add(int1, int2, int3, int4, int5, int6, int7).update.transact

        // Remove value
        _ <- Ns(id).intSet.remove(int7).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing non-existing value has no effect
        _ <- Ns(id).intSet.remove(int9).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5, int6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).intSet.remove(int6, int6).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3, int4, int5))

        // Remove multiple values with varargs
        _ <- Ns(id).intSet.remove(int4, int5).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2, int3))

        // Remove multiple values with Iterable
        _ <- Ns(id).intSet.remove(List(int2, int3)).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).intSet.remove(Vector.empty[Int]).update.transact
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).intSet.remove(Set(int1)).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> Nil)
      } yield ()
    }

    
    "Types apply" - types { implicit conn =>
      for {
        id1 <- Ns.stringSet(Set(string1)).save.transact.map(_.id)
        id2 <- Ns.intSet(Set(int1)).save.transact.map(_.id)
        id3 <- Ns.longSet(Set(long1)).save.transact.map(_.id)
        id4 <- Ns.floatSet(Set(float1)).save.transact.map(_.id)
        id5 <- Ns.doubleSet(Set(double1)).save.transact.map(_.id)
        id6 <- Ns.booleanSet(Set(boolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntSet(Set(bigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSet(Set(bigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateSet(Set(date1)).save.transact.map(_.id)
        id10 <- Ns.durationSet(Set(duration1)).save.transact.map(_.id)
        id11 <- Ns.instantSet(Set(instant1)).save.transact.map(_.id)
        id12 <- Ns.localDateSet(Set(localDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeSet(Set(localTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSet(Set(localDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSet(Set(offsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSet(Set(offsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSet(Set(zonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidSet(Set(uuid1)).save.transact.map(_.id)
        id19 <- Ns.uriSet(Set(uri1)).save.transact.map(_.id)
        id20 <- Ns.byteSet(Set(byte1)).save.transact.map(_.id)
        id21 <- Ns.shortSet(Set(short1)).save.transact.map(_.id)
        id22 <- Ns.charSet(Set(char1)).save.transact.map(_.id)

        // Refs are sets
        id23 <- Ns.refs(Set(ref1)).save.transact.map(_.id)

        _ <- Ns(id1).stringSet(Set(string2)).update.transact
        _ <- Ns(id2).intSet(Set(int2)).update.transact
        _ <- Ns(id3).longSet(Set(long2)).update.transact
        _ <- Ns(id4).floatSet(Set(float2)).update.transact
        _ <- Ns(id5).doubleSet(Set(double2)).update.transact
        _ <- Ns(id6).booleanSet(Set(boolean2)).update.transact
        _ <- Ns(id7).bigIntSet(Set(bigInt2)).update.transact
        _ <- Ns(id8).bigDecimalSet(Set(bigDecimal2)).update.transact
        _ <- Ns(id9).dateSet(Set(date2)).update.transact
        _ <- Ns(id10).durationSet(Set(duration2)).update.transact
        _ <- Ns(id11).instantSet(Set(instant2)).update.transact
        _ <- Ns(id12).localDateSet(Set(localDate2)).update.transact
        _ <- Ns(id13).localTimeSet(Set(localTime2)).update.transact
        _ <- Ns(id14).localDateTimeSet(Set(localDateTime2)).update.transact
        _ <- Ns(id15).offsetTimeSet(Set(offsetTime2)).update.transact
        _ <- Ns(id16).offsetDateTimeSet(Set(offsetDateTime2)).update.transact
        _ <- Ns(id17).zonedDateTimeSet(Set(zonedDateTime2)).update.transact
        _ <- Ns(id18).uuidSet(Set(uuid2)).update.transact
        _ <- Ns(id19).uriSet(Set(uri2)).update.transact
        _ <- Ns(id20).byteSet(Set(byte2)).update.transact
        _ <- Ns(id21).shortSet(Set(short2)).update.transact
        _ <- Ns(id22).charSet(Set(char2)).update.transact
        _ <- Ns(id23).refs(Set(ref2)).update.transact

        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string2))
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int2))
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long2))
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float2))
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double2))
        _ <- Ns.booleanSet.query.get.map(_.head ==> Set(boolean2))
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt2))
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal2))
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date2))
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration2))
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant2))
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate2))
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime2))
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime2))
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime2))
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime2))
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime2))
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid2))
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri2))
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte2))
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short2))
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char2))
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref2))
      } yield ()
    }

    
    "Types add" - types { implicit conn =>
      for {
        id1 <- Ns.stringSet(Set(string1)).save.transact.map(_.id)
        id2 <- Ns.intSet(Set(int1)).save.transact.map(_.id)
        id3 <- Ns.longSet(Set(long1)).save.transact.map(_.id)
        id4 <- Ns.floatSet(Set(float1)).save.transact.map(_.id)
        id5 <- Ns.doubleSet(Set(double1)).save.transact.map(_.id)
        id6 <- Ns.booleanSet(Set(boolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntSet(Set(bigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSet(Set(bigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateSet(Set(date1)).save.transact.map(_.id)
        id10 <- Ns.durationSet(Set(duration1)).save.transact.map(_.id)
        id11 <- Ns.instantSet(Set(instant1)).save.transact.map(_.id)
        id12 <- Ns.localDateSet(Set(localDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeSet(Set(localTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSet(Set(localDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSet(Set(offsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSet(Set(offsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSet(Set(zonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidSet(Set(uuid1)).save.transact.map(_.id)
        id19 <- Ns.uriSet(Set(uri1)).save.transact.map(_.id)
        id20 <- Ns.byteSet(Set(byte1)).save.transact.map(_.id)
        id21 <- Ns.shortSet(Set(short1)).save.transact.map(_.id)
        id22 <- Ns.charSet(Set(char1)).save.transact.map(_.id)
        id23 <- Ns.refs(Set(ref1)).save.transact.map(_.id)

        _ <- Ns(id1).stringSet.add(string2).update.transact
        _ <- Ns(id2).intSet.add(int2).update.transact
        _ <- Ns(id3).longSet.add(long2).update.transact
        _ <- Ns(id4).floatSet.add(float2).update.transact
        _ <- Ns(id5).doubleSet.add(double2).update.transact
        _ <- Ns(id6).booleanSet.add(boolean2).update.transact
        _ <- Ns(id7).bigIntSet.add(bigInt2).update.transact
        _ <- Ns(id8).bigDecimalSet.add(bigDecimal2).update.transact
        _ <- Ns(id9).dateSet.add(date2).update.transact
        _ <- Ns(id10).durationSet.add(duration2).update.transact
        _ <- Ns(id11).instantSet.add(instant2).update.transact
        _ <- Ns(id12).localDateSet.add(localDate2).update.transact
        _ <- Ns(id13).localTimeSet.add(localTime2).update.transact
        _ <- Ns(id14).localDateTimeSet.add(localDateTime2).update.transact
        _ <- Ns(id15).offsetTimeSet.add(offsetTime2).update.transact
        _ <- Ns(id16).offsetDateTimeSet.add(offsetDateTime2).update.transact
        _ <- Ns(id17).zonedDateTimeSet.add(zonedDateTime2).update.transact
        _ <- Ns(id18).uuidSet.add(uuid2).update.transact
        _ <- Ns(id19).uriSet.add(uri2).update.transact
        _ <- Ns(id20).byteSet.add(byte2).update.transact
        _ <- Ns(id21).shortSet.add(short2).update.transact
        _ <- Ns(id22).charSet.add(char2).update.transact
        _ <- Ns(id23).refs.add(ref2).update.transact

        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2))
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2))
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))
        _ <- Ns.booleanSet.query.get.map(_.head ==> Set(boolean1, boolean2))
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2))
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2))
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))
      } yield ()
    }

    
    "Types remove" - types { implicit conn =>
      for {
        id1 <- Ns.stringSet(Set(string1, string2)).save.transact.map(_.id)
        id2 <- Ns.intSet(Set(int1, int2)).save.transact.map(_.id)
        id3 <- Ns.longSet(Set(long1, long2)).save.transact.map(_.id)
        id4 <- Ns.floatSet(Set(float1, float2)).save.transact.map(_.id)
        id5 <- Ns.doubleSet(Set(double1, double2)).save.transact.map(_.id)
        id6 <- Ns.booleanSet(Set(boolean1, boolean2)).save.transact.map(_.id)
        id7 <- Ns.bigIntSet(Set(bigInt1, bigInt2)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact.map(_.id)
        id9 <- Ns.dateSet(Set(date1, date2)).save.transact.map(_.id)
        id10 <- Ns.durationSet(Set(duration1, duration2)).save.transact.map(_.id)
        id11 <- Ns.instantSet(Set(instant1, instant2)).save.transact.map(_.id)
        id12 <- Ns.localDateSet(Set(localDate1, localDate2)).save.transact.map(_.id)
        id13 <- Ns.localTimeSet(Set(localTime1, localTime2)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeSet(Set(localDateTime1, localDateTime2)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeSet(Set(offsetTime1, offsetTime2)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2)).save.transact.map(_.id)
        id18 <- Ns.uuidSet(Set(uuid1, uuid2)).save.transact.map(_.id)
        id19 <- Ns.uriSet(Set(uri1, uri2)).save.transact.map(_.id)
        id20 <- Ns.byteSet(Set(byte1, byte2)).save.transact.map(_.id)
        id21 <- Ns.shortSet(Set(short1, short2)).save.transact.map(_.id)
        id22 <- Ns.charSet(Set(char1, char2)).save.transact.map(_.id)
        id23 <- Ns.refs(Set(ref1, ref2)).save.transact.map(_.id)

        _ <- Ns(id1).stringSet.remove(string2).update.transact
        _ <- Ns(id2).intSet.remove(int2).update.transact
        _ <- Ns(id3).longSet.remove(long2).update.transact
        _ <- Ns(id4).floatSet.remove(float2).update.transact
        _ <- Ns(id5).doubleSet.remove(double2).update.transact
        _ <- Ns(id6).booleanSet.remove(boolean2).update.transact
        _ <- Ns(id7).bigIntSet.remove(bigInt2).update.transact
        _ <- Ns(id8).bigDecimalSet.remove(bigDecimal2).update.transact
        _ <- Ns(id9).dateSet.remove(date2).update.transact
        _ <- Ns(id10).durationSet.remove(duration2).update.transact
        _ <- Ns(id11).instantSet.remove(instant2).update.transact
        _ <- Ns(id12).localDateSet.remove(localDate2).update.transact
        _ <- Ns(id13).localTimeSet.remove(localTime2).update.transact
        _ <- Ns(id14).localDateTimeSet.remove(localDateTime2).update.transact
        _ <- Ns(id15).offsetTimeSet.remove(offsetTime2).update.transact
        _ <- Ns(id16).offsetDateTimeSet.remove(offsetDateTime2).update.transact
        _ <- Ns(id17).zonedDateTimeSet.remove(zonedDateTime2).update.transact
        _ <- Ns(id18).uuidSet.remove(uuid2).update.transact
        _ <- Ns(id19).uriSet.remove(uri2).update.transact
        _ <- Ns(id20).byteSet.remove(byte2).update.transact
        _ <- Ns(id21).shortSet.remove(short2).update.transact
        _ <- Ns(id22).charSet.remove(char2).update.transact
        _ <- Ns(id23).refs.remove(ref2).update.transact

        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1))
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1))
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1))
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1))
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1))
        _ <- Ns.booleanSet.query.get.map(_.head ==> Set(boolean1))
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1))
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1))
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1))
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1))
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1))
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1))
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1))
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1))
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1))
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1))
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1))
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1))
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1))
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1))
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1))
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1))
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))
      } yield ()
    }
  }
}
