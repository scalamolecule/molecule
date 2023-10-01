package molecule.sql.mysql.test

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.mysql.async._
import molecule.sql.mysql.setup.TestSuite_mysql
import utest._
import scala.collection.immutable.{List, Seq, Set}
import scala.language.implicitConversions

object AdhocJVM_mysql extends TestSuite_mysql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      val a = (1, Some(Set(ref1, ref2)))
      val b = (2, Some(Set(ref2, ref3, ref4)))
      val c = (3, None)
      for {
        _ <- Ns.i.refs_?.insert(a, b, c).transact

//        // Exact Set matches
//
//        // AND semantics
//        // "Is exactly this AND that"
//        _ <- Ns.i.a1.refs_?(Some(Set(ref1))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2))).query.get.map(_ ==> List(a)) // include exact match
//        _ <- Ns.i.a1.refs_?(Some(Set(ref2, ref1))).query.get.map(_ ==> List(a)) // include exact match
//        _ <- Ns.i.a1.refs_?(Some(Set(ref1, ref2, ref3))).query.get.map(_ ==> List())
//        // Same as
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1)))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2)))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref1)))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2, ref3)))).query.get.map(_ ==> List())
//
//
//        // AND/OR semantics with multiple Sets
//
//        // "(exactly this AND that) OR (exactly this AND that)"
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1), Set(ref2, ref3)))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref1, ref2), Set(ref2, ref3)))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set(ref2, ref1), Set(ref4, ref3, ref2)))).query.get.map(_ ==> List(a, b))
//
//
//        // Empty Seq/Sets match nothing
//        _ <- Ns.i.a1.refs_?(Some(Set.empty[Long])).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_?(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_?(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List())


        // None matches non-asserted values
        _ <- Ns.i.a1.refs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
//        _ <- Ns.i.a1.refs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  JSON_ARRAYAGG(Ns_refs_Ref.Ref_id) Ns_refs
//            |FROM Ns
//            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
//            |WHERE
//            |  (
//            |    SELECT
//            |      json_contains(
//            |        json_ARRAYAGG(Ns_refs_Ref.Ref_id),
//            |        json_array(2)
//            |      )
//            |    FROM Ns_refs_Ref
//            |    WHERE Ns_refs_Ref.Ns_id = Ns.id
//            |  ) AND
//            |  Ns.i IS NOT NULL
//            |GROUP BY Ns.id
//            |ORDER BY Ns.i;
//            |""".stripMargin, true)
//
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  JSON_ARRAYAGG(Ns_refs_Ref.Ref_id) Ns_refs
//            |FROM Ns
//            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
//            |WHERE
//            |  (
//            |    SELECT
//            |      json_length(json_ARRAYAGG(Ns_refs_Ref.Ref_id)) = 2 AND
//            |      json_contains(json_ARRAYAGG(Ns_refs_Ref.Ref_id), json_array(1, 2))
//            |    FROM Ns_refs_Ref
//            |    WHERE Ns_refs_Ref.Ns_id = Ns.id
//            |  ) AND
//            |  Ns.i IS NOT NULL
//            |GROUP BY Ns.id
//            |ORDER BY Ns.i;
//            |""".stripMargin, true)





//        _ <- Ns.ints.insert(Set(1, 2)).transact
//        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))
        //


      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
    //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    //
    //      } yield ()
    //    }
    //
    //
    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}