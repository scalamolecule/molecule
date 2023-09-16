package molecule.sql.core.spi

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.boilerplate.ast.Model._
import molecule.core.action.Query
import molecule.core.util.ModelUtils
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import scala.annotation.nowarn
import scala.collection.mutable.ListBuffer


trait SpiHelpers extends ModelUtils {

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

  private type L = Long

  def getRefIds(refIdsAnyCardinality: List[Any], idModel: List[Element], sqlQuery: String): List[Long] = {
    refIdsAnyCardinality.headOption.fold(
      throw ExecutionError(
        s"""Couldn't find any ref ids for update:
           |
           |${idModel.mkString("\n")}
           |
           |$sqlQuery
           |""".stripMargin
      )
    ) {
      case a: L                                                                                                                                 => List(0L, a)
      case (a: L, b: L)                                                                                                                         => List(0L, a, b)
      case (a: L, b: L, c: L)                                                                                                                   => List(0L, a, b, c)
      case (a: L, b: L, c: L, d: L)                                                                                                             => List(0L, a, b, c, d)
      case (a: L, b: L, c: L, d: L, e: L)                                                                                                       => List(0L, a, b, c, d, e)
      case (a: L, b: L, c: L, d: L, e: L, f: L)                                                                                                 => List(0L, a, b, c, d, e, f)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L)                                                                                           => List(0L, a, b, c, d, e, f, g)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L)                                                                                     => List(0L, a, b, c, d, e, f, g, h)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L)                                                                               => List(0L, a, b, c, d, e, f, g, h, i)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L)                                                                         => List(0L, a, b, c, d, e, f, g, h, i, j)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L)                                                                   => List(0L, a, b, c, d, e, f, g, h, i, j, k)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L)                                                             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L)                                                       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L)                                                 => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L)                                           => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L)                                     => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L)                               => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L)                         => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L)                   => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L)             => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L)       => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
      case (a: L, b: L, c: L, d: L, e: L, f: L, g: L, h: L, i: L, j: L, k: L, l: L, m: L, n: L, o: L, p: L, q: L, r: L, s: L, t: L, u: L, v: L) => List(0L, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    }
  }

  @nowarn // Accept dynamic type parameter of returned Query
  def getIdQuery(
    elements: List[Element],
    isUpsert: Boolean = false
  ) = {
    if (isUpsert)
      throw ModelError("Can't upsert referenced attributes. Please update instead.")
    val (refIdsModel, updateModels) = prepareMultipleUpdates(elements, isUpsert)
    val idQuery                     = updateModels.size match {
      case 1  => Query[L](refIdsModel)
      case 2  => Query[(L, L)](refIdsModel)
      case 3  => Query[(L, L, L)](refIdsModel)
      case 4  => Query[(L, L, L, L)](refIdsModel)
      case 5  => Query[(L, L, L, L, L)](refIdsModel)
      case 6  => Query[(L, L, L, L, L, L)](refIdsModel)
      case 7  => Query[(L, L, L, L, L, L, L)](refIdsModel)
      case 8  => Query[(L, L, L, L, L, L, L, L)](refIdsModel)
      case 9  => Query[(L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 10 => Query[(L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 11 => Query[(L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 12 => Query[(L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 13 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 14 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 15 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 16 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 17 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 18 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 19 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 20 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 21 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
      case 22 => Query[(L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L, L)](refIdsModel)
    }
    (idQuery, updateModels)
  }

  private def sqlNestedArrays2coalescedSet[T](row: Row, j2s: Any => T): Set[T] = {
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

  private lazy val nestedArray2coalescedSetString    : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2String)
  private lazy val nestedArray2coalescedSetInt       : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Int)
  private lazy val nestedArray2coalescedSetLong      : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Long)
  private lazy val nestedArray2coalescedSetFloat     : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Float)
  private lazy val nestedArray2coalescedSetDouble    : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Double)
  private lazy val nestedArray2coalescedSetBoolean   : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Boolean)
  private lazy val nestedArray2coalescedSetBigInt    : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2BigInt)
  private lazy val nestedArray2coalescedSetBigDecimal: Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2BigDecimal)
  private lazy val nestedArray2coalescedSetDate      : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Date)
  private lazy val nestedArray2coalescedSetUUID      : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2UUID)
  private lazy val nestedArray2coalescedSetURI       : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2String).map(v => new URI(v))
  private lazy val nestedArray2coalescedSetByte      : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Byte)
  private lazy val nestedArray2coalescedSetShort     : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Short)
  private lazy val nestedArray2coalescedSetChar      : Row => Set[Any] = (rs: Row) => sqlNestedArrays2coalescedSet(rs, j2Char)

  protected def nestedArray2coalescedSet(a: Attr, rs: Row) = {
    a match {
      case _: AttrSetManString     => nestedArray2coalescedSetString(rs)
      case _: AttrSetManInt        => nestedArray2coalescedSetInt(rs)
      case _: AttrSetManLong       => nestedArray2coalescedSetLong(rs)
      case _: AttrSetManFloat      => nestedArray2coalescedSetFloat(rs)
      case _: AttrSetManDouble     => nestedArray2coalescedSetDouble(rs)
      case _: AttrSetManBoolean    => nestedArray2coalescedSetBoolean(rs)
      case _: AttrSetManBigInt     => nestedArray2coalescedSetBigInt(rs)
      case _: AttrSetManBigDecimal => nestedArray2coalescedSetBigDecimal(rs)
      case _: AttrSetManDate       => nestedArray2coalescedSetDate(rs)
      case _: AttrSetManUUID       => nestedArray2coalescedSetUUID(rs)
      case _: AttrSetManURI        => nestedArray2coalescedSetURI(rs)
      case _: AttrSetManByte       => nestedArray2coalescedSetByte(rs)
      case _: AttrSetManShort      => nestedArray2coalescedSetShort(rs)
      case _: AttrSetManChar       => nestedArray2coalescedSetChar(rs)
      case other                   => throw ModelError(
        "Unexpected attribute type for Set validation value retriever:\n" + other
      )
    }
  }
}
