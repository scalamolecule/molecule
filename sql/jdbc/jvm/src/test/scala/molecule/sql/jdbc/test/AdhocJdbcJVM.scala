package molecule.sql.jdbc.test

import java.sql.ResultSet
import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
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

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ = printQuery(
          """SELECT DISTINCT
            |  s, i,
            |  ARRAY_SLICE(
            |    ARRAY_AGG(Ns.int order by Ns.int)
            |      FILTER (WHERE Ns.int IS NOT NULL),
            |    1,
            |    LEAST(
            |      2,
            |      ARRAY_LENGTH(
            |        ARRAY_AGG(Ns.int)
            |          FILTER (WHERE Ns.int IS NOT NULL)
            |      )
            |    )
            |  )
            |FROM Ns
            |WHERE
            |  Ns.s   IS NOT NULL AND
            |  Ns.i   IS NOT NULL AND
            |  Ns.int IS NOT NULL
            |group by s, i
            |""".stripMargin
        )

        _ <- Ns.i.int.a1.query.get.map(_ ==> List(
          (1, int1),
          (2, int2), // 2 rows coalesced
          (2, int3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.int(distinct).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.int(distinct).query.get.map(_.head ==> Set(
          int1, int2, int3
        ))

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
        _ <- A.i(1).s("a").+(B.i(2).s("b")).Tx(D.i(3).s("c")).save.transact
        _ <- (A.i.s + B.s.i).Tx(D.i).query.get.map(_ ==> List(((1, "a"), ("b", 2), 3)))
      } yield ()
    }

  }
}
