package molecule.core.util

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import scala.annotation.tailrec


trait ModelUtils {

  @tailrec
  private def count(es: List[Element], acc: Int): Int = {
    es match {
      case e :: tail => e match {
        case _: Mandatory @unchecked => count(tail, acc + 1)
        case _: Optional @unchecked  => count(tail, acc + 1)
        case _: Nested               => count(tail, acc + 1)
        case _: NestedOpt            => count(tail, acc + 1)
        case _                       => count(tail, acc)
      }
      case Nil       => acc
    }
  }

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }

  final protected def getInitialNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr                              => a.ns
      case b: Ref                               => b.ns
      case Nested(Ref(ns, _, _, _, _, _), _)    => ns
      case NestedOpt(Ref(ns, _, _, _, _, _), _) => ns
      case other                                => throw ModelError("Unexpected head element: " + other)
    }
  }

  @tailrec
  final protected def getInitialNonGenericNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr if a.attr == "id"            => getInitialNonGenericNs(elements.tail)
      case a: Attr                              => a.ns
      case b: Ref                               => b.ns
      case Nested(Ref(ns, _, _, _, _, _), _)    => ns
      case NestedOpt(Ref(ns, _, _, _, _, _), _) => ns
      case other                                => throw ModelError("Unexpected head element: " + other)
    }
  }


  def isRefUpdate(elements: List[Element]) = {
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
        case Nested(_, es)    => getAttrNames(tail ++ es, attrs)
        case NestedOpt(_, es) => getAttrNames(tail ++ es, attrs)
        case _                => getAttrNames(tail, attrs)
      }
      case Nil             => attrs
    }
  }


  protected def noKeywords(elements: List[Element], optProxy: Option[ConnProxy]): List[Element] = {
    if (optProxy.isEmpty || optProxy.get.reserved.isEmpty)
      return elements

    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc :+ prepareAttr(a))
            case r: Ref       => prepare(tail, acc :+ prepareRef(r))
            case r: BackRef   => prepare(tail, acc :+ prepareBackRef(r))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt => prepare(tail, acc :+ prepareNestedOpt(n))
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
      val (ns, refAttr, refNs) = nonReservedRef(ref, optProxy.get)
      ref.copy(ns = ns, refAttr = refAttr, refNs = refNs)
    }

    def prepareBackRef(backRef: BackRef): BackRef = {
      val (prevNs, curNs) = nonReservedBackRef(backRef, optProxy.get)
      backRef.copy(prevNs = prevNs, curNs = curNs)
    }

    def prepareNested(nested: Nested): Nested = {
      Nested(nested.ref, prepare(nested.elements, Nil))
    }

    def prepareNestedOpt(nested: NestedOpt): NestedOpt = {
      NestedOpt(nested.ref, prepare(nested.elements, Nil))
    }

    prepare(elements, Nil)
  }

  private def indexes(coord: Seq[Int]): (Int, Int, Option[Int]) = {
    coord match {
      case Seq(nsIndex, refAttrIndex, refNsIndex) => (nsIndex, refAttrIndex, Some(refNsIndex))
      case Seq(nsIndex, refAttrIndex)             => (nsIndex, refAttrIndex, None)
    }
  }

  private final  def nonReservedAttr(a: Attr, proxy: ConnProxy): (String, String) = {
    val (nsIndex, attrIndex, _) = indexes(a.coord)
    (
      if (proxy.reserved.get.reservedNss(nsIndex)) a.ns + "_" else a.ns,
      if (proxy.reserved.get.reservedAttrs(attrIndex)) a.attr + "_" else a.attr
    )
  }

  private final  def nonReservedRef(ref: Ref, proxy: ConnProxy): (String, String, String) = {
    val Seq(nsIndex, refAttrIndex, refNsIndex) = ref.coord
    val (reservedNss, reservedAttrs)           = (proxy.reserved.get.reservedNss, proxy.reserved.get.reservedAttrs)
    val refNs                                  = ref.refNs
    (
      if (reservedNss(nsIndex)) ref.ns + "_" else ref.ns,
      if (reservedAttrs(refAttrIndex)) ref.refAttr + "_" else ref.refAttr,
      if (reservedNss(refNsIndex)) refNs + "_" else refNs,
    )
  }

  private final  def nonReservedBackRef(backRef: BackRef, proxy: ConnProxy): (String, String) = {
    val Seq(prevNsIndex, curNsIndex) = backRef.coord
    val reservedNss                  = proxy.reserved.get.reservedNss
    (
      if (reservedNss(prevNsIndex)) backRef.prevNs + "_" else backRef.prevNs,
      if (reservedNss(curNsIndex)) backRef.curNs + "_" else backRef.curNs,
    )
  }

  private final  def resolveReservedNames(
    a0: Attr,
    proxy: ConnProxy,
    optFilterAttr: Option[(Int, List[String], Attr)] = None
  ): Attr = {
    a0 match {
      case a: AttrOne => a match {
        case a: AttrOneMan =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneManID             => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManString         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManInt            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLong           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManFloat          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDouble         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBoolean        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigInt         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigDecimal     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDate           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDuration       => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManInstant        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalDate      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalTime      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLocalDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManOffsetTime     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManOffsetDateTime => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManZonedDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManUUID           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManURI            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManByte           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManShort          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManChar           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrOneOpt =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneOptID             => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptString         => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptInt            => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptLong           => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptFloat          => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptDouble         => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptBoolean        => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptBigInt         => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptBigDecimal     => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptDate           => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptDuration       => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptInstant        => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptLocalDate      => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptLocalTime      => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptLocalDateTime  => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptOffsetTime     => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptOffsetDateTime => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptZonedDateTime  => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptUUID           => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptURI            => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptByte           => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptShort          => a.copy(ns = ns1, attr = attr1)
            case a: AttrOneOptChar           => a.copy(ns = ns1, attr = attr1)
          }
        case a: AttrOneTac =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneTacID             => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacString         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacInt            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLong           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacFloat          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDouble         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBoolean        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBigInt         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacBigDecimal     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDate           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacDuration       => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacInstant        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalDate      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalTime      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacLocalDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacOffsetTime     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacOffsetDateTime => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacZonedDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacUUID           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacURI            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacByte           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacShort          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneTacChar           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
          }
      }
      case a: AttrSet => a match {
        case a: AttrSetMan =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetManID             => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManString         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManInt            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLong           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManFloat          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDouble         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBoolean        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBigInt         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManBigDecimal     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDate           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManDuration       => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManInstant        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalDate      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalTime      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManLocalDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManOffsetTime     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManOffsetDateTime => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManZonedDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManUUID           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManURI            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManByte           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManShort          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetManChar           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrSetOpt =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetOptID             => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptString         => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptInt            => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptLong           => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptFloat          => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptDouble         => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptBoolean        => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptBigInt         => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptBigDecimal     => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptDate           => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptDuration       => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptInstant        => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptLocalDate      => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptLocalTime      => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptLocalDateTime  => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptOffsetTime     => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptOffsetDateTime => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptZonedDateTime  => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptUUID           => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptURI            => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptByte           => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptShort          => a.copy(ns = ns1, attr = attr1)
            case a: AttrSetOptChar           => a.copy(ns = ns1, attr = attr1)
          }
        case a: AttrSetTac =>
          val (ns1, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetTacID             => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacString         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacInt            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLong           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacFloat          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDouble         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBoolean        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBigInt         => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacBigDecimal     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDate           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacDuration       => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacInstant        => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalDate      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalTime      => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacLocalDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacOffsetTime     => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacOffsetDateTime => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacZonedDateTime  => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacUUID           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacURI            => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacByte           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacShort          => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrSetTacChar           => a.copy(ns = ns1, attr = attr1, filterAttr = optFilterAttr)
          }
      }
    }
  }
}
