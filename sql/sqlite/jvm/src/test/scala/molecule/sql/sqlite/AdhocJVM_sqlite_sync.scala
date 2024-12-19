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
object AdhocJVM_sqlite_sync extends  TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._

      unitOfWork {
        Ns.int.insert(1, 2).transact

        savepoint { sp =>

          Ns.int_(1).delete.transact

          //          implicit val conn2 = conn.getsql.asInstanceOf[Connection]
          //          val sp0 = conn2.setSavepoint()
          //          val sp1 = new SavepointImpl(sp0, () => {conn.rb()})

          //          Ns.int(3).save.transact
          //          Ns.int_.int.+(10).update.transact
          //          Ns.int.query.get ==> List(11, 12)

          sp.rollback()
          //          sp1.rollback()
          //          Ns.int.query.get ==> List(2)
          //          throw new Exception("bam")
        }
        Ns.int.query.get ==> List(1, 2)
      }
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
