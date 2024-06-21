package molecule.sql.postgres

import java.time.Duration
import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.{TestSuiteArray_postgres, TestSuite_postgres}
import utest._
import scala.language.implicitConversions

//object AdhocJVM_postgres extends TestSuiteArray_postgres {
object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {


    println(Int.MaxValue)

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.string.insert(string1).transact
        _ <- Ns.int.insert(intMin, int1, intMax).transact
        _ <- Ns.long.insert(longMin, long1, longMax).transact
        _ <- Ns.float.insert(floatMin, float1, floatMax).transact
        _ <- Ns.double.insert(doubleMin, double1, doubleMax).transact
        _ <- Ns.boolean.insert(boolean1).transact
        _ <- Ns.bigInt.insert(bigIntNeg, bigInt1, bigIntPos).transact
        _ <- Ns.bigDecimal.insert(bigDecimalNeg, bigDecimal1, bigDecimalPos).transact
        _ <- Ns.date.insert(date1).transact
        _ <- Ns.duration.insert(duration1).transact
        _ <- Ns.instant.insert(instant1).transact
        _ <- Ns.localDate.insert(localDate1).transact
        _ <- Ns.localTime.insert(localTime1).transact
        _ <- Ns.localDateTime.insert(localDateTime1).transact
        _ <- Ns.offsetTime.insert(offsetTime1).transact
        _ <- Ns.offsetDateTime.insert(offsetDateTime1).transact
        _ <- Ns.zonedDateTime.insert(zonedDateTime1).transact
        _ <- Ns.uuid.insert(uuid1).transact
        _ <- Ns.uri.insert(uri1).transact
        _ <- Ns.byte.insert(byteMin, byte1, byteMax).transact
        _ <- Ns.short.insert(shortMin, short1, shortMax).transact
        _ <- Ns.char.insert(char1).transact

        _ <- Ns.string.a1.query.get.map(_ ==> List(string1))
        _ <- Ns.int.a1.query.get.map(_ ==> List(intMin, int1, intMax))
        _ <- Ns.long.a1.query.get.map(_ ==> List(longMin, long1, longMax))
        _ <- Ns.float.a1.query.get.map(_ ==> List(floatMin, float1, floatMax))
        _ <- Ns.double.a1.query.get.map(_ ==> List(doubleMin, double1, doubleMax))
        _ <- Ns.boolean.a1.query.get.map(_ ==> List(boolean1))
        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(bigIntNeg, bigInt1, bigIntPos))
        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimalNeg, bigDecimal1, bigDecimalPos))
        _ <- Ns.date.a1.query.get.map(_ ==> List(date1))
        _ <- Ns.duration.a1.query.get.map(_ ==> List(duration1))
        _ <- Ns.instant.a1.query.get.map(_ ==> List(instant1))
        _ <- Ns.localDate.a1.query.get.map(_ ==> List(localDate1))
        _ <- Ns.localTime.a1.query.get.map(_ ==> List(localTime1))
        _ <- Ns.localDateTime.a1.query.get.map(_ ==> List(localDateTime1))
        _ <- Ns.offsetTime.a1.query.get.map(_ ==> List(offsetTime1))
        _ <- Ns.offsetDateTime.a1.query.get.map(_ ==> List(offsetDateTime1))
        _ <- Ns.zonedDateTime.a1.query.get.map(_ ==> List(zonedDateTime1))
        _ <- Ns.uuid.a1.query.get.map(_ ==> List(uuid1))
        _ <- Ns.uri.a1.query.get.map(_ ==> List(uri1))
        _ <- Ns.byte.a1.query.get.map(_ ==> List(byteMin, byte1, byteMax))
        _ <- Ns.short.a1.query.get.map(_ ==> List(shortMin, short1, shortMax))
        _ <- Ns.char.a1.query.get.map(_ ==> List(char1))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.insert(1).transact
        _ <- A.i.OwnB.i.insert((2, 20), (3, 30)).transact

        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- A.i_(1).OwnB.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- A.i_(2).OwnB.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

        // Owned B entity is deleted too
        _ <- B.i.a1.query.get.map(_ ==> List(30))

        // A.i entity has no ref to OwnB.i_(42) so nothing is deleted
        _ <- A.i_.OwnB.i_(42).delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((3, 30)))

        // A.i entity has a ref to OwnB.i_(30) so it will be deleted
        _ <- A.i_.OwnB.i_(30).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        _ <- A.i.OwnB.i.query.get.map(_ ==> List())

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = ARRAY_REMOVE(ARRAY_REMOVE(iSeq, ?::integer), ?::integer)
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  B.id IN(2, 3)
        //            |""".stripMargin)


        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE elem NOT IN(3, 4)
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE elem NOT IN(1, 2, 3, 4)
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE not elem = ANY ('{3, 4}'::int[])
        //            |""".stripMargin, true)

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}