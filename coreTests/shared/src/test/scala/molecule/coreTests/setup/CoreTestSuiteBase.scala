package molecule.coreTests.setup

import molecule.core.util.JavaConversions
import molecule.coreTests.util.{AggrUtils, TestData}
import org.scalactic.TripleEquals

trait CoreTestSuiteBase
  extends TestData
  with JavaConversions
  with TripleEquals
  with AggrUtils {

  val isJsPlatform: Boolean
//  val protocol    : String
//  val useFree     : Boolean

  lazy val platformSystemProtocol = {
    (if (isJsPlatform) "JS" else "JVM")
  }

//  override def utestFormatter: uFormatter = new uFormatter {
//    override def formatIcon(success: Boolean): ufansi.Str = {
//      formatResultColor(success)(
//        (if (success) "+ " else "X ") + platformSystemProtocol
//      )
//    }
//  }
}
