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
      val a = (1, Map("a" -> bigInt1, "b" -> bigInt2))
      val b = (2, Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4))
      for {

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int.i.refs_?.insert(23, 1, Option.empty[Set[Long]]).transact
        _ <- Ns.int.i.refs_?.insert(23, 2, Some(Set.empty[Long])).transact
        _ <- Ns.int.i.refs_?.insert(23, 3, Some(Set(r1, r2))).transact


        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  ARRAY_AGG(Ns_refs_Ref.Ref_id) Ns_refs
            |FROM Ns
            |  LEFT JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
            |WHERE
            |  Ns.int = 23 AND
            |  Ns.int IS NOT NULL AND
            |  Ns.i   IS NOT NULL
            |GROUP BY Ns.id
            |ORDER BY Ns.i;
            |""".stripMargin, true)



        _ <- Ns.int_(23).i.a1.refs_?.query.i.get
          .map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))



        //
        //        _ <- Ns.i.insert(0).transact // Entity without map attribute
        //        _ <- Ns.i.bigIntMap.insert(List(a, b)).transact
        //
        //        _ <- Ns.i(0).stringMap(Map("a" -> "hej")).save.transact
        ////        _ <- Ns.i(1).bigIntMap(Map("a" -> bigInt1, "b" -> bigInt2)).save.transact
        ////        _ <- Ns.i(2).bigIntMap(Map("a" -> bigInt2, "b" -> bigInt3, "c" -> bigInt4)).save.transact
        //
        //
        ////        _ <- Ns.i.a1.bigIntMap("_").query.get.map(_ ==> Nil) // When no map is saved
        //        _ <- Ns.i.a1.stringMap("a").query.get.map(_ ==> List((0, "hej")))
        //
        //        _ <- Ns.i.a1.bigIntMap("a").query.get.map(_ ==> List((1, bigInt1), (2, bigInt2)))
        ////        _ <- Ns.i.a1.bigIntMap("b").query.get.map(_ ==> List((1, bigInt2), (2, bigInt3)))
        ////        _ <- Ns.i.a1.bigIntMap("c").query.get.map(_ ==> List((2, bigInt4)))

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.string
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |ORDER BY Ns.string;
        //            |""".stripMargin, true)

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

        //        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        //        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.Bb.*(B.s_?.iMap_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Map(pint1, pint2))),
            //            (Some("a"), None),
            //            (Some("b"), Some(Map(pint2, pint3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        //        _ <- A.Bb.s_.iMap(Map(pint3, pint4)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.i.get.map(_ ==> List(
          //          (1, List()), //                            no change to entity without relationship to B
          (2, List(
            // (None, None),                         no relationship to B
            (None, Some(Map(pint1, pint2))), //      no change without filter match
            //            (Some("a"), None), //                    no value to update
            //            (Some("b"), Some(Map(pint3, pint4))), // B attribute updated
          ))
        ))

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
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)

        // Mandatory refs can be removed as long as some ref ids remain
        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact

        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
        _ <- MandatoryRefsB(id).refsB.remove(r1).update.i.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryRefsB.refsB
                  |""".stripMargin
          }

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
