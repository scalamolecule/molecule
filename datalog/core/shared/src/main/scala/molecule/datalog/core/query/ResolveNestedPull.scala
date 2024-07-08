package molecule.datalog.core.query

import java.util.{Iterator => jIterator}
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.ModelUtils
import scala.annotation.tailrec


trait ResolveNestedPull[Tpl]
  extends SortOneOptFlat_[Tpl]
    with SortOneSpecial[Tpl]
    with LambdasOne
    with LambdasSet
    with LambdasSeq
    with LambdasMap
    with ModelUtils
    with MoleculeLogging { self: Model2DatomicQuery[Tpl] =>


  final protected def resolveOptNestedElements(e: Var, ref: Ref, elements: List[Element]): Unit = {
    @tailrec
    def addPullAttrs(
      elements: List[Element],
      level: Int,
      attrIndex: Int,
      acc: String
    ): (String, Option[Element], List[Element], Int) = {
      val i = "  " * (level + 6)
      elements match {
        case head :: tail =>
          head match {
            case a: Attr if a.op != V => throw ModelError(
              s"Expressions not allowed in optional nested queries (${a.name})."
            )
            case a: AttrOneMan        =>
              resAttrOneMan(a, attrIndex)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrOneOpt =>
              resAttrOneOpt(a, attrIndex)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSetMan =>
              resAttrSetMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSetOpt =>
              resttrSetOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSeqMan =>
              resAttrSeqMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSeqOpt =>
              resAttrSeqOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrMapMan =>
              resAttrMapMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrMapOpt =>
              resAttrMapOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case ref: Ref             => (acc, Some(ref), tail, attrIndex)
            case backRef: BackRef     => (acc, Some(backRef), tail, attrIndex)
            case optRef: OptRef       => ??? //(acc, Some(optRef), Nil, attrIndex)
            case nestedOpt: OptNested => (acc, Some(nestedOpt), Nil, attrIndex)
            case _: Nested            => noMixedNestedModes

            case a: AttrOneTac => throw ModelError(
              s"Tacit attributes not allowed in optional nested queries (${a.name}_).")

            case other => throw ModelError(
              "Unexpected element in optional nested molecule: " + other
            )
          }
        case Nil          => (acc, None, Nil, attrIndex)
      }
    }

    def resolvePullRef(
      ref: Ref,
      elements: List[Element],
      level: Int,
      attrIndex: Int,
      append: String
    ): (String, String) = {
      val indent  = "  " * (level + 5)
      val refAttr = s":${ref.ns}/${ref.refAttr}"
      addPullAttrs(elements, level, attrIndex, "") match {
        case (acc1, None, Nil, _) =>
          val res = s"""\n$indent{($refAttr :limit nil :default "$none") [$acc1]}"""
          (res, append)

        case (acc1, Some(ref1@Ref(_, _, _, CardOne, _, _)), tail, attrIndex1) =>
          refDepths = refDepths.init :+ refDepths.last + 1
          // Continue increasing attrIndex after refs
          val (attrs, append1) = resolvePullRef(ref1, tail, level + 1, attrIndex1, "")
          val res              = s"""\n$indent{($refAttr :limit nil :default "$none") [$acc1$attrs]}"""
          (res, append + append1)

        case (_, Some(ref: Ref), _, _) => throw ModelError(
          s"Only cardinality-one refs allowed in optional nested queries (${ref.ns}.${ref.refAttr})."
        )

        case (acc1, Some(BackRef(backRef, _, _)), tail, attrIndex1) =>
          // Finish initialization of previous ref before stepping back
          val prevRef = s"""\n$indent{($refAttr :limit nil :default "$none") [$acc1"""

          @tailrec
          def rec(elements: List[Element], level1: Int): (List[Element], String, String) = elements.head match {
            case ref1: Ref  =>
              // End previous ref
              val (attrs, append1) = resolvePullRef(ref1, elements.tail, level1, attrIndex1, "]}")
              (Nil, prevRef, append + attrs + append1)
            case _: BackRef => rec(elements.tail, level1 - 1)
            case a: Attr    => throw ModelError(
              s"Expected ref after backref _$backRef. " +
                s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
                s"instead of after backref _$backRef."
            )
            case other      => unexpectedElement(other)
          }
          val (_, acc2, append2) = rec(tail, level)
          (acc2, append2)

        case (acc1, Some(OptNested(ref1, elements1)), _, _) =>
          // New sub level
          pullCastss = pullCastss :+ pullCasts.toList
          pullCasts.clear()
          pullSortss = pullSortss :+ pullSorts.sortBy(_._1).map(_._2).toList
          pullSorts.clear()
          refDepths = refDepths :+ 0
          aritiesNested()
          val (attrs, append1) = resolvePullRef(ref1, elements1, level + 1, 0, "")
          val res              = s"""\n$indent{($refAttr :limit nil :default "$none") [$acc1$attrs$append1]}"""
          (res, "")

        case (_, Some(other), _, _) => unexpectedElement(other)
        case other                  => throw ModelError("Unexpected resolvePullRef coordinates: " + other)
      }
    }

    val (attrs, append) = resolvePullRef(ref, elements, 0, 0, "")
    val nestedId        = "?id" + nestedIds.size
    find += s"(pull $nestedId [$attrs$append])\n       "
  }


  private def renderPull(indent: String, a: Attr): String = {
    if (a.attr == "id") {
      s"""\n$indent:db/id"""
    } else a match {
      case _: AttrSeq if !a.isInstanceOf[AttrSeqManByte] && !a.isInstanceOf[AttrSeqOptByte] =>
        val (ns, attr) = (a.ns, a.attr)
        s"""
           |$indent{(:$ns/$attr :limit nil :default "__none__") [
           |$indent  :$ns.$attr/i_ :$ns.$attr/v_]}""".stripMargin
      case _: AttrMap                                                                       =>
        val (ns, attr) = (a.ns, a.attr)
        s"""
           |$indent{(:$ns/$attr :limit nil :default "__none__") [
           |$indent  :$ns.$attr/k_ :$ns.$attr/v_]}""".stripMargin
      case _                                                                                =>
        s"""\n$indent(:${a.ns}/${a.attr} :limit nil :default "$none")"""
    }


  }

  private def add(
    sorter: Option[(Int, Int => (Row, Row) => Int)],
    lambda: jIterator[_] => Any,
    lambda2: AnyRef => AnyRef = identity
  ): Unit = {
    pullCasts += lambda
    addCast(lambda2)
    sorter.foreach(pullSorts += _)
  }


  private def resAttrOneMan(a: AttrOneMan, attrIndex: Int): Unit = {
    aritiesAttr()
    a match {
      case a: AttrOneManID             => add(sortOneID(a, attrIndex), it2Id, it2Id2)
      case a: AttrOneManString         => add(sortOneString(a, attrIndex), it2String, it2String2)
      case a: AttrOneManInt            => add(intSorter(a, attrIndex), it2Int, it2Int2)
      case a: AttrOneManLong           => add(sortOneLong(a, attrIndex), it2Long)
      case a: AttrOneManFloat          => add(floatSorter(a, attrIndex), it2Float)
      case a: AttrOneManDouble         => add(sortOneDouble(a, attrIndex), it2Double)
      case a: AttrOneManBoolean        => add(sortOneBooleanOptNested(a, attrIndex), it2Boolean)
      case a: AttrOneManBigInt         => add(bigIntSorter(a, attrIndex), it2BigInt)
      case a: AttrOneManBigDecimal     => add(sortOneBigDecimal(a, attrIndex), it2BigDecimal)
      case a: AttrOneManDate           => add(sortOneDate(a, attrIndex), it2Date)
      case a: AttrOneManDuration       => add(sortOneDuration(a, attrIndex), it2Duration)
      case a: AttrOneManInstant        => add(sortOneInstant(a, attrIndex), it2Instant)
      case a: AttrOneManLocalDate      => add(sortOneLocalDate(a, attrIndex), it2LocalDate)
      case a: AttrOneManLocalTime      => add(sortOneLocalTime(a, attrIndex), it2LocalTime)
      case a: AttrOneManLocalDateTime  => add(sortOneLocalDateTime(a, attrIndex), it2LocalDateTime)
      case a: AttrOneManOffsetTime     => add(sortOneOffsetTime(a, attrIndex), it2OffsetTime)
      case a: AttrOneManOffsetDateTime => add(sortOneOffsetDateTime(a, attrIndex), it2OffsetDateTime)
      case a: AttrOneManZonedDateTime  => add(sortOneZonedDateTime(a, attrIndex), it2ZonedDateTime)
      case a: AttrOneManUUID           => add(sortOneUUID(a, attrIndex), it2UUID)
      case a: AttrOneManURI            => add(sortOneURI(a, attrIndex), it2URI)
      case a: AttrOneManByte           => add(byteSorter(a, attrIndex), it2Byte)
      case a: AttrOneManShort          => add(shortSorter(a, attrIndex), it2Short)
      case a: AttrOneManChar           => add(sortOneChar(a, attrIndex), it2Char)
    }
  }

  private def resAttrOneOpt(a: AttrOneOpt, attrIndex: Int): Unit = {
    aritiesAttr()
    a match {
      case _: AttrOneOptID             => add(sortOneOptFlatId(a, attrIndex), it2OptId)
      case _: AttrOneOptString         => add(sortOneOptFlatString(a, attrIndex), it2OptString)
      case _: AttrOneOptInt            => add(sortOneOptFlatInt(a, attrIndex), it2OptInt)
      case _: AttrOneOptLong           => add(sortOneOptFlatLong(a, attrIndex), it2OptLong)
      case _: AttrOneOptFloat          => add(sortOneOptFlatFloat(a, attrIndex), it2OptFloat)
      case _: AttrOneOptDouble         => add(sortOneOptFlatDouble(a, attrIndex), it2OptDouble)
      case _: AttrOneOptBoolean        => add(sortOneOptFlatBoolean(a, attrIndex), it2Boolean)
      case _: AttrOneOptBigInt         => add(sortOneOptFlatBigInt(a, attrIndex), it2OptBigInt)
      case _: AttrOneOptBigDecimal     => add(sortOneOptFlatBigDecimal(a, attrIndex), it2OptBigDecimal)
      case _: AttrOneOptDate           => add(sortOneOptFlatDate(a, attrIndex), it2OptDate)
      case _: AttrOneOptDuration       => add(sortOneOptFlatDuration(a, attrIndex), it2OptDuration)
      case _: AttrOneOptInstant        => add(sortOneOptFlatInstant(a, attrIndex), it2OptInstant)
      case _: AttrOneOptLocalDate      => add(sortOneOptFlatLocalDate(a, attrIndex), it2OptLocalDate)
      case _: AttrOneOptLocalTime      => add(sortOneOptFlatLocalTime(a, attrIndex), it2OptLocalTime)
      case _: AttrOneOptLocalDateTime  => add(sortOneOptFlatLocalDateTime(a, attrIndex), it2OptLocalDateTime)
      case _: AttrOneOptOffsetTime     => add(sortOneOptFlatOffsetTime(a, attrIndex), it2OptOffsetTime)
      case _: AttrOneOptOffsetDateTime => add(sortOneOptFlatOffsetDateTime(a, attrIndex), it2OptOffsetDateTime)
      case _: AttrOneOptZonedDateTime  => add(sortOneOptFlatZonedDateTime(a, attrIndex), it2OptZonedDateTime)
      case _: AttrOneOptUUID           => add(sortOneOptFlatUUID(a, attrIndex), it2OptUUID)
      case _: AttrOneOptURI            => add(sortOneOptFlatURI(a, attrIndex), it2OptURI)
      case _: AttrOneOptByte           => add(sortOneOptFlatByte(a, attrIndex), it2OptByte)
      case _: AttrOneOptShort          => add(sortOneOptFlatShort(a, attrIndex), it2OptShort)
      case _: AttrOneOptChar           => add(sortOneOptFlatChar(a, attrIndex), it2OptChar)
    }
  }

  private def resAttrSetMan(a: AttrSetMan): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSetManID             => it2SetId
      case _: AttrSetManString         => it2SetString
      case _: AttrSetManInt            => it2SetInt
      case _: AttrSetManLong           => it2SetLong
      case _: AttrSetManFloat          => it2SetFloat
      case _: AttrSetManDouble         => it2SetDouble
      case _: AttrSetManBoolean        => it2SetBoolean
      case _: AttrSetManBigInt         => it2SetBigInt
      case _: AttrSetManBigDecimal     => it2SetBigDecimal
      case _: AttrSetManDate           => it2SetDate
      case _: AttrSetManDuration       => it2SetDuration
      case _: AttrSetManInstant        => it2SetInstant
      case _: AttrSetManLocalDate      => it2SetLocalDate
      case _: AttrSetManLocalTime      => it2SetLocalTime
      case _: AttrSetManLocalDateTime  => it2SetLocalDateTime
      case _: AttrSetManOffsetTime     => it2SetOffsetTime
      case _: AttrSetManOffsetDateTime => it2SetOffsetDateTime
      case _: AttrSetManZonedDateTime  => it2SetZonedDateTime
      case _: AttrSetManUUID           => it2SetUUID
      case _: AttrSetManURI            => it2SetURI
      case _: AttrSetManByte           => it2SetByte
      case _: AttrSetManShort          => it2SetShort
      case _: AttrSetManChar           => it2SetChar
    })
  }

  private def resttrSetOpt(a: AttrSetOpt): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSetOptID             => it2OptSetId
      case _: AttrSetOptString         => it2OptSetString
      case _: AttrSetOptInt            => it2OptSetInt
      case _: AttrSetOptLong           => it2OptSetLong
      case _: AttrSetOptFloat          => it2OptSetFloat
      case _: AttrSetOptDouble         => it2OptSetDouble
      case _: AttrSetOptBoolean        => it2OptSetBoolean
      case _: AttrSetOptBigInt         => it2OptSetBigInt
      case _: AttrSetOptBigDecimal     => it2OptSetBigDecimal
      case _: AttrSetOptDate           => it2OptSetDate
      case _: AttrSetOptDuration       => it2OptSetDuration
      case _: AttrSetOptInstant        => it2OptSetInstant
      case _: AttrSetOptLocalDate      => it2OptSetLocalDate
      case _: AttrSetOptLocalTime      => it2OptSetLocalTime
      case _: AttrSetOptLocalDateTime  => it2OptSetLocalDateTime
      case _: AttrSetOptOffsetTime     => it2OptSetOffsetTime
      case _: AttrSetOptOffsetDateTime => it2OptSetOffsetDateTime
      case _: AttrSetOptZonedDateTime  => it2OptSetZonedDateTime
      case _: AttrSetOptUUID           => it2OptSetUUID
      case _: AttrSetOptURI            => it2OptSetURI
      case _: AttrSetOptByte           => it2OptSetByte
      case _: AttrSetOptShort          => it2OptSetShort
      case _: AttrSetOptChar           => it2OptSetChar
    })
  }

  private def resAttrSeqMan(a: AttrSeqMan): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSeqManID             => it2ListId
      case _: AttrSeqManString         => it2ListString
      case _: AttrSeqManInt            => it2ListInt
      case _: AttrSeqManLong           => it2ListLong
      case _: AttrSeqManFloat          => it2ListFloat
      case _: AttrSeqManDouble         => it2ListDouble
      case _: AttrSeqManBoolean        => it2ListBoolean
      case _: AttrSeqManBigInt         => it2ListBigInt
      case _: AttrSeqManBigDecimal     => it2ListBigDecimal
      case _: AttrSeqManDate           => it2ListDate
      case _: AttrSeqManDuration       => it2ListDuration
      case _: AttrSeqManInstant        => it2ListInstant
      case _: AttrSeqManLocalDate      => it2ListLocalDate
      case _: AttrSeqManLocalTime      => it2ListLocalTime
      case _: AttrSeqManLocalDateTime  => it2ListLocalDateTime
      case _: AttrSeqManOffsetTime     => it2ListOffsetTime
      case _: AttrSeqManOffsetDateTime => it2ListOffsetDateTime
      case _: AttrSeqManZonedDateTime  => it2ListZonedDateTime
      case _: AttrSeqManUUID           => it2ListUUID
      case _: AttrSeqManURI            => it2ListURI
      case _: AttrSeqManByte           => it2ListByte
      case _: AttrSeqManShort          => it2ListShort
      case _: AttrSeqManChar           => it2ListChar
    })
  }

  private def resAttrSeqOpt(a: AttrSeqOpt): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSeqOptID             => it2OptListId
      case _: AttrSeqOptString         => it2OptListString
      case _: AttrSeqOptInt            => it2OptListInt
      case _: AttrSeqOptLong           => it2OptListLong
      case _: AttrSeqOptFloat          => it2OptListFloat
      case _: AttrSeqOptDouble         => it2OptListDouble
      case _: AttrSeqOptBoolean        => it2OptListBoolean
      case _: AttrSeqOptBigInt         => it2OptListBigInt
      case _: AttrSeqOptBigDecimal     => it2OptListBigDecimal
      case _: AttrSeqOptDate           => it2OptListDate
      case _: AttrSeqOptDuration       => it2OptListDuration
      case _: AttrSeqOptInstant        => it2OptListInstant
      case _: AttrSeqOptLocalDate      => it2OptListLocalDate
      case _: AttrSeqOptLocalTime      => it2OptListLocalTime
      case _: AttrSeqOptLocalDateTime  => it2OptListLocalDateTime
      case _: AttrSeqOptOffsetTime     => it2OptListOffsetTime
      case _: AttrSeqOptOffsetDateTime => it2OptListOffsetDateTime
      case _: AttrSeqOptZonedDateTime  => it2OptListZonedDateTime
      case _: AttrSeqOptUUID           => it2OptListUUID
      case _: AttrSeqOptURI            => it2OptListURI
      case _: AttrSeqOptByte           => it2OptListByte
      case _: AttrSeqOptShort          => it2OptListShort
      case _: AttrSeqOptChar           => it2OptListChar
    })
  }

  private def resAttrMapMan(a: AttrMapMan): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrMapManID             => noId
      case _: AttrMapManString         => it2MapString
      case _: AttrMapManInt            => it2MapInt
      case _: AttrMapManLong           => it2MapLong
      case _: AttrMapManFloat          => it2MapFloat
      case _: AttrMapManDouble         => it2MapDouble
      case _: AttrMapManBoolean        => it2MapBoolean
      case _: AttrMapManBigInt         => it2MapBigInt
      case _: AttrMapManBigDecimal     => it2MapBigDecimal
      case _: AttrMapManDate           => it2MapDate
      case _: AttrMapManDuration       => it2MapDuration
      case _: AttrMapManInstant        => it2MapInstant
      case _: AttrMapManLocalDate      => it2MapLocalDate
      case _: AttrMapManLocalTime      => it2MapLocalTime
      case _: AttrMapManLocalDateTime  => it2MapLocalDateTime
      case _: AttrMapManOffsetTime     => it2MapOffsetTime
      case _: AttrMapManOffsetDateTime => it2MapOffsetDateTime
      case _: AttrMapManZonedDateTime  => it2MapZonedDateTime
      case _: AttrMapManUUID           => it2MapUUID
      case _: AttrMapManURI            => it2MapURI
      case _: AttrMapManByte           => it2MapByte
      case _: AttrMapManShort          => it2MapShort
      case _: AttrMapManChar           => it2MapChar
    })
  }

  private def resAttrMapOpt(a: AttrMapOpt): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrMapOptID             => noId
      case _: AttrMapOptString         => it2OptMapString
      case _: AttrMapOptInt            => it2OptMapInt
      case _: AttrMapOptLong           => it2OptMapLong
      case _: AttrMapOptFloat          => it2OptMapFloat
      case _: AttrMapOptDouble         => it2OptMapDouble
      case _: AttrMapOptBoolean        => it2OptMapBoolean
      case _: AttrMapOptBigInt         => it2OptMapBigInt
      case _: AttrMapOptBigDecimal     => it2OptMapBigDecimal
      case _: AttrMapOptDate           => it2OptMapDate
      case _: AttrMapOptDuration       => it2OptMapDuration
      case _: AttrMapOptInstant        => it2OptMapInstant
      case _: AttrMapOptLocalDate      => it2OptMapLocalDate
      case _: AttrMapOptLocalTime      => it2OptMapLocalTime
      case _: AttrMapOptLocalDateTime  => it2OptMapLocalDateTime
      case _: AttrMapOptOffsetTime     => it2OptMapOffsetTime
      case _: AttrMapOptOffsetDateTime => it2OptMapOffsetDateTime
      case _: AttrMapOptZonedDateTime  => it2OptMapZonedDateTime
      case _: AttrMapOptUUID           => it2OptMapUUID
      case _: AttrMapOptURI            => it2OptMapURI
      case _: AttrMapOptByte           => it2OptMapByte
      case _: AttrMapOptShort          => it2OptMapShort
      case _: AttrMapOptChar           => it2OptMapChar
    })
  }
}
