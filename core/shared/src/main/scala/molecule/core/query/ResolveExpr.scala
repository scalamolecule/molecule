package molecule.core.query

import molecule.boilerplate.ast.Model._

trait ResolveExpr {

  // Implemented in database modules

  protected def resolveAttrOneMan(attr: AttrOneMan): Unit
  protected def resolveAttrOneTac(attr: AttrOneTac): Unit
  protected def resolveAttrOneOpt(attr: AttrOneOpt): Unit

  protected def resolveAttrSetMan(attr: AttrSetMan): Unit
  protected def resolveAttrSetTac(attr: AttrSetTac): Unit
  protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit

  protected def resolveRefAttrSetMan(attr: AttrSetMan): Unit
  protected def resolveRefAttrSetTac(attr: AttrSetTac): Unit
  protected def resolveRefAttrSetOpt(attr: AttrSetOpt): Unit

  protected def resolveAttrSeqMan(attr: AttrSeqMan): Unit
  protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit
  protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit

  protected def resolveAttrMapMan(attr: AttrMapMan): Unit
  protected def resolveAttrMapTac(attr: AttrMapTac): Unit
  protected def resolveAttrMapOpt(attr: AttrMapOpt): Unit
}
