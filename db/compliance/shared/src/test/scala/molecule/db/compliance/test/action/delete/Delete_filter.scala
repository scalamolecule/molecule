package molecule.db.compliance.test.action.delete

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Delete_filter(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Filter by 1 non-ns value" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      _ <- A.i.insert(1, 2).transact
      _ <- A.i_(1).delete.transact
      _ <- A.i.query.get.map(_ ==> List(2))
    } yield ()
  }


  "Filter by multiple non-ns value" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      case List(e1, e2, e3) <- A.i.insert(1, 2, 2).transact.map(_.ids)
      _ <- A.id.a1.i.query.get.map(_ ==> List(
        (e1, 1),
        (e2, 2),
        (e3, 2),
      ))

      // Delete two entities having `i` with value 2
      _ <- A.i_(2).delete.transact

      // One entity left
      _ <- A.id.i.query.get.map(_ ==> List(
        (e1, 1),
      ))
    } yield ()
  }

  "Filters: not null" - types {
    for {
      _ <- Entity.string.insert(string1, string2).transact
      _ <- Entity.int.insert(int1, int2).transact

      _ <- Entity.int_.delete.transact

      _ <- Entity.string.int_?.query.get.map(_ ==> List(
        (string1, None),
        (string2, None),
      ))
    } yield ()
  }

  "Filters: null" - types {
    for {
      _ <- Entity.string.insert(string1, string2).transact
      _ <- Entity.int.insert(int1, int2).transact

      _ <- Entity.int_().delete.transact

      _ <- Entity.string_?.int.query.get.map(_ ==> List(
        (None, int1),
        (None, int2),
      ))
    } yield ()
  }

  "Filters: equal 0" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_(int0).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int1, int2, int3))
    } yield ()
  }
  "Filters: equal 2" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_(int2).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int1, int3))
    } yield ()
  }
  "Filters: equal 1 2" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_(int1, int2).delete.transact
      _ <- Entity.int.query.get.map(_ ==> List(int3))
    } yield ()
  }


  "Filters: not equal 0" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.not(int0).delete.transact
      _ <- Entity.int.query.get.map(_ ==> List())
    } yield ()
  }
  "Filters: not equal 2" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.not(int2).delete.transact
      _ <- Entity.int.query.get.map(_ ==> List(int2))
    } yield ()
  }
  "Filters: not equal 1 2" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.not(int1, int2).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int1, int2))
    } yield ()
  }


  "Filters: <" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.<(int2).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int2, int3))
    } yield ()
  }


  "Filters: <=" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.<=(int2).delete.transact
      _ <- Entity.int.query.get.map(_ ==> List(int3))
    } yield ()
  }


  "Filters: >" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.>(int2).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int1, int2))
    } yield ()
  }


  "Filters: >=" - types {
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact
      _ <- Entity.int_.>=(int2).delete.transact
      _ <- Entity.int.query.get.map(_ ==> List(int1))
    } yield ()
  }


  "Multiple filters" - refs {
    for {
      _ <- A.i.s.insert(
        (1, "a"),
        (2, "b"),
        (3, "c"),
      ).transact

      _ <- A.i.a1.s.query.get.map(_ ==> List(
        (1, "a"),
        (2, "b"),
        (3, "c"),
      ))

      // Update all entities where non-ns attribute i > 1 and s < "c"
      _ <- A.i_.>(1).s_.<("c").delete.transact

      _ <- A.i.a1.s.query.get.map(_ ==> List(
        (1, "a"),
        (3, "c"),
      ))
    } yield ()
  }


  "Range" - refs {
    for {
      _ <- A.i.insert(1, 2, 3, 4).transact

      // Delete all entities where `i` is between 1 and 4
      _ <- A.i_.>(1).i_.<(4).delete.transact

      // 2 entities deleted
      _ <- A.i.a1.query.get.map(_ ==> List(1, 4))
    } yield ()
  }


  "Ref" - refs {
    for {
      _ <- A.i.insert(1).transact
      _ <- A.i.B.i.insert((2, 20), (3, 30)).transact

      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.B.i.query.get.map(_ ==> List((2, 20), (3, 30)))

      // Nothing deleted since entity 1 doesn't have a ref
      _ <- A.i_(1).B.i_.delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

      // Second entity has a ref and will be deleted
      _ <- A.i_(2).B.i_.delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i.a1.B.i.query.get.map(_ ==> List((3, 30)))

      // Note that B.int entity is a separate entity and is not deleted.
      // Only the entity of the initial entity is deleted
      _ <- B.i.a1.query.get.map(_ ==> List(20, 30))

      // A.i entity has no ref to B.i_(42) so nothing is deleted
      _ <- A.i_.B.i_(42).delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i.B.i.query.get.map(_ ==> List((3, 30)))

      // A.i entity has a ref to B.i_(30) so it will be deleted
      _ <- A.i_.B.i_(30).delete.transact
      _ <- A.i.query.get.map(_ ==> List(1))
      _ <- A.i.B.i.query.get.map(_ ==> List())
    } yield ()
  }

  "Ref owned" - refs {

    for {
      _ <- A.i.insert(1).transact
      _ <- A.i.OwnB.i.insert((2, 20), (3, 30)).transact

      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((2, 20), (3, 30)))

      // Nothing deleted since entity 1 doesn't have a ref
      _ <- A.i_(1).OwnB.i_.delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

      // Second entity has a ref and will be deleted
      _ <- A.i_(2).OwnB.i_.delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

      // Owned B entity is deleted too
      _ <- B.i.a1.query.get.map(_ ==> List(30))

      // A.i entity has no ref to OwnB.i_(42) so nothing is deleted
      _ <- A.i_.OwnB.i_(42).delete.transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

      // A.i entity has a ref to OwnB.i_(30) so it will be deleted
      _ <- A.i_.OwnB.i_(30).delete.transact
      _ <- A.i.query.get.map(_ ==> List(1))
      _ <- A.i.OwnB.i.query.get.map(_ ==> List())
    } yield ()
  }


  "Ref + expr" - refs {
    for {
      _ <- A.i.B.i.insert((1, 10), (2, 20)).transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
      _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
      _ <- A.i.a1.B.i.query.get.map(_ ==> List((1, 10), (2, 20)))

      _ <- A.i_.B.i_.>(15).delete.transact
      _ <- A.i.query.get.map(_ ==> List(1))
      // Note that the B.int entity is a separate entity and is not deleted
      // Only the entity of the initial entity is deleted
      _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
      _ <- A.i.B.i.query.get.map(_ ==> List((1, 10)))
    } yield ()
  }

  "Ref owned + expr" - refs {
    // In other dbs, owned B is a separate entity. So we can query it independently
    for {
      _ <- A.i.OwnB.i.insert((1, 10), (2, 20)).transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
      _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
      _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((1, 10), (2, 20)))

      _ <- A.i_.OwnB.i_.>(15).delete.transact
      _ <- A.i.query.get.map(_ ==> List(1))

      // Owned B entity with i == 20 is deleted too
      _ <- B.i.a1.query.get.map(_ ==> List(10))
      _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 10)))
    } yield ()
  }


  "Filter types" - types {

    for {
      // Initial values
      _ <- Entity.i.string.insert((1, string1), (2, string2)).transact
      _ <- Entity.i.int.insert((1, int1), (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (2, double2)).transact
      _ <- Entity.i.boolean.insert((1, boolean1), (2, boolean2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2)).transact
      _ <- Entity.i.date.insert((1, date1), (2, date2)).transact
      _ <- Entity.i.duration.insert((1, duration1), (2, duration2)).transact
      _ <- Entity.i.instant.insert((1, instant1), (2, instant2)).transact
      _ <- Entity.i.localDate.insert((1, localDate1), (2, localDate2)).transact
      _ <- Entity.i.localTime.insert((1, localTime1), (2, localTime2)).transact
      _ <- Entity.i.localDateTime.insert((1, localDateTime1), (2, localDateTime2)).transact
      _ <- Entity.i.offsetTime.insert((1, offsetTime1), (2, offsetTime2)).transact
      _ <- Entity.i.offsetDateTime.insert((1, offsetDateTime1), (2, offsetDateTime2)).transact
      _ <- Entity.i.zonedDateTime.insert((1, zonedDateTime1), (2, zonedDateTime2)).transact
      _ <- Entity.i.uuid.insert((1, uuid1), (2, uuid2)).transact
      _ <- Entity.i.uri.insert((1, uri1), (2, uri2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (2, short2)).transact
      _ <- Entity.i.char.insert((1, char1), (2, char2)).transact

      // Delete with looked up unique value
      _ <- Entity.string_(string1).delete.transact
      _ <- Entity.int_(int1).delete.transact
      _ <- Entity.long_(long1).delete.transact
      _ <- Entity.float_(float1).delete.transact
      _ <- Entity.double_(double1).delete.transact
      _ <- Entity.boolean_(boolean1).delete.transact
      _ <- Entity.bigInt_(bigInt1).delete.transact
      _ <- Entity.bigDecimal_(bigDecimal1).delete.transact
      _ <- Entity.date_(date1).delete.transact
      _ <- Entity.duration_(duration1).delete.transact
      _ <- Entity.instant_(instant1).delete.transact
      _ <- Entity.localDate_(localDate1).delete.transact
      _ <- Entity.localTime_(localTime1).delete.transact
      _ <- Entity.localDateTime_(localDateTime1).delete.transact
      _ <- Entity.offsetTime_(offsetTime1).delete.transact
      _ <- Entity.offsetDateTime_(offsetDateTime1).delete.transact
      _ <- Entity.zonedDateTime_(zonedDateTime1).delete.transact
      _ <- Entity.uuid_(uuid1).delete.transact
      _ <- Entity.uri_(uri1).delete.transact
      _ <- Entity.byte_(byte1).delete.transact
      _ <- Entity.short_(short1).delete.transact
      _ <- Entity.char_(char1).delete.transact

      // i == 1 has been deleted
      _ <- Entity.i.string_.query.get.map(_ ==> List(2))
      _ <- Entity.i.int_.query.get.map(_ ==> List(2))
      _ <- Entity.i.long_.query.get.map(_ ==> List(2))
      _ <- Entity.i.float_.query.get.map(_ ==> List(2))
      _ <- Entity.i.double_.query.get.map(_ ==> List(2))
      _ <- Entity.i.boolean_.query.get.map(_ ==> List(2))
      _ <- Entity.i.bigInt_.query.get.map(_ ==> List(2))
      _ <- Entity.i.bigDecimal_.query.get.map(_ ==> List(2))
      _ <- Entity.i.date_.query.get.map(_ ==> List(2))
      _ <- Entity.i.duration_.query.get.map(_ ==> List(2))
      _ <- Entity.i.instant_.query.get.map(_ ==> List(2))
      _ <- Entity.i.localDate_.query.get.map(_ ==> List(2))
      _ <- Entity.i.localTime_.query.get.map(_ ==> List(2))
      _ <- Entity.i.localDateTime_.query.get.map(_ ==> List(2))
      _ <- Entity.i.offsetTime_.query.get.map(_ ==> List(2))
      _ <- Entity.i.offsetDateTime_.query.get.map(_ ==> List(2))
      _ <- Entity.i.zonedDateTime_.query.get.map(_ ==> List(2))
      _ <- Entity.i.uuid_.query.get.map(_ ==> List(2))
      _ <- Entity.i.uri_.query.get.map(_ ==> List(2))
      _ <- Entity.i.byte_.query.get.map(_ ==> List(2))
      _ <- Entity.i.short_.query.get.map(_ ==> List(2))
      _ <- Entity.i.char_.query.get.map(_ ==> List(2))
    } yield ()
  }

  "Only tacit card-one attributes" - refs {
    for {
      _ <- A.iSet_(Set(int1)).delete.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only filter delete by values applied to tacit card-one attributes (A.iSet)."
        }

      _ <- A.iSeq_(List(int1)).delete.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only filter delete by values applied to tacit card-one attributes (A.iSeq)."
        }

      _ = compileErrors("A.iMap_(Map(pint1))")
    } yield ()
  }
}
