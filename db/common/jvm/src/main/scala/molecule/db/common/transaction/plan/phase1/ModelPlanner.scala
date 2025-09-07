package molecule.db.common.transaction.plan.phase1

import molecule.core.dataModel.*
import molecule.core.dataModel.ManyToOne
import molecule.db.common.transaction.plan.*

object ModelPlanner {

  def build(elements: List[Element]): LogicalPlan = {
    val planner = new Planner

    val firstEnt =
      elements.collectFirst { case a: Attr => a.ent }
        .orElse(elements.collectFirst { case Ref(ent, _, _, _, _, _, _) => ent })
        .getOrElse(throw new IllegalStateException("No entity found in insert elements."))

    planner.startEntity(firstEnt)

    // Recursive planner for elements
    def planElements(els: List[Element]): Unit =
      els.foreach(planElement)

    def planElement(el: Element): Unit = el match {
      case a: Attr =>
        // No typeCast needed here; default empty is fine
        planner.addAttr(a.ent, a.attr)

      case Ref(_, refAttr, ref, relationship, _, _, reverseRefAttr) =>
        relationship match {
          case ManyToOne =>
            planner.addRef_manyToOne(ref, refAttr)
          case _ =>
            val fk = reverseRefAttr.getOrElse(
              throw new IllegalStateException(s"Missing reverse ref attr for one-to-many to $ref")
            )
            planner.addRef_oneToMany(ref, fk)
        }

      case OptRef(Ref(_, refAttr, ref, relationship, _, _, reverseRefAttr), inner) =>
        // Optional nested ref: add edge to target node, recurse into inner elements, then return
        relationship match {
          case ManyToOne =>
            planner.addRef_manyToOne(ref, refAttr)
          case _ =>
            val fk = reverseRefAttr.getOrElse(
              throw new IllegalStateException(s"Missing reverse ref attr for one-to-many to $ref")
            )
            planner.addRef_oneToMany(ref, fk)
        }
        planElements(inner)
        // Return to parent after finishing optional ref group
        planner.backRef()

      case Nested(Ref(_, refAttr, ref, relationship, _, _, reverseRefAttr), inner) =>
        // Non-optional nested ref: same planning as OptRef for edges/backtracking
        relationship match {
          case ManyToOne =>
            planner.addRef_manyToOne(ref, refAttr)
          case _ =>
            val fk = reverseRefAttr.getOrElse(
              throw new IllegalStateException(s"Missing reverse ref attr for one-to-many to $ref")
            )
            planner.addRef_oneToMany(ref, fk)
        }
        planElements(inner)
        planner.backRef()

      case OptNested(Ref(_, refAttr, ref, relationship, _, _, reverseRefAttr), inner) =>
        // Optional nested ref behaves like Nested for planning
        relationship match {
          case ManyToOne =>
            planner.addRef_manyToOne(ref, refAttr)
          case _ =>
            val fk = reverseRefAttr.getOrElse(
              throw new IllegalStateException(s"Missing reverse ref attr for one-to-many to $ref")
            )
            planner.addRef_oneToMany(ref, fk)
        }
        planElements(inner)
        planner.backRef()

      case BackRef(_, _, _) =>
        planner.backRef()

      case OptEntity(attrs) =>
        attrs.foreach(a => planner.addAttr(a.ent, a.attr))

      case _ => ()
    }

    // Kick off recursive planning from the top-level elements
    planElements(elements)

    planner.build()
  }
}
