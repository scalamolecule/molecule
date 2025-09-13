package molecule.db.postgresql

import java.net.URI
import java.util.UUID
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.postgresql.async.*
import molecule.db.postgresql.setup.DbProviders_postgresql
import org.scalactic.Equality


class Adhoc_postgresql_jvm_async extends MUnit with DbProviders_postgresql with TestUtils {

  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {

      //      _ <- Entity.uuid(uuid1).save.i.transact
//      _ <- rawTransact(
//        """INSERT INTO Entity (
//          |      uuid
//          |    ) VALUES (('00000000-0000-0000-0000-000000000001'))""".stripMargin, true
//      )
//      _ <- Entity.uuid.insert(uuid1).transact

      _ <- Entity.i(1).uuid_(uuid1).save.i.transact
//      _ <- Entity.uuid(uuid1).save.i.transact
    } yield ()
  }


//    "refs" - refs {
//      import molecule.db.compliance.domains.dsl.Refs.*
//      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
//
//      for {
//
////        _ <- rawTransact(
////          """INSERT INTO B (
////            |  s,
////            |  iMap,
////            |  a
////            |) VALUES (('d'), ('{"a": 1, "b": 2}'), ('1'::int8))""".stripMargin
////        )
//
//        case List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
//          (1, List()),
//          (2, List((Some("a"), None))),
//          (3, List((Some("b"), None), (Some("c"), None))),
//          (4, List((Some("d"), Some(Map(pint1, pint2))))),
//          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
//          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
//        ).transact.map(_.ids)
//
//        // Filter by A ids, update B values
//        _ <- A(a, b, c, d, e, f).Bb.iMap(Map(pint4, pint5)).update.i.transact
//
//
//      } yield ()
//    }


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
  //        _ <- Tpe.string.insert("a").transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case InsertErrors(errors, _) =>
  //              errors.head._2.head.errors.head ==>
  //                s"""Tpe.string with value `a` doesn't satisfy validation:
  //                   |_ > "b"
  //                   |""".stripMargin
  //          }
  //      } yield ()
  //    }
}