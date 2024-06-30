package molecule.sql.h2

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.collection.mutable
import scala.concurrent.Future
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      val (a1, b2) = ("a" -> int1, "b" -> int2)
      val (b3, c4) = ("b" -> int3, "c" -> int4)

      for {
        _ <- Ns.i.insert(0).transact // Entity without map attribute
        _ <- Ns.i.intMap.insert(List(
          (1, Map(a1, b2)),
          (2, Map(b3, c4)),
        )).transact

        // OR semantics! (different from Set and Seq)

        // "Map contains this OR that key"
//        _ <- Ns.i.a1.intMap_("_").query.get.map(_ ==> Nil)
        _ <- Ns.i.a1.intMap_("a").query.i.get.map(_ ==> List(1))
        //        List(a, b) <- Ns.iMap.int.insert(
        //          (Map("a" -> 1, "b" -> 2), 1),
        //          (Map("b" -> 2, "c" -> 3), 2),
        //        ).transact.map(_.ids)
        //
        //        _ <- Ns.iMap_.hasNo(1).int(3).update.i.transact
        //
        //        // Update all entities where `iMap` has a key = "a"
        //        _ <- Ns.iMap_.apply("a").int(3).update.i.transact
        //
        //        // 1 entity updated
        //        _ <- Ns.id.a1.iMap.int.query.get.map(_ ==> List(
        //          (a, Map("a" -> 1, "b" -> 2), 3), // updated
        //          (b, Map("b" -> 2, "c" -> 3), 2),
        //        ))
        //
        //        // Update all entities where `iMap` has keys "a" or "c"
        //        _ <- Ns.iMap_(Seq("a", "c")).int(4).update.transact
        //        _ <- Ns.id.a1.iMap.int.query.get.map(_ ==> List(
        //          (a, Map("a" -> 1, "b" -> 2), 4), // updated
        //          (b, Map("b" -> 2, "c" -> 3), 4), // updated
        //        ))
        //
        //        // Update all entities where `iMap` has keys "x" or "c"
        //        _ <- Ns.iMap_("x", "c").int(5).update.transact
        //        _ <- Ns.id.a1.iMap.int.query.get.map(_ ==> List(
        //          (a, Map("a" -> 1, "b" -> 2), 4),
        //          (b, Map("b" -> 2, "c" -> 3), 5), // updated
        //        ))
        //
        //        // Nothing updated since no `iMap` has key "x"
        //        _ <- Ns.iMap_("x").int(5).update.transact
        //        _ <- Ns.id.a1.iMap.int.query.get.map(_ ==> List(
        //          (a, Map("a" -> 1, "b" -> 2), 4),
        //          (b, Map("b" -> 2, "c" -> 3), 5),
        //        ))



        //        _ <- rawQuery(
        //          """select count(*) from Ns
        //            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |""".stripMargin, true)
        //
        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  i = 7
        //            |WHERE
        //            |  Ns.i IS NOT NULL AND
        //            |  exists (
        //            |    select * from Ns
        //            |      INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |  )
        //            |""".stripMargin)
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          //          (1, List()),
          //          (2, List((Some("a"), None))),
          //          (3, List((Some("b"), None), (Some("c"), None))),
          //          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          //          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSeq.add(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          //          (1, List()), //                                                           no B value to update
          //          (2, List()), //                                                           no B value to update
          //          (3, List()), //                                                           no B value to update
          //          (4, List((Some("d"), Seq(1, 2, 4, 5)))), //                               update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5)), (Some("f"), Seq(3, 4, 4, 5)))), // update in 2 ref entities
          //          (6, List((Some("g"), Seq(4, 5, 4, 5)))), //                               update, but already has same values
        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A.i_.Bb.iSeq.add(5, 6).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
        //          (1, List((None, Seq(5, 6)))), //                                                      ref + insertion
        //          (2, List((Some("a"), Seq(5, 6)))), //                                                 insertion in 1 ref entity
        //          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), //                         insertion in 2 ref entities
        //          (4, List((Some("d"), Seq(1, 2, 4, 5, 5, 6)))), //                                     update in 1 ref entity
        //          (5, List((Some("e"), Seq(2, 3, 4, 5, 5, 6)), (Some("f"), Seq(3, 4, 4, 5, 5, 6)))), // update in 2 ref entities
        //          (6, List((Some("g"), Seq(4, 5, 4, 5, 5, 6)), (Some("h"), Seq(5, 6)))), //             update in one ref entity and insertion in another
        //        ))

        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  i = 7
        //            |WHERE
        //            |  Ns.i IS NOT NULL AND
        //            |  exists (
        //            |    select * from Ns
        //            |      INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |  )
        //            |""".stripMargin)


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)

        // We can remove a value from a Set as long as it's not the last value
        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact

        //        // Can't remove the last value of a mandatory attribute Set of values
        //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
        //          .map(_ ==> "Unexpected success").recover {
        //            case ModelError(error) =>
        //              error ==>
        //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
        //                  |  MandatoryAttr.hobbies
        //                  |""".stripMargin
        //          }

      } yield ()
    }
    //
    //    "partitions" - partition { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Partitions._
    //      for {
    //
    //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
    //          .insert("book", "Jan", List("Musician")).transact
    //
    //      } yield ()
    //    }
  }
}
