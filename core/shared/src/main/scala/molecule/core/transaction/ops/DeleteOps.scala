package molecule.core.transaction.ops

import molecule.boilerplate.ast.Model.Element

trait DeleteOps {

  protected def addIds(ids: Seq[String]): Unit

  protected def addFilterElement(element: Element): Unit
}