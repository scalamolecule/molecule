package molecule.core.validation

import molecule.base.ast.SchemaAST.MetaNs
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


case class PreValidation(
  nsMap: Map[String, MetaNs],
  getSetValues: Option[Attr => Set[Any]] = None
) {
  private lazy val isUpdate = getSetValues.isDefined

  private def dup(element: String) = throw ModelError(s"Can't transact duplicate attribute `$element`.")


  @tailrec
  final def check(
    elements: List[Element],
    prev: Array[Array[Array[String]]] = Array(Array(Array.empty[String])),
    level: Int = 0,
    group: Int = 0,
    refPath: Seq[String] = Seq(""),
    prevNs: String = "",
    isTx: Boolean = false,
    mandatoryAttrs: Set[String] = Set.empty[String],
    deletingAttrs: Set[String] = Set.empty[String],
    validationErrors: Map[String, Seq[String]] = Map.empty[String, Seq[String]]
  ): Map[String, Seq[String]] = {
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr    = a.ns + "." + a.attr
          val generic = a.ns == "_Generic"

          val (newNs, mandatoryAttrs1) = if (prevNs == a.ns || generic) {
            (prevNs, mandatoryAttrs)
          } else {
            (a.ns, mandatoryAttrs ++ nsMap(a.ns).mandatory.map(attr => a.ns + "." + attr))
          }

          val (mandatoryAttrs2, deletingAttrs1) = if (generic || !mandatoryAttrs1.contains(attr)) {
            (mandatoryAttrs1, deletingAttrs)
          } else {
            if (isUpdate) {
              if (
                (a.op == V || a.op == Appl) && deletingAttr(a)
                  || a.op == Remove && removingLastValue(a, getSetValues.get(a))
              ) {
                // Wrongfully trying to delete mandatory attr - add to watchlist
                (mandatoryAttrs1, deletingAttrs + attr)
              } else {
                (mandatoryAttrs1, deletingAttrs)
              }

            } else if (
              (a.isInstanceOf[Mandatory] || isTx)
                && !(a.isInstanceOf[AttrSet] && a.op == Appl && deletingAttr(a))
            ) {
              // Mandatory attribute is ok - remove from watchlist
              (mandatoryAttrs1 - attr, deletingAttrs)

            } else {
              (mandatoryAttrs1, deletingAttrs)
            }
          }

          // Distinguish multiple ref paths to the same namespace
          val attrPrefixed = if (isUpdate) {
            val mode = a match {
              case _: AttrOneMan => "man"
              case _: AttrOneOpt => "opt"
              case _: AttrOneTac => "tac"
              case _: AttrSetMan => "man"
              case _: AttrSetOpt => "opt"
              case _: AttrSetTac => "tac"
            }
            refPath.mkString("-") + "-" + attr + "-" + mode
          } else {
            refPath.mkString("-") + "-" + attr
          }
          if (prev(level)(group).contains(attrPrefixed))
            dup(attr)
          prev(level)(group) = prev(level)(group) :+ attrPrefixed

          val validationErrors1 = if (a.errors.isEmpty) validationErrors else {
            validationErrors.+(attr -> a.errors)
          }

          check(tail, prev, level, group, refPath,
            newNs, isTx, mandatoryAttrs2, deletingAttrs1, validationErrors1)

        case r: Ref =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref)) {
            dup(ref)
          }
          if (refPath.contains(ref)) {
            dup(ref)
          }
          prev(level) = prev(level) :+ Array(ref)
          check(tail, prev, level, group + 1, refPath :+ ref,
            prevNs, isTx, mandatoryAttrs, deletingAttrs, validationErrors)

        case backRef: BackRef =>
          if (group == 0)
            throw ModelError(s"Can't use backref namespace `_${backRef.backRef}` from here.")
          check(tail, prev, level, group - 1, refPath.init,
            prevNs, isTx, mandatoryAttrs, deletingAttrs, validationErrors)

        case Composite(es) =>
          check(es ++ tail, prev, level, group, Seq(""),
            prevNs, isTx, mandatoryAttrs, deletingAttrs, validationErrors)

        case Nested(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          check(es ++ tail, prev1, level + 1, 0, refPath,
            prevNs, isTx, mandatoryAttrs, deletingAttrs, validationErrors)

        case NestedOpt(r, es) =>
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          val prev1 = prev :+ Array(Array(ref))
          check(es ++ tail, prev1, level + 1, 0, refPath,
            prevNs, isTx, mandatoryAttrs, deletingAttrs, validationErrors)

        case TxMetaData(txElements) =>
          val prev1 = prev :+ Array(Array.empty[String])
          check(txElements, prev1, level + 1, 0, Nil,
            prevNs, true, mandatoryAttrs, deletingAttrs, validationErrors)
      }
      case Nil          =>
        if (!isUpdate && mandatoryAttrs.nonEmpty) {
          throw ModelError(
            s"""Missing/empty mandatory attributes:
               |  ${mandatoryAttrs.mkString("\n  ")}
               |""".stripMargin
          )
        }
        //        if (isUpdate && mandatoryAttrs.nonEmpty) {
        if (isUpdate && deletingAttrs.nonEmpty) {
          throw ModelError(
            s"""Can't delete mandatory attributes (or remove last values of card-many attributes):
               |  ${deletingAttrs.mkString("\n  ")}
               |""".stripMargin
          )
        }
        validationErrors
    }
  }

  private def deletingAttr(a: Attr): Boolean = {
    a match {
      case a: AttrOneMan => a match {
        case AttrOneManString(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneManInt(_, _, _, Nil, _, _, _, _)        => true
        case AttrOneManLong(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneManFloat(_, _, _, Nil, _, _, _, _)      => true
        case AttrOneManDouble(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneManBoolean(_, _, _, Nil, _, _, _, _)    => true
        case AttrOneManBigInt(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneManBigDecimal(_, _, _, Nil, _, _, _, _) => true
        case AttrOneManDate(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneManUUID(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneManURI(_, _, _, Nil, _, _, _, _)        => true
        case AttrOneManByte(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneManShort(_, _, _, Nil, _, _, _, _)      => true
        case AttrOneManChar(_, _, _, Nil, _, _, _, _)       => true
        case _                                              => false
      }
      // Tacit tx meta attrs can update
      case a: AttrOneTac => a match {
        case AttrOneTacString(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneTacInt(_, _, _, Nil, _, _, _, _)        => true
        case AttrOneTacLong(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneTacFloat(_, _, _, Nil, _, _, _, _)      => true
        case AttrOneTacDouble(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneTacBoolean(_, _, _, Nil, _, _, _, _)    => true
        case AttrOneTacBigInt(_, _, _, Nil, _, _, _, _)     => true
        case AttrOneTacBigDecimal(_, _, _, Nil, _, _, _, _) => true
        case AttrOneTacDate(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneTacUUID(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneTacURI(_, _, _, Nil, _, _, _, _)        => true
        case AttrOneTacByte(_, _, _, Nil, _, _, _, _)       => true
        case AttrOneTacShort(_, _, _, Nil, _, _, _, _)      => true
        case AttrOneTacChar(_, _, _, Nil, _, _, _, _)       => true
        case _                                              => false
      }
      case a: AttrSetMan => a match {
        case AttrSetManString(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManInt(_, _, _, vs, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetManLong(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManFloat(_, _, _, vs, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetManDouble(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _)    => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _) => vs.isEmpty || vs.head.isEmpty
        case AttrSetManDate(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManUUID(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManURI(_, _, _, vs, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetManByte(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManShort(_, _, _, vs, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetManChar(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case _                                             => false
      }
      case a: AttrSetTac => a match {
        case AttrSetTacString(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacInt(_, _, _, vs, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacLong(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _)    => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _) => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacDate(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacURI(_, _, _, vs, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacByte(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacShort(_, _, _, vs, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacChar(_, _, _, vs, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case _                                             => false
      }
      case _             => false
    }
  }

  private def removingLastValue(a: Attr, curVs: Set[Any]): Boolean = {
    a match {
      case a: AttrSetMan => a match {
        case AttrSetManString(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManInt(_, _, _, vs, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetManLong(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManFloat(_, _, _, vs, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetManDouble(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _)    => vs.nonEmpty && vs.head == curVs
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _) => vs.nonEmpty && vs.head == curVs
        case AttrSetManDate(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManUUID(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManURI(_, _, _, vs, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetManByte(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManShort(_, _, _, vs, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetManChar(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case _                                             => false
      }
      case a: AttrSetTac => a match {
        case AttrSetTacString(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacInt(_, _, _, vs, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetTacLong(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _)    => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _) => vs.nonEmpty && vs.head == curVs
        case AttrSetTacDate(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacURI(_, _, _, vs, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetTacByte(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacShort(_, _, _, vs, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetTacChar(_, _, _, vs, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case _                                             => false
      }
      case _             => false
    }
  }
}
