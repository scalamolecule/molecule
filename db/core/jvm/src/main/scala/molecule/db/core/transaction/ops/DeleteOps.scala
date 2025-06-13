package molecule.db.core.transaction.ops

import molecule.core.dataModel.Element

trait DeleteOps {

  protected def addIds(ids: Seq[Long]): Unit

  protected def addFilterElement(element: Element): Unit
}