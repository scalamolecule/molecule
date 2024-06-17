package molecule.sql.sqlite

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.{TestSuiteArray_sqlite, TestSuite_sqlite}
import utest._
import scala.collection.mutable
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

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

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.ref.insert(r1).transact
        _ <- Ns.ref.insert(Seq(r2)).transact
        _ <- Ns.ref.a1.query.get.map(_ ==> List(r1, r2))

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
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {


        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSeq(Seq(1, 2, 3)).save.transact


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
