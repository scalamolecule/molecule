package molecule.db.datalog.datomic.compliance.inspection

import molecule.core.dataModel.{AttrOneManInt, AttrOneManString, DataModel, V}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class Test_Inspect extends MUnit with DbProviders_datomic with TestUtils {


  "Inspect query" - types { implicit conn =>
    for {
      _ <- Entity.string("a").int(1).save.transact

      // Inspect query without returning data
      _ <- Entity.string.int.query.inspect.map(_ ==>
        """========================================
          |QUERY:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
          |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
          |  ),
          |  Set(10, 11), 0, 0, Nil
          |)
          |
          |[:find  ?b ?c
          | :where [?a :Entity/string ?b]
          |        [?a :Entity/int ?c]]
          |----------------------------------------
          |""".stripMargin
      )

      // Or get the DataModel
      _ = Entity.string.int.query.dataModel ==> DataModel(
        List(
          AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
          AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
        ),
        Set(10, 11), 0, 0
      )

      // Return query result and print the above inspection output to console
      _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without saving" - types { implicit conn =>
    for {
      // Inspect save action without saving
      _ <- Entity.string("a").int(1).save.inspect.map(_ ==>
        """========================================
          |SAVE:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, false, List(0, 10)),
          |    AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, false, List(0, 11))
          |  ),
          |  Set(10, 11), 0, 0, Nil
          |)
          |
          |[
          |  [:db/add #db/id[db.part/user -1] :Entity/string a]
          |  [:db/add #db/id[db.part/user -1] :Entity/int 1]
          |]
          |----------------------------------------
          |""".stripMargin
      )

      // Save action was inspected without saving
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and save" - types { implicit conn =>
    for {
      // Save data and print inspection
      _ <- Entity.string("a").int(1).save.i.transact

      // Save action was inspected and data saved
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without inserting" - types { implicit conn =>
    for {
      // Inspect insert action without inserting
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).inspect.map(_ ==>
        """========================================
          |INSERT:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
          |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
          |  ),
          |  Set(10, 11), 0, 0, Nil
          |)
          |
          |[
          |  [:db/add #db/id[db.part/user -1] :Entity/string a]
          |  [:db/add #db/id[db.part/user -1] :Entity/int 1]
          |  [:db/add #db/id[db.part/user -2] :Entity/string b]
          |  [:db/add #db/id[db.part/user -2] :Entity/int 2]
          |]
          |----------------------------------------
          |""".stripMargin
      )

      // Insert action was inspected without inserting
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and insert" - types { implicit conn =>
    for {
      // Insert data and print inspection
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).i.transact

      // Insert action was inspected and data inserted
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }


  "Inspect without updating" - types { implicit conn =>
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Inspect update action without updating
      _ <- Entity(id).string("ZZZ").update.inspect.map(_ ==>
        """========================================
          |UPDATE:
          |DataModel(
          |  List(
          |    AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, false, List(0, 0)),
          |    AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, false, List(0, 10))
          |  ),
          |  Set(10), 0, 0, Nil
          |)
          |
          |[
          |  [:db/add 17592186045418 :Entity/string ZZZ]
          |]
          |----------------------------------------
          |""".stripMargin
      )

      // Update was inspected without updating
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }

  "Inspect and update" - types { implicit conn =>
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Update data and print inspection
      _ <- Entity(id).string("ZZZ").update.i.transact

      // Update was inspected and date updated
      _ <- Entity.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
    } yield ()
  }


  "Inspect without deleting" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Inspect delete action without deleting
      _ <- Entity(a).delete.inspect.map(_ ==>
        """========================================
          |DELETE:
          |DataModel(
          |  List(
          |    AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, false, List(0, 0))
          |  ),
          |  Set(), 0, 0, Nil
          |)
          |
          |[
          |  [:db/retractEntity 17592186045418]
          |]
          |----------------------------------------
          |""".stripMargin
      )

      // Deletion was inspected without deleting
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }

  "Inspect and delete" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Delete data and print inspection
      _ <- Entity(a).delete.i.transact

      // Deletion was inspected and date deleted
      _ <- Entity.string.int.query.get.map(_ ==> List(("b", 2)))
    } yield ()
  }
}