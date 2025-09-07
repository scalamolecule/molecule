package molecule.db.common.transaction.plan

import java.sql.PreparedStatement
import molecule.core.dataModel.*
import molecule.db.common.transaction.ValueTransformers
import molecule.db.common.transaction.plan.phase1.ModelPlanner
import molecule.db.common.transaction.plan.phase2.AttrCollector
import molecule.db.common.transaction.plan.phase3.RowShapeBuilder.RowShape
import molecule.db.common.transaction.plan.phase3.{MappingBuilder, RowShapeBuilder}
import molecule.db.common.transaction.plan.phase4.Binders
import molecule.db.common.transaction.strategy.SqlOps

// Adapter to plug the new planner/executor into the current insert path (wiring step will follow)

final class InsertEngine(sqlOps: SqlOps) extends ValueTransformers {

  // Orchestrator
  def run(elements: List[Element], tpls: Seq[Product]): List[Long] = {
    // Phase 1: Build logical plan from elements
    val plan = ModelPlanner.build(elements)

    // Phase 2: Collect Attr elements in order (for tuple index mapping)
    val attrElems = AttrCollector.collect(elements)

    // Phase 3: Build flat mapping for RowShapeBuilder
    val flatMapping = MappingBuilder.flatten(MappingBuilder.build(plan, attrElems))

    // Build per-node row shape for Nested/OptNested
    val shape = RowShapeBuilder.build(plan, flatMapping, tpls)

    // Phase 4: Build binders directly from DataModel AST
    val binders = Binders.build(plan, DataModel(elements), tpls, shape)

    // Phase 5: Execute
    new Executor(sqlOps).run(plan, shape.nodeRowCounts, shape.topIdxByNode, binders)
  }
}
