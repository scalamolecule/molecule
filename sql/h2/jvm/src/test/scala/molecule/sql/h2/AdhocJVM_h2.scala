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
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

//        // Can't save multiple values (use insert for that)
//        _ <- Ns.i(1, 2).save.transact
//          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
//            err ==> "Can only save one value for attribute `Ns.i`. Found: 1, 2"
//          }
//
//        _ <- Ns.i(Seq(1, 2)).save.transact
//          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
//            err ==> "Can only save one value for attribute `Ns.i`. Found: 1, 2"
//          }
//
//        // Saving empty list of values is ignored
//        _ <- Ns.i(Seq.empty[Int]).save.transact
//        _ <- Ns.i.query.get.map(_ ==> List())
//
//        _ <- Ns.string(string1).save.transact
//        _ <- Ns.int(int1).save.transact
//        _ <- Ns.long(long1).save.transact
//        _ <- Ns.float(float1).save.transact
//        _ <- Ns.double(double1).save.transact
//        _ <- Ns.boolean(boolean1).save.transact
//        _ <- Ns.bigInt(bigInt1).save.transact
//        _ <- Ns.bigDecimal(bigDecimal1).save.transact
//        _ <- Ns.date(date1).save.transact
//        _ <- Ns.duration(duration1).save.transact
//        _ <- Ns.instant(instant1).save.transact
//        _ <- Ns.localDate(localDate1).save.transact
//        _ <- Ns.localTime(localTime1).save.transact
//        _ <- Ns.localDateTime(localDateTime1).save.transact
//        _ <- Ns.offsetTime(offsetTime1).save.transact
//        _ <- Ns.offsetDateTime(offsetDateTime1).save.transact
//        _ <- Ns.zonedDateTime(zonedDateTime1).save.transact
//        _ <- Ns.uuid(uuid1).save.transact
//        _ <- Ns.uri(uri1).save.transact
//        _ <- Ns.byte(byte1).save.transact
//        _ <- Ns.short(short1).save.transact
//        _ <- Ns.char(char1).save.transact
//
//        _ <- Ns.string.a1.query.get.map(_ ==> List(string1))
//        _ <- Ns.int.a1.query.get.map(_ ==> List(int1))
//        _ <- Ns.long.a1.query.get.map(_ ==> List(long1))
//        _ <- Ns.float.a1.query.get.map(_ ==> List(float1))
//        _ <- Ns.double.a1.query.get.map(_ ==> List(double1))
//        _ <- Ns.boolean.a1.query.get.map(_ ==> List(boolean1))
//        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(bigInt1))
//        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1))
//        _ <- Ns.date.a1.query.get.map(_ ==> List(date1))
//        _ <- Ns.duration.a1.query.get.map(_ ==> List(duration1))
//        _ <- Ns.instant.a1.query.get.map(_ ==> List(instant1))
//        _ <- Ns.localDate.a1.query.get.map(_ ==> List(localDate1))
//        _ <- Ns.localTime.a1.query.get.map(_ ==> List(localTime1))
//        _ <- Ns.localDateTime.a1.query.get.map(_ ==> List(localDateTime1))
//        _ <- Ns.offsetTime.a1.query.get.map(_ ==> List(offsetTime1))
//        _ <- Ns.offsetDateTime.a1.query.get.map(_ ==> List(offsetDateTime1))
//        _ <- Ns.zonedDateTime.a1.query.get.map(_ ==> List(zonedDateTime1))
//        _ <- Ns.uuid.a1.query.get.map(_ ==> List(uuid1))
//        _ <- Ns.uri.a1.query.get.map(_ ==> List(uri1))
//        _ <- Ns.byte.a1.query.get.map(_ ==> List(byte1))
//        _ <- Ns.short.a1.query.get.map(_ ==> List(short1))
//        _ <- Ns.char.a1.query.get.map(_ ==> List(char1))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
//        _ <- Ns.ref(r1).save.transact
//        _ <- Ns.ref(Seq(r2)).save.transact
//        _ <- Ns.ref.a1.query.get.map(_ ==> List(r1, r2))
//
//
//
//
//
//
//
//
//
////        _ <- Ns.i.insert(5, 6).transact
////        _ = println("=============")
////        _ <- Ns.i.query.get.map(_ ==> List(5, 6))
//
//
//        _ <- Ns.i_?.int.insert(
//          (None, 0), // entity with missing i
//          (Some(1), 1),
//          (Some(2), 2),
//        ).transact
//
//        // Update all entities where `i` is not asserted (null)
//        _ <- Ns.i_().int(3).update.transact
//
//        // 1 entity updated
//        _ <- Ns.i_?.a1.int.query.get.map(_ ==> List(
//          (None, 3), // updated
//          (Some(1), 1),
//          (Some(2), 2),
//        ))

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
