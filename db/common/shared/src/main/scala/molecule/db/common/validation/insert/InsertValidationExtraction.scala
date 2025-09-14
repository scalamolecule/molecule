package molecule.db.common.validation.insert

import molecule.core.dataModel.*
import molecule.core.error.{InsertError, ModelError}
import molecule.db.common.api.MetaDb
import molecule.db.common.util.ModelUtils

trait InsertValidationExtraction
  extends InsertValidators_
    with ModelUtils
    { self: InsertValidationResolvers_ =>

  private var curElements: List[Element] = List.empty[Element]
  private def noEmpty(a: Attr) = throw new Exception("Can't use tacit attributes in insert molecule (${a.name}).")

  final override def getValidators(
    metaDb: MetaDb,
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
              case a: AttrOneMan => getValidators(metaDb, tail, validators :+
                resolveAttrOneMan(a, tplIndex), tplIndex + 1, prevRefs)

              case a: AttrOneOpt => getValidators(metaDb, tail, validators :+
                resolveAttrOneOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrSet => a match {
              case a: AttrSetMan =>
                val mandatory = metaDb.mandatoryAttrs.get(a.ent).exists(_.contains(a.ent + "." + a.attr))
                getValidators(metaDb, tail, validators :+
                  resolveAttrSetMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrSetOpt => getValidators(metaDb, tail, validators :+
                resolveAttrSetOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrSeq => a match {
              case a: AttrSeqMan =>
                val mandatory = metaDb.mandatoryAttrs.get(a.ent).exists(_.contains(a.attr))
                getValidators(metaDb, tail, validators :+
                  resolveAttrSeqMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrSeqOpt => getValidators(metaDb, tail, validators :+
                resolveAttrSeqOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }

            case a: AttrMap => a match {
              case a: AttrMapMan =>
                val mandatory = metaDb.mandatoryAttrs.get(a.ent).exists(_.contains(a.attr))
                getValidators(metaDb, tail, validators :+
                  resolveAttrMapMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

              case a: AttrMapOpt => getValidators(metaDb, tail, validators :+
                resolveAttrMapOpt(a, tplIndex), tplIndex + 1, prevRefs)

              case a => noEmpty(a)
            }
          }

        case Ref(_, refAttr, _, _, _, _) =>
          getValidators(metaDb, tail, validators, tplIndex, prevRefs :+ refAttr)

        case BackRef(backRef, _, _) =>
          noEntityReUseAfterBackref(tail.head, prevRefs, backRef)
          getValidators(metaDb, tail, validators, tplIndex, prevRefs)

        case OptRef(Ref(ent, refAttr, _, _, _, _), optRefElements) =>
          curElements = optRefElements
          getValidators(metaDb, tail, validators :+
            addOptRef(metaDb, tplIndex, ent, refAttr, optRefElements), tplIndex + 1, Nil)

        case OptEntity(optRefElements) =>
          curElements = optRefElements
          getValidators(metaDb, tail, validators :+
            addOptRef(metaDb, tplIndex, "Optional", "entity", optRefElements), tplIndex + 1, Nil)

        case Nested(Ref(ent, refAttr, _, _, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(metaDb, tail, validators :+
            addNested(metaDb, tplIndex, ent, refAttr, nestedElements), tplIndex, Nil)

        case OptNested(Ref(ent, refAttr, _, _, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(metaDb, tail, validators :+
            addNested(metaDb, tplIndex, ent, refAttr, nestedElements), tplIndex, Nil)
      }
      case Nil             => validators
    }
  }


  private def addOptRef(
    metaDb: MetaDb,
    tplIndex: Int,
    ent: String,
    refAttr: String,
    optElements: List[Element]
  ): Product => Seq[InsertError] = {
    // Recursively validate optional data
    val validate    = getInsertValidator(metaDb, optElements)
    val fullRefAttr = ent + "." + refAttr
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
    metaDb: MetaDb,
    tplIndex: Int,
    ent: String,
    refAttr: String,
    nestedElements: List[Element]
  ): Product => Seq[InsertError] = {
    // Recursively validate nested data
    val validate    = getInsertValidator(metaDb, nestedElements)
    val fullRefAttr = ent + "." + refAttr
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
    ent: String,
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
          Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneManID             => validatorV(ent, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrOneManString         => validatorV(ent, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneManInt            => validatorV(ent, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneManLong           => validatorV(ent, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneManFloat          => validatorV(ent, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneManDouble         => validatorV(ent, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneManBoolean        => validatorV(ent, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneManBigInt         => validatorV(ent, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneManBigDecimal     => validatorV(ent, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneManDate           => validatorV(ent, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneManDuration       => validatorV(ent, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrOneManInstant        => validatorV(ent, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrOneManLocalDate      => validatorV(ent, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrOneManLocalTime      => validatorV(ent, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrOneManLocalDateTime  => validatorV(ent, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrOneManOffsetTime     => validatorV(ent, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrOneManOffsetDateTime => validatorV(ent, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrOneManZonedDateTime  => validatorV(ent, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrOneManUUID           => validatorV(ent, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneManURI            => validatorV(ent, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneManByte           => validatorV(ent, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneManShort          => validatorV(ent, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneManChar           => validatorV(ent, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }


  private def validatorOptV[T](
    ent: String,
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
              Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
          case None        => Nil
        }
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrOneOptID             => validatorOptV(ent, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrOneOptString         => validatorOptV(ent, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneOptInt            => validatorOptV(ent, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneOptLong           => validatorOptV(ent, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneOptFloat          => validatorOptV(ent, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneOptDouble         => validatorOptV(ent, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneOptBoolean        => validatorOptV(ent, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneOptBigInt         => validatorOptV(ent, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneOptBigDecimal     => validatorOptV(ent, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneOptDate           => validatorOptV(ent, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneOptDuration       => validatorOptV(ent, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrOneOptInstant        => validatorOptV(ent, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrOneOptLocalDate      => validatorOptV(ent, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrOneOptLocalTime      => validatorOptV(ent, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrOneOptLocalDateTime  => validatorOptV(ent, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrOneOptOffsetTime     => validatorOptV(ent, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrOneOptOffsetDateTime => validatorOptV(ent, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrOneOptZonedDateTime  => validatorOptV(ent, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrOneOptUUID           => validatorOptV(ent, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneOptURI            => validatorOptV(ent, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneOptByte           => validatorOptV(ent, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneOptShort          => validatorOptV(ent, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneOptChar           => validatorOptV(ent, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def noEmptyCollection(collection: String, ent: String, attr: String, tplIndex: Int): Seq[InsertError] = Seq(
    InsertError(tplIndex, ent + "." + attr,
      Seq(s"Can't insert empty $collection for mandatory attribute"), Nil)
  )

  private def validatorSet[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Set[?]].isEmpty)
          noEmptyCollection("Set", ent, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val set      = tpl.productElement(tplIndex).asInstanceOf[Set[?]]
        if (mandatory && set.isEmpty) {
          noEmptyCollection("Set", ent, attr, tplIndex)
        } else {
          val errors = set.flatMap(value => validate(value.asInstanceOf[T])).toSeq
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrSetManID             => validatorSet(ent, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrSetManString         => validatorSet(ent, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSetManInt            => validatorSet(ent, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSetManLong           => validatorSet(ent, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSetManFloat          => validatorSet(ent, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSetManDouble         => validatorSet(ent, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSetManBoolean        => validatorSet(ent, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetManBigInt         => validatorSet(ent, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetManBigDecimal     => validatorSet(ent, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetManDate           => validatorSet(ent, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSetManDuration       => validatorSet(ent, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrSetManInstant        => validatorSet(ent, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrSetManLocalDate      => validatorSet(ent, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSetManLocalTime      => validatorSet(ent, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSetManLocalDateTime  => validatorSet(ent, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSetManOffsetTime     => validatorSet(ent, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSetManOffsetDateTime => validatorSet(ent, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSetManZonedDateTime  => validatorSet(ent, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSetManUUID           => validatorSet(ent, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSetManURI            => validatorSet(ent, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSetManByte           => validatorSet(ent, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSetManShort          => validatorSet(ent, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSetManChar           => validatorSet(ent, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptSet[T](
    ent: String,
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
              Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
          case None              => Nil
        }
    }
  }
  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrSetOptID             => validatorOptSet(ent, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrSetOptString         => validatorOptSet(ent, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSetOptInt            => validatorOptSet(ent, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSetOptLong           => validatorOptSet(ent, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSetOptFloat          => validatorOptSet(ent, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSetOptDouble         => validatorOptSet(ent, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSetOptBoolean        => validatorOptSet(ent, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetOptBigInt         => validatorOptSet(ent, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetOptBigDecimal     => validatorOptSet(ent, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetOptDate           => validatorOptSet(ent, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSetOptDuration       => validatorOptSet(ent, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrSetOptInstant        => validatorOptSet(ent, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrSetOptLocalDate      => validatorOptSet(ent, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSetOptLocalTime      => validatorOptSet(ent, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSetOptLocalDateTime  => validatorOptSet(ent, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSetOptOffsetTime     => validatorOptSet(ent, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSetOptOffsetDateTime => validatorOptSet(ent, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSetOptZonedDateTime  => validatorOptSet(ent, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSetOptUUID           => validatorOptSet(ent, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSetOptURI            => validatorOptSet(ent, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSetOptByte           => validatorOptSet(ent, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSetOptShort          => validatorOptSet(ent, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSetOptChar           => validatorOptSet(ent, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorSeq[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Seq[?]].isEmpty)
          noEmptyCollection("Seq", ent, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val seq      = tpl.productElement(tplIndex).asInstanceOf[Seq[?]]
        if (mandatory && seq.isEmpty) {
          noEmptyCollection("Seq", ent, attr, tplIndex)
        } else {
          val errors = seq.flatMap(value => validate(value.asInstanceOf[T]))
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrSeqMan(a: AttrSeqMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrSeqManID             => validatorSeq(ent, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrSeqManString         => validatorSeq(ent, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSeqManInt            => validatorSeq(ent, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSeqManLong           => validatorSeq(ent, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSeqManFloat          => validatorSeq(ent, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSeqManDouble         => validatorSeq(ent, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSeqManBoolean        => validatorSeq(ent, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSeqManBigInt         => validatorSeq(ent, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSeqManBigDecimal     => validatorSeq(ent, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSeqManDate           => validatorSeq(ent, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSeqManDuration       => validatorSeq(ent, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrSeqManInstant        => validatorSeq(ent, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrSeqManLocalDate      => validatorSeq(ent, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSeqManLocalTime      => validatorSeq(ent, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSeqManLocalDateTime  => validatorSeq(ent, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSeqManOffsetTime     => validatorSeq(ent, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSeqManOffsetDateTime => validatorSeq(ent, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSeqManZonedDateTime  => validatorSeq(ent, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSeqManUUID           => validatorSeq(ent, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSeqManURI            => validatorSeq(ent, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSeqManByte           => validatorSeq(ent, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSeqManShort          => validatorSeq(ent, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSeqManChar           => validatorSeq(ent, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptSeq[T](
    ent: String,
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
              Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
          case None              => Nil
        }
    }
  }
  private def resolveAttrSeqOpt(a: AttrSeqOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrSeqOptID             => validatorOptSeq(ent, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrSeqOptString         => validatorOptSeq(ent, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSeqOptInt            => validatorOptSeq(ent, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSeqOptLong           => validatorOptSeq(ent, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSeqOptFloat          => validatorOptSeq(ent, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSeqOptDouble         => validatorOptSeq(ent, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSeqOptBoolean        => validatorOptSeq(ent, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSeqOptBigInt         => validatorOptSeq(ent, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSeqOptBigDecimal     => validatorOptSeq(ent, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSeqOptDate           => validatorOptSeq(ent, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSeqOptDuration       => validatorOptSeq(ent, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrSeqOptInstant        => validatorOptSeq(ent, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrSeqOptLocalDate      => validatorOptSeq(ent, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrSeqOptLocalTime      => validatorOptSeq(ent, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrSeqOptLocalDateTime  => validatorOptSeq(ent, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrSeqOptOffsetTime     => validatorOptSeq(ent, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrSeqOptOffsetDateTime => validatorOptSeq(ent, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrSeqOptZonedDateTime  => validatorOptSeq(ent, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrSeqOptUUID           => validatorOptSeq(ent, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSeqOptURI            => validatorOptSeq(ent, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSeqOptByte           => validatorOptSeq(ent, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSeqOptShort          => validatorOptSeq(ent, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSeqOptChar           => validatorOptSeq(ent, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorMap[T](
    ent: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]].isEmpty)
          noEmptyCollection("Map", ent, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val map      = tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]]
        if (mandatory && map.isEmpty) {
          noEmptyCollection("Map", ent, attr, tplIndex)
        } else {
          val errors = map.flatMap(value => validate(value.asInstanceOf[T])).toSeq
          if (errors.isEmpty) Nil else
            Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrMapMan(a: AttrMapMan, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrMapManID             => validatorMap(ent, attr, tplIndex, mandatory, validatorID(a.validator, a, curElements))
      case a: AttrMapManString         => validatorMap(ent, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrMapManInt            => validatorMap(ent, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrMapManLong           => validatorMap(ent, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrMapManFloat          => validatorMap(ent, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrMapManDouble         => validatorMap(ent, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrMapManBoolean        => validatorMap(ent, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrMapManBigInt         => validatorMap(ent, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrMapManBigDecimal     => validatorMap(ent, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrMapManDate           => validatorMap(ent, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrMapManDuration       => validatorMap(ent, attr, tplIndex, mandatory, validatorDuration(a.validator, a, curElements))
      case a: AttrMapManInstant        => validatorMap(ent, attr, tplIndex, mandatory, validatorInstant(a.validator, a, curElements))
      case a: AttrMapManLocalDate      => validatorMap(ent, attr, tplIndex, mandatory, validatorLocalDate(a.validator, a, curElements))
      case a: AttrMapManLocalTime      => validatorMap(ent, attr, tplIndex, mandatory, validatorLocalTime(a.validator, a, curElements))
      case a: AttrMapManLocalDateTime  => validatorMap(ent, attr, tplIndex, mandatory, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrMapManOffsetTime     => validatorMap(ent, attr, tplIndex, mandatory, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrMapManOffsetDateTime => validatorMap(ent, attr, tplIndex, mandatory, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrMapManZonedDateTime  => validatorMap(ent, attr, tplIndex, mandatory, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrMapManUUID           => validatorMap(ent, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrMapManURI            => validatorMap(ent, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrMapManByte           => validatorMap(ent, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrMapManShort          => validatorMap(ent, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrMapManChar           => validatorMap(ent, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  private def validatorOptMap[T](
    ent: String,
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
              Seq(InsertError(tplIndex, ent + "." + attr, errors, Nil))
          case None                 => Nil
        }
    }
  }
  private def resolveAttrMapOpt(a: AttrMapOpt, tplIndex: Int): Product => Seq[InsertError] = {
    val (ent, attr) = (a.ent, a.attr)
    a match {
      case a: AttrMapOptID             => validatorOptMap(ent, attr, tplIndex, validatorID(a.validator, a, curElements))
      case a: AttrMapOptString         => validatorOptMap(ent, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrMapOptInt            => validatorOptMap(ent, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrMapOptLong           => validatorOptMap(ent, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrMapOptFloat          => validatorOptMap(ent, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrMapOptDouble         => validatorOptMap(ent, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrMapOptBoolean        => validatorOptMap(ent, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrMapOptBigInt         => validatorOptMap(ent, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrMapOptBigDecimal     => validatorOptMap(ent, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrMapOptDate           => validatorOptMap(ent, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrMapOptDuration       => validatorOptMap(ent, attr, tplIndex, validatorDuration(a.validator, a, curElements))
      case a: AttrMapOptInstant        => validatorOptMap(ent, attr, tplIndex, validatorInstant(a.validator, a, curElements))
      case a: AttrMapOptLocalDate      => validatorOptMap(ent, attr, tplIndex, validatorLocalDate(a.validator, a, curElements))
      case a: AttrMapOptLocalTime      => validatorOptMap(ent, attr, tplIndex, validatorLocalTime(a.validator, a, curElements))
      case a: AttrMapOptLocalDateTime  => validatorOptMap(ent, attr, tplIndex, validatorLocalDateTime(a.validator, a, curElements))
      case a: AttrMapOptOffsetTime     => validatorOptMap(ent, attr, tplIndex, validatorOffsetTime(a.validator, a, curElements))
      case a: AttrMapOptOffsetDateTime => validatorOptMap(ent, attr, tplIndex, validatorOffsetDateTime(a.validator, a, curElements))
      case a: AttrMapOptZonedDateTime  => validatorOptMap(ent, attr, tplIndex, validatorZonedDateTime(a.validator, a, curElements))
      case a: AttrMapOptUUID           => validatorOptMap(ent, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrMapOptURI            => validatorOptMap(ent, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrMapOptByte           => validatorOptMap(ent, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrMapOptShort          => validatorOptMap(ent, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrMapOptChar           => validatorOptMap(ent, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }
}