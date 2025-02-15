package molecule.sql.core.spi

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.core.action.Query
import molecule.core.ast.DataModel._
import molecule.core.util.ModelUtils
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import scala.annotation.nowarn
import scala.collection.mutable.ListBuffer


trait SpiHelpers extends ModelUtils {

  def noCollectionFilterEq(attr: String) = throw ModelError(
    s"Filtering by collection equality ($attr) not supported in updates."
  )
  def noIdsTwice() = throw ModelError(
    "Can't apply entity ids twice in update."
  )
  def noMixIdsFilterAttrs() = throw ModelError(
    "Can't mix using ids and filter elements"
  )

  final def getUpdateIdsModel(elements: List[Element]): (Int, List[Element]) = {
    var hasData     = false
    var arity       = 0
    val firstEnt    = getInitialEntity(elements)
    var curEnt      = firstEnt
    val filterModel = ListBuffer.empty[Element]

    // Since updates work on fully present ref structures we can accumulate forward
    elements.foreach {
      case a: Attr =>
        a match {
          case a: AttrOne => a match {
            case a: AttrOneMan => hasData = true
            case a: AttrOneTac => filterModel += a
            case a: AttrOneOpt => noOptional(a)
          }

          case a: AttrSet => a match {
            case a: AttrSetMan => hasData = true
            case a: AttrSetTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrSetOpt => noOptional(a)
          }

          case a: AttrSeq => a match {
            case a: AttrSeqMan => hasData = true
            case a: AttrSeqTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrSeqOpt => noOptional(a)
          }

          case a: AttrMap => a match {
            case a: AttrMapMan => hasData = true
            case a: AttrMapTac =>
              if (a.op == Eq) {
                noCollectionFilterEq(a.name)
              }
              filterModel += a
            case _: AttrMapOpt => noOptional(a)
          }
        }

      case r: Ref =>
        if (hasData) {
          filterModel += AttrOneManID(curEnt, "id")
          arity += 1
        }
        filterModel += r
        curEnt = r.ref
        hasData = false

      case BackRef(_, ent, _) => curEnt = ent
      case r: OptRef          => ???
      case _                  => noNested
    }

    // Handle last entity
    if (hasData) {
      filterModel += AttrOneManID(curEnt, "id")
      arity += 1
    }
    (arity, filterModel.toList)
  }

  def getUpdateResolvers(elements: List[Element]): List[
    // List of resolvers for each table involved in update/upsert
    (
      List[String], // ref path
        List[Long] => List[Element] // ref ids => update model
      )
  ] = {
    val dummyCoord   = Seq(0, 0) // irrelevant for id columns that will never collide with keywords
    var hasId        = false
    var hasData      = false
    val firstEnt     = getInitialEntity(elements)
    var curEnt       = firstEnt
    var refPath      = List(firstEnt)
    val updateModel  = ListBuffer.empty[Element]
    val updateModels = ListBuffer.empty[(List[String], List[Long] => List[Element])]

    elements.foreach {
      case a@AttrOneTacID(_, "id", _, _, _, _, _, _, _, _, _) =>
        updateModel += a
        hasId = true

      case a: Attr if a.isInstanceOf[Mandatory] =>
        updateModel += a
        hasData = true

      case Ref(_, refAttr, ref, CardOne, _, _) =>
        if (hasData) {
          val updateElements = updateModel.toList
          if (hasId) {
            updateModels += refPath -> (_ => updateElements)
          } else {
            val ent = curEnt // immutable value for later lambda resolution
            updateModels += refPath -> ((ids: List[Long]) =>
              AttrOneTacID(ent, "id", Eq, ids, coord = dummyCoord) +: updateElements)
          }
        }

        refPath ++= List(refAttr, ref)
        curEnt = ref
        hasId = false
        hasData = false
        updateModel.clear()

      case Ref(_, refAttr, ref, _, _, _) =>
        if (hasData) {
          val updateElements = updateModel.toList
          if (hasId) {
            updateModels += refPath -> (_ => updateElements)
          } else {
            val ent = curEnt // immutable value for later lambda resolution
            updateModels += refPath -> ((ids: List[Long]) =>
              AttrOneTacID(ent, "id", Eq, ids, coord = dummyCoord) +: updateElements)
          }
        }

        refPath ++= List(refAttr, ref)
        curEnt = ref
        hasId = false
        hasData = false
        updateModel.clear()

      case BackRef(_, ent, _) =>
        refPath = refPath.dropRight(2)
        curEnt = ent

      case other => ()
    }

    if (hasData) {
      val updateElements = updateModel.toList
      updateModels += refPath -> ((ids: List[Long]) =>
        AttrOneTacID(refPath.last, "id", Eq, ids, coord = dummyCoord) +: updateElements)
    }
    updateModels.toList
  }


