package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      for {

        _ <- Ns.int.insert(int1, int2, int3).transact
        _ <- Ns.int_(int0).delete.i.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2, int3))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {


        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint1, pint2, pint3)).save.transact

        //        // Current entity with A value and ref to B value
        //        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        //          (3, Map(pint1, pint2, pint3))
        //        ))
        //
        // Filter by A attribute, add B pairs with update
        _ <- A.i_.B.iMap.add(pint20, pint3, pint4).update.i.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(
            pint1,
            pint20, // key unchanged, value updated
            pint3, //  pair already existed, so no change
            pint4 //   new pair added
          ))
        ))

        //        // Filter by A attribute, add B pairs with upsert
        //        _ <- A.i_.B.iMap.add(pint30, pint4, pint5).upsert.transact
        //
        //        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        //          (1, Map(pint30, pint4, pint5)), // relationship to B created, pairs added
        //          (2, Map(pint30, pint4, pint5)), // pairs added
        //          (3, Map(
        //            pint1,
        //            pint20,
        //            pint30, // key unchanged, value updated
        //            pint4, //  pair already existed, so no change
        //            pint5 //   new pair added
        //          )),
        //        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A.i_.Bb.iSeq(Seq(5, 6)).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
        //          (1, List((None, Seq(5, 6)))), //                              ref + addition
        //          (2, List((Some("a"), Seq(5, 6)))), //                         addition in 1 ref entity
        //          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), // addition in 2 ref entities
        //          (4, List((Some("d"), Seq(5, 6)))), //                         update in 1 ref entity
        //          (5, List((Some("e"), Seq(5, 6)), (Some("f"), Seq(5, 6)))), // update in 2 ref entities
        //          (6, List((Some("g"), Seq(5, 6)), (Some("h"), Seq(5, 6)))), // update in one ref entity and addition in another
        //        ))

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSeq, '$[*]' COLUMNS (v INT PATH '$')) as list
        //            |      WHERE  list.v NOT IN (3, 4) and B.id is not null
        //            |    )
        //            |WHERE
        //            |  B.iSeq is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      val attr = database match {
        case "Mysql" => "string_"
        case _       => "string"
      }


      for {

        //            _ <- Uniques.i(1).i(2).int_(1).update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can't transact duplicate attribute Uniques.i"
        //              }
        //
        //            _ <- Uniques.i_(1).i(2).update.transact
        //
        //            _ <- Uniques.int_(1).string_("x").s("c").update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can only apply one unique attribute value for update. Found:\n" +
        //                  s"""AttrOneTacString("Uniques", "$attr", Eq, Seq("x"), None, None, Nil, Nil, None, None, Seq(0, 3))"""
        //              }
        //
        //            _ <- Uniques.intSet_(1).s("b").update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        //                  """AttrSetTacInt("Uniques", "intSet", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 25))"""
        //              }


        //
        //            _ <- Uniques.int.insert(1, 2, 3).transact
        //
        //            c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
        //
        //            // Turning around with first cursor leads nowhere
        //            _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }




        _ <- Uniques.int.insert(1, 2).transact


      } yield ()
    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        _ <- Type.string("a").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        _ <- Type.string.insert("a").transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        id <- Type.string("c").save.transact.map(_.id)
        _ <- Type(id).string("a").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

      } yield ()
    }
  }
}