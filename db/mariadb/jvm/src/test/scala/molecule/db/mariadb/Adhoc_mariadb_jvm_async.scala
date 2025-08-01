package molecule.db.mariadb

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.Entity
import async.*
import molecule.db.mariadb.setup.DbProviders_mariadb
import org.scalactic.Equality


class Adhoc_mariadb_jvm_async extends MUnit with DbProviders_mariadb with TestUtils {

  "types" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)

    //    for {
    //      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
    //      _ <- Entity.int(3).save.transact
    //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
    //      _ <- Entity(a).int(10).update.transact
    //      _ <- Entity(b).delete.transact
    //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


    val avgAll = ((double1 + double2 + double2 + double3 + double4).toDouble * 100 / 500.0)
    val avg1   = ((double1 + double2).toDouble * 100 / 200.0)
    val avg2   = ((double2 + double3 + double4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double3),
        (2, double4),
      )).transact

      // 1 attribute
      _ <- Entity.double(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.double(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.double(avg)(avg1).query.i.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).>=(avg2).query.get.map(_ ==> List(b))


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