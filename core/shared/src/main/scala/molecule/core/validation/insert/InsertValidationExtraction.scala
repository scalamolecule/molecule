package molecule.core.validation.insert

import molecule.base.ast._
import molecule.base.error.{InsertError, ModelError}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.InsertValidators_
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

trait InsertValidationExtraction extends InsertValidators_ with ModelUtils { self: InsertValidationResolvers_ =>

  private var curElements: List[Element] = List.empty[Element]

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
            throw ModelError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => getValidators(nsMap, tail, validators :+
                  resolveAttrOneMan(a, tplIndex), tplIndex + 1, prevRefs)

                case a: AttrOneOpt => getValidators(nsMap, tail, validators :+
                  resolveAttrOneOpt(a, tplIndex), tplIndex + 1, prevRefs)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                  getValidators(nsMap, tail, validators :+
                    resolveAttrSetMan(a, tplIndex, mandatory), tplIndex + 1, prevRefs)

                case a: AttrSetOpt => getValidators(nsMap, tail, validators :+
                  resolveAttrSetOpt(a, tplIndex), tplIndex + 1, prevRefs)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
          }

        case Ref(_, refAttr, _, _) =>
          getValidators(nsMap, tail, validators, tplIndex, prevRefs :+ refAttr)

        case BackRef(backRefNs, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          getValidators(nsMap, tail, validators, tplIndex, prevRefs)

        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), tplIndex, Nil)

        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
          curElements = nestedElements
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), tplIndex, Nil)
      }
      case Nil             => validators
    }
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
          val indexedErrors = nestedValues.zipWithIndex.flatMap { case (nestedValue, rowIndex) =>
            val rowErrors: Seq[InsertError] = validate(Tuple1(nestedValue))
            if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
          }
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls    = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val indexedErrors = nestedTpls.zipWithIndex.flatMap { case (nestedTpl, rowIndex) =>
            val rowErrors: Seq[InsertError] = validate(nestedTpl)
            if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
          }
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, fullRefAttr, Nil, indexedErrors))
        }
    }
  }

  def validatorV[T](
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
      case a: AttrOneManString     => validatorV(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneManInt        => validatorV(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneManLong       => validatorV(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneManFloat      => validatorV(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneManDouble     => validatorV(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneManBoolean    => validatorV(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneManBigInt     => validatorV(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneManBigDecimal => validatorV(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneManDate       => validatorV(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneManUUID       => validatorV(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneManURI        => validatorV(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneManByte       => validatorV(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneManShort      => validatorV(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneManChar       => validatorV(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }


  def validatorOptV[T](
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
      case a: AttrOneOptString     => validatorOptV(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneOptInt        => validatorOptV(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneOptLong       => validatorOptV(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneOptFloat      => validatorOptV(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneOptDouble     => validatorOptV(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneOptBoolean    => validatorOptV(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneOptBigInt     => validatorOptV(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneOptBigDecimal => validatorOptV(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneOptDate       => validatorOptV(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneOptUUID       => validatorOptV(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneOptURI        => validatorOptV(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneOptByte       => validatorOptV(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneOptShort      => validatorOptV(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneOptChar       => validatorOptV(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  def noEmptySet(ns: String, attr: String, tplIndex: Int): Seq[InsertError] = Seq(
    InsertError(tplIndex, ns + "." + attr,
      Seq("Can't insert empty Set for mandatory attribute"), Nil)
  )

  def validatorSet[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Set[_]].isEmpty)
          noEmptySet(ns, attr, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        val validate = validator(tpl)
        val set      = tpl.productElement(tplIndex).asInstanceOf[Set[_]]
        if (mandatory && set.isEmpty) {
          noEmptySet(ns, attr, tplIndex)
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
      case a: AttrSetManString     => validatorSet(ns, attr, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSetManInt        => validatorSet(ns, attr, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSetManLong       => validatorSet(ns, attr, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSetManFloat      => validatorSet(ns, attr, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSetManDouble     => validatorSet(ns, attr, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSetManBoolean    => validatorSet(ns, attr, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetManBigInt     => validatorSet(ns, attr, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetManBigDecimal => validatorSet(ns, attr, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetManDate       => validatorSet(ns, attr, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSetManUUID       => validatorSet(ns, attr, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSetManURI        => validatorSet(ns, attr, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSetManByte       => validatorSet(ns, attr, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSetManShort      => validatorSet(ns, attr, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSetManChar       => validatorSet(ns, attr, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  def validatorOptSet[T](
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
      case a: AttrSetOptString     => validatorOptSet(ns, attr, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSetOptInt        => validatorOptSet(ns, attr, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSetOptLong       => validatorOptSet(ns, attr, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSetOptFloat      => validatorOptSet(ns, attr, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSetOptDouble     => validatorOptSet(ns, attr, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSetOptBoolean    => validatorOptSet(ns, attr, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetOptBigInt     => validatorOptSet(ns, attr, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetOptBigDecimal => validatorOptSet(ns, attr, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetOptDate       => validatorOptSet(ns, attr, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSetOptUUID       => validatorOptSet(ns, attr, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSetOptURI        => validatorOptSet(ns, attr, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSetOptByte       => validatorOptSet(ns, attr, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSetOptShort      => validatorOptSet(ns, attr, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSetOptChar       => validatorOptSet(ns, attr, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }
}