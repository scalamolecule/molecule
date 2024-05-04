package molecule.document.mongodb.transaction

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, MongoProxy}
import molecule.core.transaction.ops.BaseOps
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.{MongoConn_JVM, MongoHandler_JVM}
import org.bson._
import org.bson.types.{Decimal128, ObjectId}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait Base_JVM_mongodb extends DataType_JVM_mongodb with ModelUtils with BaseHelpers with BaseOps {

  override def indent(level: Int) = "  " * level

  protected var doPrint = false
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var nsIndex              = 0
  protected val nsDocs               = mutable.Map.empty[String, (Int, BsonArray)]
  protected var embeddedPath         = List.empty[String]
  protected var doc                  = new BsonDocument()
  protected var docs                 = List(List(doc))
  protected var uniqueFilterElements = List.empty[Element]
  protected var filterElements       = List.empty[Element]
  protected var nss                  = Set.empty[String]
  protected var optIds               = Option.empty[Seq[String]]
  //  protected val ids                  = ListBuffer.empty

  protected var level     = 0
  protected var selfJoins = 0
  protected var initialNs = ""

  // "Connection pool" ---------------------------------------------

  // todo: offer real solution
  private val connectionPool = mutable.HashMap.empty[UUID, Future[MongoConn_JVM]]

  //  override def clearConnPool: Future[Unit] = Future {
  //    // logger.debug(s"Connection pool with ${connectionPool.size} connections cleared.")
  //    connectionPool.clear()
  //  }

  protected def getConn(proxy: ConnProxy): Future[MongoConn_JVM] = {
    val futConn             = connectionPool.getOrElse(proxy.uuid, getFreshConn(proxy))
    val futConnTimeAdjusted = futConn.map { conn =>
      conn
    }
    connectionPool(proxy.uuid) = futConnTimeAdjusted
    // logger.debug("connectionPool.size: " + connectionPool.size)
    futConnTimeAdjusted
  }

  private def getFreshConn(proxy: ConnProxy): Future[MongoConn_JVM] = {
    Future(MongoHandler_JVM.recreateDb(proxy.asInstanceOf[MongoProxy]))
  }

  override protected lazy val transformID            : String => Any         = (v: String) => new BsonObjectId(new ObjectId(v))
  override protected lazy val transformString        : String => Any         = (v: String) => new BsonString(v)
  override protected lazy val transformInt           : Int => Any            = (v: Int) => new BsonInt32(v)
  override protected lazy val transformLong          : Long => Any           = (v: Long) => new BsonInt64(v)
  override protected lazy val transformFloat         : Float => Any          = (v: Float) => new BsonDouble(v)
  override protected lazy val transformDouble        : Double => Any         = (v: Double) => new BsonDouble(v)
  override protected lazy val transformBoolean       : Boolean => Any        = (v: Boolean) => new BsonBoolean(v)
  override protected lazy val transformBigInt        : BigInt => Any         = (v: BigInt) => new BsonDecimal128(new Decimal128(BigDecimal(v).bigDecimal))
  override protected lazy val transformBigDecimal    : BigDecimal => Any     = (v: BigDecimal) => new BsonDecimal128(new Decimal128(v.bigDecimal))
  override protected lazy val transformDate          : Date => Any           = (v: Date) => new BsonDateTime(v.getTime)
  override protected lazy val transformDuration      : Duration => Any       = (v: Duration) => new BsonString(v.toString)
  override protected lazy val transformInstant       : Instant => Any        = (v: Instant) => new BsonString(v.toString)
  override protected lazy val transformLocalDate     : LocalDate => Any      = (v: LocalDate) => new BsonString(v.toString)
  override protected lazy val transformLocalTime     : LocalTime => Any      = (v: LocalTime) => new BsonString(v.toString)
  override protected lazy val transformLocalDateTime : LocalDateTime => Any  = (v: LocalDateTime) => new BsonString(v.toString)
  override protected lazy val transformOffsetTime    : OffsetTime => Any     = (v: OffsetTime) => new BsonString(v.toString)
  override protected lazy val transformOffsetDateTime: OffsetDateTime => Any = (v: OffsetDateTime) => new BsonString(v.toString)
  override protected lazy val transformZonedDateTime : ZonedDateTime => Any  = (v: ZonedDateTime) => new BsonString(v.toString)
  override protected lazy val transformUUID          : UUID => Any           = (v: UUID) => new BsonString(v.toString)
  override protected lazy val transformURI           : URI => Any            = (v: URI) => new BsonString(v.toString)
  override protected lazy val transformByte          : Byte => Any           = (v: Byte) => new BsonInt32(v)
  override protected lazy val transformShort         : Short => Any          = (v: Short) => new BsonInt32(v)
  override protected lazy val transformChar          : Char => Any           = (v: Char) => new BsonString(v.toString)

  override protected lazy val extsID = List("ID", "AnyRef", "")

  protected def noEmbeddedIds(exts: List[String]) = {
    if (exts.head == "ID") {
      throw ModelError("Using ids for embedded documents not allowed with MongoDB.")
    }
  }
}
