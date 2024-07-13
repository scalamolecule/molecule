package molecule.core.validation.insert

import molecule.base.ast._
import molecule.base.error.{InsertError, ModelError}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.InsertValidators_
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

trait InsertValidationExtraction
  extends InsertValidators_
    with ModelUtils { self: InsertValidationResolvers_ =>

  private var curElements: List[Element] = List.empty[Element]
  private def noEmpty(a: Attr) = throw new Exception("Can't use tacit attributes in insert molecule (${a.name}).")

  @tailrec
  final override def getValidators(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    validators: List[Product => Seq[InsertError]],
    tplIndex: Int,
    prevRefs: List[String]
  ): List[Product => Seq[InsertError]] = {
    if (validators.isEmpty) {
      curElements = elements
    }
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError("Can't insert attributes with an applied value (${a.name}).")
          }
          a match {
            case a: AttrOne => a match {
              case a: AttrOneMan => getValidators(nsMap, tail, validators :+
                resolveAttrOneMan(a, tplIndex), tplIndex + 1, prevRefs)

              case a: AttrOneOpt => getValidators(nsMap, tail, validators :+
                resolveAttrOneOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan =>
                val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                getValidators(nsMap, tail, validators :+
                  resolveAttrSetMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrSetOpt => getValidators(nsMap, tail, validators :+
                resolveAttrSetOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan =>
                val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                getValidators(nsMap, tail, validators :+
                  resolveAttrSeqMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrSeqOpt => getValidators(nsMap, tail, validators :+
                resolveAttrSeqOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan =>
                val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                getValidators(nsMap, tail, validators :+
                  resolveAttrMapMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrMapOpt => getValidators(nsMap, tail, validators :+
                resolveAttrMapOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }
          }

        case Ref(_, refAttr, _, _, _, _) =>
          getValidators(nsMap, tail, validators, tplIndex, prevRefs :+ refAttr)

        case BackRef(backRefNs, _, _) =>
          noNsReUseAfterBackref(tail.head, prevRefs, backRefNs)
          getValidators(nsMap, tail, validators, tplIndex, prevRefs)

        case OptRef(Ref(ns, refAttr, _, _, _, _), optRefElements) =>
          curElements = optRefElements
          getValidators(nsMap, tail, validators :+
            addOptRef(nsMap, tplIndex, ns, refAttr, optRefElements), tplIndex, Nil)

        case Nested(Ref(ns, refAttr, _, _, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), tplIndex, Nil)

        case OptNested(Ref(ns, refAttr, _, _, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), tplIndex, Nil)
      }
      case Nil             => validators
    }
  }


  private def addOptRef(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    optElements: List[Element]
  ): Product => Seq[InsertError] = {
    // Recursively validate nested data
    val validate    = getInsertValidator(nsMap, optElements)
    val fullRefAttr = ns + "." + refAttr
    countValueAttrs(optElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val optionValue   = tpl.productElement(tplIndex).asInstanceOf[Option[Any]]
          val indexedErrors = collectInsertErrorsSingle(validate, optionValue)
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
      case _ =>
        (tpl: Product) => {
          val optionTpl     = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
          val indexedErrors = collectInsertErrorsTuple(validate, optionTpl)
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
    }
  }

  private def collectInsertErrorsSingle(
    validate: Product => Seq[InsertError],
    values: Iterable[Any],
  ): Seq[(Int, Seq[InsertError])] = {
    values.zipWithIndex.flatMap { case (nestedValue, rowIndex) =>
      val rowErrors: Seq[InsertError] = validate(Tuple1(nestedValue)) // Need tuple
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }.toSeq
  }

  private def collectInsertErrorsTuple(
    validate: Product => Seq[InsertError],
    values: Iterable[Product],
  ): Seq[(Int, Seq[InsertError])] = {
    values.zipWithIndex.flatMap { case (nestedValue, rowIndex) =>
      val rowErrors: Seq[InsertError] = validate(nestedValue) // Need tuple
      if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
    }.toSeq
  }

  private def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    nestedElements: List[Element]
  ): Product => Seq[InsertError] = {
    // Recursively validate nested data
    val validate    = getInsertValidator(nsMap, nestedElements)
    val fullRefAttr = ns + "." + refAttr
    countValueAttrs(nestedElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val nestedValues  = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val indexedErrors = collectInsertErrorsSingle(validate, nestedValues)
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls    = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val indexedErrors = collectInsertErrorsTuple(validate, nestedTpls)
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
    }
  }


  private def validatorV[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        val errors = validator(tpl)(tpl.productElement(tplIndex).asInstanceOf[T])
        if (errors.isEmpty) {
          Nil
        } else {
          Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManID             => validatorV(ns, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrOneManString         => validatorV(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneManInt            => validatorV(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneManLong           => validatorV(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneManFloat          => validatorV(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneManDouble         => validatorV(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneManBoolean        => validatorV(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneManBigInt         => validatorV(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneManBigDecimal     => validatorV(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneManDate           => validatorV(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneManDuration       => validatorV(ns, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrOneManInstant        => validatorV(ns, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrOneManLocalDate      => validatorV(ns, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrOneManLocalTime      => validatorV(ns, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrOneManLocalDateTime  => validatorV(ns, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrOneManOffsetTime     => validatorV(ns, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrOneManOffsetDateTime => validatorV(ns, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrOneManZonedDateTime  => validatorV(ns, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrOneManUUID           => validatorV(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneManURI            => validatorV(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneManByte           => validatorV(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneManShort          => validatorV(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneManChar           => validatorV(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }


  private def validatorOptV[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case Some(value) =>
            val errors = validator(tpl)(value.asInstanceOf[T])
            if (errors.isEmpty) Nil else
              Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
          case None        => Nil
        }
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptID             => validatorOptV(ns, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrOneOptString         => validatorOptV(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneOptInt            => validatorOptV(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneOptLong           => validatorOptV(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneOptFloat          => validatorOptV(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneOptDouble         => validatorOptV(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneOptBoolean        => validatorOptV(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneOptBigInt         => validatorOptV(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneOptBigDecimal     => validatorOptV(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneOptDate           => validatorOptV(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneOptDuration       => validatorOptV(ns, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrOneOptInstant        => validatorOptV(ns, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrOneOptLocalDate      => validatorOptV(ns, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrOneOptLocalTime      => validatorOptV(ns, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrOneOptLocalDateTime  => validatorOptV(ns, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrOneOptOffsetTime     => validatorOptV(ns, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrOneOptOffsetDateTime => validatorOptV(ns, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrOneOptZonedDateTime  => validatorOptV(ns, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrOneOptUUID           => validatorOptV(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneOptURI            => validatorOptV(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneOptByte           => validatorOptV(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneOptShort          => validatorOptV(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneOptChar           => validatorOptV(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def noEmptyCollection(collection: String, ns: String, attr: String, tplIndex: Int): Seq[InsertError] = Seq(
    InsertError(tplIndex, ns + "." + attr,
      Seq(s"Can't insert empty $collection for mandatory attribute"), Nil)
  )

  private def validatorSet[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Set[_]].isEmpty)
          noEmptyCollection("Set", ns, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val set      = tpl.productElement(tplIndex).asInstanceOf[Set[_]]
        if (mandatory && set.isEmpty) {
          noEmptyCollection("Set", ns, attr, tplIndex)
        } else {
          val errors = set.flatMap(value => validate(value.asInstanceOf[T])).toSeq
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManID             => validatorSet(ns, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrSetManString         => validatorSet(ns, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSetManInt            => validatorSet(ns, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSetManLong           => validatorSet(ns, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSetManFloat          => validatorSet(ns, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSetManDouble         => validatorSet(ns, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSetManBoolean        => validatorSet(ns, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetManBigInt         => validatorSet(ns, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetManBigDecimal     => validatorSet(ns, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetManDate           => validatorSet(ns, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSetManDuration       => validatorSet(ns, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrSetManInstant        => validatorSet(ns, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrSetManLocalDate      => validatorSet(ns, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSetManLocalTime      => validatorSet(ns, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSetManLocalDateTime  => validatorSet(ns, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSetManOffsetTime     => validatorSet(ns, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSetManOffsetDateTime => validatorSet(ns, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSetManZonedDateTime  => validatorSet(ns, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSetManUUID           => validatorSet(ns, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSetManURI            => validatorSet(ns, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSetManByte           => validatorSet(ns, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSetManShort          => validatorSet(ns, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSetManChar           => validatorSet(ns, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptSet[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) =>
            val errors = set.toSeq.flatMap(value => validate(value.asInstanceOf[T]))
            if (errors.isEmpty) Nil else
              Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
          case None              => Nil
        }
    }
  }
  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetOptID             => validatorOptSet(ns, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrSetOptString         => validatorOptSet(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSetOptInt            => validatorOptSet(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSetOptLong           => validatorOptSet(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSetOptFloat          => validatorOptSet(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSetOptDouble         => validatorOptSet(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSetOptBoolean        => validatorOptSet(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetOptBigInt         => validatorOptSet(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetOptBigDecimal     => validatorOptSet(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetOptDate           => validatorOptSet(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSetOptDuration       => validatorOptSet(ns, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrSetOptInstant        => validatorOptSet(ns, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrSetOptLocalDate      => validatorOptSet(ns, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSetOptLocalTime      => validatorOptSet(ns, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSetOptLocalDateTime  => validatorOptSet(ns, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSetOptOffsetTime     => validatorOptSet(ns, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSetOptOffsetDateTime => validatorOptSet(ns, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSetOptZonedDateTime  => validatorOptSet(ns, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSetOptUUID           => validatorOptSet(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSetOptURI            => validatorOptSet(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSetOptByte           => validatorOptSet(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSetOptShort          => validatorOptSet(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSetOptChar           => validatorOptSet(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorSeq[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Seq[_]].isEmpty)
          noEmptyCollection("Seq", ns, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val seq      = tpl.productElement(tplIndex).asInstanceOf[Seq[_]]
        if (mandatory && seq.isEmpty) {
          noEmptyCollection("Seq", ns, attr, tplIndex)
        } else {
          val errors = seq.flatMap(value => validate(value.asInstanceOf[T]))
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrSeqMan(a: AttrSeqMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSeqManID             => validatorSeq(ns, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrSeqManString         => validatorSeq(ns, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSeqManInt            => validatorSeq(ns, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSeqManLong           => validatorSeq(ns, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSeqManFloat          => validatorSeq(ns, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSeqManDouble         => validatorSeq(ns, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSeqManBoolean        => validatorSeq(ns, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSeqManBigInt         => validatorSeq(ns, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSeqManBigDecimal     => validatorSeq(ns, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSeqManDate           => validatorSeq(ns, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSeqManDuration       => validatorSeq(ns, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrSeqManInstant        => validatorSeq(ns, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrSeqManLocalDate      => validatorSeq(ns, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSeqManLocalTime      => validatorSeq(ns, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSeqManLocalDateTime  => validatorSeq(ns, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSeqManOffsetTime     => validatorSeq(ns, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSeqManOffsetDateTime => validatorSeq(ns, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSeqManZonedDateTime  => validatorSeq(ns, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSeqManUUID           => validatorSeq(ns, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSeqManURI            => validatorSeq(ns, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSeqManByte           => validatorSeq(ns, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSeqManShort          => validatorSeq(ns, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSeqManChar           => validatorSeq(ns, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptSeq[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        tpl.productElement(tplIndex) match {
          case Some(seq: Seq[_]) =>
            val errors = seq.flatMap(value => validate(value.asInstanceOf[T]))
            if (errors.isEmpty) Nil else
              Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
          case None              => Nil
        }
    }
  }
  private def resolveAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSeqOptID             => validatorOptSeq(ns, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrSeqOptString         => validatorOptSeq(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSeqOptInt            => validatorOptSeq(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSeqOptLong           => validatorOptSeq(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSeqOptFloat          => validatorOptSeq(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSeqOptDouble         => validatorOptSeq(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSeqOptBoolean        => validatorOptSeq(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSeqOptBigInt         => validatorOptSeq(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSeqOptBigDecimal     => validatorOptSeq(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSeqOptDate           => validatorOptSeq(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSeqOptDuration       => validatorOptSeq(ns, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrSeqOptInstant        => validatorOptSeq(ns, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrSeqOptLocalDate      => validatorOptSeq(ns, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSeqOptLocalTime      => validatorOptSeq(ns, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSeqOptLocalDateTime  => validatorOptSeq(ns, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSeqOptOffsetTime     => validatorOptSeq(ns, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSeqOptOffsetDateTime => validatorOptSeq(ns, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSeqOptZonedDateTime  => validatorOptSeq(ns, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSeqOptUUID           => validatorOptSeq(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSeqOptURI            => validatorOptSeq(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSeqOptByte           => validatorOptSeq(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSeqOptShort          => validatorOptSeq(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSeqOptChar           => validatorOptSeq(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorMap[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Map[String, _]].isEmpty)
          noEmptyCollection("Map", ns, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val map      = tpl.productElement(tplIndex).asInstanceOf[Map[String, _]]
        if (mandatory && map.isEmpty) {
          noEmptyCollection("Map", ns, attr, tplIndex)
        } else {
          val errors = map.flatMap(value => validate(value.asInstanceOf[T])).toSeq
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrMapMan(a: AttrMapMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrMapManID             => validatorMap(ns, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrMapManString         => validatorMap(ns, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrMapManInt            => validatorMap(ns, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrMapManLong           => validatorMap(ns, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrMapManFloat          => validatorMap(ns, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrMapManDouble         => validatorMap(ns, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrMapManBoolean        => validatorMap(ns, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrMapManBigInt         => validatorMap(ns, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrMapManBigDecimal     => validatorMap(ns, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrMapManDate           => validatorMap(ns, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrMapManDuration       => validatorMap(ns, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrMapManInstant        => validatorMap(ns, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrMapManLocalDate      => validatorMap(ns, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrMapManLocalTime      => validatorMap(ns, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrMapManLocalDateTime  => validatorMap(ns, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrMapManOffsetTime     => validatorMap(ns, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrMapManOffsetDateTime => validatorMap(ns, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrMapManZonedDateTime  => validatorMap(ns, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrMapManUUID           => validatorMap(ns, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrMapManURI            => validatorMap(ns, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrMapManByte           => validatorMap(ns, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrMapManShort          => validatorMap(ns, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrMapManChar           => validatorMap(ns, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptMap[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        tpl.productElement(tplIndex) match {
          case Some(map: Map[_, _]) =>
            val errors = map.toList.flatMap { case (k, v) => validate(v.asInstanceOf[T]) }
            if (errors.isEmpty) Nil else
              Seq(InsertError(tplIndex, ns + "." + attr, errors, Nil))
          case None                 => Nil
        }
    }
  }
  private def resolveAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrMapOptID             => validatorOptMap(ns, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrMapOptString         => validatorOptMap(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrMapOptInt            => validatorOptMap(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrMapOptLong           => validatorOptMap(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrMapOptFloat          => validatorOptMap(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrMapOptDouble         => validatorOptMap(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrMapOptBoolean        => validatorOptMap(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrMapOptBigInt         => validatorOptMap(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrMapOptBigDecimal     => validatorOptMap(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrMapOptDate           => validatorOptMap(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrMapOptDuration       => validatorOptMap(ns, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrMapOptInstant        => validatorOptMap(ns, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrMapOptLocalDate      => validatorOptMap(ns, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrMapOptLocalTime      => validatorOptMap(ns, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrMapOptLocalDateTime  => validatorOptMap(ns, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrMapOptOffsetTime     => validatorOptMap(ns, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrMapOptOffsetDateTime => validatorOptMap(ns, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrMapOptZonedDateTime  => validatorOptMap(ns, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrMapOptUUID           => validatorOptMap(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrMapOptURI            => validatorOptMap(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrMapOptByte           => validatorOptMap(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrMapOptShort          => validatorOptMap(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrMapOptChar           => validatorOptMap(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }
}