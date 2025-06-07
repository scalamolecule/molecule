package molecule.db.datalog.datomic

import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.ast.{AttrOneManInt, AttrOneManString, DataModel, V}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class AdhocJVM_datomic_async extends Test with DbProviders_datomic with TestUtils {

    "types" - types { implicit conn =>
      import molecule.db.compliance.domains.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
//        _ <- Entity.int(3).save.transact
//        _ <- Entity.int.query.get.map(_ ==> List(3))


//        _ <- Entity.string("a").int(1).save.transact
//
//        _ <- Entity.string.int.query.inspect.map(_ ==>
//          """========================================
//            |QUERY:
//            |DataModel(
//            |  List(
//            |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 7)),
//            |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 8))
//            |  ),
//            |  Set(7, 8), 0, 0
//            |)
//            |
//            |[:find  ?b ?c
//            | :where [?a :Entity/string ?b]
//            |        [?a :Entity/int ?c]]
//            |----------------------------------------
//            |""".stripMargin
//        )
//
//        // Or get the DataModel directly
//        _ = Entity.string.int.query.dataModel ==> DataModel(
//          List(
//            AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 7)),
//            AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 8))
//          ),
//          Set(7, 8), 0, 0
//        )
//
//        // returns query result - prints the above
//        _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1)))

        _ <- Entity.i.int(avg).a1.Refs.*(Ref.string("foo")).query.inspect.map(_ ==>
        """========================================
          |QUERY:
          |DataModel(
          |  List(
          |    AttrOneManInt("Entity", "i", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1)),
          |    AttrOneManDouble("Entity", "int", Fn(avg,None), Seq(), None, None, Nil, Nil, None, Some("a1"), false, List(0, 8)),
          |    Nested(
          |      Ref("Entity", "refs", "Ref", CardSet, false, List(0, 53, 1)),
          |      List(
          |        AttrOneManString("Ref", "string", Eq, Seq("foo"), None, None, Nil, Nil, None, None, false, List(1, 101))))
          |  ),
          |  Set(1, 8, 53, 101), -1, 0
          |)
          |
          |[:find  ?id0 ?b
          |        (avg ?c) ?e
          | :with  ?a
          | :in    $ [?e ...]
          | :where [?a :Entity/i ?b]
          |        [?a :Entity/int ?c]
          |        [(identity ?a) ?id0]
          |        [?a :Entity/refs ?d]
          |        [?d :Ref/string ?e]]
          |
          |List(foo)
          |----------------------------------------
          |""".stripMargin
        )

        _ = Entity.int(?).string.query.inspect



      } yield ()
    }


  //    "unique" - unique { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Types._
  //      for {
  //        _ <- Entity.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //
  //
  //        "validation" - validation { implicit conn =>
  //          import molecule.db.compliance.domains.dsl.Validation._
  //          for {
  //
  //            id <- MandatoryRefB.i(1).RefB.i(2).save.transact.map(_.id)
  //
  //            _ <- MandatoryRefB(id).refB().update.i.transact
  //              .map(_ ==> "Unexpected success").recover {
  //                case ModelError(error) =>
  //                  error ==>
  //                    """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                      |  MandatoryRefB.refB
  //                      |""".stripMargin
  //              }
  //
  //          } yield ()
  //        }

  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
  //
  //        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
  //
  //        // Mandatory refs can be removed as long as some ref ids remain
  //        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
  //
  //        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
  //        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                  |  MandatoryRefsB.refsB
  //                  |""".stripMargin
  //          }
  //      } yield ()
  //    }
}