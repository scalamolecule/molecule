package molecule.sql.jdbc.spi

import java.net.URI
import java.sql.{ResultSet => RS}
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.{ExecutionError, ModelError, MoleculeError}
import molecule.boilerplate.ast.Model._
import molecule.core.action.Update
import molecule.core.spi.Conn
import molecule.core.util.ModelUtils
import molecule.core.validation.ModelValidation
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.mutable.ListBuffer


trait JVMJdbcSpiBase extends ModelUtils {


  def validateUpdate(conn0: Conn, update: Update): Map[String, Seq[String]] = {
    val conn                           = conn0.asInstanceOf[JdbcConn_jvm]
    val proxy                          = conn.proxy
    val curSetValues: Attr => Set[Any] = (a: Attr) => try {
      val ns        = a.ns
      val attr      = a.attr
      val query     = a.refNs.fold(
        s"""SELECT DISTINCT
           |  ARRAY_AGG($ns.$attr)
           |FROM $ns
           |WHERE
           |  $ns.$attr IS NOT NULL
           |HAVING COUNT(*) > 0;""".stripMargin

      ) { refNs =>
        val joinTable = s"${ns}_${attr}_$refNs"
        s"""SELECT DISTINCT
           |  ARRAY_AGG($joinTable.${refNs}_id)
           |FROM $ns
           |INNER JOIN $joinTable ON $ns.id = $joinTable.${ns}_id
           |GROUP BY $ns.id;""".stripMargin
      }
      val ps        = conn.sqlConn.prepareStatement(query, RS.TYPE_SCROLL_INSENSITIVE, RS.CONCUR_READ_ONLY)
      val resultSet = ps.executeQuery()
      resultSet.next()
      a match {
        case _: AttrSetManString     => nestedArray2coalescedSetString(resultSet)
        case _: AttrSetManInt        => nestedArray2coalescedSetInt(resultSet)
        case _: AttrSetManLong       => nestedArray2coalescedSetLong(resultSet)
        case _: AttrSetManFloat      => nestedArray2coalescedSetFloat(resultSet)
        case _: AttrSetManDouble     => nestedArray2coalescedSetDouble(resultSet)
        case _: AttrSetManBoolean    => nestedArray2coalescedSetBoolean(resultSet)
        case _: AttrSetManBigInt     => nestedArray2coalescedSetBigInt(resultSet)
        case _: AttrSetManBigDecimal => nestedArray2coalescedSetBigDecimal(resultSet)
        case _: AttrSetManDate       => nestedArray2coalescedSetDate(resultSet)
        case _: AttrSetManUUID       => nestedArray2coalescedSetUUID(resultSet)
        case _: AttrSetManURI        => nestedArray2coalescedSetURI(resultSet)
        case _: AttrSetManByte       => nestedArray2coalescedSetByte(resultSet)
        case _: AttrSetManShort      => nestedArray2coalescedSetShort(resultSet)
        case _: AttrSetManChar       => nestedArray2coalescedSetChar(resultSet)
        case other                   => throw ModelError(
          "Unexpected attribute type for Set validation value retriever:\n" + other
        )
      }
    } catch {
      case e: MoleculeError => throw e
      case t: Throwable     =>
        t.printStackTrace()
        throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${a.name}")
    }
    ModelValidation(proxy.nsMap, proxy.attrMap, "update", Some(curSetValues)).validate(update.elements)
  }


