package molecule.sql.jdbc.test

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


  override lazy val tests = Tests {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    "types" - types { implicit conn =>

      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))


        id <- Ns.int.insert(1).transact.map(_.id)
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Update existing value
        _ <- Ns(id).int(2).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(2))

        // Or update using id_
        _ <- Ns.id_(id).int(3).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(3))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).string("a").update.inspect
        _ <- Ns(id).string("a").update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((3, None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).string("a").upsert.inspect
        _ <- Ns(id).string("a").upsert.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((3, Some("a"))))

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
        _ <- A.i(1).s("a").+(B.i(2).s("b")).Tx(D.i(3).s("c")).save.transact
        _ <- (A.i.s + B.s.i).Tx(D.i).query.get.map(_ ==> List(((1, "a"), ("b", 2), 3)))


        //        _ <- (A.i(1).B.i(2) + B.i(3)).save.transact

      } yield ()
    }

  }
}
