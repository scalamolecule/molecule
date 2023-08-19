package molecule.sql.jdbc.test

import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.List
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  //  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
  //  val name_k = Set("en", "de", "da")
  //  val name_v = Set("hello", "hej")
  //
  //  val name_   = 7
  //  val name_k_ = 7
  //  val name_v_ = 7
  //
  //  val name_?   = 7
  //  val name_k_? = 7
  //  val name_v_? = 7
  //
  //  val ranked   = List("Peter", "Bob", "Mary")
  //  val ranked_i = List(0, 1, 2)
  //
  //  val rankedA   = Array("Peter", "Bob", "Mary")
  //  val rankedA_i = Array(0, 1, 2)

  //  val x: Instant = ???
  //  val y: Long    = x.toEpochMilli

  //  val all = List(
  //    AttrOneManInt("A", "i", Eq, Seq(1), None, None, Nil, Nil, None, None),
  //      Ref("A", "b", "B", CardOne, false),
  //      AttrOneManInt("B", "i", Eq, Seq(2), None, None, Nil, Nil, None, None),
  //      Ref("B", "c", "C", CardOne, false),
  //      AttrOneManInt("C", "i", Eq, Seq(3), None, None, Nil, Nil, None, None)
  //  )

  override lazy val tests = Tests {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    "types" - types { implicit conn =>

      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))


        //                _ <- Future(printQuery(
        //                  """SELECT DISTINCT
        //                    |  ARRAY_AGG(Ns.ints)
        //                    |FROM Ns
        //                    |WHERE
        //                    |  Ns.ints IS NOT NULL AND CARDINALITY(Ns.ints) > 0;
        //                    |""".stripMargin))
      } yield ()
    }



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

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {
        //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
        _ <- A.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- A(e1).delete.transact
        // or
        _ <- A.id_(e2).delete.transact
        _ <- A.i.query.get.map(_ ==> List(3))

      } yield ()
    }

  }
}
