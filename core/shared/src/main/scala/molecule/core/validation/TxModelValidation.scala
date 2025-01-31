package molecule.core.validation

import molecule.base.ast._
import molecule.base.error._
import molecule.core.ast.DataModel._
import molecule.core.ops.ModelTransformations_
import scala.annotation.tailrec


case class TxModelValidation(
  entityMap: Map[String, MetaEntity],
  attrMap: Map[String, (Card, String, Seq[String])],
  action: String,
  getCurSetValues: Option[Attr => Set[Any]] = None
) extends ModelTransformations_ {

  private def dup(element: String) = {
    throw ModelError(s"Can't transact duplicate attribute $element")
  }

  private val isInsert        : Boolean                     = action == "insert"
  private val isUpdate        : Boolean                     = action == "update"
  private var prev            : Array[Array[Array[String]]] = Array(Array(Array.empty[String]))
  private var level           : Int                         = 0
  private var group           : Int                         = 0
  private var refPath         : Seq[String]                 = Seq.empty[String]
  private var prevEnt         : String                      = ""
  private var mandatoryAttrs  : Set[String]                 = Set.empty[String]
  private var mandatoryRefs   : Set[(String, String)]       = Set.empty[(String, String)]
  private var requiredAttrs   : Set[String]                 = Set.empty[String]
  private var presentAttrs    : Set[String]                 = Set.empty[String]
  private var deletingAttrs   : Set[String]                 = Set.empty[String]
  private var validationErrors: Map[String, Seq[String]]    = Map.empty[String, Seq[String]]
  private var curElements     : List[Element]               = List.empty[Element]


  @tailrec
  final def validate(elements: List[Element]): Map[String, Seq[String]] = {
    if (prevEnt.isEmpty)
      curElements = elements
    elements match {
      case head :: tail => head match {
        case a: Attr =>
          val attr = a.name
          if (a.attr != "id") {
            register(a, attr)
          }
          checkPath(a, attr)
          val valueAttrErrors = if (a.valueAttrs.isEmpty) Nil else valueValidate(a)
          val allErrors       = valueAttrErrors ++ a.errors
          if (allErrors.nonEmpty) {
            validationErrors += attr -> allErrors
          }
          validate(tail)

        case r: Ref =>
          val refAttr = r.ent + "." + r.refAttr
          if (prev(level)(group).contains(refAttr))
            dup(refAttr)
          if (refPath.contains(refAttr))
            dup(refAttr)
          noEmpty(r.ent, r.refAttr)
          prev(level) = prev(level) :+ Array(refAttr)
          group += 1
          mandatoryRefs = mandatoryRefs.filterNot(_._1 == refAttr)
          presentAttrs += r.refAttr
          refPath = refPath :+ refAttr
          validate(tail)

        case BackRef(prevEnt1, curEnt, _) =>
          if (group == 0) {
            throw ModelError(s"Can't use backref entity _$prevEnt1 from here")
          }
          if (prevEnt == prevEnt1) {
            throw ModelError(
              s"Please add attributes to entity $curEnt before going back to entity $prevEnt1"
            )
          }
          group -= 1
          refPath = refPath.init
          validate(tail)

        case OptRef(r, es) =>
          val refAttr = r.ent + "." + r.refAttr
          if (prev(level)(group).contains(refAttr))
            dup(refAttr)
          if (refPath.contains(refAttr))
            dup(refAttr)
          prev(level) = prev(level) :+ Array(refAttr)
          group += 1
          mandatoryRefs = mandatoryRefs.filterNot(_._1 == refAttr)
          presentAttrs += r.refAttr
          refPath = refPath :+ refAttr
          validate(es ++ tail)

        case Nested(r, es) =>
          curElements = es
          val ref = r.name
          if (prev(level)(group).contains(ref))
            dup(ref)
          noEmpty(r.ent, r.refAttr)
          prev = prev :+ Array(Array(ref))
          level += 1
          group = 0
          validate(es ++ tail)

        case OptNested(r, es) =>
          curElements = es
          val ref = r.name
          if (prev(level)(group).contains(ref))
            dup(ref)
          prev = prev :+ Array(Array(ref))
          level += 1
          group = 0
          validate(es ++ tail)
      }
      case Nil          =>
        checkMandatoryAndRequiredAttrs()
        validationErrors
    }
  }

  private def register(a: Attr, attr: String) = {
    if (prevEnt != a.ent) {
      prevEnt = a.ent
      mandatoryAttrs ++= entityMap(a.ent).mandatoryAttrs.map(attr => a.ent + "." + attr)
      mandatoryRefs ++= entityMap(a.ent).mandatoryRefs.map { case (attr, ref) => (a.ent + "." + attr) -> ref }
    }
    requiredAttrs ++= attrMap(attr)._3
    presentAttrs += a.attr

    if (mandatoryAttrs.contains(attr)) {
      registerMandatoryAttr(a, attr)
    }
    if (mandatoryRefs.map(_._1).contains(attr)) {
      registerMandatoryRefAttr(a, attr)
    }
  }

  private def checkPath(a: Attr, attr: String) = {
    // Distinguish multiple ref paths to the same entity
    val attrPrefixed = if (isUpdate) {
      val mode = a match {
        case _: AttrOneMan => "man"
        case _: AttrOneOpt => "opt"
        case _: AttrOneTac => "tac"
        case _: AttrSetMan => "man"
        case _: AttrSetOpt => "opt"
        case _: AttrSetTac => "tac"
        case _: AttrSeqMan => "man"
        case _: AttrSeqOpt => "opt"
        case _: AttrSeqTac => "tac"
        case _: AttrMapMan => "man"
        case _: AttrMapOpt => "opt"
        case _: AttrMapTac => "tac"
      }
      refPath.mkString("-") + "-" + attr + "-" + mode
    } else {
      refPath.mkString("-") + "-" + attr
    }

    //    val pp = prev.toList.map(a => a.toList.map(a => a.toList))
    //    println(s"------------------ $level  $group  $attrPrefixed")
    //    println(pp.map(l => l.mkString("\n  ", "\n  ", "")).mkString("\n", "\n", ""))

    if (prev(level)(group).contains(attrPrefixed))
      dup(attr)

    // Allow duplicate tacit update filter attributes (for ranges etc.)
    if (!a.isInstanceOf[Tacit])
      prev(level)(group) = prev(level)(group) :+ attrPrefixed
  }

  private def onlyMandatory(a: Attr) = {
    val mode = if (a.isInstanceOf[Tacit]) "tacit" else "optional"
    throw ModelError(s"Required attributes have to be mandatory. Found $mode attribute ${a.ent}.${a.attr}")
  }
  private def noEmpty(ent: String, refAttr: String): Unit = {
    if (presentAttrs.isEmpty && !isUpdate) {
      throw ModelError(
        s"Please add at least 1 attribute to entity $ent " +
          s"before relating to " + refAttr.capitalize
      )
    }
  }

  private def valueValidate(a: Attr): Seq[String] = {
    val attrs0 = a.valueAttrs.flatMap { attr =>
      curElements.collectFirst {
        case a1: Attr if a1.attr == attr =>
          a1 match {
            case _: AttrOneMan | _: AttrSetMan | _: AttrSeqMan | _: AttrMapMan =>
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
        s"""Attribute `${a.ent}.${a.attr}` is missing some attributes needed for its validations:
           |  Needs  : ${needs.mkString(", ")}
           |  Found  :$foundStr
           |  Missing: ${missing.mkString(", ")}
           |""".stripMargin
      )
    }

    def err = throw new Exception("Unexpected value validation attribute: " + a)
    def one[T](vs: Seq[T]): T = if (vs.length == 1) vs.head else {
      throw ExecutionError(
        s"Please use `insert` to store multiple values for attribute ${a.ent}.${a.attr}"
      )
    }

    if (isInsert) {
      Nil
    } else {
      a match {
        case a1: AttrOneMan                => a1 match {
          case AttrOneManID(_, _, _, vs, _, Some(validator), _, _, _, _, _)             => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManString(_, _, _, vs, _, Some(validator), _, _, _, _, _)         => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManInt(_, _, _, vs, _, Some(validator), _, _, _, _, _)            => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManLong(_, _, _, vs, _, Some(validator), _, _, _, _, _)           => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManFloat(_, _, _, vs, _, Some(validator), _, _, _, _, _)          => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManDouble(_, _, _, vs, _, Some(validator), _, _, _, _, _)         => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBoolean(_, _, _, vs, _, Some(validator), _, _, _, _, _)        => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBigInt(_, _, _, vs, _, Some(validator), _, _, _, _, _)         => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManBigDecimal(_, _, _, vs, _, Some(validator), _, _, _, _, _)     => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManDate(_, _, _, vs, _, Some(validator), _, _, _, _, _)           => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManDuration(_, _, _, vs, _, Some(validator), _, _, _, _, _)       => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManInstant(_, _, _, vs, _, Some(validator), _, _, _, _, _)        => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManLocalDate(_, _, _, vs, _, Some(validator), _, _, _, _, _)      => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManLocalTime(_, _, _, vs, _, Some(validator), _, _, _, _, _)      => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManLocalDateTime(_, _, _, vs, _, Some(validator), _, _, _, _, _)  => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManOffsetTime(_, _, _, vs, _, Some(validator), _, _, _, _, _)     => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManOffsetDateTime(_, _, _, vs, _, Some(validator), _, _, _, _, _) => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManZonedDateTime(_, _, _, vs, _, Some(validator), _, _, _, _, _)  => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManUUID(_, _, _, vs, _, Some(validator), _, _, _, _, _)           => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManURI(_, _, _, vs, _, Some(validator), _, _, _, _, _)            => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManByte(_, _, _, vs, _, Some(validator), _, _, _, _, _)           => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManShort(_, _, _, vs, _, Some(validator), _, _, _, _, _)          => validator.withAttrs(attrs).validate(one(vs))
          case AttrOneManChar(_, _, _, vs, _, Some(validator), _, _, _, _, _)           => validator.withAttrs(attrs).validate(one(vs))
          case _                                                                        => err
        }
        case _: AttrOneTac | _: AttrOneOpt => onlyMandatory(a)

        case a: AttrSetMan                 => a match {
          case AttrSetManID(_, _, _, set, _, Some(validator), _, _, _, _, _)             => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManString(_, _, _, set, _, Some(validator), _, _, _, _, _)         => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManInt(_, _, _, set, _, Some(validator), _, _, _, _, _)            => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManLong(_, _, _, set, _, Some(validator), _, _, _, _, _)           => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManFloat(_, _, _, set, _, Some(validator), _, _, _, _, _)          => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManDouble(_, _, _, set, _, Some(validator), _, _, _, _, _)         => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBoolean(_, _, _, set, _, Some(validator), _, _, _, _, _)        => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBigInt(_, _, _, set, _, Some(validator), _, _, _, _, _)         => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManBigDecimal(_, _, _, set, _, Some(validator), _, _, _, _, _)     => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManDate(_, _, _, set, _, Some(validator), _, _, _, _, _)           => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManDuration(_, _, _, set, _, Some(validator), _, _, _, _, _)       => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManInstant(_, _, _, set, _, Some(validator), _, _, _, _, _)        => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManLocalDate(_, _, _, set, _, Some(validator), _, _, _, _, _)      => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManLocalTime(_, _, _, set, _, Some(validator), _, _, _, _, _)      => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManLocalDateTime(_, _, _, set, _, Some(validator), _, _, _, _, _)  => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManOffsetTime(_, _, _, set, _, Some(validator), _, _, _, _, _)     => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManOffsetDateTime(_, _, _, set, _, Some(validator), _, _, _, _, _) => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManZonedDateTime(_, _, _, set, _, Some(validator), _, _, _, _, _)  => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManUUID(_, _, _, set, _, Some(validator), _, _, _, _, _)           => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManURI(_, _, _, set, _, Some(validator), _, _, _, _, _)            => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManByte(_, _, _, set, _, Some(validator), _, _, _, _, _)           => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManShort(_, _, _, set, _, Some(validator), _, _, _, _, _)          => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case AttrSetManChar(_, _, _, set, _, Some(validator), _, _, _, _, _)           => val vr = validator.withAttrs(attrs); set.toSeq.flatMap(v => vr.validate(v))
          case _                                                                         => err
        }
        case _: AttrSetTac | _: AttrSetOpt => onlyMandatory(a)

        case a: AttrSeq => ???
        case a: AttrMap => ???
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
        case (a, ref) => s"$a pointing to entity $ref"
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
        (a.op == V || a.op == Eq || a.op == NoValue) && deletingAttr(a)
          || a.op == Remove && getCurSetValues.nonEmpty && removingLastValue(a, getCurSetValues.get(a))
      ) {
        // Wrongfully trying to delete mandatory attr - add to watchlist
        deletingAttrs += attr
      }
    } else if (a.isInstanceOf[Mandatory] && !(a.isInstanceOf[AttrSet] && a.op == Eq && deletingAttr(a))) {
      // Mandatory attribute is ok - remove from watchlist
      mandatoryAttrs -= attr
    }
  }

  private def registerMandatoryRefAttr(a: Attr, attr: String) = {
    if (isUpdate) {
      if (
        (a.op == V || a.op == Eq || a.op == NoValue) && deletingAttr(a)
          || a.op == Remove && getCurSetValues.nonEmpty && removingLastValue(a, getCurSetValues.get(a))
      ) {
        // Wrongfully trying to delete mandatory attr - add to watchlist
        deletingAttrs += attr
      }
    } else if (a.isInstanceOf[Mandatory] && !(a.isInstanceOf[AttrSet] && a.op == Eq && deletingAttr(a))) {
      // Mandatory attribute is ok - remove from watchlist
      mandatoryRefs = mandatoryRefs.filterNot(_._1 == attr)
    }
  }


  private def deletingAttr(a: Attr): Boolean = {
    a match {
      case a: AttrOneMan => a match {
        case AttrOneManID(_, _, _, Nil, _, _, _, _, _, _, _)             => true
        case AttrOneManString(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneManInt(_, _, _, Nil, _, _, _, _, _, _, _)            => true
        case AttrOneManLong(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneManFloat(_, _, _, Nil, _, _, _, _, _, _, _)          => true
        case AttrOneManDouble(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneManBoolean(_, _, _, Nil, _, _, _, _, _, _, _)        => true
        case AttrOneManBigInt(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneManBigDecimal(_, _, _, Nil, _, _, _, _, _, _, _)     => true
        case AttrOneManDate(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneManDuration(_, _, _, Nil, _, _, _, _, _, _, _)       => true
        case AttrOneManInstant(_, _, _, Nil, _, _, _, _, _, _, _)        => true
        case AttrOneManLocalDate(_, _, _, Nil, _, _, _, _, _, _, _)      => true
        case AttrOneManLocalTime(_, _, _, Nil, _, _, _, _, _, _, _)      => true
        case AttrOneManLocalDateTime(_, _, _, Nil, _, _, _, _, _, _, _)  => true
        case AttrOneManOffsetTime(_, _, _, Nil, _, _, _, _, _, _, _)     => true
        case AttrOneManOffsetDateTime(_, _, _, Nil, _, _, _, _, _, _, _) => true
        case AttrOneManZonedDateTime(_, _, _, Nil, _, _, _, _, _, _, _)  => true
        case AttrOneManUUID(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneManURI(_, _, _, Nil, _, _, _, _, _, _, _)            => true
        case AttrOneManByte(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneManShort(_, _, _, Nil, _, _, _, _, _, _, _)          => true
        case AttrOneManChar(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case _                                                           => false
      }
      case a: AttrOneTac => a match {
        case AttrOneTacID(_, _, _, Nil, _, _, _, _, _, _, _)             => true
        case AttrOneTacString(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneTacInt(_, _, _, Nil, _, _, _, _, _, _, _)            => true
        case AttrOneTacLong(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneTacFloat(_, _, _, Nil, _, _, _, _, _, _, _)          => true
        case AttrOneTacDouble(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneTacBoolean(_, _, _, Nil, _, _, _, _, _, _, _)        => true
        case AttrOneTacBigInt(_, _, _, Nil, _, _, _, _, _, _, _)         => true
        case AttrOneTacBigDecimal(_, _, _, Nil, _, _, _, _, _, _, _)     => true
        case AttrOneTacDate(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneTacDuration(_, _, _, Nil, _, _, _, _, _, _, _)       => true
        case AttrOneTacInstant(_, _, _, Nil, _, _, _, _, _, _, _)        => true
        case AttrOneTacLocalDate(_, _, _, Nil, _, _, _, _, _, _, _)      => true
        case AttrOneTacLocalTime(_, _, _, Nil, _, _, _, _, _, _, _)      => true
        case AttrOneTacLocalDateTime(_, _, _, Nil, _, _, _, _, _, _, _)  => true
        case AttrOneTacOffsetTime(_, _, _, Nil, _, _, _, _, _, _, _)     => true
        case AttrOneTacOffsetDateTime(_, _, _, Nil, _, _, _, _, _, _, _) => true
        case AttrOneTacZonedDateTime(_, _, _, Nil, _, _, _, _, _, _, _)  => true
        case AttrOneTacUUID(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneTacURI(_, _, _, Nil, _, _, _, _, _, _, _)            => true
        case AttrOneTacByte(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case AttrOneTacShort(_, _, _, Nil, _, _, _, _, _, _, _)          => true
        case AttrOneTacChar(_, _, _, Nil, _, _, _, _, _, _, _)           => true
        case _                                                           => false
      }
      case a: AttrSetMan => a match {
        case AttrSetManID(_, _, _, vs, _, _, _, _, _, _, _)             => vs.isEmpty
        case AttrSetManString(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetManInt(_, _, _, vs, _, _, _, _, _, _, _)            => vs.isEmpty
        case AttrSetManLong(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetManFloat(_, _, _, vs, _, _, _, _, _, _, _)          => vs.isEmpty
        case AttrSetManDouble(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _, _, _, _)        => vs.isEmpty
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _, _, _, _)     => vs.isEmpty
        case AttrSetManDate(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetManDuration(_, _, _, vs, _, _, _, _, _, _, _)       => vs.isEmpty
        case AttrSetManInstant(_, _, _, vs, _, _, _, _, _, _, _)        => vs.isEmpty
        case AttrSetManLocalDate(_, _, _, vs, _, _, _, _, _, _, _)      => vs.isEmpty
        case AttrSetManLocalTime(_, _, _, vs, _, _, _, _, _, _, _)      => vs.isEmpty
        case AttrSetManLocalDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs.isEmpty
        case AttrSetManOffsetTime(_, _, _, vs, _, _, _, _, _, _, _)     => vs.isEmpty
        case AttrSetManOffsetDateTime(_, _, _, vs, _, _, _, _, _, _, _) => vs.isEmpty
        case AttrSetManZonedDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs.isEmpty
        case AttrSetManUUID(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetManURI(_, _, _, vs, _, _, _, _, _, _, _)            => vs.isEmpty
        case AttrSetManByte(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetManShort(_, _, _, vs, _, _, _, _, _, _, _)          => vs.isEmpty
        case AttrSetManChar(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
      }
      case a: AttrSetTac => a match {
        case AttrSetTacID(_, _, _, vs, _, _, _, _, _, _, _)             => vs.isEmpty
        case AttrSetTacString(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetTacInt(_, _, _, vs, _, _, _, _, _, _, _)            => vs.isEmpty
        case AttrSetTacLong(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _, _, _, _)          => vs.isEmpty
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _, _, _, _)        => vs.isEmpty
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _, _, _, _)         => vs.isEmpty
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _, _, _, _)     => vs.isEmpty
        case AttrSetTacDate(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetTacDuration(_, _, _, vs, _, _, _, _, _, _, _)       => vs.isEmpty
        case AttrSetTacInstant(_, _, _, vs, _, _, _, _, _, _, _)        => vs.isEmpty
        case AttrSetTacLocalDate(_, _, _, vs, _, _, _, _, _, _, _)      => vs.isEmpty
        case AttrSetTacLocalTime(_, _, _, vs, _, _, _, _, _, _, _)      => vs.isEmpty
        case AttrSetTacLocalDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs.isEmpty
        case AttrSetTacOffsetTime(_, _, _, vs, _, _, _, _, _, _, _)     => vs.isEmpty
        case AttrSetTacOffsetDateTime(_, _, _, vs, _, _, _, _, _, _, _) => vs.isEmpty
        case AttrSetTacZonedDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs.isEmpty
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetTacURI(_, _, _, vs, _, _, _, _, _, _, _)            => vs.isEmpty
        case AttrSetTacByte(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
        case AttrSetTacShort(_, _, _, vs, _, _, _, _, _, _, _)          => vs.isEmpty
        case AttrSetTacChar(_, _, _, vs, _, _, _, _, _, _, _)           => vs.isEmpty
      }
      case _             => false
    }
  }

  private def removingLastValue(a: Attr, curVs: Set[Any]): Boolean = {
    a match {
      case a: AttrSetMan => a match {
        case AttrSetManID(_, _, _, vs, _, _, _, _, _, _, _)             => vs == curVs
        case AttrSetManString(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetManInt(_, _, _, vs, _, _, _, _, _, _, _)            => vs == curVs
        case AttrSetManLong(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetManFloat(_, _, _, vs, _, _, _, _, _, _, _)          => vs == curVs
        case AttrSetManDouble(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetManBoolean(_, _, _, vs, _, _, _, _, _, _, _)        => vs == curVs
        case AttrSetManBigInt(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetManBigDecimal(_, _, _, vs, _, _, _, _, _, _, _)     => vs == curVs
        case AttrSetManDate(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetManDuration(_, _, _, vs, _, _, _, _, _, _, _)       => vs == curVs
        case AttrSetManInstant(_, _, _, vs, _, _, _, _, _, _, _)        => vs == curVs
        case AttrSetManLocalDate(_, _, _, vs, _, _, _, _, _, _, _)      => vs == curVs
        case AttrSetManLocalTime(_, _, _, vs, _, _, _, _, _, _, _)      => vs == curVs
        case AttrSetManLocalDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs == curVs
        case AttrSetManOffsetTime(_, _, _, vs, _, _, _, _, _, _, _)     => vs == curVs
        case AttrSetManOffsetDateTime(_, _, _, vs, _, _, _, _, _, _, _) => vs == curVs
        case AttrSetManZonedDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs == curVs
        case AttrSetManUUID(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetManURI(_, _, _, vs, _, _, _, _, _, _, _)            => vs == curVs
        case AttrSetManByte(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetManShort(_, _, _, vs, _, _, _, _, _, _, _)          => vs == curVs
        case AttrSetManChar(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
      }
      case a: AttrSetTac => a match {
        case AttrSetTacID(_, _, _, vs, _, _, _, _, _, _, _)             => vs == curVs
        case AttrSetTacString(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetTacInt(_, _, _, vs, _, _, _, _, _, _, _)            => vs == curVs
        case AttrSetTacLong(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetTacFloat(_, _, _, vs, _, _, _, _, _, _, _)          => vs == curVs
        case AttrSetTacDouble(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetTacBoolean(_, _, _, vs, _, _, _, _, _, _, _)        => vs == curVs
        case AttrSetTacBigInt(_, _, _, vs, _, _, _, _, _, _, _)         => vs == curVs
        case AttrSetTacBigDecimal(_, _, _, vs, _, _, _, _, _, _, _)     => vs == curVs
        case AttrSetTacDate(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetTacDuration(_, _, _, vs, _, _, _, _, _, _, _)       => vs == curVs
        case AttrSetTacInstant(_, _, _, vs, _, _, _, _, _, _, _)        => vs == curVs
        case AttrSetTacLocalDate(_, _, _, vs, _, _, _, _, _, _, _)      => vs == curVs
        case AttrSetTacLocalTime(_, _, _, vs, _, _, _, _, _, _, _)      => vs == curVs
        case AttrSetTacLocalDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs == curVs
        case AttrSetTacOffsetTime(_, _, _, vs, _, _, _, _, _, _, _)     => vs == curVs
        case AttrSetTacOffsetDateTime(_, _, _, vs, _, _, _, _, _, _, _) => vs == curVs
        case AttrSetTacZonedDateTime(_, _, _, vs, _, _, _, _, _, _, _)  => vs == curVs
        case AttrSetTacUUID(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetTacURI(_, _, _, vs, _, _, _, _, _, _, _)            => vs == curVs
        case AttrSetTacByte(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
        case AttrSetTacShort(_, _, _, vs, _, _, _, _, _, _, _)          => vs == curVs
        case AttrSetTacChar(_, _, _, vs, _, _, _, _, _, _, _)           => vs == curVs
      }
      case _             => false
    }
  }
}
