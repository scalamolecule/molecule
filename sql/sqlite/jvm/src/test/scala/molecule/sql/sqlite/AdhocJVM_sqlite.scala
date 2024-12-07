package molecule.sql.sqlite

import molecule.core.util.Executor._
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.TestSuite_sqlite
import utest._
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuite_sqlite_array {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  lazy val tests = Tests {

    "".replace('0', '9')

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

//      val l = List(
//        BigInt("-20"),
//        BigInt("-10"),
//        BigInt("-2"),
//        BigInt("-1"),
//        BigInt("0"),
//        BigInt("1"),
//        BigInt("2"),
//        BigInt("10"),
//        BigInt("20"),
//      )
//
//      val upper = '0' + '9'
//
//      val encoded = l.map {
//        case pos if pos.signum >= 0 => "2" + f"$pos%40s"
//        case neg                    => "1" + (-neg).toString.map(c => (upper - c).toChar).reverse.padTo(40, '9').reverse
//      }
//      encoded.sorted.foreach(println)
//
//      val decoded = encoded.map {
//        case pos if pos.head == '2' => BigInt(pos.tail.trim)
//        case neg                    => BigInt("-" + neg.tail.map(c => (upper - c).toChar))
//      }
//
//      println("\n####################################################################################\n")
//      decoded.foreach(println)


      for {

        //        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        //        _ <- Ns.int(3).save.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        //        _ <- Ns(a).int(10).update.transact
        //        _ <- Ns(b).delete.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

        //        _ <- Ns.bigInt(BigInt("123456789012345678901234567890")).save.transact
        //        _ <- Ns.bigInt.query.get.map(_.head ==> BigInt("123456789012345678901234567890"))

//        ids <- Ns.bigInt.insert(-bigInt1, bigInt2).i.transact.map(_.ids)
////        _ <- Ns(ids).bigInt.absNeg.update.transact
//
//        /*
//                SUBSTR(rest, INSTR(rest, ',') + 1),  -- Remaining text after the first delimiter
//        SUBSTR(rest, 1, INSTR(rest, ',') - 1)  -- Part before the first delimiter
//
//
//
//         */
//
////        _ <- rawQuery(
////          """SELECT DISTINCT
////            |  Ns.bigInt,
////            |  substr(Ns.bigInt, 2)
////            |FROM Ns
////            |WHERE
////            |  Ns.bigInt IS NOT NULL
////            |ORDER BY
////            |   (CASE
////            |        WHEN substr(Ns.bigInt, 1, 1) = '-'
////            |        THEN substr(Ns.bigInt, 2)
////            |        ELSE Ns.bigInt
////            |    END)
////            |
////            |DESC;
////            |""".stripMargin, true
////        )
//        _ <- Ns.bigInt.a1.query.i.get.map(_ ==> List(-bigInt1, bigInt2))
//        _ <- Ns.bigInt.d1.query.i.get.map(_ ==> List(bigInt2, -bigInt1))

//        _ <- Ns.bigInt.d1.query.i.get.map(_ ==> List(-bigInt1, -bigInt2))


//        _ <- Ns.bigInt.insert(-bigInt1, bigInt2).transact
//
//        // bigInt sorted lexicographically in SQlite since we have to save the data as a String
//        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(-bigInt1, bigInt2))
//        _ <- Ns.bigInt.d1.query.get.map(_ ==> List(bigInt2, -bigInt1))
//
//        // We can enforce correct behaviour by sorting the result instead
//        _ <- Ns.bigInt.query.get.map(_.sorted ==> List(-bigInt1, bigInt2))
//        _ <- Ns.bigInt.query.get.map(_.sorted.reverse ==> List(bigInt2, -bigInt1))



        ids <- Ns.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
        _ <- Ns.bigInt.d1.query.get.map(_ ==> List(-bigInt1, bigInt2))

        _ <- Ns(ids).bigInt.negate.update.transact
        _ <- Ns.bigInt.d1.query.get.map(_.sorted.reverse ==> List(bigInt1, -bigInt2))


//        _ <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact
//
//        // BigDecimal sorted lexicographically in SQlite since we have to save the data as a String
//        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(-bigDecimal1, bigDecimal2))
//        _ <- Ns.bigDecimal.d1.query.get.map(_ ==> List(bigDecimal2, -bigDecimal1))
//
//        // We can enforce correct behaviour by sorting the result instead
//        _ <- Ns.bigDecimal.query.get.map(_.sorted ==> List(-bigDecimal1, bigDecimal2))
//        _ <- Ns.bigDecimal.query.get.map(_.sorted.reverse ==> List(bigDecimal2, -bigDecimal1))




//        ids <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
//        _ <- Ns(ids).bigDecimal.negate.update.transact
//        _ <- Ns.bigDecimal_?.a1.query.i.get.map(_.sorted ==> List(-bigDecimal2, bigDecimal1))


//        ids <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
//        _ <- Ns(ids).bigDecimal.absNeg.update.transact
//        _ <- Ns.bigDecimal.query.get.map(_.sorted ==> List(-bigDecimal2, -bigDecimal1))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- A.i.B.?(B.iSet).insert(
          (0, None),
          (1, Some(Set(1, 2))),
        ).transact

        _ <- A.i.a1.B.?(B.iSet).query.get.map(_ ==> List(
          (0, None),
          (1, Some(Set(1, 2))),
        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Uniques._
    //      //          val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      //          val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      //          val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //
    //
    //        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
    //        //        _ <- Uniques.i.float.insert((1, float1)).transact
    //        //        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
    //        //            _ <- Uniques.i.float.insert((2, float2)).transact
    //
    //        _ <- Uniques.i.float.query.get.map(_ ==> List(
    //          (1, 1.1f),
    //          (2, 2.2f),
    //        ))
    //
    //      } yield ()
    //    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Validation._
    //      for {
    //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //
    //        // We can remove a value from a Set as long as it's not the last value
    //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of values
    //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryAttr.hobbies
    //                  |""".stripMargin
    //          }
    //
    //      } yield ()
    //    }
    //
    //    "partitions" - partition { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Partitions._
    //      for {
    //
    //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
    //          .insert("book", "Jan", List("Musician")).transact
    //
    //      } yield ()
    //    }
  }
}
