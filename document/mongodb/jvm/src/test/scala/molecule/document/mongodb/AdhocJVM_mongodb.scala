package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
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
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.i.query.get.map(_ ==> List(int1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1))
      val b = (2, Set(2))
      val c = (3, Set(3))
      for {


        //        _ <- A.i.ii.insert(
        //          (1, Set(1)),
        //          (1, Set(2)),
        //          (2, Set.empty[Int])
        //        ).transact
        //
        //
        //        _ <- A.i.ii.query.get.map(_ ==> List(
        //          (1, Set(1, 2)),
        //        ))
        //
        //        _ <- A.i.ii_?.query.i.get.map(_ ==> List(
        //          (1, Some(Set(1, 2))),
        //          (2, None),
        //        ))







        _ <- A.i.ii.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact

        _ <- A.i.a1.ii.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))

        _ <- A.i.a1.ii(Set(1)).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii(Set(1, 2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))

        _ <- A.i.a1.ii.not(Set(1)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set(1, 2)).query.get.map(_ ==> List(
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))

        _ <- A.i.a1.ii.has(1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.ii.has(2).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.ii.has(2, 7).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
        ))
        _ <- A.i.a1.ii.has(2, 3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (3, Set(3)),
        ))

        _ <- A.i.a1.ii.hasNo(1).query.get.map(_ ==> List(
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(2).query.get.map(_ ==> List(
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7))
        ))



        _ <- A.i.a1.ii_.query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_(Set(1)).query.get.map(_ ==> List())
        _ <- A.i.a1.ii_(Set(1, 2)).query.get.map(_ ==> List(1))

        _ <- A.i.a1.ii_.not(Set(1)).query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.ii_.not(Set(1, 2)).query.get.map(_ ==> List(2, 3))

        _ <- A.i.a1.ii_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.ii_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 7).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_.hasNo(1).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(3).query.get.map(_ ==> List(1, 2))



        _ <- A.i.a1.ii_?.query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
          (4, None)
        ))

        _ <- A.i.a1.ii_?(Some(Set(1))).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii_?(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        _ <- A.i.a1.ii_?.not(Some(Set(1))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))), // 2 rows coalesced
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.not(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))), // 2 rows coalesced
          (3, Some(Set(3))),
        ))

        _ <- A.i.a1.ii_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))
        _ <- A.i.a1.ii_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // same as
        _ <- A.i.a1.ii_?.has(Some(Seq(2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.ii_?.has(Some(Seq(2, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
          (3, Some(Set(3))),
        ))
        // has 2 and 3
        _ <- A.i.a1.ii_?.has(Some(Set(2, 3))).query.get.map(_ ==> List(

        ))
        _ <- A.i.a1.ii_?.has(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        _ <- A.i.a1.ii_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))), // 2 rows coalesced
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(Set(7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7)))
        ))




