  def prepareMultipleUpdates(
    elements: List[Element],
    isUpsert: Boolean
  ): (List[Element], List[Long => List[Element]]) = {
    val update       = if (isUpsert) "upsert" else "update"
    var firstNs      = true
    var prevNs       = ""
    val idsModel     = ListBuffer.empty[Element]
    val updateModel  = ListBuffer.empty[Element]
    var updateModels = List.empty[Long => List[Element]]
    elements.foreach {
      case a: Attr =>
        updateModel += a
        a match {
          case a: AttrOneMan =>
            prevNs = a.ns
            a match {
              case a: AttrOneManString     => idsModel += AttrOneTacString(a.ns, a.attr)
              case a: AttrOneManInt        => idsModel += AttrOneTacInt(a.ns, a.attr)
              case a: AttrOneManLong       => idsModel += AttrOneTacLong(a.ns, a.attr)
              case a: AttrOneManFloat      => idsModel += AttrOneTacFloat(a.ns, a.attr)
              case a: AttrOneManDouble     => idsModel += AttrOneTacDouble(a.ns, a.attr)
              case a: AttrOneManBoolean    => idsModel += AttrOneTacBoolean(a.ns, a.attr)
              case a: AttrOneManBigInt     => idsModel += AttrOneTacBigInt(a.ns, a.attr)
              case a: AttrOneManBigDecimal => idsModel += AttrOneTacBigDecimal(a.ns, a.attr)
              case a: AttrOneManDate       => idsModel += AttrOneTacDate(a.ns, a.attr)
              case a: AttrOneManUUID       => idsModel += AttrOneTacUUID(a.ns, a.attr)
              case a: AttrOneManURI        => idsModel += AttrOneTacURI(a.ns, a.attr)
              case a: AttrOneManByte       => idsModel += AttrOneTacByte(a.ns, a.attr)
              case a: AttrOneManShort      => idsModel += AttrOneTacShort(a.ns, a.attr)
              case a: AttrOneManChar       => idsModel += AttrOneTacChar(a.ns, a.attr)
            }
          case a: AttrOneTac => idsModel += a

          case a: AttrSetMan =>
            if (a.op == Eq || a.op == Add || a.op == Swap || a.op == Remove) {
              prevNs = a.ns
              a match {
                case a: AttrSetManString     => idsModel += AttrSetTacString(a.ns, a.attr)
                case a: AttrSetManInt        => idsModel += AttrSetTacInt(a.ns, a.attr)
                case a: AttrSetManLong       => idsModel += AttrSetTacLong(a.ns, a.attr)
                case a: AttrSetManFloat      => idsModel += AttrSetTacFloat(a.ns, a.attr)
                case a: AttrSetManDouble     => idsModel += AttrSetTacDouble(a.ns, a.attr)
                case a: AttrSetManBoolean    => idsModel += AttrSetTacBoolean(a.ns, a.attr)
                case a: AttrSetManBigInt     => idsModel += AttrSetTacBigInt(a.ns, a.attr)
                case a: AttrSetManBigDecimal => idsModel += AttrSetTacBigDecimal(a.ns, a.attr)
                case a: AttrSetManDate       => idsModel += AttrSetTacDate(a.ns, a.attr)
                case a: AttrSetManUUID       => idsModel += AttrSetTacUUID(a.ns, a.attr)
                case a: AttrSetManURI        => idsModel += AttrSetTacURI(a.ns, a.attr)
                case a: AttrSetManByte       => idsModel += AttrSetTacByte(a.ns, a.attr)
                case a: AttrSetManShort      => idsModel += AttrSetTacShort(a.ns, a.attr)
                case a: AttrSetManChar       => idsModel += AttrSetTacChar(a.ns, a.attr)
              }

            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
            }

          case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
          case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
          case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
        }

      case ref@Ref(_, _, _, CardOne) =>
        if (firstNs) {
          firstNs = false
          val tacitElements = updateModel.toList
          // First namespace already has a tacit id attribute
          updateModels = updateModels :+ ((_: Long) => tacitElements)

        } else if (prevNs.nonEmpty) {
          // Get id
          idsModel += AttrOneManLong(prevNs, "id")

          // Make update model once we get an id
          val ns            = prevNs
          val tacitElements = updateModel.toList
          updateModels = updateModels :+ ((id: Long) => AttrOneTacLong(ns, "id", Eq, Seq(id)) +: tacitElements)
        }

        idsModel += ref
        prevNs = ""
        updateModel.clear()

      case ref: Ref => throw ModelError(
        s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
      )

      case other => idsModel += other
    }

    // Add id to last ref ns
    if (prevNs.nonEmpty) {
      // Get id
      idsModel += AttrOneManLong(prevNs, "id")

      // Make update model once we get an id
      val id2updateModel = (id: Long) => AttrOneTacLong(prevNs, "id", Eq, Seq(id)) +: updateModel.toList
      updateModels = updateModels :+ id2updateModel
    }

    (idsModel.toList, updateModels)
  }


  private def sqlNestedArrays2coalescedSet[T](row: RS, j2s: Any => T): Set[T] = {
    val array = row.getArray(1)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      var set                 = Set.empty[T]
      while (outerArrayResultSet.next()) {
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }
  private lazy val j2String    : Any => String     = (v: Any) => v.asInstanceOf[String]
  private lazy val j2Int       : Any => Int        = (v: Any) => v.toString.toInt
  private lazy val j2Long      : Any => Long       = (v: Any) => v.asInstanceOf[Long]
  private lazy val j2Float     : Any => Float      = (v: Any) => v.asInstanceOf[Float]
  private lazy val j2Double    : Any => Double     = (v: Any) => v.asInstanceOf[Double]
  private lazy val j2Boolean   : Any => Boolean    = (v: Any) => v.asInstanceOf[Boolean]
  private lazy val j2BigInt    : Any => BigInt     = (v: Any) => BigInt(v.toString)
  private lazy val j2BigDecimal: Any => BigDecimal = (v: Any) => BigDecimal(v.toString)
  private lazy val j2Date      : Any => Date       = (v: Any) => v.asInstanceOf[Date]
  private lazy val j2UUID      : Any => UUID       = (v: Any) => v.asInstanceOf[UUID]
  private lazy val j2URI       : Any => URI        = (v: Any) => v.asInstanceOf[URI]
  private lazy val j2Byte      : Any => Byte       = (v: Any) => v.asInstanceOf[Integer].toByte
  private lazy val j2Short     : Any => Short      = (v: Any) => v.asInstanceOf[Integer].toShort
  private lazy val j2Char      : Any => Char       = (v: Any) => v.asInstanceOf[String].charAt(0)

  private lazy val nestedArray2coalescedSetString    : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2String)
  private lazy val nestedArray2coalescedSetInt       : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Int)
  private lazy val nestedArray2coalescedSetLong      : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Long)
  private lazy val nestedArray2coalescedSetFloat     : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Float)
  private lazy val nestedArray2coalescedSetDouble    : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Double)
  private lazy val nestedArray2coalescedSetBoolean   : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Boolean)
  private lazy val nestedArray2coalescedSetBigInt    : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2BigInt)
  private lazy val nestedArray2coalescedSetBigDecimal: RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2BigDecimal)
  private lazy val nestedArray2coalescedSetDate      : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Date)
  private lazy val nestedArray2coalescedSetUUID      : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2UUID)
  private lazy val nestedArray2coalescedSetURI       : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2String).map(v => new URI(v))
  private lazy val nestedArray2coalescedSetByte      : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Byte)
  private lazy val nestedArray2coalescedSetShort     : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Short)
  private lazy val nestedArray2coalescedSetChar      : RS => Set[Any] = (rs: RS) => sqlNestedArrays2coalescedSet(rs, j2Char)
}
