package molecule.datomic.setup

import molecule.core.api.TxReport
import molecule.core.util.JavaConversions
import molecule.coreTests.util.{AggrUtils, TestData}
import org.scalactic.TripleEquals
import utest._
import utest.framework.{Formatter => uFormatter}
import scala.concurrent.Future
import molecule.core.util.Executor._

trait DatomicTestSuiteBase extends TestSuite
  with TestData
  with JavaConversions
  with TripleEquals
  with AggrUtils {

  val isJsPlatform: Boolean
  val protocol    : String
  val useFree     : Boolean

  lazy val platformSystemProtocol = {
    val dbType = if (protocol == "mem") if (useFree) "(free)" else "(pro)" else ""
    (if (isJsPlatform) "JS" else "JVM") + s" Peer $protocol $dbType"
  }

  override def utestFormatter: uFormatter = new uFormatter {
    override def formatIcon(success: Boolean): ufansi.Str = {
      formatResultColor(success)(
        (if (success) "+ " else "X ") + platformSystemProtocol
      )
    }
  }


  implicit class txResult2expectedError(txR: Future[TxReport]) {
    def expect(error: PartialFunction[Throwable, Any]): Future[Any] = {
      txR.map(_ ==> "Unexpected success").recover(error)
    }
  }

  implicit class querytResult2expectedError[Tpl](data: Future[List[Tpl]]) {
    def expect(error: PartialFunction[Throwable, Any]): Future[Any] = {
      data.map(_ ==> "Unexpected success").recover(error)
    }
  }
  implicit class query2Result2expectedError[Tpl](data: Future[(List[Tpl], Int, Boolean)]) {
    def expect(error: PartialFunction[Throwable, Any]): Future[Any] = {
      data.map(_ ==> "Unexpected success").recover(error)
    }
  }
  implicit class query3Result2expectedError[Tpl](data: Future[(List[Tpl], String, Boolean)]) {
    def expect(error: PartialFunction[Throwable, Any]): Future[Any] = {
      data.map(_ ==> "Unexpected success").recover(error)
    }
  }
}
