package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._
import scala.util.Random

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
//        _ <- Ns.i(1).save.transact
//        _ <- Ns.i.i.query.get.map(_ ==> List(1))

        _ <- Ns.i.string.insert(
          (1, string1),
          (1, string2),
          (1, string2),
          (2, string2)).transact

        _ <- Ns.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).transact
//        _ <- Ns.i.long.insert((1, long1), (1, long2), (1, long2), (2, long2)).transact
//        _ <- Ns.i.float.insert((1, float1), (1, float2), (1, float2), (2, float2)).transact
//        _ <- Ns.i.double.insert((1, double1), (1, double2), (1, double2), (2, double2)).transact
//        _ <- Ns.i.boolean.insert((1, boolean1), (1, boolean2), (1, boolean2), (2, boolean2)).transact
//        _ <- Ns.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt2), (2, bigInt2)).transact
//        _ <- Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal2), (2, bigDecimal2)).transact
//        _ <- Ns.i.date.insert((1, date1), (1, date2), (1, date2), (2, date2)).transact
//        _ <- Ns.i.duration.insert((1, duration1), (1, duration2), (1, duration2), (2, duration2)).transact
//        _ <- Ns.i.instant.insert((1, instant1), (1, instant2), (1, instant2), (2, instant2)).transact
//        _ <- Ns.i.localDate.insert((1, localDate1), (1, localDate2), (1, localDate2), (2, localDate2)).transact
//        _ <- Ns.i.localTime.insert((1, localTime1), (1, localTime2), (1, localTime2), (2, localTime2)).transact
//        _ <- Ns.i.localDateTime.insert((1, localDateTime1), (1, localDateTime2), (1, localDateTime2), (2, localDateTime2)).transact
//        _ <- Ns.i.offsetTime.insert((1, offsetTime1), (1, offsetTime2), (1, offsetTime2), (2, offsetTime2)).transact
//        _ <- Ns.i.offsetDateTime.insert((1, offsetDateTime1), (1, offsetDateTime2), (1, offsetDateTime2), (2, offsetDateTime2)).transact
//        _ <- Ns.i.zonedDateTime.insert((1, zonedDateTime1), (1, zonedDateTime2), (1, zonedDateTime2), (2, zonedDateTime2)).transact
//        _ <- Ns.i.uuid.insert((1, uuid1), (1, uuid2), (1, uuid2), (2, uuid2)).transact
//        _ <- Ns.i.uri.insert((1, uri1), (1, uri2), (1, uri2), (2, uri2)).transact
//        _ <- Ns.i.byte.insert((1, byte1), (1, byte2), (1, byte2), (2, byte2)).transact
//        _ <- Ns.i.short.insert((1, short1), (1, short2), (1, short2), (2, short2)).transact
//        _ <- Ns.i.char.insert((1, char1), (1, char2), (1, char2), (2, char2)).transact
//        _ <- Ns.i.ref.insert((1, ref1), (1, ref2), (1, ref2), (2, ref2)).transact

        _ <- Ns.i.string(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
        /*
FlatEmbed(0, None, false,
  Ns, , , ListBuffer(i, string), , , , ,
  {"_id": 0, "i": 1, "string": 1}
)
         */

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.B.i.insert(List(
          (1, 1),
          (2, 2),
          (2, 2),
          (2, 3),
        )).transact

//        _ <- B.i(count).query.get.map(_ ==> List(4))
        _ <- A.B.i(count).query.get.map(_ ==> List(4))
        /*
FlatEmbed(0, None, false,
  A, , , ListBuffer(), , , , ,
  {"_id": 0, "b": {"i": 1}}
)
  FlatRef(0, Some(A), false,
    A, b, B, ListBuffer(i), , , b., b_,
    {"i": 1}
  )
         */



        _ <- A.i.B.i.OwnC.s.i.insert(List(
          (1, 1, "a", 1),
          (1, 2, "a", 2),
          (1, 2, "b", 2),
          (2, 2, "b", 3),
        )).transact

        _ <- A.B.OwnC.i(count).query.get.map(_ ==> List(
          4
        ))
/*

FlatEmbed(0, None, false,
  A, , , ListBuffer(), , , , ,
  {"_id": 0, "b": {"ownC": {"i": 1}}}
)
  FlatRef(0, Some(A), false,
    A, b, B, ListBuffer(ownC.i), , , b., b_,
    {"ownC": {"i": 1}}
  )
    FlatEmbed(0, Some(A), false,
      B, ownC, C, ListBuffer(ownC.i), ownC., ownC_, b.ownC., b_ownC_,
      {"i": 1}
    )
 */



//        _ <- A.i.a1.B.i(count).query.get.map(_ ==> List(
//          (1, 1),
//          (2, 3)
//        ))

//        _ <- A.B.i(countDistinct).query.get.map(_ ==> List(3))
//        _ <- A.i.a1.B.i(countDistinct).query.get.map(_ ==> List(
//          (1, 1),
//          (2, 2)
//        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //
    //        _ <- Uniques.int.insert(1, 2, 3).transact
    //
    //        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
    //
    //        // Turning around with first cursor leads nowhere
    //        _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
    //
    //      } yield ()
    //    }


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
