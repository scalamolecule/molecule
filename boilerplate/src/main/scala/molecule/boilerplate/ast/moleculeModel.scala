package molecule.boilerplate.ast

import scala.util.Random

/** AST for molecule Model representation. <br><br> Molecule transforms custom
 * boilerplate DSL constructs to Datomic queries in 3 steps: <br><br> Custom
 * DSL molecule --> MoleculeModel --> Query --> Datomic query string
 */
object moleculeModel {

  //  case class PreQuery(query0: String, rules0: String)
  case class PreQuery(preQuery: String, preInputs: Seq[AnyRef])

  /** Molecule Model representation. <br><br> Molecule transforms custom
   * boilerplate DSL constructs to Datomic queries in 3 steps: <br><br> Custom
   * DSL molecule --> Model --> Query --> Datomic query string <br><br> Model
   * is thus derived from custom meta-DSL constructs ("molecules").
   *
   * @param elements
   * Elements of the model
   */
  case class Model(elements: Seq[Element]) {

    override def toString: String = {
      def draw(elements: Seq[Element], indent: Int): Seq[String] = {
        val s = "  " * indent
        elements map {
          case Nested(bond, nestedElements) =>
            s"""|Nested(
                |$s  $bond,
                |$s  List(
                |$s    ${
              draw(nestedElements, indent + 2).mkString(
                s",\n$s    "
              )
            }))""".stripMargin
          case TxMetaData(nestedElements)   =>
            s"""|TxMetaData(List(
                |$s  ${
              draw(nestedElements, indent + 1).mkString(
                s",\n$s  "
              )
            }))""".stripMargin
          case Composite(elements)          =>
            s"""|Composite(List(
                |$s  ${
              draw(elements, indent + 1).mkString(
                s",\n$s  "
              )
            }))""".stripMargin
          case other                        => s"$other"
        }
      }
      draw(elements, 1).mkString("Model(List(\n  ", ",\n  ", "))")
    }
  }

  sealed trait Element

  case class MoleculeModel(elements: Seq[Element]) //extends Element

  //  sealed trait GenericAtom extends Element {
  //    val ns   : String
  //    val attr : String
  //    val mode : String // "man": mandatory, "opt": optional, "tac": tacit
  //    val tpe  : String
  //    val card : Int // 1: card-one, 2: card-many, 3: card-map
  //    val group: String
  //    val value: Op
  //    val sort : String
  //  }

  //  case class Generic(
  //      override val ns: String,
  //      override val attr: String,
  //      override val mode: String,
  //      override val tpe: String,
  //      override val group: String,
  //      override val value: ModelValue,
  //      override val sort: String = ""
  //  ) extends GenericAtom {
  //    override val card: Int        = 1
  //    override def toString: String =
  //      s"""Generic("$ns", "$attr", "$mode", "$tpe", "$group", $value, "$sort")"""
  //  }
  //
  //  case class Atom(
  //      override val ns: String,
  //      override val attr: String,
  //      override val mode: String,
  //      override val tpe: String,
  //      override val card: Int,
  //      override val group: String,
  //      override val value: ModelValue,
  //      override val sort: String = ""
  //  ) extends GenericAtom {
  //    override def toString: String =
  //      s"""Atom("$ns", "$attr", "$mode", "$tpe", $card, "$group", $value, "$sort")"""
  //  }

  sealed trait Atom extends Element {
    val ns: String
    val attr: String
    val op: Op
    val sort: String
    def unapply(a: Atom): (String, String, Op) = (a.ns, a.attr, a.op)
  }

  sealed trait AtomMandatory extends Atom

