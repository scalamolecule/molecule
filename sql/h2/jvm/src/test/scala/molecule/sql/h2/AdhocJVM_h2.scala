package molecule.sql.h2

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
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

      for {
        //        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        //        _ <- Ns.i.refs.insert(1, Set(r1, r2)).i.transact
        //        _ <- Ns.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))

        List(ref1, ref2, ref3, ref4) <- Ref.i.insert(1, 2, 3, 4).transact.map(_.ids)
        a = (5, Set(ref1, ref2))
        b = (6, Set(ref2, ref3, ref4))

//        _ <- Ns.i.longSet.insert(List(a, b)).transact
//        _ <- Ns.id.i.a1.longSet.query.i.get.map(_ ==> List(
//          (1, 1, Set(1, 2)),
//          (2, 2, Set(2, 3, 4)),
//        ))


        _ <- Ns.i.refs.insert(List(a, b)).transact

        // Sets with one or more values matching
        _ <- Ns.id.i.a1.refs.query.i.get.map(_ ==> List(
          (1, 5, Set(1, 2)),
          (2, 6, Set(2, 3, 4)),
        ))

        // "Has this value"
        //        _ <- Ns.i.a1.refs.has(ref0).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs.has(ref1).query.i.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.refs.has(ref2).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(ref3).query.get.map(_ ==> List(b))
        //        // Same as
        //        _ <- Ns.i.a1.refs.has(Seq(ref0)).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.refs.has(Seq(ref1)).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.refs.has(Seq(ref2)).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(Seq(ref3)).query.get.map(_ ==> List(b))
        //
        //
        //        // OR semantics when multiple values
        //
        //        // "Has this OR that"
        //        _ <- Ns.i.a1.refs.has(ref1, ref2).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(ref1, ref3).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(ref2, ref3).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
        //        // Same as
        //        _ <- Ns.i.a1.refs.has(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
        //        _ <- Ns.i.a1.refs.has(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))
        //
        //
        //        // Empty Seq/Sets match nothing
        //        _ <- Ns.i.a1.refs.has(Seq.empty[Long]).query.get.map(_ ==> List())


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      //      val a0: Future[List[(Int, String, Seq[(Int, String)])]]                   = A.i.s.Bb.*?(B.i.s).query.get
      //      val a1: Future[List[(Int, String, Seq[(Int, String)])]]                   = A.i.s.Bb.*(B.i.s).query.get
      //      val a2: Future[List[(Int, String, Option[(Int, String)])]]                = A.i.s.B.?(B.i.s).query.get
      //      val a3: Future[List[(Int, String, Option[(Int, Option[String])])]]        = A.i.s.B.?(B.i.s_?).query.get
      //      val a4: Future[List[(Int, String, Int, String)]]                          = A.i.s.B.i.s.query.get
      //      val a6: Future[List[(Int, Option[(Int, String, Int, String)])]]           = A.i.B.?(B.i.s.C.i.s).query.get
      //      val a7: Future[List[(Int, Option[(Int, String)])]]                        = A.i.B.?(B.i.s).query.get
      //      val a5: Future[List[(Int, Option[(Int, String, Option[(Int, String)])])]] = A.i.B.?(B.i.s.C.?(C.i.s)).query.get
      //      val a8: Future[List[(Int, Option[(Int, String)], Option[(Int, String)])]] = A.i.B.?(B.i.s).C.?(C.i.s).query.get

      for {

        _ <- A.iMap(Map(pint0, pint1)).save.transact // won't be updated since there's no B value
        _ <- A.s("x").B.i(1).save.transact
        _ <- A.iMap(Map(pint2, pint3)).B.i(2).save.transact
        _ <- A.iMap(Map(pint3, pint4)).B.i(3).save.transact

        // Current 2 entities with A value and ref to B value
        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint2, pint3), 2),
          (Map(pint3, pint4), 3),
        ))

        // Filter by B value, update A values
        _ <- A.iMap(Map(pint4, pint5)).B.i_.update.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint4, pint5), 2), // A value updated
          (Map(pint4, pint5), 3), // A value updated
        ))

        // Filter by B value, upsert A values (insert if not already present)
        _ <- A.iMap(Map(pint5, pint6)).B.i_.upsert.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint5, pint6), 1), // A value and relationship to B value inserted
          (Map(pint5, pint6), 2), // A value updated
          (Map(pint5, pint6), 3), // A value updated
        ))

        // Initial entity without ref to B was not updated/upserted
        _ <- A.iMap.b_().query.get.map(_ ==> List(Map(pint0, pint1)))

        //        List(
        //          (1,List((Some(a),None), (Some(b),None), (Some(c),None), (Some(d),Some(1)), (Some(e),Some(2)), (Some(f),Some(3)), (Some(g),Some(4)), (Some(h),None))), (2,List()), (3,List()), (4,List()), (5,List()), (6,List()))
        //          List(
        //          (1,List()),
        //          (2,List((Some(a),None))), (3,List((Some(b),None), (Some(c),None))), (4,List((Some(d),Some(1)))), (5,List((Some(e),Some(2)), (Some(f),Some(3)))), (6,List((Some(g),Some(4)), (Some(h),None))))

        //        // Filter by A ids, update B values
        //        _ <- A(a, b, c, d, e, f).Bb.i(4).update.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        //          (1, List()), //                                no B.i value
        //          (2, List()), //                                no B.i value
        //          (3, List()), //                                no B.i value
        //          (4, List((Some("d"), 4))), //                  update in 1 ref entity
        //          (5, List((Some("e"), 4), (Some("f"), 4))), //  update in 2 ref entities
        //          (6, List((Some("g"), 4))), //                  already had same value
        //        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A(a, b, c, d, e, f).Bb.i(5).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        //          (1, List((None, 5))), //                       ref + insert
        //          (2, List((Some("a"), 5))), //                  addition in 1 ref entity
        //          (3, List((Some("b"), 5), (Some("c"), 5))), //  addition in 2 ref entities
        //          (4, List((Some("d"), 5))), //                  update in 1 ref entity
        //          (5, List((Some("e"), 5), (Some("f"), 5))), //  update in 2 ref entities
        //          (6, List((Some("g"), 5), (Some("h"), 5))), //  update in one ref entity and insertion in another
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
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        id <- MandatoryAttr.name("Bob").age(42)
    //          .hobbies(Set("golf")).save.transact.map(_.id)
    //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //
    //        _ = println("+++++++++++++++++++++")
    //        //        // We can remove a value from a Set as long as it's not the last value
    //        //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of values
    //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryAttr.hobbies
    //                  |""".stripMargin
    //          }
    //
    //      } yield ()
    //    }
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
