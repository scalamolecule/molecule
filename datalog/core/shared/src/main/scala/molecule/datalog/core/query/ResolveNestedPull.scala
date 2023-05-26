package molecule.datalog.core.query

import java.util.{Iterator => jIterator}
import molecule.base.ast.SchemaAST._
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
    with ModelUtils
    with MoleculeLogging { self: DatomicModel2Query[Tpl] =>


  final protected def resolveNestedOptElements(e: Var, ref: Ref, elements: List[Element]): Unit = {
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
              "Expressions not allowed in optional nested data structure. Found:\n  " + a
            )

            case a: AttrOneMan =>
              resolveAttrOneMan(a, attrIndex)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrOneOpt =>
              resolveAttrOneOpt(a, attrIndex)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSetMan =>
              resolveAttrSetMan(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case a: AttrSetOpt =>
              resolveAttrSetOpt(a)
              addPullAttrs(tail, level, attrIndex + 1, acc + renderPull(i, a))

            case ref: Ref                     => (acc, Some(ref), tail, attrIndex)
            case backRef: BackRef             => (acc, Some(backRef), tail, attrIndex)
            case nestedOpt: NestedOpt         => (acc, Some(nestedOpt), Nil, attrIndex)
            case _: Nested                    => noMixedNestedModes
            case Composite(compositeElements) =>
              aritiesComposite()
              addPullAttrs(compositeElements ++ tail, level, attrIndex, acc)

            case a: AttrOneTac => throw ModelError(
              "Tacit attributes not allowed in optional nested data structure. Found:\n" + a)
            case other         => throw ModelError(
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

        case (acc1, Some(ref1@Ref(_, _, _, CardOne, _)), tail, attrIndex1) =>
          refDepths = refDepths.init :+ refDepths.last + 1
          // Continue increasing attrIndex after refs
          val (attrs, append1) = resolvePullRef(ref1, tail, level + 1, attrIndex1, "")
          val res              = s"""\n$indent{($refAttr :limit nil :default "$none") [$acc1$attrs]}"""
          (res, append + append1)

        case (_, Some(ref: Ref), _, _) => throw ModelError(
          "Only cardinality-one refs allowed in optional nested data structures. Found: " + ref
        )

        case (acc1, Some(BackRef(backRef, _)), tail, attrIndex1) =>
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

        case (acc1, Some(NestedOpt(ref1, elements1)), _, _) =>
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
    find += s"(pull $e [$attrs$append])\n       "
  }


  private def renderPull(indent: String, a: Attr): String = {
    s"""\n$indent(:${a.ns}/${a.attr} :limit nil :default "$none")"""
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


  private def resolveAttrOneMan(a: AttrOneMan, attrIndex: Int): Unit = {
    aritiesAttr()
    a match {
      case a: AttrOneManString     => add(sortOneString(a, attrIndex), it2String, it2String2)
      case a: AttrOneManInt        => add(intSorter(a, attrIndex), it2Int, it2Int2)
      case a: AttrOneManLong       => add(sortOneLong(a, attrIndex), it2Long)
      case a: AttrOneManFloat      => add(floatSorter(a, attrIndex), it2Float)
      case a: AttrOneManDouble     => add(sortOneDouble(a, attrIndex), it2Double)
      case a: AttrOneManBoolean    =>
        // add(sortOneBooleanOptNested(a, attrIndex), it2Boolean)
        datomicFreePullBooleanBug
      case a: AttrOneManBigInt     => add(bigIntSorter(a, attrIndex), it2BigInt)
      case a: AttrOneManBigDecimal => add(sortOneBigDecimal(a, attrIndex), it2BigDecimal)
      case a: AttrOneManDate       => add(sortOneDate(a, attrIndex), it2Date)
      case a: AttrOneManUUID       => add(sortOneUUID(a, attrIndex), it2UUID)
      case a: AttrOneManURI        => add(sortOneURI(a, attrIndex), it2URI)
      case a: AttrOneManByte       => add(byteSorter(a, attrIndex), it2Byte)
      case a: AttrOneManShort      => add(shortSorter(a, attrIndex), it2Short)
      case a: AttrOneManChar       => add(sortOneChar(a, attrIndex), it2Char)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, attrIndex: Int): Unit = {
    aritiesAttr()
    a match {
      case _: AttrOneOptString     => add(sortOneOptFlatString(a, attrIndex), it2OptString)
      case _: AttrOneOptInt        => add(sortOneOptFlatInt(a, attrIndex), it2OptInt)
      case _: AttrOneOptLong       => add(sortOneOptFlatLong(a, attrIndex), it2OptLong)
      case _: AttrOneOptFloat      => add(sortOneOptFlatFloat(a, attrIndex), it2OptFloat)
      case _: AttrOneOptDouble     => add(sortOneOptFlatDouble(a, attrIndex), it2OptDouble)
      case _: AttrOneOptBoolean    =>
        // add(sortOneOptFlatBoolean(a, attrIndex), it2Boolean)
        datomicFreePullBooleanBug
      case _: AttrOneOptBigInt     => add(sortOneOptFlatBigInt(a, attrIndex), it2OptBigInt)
      case _: AttrOneOptBigDecimal => add(sortOneOptFlatBigDecimal(a, attrIndex), it2OptBigDecimal)
      case _: AttrOneOptDate       => add(sortOneOptFlatDate(a, attrIndex), it2OptDate)
      case _: AttrOneOptUUID       => add(sortOneOptFlatUUID(a, attrIndex), it2OptUUID)
      case _: AttrOneOptURI        => add(sortOneOptFlatURI(a, attrIndex), it2OptURI)
      case _: AttrOneOptByte       => add(sortOneOptFlatByte(a, attrIndex), it2OptByte)
      case _: AttrOneOptShort      => add(sortOneOptFlatShort(a, attrIndex), it2OptShort)
      case _: AttrOneOptChar       => add(sortOneOptFlatChar(a, attrIndex), it2OptChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSetManString     => it2SetString
      case _: AttrSetManInt        => it2SetInt
      case _: AttrSetManLong       => it2SetLong
      case _: AttrSetManFloat      => it2SetFloat
      case _: AttrSetManDouble     => it2SetDouble
      case _: AttrSetManBoolean    => it2SetBoolean
      case _: AttrSetManBigInt     => it2SetBigInt
      case _: AttrSetManBigDecimal => it2SetBigDecimal
      case _: AttrSetManDate       => it2SetDate
      case _: AttrSetManUUID       => it2SetUUID
      case _: AttrSetManURI        => it2SetURI
      case _: AttrSetManByte       => it2SetByte
      case _: AttrSetManShort      => it2SetShort
      case _: AttrSetManChar       => it2SetChar
    })
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    aritiesAttr()
    pullCasts += (a match {
      case _: AttrSetOptString     => it2OptSetString
      case _: AttrSetOptInt        => it2OptSetInt
      case _: AttrSetOptLong       => it2OptSetLong
      case _: AttrSetOptFloat      => it2OptSetFloat
      case _: AttrSetOptDouble     => it2OptSetDouble
      case _: AttrSetOptBoolean    => it2OptSetBoolean
      case _: AttrSetOptBigInt     => it2OptSetBigInt
      case _: AttrSetOptBigDecimal => it2OptSetBigDecimal
      case _: AttrSetOptDate       => it2OptSetDate
      case _: AttrSetOptUUID       => it2OptSetUUID
      case _: AttrSetOptURI        => it2OptSetURI
      case _: AttrSetOptByte       => it2OptSetByte
      case _: AttrSetOptShort      => it2OptSetShort
      case _: AttrSetOptChar       => it2OptSetChar
    })
  }
}
