package molecule.db.common.transaction.ops

import molecule.core.dataModel.Element

trait DeleteOps {

  protected def addIds(ids: Seq[Long]): Unit

  protected def addFilterElement(element: Element): Unit
}