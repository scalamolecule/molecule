package molecule.core.transaction.ops

import molecule.boilerplate.ast.DataModel.Element

trait DeleteOps {

  protected def addIds(ids: Seq[Long]): Unit

  protected def addFilterElement(element: Element): Unit
}