package molecule.db.datomic.setup

import java.util.UUID.randomUUID
import molecule.base.api.SchemaTransaction
import molecule.core.api.Connection
import molecule.coreTests.dataModels.core.ref.schema._
import molecule.coreTests.dataModels.core.types.schema._
import molecule.db.datomic.facade.{Conn_Peer, Datomic_Peer}
import molecule.db.datomic.util.DatomicApiLoader
import moleculeBuildInfo.BuildInfo
//import moleculeTests.dataModels.core.bidirectionals.schema.BidirectionalSchema
//import moleculeTests.dataModels.core.ref.schema.{NestedSchema, SelfJoinSchema}
//import moleculeTests.dataModels.core.schemaDef.schema.PartitionTestSchema
//import moleculeTests.dataModels.examples.datomic.dayOfDatomic.schema._
//import moleculeTests.dataModels.examples.datomic.mbrainz.schema.MBrainzSchema
//import moleculeTests.dataModels.examples.datomic.seattle.schema.SeattleSchema
//import moleculeTests.dataModels.examples.gremlin.gettingStarted.schema.{ModernGraph1Schema, ModernGraph2Schema}


trait DatomicTestSuiteImpl extends DatomicApiLoader { self: DatomicTestSuite =>

  lazy val isJsPlatform_ = false
  lazy val protocol_     = BuildInfo.datomicProtocol
  lazy val useFree_      = BuildInfo.datomicUseFree

  // Needed to make api visible to classloader when using Datomic Free
  //  require("datomic.api")


  def inMem[T](
    test: Conn_Peer => T,
    schemaTx: SchemaTransaction,
    db: String
  ): T = {
    val dbUri           = if (protocol_ == "mem") "" else {
      println(s"Re-creating live `$db` database...")
      "localhost:4334/" + randomUUID().toString
    }
    val conn: Conn_Peer = Datomic_Peer.recreateDbFromEdn(schemaTx, protocol_, dbUri)

    //    val futConn = system match {
    //      case SystemPeer       => Datomic_Peer.recreateDbFrom(schemaTx, protocol_, dbUri)
    //      case SystemDevLocal   => Datomic_DevLocal("datomic-samples-temp", datomicHome).recreateDbFrom(schemaTx)
    //      case SystemPeerServer => Datomic_PeerServer("k", "s", "localhost:8998").connect(schemaTx, db)
    //    }
    test(conn)
  }

  //  def emptyImpl[T](test: Connection => T): T = inMem(test, EmptySchema, "")
//  def cardAllImpl[T](test: Connection => T): T = inMem(test, CardAllSchema, "m_cardAll")
  def typesOneImpl[T](test: Connection => T): T = inMem(test, TypesOneSchema, "m_typesOne")
  def typesSetImpl[T](test: Connection => T): T = inMem(test, TypesSetSchema, "m_typesSet")
  def refsImpl[T](test: Connection => T): T = inMem(test, RefsSchema, "m_refs")

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
  //        Datomic_Peer
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
