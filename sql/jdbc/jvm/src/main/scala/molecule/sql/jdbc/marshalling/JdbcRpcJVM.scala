package molecule.sql.jdbc.marshalling

import java.net.URI
import java.nio.ByteBuffer
import java.sql.Connection
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError, MoleculeError, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, QueryCursor, QueryOffset, Update}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.{Conn, TxReport}
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.facade.JdbcConn_JVM
import molecule.sql.jdbc.sync.prepareMultipleUpdates
import molecule.sql.jdbc.transaction._
import scala.concurrent.Future
import scala.concurrent.{Future, ExecutionContext => EC}
import java.sql.{Statement, ResultSet => Row}
import molecule.sql.core.javaSql.ResultSetImpl
import molecule.sql.jdbc.spi.JdbcSpi
import scala.annotation.nowarn


object JdbcRpcJVM
  extends MoleculeRpc
    with JdbcBase_JVM
    with JdbcSpi
    with FutureUtils {

  override lazy val sqlConn: Connection = ???

  /**
   * Tuple type is not marshalled from client to server. So we signal this with
   * the 'Any' type parameter. Model elements are used to pickle the correct types
   * here on the server side. And once wired to the client side we can unpickle
   * the data again from the model and cast to type `Tpl`.
   */
  override def query[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Any]]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- Query[Any](elements, limit, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def queryOffset[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Any], Int, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryOffset[Any](elements, limit, offset, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def queryCursor[Any](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Any], String, Boolean)]] = either {
    for {
      conn <- getConn(proxy)
      tpls <- QueryCursor[Any](elements, limit, cursor, proxy.dbView).get(conn, global)
    } yield tpls
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      stmts = new ResolveSave with Data_Save {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getData(elements)
      txReport <- conn.transact_async(stmts)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tplsEither = UnpickleTpls[Any](tplElements, ByteBuffer.wrap(tplsSerialized)).unpickle
      tplProducts = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(tplElements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err // catched in outer either wrapper
      }
      data = new ResolveInsert with Data_Insert {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getData(proxy.nsMap, tplElements, tplProducts)
      txReport <- conn.transact_async(data)
    } yield txReport
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      errors = validateUpdate(conn.proxy, elements, isUpsert,
        (query: String) => {
          val ps        = conn.sqlConn.prepareStatement(
            query, Row.TYPE_SCROLL_INSENSITIVE, Row.CONCUR_READ_ONLY
          )
          val resultSet = ps.executeQuery()
          resultSet.next()
          new ResultSetImpl(resultSet)
        }
      )
      _ = if (errors.nonEmpty) {
        throw ValidationErrors(errors)
      }
      txReport <- if (isRefUpdate(elements)) {
        // Atomic transaction with updates for each ref namespace
        refUpdates(elements, isUpsert)(conn, global).map { res =>
          conn.atomicTransaction(res)
        }
      } else {
        val data = new ResolveUpdate(conn.proxy.uniqueAttrs, isUpsert) with Data_Update {
          override lazy val sqlConn: Connection = conn.sqlConn
        }.getData(elements)
        Future(conn.transact_sync(data))
      }
    } yield txReport
  }

  @nowarn // Accept dynamic type parameter of returned Query
  private def refUpdates(
    elements: List[Element],
    isUpsert: Boolean = false
  )(implicit conn: JdbcConn_JVM, ec: EC): Future[() => Map[List[String], List[Long]]] = {
    val (idQuery, updateModels) = getIdQuery(elements, isUpsert)
    idQuery.get.map { refIdsResult =>
      val refIds: List[Long] = getRefIds(refIdsResult, idQuery.elements)

      () => {
        val refIdMaps = refIds.zipWithIndex.map {
          case (refId: Long, i) =>
            val updateModel = updateModels(i)(refId)
            val data        = new ResolveUpdate(conn.proxy.uniqueAttrs, isUpsert) with Data_Update {
              override lazy val sqlConn = conn.sqlConn
            }.getData(updateModel)
            conn.populateStmts(data)
        }
        // Return TxReport with initial update ids
        refIdMaps.head
      }
    }
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      data = new ResolveDelete with Data_Delete {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getData(elements, proxy.nsMap)
      txReport <- conn.transact_async(data)
    } yield txReport
  }
}
