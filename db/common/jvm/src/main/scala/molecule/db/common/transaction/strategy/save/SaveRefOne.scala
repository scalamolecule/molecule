package molecule.db.common.transaction.strategy.save

import java.sql.PreparedStatement as PS
import scala.collection.mutable.ListBuffer
import molecule.db.common.transaction.strategy.SqlOps

case class SaveRefOne(
  parent: SaveAction,
  sqlOps: SqlOps,
  ent: String,
  refAttr: String,
  ref: String,
  refAttrIndex: Int
) extends SaveAction(parent, sqlOps, ref) {

  rowSetters += ListBuffer.empty[PS => Unit]

  override def rootAction: SaveAction = parent.rootAction

//  def refEntity = this

//  override def process(): Unit = {
//    children.foreach(_.process())
//    insert()
//
//    parent.rowSetters.last += {
//      (ps: PS) => ps.setLong(refAttrIndex, ids.head)
//    }
//  }

  override def process(): Unit = {
    // Process any children first (rare in save, but consistent)
    children.foreach(_.process())

    // Insert the referenced row (e.g. B)
    insert()

    // Set FK on the parent side (ManyToOne A.b -> B.id)
    parent.rowSetters.last += { (ps: PS) =>
      ps.setLong(refAttrIndex, ids.head)
    }

    // Reverse handoff for OneToMany (B -> C): inject B.id into C.b
    reverse.foreach { case (manySide, fkIdx) =>
      // Ensure many-side has a row to receive setters
      if (manySide.rowSetters.isEmpty)
        manySide.rowSetters += ListBuffer.empty[PS => Unit]

      // Add FK setter for C.b
      manySide.rowSetters.last += { (ps: PS) =>
        ps.setLong(fkIdx, ids.head)
      }
    }
  }


  override def curStmt: String = {
    sqlOps.insertStmt(ref, cols, placeHolders)
  }

  override def render(indent: Int): String = {
    recurseRender(indent, "RefOne")
  }
}