//        _ <- A.i.ii.insert(
//          (1, Set(1)),
//          (2, Set(2)),
//          (2, Set(7)),
//          (3, Set(3)),
//        ).transact
//
//        _ <- A.i.ii.query.get.map(_ ==> List(
//          (1, Set(1)),
//          (2, Set(2, 7)),
//          (3, Set(3)),
//        ))
//
//        _ <- A.i.ii_?.query.get.map(_ ==> List(
//          (1, Some(Set(1))),
//          (2, Some(Set(2, 7))),
//          (3, Some(Set(3))),
//        ))
//
//
//
//
//        _ <- A.i.B.ii.insert(a, b, c).transact
//
//        //        _ <- B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//        //        _ <- B.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
//        //        _ <- B.ii.not(Set(2)).query.get.map(_ ==> List(Set(1, 3)))
//        //        _ <- B.ii.has(2).query.get.map(_ ==> List(Set(2)))
//        //        _ <- B.ii.hasNo(2).query.get.map(_ ==> List(Set(1, 3)))
//        //
//        //        _ <- A.B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//        //        _ <- A.B.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
//        //        _ <- A.B.ii.not(Set(2)).query.get.map(_ ==> List(Set(1, 3)))
//        //        _ <- A.B.ii.has(2).query.get.map(_ ==> List(Set(2)))
//        //        _ <- A.B.ii.hasNo(2).query.get.map(_ ==> List(Set(1, 3)))
//        //
//        //        _ <- A.i.B.ii.query.get.map(_ ==> List(a, b, c))
//        //        _ <- A.i.B.ii(Set(2)).query.get.map(_ ==> List(b))
//        //        _ <- A.i.B.ii.not(Set(2)).query.get.map(_ ==> List(a, c))
//        //        _ <- A.i.B.ii.has(2).query.get.map(_ ==> List(b))
//        //        _ <- A.i.B.ii.hasNo(2).query.get.map(_ ==> List(a, c))
//        //
//        //
//        //
//        //        _ <- A.i.a1.B.ii_.query.get.map(_            ==> List(1, 2, 3))
//        //        _ <- A.i.a1.B.ii_(Set(2)).query.get.map(_    ==> List(2))
//        //        _ <- A.i.a1.B.ii_.not(Set(2)).query.get.map(_ ==> List(1, 3))
//        //        _ <- A.i.a1.B.ii_.has(2).query.get.map(_      ==> List(2))
//        //        _ <- A.i.a1.B.ii_.hasNo(2).query.get.map(_   ==> List(1, 3))
//
//        _ <- B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//        _ <- A.B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//        _ <- A.B.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
//        _ <- A.i.B.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
//        _ <- A.i_.B.ii_?.query.get.map(_ ==> List(Some(Set(1, 2, 3))))
//        //        _ <- A.i_.B.ii_?(Some(Set(2))).query.get.map(_ ==> List(Some(Set(2))))
//        //        _ <- A.i_.B.ii_?.not(Some(Set(2))).query.get.map(_ ==> List(Some(Set(1)),Some(Set(3))))
//        //        _ <- A.i_.B.ii_?.has(Some(2)).query.get.map(_ ==> List(Some(Set(2))))
//        //        _ <- A.i_.B.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(Some(Set(1)), Some(Set(3))))
//
//
//
//
//
//
//        _ <- A.i.B.ii.insert(
//          (1, Set(1)),
//          (2, Set(2)),
//          (3, Set(3)),
//        ).transact
//
//        _ <- B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//
//        _ <- A.i_.B.ii.query.get.map(_ ==> List(Set(1, 2, 3)))
//
//        _ <- A.i.B.ii.query.get.map(_ ==> List(
//          (1, Set(1)),
//          (2, Set(2)),
//          (3, Set(3)),
//        ))
//
//        _ <- A.i_.B.ii(Set(2)).query.get.map(_ ==> List(Set(2)))
//
//        //        _ = {
//        //          println("===============================================")
//        //          println("------- A")
//        //          val a = Peer.q(
//        //            """[:find  ?a
//        //              | :in    $ [?d-set ...]
//        //              | :where [?a :A/i ?b]
//        //              |        [?a :A/b ?c]
//        //              |        [?c :B/ii ?d]
//        //              |        [(datomic.api/q
//        //              |          "[:find (distinct ?d1)
//        //              |            :in $ ?c1
//        //              |            :where [?c1 :B/ii ?d1]]" $ ?c) [[?d2]]]
//        //              |        [(into #{} ?d-set) ?d-set1]
//        //              |        [(= ?d2 ?d-set1)]]
//        //              |""".stripMargin, conn.db, Util.list(Util.list(2))
//        //          )
//        //          a.forEach { r => println(r) }
//        //
//        //          val preIds  = new java.util.HashSet[Long](a.size())
//        //          a.forEach { row =>
//        //            preIds.add(row.get(0).asInstanceOf[Long])
//        //          }
//        //
//        ////          println("------- B")
//        ////          val b = Peer.q(
//        ////            """[:find  ?a
//        ////              | :in    $ [?d-set ...]
//        ////              | :where [?a :A/i ?b]
//        ////              |        [?a :A/b ?c]
//        ////              |        [?c :B/ii ?d]
//        ////              |        [(datomic.api/q
//        ////              |          "[:find (distinct ?d1)
//        ////              |            :in $ ?c1
//        ////              |            :where [?c1 :B/ii ?d1]]" $ ?c) [[?d2]]]
//        ////              |        [(into #{} ?d-set) ?d-set1]
//        ////              |        [(= ?d2 ?d-set1)]]
//        ////              |""".stripMargin, conn.db, Util.list(Util.list(2))
//        ////          )
//        ////          b.forEach { r => println(r) }
//        //
//        //          println("------- C")
//        //          val c = Peer.q(
//        //            """[:find  ?b
//        //              |        (distinct ?d)
//        //              |?d-blacklist  ?d-blacklisted
//        //              | :in    $ ?d-blacklist
//        //              | :where [?a :A/i ?b]
//        //              |        [?a :A/b ?c]
//        //              |        [?c :B/ii ?d]
//        //              |        [(contains? ?d-blacklist ?c) ?d-blacklisted]
//        //              |        [(not ?d-blacklisted)]]
//        //              |""".stripMargin, conn.db, preIds
//        //          )
//        //          c.forEach { r => println(r) }
//        //        }
//
//        _ <- A.i.B.ii.not(Set(2)).query.get.map(_ ==> List(
//          (1, Set(1)),
//          (3, Set(3)),
//        ))
//
//        _ <- A.i_.B.ii.not(Set(2)).query.get.map(_ ==> List(Set(1, 3)))
//        _ <- A.i_.B.ii.has(2).query.get.map(_ ==> List(Set(2)))
//        _ <- A.i_.B.ii.hasNo(2).query.i.get.map(_ ==> List(Set(1, 3)))




        //        _ <- A.i.OwnBb.*(B.ii).insert(List((2, List(Set(3, 4))))).i.transact
        //        _ <- A.i.OwnBb.*(B.ii).query.i.get.map(_ ==> List((2, List(Set(3, 4)))))


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
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //
    //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.i.transact.map(_.id)
    //
    //        // We can remove a value from a Set as long as it's not the last value
    //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
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
  }
}
