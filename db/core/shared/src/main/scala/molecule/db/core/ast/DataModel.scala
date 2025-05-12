package molecule.db.core.ast
import java.util.BitSet as jBitSet

case class DataModel(
  elements: List[Element],
  attrIndexes: Set[Int] = Set.empty[Int]
) {

  def add(attr: Attr): DataModel = {
    copy(elements :+ attr, attrIndexes + attr.coord(1))
  }
}

