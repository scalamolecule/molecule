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
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {
//        _ <- Ns.i(1).save.transact
//        _ <- Ns.i.i.query.get.map(_ ==> List(1))

//
//        _ <- Ns.i.string.insert(
//          (1, string1),
//          (1, string2),
//          (1, string2),
//          (2, string2)).i.transact
//
//        _ <- Ns.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).i.transact
//
//        _ <- Ns.i.string(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
//        _ <- Ns.i.int(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))

        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Ns.i.long.insert((1, long1), (1, long2), (1, long3), (2, long4)).transact
        _ <- Ns.i.float.insert((1, float1), (1, float2), (1, float3), (2, float4)).transact
        _ <- Ns.i.double.insert((1, double1), (1, double2), (1, double3), (2, double4)).transact
        _ <- Ns.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt3), (2, bigInt4)).transact
        _ <- Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Ns.i.byte.insert((1, byte1), (1, byte2), (1, byte3), (2, byte4)).transact
        _ <- Ns.i.short.insert((1, short1), (1, short2), (1, short3), (2, short4)).transact

        _ <- Ns.i.int(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.long(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.float(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.double(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigInt(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigDecimal(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.byte(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.short(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))

        _ <- Ns.i.int(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.long(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.float(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.double(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigInt(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigDecimal(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.byte(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.short(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)

      for {

        _ <- A.i.Bb.*(B.i.C.ii).insert(0, List((1, Set(2, 3)))).transact
        _ <- A.i.Bb.*(B.i.C.ii).query.get.map(_ ==> List((0, List((1, Set(2, 3))))))

        List(a1,_, a2,_, a3,_) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)

        _ <- A.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))

///*
//FlatEmbed(0, None, false
//  A, , , ListBuffer(i), , , , ,
//  {"_id": 0, "i": 1, "bb": 1}
//)
//  NestedRef(1, Some(),
//    A, bb, B, ListBuffer(i), , , bb., bb_,
//    true, {"_id": 0, "i": 1, "c": {"ii": 1}}
//  )
//    FlatRef(1, Some(A),
//      B, c, C, ListBuffer(ii), , , bb.c., bb_c_,
//      {"ii": 1}
//    )
//
//
//
//FlatEmbed(0, None, false
//  A, , , ListBuffer(i), , , , ,
//  {"_id": 0, "b": {"ii": 1}}
//)
//  FlatRef(0, Some(A),
//    A, b, B, ListBuffer(ii), , , b., b_,
//    {"ii": 1}
//  )
// */
//
//        _ <- A.i.B.ii.insert(
//          (1, Set(1)),
//          (2, Set(2)),
//          (3, Set(3)),
//        ).transact
//
//        _ <- A.i_.B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))



//        _ <- A.s.Bb.*(B.i_?.s_?).insert(
////          ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),
////
////          ("1a", List((None, Some("x")), (Some(2), Some("y")))),
////          ("1b", List((Some(1), None), (Some(2), Some("y")))),
//          ("1c", List((Some(1), Some("x")), (None, Some("y")))),
////          ("1d", List((Some(1), Some("x")), (Some(2), None))),
////
////          ("2a", List((None, None), (Some(2), Some("y")))),
////          ("2b", List((None, Some("x")), (None, Some("y")))),
////          ("2c", List((None, Some("x")), (Some(2), None))),
////          ("2d", List((Some(1), None), (None, Some("y")))),
////          ("2e", List((Some(1), None), (Some(2), None))),
////          ("2f", List((Some(1), Some("x")), (None, None))),
////
////          ("3a", List((None, None), (None, Some("y")))),
////          ("3b", List((None, None), (Some(2), None))),
////          ("3c", List((None, Some("x")), (None, None))),
////          ("3d", List((Some(1), None), (None, None))),
////
////          ("4", List((None, None), (None, None))),
////
////          ("a", Nil),
//        ).transact
//
//        _ <- A.s.a1.Bb.*?(B.i_?.a1.s_?.a2).query.get.map(_ ==> List(
////          ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),
////
////          ("1a", List((None, Some("x")), (Some(2), Some("y")))),
////          ("1b", List((Some(1), None), (Some(2), Some("y")))),
//          ("1c", List((None, Some("y")), (Some(1), Some("x")))), // None sorted first
////          ("1d", List((Some(1), Some("x")), (Some(2), None))),
////
////          ("2a", List((Some(2), Some("y")))), // (None, None) not included
////          ("2b", List((None, Some("x")), (None, Some("y")))),
////          ("2c", List((None, Some("x")), (Some(2), None))),
////          ("2d", List((None, Some("y")), (Some(1), None))), // None sorted first
////          ("2e", List((Some(1), None), (Some(2), None))),
////          ("2f", List((Some(1), Some("x")))), // (None, None) not included
////
////          ("3a", List((None, Some("y")))),
////          ("3b", List((Some(2), None))),
////          ("3c", List((None, Some("x")))),
////          ("3d", List((Some(1), None))),
////
////          ("4", Nil), // List((None, None), (None, None)) collapsing to Nil
////
////          ("a", Nil),
//        ))
//
//
//
//
//
//        _ <- A.i.B.i.insert(
//          (2, 3),
//          (1, 4),
//          (1, 3),
//          (7, 3)
//        ).transact
//
//        // Since sort markers can't attach to tacit filter attributes there's no sorting ambiguity
//
//        _ <- A.i.<(B.i_).a1.B.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))

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
