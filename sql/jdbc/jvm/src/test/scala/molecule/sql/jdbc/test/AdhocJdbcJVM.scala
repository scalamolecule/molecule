package molecule.sql.jdbc.test

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Uniques.Uniques
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.annotation.nowarn
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {


  def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
    val s = ('a' + scala.util.Random.nextInt(3)).toChar.toString // "a" or "b"
    val i = scala.util.Random.nextInt(3) + 1 // 1 or 2
    (s, i, int)
  }

  @nowarn // (Allow pattern matching results without warnings)
  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._

      val triples             = getTriples.map(t => (t._3, t._1, t._2))
      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)

      for {
        //        _ <- Uniques.int.i.s.insert(0, 1, "a").transact
        //        _ <- Uniques.i.s.query.get.map(_ ==> List((1, "a")))

        _ <- Uniques.int.s.i.insert(triples).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
//        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
//        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
//        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }

      } yield ()
    }

    /*
    identifiers: List(1, 3)
    identifier: 1
      identity(row): rs8: org.h2.result.LocalResult@2a898881 columns: 3 rows: 2 pos: 0
      identity(row): rs8: org.h2.result.LocalResult@2a898881 columns: 3 rows: 2 pos: 1
    identifier: 3
     */

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }
  }
}

/*
// Possible Map attribute key/value accessors?

//  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
//  val name_k = Set("en", "de", "da") // unique keys
//  val name_v = Seq("hello", "hej") // non-unique values
//
//  val name_
//  val name_k_
//  val name_v_
//
//  val name_?
//  val name_k_?
//  val name_v_?
 */