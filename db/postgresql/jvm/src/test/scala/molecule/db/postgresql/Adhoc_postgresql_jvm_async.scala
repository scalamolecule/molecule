package molecule.db.postgresql

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.postgresql.async.*
import molecule.db.postgresql.setup.DbProviders_postgresql
import org.scalactic.Equality


class Adhoc_postgresql_jvm_async extends MUnit with DbProviders_postgresql with TestUtils {


  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)



    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (1, int3),
        (2, int4),
      ).transact
//      _ <- Entity.i.long.insert((1, long1), (1, long2), (1, long3), (2, long4)).transact
//      _ <- Entity.i.float.insert((1, float1), (1, float2), (1, float3), (2, float4)).transact
//      _ <- Entity.i.double.insert((1, double1), (1, double2), (1, double3), (2, double4)).transact
//      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt3), (2, bigInt4)).transact
//      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal3), (2, bigDecimal4)).transact
//      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (1, byte3), (2, byte4)).transact
//      _ <- Entity.i.short.insert((1, short1), (1, short2), (1, short3), (2, short4)).transact

      _ <- Entity.i.int(median).a1.query.i.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.long(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.float(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.double(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.bigInt(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.bigDecimal(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.byte(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//      _ <- Entity.i.short(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
//
//      _ <- Entity.i.int(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.long(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.float(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.double(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.bigInt(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.bigDecimal(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.byte(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
//      _ <- Entity.i.short(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))


      //      _ <- rawQuery(
      //        s"""SELECT DISTINCT
      //           |  Entity.i,
      //           |  VAR_POP(Entity.int)
      //           |FROM Entity
      //           |WHERE
      //           |  Entity.i IS NOT NULL
      //           |GROUP BY Entity.i
      //           |having round(VAR_POP(Entity.int), 10) = round(0.6666666666666666, 10)
      //           |ORDER BY Entity.i NULLS FIRST
      //           |
      //           |;
      //           |""".stripMargin, true
      //      ).map(_ ==> 42)


    } yield ()
  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
  //
  //    for {
  //
  //      _ <- A.i.insert(1).transact
  //      _ <- A.i.B.i.insert((2, 20), (3, 30)).transact
  //
  //      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
  //      _ <- A.i.a1.B.i.query.get.map(_ ==> List((2, 20), (3, 30)))
  //
  //      // Nothing deleted since entity 1 doesn't have a ref
  //      _ <- A.i_(1).B.i_.delete.transact
  //      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
  //
  //      // Second entity has a ref and will be deleted
  //      _ <- A.i_(2).B.i_.delete.i.transact
  //      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
  //
  //    } yield ()
  //  }


  //    "unique" - unique {
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //      for {
  //        _ <- Uniques.i(1).save.transact
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        _ <- Type.string.insert("a").transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case InsertErrors(errors, _) =>
  //              errors.head._2.head.errors.head ==>
  //                s"""Type.string with value `a` doesn't satisfy validation:
  //                   |_ > "b"
  //                   |""".stripMargin
  //          }
  //      } yield ()
  //    }
}