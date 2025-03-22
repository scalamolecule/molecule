package molecule.core.util

import molecule.base.error.ModelError
import molecule.core.marshalling.ConnProxy
import scala.annotation.tailrec
import molecule.core.ast.DataModel._

trait ModelUtils {

  @tailrec
  private def count(es: List[Element], acc: Int): Int = {
    es match {
      case e :: tail => e match {
        case _: Mandatory @unchecked => count(tail, acc + 1)
        case _: Optional @unchecked  => count(tail, acc + 1)
        case _: OptRef               => count(tail, acc + 1)
        case _: OptEntity            => count(tail, acc + 1)
        case _: Nested               => count(tail, acc + 1)
        case _: OptNested            => count(tail, acc + 1)
        case _                       => count(tail, acc)
      }
      case Nil       => acc
    }
  }

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }

  final protected def getInitialEntity(elements: List[Element]): String = {
    elements.head match {
      case a: Attr                               => a.ent
      case r: Ref                                => r.ent
      case OptRef(Ref(ent, _, _, _, _, _), _)    => ent
      case OptEntity(attrs)                      => attrs.head.ent
      case Nested(Ref(ent, _, _, _, _, _), _)    => ent
      case OptNested(Ref(ent, _, _, _, _, _), _) => ent
      case other                                 => throw ModelError("Unexpected head element: " + other)
    }
  }

  @tailrec
  final protected def getInitialNonGenericEntity(elements: List[Element]): String = {
    elements.head match {
      case a: Attr if a.attr == "id"             => getInitialNonGenericEntity(elements.tail)
      case a: Attr                               => a.ent
      case r: Ref                                => r.ent
      case OptRef(Ref(ent, _, _, _, _, _), _)    => ent
      case OptEntity(attrs)                      => attrs.head.ent
      case Nested(Ref(ent, _, _, _, _, _), _)    => ent
      case OptNested(Ref(ent, _, _, _, _, _), _) => ent
      case other                                 => throw ModelError("Unexpected head element: " + other)
    }
  }


  def hasRef(elements: List[Element]) = {
    elements.exists {
      case _: Ref => true
      case _      => false
    }
  }


  @tailrec
  final def getAttrNames(elements: List[Element], attrs: Set[String] = Set.empty[String]): Set[String] = {
    elements match {
      case element :: tail => element match {
        case a: Attr          => getAttrNames(tail, attrs + a.name)
        case OptRef(_, es)    => getAttrNames(tail ++ es, attrs)
        case OptEntity(as)    => getAttrNames(tail ++ as, attrs)
        case Nested(_, es)    => getAttrNames(tail ++ es, attrs)
        case OptNested(_, es) => getAttrNames(tail ++ es, attrs)
        case _                => getAttrNames(tail, attrs)
      }
      case Nil             => attrs
    }
  }


  protected def noKeywords(elements: List[Element], optProxy: Option[ConnProxy]): List[Element] = {
    if (optProxy.isEmpty || optProxy.get.reservedEntities.isEmpty)
      return elements

    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc :+ prepareAttr(a))
            case r: Ref       => prepare(tail, acc :+ prepareRef(r))
            case b: BackRef   => prepare(tail, acc :+ prepareBackRef(b))
            case r: OptRef    => prepare(tail, acc :+ prepareOptRef(r))
            case e: OptEntity => prepare(tail, acc :+ prepareOptEntity(e))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: OptNested => prepare(tail, acc :+ prepareOptNested(n))
          }
        case Nil             => acc
      }
    }

    def prepareAttr(a: Attr): Attr = {
      a.filterAttr.fold {
        resolveReservedNames(a, optProxy.get)
      } { case (dir, filterPath, filterAttr0) =>
        val filterAttr = resolveReservedNames(filterAttr0, optProxy.get)
        resolveReservedNames(a, optProxy.get, Some((dir, filterPath, filterAttr)))
      }
    }

    def prepareRef(ref: Ref): Ref = {
      val (ent, refAttr, refEntity) = nonReservedRef(ref, optProxy.get)
      ref.copy(ent = ent, refAttr = refAttr, ref = refEntity)
    }

    def prepareBackRef(backRef: BackRef): BackRef = {
      val (prevEnt, curEnt) = nonReservedBackRef(backRef, optProxy.get)
      backRef.copy(prev = prevEnt, cur = curEnt)
    }

    def prepareOptRef(optRef: OptRef): OptRef = {
      OptRef(optRef.ref, prepare(optRef.elements, Nil))
    }

    def prepareOptEntity(optEntity: OptEntity): OptEntity = {
      OptEntity(prepare(optEntity.attrs, Nil).asInstanceOf[List[Attr]])
    }

    def prepareNested(nested: Nested): Nested = {
      Nested(nested.ref, prepare(nested.elements, Nil))
    }

    def prepareOptNested(nested: OptNested): OptNested = {
      OptNested(nested.ref, prepare(nested.elements, Nil))
    }

    prepare(elements, Nil)
  }

  protected def validKey(key: String): String = {
    if (key.matches("[a-zA-Z_\\-0-9]+")) key else {
      throw ModelError(
        "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
      )
    }
  }

  def noOptional(a: Attr): Nothing =
    throw ModelError(s"Can't update optional values (${a.cleanName}_?)")

  def noNested: Nothing =
    throw ModelError(s"Nested data structure not allowed in update molecule.")

  def noOptRef: Nothing =
    throw ModelError(s"Optional ref data structure not allowed in update molecule.")

  def noEntityReUseAfterBackref(
    nextElement: Element,
    prevRefs: List[String],
    backRef: String
  ): Unit = {
    nextElement match {
      case Ref(_, refAttr, _, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
        s"Can't re-use previous entity ${refAttr.capitalize} after backref _$backRef."
      )

      case _ => () // ok
    }
  }

  private def indexes(coord: Seq[Int]): (Int, Int, Option[Int]) = {
    coord match {
      case Seq(entIndex, refAttrIndex, refIndex) => (entIndex, refAttrIndex, Some(refIndex))
      case Seq(entIndex, refAttrIndex)           => (entIndex, refAttrIndex, None)
    }
  }

  private final def nonReservedAttr(a: Attr, proxy: ConnProxy): (String, String) = {
    val (entIndex, attrIndex, _) = indexes(a.coord)
    (
      if (proxy.reservedEntities(entIndex)) a.ent + "_" else a.ent,
      if (proxy.reservedAttributes(attrIndex)) a.attr + "_" else a.attr
    )
  }

  private final def nonReservedRef(r: Ref, proxy: ConnProxy): (String, String, String) = {
    val Seq(entIndex, refAttrIndex, refIndex) = r.coord
    val (reservedEnts, reservedAttrs)         = (proxy.reservedEntities, proxy.reservedAttributes)
    val ref                                   = r.ref
    (
      if (reservedEnts(entIndex)) r.ent + "_" else r.ent,
      if (reservedAttrs(refAttrIndex)) r.refAttr + "_" else r.refAttr,
      if (reservedEnts(refIndex)) ref + "_" else ref,
    )
  }

  private final def nonReservedBackRef(backRef: BackRef, proxy: ConnProxy): (String, String) = {
    val Seq(prevEntIndex, curEntIndex) = backRef.coord
    val reservedEntitites              = proxy.reservedEntities
    (
      if (reservedEntitites(prevEntIndex)) backRef.prev + "_" else backRef.prev,
      if (reservedEntitites(curEntIndex)) backRef.cur + "_" else backRef.cur,
    )
  }

  private final def resolveReservedNames(
    a0: Attr,
    proxy: ConnProxy,
    optFilterAttr: Option[(Int, List[String], Attr)] = None
  ): Attr = {
    a0 match {
      case a: AttrOne => a match {
        case a: AttrOneMan =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneManID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrOneOpt =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneOptID             => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptString         => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptInt            => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptLong           => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptFloat          => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptDouble         => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptBoolean        => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptBigInt         => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptBigDecimal     => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptDate           => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptDuration       => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptInstant        => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptLocalDate      => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptLocalTime      => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptLocalDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptOffsetTime     => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptOffsetDateTime => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptZonedDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptUUID           => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptURI            => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptByte           => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptShort          => a.copy(ent = ent1, attr = attr1)
            case a: AttrOneOptChar           => a.copy(ent = ent1, attr = attr1)
          }
        case a: AttrOneTac =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneTacID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
      }

      case a: AttrSet => a match {
        case a: AttrSetMan =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetManID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrSetOpt =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetOptID             => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptString         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptInt            => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptLong           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptFloat          => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptDouble         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptBoolean        => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptBigInt         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptBigDecimal     => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptDate           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptDuration       => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptInstant        => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptLocalDate      => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptLocalTime      => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptLocalDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptOffsetTime     => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptOffsetDateTime => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptZonedDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptUUID           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptURI            => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptByte           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptShort          => a.copy(ent = ent1, attr = attr1)
            case a: AttrSetOptChar           => a.copy(ent = ent1, attr = attr1)
          }
        case a: AttrSetTac =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetTacID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
      }

      case a: AttrSeq => a match {
        case a: AttrSeqMan =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSeqManID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqManChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrSeqOpt =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSeqOptID             => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptString         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptInt            => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptLong           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptFloat          => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptDouble         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptBoolean        => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptBigInt         => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptBigDecimal     => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptDate           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptDuration       => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptInstant        => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptLocalDate      => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptLocalTime      => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptLocalDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptOffsetTime     => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptOffsetDateTime => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptZonedDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptUUID           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptURI            => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptByte           => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptShort          => a.copy(ent = ent1, attr = attr1)
            case a: AttrSeqOptChar           => a.copy(ent = ent1, attr = attr1)
          }
        case a: AttrSeqTac =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSeqTacID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSeqTacChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
      }

      case a: AttrMap => a match {
        case a: AttrMapMan =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrMapManID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapManChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrMapOpt =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrMapOptID             => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptString         => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptInt            => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptLong           => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptFloat          => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptDouble         => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptBoolean        => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptBigInt         => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptBigDecimal     => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptDate           => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptDuration       => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptInstant        => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptLocalDate      => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptLocalTime      => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptLocalDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptOffsetTime     => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptOffsetDateTime => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptZonedDateTime  => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptUUID           => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptURI            => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptByte           => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptShort          => a.copy(ent = ent1, attr = attr1)
            case a: AttrMapOptChar           => a.copy(ent = ent1, attr = attr1)
          }
        case a: AttrMapTac =>
          val (ent1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrMapTacID             => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacString         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacInt            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacLong           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacFloat          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacDouble         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacBoolean        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacBigInt         => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacBigDecimal     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacDate           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacDuration       => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacInstant        => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacLocalDate      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacLocalTime      => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacLocalDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacOffsetTime     => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacOffsetDateTime => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacZonedDateTime  => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacUUID           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacURI            => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacByte           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacShort          => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrMapTacChar           => a.copy(ent = ent1, attr = attr1, filterAttr = optFilterAttr)
          }
      }
    }
  }
}
