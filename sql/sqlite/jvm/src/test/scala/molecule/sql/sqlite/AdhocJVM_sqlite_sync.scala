package molecule.sql.sqlite

import molecule.base.error.ValidationErrors
import molecule.core.api.{Api_async, Api_async_transact}
import molecule.core.spi.{Conn, Spi_sync}
import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.sqlite.setup.TestSuite_sqlite
import utest._
import scala.language.implicitConversions
import scala.util.control.NonFatal
import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.sqlite.sync._


//object AdhocJVM_h2 extends TestSuite_h2_array {
object AdhocJVM_sqlite_sync extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._

      Ns.int.insert(1 to 7).transact
      Ns.int(count).query.get.head ==> 7

      Ns.int_.delete.transact
      Ns.int(count).query.get.head ==> 0
    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Validation._

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
}
