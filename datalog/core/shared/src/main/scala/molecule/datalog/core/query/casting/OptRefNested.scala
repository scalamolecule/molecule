package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.boilerplate.ast.Model.{Element, Ref}
import molecule.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery, ResolveOptRefPull}


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
      caster(rowValue.asInstanceOf[jMap[_, _]].values.iterator)
    }
  }

  def rec(
    castss: List[List[jIterator[_] => Any]],
    refDepths: List[Int],
    leaf: jIterator[_] => Option[Any] = null // when leaf materializes
  ): jIterator[_] => Option[Any] = {
    castss match {
      case casts :: Nil  => rec(Nil, Nil, pullOptRefLeaf(casts))
      case casts :: more => pullOptRefBranch(casts, rec(more, refDepths.tail), refDepths.head)
      case Nil           => leaf
    }
  }
}