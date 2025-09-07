package molecule.db.common.transaction.plan

import scala.collection.mutable

// Planner: builds a logical plan from a linearized molecule

final class Planner {
  private val nodesBuf    = mutable.ArrayBuffer.empty[PlanNode]
  private val edgesBuf    = mutable.ArrayBuffer.empty[PlanEdge]
  private val backrefsBuf = mutable.ArrayBuffer.empty[BackRefTarget]
  private val navStack    = mutable.Stack.empty[NodeId]

  private def newNode(ent: String): NodeId = {
    val id = NodeId(nodesBuf.size)
    nodesBuf += PlanNode(id, ent)
    id
  }

  private def push(nodeId: NodeId): Unit = navStack.push(nodeId)
  private def cur: NodeId = navStack.headOption.getOrElse(
    throw new IllegalStateException("Navigation stack is empty while planning.")
  )
  private def pop(): NodeId = navStack.pop()

  // API used by the resolver to build the plan

  def startEntity(ent: String): NodeId = {
    val id = newNode(ent)
    push(id)
    id
  }

  def addAttr(ent: String, attr: String, typeCast: String = ""): Unit = {
    nodesBuf(cur.id).attrs += AttrCol(ent, attr, typeCast, isFk = false)
  }

  // ManyToOne: current ("from") entity holds FK column named fkAttr, referencing the new "to" entity
  def addRef_manyToOne(refEnt: String, fkAttr: String): NodeId = {
    // Add FK column to current node (fulfilled later by executor)
    nodesBuf(cur.id).attrs += AttrCol(nodesBuf(cur.id).ent, fkAttr, "", isFk = true)
    val to = newNode(refEnt)
    edgesBuf += EdgeM2O(cur, to, fkAttr)
    push(to)
    to
  }

  // OneToMany: the "to" (many-side) holds reverse FK column reverseFkAttr referencing current ("from")
  def addRef_oneToMany(refEnt: String, reverseFkAttr: String): NodeId = {
    val to = newNode(refEnt)
    // Add reverse FK column first on the many-side node
    nodesBuf(to.id).attrs += AttrCol(nodesBuf(to.id).ent, reverseFkAttr, "", isFk = true)
    edgesBuf += EdgeO2M(cur, to, reverseFkAttr)
    push(to)
    to
  }

  def backRef(): NodeId = {
    // Logical backref: pop back to previous entity when possible
    if (navStack.size > 1) pop()
    cur
  }

  def build(): LogicalPlan =
    LogicalPlan(nodesBuf.toVector, edgesBuf.toVector, backrefsBuf.toVector)
}
