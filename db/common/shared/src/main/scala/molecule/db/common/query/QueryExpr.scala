package molecule.db.common.query

import molecule.core.dataModel.*

trait QueryExpr {

  // Implemented for each database

  protected def queryIdMan(attr: AttrOneMan): Unit
  protected def queryIdTac(attr: AttrOneTac): Unit

  protected def queryAttrOneMan(attr: AttrOneMan): Unit
  protected def queryAttrOneTac(attr: AttrOneTac): Unit
  protected def queryAttrOneOpt(attr: AttrOneOpt): Unit

  protected def queryAttrSetMan(attr: AttrSetMan): Unit
  protected def queryAttrSetTac(attr: AttrSetTac): Unit
  protected def queryAttrSetOpt(attr: AttrSetOpt): Unit

  protected def queryRefAttrSetMan(attr: AttrSetMan): Unit
  protected def queryRefAttrSetOpt(attr: AttrSetOpt): Unit
  protected def queryRefAttrSetTac(attr: AttrSetTac): Unit

  protected def queryAttrSeqMan(attr: AttrSeqMan): Unit
  protected def queryAttrSeqOpt(attr: AttrSeqOpt): Unit
  protected def queryAttrSeqTac(attr: AttrSeqTac): Unit

  protected def queryAttrMapMan(attr: AttrMapMan): Unit
  protected def queryAttrMapTac(attr: AttrMapTac): Unit
  protected def queryAttrMapOpt(attr: AttrMapOpt): Unit


  protected def queryRef(ref: Ref, tail: List[Element]): Unit
  protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit
  protected def queryOptRef(ref: Ref, optElements: List[Element]): Unit
  protected def queryOptEntity(optElements: List[Element]): Unit
  protected def queryNested(ref: Ref, nestedElements: List[Element]): Unit
  protected def queryOptNested(ref: Ref, nestedElements: List[Element]): Unit
}
