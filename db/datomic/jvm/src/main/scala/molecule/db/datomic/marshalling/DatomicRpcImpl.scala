package molecule.db.datomic.marshalling

import java.io.StringReader
import java.util.{UUID, List => jList}
import datomic.Util._
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.marshalling._
import molecule.core.util.Executor._
import molecule.core.util.JavaConversions
import molecule.db.datomic.api.ops.DatomicQueryOpsImpl
import molecule.db.datomic.facade.{DatomicConn_JVM, DatomicPeer}
import molecule.db.datomic.transaction.DatomicDataType_JVM
import scala.collection.mutable
import scala.concurrent.Future

object DatomicRpcImpl extends MoleculeRpc
  with BooPicklers
  //  with ClojureBridge
  with DatomicDataType_JVM
  with JavaConversions {

  // Api ---------------------------------------------

  override def transact(
    elements: Seq[Element]
  ): Future[Either[MoleculeException, TxReport]] = {
    ???
  }

  override def transactEdn(
    proxy: ConnProxy,
    edn: String,
    uriAttrs: Set[String] = Set.empty[String]
  ): Future[Either[MoleculeException, TxReport]] = {
    for {
      conn <- getConn(proxy)
      javaStmts = getJavaStmts(edn, uriAttrs)
      _ = javaStmts.forEach(s => println(s))

      txReport <- conn.transact(javaStmts)
        .flatMap(txR => Future(Right(txR)))
        .recoverWith {
          case e: MoleculeException => Future(Left(e))
          case e: Throwable         => Future(Left(MoleculeException(e.getMessage, e)))
        }
    } yield txReport
  }

  override def query(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, (Seq[Element], List[List[Int]])]] = {
    getConn(proxy).map { conn =>
      try {
        val sortedRows = new DatomicQueryOpsImpl(elements).getSortedRows(conn)
        Right(DatomicDataTransfer(elements, sortedRows).pack)
      } catch {
        case exc: Throwable => Left(MoleculeException(exc.getMessage, exc))
      }
    }
  }


  // Connection pool ---------------------------------------------

  // todo - this is primitive, is a more correct implementation needed?
  private val connectionPool = mutable.HashMap.empty[UUID, Future[DatomicConn_JVM]]

  private def clearConnPool: Future[Unit] = Future {
    //    println(s"Connection pool with ${connectionPool.size} connections cleared.")
    connectionPool.clear()
  }

  private def getConn(proxy: ConnProxy): Future[DatomicConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      //      conn.updateAdhocDbView(proxy.adhocDbView)
      //      conn.updateTestDbView(proxy.testDbView, proxy.testDbStatus)
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    futConnTimeAdjusted
  }

  private def getFreshConn(proxy: ConnProxy): Future[DatomicConn_JVM] = proxy match {
    case proxy@DatomicPeerProxy(protocol, dbIdentifier, _, _, _, _, _, _, _, _) =>
      protocol match {
        case "mem" =>
          DatomicPeer.recreateDbFromEdn(proxy)
            .recoverWith { case exc =>
              //              printStackTrace(exc)
              Future.failed[DatomicConn_JVM](MoleculeException(exc.getMessage))
            }

        case "free" | "dev" | "pro" =>
          Future(DatomicPeer.connect(proxy, protocol, dbIdentifier))
            .recoverWith { case exc =>
              //              printStackTrace(exc)
              Future.failed[DatomicConn_JVM](MoleculeException(exc.getMessage))
            }

        case other =>
          Future.failed(MoleculeException(
            s"\nCan't serve Peer protocol `$other`."
          ))
      }
  }
  private def getJavaStmts(
    stmtsEdn: String,
    uriAttrs: Set[String]
  ): Data = {
    val stmts = readAll(new StringReader(stmtsEdn)).get(0).asInstanceOf[jList[AnyRef]]
    stmts.asInstanceOf[Data]
    //    if (uriAttrs.isEmpty) {
    //      stmts.asInstanceOf[Data]
    //    } else {
    //      def uri(s: AnyRef): AnyRef = {
    //        // Depends on requiring clojure.core.async
    //        readString(s"""#=(new java.net.URI "$s")""")
    //      }
    //      val stmtsSize = stmts.size()
    //      val newStmts  = new util.ArrayList[jList[_]](stmtsSize)
    //      stmts.forEach { stmtRaw =>
    //        val stmt = stmtRaw.asInstanceOf[jList[AnyRef]]
    //        if (uriAttrs.contains(stmt.get(2).toString)) {
    //          val uriStmt = stmt.get(0).toString match {
    //            case ":db/add"     => list(stmt.get(0), stmt.get(1), stmt.get(2), uri(stmt.get(3)))
    //            case ":db/retract" => list(stmt.get(0), stmt.get(1), stmt.get(2), uri(stmt.get(3)))
    //            case ":db.fn/cas"  => list(stmt.get(0), stmt.get(1), stmt.get(2), uri(stmt.get(3)), uri(stmt.get(4)))
    //            case _             => stmt
    //          }
    //          newStmts.add(uriStmt)
    //        } else {
    //          newStmts.add(stmt)
    //        }
    //      }
    //      Collections.unmodifiableList(newStmts).asInstanceOf[Data]
    //    }
  }

  //  def transact(
  //    proxy: ConnProxy,
  //    stmtsEdn: String,
  //    uriAttrs: Set[String]
  //  ): Future[TxReportRPC] = {
  //    for {
  //      conn <- getConn(proxy)
  //      javaStmts = getJavaStmts(stmtsEdn, uriAttrs)
  //
  //      _ = javaStmts.forEach(s => println(s))
  //
  //      txReport <- conn.transact(javaStmts)
  //    } yield txReportRPC(txReport)
  //  }
  //
  //  def retract(
  //    proxy: ConnProxy,
  //    stmtsEdn: String,
  //    uriAttrs: Set[String]
  //  ): Future[TxReport] = {
  //    println(stmtsEdn)
  //    for {
  //      conn <- getConn(proxy)
  //      javaStmts = getJavaStmts(stmtsEdn, uriAttrs)
  //      txReport <- conn.transact(javaStmts)
  //    } yield txReportRPC(txReport)
  //  }
  //
  //  private def txReportRPC(txReport: TxReport): TxReportRPC = {
  //    TxReportRPC(txReport.t, txReport.tx, txReport.txInstant, txReport.eids, txReport.txData, txReport.toString)
  //  }
  //
  //  def schemaHistoryQuery2packed(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    obj: Obj,
  //    schemaAttrs: Seq[SchemaAttr],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, Int)] = {
  //    for {
  //      conn <- getConn(proxy)
  //      rawRows <- QuerySchemaHistory(conn).fetchSchemaHistory(schemaAttrs, datalogQuery)
  //    } yield {
  //      val rows                          = if (sortCoordinates.nonEmpty)
  //        SortRows(rawRows, sortCoordinates).get
  //      else
  //        new java.util.ArrayList(rawRows)
  //      val (selectedRows, _, totalCount) = Flat2packed(obj, rows, rows.size, -1, 0, "").get
  //      (selectedRows, totalCount)
  //    }
  //  }
  //
  //  def query2packed(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    rules: Seq[String],
  //    l: Seq[(Int, String, String)],
  //    ll: Seq[(Int, String, Seq[String])],
  //    lll: Seq[(Int, String, Seq[Seq[String]])],
  //    limit: Int,
  //    offset: Int,
  //    cursor: String,
  //    cursorQuery: String,
  //    obj: Obj,
  //    nestedLevels: Int,
  //    isOptNested: Boolean,
  //    refIndexes: List[List[Int]],
  //    tacitIndexes: List[List[Int]],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, String, Int)] = Future {
  //    try {
  //      val log       = new log
  //      val t         = TimerPrint("DatomicRpc")
  //      val inputs    = unmarshallInputs(l ++ ll ++ lll)
  //      val allInputs = if (rules.nonEmpty) rules ++ inputs else inputs
  //      for {
  //        conn <- getConn(proxy)
  //        rawRows <- conn.rawQuery(datalogQuery, allInputs)
  //      } yield {
  //        val flatTotalCount = rawRows.size
  //        val queryTime      = t.delta
  //        val space          = " " * (70 - datalogQuery.split('\n').last.length)
  //        val time           = qTime(queryTime)
  //        val timeRight      = " " * (8 - time.length) + time
  //
  //        log("================================================================================")
  //        log(datalogQuery + space + timeRight)
  //        if (allInputs.nonEmpty)
  //          log(allInputs.mkString("Inputs:\n", "\n", ""))
  //        log("-------------------------------")
  //        rawRows.forEach(row => log(row.toString))
  //        log.print()
  //
  //        val (packed, newCursor, totalCountOrMore) = if (flatTotalCount == 0) {
  //          ("", "", 0)
  //        } else if (isOptNested) {
  //          // Optional nested
  //          if (offset > flatTotalCount) {
  //            ("", "", flatTotalCount)
  //          } else {
  //            val sortedRows = if (sortCoordinates.nonEmpty && sortCoordinates.head.nonEmpty)
  //              SortRows(rawRows, sortCoordinates).get else new java.util.ArrayList(rawRows)
  //            OptNested2packed(
  //              obj, sortedRows, flatTotalCount, limit, offset, cursor, refIndexes, tacitIndexes, sortCoordinates
  //            ).get
  //          }
  //
  //        } else if (nestedLevels == 0) {
  //          // Flat
  //          if (offset > flatTotalCount) {
  //            ("", "", flatTotalCount)
  //          } else {
  //            val sortedRows = if (sortCoordinates.flatten.nonEmpty)
  //              SortRows(rawRows, sortCoordinates).get else new java.util.ArrayList(rawRows)
  //            Flat2packed(obj, sortedRows, flatTotalCount, limit, offset, cursor).get
  //          }
  //
  //        } else {
  //          // Nested
  //          val sortedRows = SortRows(rawRows, sortCoordinates).get
  //          Nested2packed(obj, sortedRows, flatTotalCount, limit, offset, cursor, nestedLevels).get
  //        }
  //
  //        //        println(s"-------------------------------" + packed)
  //        (packed, newCursor, totalCountOrMore)
  //      }
  //    } catch {
  //      case NonFatal(exc) => Future.failed(exc)
  //    }
  //  }.flatten
  //
  //
  //  def index2packed(
  //    proxy: ConnProxy,
  //    api: String,
  //    index: String,
  //    indexArgs: IndexArgs,
  //    attrs: Seq[String],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, String, Int)] = {
  //    var totalCountOrMore = 0
  //    for {
  //      conn <- getConn(proxy)
  //      db <- conn.db
  //      packer = PackDatoms(conn, db, attrs, index, indexArgs)
  //      sb <- {
  //        api match {
  //          case "datoms" =>
  //            db match {
  //              case db: DatomicDb_Peer        =>
  //                val datomicIndex = index match {
  //                  case "EAVT" => datomic.Database.EAVT
  //                  case "AEVT" => datomic.Database.AEVT
  //                  case "AVET" => datomic.Database.AVET
  //                  case "VAET" => datomic.Database.VAET
  //                }
  //                db.datoms(datomicIndex, packer.args: _*).flatMap { datoms =>
  //                  if (sortCoordinates.nonEmpty) {
  //                    SortDatoms_Peer(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                      case (count, packed) =>
  //                        totalCountOrMore = count
  //                        packed
  //                    }
  //                  } else {
  //                    val datom2packed = packer.getPeerDatom2packed(None)
  //                    datoms.asScala.foldLeft(Future(new StringBuffer())) {
  //                      case (sbFut, datom) => sbFut.flatMap { sb =>
  //                        totalCountOrMore += 1
  //                        datom2packed(sb, datom)
  //                      }
  //                    }
  //                  }
  //                }
  //              case adhocDb: DatomicDb_Client =>
  //                val datomicIndex = index match {
  //                  case "EAVT" => ":eavt"
  //                  case "AEVT" => ":aevt"
  //                  case "AVET" => ":avet"
  //                  case "VAET" => ":vaet"
  //                }
  //                adhocDb.datoms(datomicIndex, packer.args).flatMap { datoms =>
  //                  if (sortCoordinates.nonEmpty) {
  //                    SortDatoms_Client(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                      case (count, packed) =>
  //                        totalCountOrMore = count
  //                        packed
  //                    }
  //                  } else {
  //                    val datom2packed = packer.getClientDatom2packed(None)
  //                    datoms.iterator.asScala.foldLeft(Future(new StringBuffer())) {
  //                      case (sbFut, datom) => sbFut.flatMap { sb =>
  //                        totalCountOrMore += 1
  //                        datom2packed(sb, datom)
  //                      }
  //                    }
  //                  }
  //                }
  //            }
  //
  //          case "indexRange" =>
  //            db match {
  //              case db: DatomicDb_Peer   =>
  //                val datom2packed = packer.getPeerDatom2packed(None)
  //                val startValue   = if (indexArgs.v.isEmpty) null else castTpeV(indexArgs.tpe, indexArgs.v)
  //                val endValue     = if (indexArgs.v2.isEmpty) null else castTpeV(indexArgs.tpe2, indexArgs.v2)
  //                db.indexRange(indexArgs.a, startValue, endValue).flatMap { datoms =>
  //                  if (sortCoordinates.nonEmpty) {
  //                    SortDatoms_Peer(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                      case (count, packed) =>
  //                        totalCountOrMore = count
  //                        packed
  //                    }
  //                  } else {
  //                    datoms.asScala.foldLeft(Future(new StringBuffer())) {
  //                      case (sbFut, datom) => sbFut.flatMap { sb =>
  //                        totalCountOrMore += 1
  //                        datom2packed(sb, datom)
  //                      }
  //                    }
  //                  }
  //                }
  //              case db: DatomicDb_Client =>
  //                val datom2packed = packer.getClientDatom2packed(None)
  //                val startValue   = if (indexArgs.v.isEmpty) None else Some(castTpeV(indexArgs.tpe, indexArgs.v))
  //                val endValue     = if (indexArgs.v2.isEmpty) None else Some(castTpeV(indexArgs.tpe2, indexArgs.v2))
  //                db.indexRange(indexArgs.a, startValue, endValue).flatMap { datoms =>
  //                  if (sortCoordinates.nonEmpty) {
  //                    SortDatoms_Client(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                      case (count, packed) =>
  //                        totalCountOrMore = count
  //                        packed
  //                    }
  //                  } else {
  //                    datoms.iterator.asScala.foldLeft(Future(new StringBuffer())) {
  //                      case (sbFut, datom) => sbFut.flatMap { sb =>
  //                        totalCountOrMore += 1
  //                        datom2packed(sb, datom)
  //                      }
  //                    }
  //                  }
  //                }
  //            }
  //
  //          case "txRange" =>
  //            // Flatten transaction datoms to uniform tuples return type
  //            conn match {
  //              case conn: DatomicConn_JVM =>
  //                val from  = if (indexArgs.v.isEmpty) null else castTpeV(indexArgs.tpe, indexArgs.v)
  //                val until = if (indexArgs.v2.isEmpty) null else castTpeV(indexArgs.tpe2, indexArgs.v2)
  //                if (sortCoordinates.nonEmpty) {
  //                  val datoms = new util.ArrayList[PeerDatom]()
  //                  conn.peerConn.log.txRange(from, until).asScala.foreach {
  //                    txMap => datoms.addAll(txMap.get(datomic.Log.DATA).asInstanceOf[jList[PeerDatom]])
  //                  }
  //                  SortDatoms_Peer(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                    case (count, packed) =>
  //                      totalCountOrMore = count
  //                      packed
  //                  }
  //                } else {
  //                  conn.peerConn.log.txRange(from, until).asScala.foldLeft(Future(new StringBuffer())) {
  //                    case (sbFut, txMap) =>
  //                      val datom2packed = packer
  //                        .getPeerDatom2packed(Some(txMap.get(datomic.Log.T).asInstanceOf[Long]))
  //                      txMap.get(datomic.Log.DATA).asInstanceOf[jList[PeerDatom]].asScala.foldLeft(sbFut) {
  //                        case (sbFut, datom) => sbFut.flatMap { sb =>
  //                          totalCountOrMore += 1
  //                          datom2packed(sb, datom)
  //                        }
  //                      }
  //                  }
  //                }
  //
  //              case conn: Conn_Client =>
  //                val from  = if (indexArgs.v.isEmpty) None else Some(castTpeV(indexArgs.tpe, indexArgs.v))
  //                val until = if (indexArgs.v2.isEmpty) None else Some(castTpeV(indexArgs.tpe2, indexArgs.v2))
  //                if (sortCoordinates.nonEmpty) {
  //                  val datoms = new util.ArrayList[ClientDatom]()
  //                  conn.clientConn.txRange(from, until).foreach {
  //                    case (_, txDatoms) => txDatoms.foreach(d => datoms.add(d))
  //                  }
  //                  SortDatoms_Client(conn, db, attrs, sortCoordinates, datoms.iterator).getPacked.map {
  //                    case (count, packed) =>
  //                      totalCountOrMore = count
  //                      packed
  //                  }
  //                } else {
  //                  conn.clientConn.txRange(from, until).foldLeft(Future(new StringBuffer())) {
  //                    case (sbFut, (t, datoms)) =>
  //                      val datom2packed: (StringBuffer, ClientDatom) => Future[StringBuffer] =
  //                        packer.getClientDatom2packed(Some(t))
  //                      datoms.foldLeft(sbFut) {
  //                        case (sbFut, datom) => sbFut.flatMap { sb =>
  //                          totalCountOrMore += 1
  //                          datom2packed(sb, datom)
  //                        }
  //                      }
  //                  }
  //                }
  //            }
  //        }
  //      }
  //    } yield {
  //      //      val s = sb.toString
  //      //      println("--------------- packed ----------------" + s)
  //      //      s
  //      (sb.toString, "", totalCountOrMore)
  //    }
  //  }
  //
  //
  //  // Schema ---------------------------------------------
  //
  //  // Presuming a datalog query returning rows of single values.
  //  // Card-many attributes should therefore not be returned as Sets.
  //  def getAttrValues(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    card: Int,
  //    tpe: String
  //  ): Future[List[String]] = {
  //    for {
  //      conn <- getConn(proxy)
  //      rows0 <- conn.rawQuery(datalogQuery)
  //    } yield {
  //      val cast = if (tpe == "Date" && card != 3)
  //        (v: Any) => date2str(v.asInstanceOf[Date])
  //      else
  //        (v: Any) => v.toString
  //      var vs   = List.empty[String]
  //      rows0.forEach(row => vs = vs :+ cast(row.get(0)))
  //      vs
  //    }
  //  }
  //
  //  def getEntityAttrKeys(
  //    proxy: ConnProxy,
  //    datalogQuery: String
  //  ): Future[List[String]] = {
  //    var list = List.empty[String]
  //    for {
  //      conn <- getConn(proxy)
  //      rows <- conn.rawQuery(datalogQuery)
  //    } yield {
  //      rows.forEach { row =>
  //        list = row.get(0).toString :: list
  //      }
  //      list.sorted
  //    }
  //  }
  //
  //  final def getEnumHistory(proxy: ConnProxy)
  //  : Future[List[(String, Int, Long, Date, String, Boolean)]] = {
  //    for {
  //      conn <- getConn(proxy)
  //      res <- getEnumHistory_(conn)
  //    } yield res
  //  }
  //
  //
  //  // Entity api ---------------------------------------------------
  //
  //  final def rawValue(proxy: ConnProxy, eid: Long, attr: String): Future[String] = {
  //    getDatomicEntity(proxy, eid)
  //      .flatMap(_.rawValue(attr))
  //      .map(res => entityList2packed(List(attr -> res)))
  //  }
  //
  //  final def asMap(proxy: ConnProxy, eid: Long, depth: Int, maxDepth: Int): Future[String] = {
  //    getDatomicEntity(proxy, eid).flatMap(_.asMap(depth, maxDepth)).map(entityMap2packed)
  //  }
  //
  //  final def asList(proxy: ConnProxy, eid: Long, depth: Int, maxDepth: Int): Future[String] = {
  //    getDatomicEntity(proxy, eid).flatMap(_.asList(depth, maxDepth)).map(entityList2packed)
  //  }
  //
  //
  //  final def attrs(proxy: ConnProxy, eid: Long): Future[List[String]] = {
  //    getDatomicEntity(proxy, eid).flatMap(_.attrs)
  //  }
  //
  //
  //  final def apply(proxy: ConnProxy, eid: Long, attr: String): Future[String] = {
  //    getDatomicEntity(proxy, eid)
  //      .flatMap(_.apply[Any](attr))
  //      .map(_.fold("")(v => entityMap2packed(Map(attr -> v))))
  //  }
  //
  //  final def apply(proxy: ConnProxy, eid: Long, attrs: List[String]): Future[List[String]] = {
  //    val attr1 :: attr2 :: moreAttrs = attrs
  //    getDatomicEntity(proxy, eid)
  //      .flatMap(_.apply(attr1, attr2, moreAttrs: _*))
  //      .map { optValues =>
  //        attrs.zip(optValues).map { case (attr, optV) =>
  //          optV.fold("")(v => entityMap2packed(Map(attr -> v)))
  //        }
  //      }
  //  }
  //
  //  final def graphDepth(proxy: ConnProxy, eid: Long, maxDepth: Int): Future[String] = {
  //    // Use list to guarantee order of attributes for packing
  //    getDatomicEntity(proxy, eid).flatMap(_.asList(1, maxDepth)).map(entityList2packed)
  //  }
  //
  //  final def graphCode(proxy: ConnProxy, eid: Long, maxDepth: Int): Future[String] = {
  //    getDatomicEntity(proxy, eid).flatMap(_.graphCode(maxDepth))
  //  }
  //
  //  private def getDatomicEntity(proxy: ConnProxy, eid: Any): Future[DatomicEntity] = {
  //    for {
  //      conn <- getConn(proxy)
  //      db <- conn.db
  //    } yield db.entity(conn, eid)
  //  }
  //
  //
  //  // Connection pool ---------------------------------------------
  //
  //  // todo - this is primitive, is a more correct implementation needed?
  //  private val connectionPool = mutable.HashMap.empty[String, Future[Conn_Jvm]]
  //
  //  def clearConnPool: Future[Unit] = Future {
  //    //    println(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }
  //
  //  private def getConn(proxy: ConnProxy): Future[Conn_Jvm] = {
  //    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
  //    val futConnTimeAdjusted = futConn.map { conn =>
  //      conn.updateAdhocDbView(proxy.adhocDbView)
  //      conn.updateTestDbView(proxy.testDbView, proxy.testDbStatus)
  //      conn
  //    }
  //    connectionPool(proxy.uuid) = futConnTimeAdjusted
  //    futConnTimeAdjusted
  //  }
  //
  //  private def printStackTrace(exc: Throwable): Unit = {
  //    println(exc)
  //    exc.getStackTrace.toList.foreach(println)
  //    println("----")
  //  }
  //
  //  private def getFreshConn(proxy: ConnProxy): Future[Conn_Jvm] = proxy match {
  //    case proxy@DatomicPeerProxy(protocol, dbIdentifier, schema, _, _, _, _, _, _) =>
  //      protocol match {
  //        case "mem" =>
  //          DatomicPeer.recreateDbFromEdn(proxy, schema)
  //            .recoverWith { case exc =>
  //              printStackTrace(exc)
  //              Future.failed[Conn_Jvm](MoleculeException(exc.getMessage))
  //            }
  //
  //        case "free" | "dev" | "pro" =>
  //          DatomicPeer.connect(proxy, protocol, dbIdentifier)
  //            .recoverWith { case exc =>
  //              printStackTrace(exc)
  //              Future.failed[Conn_Jvm](MoleculeException(exc.getMessage))
  //            }
  //
  //        case other =>
  //          Future.failed(MoleculeException(
  //            s"\nCan't serve Peer protocol `$other`."
  //          ))
  //      }
  //
  //    case proxy@DatomicDevLocalProxy(protocol, system, storageDir, dbName, schema, _, _, _, _, _, _) =>
  //      val devLocal = Datomic_DevLocal(system, storageDir)
  //      protocol match {
  //        case "mem" =>
  //          devLocal.recreateDbFromEdn(schema, proxy)
  //            .recoverWith { case exc =>
  //              printStackTrace(exc)
  //              Future.failed[Conn_Jvm](MoleculeException(exc.getMessage))
  //            }
  //
  //        case "dev" | "pro" =>
  //          devLocal.connect(proxy, dbName)
  //            .recoverWith { case exc =>
  //              printStackTrace(exc)
  //              Future.failed[Conn_Jvm](MoleculeException(exc.getMessage))
  //            }
  //
  //        case other =>
  //          Future.failed(MoleculeException(
  //            s"\nCan't serve DevLocal protocol `$other`."
  //          ))
  //      }
  //
  //    case proxy@DatomicPeerServerProxy(accessKey, secret, endpoint, dbName, _, _, _, _, _, _, _) =>
  //      Datomic_PeerServer(accessKey, secret, endpoint).connect(proxy, dbName)
  //        .recoverWith { case exc => Future.failed[Conn_Jvm](MoleculeException(exc.getMessage)) }
  //  }
  //
  //
  //  // Helpers -------------------------------------------------
  //
  //  def basisT(proxy: ConnProxy): Future[Long] = {
  //    for {
  //      conn <- getConn(proxy)
  //      db <- conn.db
  //      t <- db.basisT
  //    } yield t
  //  }
  //
  //  // Unmarshall to Datomic java types
  //  private def unmarshallInputs(lists: Seq[(Int, String, Any)]): Seq[Object] = {
  //    lists.sortBy(_._1).map {
  //      case (_, tpe, rawValue) =>
  //        val cast = tpe match {
  //          case "String"     => if (isEnum(rawValue)) (v: String) => getEnum(v) else (v: String) => v
  //          case "Int"        => (v: String) => new java.lang.Long(v)
  //          case "Long"       => (v: String) => new java.lang.Long(v)
  //          case "Double"     => (v: String) => new java.lang.Double(v)
  //          case "Boolean"    => (v: String) => v.toBoolean.asInstanceOf[Object]
  //          case "Date"       => (v: String) => str2date(v).asInstanceOf[Object]
  //          case "URI"        => (v: String) => new java.net.URI(v).asInstanceOf[Object]
  //          case "UUID"       => (v: String) => java.util.UUID.fromString(v).asInstanceOf[Object]
  //          case "BigInt"     => (v: String) => new java.math.BigInteger(v).asInstanceOf[Object]
  //          case "BigDecimal" => (v: String) => new java.math.BigDecimal(v).asInstanceOf[Object]
  //          case "Any"        => (s: String) =>
  //            val v = s.drop(10)
  //            s.take(10) match {
  //              case "String    " => if (isEnum(v)) getEnum(v) else v
  //              case "Int       " => new java.lang.Long(v)
  //              case "Long      " => new java.lang.Long(v)
  //              case "Double    " => new java.lang.Double(v)
  //              case "Boolean   " => v.toBoolean.asInstanceOf[Object]
  //              case "Date      " => str2date(v).asInstanceOf[Object]
  //              case "URI       " => new URI(v).asInstanceOf[Object]
  //              case "UUID      " => UUID.fromString(v).asInstanceOf[Object]
  //              case "BigInt    " => new java.math.BigInteger(v).asInstanceOf[Object]
  //              case "BigDecimal" => new java.math.BigDecimal(v).asInstanceOf[Object]
  //            }
  //          case _            => throw MoleculeException(s"Unexpected type to cast: $tpe")
  //        }
  //
  //        rawValue match {
  //          case l: Seq[_] =>
  //            Util.list(l.collect {
  //              case l2: Seq[_] =>
  //                val Seq(k, v2: String) = l2
  //                Util.list(k.toString.asInstanceOf[Object], cast(v2))
  //
  //              case v1: String => cast(v1)
  //            }: _*)
  //
  //          case v: String => cast(v)
  //          case _         => throw MoleculeException("Unexpected input values")
  //        }
  //    }
  //  }

  // Necessary for `readString` to encode uri in transactions
  //  require("clojure.core.async")


  //
  //  def qTime(queryTime: Long): String = {
  //    val indents = 5 - queryTime.toString.length
  //    " " * indents + thousands(queryTime) + " ms"
  //  }
}
