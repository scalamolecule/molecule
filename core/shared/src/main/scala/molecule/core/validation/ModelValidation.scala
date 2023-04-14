package molecule.core.validation

import molecule.base.ast.SchemaAST.{Cardinality, MetaNs}
import molecule.base.error._
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ops.ModelTransformations_
import scala.annotation.tailrec


case class ModelValidation(
  nsMap: Map[String, MetaNs],
  attrMap: Map[String, (Cardinality, String, Seq[String])],
  action: String,
  getCurSetValues: Option[Attr => Set[Any]] = None
) extends ModelTransformations_ {

  private def dup(element: String) = {
    throw ModelError(s"Can't transact duplicate attribute `$element`.")
  }

  private val isInsert        : Boolean                     = action == "insert" || action == "insertTx"
  private val isUpdate        : Boolean                     = action == "update"
  private var prev            : Array[Array[Array[String]]] = Array(Array(Array.empty[String]))
  private var level           : Int                         = 0
  private var group           : Int                         = 0
  private var refPath         : Seq[String]                 = Seq.empty[String]
  private var prevNs          : String                      = ""
  private var isTx            : Boolean                     = action == "insertTx"
  private var mandatoryAttrs  : Set[String]                 = Set.empty[String]
  private var mandatoryRefs   : Set[(String, String)]       = Set.empty[(String, String)]
  private var requiredAttrs   : Set[String]                 = Set.empty[String]
  private var presentAttrs    : Set[String]                 = Set.empty[String]
  private var deletingAttrs   : Set[String]                 = Set.empty[String]
  private var validationErrors: Map[String, Seq[String]]    = Map.empty[String, Seq[String]]
  private var curElements     : List[Element]               = List.empty[Element]


  @tailrec
  final def validate(elements: List[Element]): Map[String, Seq[String]] = {
    if (prevNs.isEmpty)
      curElements = elements
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr = a.ns + "." + a.attr
          if (a.ns != "_Generic") {
            register(a, attr)
          }
          if (isTx && isInsert && !(a.isInstanceOf[AttrOneTac] || a.isInstanceOf[AttrSetTac])) {
            throw ModelError(
              s"For inserts, tx meta data must be applied to tacit attributes, " +
                s"like ${attr}_(<metadata>)")
          }
          checkPath(a, attr)
          val valueAttrErrors = if (a.valueAttrs.isEmpty) Nil else valueValidate(a)
          val allErrors       = valueAttrErrors ++ a.errors
          if (allErrors.nonEmpty) {
            validationErrors += attr -> allErrors
          }
          validate(tail)

        case r: Ref =>
          val refAttr = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(refAttr))
            dup(refAttr)
          if (refPath.contains(refAttr))
            dup(refAttr)
          prev(level) = prev(level) :+ Array(refAttr)
          group += 1
          mandatoryRefs = mandatoryRefs.filterNot(_._1 == refAttr)
          presentAttrs += r.refAttr
          refPath = refPath :+ refAttr
          validate(tail)

        case backRef: BackRef =>
          if (group == 0) {
            throw ModelError(s"Can't use backref namespace `_${backRef.backRef}` from here.")
          }
          group -= 1
          refPath = refPath.init
          validate(tail)

        case Composite(es) =>
          curElements = es
          refPath = Seq.empty[String]
          validate(es ++ tail)

        case Nested(r, es) =>
          curElements = es
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          prev = prev :+ Array(Array(ref))
          level += 1
          group = 0
          validate(es ++ tail)

        case NestedOpt(r, es) =>
          curElements = es
          val ref = r.ns + "." + r.refAttr
          if (prev(level)(group).contains(ref))
            dup(ref)
          prev = prev :+ Array(Array(ref))
          level += 1
          group = 0
          validate(es ++ tail)

        case TxMetaData(txElements) =>
          curElements = txElements
          prev = prev :+ Array(Array.empty[String])
          level += 1
          group = 0
          refPath = Seq.empty[String]
          isTx = true
          validate(txElements)
      }
      case Nil          =>
        checkMandatoryAndRequiredAttrs()
        validationErrors
    }
  }

  private def register(a: Attr, attr: String) = {
    if (prevNs != a.ns) {
      prevNs = a.ns
      mandatoryAttrs ++= nsMap(a.ns).mandatoryAttrs.map(attr => a.ns + "." + attr)
      mandatoryRefs ++= nsMap(a.ns).mandatoryRefs.map { case (attr, refNs) => (a.ns + "." + attr) -> refNs }
    }
    requiredAttrs ++= attrMap(attr)._3
    presentAttrs += a.attr

    if (mandatoryAttrs.contains(attr))
      registerMandatoryAttr(a, attr)

    if (mandatoryRefs.map(_._1).contains(attr))
      registerMandatoryRefAttr(a, attr)
  }

  private def checkPath(a: Attr, attr: String) = {
    // Distinguish multiple ref paths to the same namespace
    val attrPrefixed = if (isUpdate) {
      val mode = a match {
        case _: AttrOneMan => "man"
        case _: AttrOneOpt => "opt"
        case _: AttrOneTac => "tac"
        case _: AttrSetMan => "man"
        case _: AttrSetOpt => "opt"
        case _: AttrSetTac => "tac"
        case _             => throw new Exception("Attribute family not implemented for " + a)
      }
      refPath.mkString("-") + "-" + attr + "-" + mode
    } else {
      refPath.mkString("-") + "-" + attr
    }
    if (prev(level)(group).contains(attrPrefixed))
      dup(attr)
    prev(level)(group) = prev(level)(group) :+ attrPrefixed
  }

  private def onlyMandatory(a: Attr) = {
    val mode = if (a.isInstanceOf[Tacit]) "tacit" else "optional"
    throw ModelError(s"Required attributes have to be mandatory. Found $mode attribute ${a.ns}.${a.attr}")
  }

  private def valueValidate(a: Attr): Seq[String] = {
    val attrs0 = a.valueAttrs.flatMap { attr =>
      curElements.collectFirst {
        case a1: Attr if a1.attr == attr =>
          a1 match {
            case _: AttrOneMan | _: AttrSetMan =>
              requiredAttrs -= attr
              attr -> a1

            case a2 => onlyMandatory(a2)
          }
      }
    }
    val attrs  = attrs0.sortBy(_._1).map(_._2)
    if (a.valueAttrs.length != attrs.length) {
      val needs    = a.valueAttrs
      val found    = attrs0.map(_._1)
      val foundStr = if (found.isEmpty) "" else " " + found.mkString(", ")
      val missing  = needs.diff(found)
      throw ModelError(
        s"""Attribute `${a.ns}.${a.attr}` is missing some attributes needed for its validations:
           |  Needs  : ${needs.mkString(", ")}
           |  Found  :$foundStr
           |  Missing: ${missing.mkString(", ")}
           |""".stripMargin
      )
    }

    def err = throw new Exception("Unexpected value validation attribute: " + a)
    def one[T](vs: Seq[T]): T = if (vs.length == 1) vs.head else {
      throw ExecutionError(
        s"Please use `insert` to store multiple values for attribute ${a.ns}.${a.attr}"
      )
    }

    if (isInsert) {
      Nil
    } else {
      a match {
        case a1: AttrOneMan                => a1 match {
          case AttrOneManString(_, _, _, vs, Some(validator), _, _, _, _)     => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManInt(_, _, _, vs, Some(validator), _, _, _, _)        => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManLong(_, _, _, vs, Some(validator), _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManFloat(_, _, _, vs, Some(validator), _, _, _, _)      => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManDouble(_, _, _, vs, Some(validator), _, _, _, _)     => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBoolean(_, _, _, vs, Some(validator), _, _, _, _)    => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBigInt(_, _, _, vs, Some(validator), _, _, _, _)     => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBigDecimal(_, _, _, vs, Some(validator), _, _, _, _) => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManDate(_, _, _, vs, Some(validator), _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManUUID(_, _, _, vs, Some(validator), _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManURI(_, _, _, vs, Some(validator), _, _, _, _)        => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManByte(_, _, _, vs, Some(validator), _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManShort(_, _, _, vs, Some(validator), _, _, _, _)      => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManChar(_, _, _, vs, Some(validator), _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case _                                                              => err
        }
        case _: AttrOneTac | _: AttrOneOpt => onlyMandatory(a)

        case a: AttrSetMan                 => a match {
          case AttrSetManString(_, _, _, sets, Some(validator), _, _, _, _)     => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManInt(_, _, _, sets, Some(validator), _, _, _, _)        => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManLong(_, _, _, sets, Some(validator), _, _, _, _)       => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManFloat(_, _, _, sets, Some(validator), _, _, _, _)      => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManDouble(_, _, _, sets, Some(validator), _, _, _, _)     => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBoolean(_, _, _, sets, Some(validator), _, _, _, _)    => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBigInt(_, _, _, sets, Some(validator), _, _, _, _)     => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBigDecimal(_, _, _, sets, Some(validator), _, _, _, _) => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManDate(_, _, _, sets, Some(validator), _, _, _, _)       => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManUUID(_, _, _, sets, Some(validator), _, _, _, _)       => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManURI(_, _, _, sets, Some(validator), _, _, _, _)        => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManByte(_, _, _, sets, Some(validator), _, _, _, _)       => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManShort(_, _, _, sets, Some(validator), _, _, _, _)      => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case AttrSetManChar(_, _, _, sets, Some(validator), _, _, _, _)       => val vr = validator.withAttrs(attrs); one(sets).toSeq.flatMap(v => vr.validate(v))
          case _                                                                => err
        }
        case _: AttrSetTac | _: AttrSetOpt => onlyMandatory(a)
        case _                             => err
      }
    }
  }

  private def checkMandatoryAndRequiredAttrs(): Unit = {
    if (!isUpdate && mandatoryAttrs.nonEmpty) {
      throw ModelError(
        s"""Missing/empty mandatory attributes:
           |  ${mandatoryAttrs.mkString("\n  ")}
           |""".stripMargin
      )
    }
    if (!isUpdate && mandatoryRefs.nonEmpty) {
      val list = mandatoryRefs.map {
        case (a, refNs) => s"$a pointing to namespace $refNs"
      }.mkString("\n  ")
      throw ModelError(
        s"""Missing/empty mandatory references:
           |  $list
           |""".stripMargin
      )
    }
    if (isUpdate && deletingAttrs.nonEmpty) {
      throw ModelError(
        s"""Can't delete mandatory attributes (or remove last values of card-many attributes):
           |  ${deletingAttrs.mkString("\n  ")}
           |""".stripMargin
      )
    }
    if (requiredAttrs.nonEmpty) {
      val requiredAttrs1       = requiredAttrs.toList.distinct
      val presentAttrs1        = presentAttrs.toList.distinct
      val missingRequiredAttrs = requiredAttrs1.diff(presentAttrs1)
      if (missingRequiredAttrs.nonEmpty) {
        throw ModelError(
          s"""Missing/empty required attributes:
             |  Required: ${requiredAttrs1.mkString(", ")}
             |  Present : ${presentAttrs.mkString(", ")}
             |  Missing : ${missingRequiredAttrs.mkString(", ")}
             |""".stripMargin
        )
      }
    }
  }

  private def registerMandatoryAttr(a: Attr, attr: String) = {
    if (isUpdate) {
      if (
        (a.op == V || a.op == Eq) && deletingAttr(a)
          || a.op == Remove && getCurSetValues.nonEmpty && removingLastValue(a, getCurSetValues.get(a))
      //          || a.op == Remove && removingLastValue(a, getCurSetValues.get(a))
      ) {
        // Wrongfully trying to delete mandatory attr - add to watchlist
        deletingAttrs += attr
      }
    } else if (
      (a.isInstanceOf[Mandatory] || isTx)
        && !(a.isInstanceOf[AttrSet] && a.op == Eq && deletingAttr(a))
    ) {
      // Mandatory attribute is ok - remove from watchlist
      mandatoryAttrs -= attr
    }
  }

  private def registerMandatoryRefAttr(a: Attr, attr: String) = {
    if (isUpdate) {
      if (
        (a.op == V || a.op == Eq) && deletingAttr(a)
          || a.op == Remove && getCurSetValues.nonEmpty && removingLastValue(a, getCurSetValues.get(a))
      ) {
        // Wrongfully trying to delete mandatory attr - add to watchlist
        deletingAttrs += attr
      }
    } else if (
      (a.isInstanceOf[Mandatory] || isTx)
        && !(a.isInstanceOf[AttrSet] && a.op == Eq && deletingAttr(a))
    ) {
      // Mandatory attribute is ok - remove from watchlist
      mandatoryRefs = mandatoryRefs.filterNot(_._1 == attr)
    }
  }


  private def deletingAttr(a: Attr): Boolean = {
    a match {
      case a: AttrOneMan => a match {
        case AttrOneManString(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneManInt(_, _, _, Nil, _, _, _, _, _)        => true
        case AttrOneManLong(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneManFloat(_, _, _, Nil, _, _, _, _, _)      => true
        case AttrOneManDouble(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneManBoolean(_, _, _, Nil, _, _, _, _, _)    => true
        case AttrOneManBigInt(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneManBigDecimal(_, _, _, Nil, _, _, _, _, _) => true
        case AttrOneManDate(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneManUUID(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneManURI(_, _, _, Nil, _, _, _, _, _)        => true
        case AttrOneManByte(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneManShort(_, _, _, Nil, _, _, _, _, _)      => true
        case AttrOneManChar(_, _, _, Nil, _, _, _, _, _)       => true
        case _                                                 => false
      }
      // Tacit tx meta attrs can update
      case a: AttrOneTac => a match {
        case AttrOneTacString(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneTacInt(_, _, _, Nil, _, _, _, _, _)        => true
        case AttrOneTacLong(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneTacFloat(_, _, _, Nil, _, _, _, _, _)      => true
        case AttrOneTacDouble(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneTacBoolean(_, _, _, Nil, _, _, _, _, _)    => true
        case AttrOneTacBigInt(_, _, _, Nil, _, _, _, _, _)     => true
        case AttrOneTacBigDecimal(_, _, _, Nil, _, _, _, _, _) => true
        case AttrOneTacDate(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneTacUUID(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneTacURI(_, _, _, Nil, _, _, _, _, _)        => true
        case AttrOneTacByte(_, _, _, Nil, _, _, _, _, _)       => true
        case AttrOneTacShort(_, _, _, Nil, _, _, _, _, _)      => true
        case AttrOneTacChar(_, _, _, Nil, _, _, _, _, _)       => true
        case _                                                 => false
      }
      case a: AttrSetMan => a match {
        case AttrSetManString(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManInt(_, _, _, vs, _, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetManLong(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManFloat(_, _, _, vs, _, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetManDouble(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _, _)    => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _, _) => vs.isEmpty || vs.head.isEmpty
        case AttrSetManDate(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManUUID(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManURI(_, _, _, vs, _, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetManByte(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetManShort(_, _, _, vs, _, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetManChar(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case _                                                => false
      }
      case a: AttrSetTac => a match {
        case AttrSetTacString(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacInt(_, _, _, vs, _, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacLong(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _, _)    => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _, _)     => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _, _) => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacDate(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacURI(_, _, _, vs, _, _, _, _, _)        => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacByte(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacShort(_, _, _, vs, _, _, _, _, _)      => vs.isEmpty || vs.head.isEmpty
        case AttrSetTacChar(_, _, _, vs, _, _, _, _, _)       => vs.isEmpty || vs.head.isEmpty
        case _                                                => false
      }
      case _             => false
    }
  }

  private def removingLastValue(a: Attr, curVs: Set[Any]): Boolean = {
    a match {
      case a: AttrSetMan => a match {
        case AttrSetManString(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManInt(_, _, _, vs, _, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetManLong(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManFloat(_, _, _, vs, _, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetManDouble(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _, _)    => vs.nonEmpty && vs.head == curVs
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _, _) => vs.nonEmpty && vs.head == curVs
        case AttrSetManDate(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManUUID(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManURI(_, _, _, vs, _, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetManByte(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetManShort(_, _, _, vs, _, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetManChar(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case _                                                => false
      }
      case a: AttrSetTac => a match {
        case AttrSetTacString(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacInt(_, _, _, vs, _, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetTacLong(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _, _)    => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _, _)     => vs.nonEmpty && vs.head == curVs
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _, _) => vs.nonEmpty && vs.head == curVs
        case AttrSetTacDate(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacURI(_, _, _, vs, _, _, _, _, _)        => vs.nonEmpty && vs.head == curVs
        case AttrSetTacByte(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case AttrSetTacShort(_, _, _, vs, _, _, _, _, _)      => vs.nonEmpty && vs.head == curVs
        case AttrSetTacChar(_, _, _, vs, _, _, _, _, _)       => vs.nonEmpty && vs.head == curVs
        case _                                                => false
      }
      case _             => false
    }
  }
}
