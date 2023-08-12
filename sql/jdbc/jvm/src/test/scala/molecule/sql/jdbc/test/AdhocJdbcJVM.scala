package molecule.sql.jdbc.test

import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.List
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

  override lazy val (float1, float2, float3, float4)                     = (1.0f, 2.0f, 3.0f, 4.0f)
  override lazy val (double1, double2, double3, double4)                 = (1.0, 2.0, 3.0, 4.0)
  override lazy val (bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
    (BigDecimal(1.0), BigDecimal(2.0), BigDecimal(3.0), BigDecimal(4.0))

  override lazy val tests = Tests {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
        _ <- Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
        _ <- Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
        _ <- Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
        _ <- Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
        _ <- Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact
        _ <- Ns.i.ref.insert((1, ref1), (1, ref3), (2, ref4)).transact

        _ <- Ns.i.int(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.long(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.float(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.double(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigInt(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigDecimal(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.byte(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.short(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.ref(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))

        _ <- Ns.i.int(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.long(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.float(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.double(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigInt(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigDecimal(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.byte(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.short(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.ref(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))









        //        _ = printQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  ARRAY_AGG(Ns_refs_Ref.Ref_id)
        //            |FROM Ns
        //            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |GROUP BY  Ns.id, Ns.i
        //            |ORDER BY Ns.i NULLS FIRST;
        //            |""".stripMargin)


        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

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
