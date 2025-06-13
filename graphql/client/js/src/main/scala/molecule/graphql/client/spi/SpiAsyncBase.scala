//package molecule.graphql.client.spi
//
//import boopickle.Default.*
//import molecule.base.error.InsertError
//import molecule.db.core.action.*
//import molecule.core.dataModel.*
//import molecule.db.core.spi.{Conn, Renderer, Spi_async, TxReport}
//import molecule.db.core.util.FutureUtils
//import molecule.graphql.client
//import scala.concurrent.{Future, ExecutionContext as EC}
//
//
//trait SpiAsyncBase extends Spi_async with Renderer with FutureUtils {
//
//  // Query --------------------------------------------------------
//
//  override def query_get[Tpl](q: Query[Tpl])(implicit conn0: Conn, ec: EC): Future[List[Tpl]] = {
////    val conn  = conn0.asInstanceOf[JdbcConn_JS]
////    val proxy = conn.proxy.copy(dbView = q.dbView)
////    conn.rpc.query[Tpl](proxy, q.elements, q.optLimit).future
//    ???
//  }
//
//  override def query_subscribe[Tpl](q: Query[Tpl], callback: List[Tpl] => Unit)
//                                   (implicit conn0: Conn, ec: EC): Future[Unit] = {
////    val conn             = conn0.asInstanceOf[JdbcConn_JS]
////    val elements         = q.elements
////    val involvedAttrs    = getAttrNames(elements)
////    val involvedDeleteNs = getInitialNs(elements)
////    val maybeCallback    = (mutationAttrs: Set[String], isDelete: Boolean) => {
////      if (
////        mutationAttrs.exists(involvedAttrs.contains) ||
////          isDelete && mutationAttrs.head.startsWith(involvedDeleteNs)
////      ) {
////        conn.rpc.query[Tpl](conn.proxy, q.elements, q.optLimit).future.map(callback)
////        ()
////      }
////    }
////    Future(conn.addCallback(elements -> maybeCallback))
//    ???
//  }
//
//  override def query_unsubscribe[Tpl](q: Query[Tpl])(implicit conn0: Conn, ec: EC): Future[Unit] = {
////    Future(conn0.removeCallback(q.elements))
//    ???
//  }
//
//  override def query_inspect[Tpl](q: Query[Tpl])(implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectQuery("QUERY", q.elements)
//    ???
//  }
//
//
//  override def queryOffset_get[Tpl](q: QueryOffset[Tpl])
//                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], Int, Boolean)] = {
////    val conn  = conn0.asInstanceOf[JdbcConn_JS]
////    val proxy = conn.proxy.copy(dbView = q.dbView)
////    conn.rpc.queryOffset[Tpl](proxy, q.elements, q.optLimit, q.offset).future
//    ???
//  }
//
//  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])
//                                       (implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectQuery("QUERY (offset)", q.elements)
//    ???
//  }
//
//
//  override def queryCursor_get[Tpl](q: QueryCursor[Tpl])
//                                   (implicit conn0: Conn, ec: EC): Future[(List[Tpl], String, Boolean)] = {
////    val conn  = conn0.asInstanceOf[JdbcConn_JS]
////    val proxy = conn.proxy.copy(dbView = q.dbView)
////    conn.rpc.queryCursor[Tpl](proxy, q.elements, q.optLimit, q.cursor).future
//    ???
//  }
//
//  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])
//                                       (implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectQuery("QUERY (cursor)", q.elements)
//    ???
//  }
//
//
//  // Save --------------------------------------------------------
//
//  override def save_transact(save: Save)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
////    val conn = conn0.asInstanceOf[JdbcConn_JS]
////    val save = save0.copy(elements = noKeywords(save0.elements, conn.proxy))
////    for {
////      _ <- if (save.printInspect) save_inspect(save).map(println) else Future.unit
////      errors <- save_validate(save0) // validate original elements against meta model
////      txReport <- errors match {
////        case errors if errors.isEmpty => conn.rpc.save(conn.proxy, save.elements).future
////        case errors                   => throw ValidationErrors(errors)
////      }
////    } yield {
////      conn.callback(save.elements)
////      txReport
////    }
//    ???
//  }
//
//  override def save_inspect(save: Save)(implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectTx("SAVE", save.elements)
//    ???
//  }
//
//  override def save_validate(save: Save)(implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
////    val proxy = conn.proxy
////    TxModelValidation(proxy.entityMap, proxy.attrMap, "save").validate(save.elements)
//    ???
//  }
//
//
//  // Insert --------------------------------------------------------
//
//  override def insert_transact(insert: Insert)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
////    val conn   = conn0.asInstanceOf[JdbcConn_JS]
////    val insert = insert0.copy(elements = noKeywords(insert0.elements, conn.proxy))
////    for {
////      _ <- if (insert.printInspect) insert_inspect(insert).map(println) else Future.unit
////      errors <- insert_validate(insert0) // validate original elements against meta model
////      txReport <- errors match {
////        case errors if errors.isEmpty =>
////          val tplsSerialized = PickleTpls(insert.elements, true).pickle(Right(insert.tpls))
////          conn.rpc.insert(conn.proxy, insert.elements, tplsSerialized).future
////        case errors                   => throw InsertErrors(errors)
////      }
////    } yield {
////      conn.callback(insert.elements)
////      txReport
////    }
//    ???
//  }
//
//  override def insert_inspect(insert: Insert)(implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectTx("INSERT", insert.elements)
//    ???
//  }
//
//  override def insert_validate(insert: Insert)
//                              (implicit conn: Conn, ec: EC): Future[Seq[(Int, Seq[InsertError])]] = future {
////    InsertValidation.validate(conn, insert.elements, insert.tpls)
//    ???
//  }
//
//
//  // Update --------------------------------------------------------
//
//  override def update_transact(update: Update)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
////    val conn   = conn0.asInstanceOf[JdbcConn_JS]
////    val update = update0.copy(elements = noKeywords(update0.elements, conn.proxy))
////    for {
////      _ <- if (update.printInspect) update_inspect(update).map(println) else Future.unit
////      errors <- update_validate(update0) // validate original elements against meta model
////      txReport <- errors match {
////        case errors if errors.isEmpty => conn.rpc.update(conn.proxy, update.elements, update.isUpsert).future
////        case errors                   => throw ValidationErrors(errors)
////      }
////    } yield {
////      conn.callback(update.elements)
////      txReport
////    }
//    ???
//  }
//
//  override def update_inspect(update: Update)(implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectTx("UPDATE", update.elements)
//    ???
//  }
//
//  override def update_validate(update: Update)
//                              (implicit conn: Conn, ec: EC): Future[Map[String, Seq[String]]] = future {
////    val proxy = conn.proxy
////    if (update.isUpsert && hasRef(update.elements))
////      throw ModelError("Can't upsert referenced attributes. Please update instead.")
////    TxModelValidation(proxy.entityMap, proxy.attrMap, "update").validate(update.elements)
//    ???
//  }
//
//
//  // Delete --------------------------------------------------------
//
//  override def delete_transact(delete: Delete)(implicit conn0: Conn, ec: EC): Future[TxReport] = {
////    val conn   = conn0.asInstanceOf[JdbcConn_JS]
////    val delete = delete0.copy(elements = noKeywords(delete0.elements, conn.proxy))
////    conn.rpc.delete(conn.proxy, delete.elements).future.map { txReport =>
////      conn.callback(delete.elements, true)
////      txReport
////    }
//    ???
//  }
//
//  override def delete_inspect(delete: Delete)(implicit conn: Conn, ec: EC): Future[String] = {
////    renderInspectTx("DELETE", delete.elements)
//    ???
//  }
//
//
//  // Util --------------------------------------
//
//  private def renderInspectTx(label: String, dataModel: DataModel)
//                            (implicit ec: EC): Future[String] = {
////    Future(printRaw("RPC " + label, elements))
//    ???
//  }
//
//  protected def renderInspectQuery(label: String, dataModel: DataModel)
//                                  (implicit ec: EC): Future[String]
//}
