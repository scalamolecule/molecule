package molecule.document.mongodb.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr

trait ResolveExprSetRefAttr extends ResolveExpr with LambdasSet { self: MongoQueryBase =>

  // Not used with Mongo
  override protected def resolveRefAttrSetMan(attr: AttrSetMan): Unit = ()
  override protected def resolveRefAttrSetTac(attr: AttrSetTac): Unit = ()
  override protected def resolveRefAttrSetOpt(attr: AttrSetOpt): Unit = ()
}
