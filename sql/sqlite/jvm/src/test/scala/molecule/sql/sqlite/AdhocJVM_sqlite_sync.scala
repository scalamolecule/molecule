package molecule.sql.sqlite

import molecule.base.error.ValidationErrors
import molecule.coreTests.setup.{MUnitSuite, TestUtils}
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.sync._
import scala.language.implicitConversions
import scala.util.control.NonFatal


class AdhocJVM_sqlite_sync extends MUnitSuite with DbProviders_sqlite with TestUtils {

  "types" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types._

    Entity.int.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    Entity.int_.delete.transact
    Entity.int(count).query.get.head ==> 0
  }


  "validation" - validation { implicit conn =>
    import molecule.coreTests.domains.dsl.Validation._

    try {
      transact(
        Type.int.insert(5, 6),
        Type.int(1).save,
      )
    } catch {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.int with value `1` doesn't satisfy validation:
             |_ > 2
             |""".stripMargin
      case NonFatal(e)                =>
        println("ERROR: " + e)
    }


    try {
      unitOfWork {
        Type.int.insert(5, 6).transact
        // Updated database is queryable - useful for intermediate checks
        Type.int.query.get ==> List(5, 6)
        // Some error action will roll back the entire uow
        Type.int(1).save.transact
      }
    } catch {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.int with value `1` doesn't satisfy validation:
             |_ > 2
             |""".stripMargin
    }


    try {
      unitOfWork {
        Type.int.insert(5, 6).transact
        // Updated database is queryable - useful for intermediate checks
        Type.int.query.get ==> List(5, 6)
        // Some error action will roll back the entire uow
        Type.int(1).save.transact
      }
    } catch {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.int with value `1` doesn't satisfy validation:
             |_ > 2
             |""".stripMargin
    }


    Type.int.query.get ==> List()
  }
}
