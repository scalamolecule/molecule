package molecule.db.datalog.core.query.casting

import java.util.{Iterator as jIterator, Map as jMap}
import molecule.core.ast.DataModel.{Element, Ref}
import molecule.db.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery, ResolveOptRefPull}


trait OptRefNested[Tpl]
  extends CastOptRefBranch_
    with CastOptRefLeaf_
    with CastRow2AnyTpl_
    with DatomicQueryBase
    with ResolveOptRefPull[Tpl] { self: Model2DatomicQuery[Tpl] =>

  protected def pullOptRefData(
    ref: Ref, optionalElements: List[Element]
  ): AnyRef => AnyRef = {
    val nestedOptRefCasts = resolveOptRefElements(ref, optionalElements)
    val caster            = rec(nestedOptRefCasts, refDepths)
    (rowValue: AnyRef) => {
      // println(s"===  ${nestedOptRefCasts.size}   " + rowValue)
      caster(rowValue.asInstanceOf[jMap[?, ?]].values.iterator)
    }
  }

  def rec(
    castss: List[List[jIterator[?] => Any]],
    refDepths: List[Int],
    leaf: jIterator[?] => Option[Any] = null // when leaf materializes
  ): jIterator[?] => Option[Any] = {
    castss match {
      case casts :: Nil  => rec(Nil, Nil, pullOptRefLeaf(casts))
      case casts :: more => pullOptRefBranch(casts, rec(more, refDepths.tail), refDepths.head)
      case Nil           => leaf
    }
  }
}