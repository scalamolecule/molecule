package molecule.sql.h2

import molecule.core.util.Executor._
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.language.implicitConversions


object AdhocJVM_h2 extends TestSuiteArray_h2 {
  //object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.i(0).save.transact
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3, ref4))
        )).transact

        // Sets without one or more values matching

//        // "Doesn't have this value"
//        _ <- Ns.i.a1.refs_.hasNo(ref0).query.get.map(_ ==> List(1, 2))
//        _ <- Ns.i.a1.refs_.hasNo(ref1).query.get.map(_ ==> List(2))
//        _ <- Ns.i.a1.refs_.hasNo(ref2).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(ref3).query.get.map(_ ==> List(1))
//        _ <- Ns.i.a1.refs_.hasNo(ref4).query.get.map(_ ==> List(1))
//        _ <- Ns.i.a1.refs_.hasNo(ref5).query.get.map(_ ==> List(1, 2))
//        // Same as
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref0)).query.get.map(_ ==> List(1, 2))
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref1)).query.get.map(_ ==> List(2))
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref2)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref3)).query.get.map(_ ==> List(1))
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref4)).query.get.map(_ ==> List(1))
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref5)).query.get.map(_ ==> List(1, 2))


        // OR semantics when multiple values

        _ <- Ns.i.a1.refs_.has(ref1, ref2).query.get.map(_ ==> List(1, 2))


        // "Has neither this OR that"
        _ <- Ns.i.a1.refs_.hasNo(ref1, ref2).query.i.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(ref1, ref3).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(ref1, ref4).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(ref1, ref5).query.get.map(_ ==> List(2))
//        // Same as
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(2))
//
//
//        // Negating empty Seqs/Sets has no effect
//        _ <- Ns.i.a1.refs_.hasNo(Seq.empty[String]).query.get.map(_ ==> List(1, 2))


//        _ <- rawTransact(
//          """INSERT INTO Ns (
//            |  intSet
//            |) VALUES (array[1, 2, 2])
//            |""".stripMargin)
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  ARRAY_AGG(Ns.intSet)
//            |FROM Ns
//            |WHERE
//            |  Ns.intSet IS NOT NULL
//            |HAVING COUNT(*) > 0;
//            |""".stripMargin, true)
//          .map(println)
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.intSeq
//            |FROM Ns
//            |WHERE
//            |  Ns.intSeq IS NOT NULL;
//            |""".stripMargin, true)
//          .map(println)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.s.Bb.s.Cc.*(C.s)
          .insert("book", "Jan", List("Musician")).transact

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
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
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
