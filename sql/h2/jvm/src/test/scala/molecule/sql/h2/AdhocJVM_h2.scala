package molecule.sql.h2

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuite_h2_array, TestSuite_h2}
import utest._
import scala.collection.mutable
import scala.concurrent.Future
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuite_h2_array {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {
        //        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        //        _ <- Ns.int(3).save.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        //        _ <- Ns(a).int(10).update.transact
        //        _ <- Ns(b).delete.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

        _ <- Ns.i.int.insert(
          (-2, -int2),
          (-1, -int1),
          (0, int0),
          (1, int1),
          (2, int2),
        ).transact

        // Mandatory
        _ <- Ns.int.even.query.get.map(_ ==> List(-int2, int0, int2))
        _ <- Ns.int.odd.query.get.map(_ ==> List(-int1, int1))

        // Tacit
        _ <- Ns.i.int_.even.query.get.map(_ ==> List(-2, 0, 2))
        _ <- Ns.i.int_.odd.query.get.map(_ ==> List(-1, 1))

//        // Mandatory
//        _ <- Ns.int.even.query.get.map(_ ==> List(int2, int4))
//        _ <- Ns.int.odd.query.get.map(_ ==> List(int1, int3, int5))
//
//        // Tacit
//        _ <- Ns.i.int_.even.query.get.map(_ ==> List(2, 4))
//        _ <- Ns.i.int_.odd.query.get.map(_ ==> List(1, 3, 5))

      } yield ()
    }

    /*

    _ <- if (database == "SQlite" && int1.isInstanceOf[BigInt]) {
          // Generated tests for `BigInt`s are not sorted correctly since
          // `BigInt`s have to be saved as Text in SQlite and therefore
          // sort lexicographically which gives wrong numeric order.
          // So in this special case we simply sort the output:
          Ns.int.query.get.map(_.sorted.reverse ==> List(-int1, -int2))
        } else {
          // In all other cases, normal correct sorting applies
          Ns.int.d1.query.get.map(_ ==> List(-int1, -int2))
        }
     */

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Refs._
      for {

        refId <- B.i(7).save.transact.map(_.id)
        id <- A.i.b.insert(1, refId).transact.map(_.id)
        _ <- A.i.b.query.get.map(_ ==> List((1, refId)))

        // Apply empty value to delete ref id of entity (entity remains)
        _ <- A(id).b().update.i.transact
        _ <- A.i.b_?.query.get.map(_ ==> List((1, None)))

        //
        //        //        _ <- rawQuery(
        //        //          """SELECT DISTINCT
        //        //            |  A.i,
        //        //            |  B.i
        //        //            |FROM A
        //        //            |  LEFT JOIN B ON
        //        //            |    A.b = B.id
        //        //            |WHERE
        //        //            |  A.i IS NOT NULL;
        //        //            |""".stripMargin, true)
        //
        //        _ <- A.i.B.?(B.i).query.i.get.map(_ ==> List(
        //          //          (1, None),
        //          (2, Some(3)),
        //        ))


        //        _ <- A.i.B.i.query.get.map(_ ==> List(
        //          (2, 3),
        //        ))

        //        _ <- rawQuery(
        //          """select count(*) from Ns
        //            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |""".stripMargin, true)
        //
        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  i = ?
        //            |WHERE
        //            |  i IS NOT NULL AND
        //            |  B.id IN(42)
        //            |""".stripMargin)
      } yield ()
    }



    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Uniques._
    //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Validation._
    //      for {
    //        id <- MandatoryAttr.name("Bob").age(42)
    //          .hobbies(Set("golf")).save.transact.map(_.id)
    //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //
    //        _ = println("+++++++++++++++++++++")
    //        //        // We can remove a value from a Set as long as it's not the last value
    //        //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
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
    //    "partitions" - partition { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Partitions._
    //      for {
    //
    //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
    //          .insert("book", "Jan", List("Musician")).transact
    //
    //      } yield ()
    //    }
  }
}
