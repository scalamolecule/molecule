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
        case _: Mandatory@unchecked => count(tail, acc + 1)
        case _: Optional@unchecked  => count(tail, acc + 1)
        case _: Nested              => count(tail, acc + 1)
        case _: NestedOpt           => count(tail, acc + 1)
        case _                      => count(tail, acc)
      }
      case Nil       => acc
    }
  }

  protected def countValueAttrs(elements: List[Element]): Int = {
    count(elements, 0)
  }

  final protected def getInitialNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr => a.ns
      case b: Ref  => b.ns
      case other   => throw ModelError("Unexpected head element: " + other)
    }
  }

  @tailrec
  final protected def getInitialNonGenericNs(elements: List[Element]): String = {
    elements.head match {
      case a: Attr if a.attr == "id"         => getInitialNonGenericNs(elements.tail)
      case a: Attr                           => a.ns
      case b: Ref                            => b.ns
      case Nested(Ref(ns, _, _, _, _), _)    => ns
      case NestedOpt(Ref(ns, _, _, _, _), _) => ns
      case other                             => throw ModelError("Unexpected head element: " + other)
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
      } { filterAttr0 =>
        val filterAttr = resolveReservedNames(filterAttr0, optProxy.get)
        resolveReservedNames(a, optProxy.get, Some(filterAttr))
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

  final protected def nonReservedAttr(a: Attr, proxy: ConnProxy): (String, String) = {
    val (nsIndex, attrIndex, _) = indexes(a.coord)
    (
      if (proxy.reserved.get.reservedNss(nsIndex)) a.ns + "_" else a.ns,
      if (proxy.reserved.get.reservedAttrs(attrIndex)) a.attr + "_" else a.attr
    )
  }

  final protected def nonReservedRef(ref: Ref, proxy: ConnProxy): (String, String, String) = {
    val Seq(nsIndex, refAttrIndex, refNsIndex) = ref.coord
    val (reservedNss, reservedAttrs)           = (proxy.reserved.get.reservedNss, proxy.reserved.get.reservedAttrs)
    val refNs                                  = ref.refNs
    (
      if (reservedNss(nsIndex)) ref.ns + "_" else ref.ns,
      if (reservedAttrs(refAttrIndex)) ref.refAttr + "_" else ref.refAttr,
      if (reservedNss(refNsIndex)) refNs + "_" else refNs,
    )
  }

  final protected def nonReservedBackRef(backRef: BackRef, proxy: ConnProxy): (String, String) = {
    val Seq(prevNsIndex, curNsIndex) = backRef.coord
    val reservedNss                  = proxy.reserved.get.reservedNss
    (
      if (reservedNss(prevNsIndex)) backRef.prevNs + "_" else backRef.prevNs,
      if (reservedNss(curNsIndex)) backRef.curNs + "_" else backRef.curNs,
    )
  }

  final protected def resolveReservedNames(
    a0: Attr,
    proxy: ConnProxy,
    optFilterAttr: Option[Attr] = None
  ): Attr = {
    a0 match {
      case a: AttrOne => a match {
        case a: AttrOneMan =>
          val (ns, attr1) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneManString     => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManInt        => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManLong       => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManFloat      => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDouble     => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBoolean    => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigInt     => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManBigDecimal => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManDate       => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManUUID       => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManURI        => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManByte       => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManShort      => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
            case a: AttrOneManChar       => a.copy(ns = ns, attr = attr1, filterAttr = optFilterAttr)
          }
        case a: AttrOneOpt =>
          val (ns, attr) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneOptString     => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptInt        => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptLong       => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptFloat      => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptDouble     => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptBoolean    => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptBigInt     => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptBigDecimal => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptDate       => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptUUID       => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptURI        => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptByte       => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptShort      => a.copy(ns = ns, attr = attr)
            case a: AttrOneOptChar       => a.copy(ns = ns, attr = attr)
          }
        case a: AttrOneTac =>
          val (ns, attr) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrOneTacString     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacInt        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacLong       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacFloat      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacDouble     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacBoolean    => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacBigInt     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacBigDecimal => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacDate       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacUUID       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacURI        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacByte       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacShort      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrOneTacChar       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
          }
      }
      case a: AttrSet => a match {
        case a: AttrSetMan =>
          val (ns, attr) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetManString     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManInt        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManLong       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManFloat      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManDouble     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManBoolean    => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManBigInt     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManBigDecimal => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManDate       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManUUID       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManURI        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManByte       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManShort      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetManChar       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
          }
        case a: AttrSetOpt =>
          val (ns, attr) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetOptString     => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptInt        => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptLong       => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptFloat      => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptDouble     => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptBoolean    => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptBigInt     => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptBigDecimal => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptDate       => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptUUID       => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptURI        => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptByte       => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptShort      => a.copy(ns = ns, attr = attr)
            case a: AttrSetOptChar       => a.copy(ns = ns, attr = attr)
          }
        case a: AttrSetTac =>
          val (ns, attr) = nonReservedAttr(a, proxy)
          a match {
            case a: AttrSetTacString     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacInt        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacLong       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacFloat      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacDouble     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacBoolean    => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacBigInt     => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacBigDecimal => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacDate       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacUUID       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacURI        => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacByte       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacShort      => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
            case a: AttrSetTacChar       => a.copy(ns = ns, attr = attr, filterAttr = optFilterAttr)
          }
      }
    }
  }
}
