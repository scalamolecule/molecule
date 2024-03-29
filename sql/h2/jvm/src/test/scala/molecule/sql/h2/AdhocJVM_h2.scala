package molecule.sql.h2

import molecule.core.util.Executor._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.language.implicitConversions


object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

//        // Sum of all values
//        _ <- Ns.intSet(sum).query.get.map(_.head ==~ (
//          int1 + int2 +
//            int2 +
//            int3 + int4 +
//            int3 + int4))
//
//        // Sort by sum
//        _ <- Ns.i.intSet(sum).a1.query.get.map(_ ==~ List(
//          (1, int1 + int2),
//          (2, int2 + int3 + int4 + int3 + int4),
//        ))


        _ <- rawQuery(
          """
            |
            |// select intSet from Ns;
            |select * from unnest((select intSet from Ns));
            |
            |// SELECT * FROM UNNEST((SELECT intSet FROM Ns));
            |""".stripMargin, true)
          .map(println)
/*

//CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);

DROP TABLE A IF EXISTS;
CREATE TABLE A(i INT, ints INT ARRAY);
INSERT INTO A(i, ints) VALUES
  (1, ARRAY[1, 2]),
  (1, ARRAY[3, 4]),
  (2, ARRAY[5, 6]);
SELECT i, ints FROM A;

SELECT * FROM UNNEST((SELECT ints FROM A));

SELECT * FROM TABLE(V INT = SELECT * FROM UNNEST((SELECT ints FROM A)));
SELECT * FROM TABLE(V INT = UNNEST((SELECT ints FROM A)));

SELECT i, ints FROM A group by ints;


SELECT i, array_agg(ints) FROM A group by i;


// [1, 2]
// [3, 4]
// 1
// 2
// 3
// 4

SELECT * FROM UNNEST((SELECT ints FROM A));
 */


//        _ <- Ns.i.intSet.apply(sum).d1.query.i.get.map(_ ==~ List(
//          (2, int2 + int3 + int4 + int3 + int4),
//          (1, int1 + int2),
//        ))

//        _ <- Ns.i(1).stringSeq(List(string1, string2)).save.transact
//        _ <- Ns.i(1).intSeq(List(int1, int2)).save.transact
//        _ <- Ns.i(1).longSeq(List(long1, long2)).save.transact
//        _ <- Ns.i(1).floatSeq(List(float1, float2)).save.transact
//        _ <- Ns.i(1).doubleSeq(List(double1, double2)).save.transact
//        _ <- Ns.i(1).booleanSeq(List(boolean0)).save.transact
//        _ <- Ns.i(1).bigIntSeq(List(bigInt1, bigInt2)).save.transact
//        _ <- Ns.i(1).bigDecimalSeq(List(bigDecimal1, bigDecimal2)).save.transact
//        _ <- Ns.i(1).dateSeq(List(date1, date2)).save.transact
//        _ <- Ns.i(1).durationSeq(List(duration1, duration2)).save.transact
//        _ <- Ns.i(1).instantSeq(List(instant1, instant2)).save.transact
//        _ <- Ns.i(1).localDateSeq(List(localDate1, localDate2)).save.transact
//        _ <- Ns.i(1).localTimeSeq(List(localTime1, localTime2)).save.transact
//        _ <- Ns.i(1).localDateTimeSeq(List(localDateTime1, localDateTime2)).save.transact
//        _ <- Ns.i(1).offsetTimeSeq(List(offsetTime1, offsetTime2)).save.transact
//        _ <- Ns.i(1).offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2)).save.transact
//        _ <- Ns.i(1).zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2)).save.transact
//        _ <- Ns.i(1).uuidSeq(List(uuid1, uuid2)).save.transact
//        _ <- Ns.i(1).uriSeq(List(uri1, uri2)).save.i.transact
//        _ <- Ns.i(1).byteArray(Array(byte1, byte2)).save.i.transact // Note that Bytes are saved in Arrays
//        _ <- Ns.i(1).shortSeq(List(short1, short2)).save.transact
//        _ <- Ns.i(1).charSeq(List(char1, char2)).save.transact
//
//        _ <- Ns.i.stringSeq.query.get.map(_ ==> List((1, List(string1, string2))))
//        _ <- Ns.i.intSeq.query.get.map(_ ==> List((1, List(int1, int2))))
//        _ <- Ns.i.longSeq.query.get.map(_ ==> List((1, List(long1, long2))))
//        _ <- Ns.i.floatSeq.query.get.map(_ ==> List((1, List(float1, float2))))
//        _ <- Ns.i.doubleSeq.query.get.map(_ ==> List((1, List(double1, double2))))
//        _ <- Ns.i.booleanSeq.query.get.map(_ ==> List((1, List(boolean0))))
//        _ <- Ns.i.bigIntSeq.query.get.map(_ ==> List((1, List(bigInt1, bigInt2))))
//        _ <- Ns.i.bigDecimalSeq.query.get.map(_ ==> List((1, List(bigDecimal1, bigDecimal2))))
//        _ <- Ns.i.dateSeq.query.get.map(_ ==> List((1, List(date1, date2))))
//        _ <- Ns.i.durationSeq.query.get.map(_ ==> List((1, List(duration1, duration2))))
//        _ <- Ns.i.instantSeq.query.get.map(_ ==> List((1, List(instant1, instant2))))
//        _ <- Ns.i.localDateSeq.query.get.map(_ ==> List((1, List(localDate1, localDate2))))
//        _ <- Ns.i.localTimeSeq.query.get.map(_ ==> List((1, List(localTime1, localTime2))))
//        _ <- Ns.i.localDateTimeSeq.query.get.map(_ ==> List((1, List(localDateTime1, localDateTime2))))
//        _ <- Ns.i.offsetTimeSeq.query.get.map(_ ==> List((1, List(offsetTime1, offsetTime2))))
//        _ <- Ns.i.offsetDateTimeSeq.query.get.map(_ ==> List((1, List(offsetDateTime1, offsetDateTime2))))
//        _ <- Ns.i.zonedDateTimeSeq.query.get.map(_ ==> List((1, List(zonedDateTime1, zonedDateTime2))))
//        _ <- Ns.i.uuidSeq.query.get.map(_ ==> List((1, List(uuid1, uuid2))))
//        _ <- Ns.i.uriSeq.query.get.map(_ ==> List((1, List(uri1, uri2))))
//        _ <- Ns.i.byteArray.query.get.map(_ ==> List((1, Array(byte1, byte2)))) // Note that Bytes are saved in Arrays
//        _ <- Ns.i.shortSeq.query.get.map(_ ==> List((1, List(short1, short2))))
//        _ <- Ns.i.charSeq.query.get.map(_ ==> List((1, List(char1, char2))))
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
