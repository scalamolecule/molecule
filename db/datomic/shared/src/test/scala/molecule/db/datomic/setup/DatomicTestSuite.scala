package molecule.db.datomic.setup

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.util.Executor._
import molecule.core.util.JavaConversions
import molecule.coreTests.util.AggrUtils
import org.scalactic.TripleEquals
import scribe.Level
import scribe.format._
import utest._
import utest.framework.{Formatter => uFormatter}
import scala.concurrent.Future

trait DatomicTestSuite extends TestSuite with CoreData
  // Platform-specific implementations (JS/JVM) (shows in red as error code in IDE)
  with DatomicTestSuiteImpl
  with JavaConversions
  with TripleEquals
  with AggrUtils {


  // See scribe.format.Formatter
  val logFormatter = Formatter.fromBlocks(
    groupBySecond(
      cyan(bold(dateFull)),
      string(" ["),
      bold(levelColoredPaddedRight),
      string("]"),
      space,
      green(position),
      newLine,
    ),
    multiLine(messages),
    mdc,
  )

  scribe.Logger.root
    .clearHandlers()
    .clearModifiers()
    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Trace))
    .replace()


  lazy val isJsPlatform: Boolean = isJsPlatform_
  lazy val protocol    : String  = protocol_
  lazy val useFree     : Boolean = useFree_

  //  lazy val system: System = {
  //    SystemPeer
  //        SystemDevLocal

  // Since we run asynchronous tests and can't recreate databases against the Peer Server,
  // we can only test reliably by restarting the Peer Server and test a single test at a time.
  //    SystemPeerServer
  //  }

  lazy val platformSystemProtocol = {
    val dbType = if (protocol == "mem") if (useFree) "(free)" else "(pro)" else ""
    (if (isJsPlatform) "JS" else "JVM") + s" Peer $protocol $dbType"
    //      (system match {
    //        case SystemPeer       => s" Peer $protocol $dbType"
    //        case SystemDevLocal   => s" DevLocal $protocol"
    //        case SystemPeerServer => s" PeerServer $protocol"
    //      })
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
      throw MoleculeException("Test unexpectedly passed")
    } catch {
      case MoleculeException(`error`, _)                      => assert(true)
      case e@MoleculeException("Test unexpectedly passed", _) => throw e
      case MoleculeException(other, _)                        =>
        throw MoleculeException(s"Unexpected error message:\n$other\n\nEXPECTED:\n$error\n")
      case unexpected: Throwable                              => throw unexpected
    }
  }

  def pullBooleanBug[Tpl](query: QueryOps[Tpl])(implicit conn: Connection): Future[Unit] = {
    query.get
      .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
      err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
    }
  }

  def types[T](test: Connection => T): T = typesImpl(test)
  def refs[T](test: Connection => T): T = refsImpl(test)
  def unique[T](test: Connection => T): T = uniqueImpl(test)

  //  def empty[T](test: Future[Conn] => T): T = emptyImpl(test)
  //  def types[T](test: Future[Connection] => T): T = typesImpl(test)
  //  def refs[T](test: Future[Connection] => T): T = refsImpl(test)
  //  def unique[T](test: Future[Connection] => T): T = uniqueImpl(test)

  //  def corePeerOnly[T](test: Future[Conn] => T): T = corePeerOnlyImpl(test)
  //  def bidirectional[T](test: Future[Conn] => T): T = bidirectionalImpl(test)
  //  def partition[T](test: Future[Conn] => T): T = partitionImpl(test)
  //  def nested[T](test: Future[Conn] => T): T = nestedImpl(test)
  //  def selfJoin[T](test: Future[Conn] => T): T = selfJoinImpl(test)
  //  def aggregate[T](test: Future[Conn] => T): T = aggregateImpl(test)
  //  def socialNews[T](test: Future[Conn] => T): T = socialNewsImpl(test)
  //  def graph[T](test: Future[Conn] => T): T = graphImpl(test)
  //  def graph2[T](test: Future[Conn] => T): T = graph2Impl(test)
  //  def modernGraph1[T](test: Future[Conn] => T): T = modernGraph1Impl(test)
  //  def modernGraph2[T](test: Future[Conn] => T): T = modernGraph2Impl(test)
  //  def products[T](test: Future[Conn] => T): T = productsImpl(test)
  //  def seattle[T](test: Future[Conn] => T): T = seattleImpl(test)
  //  def mbrainz[T](test: Future[Conn] => Future[T]): Future[T] = mbrainzImpl(test)
  //
  //
  //  // At least 1 ms delay between transactions involving Dates to avoid overlapping
  //  // (can't use Thread.sleep(1000) on js platform)
  //  def delay = (1 to 50000).sum
  //
  //
  //  // Testing schema definitions
  //  def transact(schema: SchemaTransaction)(implicit futConn: Future[Conn]): Future[Seq[TxReport]] = {
  //    for {
  //      conn <- futConn
  //      _ = conn.updateConnProxy(schema)
  //      txrs <- system match {
  //        case SystemPeer       => Future.sequence(schema.datomicPeer.map(edn => conn.transact(edn)))
  //        case SystemDevLocal   => Future.sequence(schema.datomicClient.map(edn => conn.transact(edn)))
  //        case SystemPeerServer => Future.sequence(schema.datomicClient.map(edn => conn.transact(edn)))
  //      }
  //    } yield txrs
  //  }
}
