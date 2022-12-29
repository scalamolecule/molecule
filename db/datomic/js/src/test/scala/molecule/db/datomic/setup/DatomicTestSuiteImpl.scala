package molecule.db.datomic.setup

import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.core.marshalling.WebClient
import molecule.coreTests.dataModels.core.schema._
import molecule.db.datomic.facade.DatomicConn_JS
import moleculeBuildInfo.BuildInfo
import scala.concurrent.Future
import molecule.core.util.Executor._


trait DatomicTestSuiteImpl extends WebClient { self: DatomicTestSuite =>

  lazy val isJsPlatform_ = true
  lazy val protocol_     = BuildInfo.datomicProtocol
  lazy val useFree_      = BuildInfo.datomicUseFree

  def inMem[T](
//    test: Future[Connection] => T,
    test: Connection => T,
    schema: SchemaTransaction
//  ): Future[T] = {
  ): T = {
    //    val (peerSchema, nsMap, attrMap) = (schema.datomicPeer, schema.datomicClient, schema.nsMap, schema.attrMap)

    //    val proxy = system match {
    //      case SystemPeer       => DatomicPeerProxy("mem", "", peerSchema, nsMap, attrMap)
    //      case SystemDevLocal   => DatomicDevLocalProxy("mem", "datomic-samples-temp", datomicHome, "", clientSchema, nsMap, attrMap)
    //      case SystemPeerServer => DatomicPeerServerProxy("k", "s", "localhost:8998", peerServerDb, clientSchema, nsMap, attrMap)
    //    }

//    val conn: Connection = new Connection(schema) {
//      override type Data = this.type
//      override def transact(data: this.type): TxReport = ???
//    }

    val conn = DatomicConn_JS(schema, "localhost", 8080)
    test(conn)
//    Future(test(conn))
  }

  //  def emptyImpl[T](test: Future[Conn] => T): T = inMem(test, EmptySchema, "")
//  def typesImpl[T](test: Future[Connection] => T): T = inMem(test, TypesSchema)
//  def refsImpl[T](test: Future[Connection] => T): T = inMem(test, RefsSchema)
//  def uniqueImpl[T](test: Future[Connection] => T): T = inMem(test, UniqueSchema)

  def typesImpl[T](test: Connection => T): T = inMem(test, TypesSchema)
  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema)
  def uniqueImpl[T](test: Connection => T): T = inMem(test, UniqueSchema)

  //  def corePeerOnlyImpl[T](test: Future[Conn] => T): T = if (system == SystemPeer) coreImpl(test) else ().asInstanceOf[T]
  //  def bidirectionalImpl[T](test: Future[Conn] => T): T = inMem(test, BidirectionalSchema, "m_bidirectional")
  //  def partitionImpl[T](test: Future[Conn] => T): T = inMem(test, PartitionTestSchema, "m_partitions")
  //  def nestedImpl[T](test: Future[Conn] => T): T = inMem(test, NestedSchema, "m_nested")
  //  def selfJoinImpl[T](test: Future[Conn] => T): T = inMem(test, SelfJoinSchema, "m_selfjoin")
  //  def aggregateImpl[T](test: Future[Conn] => T): T = inMem(test, AggregatesSchema, "m_aggregates")
  //  def socialNewsImpl[T](test: Future[Conn] => T): T = inMem(test, SocialNewsSchema, "m_socialNews")
  //  def graphImpl[T](test: Future[Conn] => T): T = inMem(test, GraphSchema, "m_graph")
  //  def graph2Impl[T](test: Future[Conn] => T): T = inMem(test, Graph2Schema, "m_graph2")
  //  def modernGraph1Impl[T](test: Future[Conn] => T): T = inMem(test, ModernGraph1Schema, "m_modernGraph1")
  //  def modernGraph2Impl[T](test: Future[Conn] => T): T = inMem(test, ModernGraph2Schema, "m_modernGraph2")
  //  def productsImpl[T](test: Future[Conn] => T): T = inMem(test, ProductsOrderSchema, "m_productsOrder")
  //  def seattleImpl[T](test: Future[Conn] => T): T = inMem(test, SeattleSchema, "m_seattle")
  //
  //  // Connecting to existing MBrainz database without recreating it
  //  def mbrainzImpl[T](test: Future[Conn] => Future[T]): Future[T] = {
  //    val proxy = system match {
  //      case SystemPeer =>
  //        DatomicPeerProxy(
  //          "dev",
  //          "localhost:4334/mbrainz-1968-1973",
  //          MBrainzSchema.datomicPeer, MBrainzSchema.nsMap, MBrainzSchema.attrMap
  //        )
  //
  //      case SystemDevLocal =>
  //        DatomicDevLocalProxy(
  //          "dev",
  //          "datomic-samples",
  //          datomicHome,
  //          "mbrainz-subset",
  //          MBrainzSchema.datomicClient, MBrainzSchema.nsMap, MBrainzSchema.attrMap
  //        )
  //
  //      case SystemPeerServer =>
  //        DatomicPeerServerProxy(
  //          "k", "s", "localhost:8998",
  //          "mbrainz-1968-1973",
  //          MBrainzSchema.datomicClient, MBrainzSchema.nsMap, MBrainzSchema.attrMap
  //        )
  //    }
  //    test(Future(Conn_Js(proxy, "localhost", 8080)))
  //  }
}
