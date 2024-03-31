package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      val a = (1, Set(ref1, ref2))
      val b = (2, Set(ref2, ref3, ref4))
      for {
        _ <- Ns.i.refs.insert(List(a, b)).transact

        // Sets without one or more values matching

//        // "Doesn't have this value"
//        _ <- Ns.i.a1.refs.hasNo(ref0).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.refs.hasNo(ref1).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.refs.hasNo(ref2).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(ref3).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs.hasNo(ref4).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs.hasNo(ref5).query.get.map(_ ==> List(a, b))
//        // Same as
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref0)).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref1)).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref2)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref3)).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref4)).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref5)).query.get.map(_ ==> List(a, b))


        // OR semantics when multiple values

        // "Has neither this OR that"
        _ <- Ns.i.a1.refs.hasNo(ref1, ref2).query.i.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(ref1, ref3).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(ref1, ref4).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(ref1, ref5).query.get.map(_ ==> List(b))
//        // Same as
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(b))
//
//
//        // Negating empty Seqs/Sets has no effect
//        _ <- Ns.i.a1.refs.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {

        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i)).insert(
          (0, Nil),
          (1, List(
            (1, 1, Nil),
          )),
          (2, List(
            (1, 1, Nil),
            (2, 2, List((1, 2))),
            (3, 3, List((1, 2), (3, 4))),
          )),
        ).transact

        _ <- A.i.a1.Bb.*?(B.i.a1.C.i.Dd.*?(D.i.a1.E.i)).query.get.map(_ ==> List(
          (0, Nil),
          (1, List(
            (1, 1, Nil),
          )),
          (2, List(
            (1, 1, Nil),
            (2, 2, List((1, 2))),
            (3, 3, List((1, 2), (3, 4))),
          )),
        ))

        _ <- A.i.Bb.*(B.i.a1.C.i.Dd.*(D.i.a1.E.i)).query.get.map(_ ==> List(
          (2, List(
            (2, 2, List((1, 2))),
            (3, 3, List((1, 2), (3, 4))),
          )),
        ))


        //        _ <- A.i.Bb.*(B.i.C.iSet).insert(
        //          (0, Nil),
        //          (1, List(
        //            (1, Set.empty[Int])
        //          )),
        //          (2, List(
        //            (1, Set.empty[Int]),
        //            (2, Set(0)),
        //            (3, Set(1, 2)),
        //          )),
        //        ).transact
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.i,
        //            |  JSON_ARRAYAGG(t_1.vs)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id
        //            |  LEFT JOIN C      ON B.c         = C.id,
        //            |  JSON_TABLE(
        //            |    IF(C.iSet IS NULL, '[null]', C.iSet),
        //            |    '$[*]' COLUMNS (vs INT PATH '$')
        //            |  ) t_1
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.id, A.i
        //            |HAVING COUNT(*) > 0
        //            |ORDER BY A.i;
        //            |""".stripMargin, true)
        //
        //        _ <- A.i.a1.Bb.*?(B.C.iSet).query.i.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, Nil),
        //          (2, List(
        //            Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        //          )),
        //        ))

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


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}