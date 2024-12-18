package molecule.sql.h2

import java.lang.Exception
import molecule.base.error.ValidationErrors
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions
import scala.util.control.{Exception, NonFatal}


//object AdhocJVM_h2 extends TestSuite_h2_array {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      println("hello".substring(5, 5))
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))


      } yield ()
    }


    "mixed" - types { implicit conn =>
      for {
        _ <- transact(
          Ns.int(1).save, //         List(1)
          Ns.int.insert(2, 3), //    List(1, 2, 3)
          Ns(1).delete, //           List(2, 3)
          Ns(3).int.*(10).update, // List(2, 30)
        )
        _ <- Ns.int.query.get.map(_ ==> List(2, 30))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Refs._
      for {
        //        _ <- A.i.insert(1, 2).transact
        ////        _ <- A.i_(1).delete.i.transact
        //        _ <- A(1).delete.i.transact
        ////        _ <- rawTransact("DELETE FROM A WHERE id IN (1)", true)
        //        _ <- A.i.query.get.map(_ ==> List(2))




        _ <- A.i.insert(1).transact
        _ <- A.i.OwnB.i.insert((2, 20), (3, 30)).transact

        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- A.i_(1).OwnB.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- A.i_(2).OwnB.i_.delete.i.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

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

    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Validation._

      for {

        //        _ <- Type.int(1).save.transact
        //          .recover {
        //            case ValidationErrors(errorMap) =>
        //              errorMap.head._2.head ==>
        //                s"""Type.int with value `1` doesn't satisfy validation:
        //                   |_ > 2
        //                   |""".stripMargin
        //          }

        //        _ <- Type.int(3).save.transact
        //        _ <- transact(Type.int(3).save, Type.int(5).save)
        //                _ <- transact(Type.int(1).save, Type.int.insert(5, 6)).recover {
        _ <- transact(
          Type.int.insert(5, 6),
          Type.int(1).save
        ).recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }

        _ <- unitOfWork {
          for {
            last <- Type.int.insert(5, 6).transact
            last <- Type.int(1).save.transact
          } yield last
        }.recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }

        // No data has been transacted
        _ <- Type.int.query.get.map(_ ==> List())
        //        _ <- Type.int.query.get.map(_ ==> List(3, 5))

      } yield ()
    }
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
