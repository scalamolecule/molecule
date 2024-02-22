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

      val all = Set(duration1, duration2, duration3, duration4)
      for {
        _ <- Ns.duration.insert(List(duration1, duration2, duration3)).transact
        _ <- Ns.duration(sample).query.get.map(res => all.contains(res.head) ==> true)

        //        _ <- Ns.i.ii.ints.insert(a, b, c).transact
        //
        ////        _ <- rawQuery(
        ////          """SELECT DISTINCT
        ////            |  Ns.i,
        ////            |  Ns.ii,
        ////            |  JSON_ARRAYAGG(t_3.vs)
        ////            |FROM Ns,
        ////            |  JSON_TABLE(
        ////            |    IF(Ns.ints IS NULL, '[null]', Ns.ints),
        ////            |    '$[*]' COLUMNS (vs INT PATH '$')
        ////            |  ) t_3
        ////            |WHERE
        ////            |  Ns.ii   = Ns.ints AND
        ////            |  Ns.i    IS NOT NULL AND
        ////            |  Ns.ii   IS NOT NULL AND
        ////            |  Ns.ints IS NOT NULL
        ////            |GROUP BY Ns.i, Ns.ii
        ////            |HAVING COUNT(*) > 0;
        ////            |""".stripMargin, true)
        //
        //        _ <- Ns.i.ii(Ns.ints).query.i.get.map(_ ==> List(b))


//        id <- Ns.ints.insert(Set(1)).transact.map(_.id)
//        _ <- Ns.ints.query.get.map(_ ==> List(Set(1)))
//
//        _ <- Ns(id).ints(Set(2)).update.transact
//        _ <- Ns.ints.query.get.map(_ ==> List(Set(2)))
//
//        // Updating a non-asserted attribute has no effect
//        _ <- Ns(id).strings(Set("a")).update.transact
//        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), None)))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {

        List(a, b, c, d) <- A.i.ii_?.insert(
          (1, None),
          (1, Some(Set(2))),
          (1, Some(Set(3))),
          (2, Some(Set(4, 5))),
        ).transact.map(_.ids)

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  JSON_ARRAYAGG(DISTINCT t_1.vs)
            |FROM A
            |  LEFT OUTER JOIN JSON_TABLE(A.ii, '$[*]' COLUMNS (vs INT PATH '$')) t_1 ON true
            |WHERE
            |  A.i IS NOT NULL
            |GROUP BY A.i
            |ORDER BY A.i;
            |""".stripMargin, true)

        _ <- A.i.a1.ii_?.query.i.get.map(_ ==> List( // (since we can't sort by Sets)
          // (1, None), // coalesced with Set(2) and Set(3)
          (1, Some(Set(2, 3))), // coalesced Set(2) and Set(3)
          (2, Some(Set(4, 5))),
        ))



        //        _ <- A.i.Bb.*(B.i.C.ii).insert(
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
        //            |    IF(C.ii IS NULL, '[null]', C.ii),
        //            |    '$[*]' COLUMNS (vs INT PATH '$')
        //            |  ) t_1
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.id, A.i
        //            |HAVING COUNT(*) > 0
        //            |ORDER BY A.i;
        //            |""".stripMargin, true)
        //
        //        _ <- A.i.a1.Bb.*?(B.C.ii).query.i.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, Nil),
        //          (2, List(
        //            Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        //          )),
        //        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}