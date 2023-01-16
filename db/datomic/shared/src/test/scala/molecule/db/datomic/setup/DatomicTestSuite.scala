package molecule.db.datomic.setup

import molecule.base.util.exceptions.MoleculeError
import molecule.core.api.Connection
import molecule.core.api.action.QueryApi
import molecule.core.util.Executor._
import molecule.core.util.JavaConversions
import molecule.coreTests.util.{AggrUtils, TestData}
import org.scalactic.TripleEquals
import utest._
import utest.framework.{Formatter => uFormatter}
import scala.concurrent.Future

trait DatomicTestSuite extends TestSuite with TestData
  // Platform-specific implementations (JS/JVM) (shows in red as error code in IDE)
  with DatomicTestSuiteImpl
  with JavaConversions
  with TripleEquals
  with AggrUtils {

  lazy val isJsPlatform: Boolean = isJsPlatform_
  lazy val protocol    : String  = protocol_
  lazy val useFree     : Boolean = useFree_

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

  protected def moleculeException(code: => Unit)(error: String): Unit = {
    try {
      code
      throw MoleculeError("Test unexpectedly passed")
    } catch {
      case MoleculeError(`error`, _)                      => assert(true)
      case e@MoleculeError("Test unexpectedly passed", _) => throw e
      case MoleculeError(other, _)                        =>
        throw MoleculeError(s"Unexpected error message:\n$other\n\nEXPECTED:\n$error\n")
      case unexpected: Throwable                          => throw unexpected
    }
  }

  def pullBooleanBug[Tpl](query: QueryApi[Tpl])(implicit conn: Connection): Future[Unit] = {
    query.get
      .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
      err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
    }
  }

  def types[T](test: Connection => T): T = typesImpl(test)
  def refs[T](test: Connection => T): T = refsImpl(test)
  def unique[T](test: Connection => T): T = uniqueImpl(test)
}
