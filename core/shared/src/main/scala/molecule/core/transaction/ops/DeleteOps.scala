package molecule.core.transaction.ops

import molecule.boilerplate.ast.Model.Element

trait DeleteOps {

  protected def addIds[T](ids: Seq[T]): Unit

  protected def addFilterElement(element: Element): Unit
}