package molecule.core.validation.insert

import molecule.base.ast.SchemaAST.MetaNs
import molecule.base.error.{InsertError, ModelError}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.InsertValidators_
import molecule.core.util.ModelUtils
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait InsertValidationExtraction extends InsertValidators_ with ModelUtils { self: InsertValidationResolvers_ =>

  private var curElements: List[Element]      = List.empty[Element]
  private val prevRefs   : ListBuffer[AnyRef] = ListBuffer.empty[AnyRef]

  @tailrec
  final override def getValidators(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    validators: List[Product => Seq[InsertError]],
    outerTpl: Int,
    tplIndex: Int
  ): List[Product => Seq[InsertError]] = {
    if (validators.isEmpty)
      curElements = elements
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
                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneOpt => getValidators(nsMap, tail, validators :+
                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                  getValidators(nsMap, tail, validators :+
                    resolveAttrSetMan(a, outerTpl, tplIndex, mandatory), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => getValidators(nsMap, tail, validators :+
                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a          => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(_, refAttr, _, _) =>
          prevRefs += refAttr
          getValidators(nsMap, tail, validators, outerTpl, tplIndex)

        case BackRef(backRefNs) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          getValidators(nsMap, tail, validators, outerTpl, tplIndex)

        case Composite(compositeElements) =>
          curElements = compositeElements
          getValidators(nsMap, tail, validators :+
            addComposite(nsMap, outerTpl, tplIndex, compositeElements), outerTpl + 1, tplIndex + 1)

        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
          curElements = nestedElements
          prevRefs.clear()
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
          curElements = nestedElements
          prevRefs.clear()
          getValidators(nsMap, tail, validators :+
            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)

        // TxMetaData is handed separately in Insert_stmts with call to save_stmts

        case other => throw ModelError("Unexpected element: " + other)
      }
      case Nil             => validators
    }
  }

  private def addComposite(
    nsMap: Map[String, MetaNs],
    tpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Seq[InsertError] = {
    val validate = getInsertValidator(nsMap, compositeElements, tpl)
    countValueAttrs(compositeElements) match {
      case 1 => (tpl: Product) => validate(Tuple1(tpl.productElement(tplIndex)))
      case _ => (tpl: Product) => validate(tpl.productElement(tplIndex).asInstanceOf[Product])
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
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, 0, fullRefAttr, Nil, indexedErrors))
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls    = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val indexedErrors = nestedTpls.zipWithIndex.flatMap { case (nestedTpl, rowIndex) =>
            val rowErrors: Seq[InsertError] = validate(nestedTpl)
            if (rowErrors.isEmpty) None else Some((rowIndex, rowErrors))
          }
          if (indexedErrors.isEmpty) Nil else Seq(InsertError(0, 0, fullRefAttr, Nil, indexedErrors))
        }
    }
  }

  def validatorV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        val errors = validator(tpl)(tpl.productElement(tplIndex).asInstanceOf[T])
        if (errors.isEmpty) {
          Nil
        } else {
          Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManString     => validatorV(ns, attr, outerTpl, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneManInt        => validatorV(ns, attr, outerTpl, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneManLong       => validatorV(ns, attr, outerTpl, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneManFloat      => validatorV(ns, attr, outerTpl, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneManDouble     => validatorV(ns, attr, outerTpl, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneManBoolean    => validatorV(ns, attr, outerTpl, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneManBigInt     => validatorV(ns, attr, outerTpl, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneManBigDecimal => validatorV(ns, attr, outerTpl, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneManDate       => validatorV(ns, attr, outerTpl, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneManUUID       => validatorV(ns, attr, outerTpl, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneManURI        => validatorV(ns, attr, outerTpl, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneManByte       => validatorV(ns, attr, outerTpl, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneManShort      => validatorV(ns, attr, outerTpl, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneManChar       => validatorV(ns, attr, outerTpl, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }


  def validatorOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold((_: Product) => Seq.empty[InsertError]) { validator =>
      (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case Some(value) =>
            val errors = validator(tpl)(value.asInstanceOf[T])
            if (errors.isEmpty) Nil else
              Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
          case None        => Nil
        }
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptString     => validatorOptV(ns, attr, outerTpl, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrOneOptInt        => validatorOptV(ns, attr, outerTpl, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrOneOptLong       => validatorOptV(ns, attr, outerTpl, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrOneOptFloat      => validatorOptV(ns, attr, outerTpl, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrOneOptDouble     => validatorOptV(ns, attr, outerTpl, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrOneOptBoolean    => validatorOptV(ns, attr, outerTpl, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrOneOptBigInt     => validatorOptV(ns, attr, outerTpl, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrOneOptBigDecimal => validatorOptV(ns, attr, outerTpl, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrOneOptDate       => validatorOptV(ns, attr, outerTpl, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrOneOptUUID       => validatorOptV(ns, attr, outerTpl, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrOneOptURI        => validatorOptV(ns, attr, outerTpl, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrOneOptByte       => validatorOptV(ns, attr, outerTpl, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrOneOptShort      => validatorOptV(ns, attr, outerTpl, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrOneOptChar       => validatorOptV(ns, attr, outerTpl, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }

  def noEmptySet(ns: String, attr: String, outerTpl: Int, tplIndex: Int) = Seq(
    InsertError(outerTpl, tplIndex, ns + "." + attr,
      Seq("Can't insert empty Set for mandatory attribute"), Nil)
  )

  def validatorSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    mandatory: Boolean,
    optValidator: Option[Product => T => Seq[String]],
  ): Product => Seq[InsertError] = {
    optValidator.fold {
      (tpl: Product) =>
        if (mandatory && tpl.productElement(tplIndex).asInstanceOf[Set[_]].isEmpty)
          noEmptySet(ns, attr, outerTpl, tplIndex) else Nil

    } { validator =>
      (tpl: Product) =>
        println(mandatory)
        println(tpl)
        val validate = validator(tpl)
        val set      = tpl.productElement(tplIndex).asInstanceOf[Set[_]]
        if (mandatory && set.isEmpty) {
          noEmptySet(ns, attr, outerTpl, tplIndex)
        } else {
          val errors = set.flatMap(value => validate(value.asInstanceOf[T])).toSeq
          if (errors.isEmpty) Nil else
            Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
        }
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan, outerTpl: Int, tplIndex: Int, mandatory: Boolean): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManString     => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorString(a.validator, a, curElements))
      case a: AttrSetManInt        => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorInt(a.validator, a, curElements))
      case a: AttrSetManLong       => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorLong(a.validator, a, curElements))
      case a: AttrSetManFloat      => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorFloat(a.validator, a, curElements))
      case a: AttrSetManDouble     => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorDouble(a.validator, a, curElements))
      case a: AttrSetManBoolean    => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetManBigInt     => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetManBigDecimal => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetManDate       => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorDate(a.validator, a, curElements))
      case a: AttrSetManUUID       => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorUUID(a.validator, a, curElements))
      case a: AttrSetManURI        => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorURI(a.validator, a, curElements))
      case a: AttrSetManByte       => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorByte(a.validator, a, curElements))
      case a: AttrSetManShort      => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorShort(a.validator, a, curElements))
      case a: AttrSetManChar       => validatorSet(ns, attr, outerTpl, tplIndex, mandatory, validatorChar(a.validator, a, curElements))
    }
  }

  def validatorOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
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
              Seq(InsertError(outerTpl, tplIndex, ns + "." + attr, errors, Nil))
          case None              => Nil
        }
    }
  }
  private def resolveAttrSetOpt(a: AttrSetOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetOptString     => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorString(a.validator, a, curElements))
      case a: AttrSetOptInt        => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorInt(a.validator, a, curElements))
      case a: AttrSetOptLong       => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorLong(a.validator, a, curElements))
      case a: AttrSetOptFloat      => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorFloat(a.validator, a, curElements))
      case a: AttrSetOptDouble     => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorDouble(a.validator, a, curElements))
      case a: AttrSetOptBoolean    => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorBoolean(a.validator, a, curElements))
      case a: AttrSetOptBigInt     => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorBigInt(a.validator, a, curElements))
      case a: AttrSetOptBigDecimal => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorBigDecimal(a.validator, a, curElements))
      case a: AttrSetOptDate       => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorDate(a.validator, a, curElements))
      case a: AttrSetOptUUID       => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorUUID(a.validator, a, curElements))
      case a: AttrSetOptURI        => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorURI(a.validator, a, curElements))
      case a: AttrSetOptByte       => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorByte(a.validator, a, curElements))
      case a: AttrSetOptShort      => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorShort(a.validator, a, curElements))
      case a: AttrSetOptChar       => validatorOptSet(ns, attr, outerTpl, tplIndex, validatorChar(a.validator, a, curElements))
    }
  }
}