  case class AtomManString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[String] = Nil,
    defaultValue: String = "<empty string>",
    validation: Option[String => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomMandatory

  abstract class ValidateInt {
    def validate(int: Int): Either[String, Boolean]
  }
  case class AtomManInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Int] = Nil,
    defaultValue: () => Int = { () => Random.nextInt(2)},
    validation: Option[Int => Either[String, Boolean]] = None,
//    validatiox: Option[ValidateInt] = None,
    sort: String = ""
  ) extends AtomMandatory

  case class AtomManDouble(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Seq[Double] = Nil,
    validation: Option[Double => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomMandatory


  sealed trait AtomTacit extends Atom

  case class AtomTacString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[String]] = None,
    validation: Option[Option[String] => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomTacit

  case class AtomTacInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Int]] = None,
    validation: Option[Option[Int] => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomTacit


  sealed trait AtomOptional extends Atom

  case class AtomOptString(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[String]] = None,
    validation: Option[Option[String] => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomOptional

  case class AtomOptInt(
    ns: String,
    attr: String,
    op: Op = V,
    vs: Option[Seq[Int]] = None,
    validation: Option[Option[Int] => Either[String, Boolean]] = None,
    sort: String = ""
  ) extends AtomOptional

  //  case class AtomSetString()
  //  case class AtomOptSetString()
  //  case class AtomTacSetString()

  case class Bond(
    ns: String,
    refAttr: String,
    refNs: String = "",
    card: Int = 1
    //    gvs: Seq[GenericValue] = Nil
  ) extends Element {
    //    override def toString: String = s"""Bond("$ns", "$refAttr", "$refNs", $card, ${sq(gvs)})"""
  }

//  sealed trait F
//  case class

  case class ReBond(backRef: String) extends Element {
    override def toString: String = s"""ReBond("$backRef")"""
  }

  case class Nested(bond: Bond, elements: Seq[Element]) extends Element
  // todo?
  //  case class OptNested(bond: Bond, elements: Seq[Element]) extends Element

  case class TxMetaData(elements: Seq[Element]) extends Element
  case class Composite(elements: Seq[Element]) extends Element
  case object Self extends Element

  sealed trait Op

  // Value
  case object EntValue extends Op
  case object V extends Op

  case class BackValue(backNs: String) extends Op {
    override def toString: String = s"""BackValue("$backNs")"""
  }
  case object EnumVal extends Op
  case object IndexVal extends Op
  case object Unify extends Op

  // Aggregate
  case object Distinct extends Op

  case class Aggr(kw: String) extends Op {
    override def toString: String = s"""Aggr("$kw")"""
  }

  //  case class AggrN(kw: String, n: OneValue) extends ModelValue {
  case class AggrN(kw: String) extends Op {
    override def toString: String = s"""AggrN("$kw")"""
    //    override def toString: String = s"""AggrN("$kw", $n)"""
  }

  case class AggrCoalesce(kw: String) extends Op {
    override def toString: String = s"""AggrCoalesce("$kw")"""
  }

  //  // Function
    case class Fn(name: String, value: Option[Int] = None) extends Op {
  //    override def toString: String = s"""Fn("$name", ${o(value)})"""
    }

  // Exact matches. For card-many/map the whole Set/Map must match
  case object Eq extends Op
  case object Neq extends Op

  // Card-many/map matches for subsets in Set/Map
  // Misspelled to distinguish it from Scala Apply
  case object Appply extends Op
  case object Not extends Op

  // Matchers taking Seqs of Set/Map as argument
  case object Eq_ extends Op
  case object Neq_ extends Op
  case object Apply_ extends Op
  case object Not_ extends Op

  // Comparison
  case object Lt extends Op
  case object Le extends Op
  case object Gt extends Op
  case object Ge extends Op

  //
  //  case class Eq1(values: Values) extends ModelValue
  //  case class Eq2(sets: ValueSets) extends ModelValue {
  //    override def toString: String = s"Eq2($sets)"
  //  }
  //  case class Eq3(maps: ValueMaps) extends ModelValue {
  //    override def toString: String = s"Eq3($maps)"
  //  }
  //
  //  case class Neq1(values: Values) extends ModelValue
  //  case class Neq2(sets: ValueSets) extends ModelValue {
  //    override def toString: String = s"Neq2($sets)"
  //  }
  //  case class Neq3(maps: ValueMaps) extends ModelValue {
  //    override def toString: String = s"Neq3($maps)"
  //  }
  //
  //
  //  case class LtX(value: OneValue) extends ModelValue {
  //    override def toString: String = s"Lt($value)"
  //  }
  //  case class GtX(value: OneValue) extends ModelValue {
  //    override def toString: String = s"Gt($value)"
  //  }
  //  case class LeX(value: OneValue) extends ModelValue {
  //    override def toString: String = s"Le($value)"
  //  }
  //  case class GeX(value: OneValue) extends ModelValue {
  //    override def toString: String = s"Ge($value)"
  //  }
  //
  //  // Fulltext
  //  case class Fulltext(search: Values) extends ModelValue
  //
  //  // Card-many attribute operations
  //  case class AssertValue(values: Values) extends ModelValue {
  //    override def toString: String = s"AssertValue($values)"
  //  }
  //  case class ReplaceValue(oldNew: ValuePairs) extends ModelValue {
  //    override def toString: String = s"ReplaceValue($oldNew)"
  //  }
  //  case class RetractValue(values: Values) extends ModelValue {
  //    override def toString: String = s"RetractValue($values)"
  //  }
  //
  //  // Map attribute operations
  //  case class AssertMapPairs(kvs: ValuePairs) extends ModelValue {
  //    override def toString: String = s"AssertMapPairs($kvs)"
  //  }
  //  case class ReplaceMapPairs(kvs: ValuePairs) extends ModelValue {
  //    override def toString: String = s"ReplaceMapPairs($kvs)"
  //  }
  //  case class RetractMapKeys(keys: Values) extends ModelValue {
  //    override def toString: String = s"RetractMapKeys($keys)"
  //  }
  //  case class MapKeys(keys: Values) extends ModelValue {
  //    override def toString: String = s"MapKeys($keys)"
  //  }
  //
  //
  //  sealed trait GenericValue extends ModelValue
  //
  //  case object NoValue extends GenericValue
  //  case class Card(card: Int) extends GenericValue {
  //    override def toString: String = s"Card($card)"
  //  }
  //
  //  sealed trait Bidirectional extends GenericValue
  //
  //  case class BiSelfRef(card: Int) extends Bidirectional {
  //    override def toString: String = s"BiSelfRef($card)"
  //  }
  //  case class BiSelfRefAttr(card: Int) extends Bidirectional {
  //    override def toString: String = s"BiSelfRefAttr($card)"
  //  }
  //
  //  case class BiOtherRef(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiOtherRef($card, "$attr")"""
  //  }
  //  case class BiOtherRefAttr(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiOtherRefAttr($card, "$attr")"""
  //  }
  //
  //  case object BiEdge extends Bidirectional
  //  case class BiEdgeRef(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiEdgeRef($card, "$attr")"""
  //  }
  //  case class BiEdgeRefAttr(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiEdgeRefAttr($card, "$attr")"""
  //  }
  //
  //  case class BiEdgePropAttr(card: Int) extends Bidirectional {
  //    override def toString: String = s"BiEdgePropAttr($card)"
  //  }
  //  case class BiEdgePropRefAttr(card: Int) extends Bidirectional {
  //    override def toString: String = s"BiEdgePropRefAttr($card)"
  //  }
  //  case class BiEdgePropRef(card: Int) extends Bidirectional {
  //    override def toString: String = s"BiEdgePropRef($card)"
  //  }
  //
  //  case class BiTargetRef(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiTargetRef($card, "$attr")"""
  //  }
  //  case class BiTargetRefAttr(card: Int, attr: String) extends Bidirectional {
  //    override def toString: String = s"""BiTargetRefAttr($card, "$attr")"""
  //  }
}
