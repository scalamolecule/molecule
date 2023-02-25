package molecule.db.datomic.setup

import molecule.core.util.JavaConversions
import molecule.coreTests.util.{AggrUtils, TestData}
import org.scalactic.TripleEquals
import utest._
import utest.framework.{Formatter => uFormatter}

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
}