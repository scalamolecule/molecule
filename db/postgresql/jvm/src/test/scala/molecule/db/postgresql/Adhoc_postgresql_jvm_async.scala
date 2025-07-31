package molecule.db.postgresql

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.postgresql.async.*
import molecule.db.postgresql.setup.DbProviders_postgresql
import org.scalactic.Equality


class Adhoc_postgresql_jvm_async extends MUnit with DbProviders_postgresql with TestUtils {


  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      id <- Entity.i(42).save.transact.map(_.id)
    } yield ()
  }


//  "refs" - refs {
//    import molecule.db.compliance.domains.dsl.Refs.*
//    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
//
//    for {
//
//      _ <- A.i.insert(1).transact
//      _ <- A.i.B.i.insert((2, 20), (3, 30)).transact
//
//      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- A.i.a1.B.i.query.get.map(_ ==> List((2, 20), (3, 30)))
//
//      // Nothing deleted since entity 1 doesn't have a ref
//      _ <- A.i_(1).B.i_.delete.transact
//      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
//
//      // Second entity has a ref and will be deleted
//      _ <- A.i_(2).B.i_.delete.i.transact
//      _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
//
//    } yield ()
//  }


  //    "unique" - unique {
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //      for {
  //        _ <- Uniques.i(1).save.transact
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        _ <- Type.string.insert("a").transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case InsertErrors(errors, _) =>
  //              errors.head._2.head.errors.head ==>
  //                s"""Type.string with value `a` doesn't satisfy validation:
  //                   |_ > "b"
  //                   |""".stripMargin
  //          }
  //      } yield ()
  //    }
}