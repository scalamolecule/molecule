package molecule.sql.postgres.marshalling

import java.nio.ByteBuffer
import java.sql.{Connection, ResultSet => Row}
import com.dimafeng.testcontainers.PostgreSQLContainer
import molecule.base.error.{MoleculeError, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.core.action.{Query, QueryCursor, QueryOffset}
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.transaction._
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.core.spi.SpiHelpers
import molecule.sql.core.transaction.{SqlBase_JVM, SqlUpdateSetValidator}
import molecule.sql.postgres.async._
import molecule.sql.postgres.transaction._
import scala.annotation.nowarn
import scala.concurrent.{Future, ExecutionContext => EC}


object Rpc_postgres
  extends MoleculeRpc
    with SqlBase_JVM
    with SpiHelpers
    with SqlUpdateSetValidator
    with FutureUtils {

  val container = PostgreSQLContainer()
  Class.forName(container.driverClassName)

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
      data = new ResolveSave with Save_postgres {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getSaveData(elements)
      txReport <- conn.transact_async(data)
    } yield txReport
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      tplsEither = UnpickleTpls[Any](elements, ByteBuffer.wrap(tplsSerialized)).unpickle
      tpls = tplsEither match {
        case Right(tpls) =>
          (if (countValueAttrs(elements) == 1) {
            tpls.map(Tuple1(_))
          } else tpls).asInstanceOf[Seq[Product]]
        case Left(err)   => throw err // catched in outer either wrapper
      }
      data = new ResolveInsert with Insert_postgres {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getInsertData(proxy.nsMap, elements, tpls)
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
      errors = validateUpdateSet(conn.proxy, elements,
        (query: String) => {
          val ps        = conn.sqlConn.prepareStatement(
            query, Row.TYPE_SCROLL_INSENSITIVE, Row.CONCUR_READ_ONLY
          )
          val resultSet = conn.resultSet(ps.executeQuery())
          resultSet.next()
          resultSet
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
        val data = new ResolveUpdate(conn.proxy, isUpsert) with Update_postgres {
          override lazy val sqlConn: Connection = conn.sqlConn
        }.getUpdateData(elements)
        Future(conn.transact_sync(data))
      }
    } yield txReport
  }

  @nowarn // Accept dynamic type parameter of returned Query
  private def refUpdates(
    elements: List[Element],
    isUpsert: Boolean = false
  )(implicit conn: JdbcConn_JVM, ec: EC): Future[() => List[Long]] = {
    val (idQuery, updateModels) = getIdQuery(elements, isUpsert)
    idQuery.get.map { refIdsResult =>
      val refIds: List[Long] = getRefIds(refIdsResult)
      () => {
        val ids = refIds.zipWithIndex.map {
          case (refId: Long, i) =>
            val updateModel = updateModels(i)(refId)
            val data        = new ResolveUpdate(conn.proxy, isUpsert) with Update_postgres {
              override lazy val sqlConn = conn.sqlConn
            }.getUpdateData(updateModel)
            conn.populateStmts(data)
        }
        // Return TxReport with initial update ids
        ids.head
      }
    }
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = either {
    for {
      conn <- getConn(proxy)
      data = new ResolveDelete with Delete_postgres {
        override lazy val sqlConn: Connection = conn.sqlConn
      }.getDeleteData(elements, proxy.nsMap)
      txReport <- conn.transact_async(data)
    } yield txReport
  }
}
