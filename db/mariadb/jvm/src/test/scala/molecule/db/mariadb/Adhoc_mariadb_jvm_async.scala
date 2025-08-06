package molecule.db.mariadb

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.mariadb.async.*
import molecule.db.mariadb.setup.DbProviders_mariadb
import org.scalactic.Equality


class Adhoc_mariadb_jvm_async extends MUnit with DbProviders_mariadb with TestUtils {

  override lazy val (float1, float2, float3, float4)                     = (1.0f, 2.0f, 3.0f, 4.0f)
  override lazy val (double1, double2, double3, double4)                 = (1.0, 2.0, 3.0, 4.0)
  override lazy val (bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
      (BigDecimal(1.0), BigDecimal(2.0), BigDecimal(3.0), BigDecimal(4.0))

  "types" - types {
//    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)

    //    for {
    //      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
    //      _ <- Entity.int(3).save.transact
    //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
    //      _ <- Entity(a).int(10).update.transact
    //      _ <- Entity(b).delete.transact
    //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))



    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (1, long2), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float2), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double2), (2, double2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (2, bigDecimal2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short2), (2, short2)).transact

      _ <- Entity.i.int(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.long(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.float(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.double(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.bigInt(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.bigDecimal(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.byte(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.short(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))

      _ <- Entity.i.int(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.long(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.float(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.double(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.bigInt(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.bigDecimal(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.byte(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.short(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))


    } yield ()
  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //
  //      _ <- A.i.B.?(B.iMap).insert(
  //        (0, None),
  //        (1, Some(Map("a" -> 1, "b" -> 2))),
  //      ).transact
  //
  //      _ <- A.i.B.?(B.iMap).query.i.get.map(_ ==> List(
  //        (0, None),
  //        (1, Some(Map("a" -> 1, "b" -> 2))),
  //      ))
  //
  //    } yield ()
  //  }


  //    "unique" - unique {
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //
  //
  //      for {
  //

  //
  //
  //
  //        _ <- Uniques.int.insert(1, 2).transact
  //
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        // We can remove a value from a Set as long as it's not the last value
  //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
  //
  //        //        // Can't remove the last value of a mandatory attribute Set of values
  //        //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
  //        //          .map(_ ==> "Unexpected success").recover {
  //        //            case ModelError(error) =>
  //        //              error ==>
  //        //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //        //                  |  MandatoryAttr.hobbies
  //        //                  |""".stripMargin
  //        //          }
  //
  //      } yield ()
  //    }
}