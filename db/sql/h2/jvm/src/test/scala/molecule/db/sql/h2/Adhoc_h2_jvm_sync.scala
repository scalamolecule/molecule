package molecule.db.sql.h2

import boopickle.Default.*
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.sql.h2.setup.DbProviders_h2
import molecule.db.sql.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  "basic" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*

    val List(a, b) = Entity.int.insert.apply(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)


    // Save an entity with 23 attributes!
    Entity
      .string(string1) // string1 is a dummy String value, etc for all types...
      .int(int1)
      .long(long1)
      .float(float1)
      .double(double1)
      .boolean(boolean1)
      .bigInt(bigInt1)
      .bigDecimal(bigDecimal1)
      .date(date1)
      .duration(duration1)
      .instant(instant1)
      .localDate(localDate1)
      .localTime(localTime1)
      .localDateTime(localDateTime1)
      .offsetTime(offsetTime1)
      .offsetDateTime(offsetDateTime1)
      .zonedDateTime(zonedDateTime1)
      .uuid(uuid1)
      .uri(uri1)
      .byte(byte1)
      .short(short1)
      .char(char1)
      .i(int2)
      .save.transact

    // Query entity with 23 attributes
    Entity
      .string
      .int
      .long
      .float
      .double
      .boolean
      .bigInt
      .bigDecimal
      .date
      .duration
      .instant
      .localDate
      .localTime
      .localDateTime
      .offsetTime
      .offsetDateTime
      .zonedDateTime
      .uuid
      .uri
      .byte
      .short
      .char
      .i
      .query.get.head ==>
      // Yes, we get all 23 attributes back with correct types
      (
        string1,
        int1,
        long1,
        float1,
        double1,
        boolean1,
        bigInt1,
        bigDecimal1,
        date1,
        duration1,
        instant1,
        localDate1,
        localTime1,
        localDateTime1,
        offsetTime1,
        offsetDateTime1,
        zonedDateTime1,
        uuid1,
        uri1,
        byte1,
        short1,
        char1,
        int2
      )

  }


  //  "community" - community { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Community.*
  //
  //    //    val a1: List[Tuple1[Int]] = Person.age.query.get
  //    val a1 = Person.id_.apply(42L).delete.transact
  //    val a2 = Person.age.query.get
  //    val a3 = Person.age.name.query.get
  //
  //    //    val a1: List[Int]           = Person.id_.apply(42L).delete.transact
  //    //    val a2: List[Int]           = Person.age.query1.get
  //    //    val a3: List[(Int, String)] = Person.age.name.queryn.get
  //  }


  //  "Subscription" - types { implicit conn =>
  //    var intermediaryCallbackResults = List.empty[List[Int]]
  //
  //    // Initial data
  //    Entity.i(1).save.transact
  //
  //    // Start subscription
  //    Entity.i.query.subscribe { freshResult =>
  //      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
  //    }
  //
  //    // Mutations to be monitored by subscription
  //    val id = Entity.i(2).save.transact.id
  //    Entity.i.a1.query.get ==> List(1, 2)
  //
  //    Entity.i.insert(3, 4).transact
  //    Entity.i.a1.query.get ==> List(1, 2, 3, 4)
  //
  //    Entity(id).i(20).update.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4, 20)
  //
  //    Entity(id).delete.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4)
  //
  //    // Mutations with no callback-involved attributes don't call back
  //    Entity.string("foo").save.transact
  //
  //    // Callback catched all intermediary results correctly
  //    intermediaryCallbackResults.map(_.sorted) ==> List(
  //      List(1, 2), // query result after 2 was saved
  //      List(1, 2, 3, 4), // query result after 3 and 4 were inserted
  //      List(1, 3, 4, 20), // query result after 2 was updated to 20
  //      List(1, 3, 4), // query result after 20 was deleted
  //    )
  //  }


  //  "commit" - refs { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.i.insert(1, 2, 3).transact
  //    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  //  }
}
