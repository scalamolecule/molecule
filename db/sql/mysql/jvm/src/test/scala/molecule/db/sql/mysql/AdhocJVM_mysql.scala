package molecule.db.sql.mysql

import molecule.core.util.Executor.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.db.sql
import molecule.db.sql.mysql.async.*
import molecule.db.sql.mysql.setup.DbProviders_mysql
import scala.language.implicitConversions

class AdhocJVM_mysql extends Test with DbProviders_mysql with TestUtils {


  "types" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types.*
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {


      id <- Entity.i(42).save.transact.map(_.id)

      // Map attribute not yet asserted
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intMap(Map(pint1, pint2)).update.i.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

    } yield ()
  }


  "refs" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {

      //        _ <- A.i.B.?(B.iSet).insert(
      //          (0, None),
      //          (1, Some(Set(1, 2))),
      //        ).transact
      //
      //        _ <- A.i.B.?(B.iSet).query.i.get.map(_ ==> List(
      //          (0, None),
      //          (1, Some(Set(1, 2))),
      //        ))
      //
      _ <- A.i.B.?(B.iSeq).insert(
        (0, None),
        (1, Some(Seq(1, 2, 1))),
      ).transact

      _ <- A.i.B.?(B.iSeq).query.i.get.map(_ ==> List(
        (0, None),
        (1, Some(Seq(1, 2, 1))),
      ))

      //        _ <- A.i.B.?(B.iMap).insert(
      //          (0, None),
      //          (1, Some(Map("a" -> 1, "b" -> 2))),
      //        ).transact
      //
      //        _ <- A.i.B.?(B.iMap).query.i.get.map(_ ==> List(
      //          (0, None),
      //          (1, Some(Map("a" -> 1, "b" -> 2))),
      //        ))


      //        _ <- rawTransact(
      //          """UPDATE B
      //            |SET
      //            |  iSet = JSON_REMOVE(iSet, JSON_UNQUOTE(JSON_SEARCH(iSet, 'one', '3')))
      //            |WHERE
      //            |  B.iSet is not null and
      //            |  B.id IN(1, 2, 3)
      //            |""".stripMargin)

      //        _ <- rawQuery(
      //          """SELECT iSeq,
      //            |  (
      //            |      SELECT JSON_ARRAYAGG(list.v)
      //            |      FROM   JSON_TABLE('[1,2,3]', '$[*]' COLUMNS (v INT PATH '$')) as list
      //            |    ) as x
      //            |FROM B
      //            |""".stripMargin, true)


    } yield ()
  }


  //    "unique" - unique { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Uniques._
  //      for {
  //        _ <- Uniques.i(1).save.transact
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Validation._
  //      for {
  //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
  //
  //      } yield ()
  //    }

}