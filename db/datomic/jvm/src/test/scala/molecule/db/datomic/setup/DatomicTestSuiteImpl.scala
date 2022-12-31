package molecule.db.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.DatomicPeerProxy
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.schema._
import molecule.db.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import molecule.db.datomic.util.DatomicApiLoader
import moleculeBuildInfo.BuildInfo
import scala.concurrent.Await
import scala.concurrent.duration._

trait DatomicTestSuiteImpl extends DatomicApiLoader { self: DatomicTestSuite =>

  lazy val isJsPlatform_ = false
  lazy val protocol_     = BuildInfo.datomicProtocol
  lazy val useFree_      = BuildInfo.datomicUseFree

  // Needed to make api visible to classloader when using Datomic Free
  //  require("datomic.api")

  def inMem[T](
    test: DatomicConn_JVM => T,
    schemaTx: SchemaTransaction,
  ): T = {
    val dbUri                    = if (protocol_ == "mem") "" else {
      println(s"Re-creating live database...")
      "localhost:4334/" + randomUUID().toString
    }
    val (schema, nsMap, attrMap, uniqueAttrs) = (
      Seq(
        schemaTx.datomicPartitions,
        schemaTx.datomicSchema,
        schemaTx.datomicAliases
      ),
      schemaTx.nsMap,
      schemaTx.attrMap,
      schemaTx.uniqueAttrs,
    )

    val proxy = DatomicPeerProxy("mem", "", schema, nsMap, attrMap, uniqueAttrs)

    // Block to enable supplying Connection instead of Future[Connection] to tests
    val conn = Await.result(
      DatomicPeer.recreateDbFromEdn(proxy, protocol_, dbUri, useFree_),
      2.seconds
    )
    test(conn)
  }

  def typesImpl[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema)
  def uniqueImpl[T](test: Connection => T): T = inMem(test, UniqueSchema)

  //  def emptyImpl[T](test: Connection => T): T = inMem(test, EmptySchema, "")
  //  def typesImpl[T](test: Future[Connection] => T): T = inMem(test, TypesSchema)
  //  def refsImpl[T](test: Future[Connection] => T): T = inMem(test, RefsSchema)
  //  def uniqueImpl[T](test: Future[Connection] => T): T = inMem(test, UniqueSchema)

  //  def corePeerOnlyImpl[T](test: Connection => T): T = if (system == SystemPeer) coreImpl(test) else ().asInstanceOf[T]
  //  def bidirectionalImpl[T](test: Connection => T): T = inMem(test, BidirectionalSchema, "m_bidirectional")
  //  def partitionImpl[T](test: Connection => T): T = inMem(test, PartitionTestSchema, "m_partitions")
  //  def nestedImpl[T](test: Connection => T): T = inMem(test, NestedSchema, "m_nested")
  //  def selfJoinImpl[T](test: Connection => T): T = inMem(test, SelfJoinSchema, "m_selfjoin")
  //  def aggregateImpl[T](test: Connection => T): T = inMem(test, AggregatesSchema, "m_aggregates")
  //  def socialNewsImpl[T](test: Connection => T): T = inMem(test, SocialNewsSchema, "m_socialNews")
  //  def graphImpl[T](test: Connection => T): T = inMem(test, GraphSchema, "m_graph")
  //  def graph2Impl[T](test: Connection => T): T = inMem(test, Graph2Schema, "m_graph2")
  //  def modernGraph1Impl[T](test: Connection => T): T = inMem(test, ModernGraph1Schema, "m_modernGraph1")
  //  def modernGraph2Impl[T](test: Connection => T): T = inMem(test, ModernGraph2Schema, "m_modernGraph2")
  //  def productsImpl[T](test: Connection => T): T = inMem(test, ProductsOrderSchema, "m_productsOrder")
  //  def seattleImpl[T](test: Connection => T): T = inMem(test, SeattleSchema, "m_seattle")
  //
  //  // Connecting to existing MBrainz database without recreating it
  //  def mbrainzImpl[T](test: Connection => Future[T]): Future[T] = {
  //    implicit val futConn: Connection = system match {
  //      case SystemPeer =>
  //        DatomicPeer
  //          .connect(MBrainzSchema, "dev", "localhost:4334/mbrainz-1968-1973")
  //
  //      case SystemDevLocal =>
  //        Datomic_DevLocal("datomic-samples", datomicHome)
  //          .connect(MBrainzSchema, "dev", "mbrainz-subset")
  //
  //      case SystemPeerServer =>
  //        Datomic_PeerServer("k", "s", "localhost:8998")
  //          .connect(MBrainzSchema, "mbrainz-1968-1973")
  //    }
  //    for {
  //      conn <- futConn
  //      // todo: uncomment when refactorings done
  ////      upperCase <- Schema.a(":Artist/name").get
  ////      _ <- if (upperCase.isEmpty) {
  ////        // Add uppercase-namespaced attribute names so that we can access the externally
  ////        // transacted lowercase names with uppercase names of the molecule code.
  ////        println("Adding uppercase namespace names to MBrainz database..")
  ////        conn.transact(MBrainzSchemaLowerToUpper.edn)
  ////      } else Future.unit
  //      res <- test(futConn)
  //    } yield res
  //  }
}
