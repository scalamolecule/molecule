package molecule.document.mongodb.transaction

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, MongoProxy}
import molecule.core.transaction.ops.BaseOps
import molecule.core.util.Executor._
import molecule.core.util.ModelUtils
import molecule.document.mongodb.facade.{MongoConn_JVM, MongoHandler_JVM}
import org.bson._
import org.bson.types.Decimal128
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

trait Base_JVM_mongodb extends DataType_JVM_mongodb with ModelUtils with BaseHelpers with BaseOps {

  var level = 0
  override def indent(level: Int) = "  " * level
  protected def debug(s: Any) = if (doPrint) println(s) else ()

  protected var doPrint              = false
  protected var initialNs            = ""
  protected var ids                  = Seq.empty[String]
  protected var uniqueFilterElements = List.empty[Element]
  protected var filterElements       = List.empty[Element]

  protected var doc    = new BsonDocument()
  protected var docs   = List(List(doc))
  protected val nsDocs = mutable.Map.empty[String, BsonArray]
  protected var nss    = Set.empty[String]


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

  override protected lazy val handleID             = (v: Any) => new BsonString(v.asInstanceOf[String])
  override protected lazy val handleString         = (v: Any) => new BsonString(v.asInstanceOf[String])
  override protected lazy val handleInt            = (v: Any) => new BsonInt32(v.asInstanceOf[Int])
  override protected lazy val handleLong           = (v: Any) => new BsonInt64(v.asInstanceOf[Long])
  override protected lazy val handleFloat          = (v: Any) => new BsonDouble(v.asInstanceOf[Float])
  override protected lazy val handleDouble         = (v: Any) => new BsonDouble(v.asInstanceOf[Double])
  override protected lazy val handleBoolean        = (v: Any) => new BsonBoolean(v.asInstanceOf[Boolean])
  override protected lazy val handleBigInt         = (v: Any) => new BsonDecimal128(new Decimal128(BigDecimal(v.asInstanceOf[BigInt]).bigDecimal))
  override protected lazy val handleBigDecimal     = (v: Any) => new BsonDecimal128(new Decimal128(v.asInstanceOf[BigDecimal].bigDecimal))
  override protected lazy val handleDate           = (v: Any) => new BsonDateTime(v.asInstanceOf[Date].getTime)
  override protected lazy val handleDuration       = (v: Any) => new BsonString(v.asInstanceOf[Duration].toString)
  override protected lazy val handleInstant        = (v: Any) => new BsonString(v.asInstanceOf[Instant].toString)
  override protected lazy val handleLocalDate      = (v: Any) => new BsonString(v.asInstanceOf[LocalDate].toString)
  override protected lazy val handleLocalTime      = (v: Any) => new BsonString(v.asInstanceOf[LocalTime].toString)
  override protected lazy val handleLocalDateTime  = (v: Any) => new BsonString(v.asInstanceOf[LocalDateTime].toString)
  override protected lazy val handleOffsetTime     = (v: Any) => new BsonString(v.asInstanceOf[OffsetTime].toString)
  override protected lazy val handleOffsetDateTime = (v: Any) => new BsonString(v.asInstanceOf[OffsetDateTime].toString)
  override protected lazy val handleZonedDateTime  = (v: Any) => new BsonString(v.asInstanceOf[ZonedDateTime].toString)
  override protected lazy val handleUUID           = (v: Any) => new BsonString(v.asInstanceOf[UUID].toString)
  override protected lazy val handleURI            = (v: Any) => new BsonString(v.asInstanceOf[URI].toString)
  override protected lazy val handleByte           = (v: Any) => new BsonInt32(v.asInstanceOf[Byte])
  override protected lazy val handleShort          = (v: Any) => new BsonInt32(v.asInstanceOf[Short])
  override protected lazy val handleChar           = (v: Any) => new BsonString(v.asInstanceOf[Char].toString)


  override protected lazy val transformID            : String => Any         = (v: String) => new BsonString(v)
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
}
