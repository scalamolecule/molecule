package molecule.db.sqlite

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.sqlite.async.*
import molecule.db.sqlite.setup.DbProviders_sqlite
import org.scalactic.Equality


class Adhoc_sqlite_jvm_async extends MUnit with DbProviders_sqlite with TestUtils {


  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      //        List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      //        _ <- Entity.int(3).save.transact
      //        _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      //        _ <- Entity(a).int(10).update.transact
      //        _ <- Entity(b).delete.transact
      //        _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10)){

      //        _ <- Entity.i(1).float(float1).save.transact
      //        _ <- Entity.i(2).float_(float1).update.i.transact
      //        _ <- Entity.i.float_.query.get.map(_.head ==> 2)



//      _ <- Entity.i.double.insert((1, double1), (2, double2)).transact
//      _ <- Entity.i.double.query.get.map { res =>
//        println(res.head._2.getClass)
//        res ==> List((1, double1), (2, double2))
//      }
//      _ <- rawTransact(
//        """DELETE FROM Entity
//          |WHERE
//          |  Entity.double = 1.1""".stripMargin, true
//      )
//      //        _ <- Entity.double_(double1).delete.i.transact
//      _ <- Entity.i.double_.query.get.map(_ ==> List(2))
//
//      _ <- Entity.i.double.query.get.map { res =>
//        println(res.head._2.getClass)
//        res ==> List((2, double2))
//      }


      _ <- Entity.i.float.insert((1, float1), (2, float2)).transact
      _ <- Entity.i.float.query.get.map { res =>
        println(res.head._2.getClass)
        res ==> List((1, float1), (2, float2))
      }
      _ <- rawTransact(
        """DELETE FROM Entity
          |WHERE
          |  Entity.float = 1.1""".stripMargin, true
      )
      ////        _ <- Entity.float_(float1).delete.i.transact

      _ <- Entity.i.float.query.get.map { res =>
        println(res.head._2.getClass)
        res ==> List((2, float2))
      }
      //        _ <- Entity.i.float_.query.get.map(_ ==> List(2))

    } yield ()
  }



  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //      //      _ <- A.i.insert(1).transact
  //      //      _ <- A.i.query.get.map(_ ==> List(1))
  //
  //      _ <- A.i.B.s.insert(
  //        (1, "a"),
  //        (2, "b"),
  //      ).i.transact
  //
  //    } yield ()
  //  }
  //
  //
  //  //    "unique" - unique {
  //  //      import molecule.db.compliance.domains.dsl.Uniques._
  //  //      //          val triples             = getTriples.map(t => (t._3, t._1, t._2))
  //      //          val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
  //      //          val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
  //      for {
  //
  //
  //        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
  //        //        _ <- Uniques.i.float.insert((1, float1)).transact
  //        //        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
  //        //            _ <- Uniques.i.float.insert((2, float2)).transact
  //
  //        _ <- Uniques.i.float.query.get.map(_ ==> List(
  //          (1, 1.1f),
  //          (2, 2.2f),
  //        ))
  //
  //      } yield ()
  //    }


  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        // We can remove a value from a Set as long as it's not the last value
  //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
  //
  //        // Can't remove the last value of a mandatory attribute Set of values
  //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                  |  MandatoryAttr.hobbies
  //                  |""".stripMargin
  //          }
  //
  //      } yield ()
  //    }
  //
  //    "partitions" - partition {
  //      import molecule.db.compliance.domains.dsl.Groups._
  //      for {
  //
  //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
  //          .insert("book", "Jan", List("Musician")).transact
  //
  //      } yield ()
  //    }
}
