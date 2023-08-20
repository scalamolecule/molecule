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

        _ <- rawQuery(
          """SELECT *
            |FROM Ns
            |""".stripMargin, true)
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

        e1 <- A.i.Bb.*(B.i).insert(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ).transact.map(_.id)

        _ <- if (platform == "Jdbc jvm") {
          // 4 join rows from A to B
          rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
            List(1, 1),
            List(1, 2),
            List(2, 3),
            List(2, 4),
          ))
        } else Future.unit

        // 2 entities, each with 2 owned sub-entities
        _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ))
        // 4 referenced entities
        _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

        _ <- A(e1).delete.transact

        // 1 entity with 2 owned sub-entities left
        _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List(
          (2, Seq(20, 21))
        ))
        // Referenced are not deleted
        _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

        _ <- rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
          // List(1, 1),
          // List(1, 2),
          List(2, 3),
          List(2, 4),
        ))

      } yield ()
    }

  }
}
