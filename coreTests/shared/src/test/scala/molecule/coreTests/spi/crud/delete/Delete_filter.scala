package molecule.coreTests.spi.crud.delete

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Delete_filter extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "Filter by 1 non-ns value" - refs { implicit conn =>
      for {
        _ <- A.i.insert(1, 2).transact
        _ <- A.i_(1).delete.transact
        _ <- A.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Filter by multiple non-ns value" - refs { implicit conn =>
      for {
        List(e1, e2, e3) <- A.i.insert(1, 2, 2).transact.map(_.ids)
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

    "Multiple entities deleted" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      for {
        _ <- Ns.int.insert(1, 2, 3).transact
        _ <- Ns.int_(1, 2).delete.transact
        _ <- Ns.int.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Expression" - {
      import molecule.coreTests.dataModels.dsl.Types._

      "equal 0" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int0).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3))
        } yield ()
      }
      "equal 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int3))
        } yield ()
      }
      "equal 1 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_(int1, int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int3))
        } yield ()
      }


      "not equal 0" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int0).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List())
        } yield ()
      }
      "not equal 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int2))
        } yield ()
      }
      "not equal 1 2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.not(int1, int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
        } yield ()
      }


      "<" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.<(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int2, int3))
        } yield ()
      }


      "<=" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.<=(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int3))
        } yield ()
      }


      ">" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.>(int2).delete.transact
          _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
        } yield ()
      }


      ">=" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(int1, int2, int3).transact
          _ <- Ns.int_.>=(int2).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(int1))
        } yield ()
      }
    }

    "Multiple expressions" - refs { implicit conn =>
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


    "Ref" - refs { implicit conn =>
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
        // Only the entity of the initial namespace is deleted
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

    "Ref owned" - refs { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Refs._
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


    "Ref + expr" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert((1, 10), (2, 20)).transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- A.i.a1.B.i.query.get.map(_ ==> List((1, 10), (2, 20)))

        _ <- A.i_.B.i_.>(15).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        // Note that the B.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 10)))
      } yield ()
    }

    "Ref owned + expr" - refs { implicit conn =>
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


    "Filter types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      for {
        // Initial values
        _ <- Ns.i.string.insert((1, string1), (2, string2)).transact
        _ <- Ns.i.int.insert((1, int1), (2, int2)).transact
        _ <- Ns.i.long.insert((1, long1), (2, long2)).transact
        _ <- Ns.i.float.insert((1, float1), (2, float2)).transact
        _ <- Ns.i.double.insert((1, double1), (2, double2)).transact
        _ <- Ns.i.boolean.insert((1, boolean1), (2, boolean2)).transact
        _ <- Ns.i.bigInt.insert((1, bigInt1), (2, bigInt2)).transact
        _ <- Ns.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2)).transact
        _ <- Ns.i.date.insert((1, date1), (2, date2)).transact
        _ <- Ns.i.duration.insert((1, duration1), (2, duration2)).transact
        _ <- Ns.i.instant.insert((1, instant1), (2, instant2)).transact
        _ <- Ns.i.localDate.insert((1, localDate1), (2, localDate2)).transact
        _ <- Ns.i.localTime.insert((1, localTime1), (2, localTime2)).transact
        _ <- Ns.i.localDateTime.insert((1, localDateTime1), (2, localDateTime2)).transact
        _ <- Ns.i.offsetTime.insert((1, offsetTime1), (2, offsetTime2)).transact
        _ <- Ns.i.offsetDateTime.insert((1, offsetDateTime1), (2, offsetDateTime2)).transact
        _ <- Ns.i.zonedDateTime.insert((1, zonedDateTime1), (2, zonedDateTime2)).transact
        _ <- Ns.i.uuid.insert((1, uuid1), (2, uuid2)).transact
        _ <- Ns.i.uri.insert((1, uri1), (2, uri2)).transact
        _ <- Ns.i.byte.insert((1, byte1), (2, byte2)).transact
        _ <- Ns.i.short.insert((1, short1), (2, short2)).transact
        _ <- Ns.i.char.insert((1, char1), (2, char2)).transact

        // Delete with looked up unique value
        _ <- Ns.string_(string1).delete.transact
        _ <- Ns.int_(int1).delete.transact
        _ <- Ns.long_(long1).delete.transact
        _ <- Ns.float_(float1).delete.transact
        _ <- Ns.double_(double1).delete.transact
        _ <- Ns.boolean_(boolean1).delete.transact
        _ <- Ns.bigInt_(bigInt1).delete.transact
        _ <- Ns.bigDecimal_(bigDecimal1).delete.transact
        _ <- Ns.date_(date1).delete.transact
        _ <- Ns.duration_(duration1).delete.transact
        _ <- Ns.instant_(instant1).delete.transact
        _ <- Ns.localDate_(localDate1).delete.transact
        _ <- Ns.localTime_(localTime1).delete.transact
        _ <- Ns.localDateTime_(localDateTime1).delete.transact
        _ <- Ns.offsetTime_(offsetTime1).delete.transact
        _ <- Ns.offsetDateTime_(offsetDateTime1).delete.transact
        _ <- Ns.zonedDateTime_(zonedDateTime1).delete.transact
        _ <- Ns.uuid_(uuid1).delete.transact
        _ <- Ns.uri_(uri1).delete.transact
        _ <- Ns.byte_(byte1).delete.transact
        _ <- Ns.short_(short1).delete.transact
        _ <- Ns.char_(char1).delete.transact

        // i == 1 has been deleted
        _ <- Ns.i.string_.query.get.map(_ ==> List(2))
        _ <- Ns.i.int_.query.get.map(_ ==> List(2))
        _ <- Ns.i.long_.query.get.map(_ ==> List(2))
        _ <- Ns.i.float_.query.get.map(_ ==> List(2))
        _ <- Ns.i.double_.query.get.map(_ ==> List(2))
        _ <- Ns.i.boolean_.query.get.map(_ ==> List(2))
        _ <- Ns.i.bigInt_.query.get.map(_ ==> List(2))
        _ <- Ns.i.bigDecimal_.query.get.map(_ ==> List(2))
        _ <- Ns.i.date_.query.get.map(_ ==> List(2))
        _ <- Ns.i.duration_.query.get.map(_ ==> List(2))
        _ <- Ns.i.instant_.query.get.map(_ ==> List(2))
        _ <- Ns.i.localDate_.query.get.map(_ ==> List(2))
        _ <- Ns.i.localTime_.query.get.map(_ ==> List(2))
        _ <- Ns.i.localDateTime_.query.get.map(_ ==> List(2))
        _ <- Ns.i.offsetTime_.query.get.map(_ ==> List(2))
        _ <- Ns.i.offsetDateTime_.query.get.map(_ ==> List(2))
        _ <- Ns.i.zonedDateTime_.query.get.map(_ ==> List(2))
        _ <- Ns.i.uuid_.query.get.map(_ ==> List(2))
        _ <- Ns.i.uri_.query.get.map(_ ==> List(2))
        _ <- Ns.i.byte_.query.get.map(_ ==> List(2))
        _ <- Ns.i.short_.query.get.map(_ ==> List(2))
        _ <- Ns.i.char_.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Semantics" - {

      "Only tacit attributes" - refs { implicit conn =>
        for {
          _ <- A.i.<=(int1).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes (A.i)."
            }

          _ <- A.iSet(Set(int1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes (A.iSet)."
            }

          _ <- A.iSeq(List(int1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes (A.iSeq)."
            }

          _ <- A.iMap(Map(pint1)).delete.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can only filter delete by values applied to tacit card-one attributes (A.iMap)."
            }
        } yield ()
      }
    }
  }
}
