package molecule.db.common.transaction.plan

import scala.collection.mutable

// Logical model for planned execution

sealed trait Cardinality
case object ManyToOne extends Cardinality
case object OneToMany extends Cardinality

// isFk marks columns that are fulfilled by FK injection (not by tuple binders)
final case class AttrCol(ent: String, attr: String, typeCast: String = "", isFk: Boolean = false)

final case class NodeId(id: Int) extends AnyVal

final case class PlanNode(
  id: NodeId,
  ent: String,
  attrs: mutable.ArrayBuffer[AttrCol] = mutable.ArrayBuffer.empty
)

sealed trait PlanEdge {
  def from: NodeId
  def to: NodeId
  def card: Cardinality
}
final case class EdgeM2O(from: NodeId, to: NodeId, fkAttr: String) extends PlanEdge { val card: Cardinality = ManyToOne }
final case class EdgeO2M(from: NodeId, to: NodeId, reverseFkAttr: String) extends PlanEdge { val card: Cardinality = OneToMany }

// Navigation backref marker (reserved for future use if we need to annotate)
final case class BackRefTarget(target: NodeId)

final case class LogicalPlan(
  nodes: Vector[PlanNode],
  edges: Vector[PlanEdge],
  backrefs: Vector[BackRefTarget]
)