  def prepareMultipleUpdates(
    elements: List[Element],
    isUpsert: Boolean
  ): (List[Element], List[Long => List[Element]]) = {
    val update       = if (isUpsert) "upsert" else "update"
    val dummyCoord   = Seq(0, 0) // irrelevant for id columns that will never collide with keywords
    var firstEnt     = true
    var prevEnt      = ""
    val idsModel     = ListBuffer.empty[Element]
    val updateModel  = ListBuffer.empty[Element]
    var updateModels = List.empty[Long => List[Element]]
    elements.foreach {
      case a: Attr =>
        updateModel += a
        a match {
          case a: AttrOneMan =>
            prevEnt = a.ent
            a match {
              case a: AttrOneManID             => idsModel += AttrOneTacID(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManString         => idsModel += AttrOneTacString(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManInt            => idsModel += AttrOneTacInt(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManLong           => idsModel += AttrOneTacLong(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManFloat          => idsModel += AttrOneTacFloat(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManDouble         => idsModel += AttrOneTacDouble(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManBoolean        => idsModel += AttrOneTacBoolean(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManBigInt         => idsModel += AttrOneTacBigInt(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManBigDecimal     => idsModel += AttrOneTacBigDecimal(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManDate           => idsModel += AttrOneTacDate(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManDuration       => idsModel += AttrOneTacDuration(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManInstant        => idsModel += AttrOneTacInstant(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManLocalDate      => idsModel += AttrOneTacLocalDate(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManLocalTime      => idsModel += AttrOneTacLocalTime(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManLocalDateTime  => idsModel += AttrOneTacLocalDateTime(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManOffsetTime     => idsModel += AttrOneTacOffsetTime(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManOffsetDateTime => idsModel += AttrOneTacOffsetDateTime(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManZonedDateTime  => idsModel += AttrOneTacZonedDateTime(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManUUID           => idsModel += AttrOneTacUUID(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManURI            => idsModel += AttrOneTacURI(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManByte           => idsModel += AttrOneTacByte(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManShort          => idsModel += AttrOneTacShort(a.ent, a.attr, coord = a.coord)
              case a: AttrOneManChar           => idsModel += AttrOneTacChar(a.ent, a.attr, coord = a.coord)
            }
          case a: AttrOneTac => idsModel += a

          case a: AttrSetMan =>
            if (a.op == Eq || a.op == Add || a.op == Remove) {
              prevEnt = a.ent
              a match {
                case a: AttrSetManID             => idsModel += AttrSetTacID(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManString         => idsModel += AttrSetTacString(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManInt            => idsModel += AttrSetTacInt(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManLong           => idsModel += AttrSetTacLong(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManFloat          => idsModel += AttrSetTacFloat(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManDouble         => idsModel += AttrSetTacDouble(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManBoolean        => idsModel += AttrSetTacBoolean(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManBigInt         => idsModel += AttrSetTacBigInt(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManBigDecimal     => idsModel += AttrSetTacBigDecimal(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManDate           => idsModel += AttrSetTacDate(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManDuration       => idsModel += AttrSetTacDuration(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManInstant        => idsModel += AttrSetTacInstant(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManLocalDate      => idsModel += AttrSetTacLocalDate(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManLocalTime      => idsModel += AttrSetTacLocalTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManLocalDateTime  => idsModel += AttrSetTacLocalDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManOffsetTime     => idsModel += AttrSetTacOffsetTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManOffsetDateTime => idsModel += AttrSetTacOffsetDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManZonedDateTime  => idsModel += AttrSetTacZonedDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManUUID           => idsModel += AttrSetTacUUID(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManURI            => idsModel += AttrSetTacURI(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManByte           => idsModel += AttrSetTacByte(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManShort          => idsModel += AttrSetTacShort(a.ent, a.attr, coord = a.coord)
                case a: AttrSetManChar           => idsModel += AttrSetTacChar(a.ent, a.attr, coord = a.coord)
              }
            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute (${a.name}).")
            }

          case a: AttrSeqMan =>
            if (a.op == Eq || a.op == Add || a.op == Remove) {
              prevEnt = a.ent
              a match {
                case a: AttrSeqManID             => idsModel += AttrSeqTacID(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManString         => idsModel += AttrSeqTacString(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManInt            => idsModel += AttrSeqTacInt(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManLong           => idsModel += AttrSeqTacLong(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManFloat          => idsModel += AttrSeqTacFloat(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManDouble         => idsModel += AttrSeqTacDouble(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManBoolean        => idsModel += AttrSeqTacBoolean(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManBigInt         => idsModel += AttrSeqTacBigInt(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManBigDecimal     => idsModel += AttrSeqTacBigDecimal(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManDate           => idsModel += AttrSeqTacDate(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManDuration       => idsModel += AttrSeqTacDuration(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManInstant        => idsModel += AttrSeqTacInstant(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManLocalDate      => idsModel += AttrSeqTacLocalDate(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManLocalTime      => idsModel += AttrSeqTacLocalTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManLocalDateTime  => idsModel += AttrSeqTacLocalDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManOffsetTime     => idsModel += AttrSeqTacOffsetTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManOffsetDateTime => idsModel += AttrSeqTacOffsetDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManZonedDateTime  => idsModel += AttrSeqTacZonedDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManUUID           => idsModel += AttrSeqTacUUID(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManURI            => idsModel += AttrSeqTacURI(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManByte           => idsModel += AttrSeqTacByte(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManShort          => idsModel += AttrSeqTacShort(a.ent, a.attr, coord = a.coord)
                case a: AttrSeqManChar           => idsModel += AttrSeqTacChar(a.ent, a.attr, coord = a.coord)
              }
            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute (${a.name}).")
            }

          case a: AttrMapMan =>
            if (a.op == Eq || a.op == Add || a.op == Remove) {
              prevEnt = a.ent
              a match {
                case a: AttrMapManID             => idsModel += AttrMapTacID(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManString         => idsModel += AttrMapTacString(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManInt            => idsModel += AttrMapTacInt(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManLong           => idsModel += AttrMapTacLong(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManFloat          => idsModel += AttrMapTacFloat(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManDouble         => idsModel += AttrMapTacDouble(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManBoolean        => idsModel += AttrMapTacBoolean(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManBigInt         => idsModel += AttrMapTacBigInt(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManBigDecimal     => idsModel += AttrMapTacBigDecimal(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManDate           => idsModel += AttrMapTacDate(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManDuration       => idsModel += AttrMapTacDuration(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManInstant        => idsModel += AttrMapTacInstant(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManLocalDate      => idsModel += AttrMapTacLocalDate(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManLocalTime      => idsModel += AttrMapTacLocalTime(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManLocalDateTime  => idsModel += AttrMapTacLocalDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManOffsetTime     => idsModel += AttrMapTacOffsetTime(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManOffsetDateTime => idsModel += AttrMapTacOffsetDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManZonedDateTime  => idsModel += AttrMapTacZonedDateTime(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManUUID           => idsModel += AttrMapTacUUID(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManURI            => idsModel += AttrMapTacURI(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManByte           => idsModel += AttrMapTacByte(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManShort          => idsModel += AttrMapTacShort(a.ent, a.attr, coord = a.coord)
                case a: AttrMapManChar           => idsModel += AttrMapTacChar(a.ent, a.attr, coord = a.coord)
              }
            } else {
              throw ModelError(s"Unexpected $update operation for card-many attribute (${a.name}).")
            }

          case _: AttrOneOpt => throw ModelError(s"Can't $update optional values (${a.cleanName}_?)")

          case _: AttrSetTac | _: AttrSeqTac | _: AttrMapTac =>
            throw ModelError("Can only lookup entity with card-one attribute value (${a.name}).")

          case _: AttrSetOpt | _: AttrSeqOpt | _: AttrMapOpt =>
            throw ModelError(s"Can't $update optional values (${a.cleanName}_?)")
        }

      case ref@Ref(_, _, _, CardOne, _, coord) =>
        if (firstEnt) {
          firstEnt = false
          val tacitElements = updateModel.toList
          // First entity already has a tacit id attribute
          updateModels = updateModels :+ ((_: Long) => tacitElements)

        } else if (prevEnt.nonEmpty) {
          // Get id
          idsModel += AttrOneManID(prevEnt, "id", coord = coord)

          // Make update model once we get an id
          val ent           = prevEnt
          val tacitElements = updateModel.toList
          updateModels = updateModels :+ ((id: Long) => AttrOneTacID(ent, "id", Eq, Seq(id), coord = coord) +: tacitElements)
        }

        idsModel += ref
        prevEnt = ""
        updateModel.clear()

      case ref: Ref => throw ModelError(
        s"Can't $update attributes in card-many referenced entity `${ref.refAttr.capitalize}`"
      )

      case ref: OptRef => ???

      case other => idsModel += other
    }

    // Add id to last ref
    if (prevEnt.nonEmpty) {
      // Get id
      idsModel += AttrOneManID(prevEnt, "id", coord = dummyCoord)

      // Make update model once we get an id
      val id2updateModel = (id: Long) => AttrOneTacID(prevEnt, "id", Eq, Seq(id), coord = dummyCoord) +: updateModel.toList
      updateModels = updateModels :+ id2updateModel
    }

    (idsModel.toList, updateModels)
  }

  private type L = Long
  private type S = Long

  def getRefIds(refIdsAnyCardinality: List[Any]): List[Long] = {
    // Start with dummy id (not used) to mark first entity
    refIdsAnyCardinality.headOption.fold(List(0L)) {
      case a: S                                                                                                                                 => 0L +: List(a)
      case (a: S, b: S)                                                                                                                         => 0L +: List(a, b)
      case (a: S, b: S, c: S)                                                                                                                   => 0L +: List(a, b, c)
      case (a: S, b: S, c: S, d: S)                                                                                                             => 0L +: List(a, b, c, d)
      case (a: S, b: S, c: S, d: S, e: S)                                                                                                       => 0L +: List(a, b, c, d, e)
      case (a: S, b: S, c: S, d: S, e: S, f: S)                                                                                                 => 0L +: List(a, b, c, d, e, f)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S)                                                                                           => 0L +: List(a, b, c, d, e, f, g)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S)                                                                                     => 0L +: List(a, b, c, d, e, f, g, h)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S)                                                                               => 0L +: List(a, b, c, d, e, f, g, h, i)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S)                                                                         => 0L +: List(a, b, c, d, e, f, g, h, i, j)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S)                                                                   => 0L +: List(a, b, c, d, e, f, g, h, i, j, k)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S)                                                             => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S)                                                       => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S)                                                 => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S)                                           => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S)                                     => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S)                               => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S, r: S)                         => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S, r: S, s: S)                   => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S, r: S, s: S, t: S)             => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S, r: S, s: S, t: S, u: S)       => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
      case (a: S, b: S, c: S, d: S, e: S, f: S, g: S, h: S, i: S, j: S, k: S, l: S, m: S, n: S, o: S, p: S, q: S, r: S, s: S, t: S, u: S, v: S) => 0L +: List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    }
  }

  @nowarn // Accept dynamic type parameter of returned Query
  def getIdQuery(
    elements: List[Element],
    isUpsert: Boolean = false
  ) = {
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

  protected def nestedArray2coalescedSet(a: Attr, rs: Row, isAttr: Boolean = true): Set[Any] = {
    a match {
      case _: AttrSetManID             => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Long])
      case _: AttrSetManString         => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String])
      case _: AttrSetManInt            => sql2set(isAttr, rs, (v: Any) => v.toString.toInt)
      case _: AttrSetManLong           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Long])
      case _: AttrSetManFloat          => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Float])
      case _: AttrSetManDouble         => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Double])
      case _: AttrSetManBoolean        => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Boolean])
      case _: AttrSetManBigInt         => sql2set(isAttr, rs, (v: Any) => BigInt(v.toString))
      case _: AttrSetManBigDecimal     => sql2set(isAttr, rs, (v: Any) => BigDecimal(v.toString))
      case _: AttrSetManDate           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Date])
      case _: AttrSetManDuration       => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Duration])
      case _: AttrSetManInstant        => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Instant])
      case _: AttrSetManLocalDate      => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalDate])
      case _: AttrSetManLocalTime      => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalTime])
      case _: AttrSetManLocalDateTime  => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[LocalDateTime])
      case _: AttrSetManOffsetTime     => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[OffsetTime])
      case _: AttrSetManOffsetDateTime => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[OffsetDateTime])
      case _: AttrSetManZonedDateTime  => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[ZonedDateTime])
      case _: AttrSetManUUID           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[UUID])
      case _: AttrSetManURI            => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String]).map(v => new URI(v))
      case _: AttrSetManByte           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Integer].toByte)
      case _: AttrSetManShort          => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[Integer].toShort)
      case _: AttrSetManChar           => sql2set(isAttr, rs, (v: Any) => v.asInstanceOf[String].charAt(0))
      case other                       => throw ModelError(
        "Unexpected attribute type for Set validation value retriever:\n" + other
      )
    }
  }


  private def sql2set[T](isAttr: Boolean, row: Row, j2s: Any => T): Set[T] = {
    if (isAttr)
      sqlNestedArrays2coalescedSet(row, j2s)
    else
      sqlArrays2coalescedSet(row, j2s)
  }

  private def sqlNestedArrays2coalescedSet[T](row: Row, j2s: Any => T): Set[T] = {
    row.next()
    val array = row.getArray(1)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val resultSet = array.getResultSet
      var set       = Set.empty[T]
      while (resultSet.next()) {
        resultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }

  private def sqlArrays2coalescedSet[T](row: Row, j2s: Any => T): Set[T] = {
    val resultSet = row
    var set       = Set.empty[T]
    while (resultSet.next()) {
      resultSet.getArray(1).getArray.asInstanceOf[Array[_]].foreach { value =>
        set += j2s(value)
      }
    }
    set
  }

  protected def jsonArray2coalescedSet(a: Attr, rs: Row): Set[Any] = {
    rs.next()
    val json = rs.getString(1)
    a match {
      case _: AttrSetManID             => json.substring(1, json.length - 1).split(", ?").map(_.toLong).toSet
      case _: AttrSetManString         => json.substring(2, json.length - 2).split("\", ?\"").toSet
      case _: AttrSetManInt            => json.substring(1, json.length - 1).split(", ?").map(_.toInt).toSet
      case _: AttrSetManLong           => json.substring(1, json.length - 1).split(", ?").map(_.toLong).toSet
      case _: AttrSetManFloat          => json.substring(1, json.length - 1).split(", ?").map(_.toFloat).toSet
      case _: AttrSetManDouble         => json.substring(1, json.length - 1).split(", ?").map(_.toDouble).toSet
      case _: AttrSetManBoolean        => json.substring(1, json.length - 1).split(", ?").map(_ == "1").toSet
      case _: AttrSetManBigInt         => json.substring(1, json.length - 1).split(", ?").map(BigInt(_)).toSet
      case _: AttrSetManBigDecimal     => json.substring(1, json.length - 1).split(", ?").map(BigDecimal(_)).toSet
      case _: AttrSetManDate           => json.substring(1, json.length - 1).split(", ?").map(v => new Date(v.toLong)).toSet
      case _: AttrSetManDuration       => json.substring(1, json.length - 1).split(", ?").map(v => Duration.parse(v)).toSet
      case _: AttrSetManInstant        => json.substring(1, json.length - 1).split(", ?").map(v => Instant.parse(v)).toSet
      case _: AttrSetManLocalDate      => json.substring(1, json.length - 1).split(", ?").map(v => LocalDate.parse(v)).toSet
      case _: AttrSetManLocalTime      => json.substring(1, json.length - 1).split(", ?").map(v => LocalTime.parse(v)).toSet
      case _: AttrSetManLocalDateTime  => json.substring(1, json.length - 1).split(", ?").map(v => LocalDateTime.parse(v)).toSet
      case _: AttrSetManOffsetTime     => json.substring(1, json.length - 1).split(", ?").map(v => OffsetTime.parse(v)).toSet
      case _: AttrSetManOffsetDateTime => json.substring(1, json.length - 1).split(", ?").map(v => OffsetDateTime.parse(v)).toSet
      case _: AttrSetManZonedDateTime  => json.substring(1, json.length - 1).split(", ?").map(v => ZonedDateTime.parse(v)).toSet
      case _: AttrSetManUUID           => json.substring(2, json.length - 2).split("\", ?\"").map(UUID.fromString).toSet
      case _: AttrSetManURI            => json.substring(2, json.length - 2).split("\", ?\"").map(new URI(_)).toSet
      case _: AttrSetManByte           => json.substring(1, json.length - 1).split(", ?").map(_.toByte).toSet
      case _: AttrSetManShort          => json.substring(1, json.length - 1).split(", ?").map(_.toShort).toSet
      case _: AttrSetManChar           => json.substring(2, json.length - 2).split("\", ?\"").map(_.charAt(0)).toSet
      case other                       => throw ModelError(
        "Unexpected attribute type for Set validation value retriever:\n" + other
      )
    }
  }
}
