package molecule.db.common.transaction.plan.render

import molecule.core.dataModel.DataModel
import molecule.db.common.transaction.plan.*
import molecule.db.common.transaction.plan.phase1.ModelPlanner

object PlanRenderer {
  def render(dataModel: DataModel): String = {
    val plan = ModelPlanner.build(dataModel.elements)
    render(dataModel, plan)
  }
  def render(dataModel: DataModel, plan: LogicalPlan): String = {
    val nodesSec = plan.nodes.map { n =>
      val colsPretty = n.attrs.map(ac => if (ac.isFk) s"${ac.attr}[FK]" else ac.attr).mkString(", ")
      val colNames   = n.attrs.map(_.attr).mkString(", ")
      val phs        = n.attrs.map(ac => "?" + (if (ac.typeCast == null || ac.typeCast.isEmpty) "" else ac.typeCast)).mkString(", ")
      val sqlPreview = if (n.attrs.nonEmpty)
        s"INSERT INTO ${n.ent} ($colNames) VALUES ($phs)"
      else
        s"-- no columns for ${n.ent}"
      s"  ${n.ent} ($colsPretty)\n    $sqlPreview"
    }.mkString("\n\n")

    val edgesSec = plan.edges.map {
      case EdgeM2O(from, to, fkAttr) =>
        s"  M2O: ${plan.nodes(from.id).ent} --- $fkAttr --> ${plan.nodes(to.id).ent}"
      case EdgeO2M(from, to, reverseFkAttr) =>
        s"  O2M: ${plan.nodes(from.id).ent} <-- $reverseFkAttr --- ${plan.nodes(to.id).ent}"
    }.mkString("\n")

    val m2oTargets = plan.edges.collect { case EdgeM2O(_, to, _) => plan.nodes(to.id).ent }.distinct
    val o2mEdges   = plan.edges.collect { case e: EdgeO2M => e }
    val steps      = new StringBuilder

    if (m2oTargets.nonEmpty)
      steps ++= s"  Insert M2O targets: ${m2oTargets.mkString(", ")}\n"

    if (o2mEdges.nonEmpty)
      o2mEdges.foreach { e =>
        steps ++= s"  Inject FK ${e.reverseFkAttr} into ${plan.nodes(e.to.id).ent} from ${plan.nodes(e.from.id).ent}.ids\n"
      }

    val insertedParents = o2mEdges.map(e => plan.nodes(e.from.id).ent).toSet
    val preInserted     = m2oTargets.toSet
    val remaining       = plan.nodes.map(_.ent).filterNot(preInserted ++ insertedParents)

    if (remaining.nonEmpty)
      steps ++= s"  Insert remaining nodes: ${remaining.mkString(", ")}\n"

    s"""Insertion plan ==============================
$dataModel

Nodes:
$nodesSec

Edges:
$edgesSec

Execution:
${steps.toString}"""
  }
}
