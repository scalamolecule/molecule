package molecule.datalog.core.query

import java.util.{Iterator => jIterator}
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.core.ast.DataModel._
import scala.annotation.tailrec


trait ResolveOptRefPull[Tpl] { self: Model2DatomicQuery[Tpl] =>

  final protected def resolveOptRefElements(
    ref: Ref,
    optionalElements: List[Element]
  ): List[List[jIterator[_] => Any]] = {

    @tailrec
    def addPullAttrs(
      elements: List[Element],
      level: Int,
      attrIndex: Int,
      acc: String
    ): (String, Option[Element], List[Element], Int) = {
      val indent = "  " * (level + 7)
      elements match {
        case head :: tail =>
          head match {
            case a: Attr if a.op != V => throw ModelError(
              s"Expressions not allowed in optional ref queries (${a.name})."
            )
            case a: AttrOneMan        =>
              resAttrOneMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrOneOpt =>
              resAttrOneOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrSetMan =>
              resAttrSetMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrSetOpt =>
              resttrSetOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrSeqMan =>
              resAttrSeqMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrSeqOpt =>
              resAttrSeqOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrMapMan =>
              resAttrMapMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case a: AttrMapOpt =>
              resAttrMapOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(indent, a))

            case ref: Ref         => (acc, Some(ref), tail, attrIndex)
            case backRef: BackRef => (acc, Some(backRef), tail, attrIndex)
            case optRef: OptRef   => (acc, Some(optRef), Nil, attrIndex)

            case nestedOpt: OptNested =>
              if (isManNested) {
                noMixedNestedModes
              }
              noCardManyInsideOptRef()
              (acc, Some(nestedOpt), Nil, attrIndex)

            case _: Nested =>
              if (isOptNested) {
                noMixedNestedModes
              }
              noCardManyInsideOptRef()
              noMixedNestedModes

            case a: AttrOneTac => throw ModelError(
              s"Tacit attributes not allowed in optional ref queries (${a.name}_).")

            case other => throw ModelError(
              "Unexpected element in optional ref molecule: " + other
            )
          }
        case Nil          => (acc, None, Nil, attrIndex)
      }
    }

    def resolvePullRef(
      ref: Ref,
      elements: List[Element],
      level: Int,
      attrIndex: Int
    ): String = {
      val indent  = "  " * (level + 6)
      val refAttr = s":${ref.ent}/${ref.refAttr}"
      addPullAttrs(elements, level, attrIndex, "") match {
        case (acc1, None, Nil, _) =>
          s"""
             |$indent{($refAttr :default "$none") [$acc1
             |$indent]}""".stripMargin

        case (acc1, Some(ref1@Ref(_, _, _, CardOne, _, _)), tail, attrIndex1) =>
          refDepths = refDepths.init :+ refDepths.last + 1
          // Continue increasing attrIndex after refs
          val attrs = resolvePullRef(ref1, tail, level + 1, attrIndex1)
          s"""
             |$indent{($refAttr :default "$none") [$acc1$attrs
             |$indent]}""".stripMargin

        case (_, Some(ref: Ref), _, _) => onlyCardOneInsideOptRef(ref)

        case (acc1, Some(OptRef(ref1, elements1)), _, _) =>
          // Add casts on this level
          pullCastss = pullCastss :+ pullCasts.toList

          // start new nested level of casts
          pullCasts.clear()
          refDepths = refDepths :+ 0

          val attrs = resolvePullRef(ref1, elements1, level + 1, 0)
          s"""
             |$indent{($refAttr :default "$none") [$acc1$attrs
             |$indent  ]
             |$indent}""".stripMargin

        case (_, Some(other), _, _) => unexpectedElement(other)
        case other                  => throw ModelError("Unexpected resolvePullRef coordinates: " + other)
      }
    }

    val attrs = resolvePullRef(ref, optionalElements, 0, 0)
    val e     = es.last
    val refId = "?id" + varIndex
    find +=
      s"""(
         |          pull $refId [$attrs
         |          ]
         |        )""".stripMargin
    where += s"[(identity $e) $refId]" -> wGround

    // Casts for this possibly nested optional ref data
    val nestedOptRefCasts = pullCastss :+ pullCasts.toList

