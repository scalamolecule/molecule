package molecule.rest.spi

import molecule.base.error.InsertError
import molecule.core.ast.DataModel._
import molecule.core.action._
import molecule.core.marshalling.ConnProxy
import molecule.core.spi.{Conn, Renderer, SpiSync, TxReport}
import molecule.core.util.{FutureUtils, JavaConversions}
import molecule.rest.facade.GraphqlConn_JVM
import molecule.rest.transaction.GraphqlDataType_JVM

object SpiSync_rest extends SpiSync_rest

trait SpiSync_rest
  extends SpiSync
//    with DatomicSpiSyncBase
//    with JVMDatomicSpiBase
    with GraphqlDataType_JVM
    with Renderer
    with FutureUtils
    with JavaConversions {

  override def query_get[Tpl](q: Query[Tpl])(implicit conn: Conn): List[Tpl] = {
//    if (q.doInspect) query_inspect(q)
//    val m2q = new Model2DatomicQuery[Tpl](q.elements)
//    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
//      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])._1
    ???
  }

  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)(implicit conn: Conn): Unit = {
//    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
//    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
//    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
//      .subscribe(datomicConn, callback)
    ???
  }

  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
//    val datomicConn = conn.asInstanceOf[DatomicConn_JVM]
//    val m2q         = new Model2DatomicQuery[Tpl](q.elements)
//    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, None, q.dbView, m2q)
//      .unsubscribe(datomicConn)
    ???
  }

  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn): Unit = {
//    printInspectQuery("QUERY", q.elements)
    ???
  }

  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): (List[Tpl], Int, Boolean) = {
//    if (q.doInspect) queryOffset_inspect(q)
//    val m2q = new Model2DatomicQuery[Tpl](q.elements)
//    DatomicQueryResolveOffset[Tpl](q.elements, q.optLimit, Some(q.offset), q.dbView, m2q)
//      .getListFromOffset_sync(conn.asInstanceOf[DatomicConn_JVM])
    ???
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(implicit conn: Conn): Unit = {
//    printInspectQuery("QUERY (offset)", q.elements)
    ???
  }

  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): (List[Tpl], String, Boolean) = {
//    if (q.doInspect) queryCursor_inspect(q)
//    val m2q = new Model2DatomicQuery[Tpl](q.elements)
//    DatomicQueryResolveCursor[Tpl](q.elements, q.optLimit, Some(q.cursor), q.dbView, m2q)
//      .getListFromCursor_sync(conn.asInstanceOf[DatomicConn_JVM])
    ???
  }
  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(implicit conn: Conn): Unit = {
//    printInspectQuery("QUERY (cursor)", q.elements)
    ???
  }


  override def save_transact(save: Save)(implicit conn: Conn): TxReport = {
//    await(Spi_datomic_async.save_transact(save)(conn, global))
    ???
  }
  override def save_inspect(save: Save)(implicit conn: Conn): Unit = {
//    printInspectTx("SAVE", save.elements, save_getStmts(save))
    ???
  }
  override def save_validate(save: Save)(implicit conn: Conn): Map[String, Seq[String]] = {
//    val proxy = conn.proxy
//    TxModelValidation(proxy.schema.entityMap, proxy.schema.attrMap, "save").validate(save.elements)
    ???
  }

  def save_getStmts(save: Save): Data = {
//    (new ResolveSave with Save_datomic).getStmts(save.elements)
    ???
  }

  override def insert_transact(insert: Insert)(implicit conn: Conn): TxReport = {
//    await(Spi_datomic_async.insert_transact(insert)(conn, global))
    ???
  }
  override def insert_inspect(insert: Insert)(implicit conn: Conn): Unit = {
//    printInspectTx("INSERT", insert.elements, insert_getStmts(insert, conn.proxy))
    ???
  }
  override def insert_validate(insert: Insert)(implicit conn: Conn): Seq[(Int, Seq[InsertError])] = {
//    InsertValidation.validate(conn, insert.elements, insert.tpls)
    ???
  }
  def insert_getStmts(insert: Insert, proxy: ConnProxy): Data = {
//    (new ResolveInsert with Insert_datomic)
//      .getStmts(proxy.schema.entityMap, insert.elements, insert.tpls)
    ???
  }

  override def update_transact(update: Update)(implicit conn: Conn): TxReport = {
//    await(Spi_datomic_async.update_transact(update)(conn, global))
    ???
  }
  override def update_inspect(update: Update)(implicit conn: Conn): Unit = {
//    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
//    printInspectTx(action, update.elements, update_getStmts(update, conn.asInstanceOf[DatomicConn_JVM]))
    ???
  }
  override def update_validate(update: Update)(implicit conn: Conn): Map[String, Seq[String]] = {
//    validateUpdate(conn, update)
    ???
  }
  def update_getStmts(update: Update, conn: GraphqlConn_JVM): Data = {
//    (new ResolveUpdate(update.isUpsert) with Update_datomic)
//      .getStmts(conn, update.elements)
    ???
  }

  override def delete_transact(delete: Delete)(implicit conn: Conn): TxReport = {
//    await(Spi_datomic_async.delete_transact(delete)(conn, global))
    ???
  }
  override def delete_inspect(delete: Delete)(implicit conn: Conn): Unit = {
//    printInspectTx("DELETE", delete.elements, delete_getStmts(delete, conn.asInstanceOf[DatomicConn_JVM]))
    ???
  }

  def delete_getStmts(delete: Delete, conn: GraphqlConn_JVM): Data = {
//    (new ResolveDelete with Delete_datomic).getData(conn, delete.elements)
    ???
  }


  // Fallbacks --------------------------------------------------------

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(implicit conn: Conn): List[List[Any]] = {
//    Peer.q(query, conn.db.asInstanceOf[AnyRef]).asScala.toList.map(_.asScala.toList.map(toScala(_)))
    ???
  }

  private def toScala(
    value: Any,
    depth: Int = 1,
    maxDepth: Int = 5,
  ): Any = {
//    def retrieve(value: Any): Any = value match {
//      case v: java.lang.String                => v
//      case v: java.lang.Integer               => v.toLong: Long
//      case v: java.lang.Long                  => v: Long
//      case v: java.lang.Float                 => v: Float
//      case v: java.lang.Double                => v: Double
//      case v: java.lang.Boolean               => v: Boolean
//      case v: Date                            => v
//      case v: UUID                            => v
//      case v: java.net.URI                    => v
//      case v: clojure.lang.BigInt             => BigInt(v.toString)
//      case v: java.math.BigInteger            => BigInt(v)
//      case v: java.math.BigDecimal            => BigDecimal(v)
//      case vs: Array[Byte]                    => vs
//      case kw: clojure.lang.Keyword           => kw.toString
//      case vs: clojure.lang.PersistentHashSet => vs.asInstanceOf[java.util.Collection[_]].asScala.map(retrieve).toSet
//      case vs: clojure.lang.PersistentVector  => vs.asInstanceOf[java.util.Collection[_]].asScala.map(retrieve).toSet
//
//      case vs: clojure.lang.PersistentArrayMap =>
//        @tailrec
//        def flat(set: Set[Any]): Set[Any] = {
//          set.head match {
//            case _: Set[_] => flat(set.asInstanceOf[Set[Set[Any]]].flatten)
//            case _         => set
//          }
//        }
//        // Flatten single Set
//        flat(vs.values.asScala.map(retrieve).toSet)
//
//      case col: jCollection[_] =>
//        new Iterable[Any] {
//          override def iterator: Iterator[Any] = new Iterator[Any] {
//            private val jIter = col.iterator.asInstanceOf[java.util.Iterator[AnyRef]]
//            override def hasNext = jIter.hasNext
//            override def next(): Any = if (depth < maxDepth)
//              retrieve(jIter.next())
//            else
//              jIter.next()
//          }
//          override def isEmpty = col.isEmpty
//          override def size: Int = col.size
//          override def toString = col.toString
//        }
//
//      case None       => None
//      case null       => null
//      case unexpected => new Exception(
//        "Unexpected Datalog type to convert: " + unexpected.getClass.toString
//      )
//    }
//    retrieve(value)
    ???
  }


  override def fallback_rawTransact(
    txData: String,
    debug: Boolean = false
  )(implicit conn: Conn): TxReport = {
//    try {
//      import molecule.core.util.Executor.global
//      Await.result(Spi_datomic_async.fallback_rawTransact(txData, debug)(conn, global), 10.seconds)
//      ???
//    } catch {
//      case t: Throwable => throw ModelError(t.toString)
//    }
    ???
  }


  private def printInspectTx(label: String, elements: List[Element], stmts: Data): Unit = {
//    val edn = stmts.asScala.map(_.asScala.mkString("  [", " ", "]")).toList.mkString("[\n", "\n", "\n]")
//    printRaw(label, elements, edn)
    ???
  }
}
