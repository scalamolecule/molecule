package molecule.core.ast

case class DataModel(
  elements: List[Element],
  attrIndexes: Set[Int] = Set.empty[Int],
  firstEntityIndex: Int = -1,
  binds: Int = 0,
  inputElements: List[Element] = Nil,
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

  override def toString: String = {
    val elems = if (elements.isEmpty) "" else
      elements.map(_.render(2)).mkString("\n", ",\n", "\n  ")

    val input = if (inputElements.isEmpty) " Nil" else
      inputElements.map(_.render(2)).mkString("\n  List(\n", ",\n", "\n  )")

    s"""DataModel(
       |  List($elems),
       |  $attrIndexes, $firstEntityIndex, $binds,$input
       |)""".stripMargin
  }
}