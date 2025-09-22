package molecule.db.sqlite

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.sqlite.async.*
import molecule.db.sqlite.setup.DbProviders_sqlite
import org.scalactic.Equality
import scala.concurrent.Future


class Adhoc_sqlite_jvm_async extends MUnit with DbProviders_sqlite with TestUtils {


  //  "types" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
  //    for {
  //      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
  //      _ <- Entity.int(3).save.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
  //      _ <- Entity(a).int(10).update.transact
  //      _ <- Entity(b).delete.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
  //
  //    } yield ()
  //  }



  "refs" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      //      _ <- A.i.insert(1).transact
      //      _ <- A.i.query.get.map(_ ==> List(1))


      e1 <- A.i.Bb.*(B.i).insert(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ).transact.map(_.id)

      // 2 entities, each with 2 sub-entities
      _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ))

      // 4 referenced entities
      _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

      // Referential integrity constraint on fk prevents orphaning B children
      _ <- A(e1).delete.transact.map(_ ==> "Unexpected success").recover {
        case e: Exception =>
          e.getMessage ==> (database match {
            case "sqlite" => "[SQLITE_CONSTRAINT_FOREIGNKEY] A foreign key constraint failed (FOREIGN KEY constraint failed)"
            case "h2"     =>
              """Referential integrity constraint violation: "_A_2: PUBLIC.B FOREIGN KEY(A) REFERENCES PUBLIC.A(ID) (CAST(1 AS BIGINT))"; SQL statement:
                |DELETE FROM A
                |WHERE
                |  A.id = 1 [23503-232]""".stripMargin
          })
      }

      // No data deleted
      _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ))

    } yield ()
  }


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
