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
////        _ <- Ns.int(3).save.transact
////        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
//        _ <- Ns(a).int(10).update.i.transact
////        _ <- Ns.int_(10).int(11).update.i.transact
////        _ <- Ns(b).delete.transact
////        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))
//
//
//
////        _ <- Ns(a).int.>(10).update.inspect
//        _ <- Ns(a).int.+(7).update.i.transact
//        _ <- Ns(a).boolean.&&(true).update.i.transact
//        _ <- Ns(a).boolean.||(true).update.i.transact
//        _ <- Ns(a).boolean.!.update.i.transact
//
//        _ <- Ns.int.a1.query.get.map(_ ==> List(2, 17))



//        ids <- Ns.string.insert("Hello", "World").transact.map(_.ids)
//        _ <- Ns(ids).string.toUpper.update.transact
//        _ <- Ns.string.a1.query.get.map(_ ==> List("HELLO", "WORLD"))

        _ <- Ns.bigInt(BigInt("123456789012345678901234567890")).save.transact
        _ <- Ns.bigInt.query.get.map(_.head ==> BigInt("123456789012345678901234567890"))

      } yield ()
    }


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
