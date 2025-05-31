package molecule.db.core.ast

case class DataModel(
  elements: List[Element],
  attrIndexes: Set[Int] = Set.empty[Int],
  firstEntityIndex: Int = -1,
  binds: Int = 0
) {

  def add(attr: Attr): DataModel = {
    copy(elements :+ attr, attrIndexes + attr.coord(1))
  }

  def add(ref: Ref): DataModel = {
    copy(elements :+ ref, attrIndexes + ref.coord(1))
  }

  def add(backRef: BackRef): DataModel = {
    copy(elements :+ backRef, attrIndexes + backRef.coord(1))
  }
}