    // Start over for next adjacent optional ref data
    pullCastss = Nil
    pullCasts.clear()
    varIndex += 1

    nestedOptRefCasts
  }


  private def renderPull(indent: String, a: Attr): String = {
    if (a.attr == "id") {
      s"""\n$indent:db/id"""
    } else a match {
      case _: AttrSeq if !a.isInstanceOf[AttrSeqManByte] && !a.isInstanceOf[AttrSeqOptByte] =>
        val (ent, attr) = (a.ent, a.attr)
        s"""
           |$indent{(:$ent/$attr :limit nil :default "$none") [
           |$indent  :$ent.$attr/i_ :$ent.$attr/v_]}""".stripMargin

      case _: AttrMap =>
        val (ent, attr) = (a.ent, a.attr)
        s"""
           |$indent{(:$ent/$attr :limit nil :default "$none") [
           |$indent  :$ent.$attr/k_ :$ent.$attr/v_]}""".stripMargin

      case _ =>
        s"""
           |$indent(:${a.ent}/${a.attr} :default "$none")""".stripMargin
    }
  }


  private def resAttrOneMan(a: AttrOneMan): Unit = {
    pullCasts += (a match {
      case _: AttrOneManID             => it2Id
      case _: AttrOneManString         => it2String
      case _: AttrOneManInt            => it2Int
      case _: AttrOneManLong           => it2Long
      case _: AttrOneManFloat          => it2Float
      case _: AttrOneManDouble         => it2Double
      case _: AttrOneManBoolean        => it2Boolean
      case _: AttrOneManBigInt         => it2BigInt
      case _: AttrOneManBigDecimal     => it2BigDecimal
      case _: AttrOneManDate           => it2Date
      case _: AttrOneManDuration       => it2Duration
      case _: AttrOneManInstant        => it2Instant
      case _: AttrOneManLocalDate      => it2LocalDate
      case _: AttrOneManLocalTime      => it2LocalTime
      case _: AttrOneManLocalDateTime  => it2LocalDateTime
      case _: AttrOneManOffsetTime     => it2OffsetTime
      case _: AttrOneManOffsetDateTime => it2OffsetDateTime
      case _: AttrOneManZonedDateTime  => it2ZonedDateTime
      case _: AttrOneManUUID           => it2UUID
      case _: AttrOneManURI            => it2URI
      case _: AttrOneManByte           => it2Byte
      case _: AttrOneManShort          => it2Short
      case _: AttrOneManChar           => it2Char
    })
  }

  private def resAttrOneOpt(a: AttrOneOpt): Unit = {
    pullCasts += (a match {
      case _: AttrOneOptID             => it2OptId
      case _: AttrOneOptString         => it2OptString
      case _: AttrOneOptInt            => it2OptInt
      case _: AttrOneOptLong           => it2OptLong
      case _: AttrOneOptFloat          => it2OptFloat
      case _: AttrOneOptDouble         => it2OptDouble
      case _: AttrOneOptBoolean        => it2Boolean
      case _: AttrOneOptBigInt         => it2OptBigInt
      case _: AttrOneOptBigDecimal     => it2OptBigDecimal
      case _: AttrOneOptDate           => it2OptDate
      case _: AttrOneOptDuration       => it2OptDuration
      case _: AttrOneOptInstant        => it2OptInstant
      case _: AttrOneOptLocalDate      => it2OptLocalDate
      case _: AttrOneOptLocalTime      => it2OptLocalTime
      case _: AttrOneOptLocalDateTime  => it2OptLocalDateTime
      case _: AttrOneOptOffsetTime     => it2OptOffsetTime
      case _: AttrOneOptOffsetDateTime => it2OptOffsetDateTime
      case _: AttrOneOptZonedDateTime  => it2OptZonedDateTime
      case _: AttrOneOptUUID           => it2OptUUID
      case _: AttrOneOptURI            => it2OptURI
      case _: AttrOneOptByte           => it2OptByte
      case _: AttrOneOptShort          => it2OptShort
      case _: AttrOneOptChar           => it2OptChar
    })
  }

  private def resAttrSetMan(a: AttrSetMan): Unit = {
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
