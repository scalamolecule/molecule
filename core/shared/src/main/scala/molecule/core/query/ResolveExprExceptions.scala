package molecule.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._

trait ResolveExprExceptions {

  protected def unexpectedElement(element: Element): Nothing =
    throw ModelError("Unexpected element: " + element)

  protected def unexpectedOp(op: Op): Nothing =
    throw ModelError("Unexpected operation: " + op)

  protected def unexpectedKw(kw: String): Nothing =
    throw ModelError("Unexpected keyword: " + kw)


  protected def noMixedNestedModes: Nothing = throw ModelError(
    "Can't mix mandatory/optional nested queries."
  )

  def noCollectionMatching(attr: Attr): Nothing = {
    val a = attr.cleanName
    throw ModelError(s"Matching collections ($a) not supported in queries.")
  }

  def noApplyNothing(attr: Attr): Nothing = {
    val a = attr.cleanName
    throw ModelError(
      s"Applying nothing to mandatory attribute ($a) is reserved for updates to retract."
    )
  }

  def noCardManyFilterAttrExpr(attr: Attr): Nothing = {
    throw ModelError(
      s"Cardinality-many filter attributes not allowed to do additional filtering (${attr.name}).")
  }
}
