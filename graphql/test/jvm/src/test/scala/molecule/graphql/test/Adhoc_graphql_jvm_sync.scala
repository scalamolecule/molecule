package molecule.graphql.test

import molecule.core.setup.{MUnit, TestUtils}
import ujson.*


class Adhoc_graphql_jvm_sync extends MUnit with TestUtils {


  val url = "https://swapi-graphql.netlify.app/graphql"

  def graphql(query: String): String = {
    val data         = Obj("query" -> Str(query))
    val dataRendered = data.render()
    println(dataRendered)
    val response = requests.post(
      url = url,
      headers = Seq("Content-Type" -> "application/json"),
      data = data.render()
    )
    response.text()
  }


  lazy val hero = graphql(
    """query MyQuery {
      |  person(personID: 1) {
      |    name
      |  }
      |}""".stripMargin
  )
  //
  //  lazy val types = graphql(
  //    """{
  //          __schema {
  //            types {
  //              name
  //            }
  //          }
  //       }"""
  //  )
  //
  //  lazy val luke = graphql(
  //    """{
  //         person(personID: 1) {
  //           id
  //           name
  //         }
  //       }"""
  //  )

  //  println("-----------------")
  println(hero)
  //  println("-----------------")
  //  println(types)
  //  println("-----------------")
  //  println(luke)
  //  println("-----------------")


  "basic" - {

    1 ==> 1

  }

  //  "Subscription" - types { implicit conn =>
  //    var intermediaryCallbackResults = List.empty[List[Int]]
  //
  //    // Initial data
  //    Entity.i(1).save.transact
  //
  //    // Start subscription
  //    Entity.i.query.subscribe { freshResult =>
  //      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
  //    }
  //
  //    // Mutations to be monitored by subscription
  //    val id = Entity.i(2).save.transact.id
  //    Entity.i.a1.query.get ==> List(1, 2)
  //
  //    Entity.i.insert(3, 4).transact
  //    Entity.i.a1.query.get ==> List(1, 2, 3, 4)
  //
  //    Entity(id).i(20).update.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4, 20)
  //
  //    Entity(id).delete.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4)
  //
  //    // Mutations with no callback-involved attributes don't call back
  //    Entity.string("foo").save.transact
  //
  //    // Callback catched all intermediary results correctly
  //    intermediaryCallbackResults.map(_.sorted) ==> List(
  //      List(1, 2), // query result after 2 was saved
  //      List(1, 2, 3, 4), // query result after 3 and 4 were inserted
  //      List(1, 3, 4, 20), // query result after 2 was updated to 20
  //      List(1, 3, 4), // query result after 20 was deleted
  //    )
  //  }


  //  "commit" - refs { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.i.insert(1, 2, 3).transact
  //    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  //  }
}
