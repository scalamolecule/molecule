package molecule.db.postgresql

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import async.*
import molecule.db.postgresql.setup.DbProviders_postgresql


class Adhoc_postgresql_jvm_async extends MUnit with DbProviders_postgresql with TestUtils {

  "types" - types {
    import molecule.db.compliance.domains.dsl.Types.*
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {

//      id <- Entity.i(42).save.transact.map(_.id)
//
//      // Map attribute not yet asserted
//      _ <- Entity.intMap.query.get.map(_ ==> Nil)
//
//      // When attribute is not already asserted, an update has no effect
//      _ <- Entity(id).intMap(Map(pint1, pint2)).update.i.transact
//      _ <- Entity.intMap.query.get.map(_ ==> Nil)


      _ <- Entity.uuid.insert(uuid1, uuid2, uuid3).transact

      _ <- Entity.uuid(uuid1).a1.query.i.get
      eq = Entity.uuid(?).a1.query.i
      _ <- eq(uuid1).get.map(_ ==> List(uuid1))
      _ <- eq(uuid2).get.map(_ ==> List(uuid2))
      _ <- eq(uuid3).get.map(_ ==> List(uuid3))

      ne = Entity.uuid.not(?).a1.query
      _ <- ne(uuid1).get.map(_ ==> List(uuid2, uuid3))
      _ <- ne(uuid2).get.map(_ ==> List(uuid1, uuid3))
      _ <- ne(uuid3).get.map(_ ==> List(uuid1, uuid2))

      lt = Entity.uuid.<(?).a1.query
      _ <- lt(uuid1).get.map(_ ==> List())
      _ <- lt(uuid2).get.map(_ ==> List(uuid1))
      _ <- lt(uuid3).get.map(_ ==> List(uuid1, uuid2))

      le = Entity.uuid.<=(?).a1.query
      _ <- le(uuid1).get.map(_ ==> List(uuid1))
      _ <- le(uuid2).get.map(_ ==> List(uuid1, uuid2))
      _ <- le(uuid3).get.map(_ ==> List(uuid1, uuid2, uuid3))

      gt = Entity.uuid.>(?).a1.query
      _ <- gt(uuid1).get.map(_ ==> List(uuid2, uuid3))
      _ <- gt(uuid2).get.map(_ ==> List(uuid3))
      _ <- gt(uuid3).get.map(_ ==> List())

      ge = Entity.uuid.>=(?).a1.query
      _ <- ge(uuid1).get.map(_ ==> List(uuid1, uuid2, uuid3))
      _ <- ge(uuid2).get.map(_ ==> List(uuid2, uuid3))
      _ <- ge(uuid3).get.map(_ ==> List(uuid3))
    } yield ()
  }


//  "refs" - refs {
//    import molecule.db.compliance.domains.dsl.Refs.*
//    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
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