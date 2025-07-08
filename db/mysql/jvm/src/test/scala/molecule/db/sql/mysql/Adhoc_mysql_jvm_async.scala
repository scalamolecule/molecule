package molecule.db.sql.mysql

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.sql.mysql.async.*
import molecule.db.sql.mysql.setup.DbProviders_mysql


class Adhoc_mysql_jvm_async extends MUnit with DbProviders_mysql with TestUtils {


  "types" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*
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

//
//  "refs" - refs { implicit conn =>
//    import molecule.db.compliance.domains.dsl.Refs.*
//    for {
//
//      _ <- A.i.B.?(B.iSeq).insert(
//        (0, None),
//        (1, Some(Seq(1, 2, 1))),
//      ).transact
//
//      _ <- A.i.B.?(B.iSeq).query.i.get.map(_ ==> List(
//        (0, None),
//        (1, Some(Seq(1, 2, 1))),
//      ))
//    } yield ()
//  }


  //    "unique" - unique { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //      for {
  //        _ <- Uniques.i(1).save.transact
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
  //
  //      } yield ()
  //    }

}