//package molecule.db.datomic.marshalling
//
//import molecule.base.util.exceptions.MoleculeError
//import molecule.boilerplate.ast.Model._
//import molecule.boilerplate.util.MoleculeLogging
//import molecule.core.api.TxReport
//import molecule.core.marshalling.Boopicklers._
//import molecule.core.marshalling._
//import molecule.core.marshalling.pack.Tpls2DTO
//import molecule.core.marshalling.unpack.DTO2tpls
//import molecule.core.transaction.{Delete, Insert, Save, Update}
//import molecule.core.util.Executor._
//import molecule.core.util.{JavaConversions, ModelUtils}
//import molecule.db.datomic.api.action.DatomicQueryApiImpl
//import molecule.db.datomic.transaction._
//import molecule.db.datomic.util.DatomicApiLoader
//import scala.concurrent.Future
//
//object DatomicRpcJVM_OLD extends MoleculeRpc
//  with DatomicTxBase_JVM
//  with JavaConversions
//  with DatomicApiLoader
//  with ModelUtils
//  with MoleculeLogging {
//
//  // Api ---------------------------------------------
//
//  override def query(
//    proxy: ConnProxy,
//    elements: List[Element]
//  ): Future[Either[MoleculeError, DTO]] = either {
//    for {
//      conn <- getConn(proxy)
//      rows <- new DatomicQueryApiImpl[Any](elements).get(conn, global)
//    } yield {
//      val tpls = {
//        if (countValueAttrs(elements) == 1) {
//          rows.map(v => Tuple1(v))
//        } else {
//          rows
//        }
//      }
//      Tpls2DTO(elements, tpls.asInstanceOf[Seq[Product]]).pack
//    }
//  }
//
//  override def save(
//    proxy: ConnProxy,
//    elements: List[Element]
//  ): Future[Either[MoleculeError, TxReport]] = either {
//    for {
//      conn <- getConn(proxy)
//      stmts = (new Save() with Save_stmts).getStmts(elements)
//      txReport <- conn.transact(stmts)
//    } yield txReport
//  }
//
//  override def insert(
//    proxy: ConnProxy,
//    tplElements: List[Element],
//    tplData: DTO,
//    txElements: List[Element],
//  ): Future[Either[MoleculeError, TxReport]] = either {
//    for {
//      conn <- getConn(proxy)
//      tpls = if (countValueAttrs(tplElements) == 1) {
//        DTO2tpls[Any](tplElements, tplData).unpack.map(v => Tuple1(v))
//      } else {
//        DTO2tpls[Product](tplElements, tplData).unpack
//      }
//      stmts = (new Insert with Insert_stmts).getStmts(tplElements, tpls)
//      _ = if (txElements.nonEmpty) {
//        val txStmts = (new Save() with Save_stmts)
//          .getRawStmts(txElements, datomicTx, false)
//        stmts.addAll(txStmts)
//      }
//      txReport <- conn.transact(stmts)
//    } yield txReport
//  }
//
//  override def update(
//    proxy: ConnProxy,
//    elements: List[Element],
//    isUpsert: Boolean,
//    isMultiple: Boolean,
//  ): Future[Either[MoleculeError, TxReport]] = either {
//    for {
//      conn <- getConn(proxy)
//      stmts = (new Update(conn.proxy.uniqueAttrs, isUpsert, isMultiple) with Update_stmts)
//        .getStmts(conn, elements)
//      txReport <- conn.transact(stmts)
//    } yield txReport
//  }
//
//  override def delete(
//    proxy: ConnProxy,
//    elements: List[Element],
//    isMultiple: Boolean
//  ): Future[Either[MoleculeError, TxReport]] = either {
//    for {
//      conn <- getConn(proxy)
//      stmts = (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
//      txReport <- conn.transact(stmts)
//    } yield txReport
//  }
//
//
//  override def ping: Future[Int] = Future(9)
//
//
//  private def either[T](fut: Future[T]): Future[Either[MoleculeError, T]] = {
//    // Transfer exceptions in Either to allow Boopickle to serialize
//    fut
//      .map(txR => Right(txR))
//      .recover {
//        case e: MoleculeError => Left(e)
//        case e: Throwable     => Left(MoleculeError(e.getMessage, e))
//      }
//  }
//}
