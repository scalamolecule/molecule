package molecule.db.core.transaction.ops

import molecule.core.ast.Element

trait DeleteOps {

  protected def addIds(ids: Seq[Long]): Unit

  protected def addFilterElement(element: Element): Unit
}