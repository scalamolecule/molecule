package molecule.boilerplate.ast

import molecule.base.ast.SchemaAST._


trait ModelBase extends Validations {

  trait Element

//  case class MoleculeModel(elements: Seq[Element]) //extends Element

  trait Atom extends Element {
    val ns  : String
    val attr: String
    val op  : Op
    val sort: String
    def unapply(a: Atom): (String, String, Op) = (a.ns, a.attr, a.op)
  }
  trait AtomOne extends Atom
  trait AtomSet extends Atom
  trait AtomArr extends Atom
  trait AtomMap extends Atom

  case class Bond(
    ns: String,
    refAttr: String,
    refNs: String = "",
    card: Cardinality = one
  ) extends Element {
    //    override def toString: String = s"""Bond("$ns", "$refAttr", "$refNs", $card, ${sq(gvs)})"""
  }

  case class ReBond(backRef: String) extends Element {
    override def toString: String = s"""ReBond("$backRef")"""
  }

  case class Nested(bond: Bond, elements: Seq[Element]) extends Element
  // todo?
  //  case class OptNested(bond: Bond, elements: Seq[Element]) extends Element

  case class TxMetaData(elements: Seq[Element]) extends Element
  case class Composite(elements: Seq[Element]) extends Element
  case object Self extends Element

  trait Op

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
