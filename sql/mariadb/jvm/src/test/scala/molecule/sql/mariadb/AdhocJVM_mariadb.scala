package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {



        List(ref1, ref2, ref3, ref4) <- Ref.i.insert(1, 2, 3, 4).transact.map(_.ids)
        a = (1, Set(ref1, ref2))
        b = (2, Set(ref2, ref3, ref4))

        _ <- Ns.i.refs.insert(List(a, b)).transact

        _ <- Ns.i.a1.refs.has(ref2).query.i.get.map(_ ==> List(a, b))



        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  i = 2
        //            |WHERE
        //            |  i IS NOT NULL AND
        //            |  Ns.bigIntSet IS NOT NULL AND
        //            |   JSON_CONTAINS(Ns.bigIntSet, JSON_ARRAY("1"))
        //            |""".stripMargin)

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.bigIntSeq,
//            |  Ref.bigInt_
//            |FROM Ns
//            |  INNER JOIN Ref ON Ns.ref = Ref.id
//            |WHERE
//            |  JSON_CONTAINS(Ns.bigIntSeq, JSON_ARRAY('1')) AND
//            |  Ns.bigIntSeq IS NOT NULL AND
//            |  Ref.bigInt_  IS NOT NULL;
//            |""".stripMargin, true)

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  bigDecimal,
//            |  CAST(bigDecimal AS char),
//            |  convert(CAST(bigDecimal AS char), decimal(65, 38)),
//            |  cast(bigDecimal as decimal(65, 38)),
//            |  convert(bigDecimal, char),
//            |  '------',
//            |  convert('1.1', decimal(65, 38)),
//            |  cast(convert('1.1', decimal(65, 38)) as char),
//            |  '------',
//            |  JSON_CONTAINS('["1.1", "2.2"]', JSON_ARRAY('1.1')),
//            |  JSON_CONTAINS('["1.1", "2.2"]', JSON_ARRAY(bigDecimal))
//            |FROM Ref
//            |""".stripMargin, true)

//        _ <- rawQuery(
//          """SELECT name from
//            |  JSON_TABLE('{"x": ["1.1", "2.2"]}',
//            |          '$.[*]' COLUMNS (
//            |          name  varchar(10) path '$.x'
//            |          )
//            |) AS alias
//            |""".stripMargin, true)

//        _ <- rawQuery(
//          """select (
//            |SELECT count(name) = 1 from
//            |  JSON_TABLE('["1.1", "2.2"]',
//            |          '$[*]' COLUMNS (
//            |          name  varchar(10) path '$'
//            |          )
//            |) AS alias
//            |          where cast(name as decimal(65, 38)) in('1.1')
//            |          ) as x
//            |""".stripMargin, true)
//            |          where JSON_CONTAINS(JSON_ARRAY('1.1'), json_array(name))
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.bigDecimalSeq,
//            |  Ref.bigDecimal
//            |FROM Ns
//            |  INNER JOIN Ref ON Ns.ref = Ref.id
//            |WHERE
//            |  (
//            |    SELECT count(_v) > 0
//            |    FROM
//            |      JSON_TABLE(
//            |        Ns.bigDecimalSeq, '$[*]'
//            |        COLUMNS(_v varchar(65) path '$')
//            |      ) AS alias
//            |    WHERE CONVERT(_v, DECIMAL(65, 38)) IN (Ref.bigDecimal)
//            |  ) AND
//            |  Ns.bigDecimalSeq IS NOT NULL AND
//            |  Ref.bigDecimal   IS NOT NULL;
//            |""".stripMargin, true)
//



        //        _ <- rawQuery(
        //          """select id from Ns
        //            |""".stripMargin, true)


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      val pint20 = "b" -> 21
      val pint30 = "c" -> 30
      for {

        //        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        //        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.Bb.*(B.s_?.iMap_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Map(pint1, pint2))),
            (Some("a"), None),
            (Some("b"), Some(Map(pint2, pint3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iMap(Map(pint3, pint4)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.i.get.map(_ ==> List(
          //          (1, List()), //                            no change to entity without relationship to B
          (2, List(
            // (None, None),                         no relationship to B
            (None, Some(Map(pint1, pint2))), //      no change without filter match
            (Some("a"), None), //                    no value to update
            (Some("b"), Some(Map(pint3, pint4))), // B attribute updated
          ))
        ))

        /*
        ========================================
QUERY:
AttrOneManInt("A", "i", V, Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 1))
NestedOpt(
  Ref("A", "bb", "B", CardSet, false, Seq(0, 14, 1)),
  List(
    AttrOneOptString("B", "s", V, None, None, None, Nil, Nil, None, Some("a1"), Seq(1, 28)),
    AttrMapOptInt("B", "iMap", V, None, None, None, Nil, Nil, None, None, Seq(1, 27))))

SELECT DISTINCT
  A.id,
  A.i,
  B.s,
  B.iMap
FROM A
  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
  LEFT JOIN B      ON A_bb_B.B_id = B.id
WHERE
  A.i IS NOT NULL
ORDER BY A.i, B.s;
----------------------------------------
???????????????  aggregate    false
???????????????  groupBy      LinkedHashSet()
???????????????  groupByCols  LinkedHashSet(A.i, A.id, B.s)



========================================
QUERY:
AttrOneManInt("A", "i", V, Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 1))
NestedOpt(
  Ref("A", "bb", "B", CardSet, false, Seq(0, 14, 1)),
  List(
    AttrOneOptString("B", "s", V, None, None, None, Nil, Nil, None, Some("a1"), Seq(1, 28)),
    AttrMapOptInt("B", "iMap", V, None, None, None, Nil, Nil, None, None, Seq(1, 27))))

SELECT DISTINCT
  A.id,
  A.i,
  B.s,
  B.iMap
FROM A
  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
  LEFT JOIN B      ON A_bb_B.B_id = B.id
WHERE
  A.i IS NOT NULL
GROUP BY A.id, A.i, B.s
ORDER BY A.i, B.s;
----------------------------------------
???????????????  aggregate    true
???????????????  groupBy      LinkedHashSet(A.id)
???????????????  groupByCols  LinkedHashSet(A.i, B.s)
         */

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSeq, '$[*]' COLUMNS (v INT PATH '$')) as list
        //            |      WHERE  list.v NOT IN (3, 4) and B.id is not null
        //            |    )
        //            |WHERE
        //            |  B.iSeq is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      val attr = database match {
        case "Mysql" => "string_"
        case _       => "string"
      }


      for {

        //            _ <- Uniques.i(1).i(2).int_(1).update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can't transact duplicate attribute Uniques.i"
        //              }
        //
        //            _ <- Uniques.i_(1).i(2).update.transact
        //
        //            _ <- Uniques.int_(1).string_("x").s("c").update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can only apply one unique attribute value for update. Found:\n" +
        //                  s"""AttrOneTacString("Uniques", "$attr", Eq, Seq("x"), None, None, Nil, Nil, None, None, Seq(0, 3))"""
        //              }
        //
        //            _ <- Uniques.intSet_(1).s("b").update.transact
        //              .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //                err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        //                  """AttrSetTacInt("Uniques", "intSet", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 25))"""
        //              }


        //
        //            _ <- Uniques.int.insert(1, 2, 3).transact
        //
        //            c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
        //
        //            // Turning around with first cursor leads nowhere
        //            _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }




        _ <- Uniques.int.insert(1, 2).transact


      } yield ()
    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        _ <- Type.string("a").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        _ <- Type.string.insert("a").transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

        id <- Type.string("c").save.transact.map(_.id)
        _ <- Type(id).string("a").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
                   |""".stripMargin
          }

      } yield ()
    }
  }
}