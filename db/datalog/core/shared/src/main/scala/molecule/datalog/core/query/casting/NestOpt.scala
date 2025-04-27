package molecule.datalog.core.query.casting

import java.util.{Iterator as jIterator, Map as jMap}
import molecule.datalog.core.query.DatomicQueryBase


trait NestOpt
  extends CastOptNestedBranch_
    with CastOptNestedLeaf_
    with CastRow2AnyTpl_
    with DatomicQueryBase {

  private lazy val levels = pullCastss.length

  private lazy val pullCasts1 = pullCastss.head
  private lazy val pullCasts2 = pullCastss(1)
  private lazy val pullCasts3 = pullCastss(2)
  private lazy val pullCasts4 = pullCastss(3)
  private lazy val pullCasts5 = pullCastss(4)
  private lazy val pullCasts6 = pullCastss(5)
  private lazy val pullCasts7 = pullCastss(6)

  private lazy val pullSorts1 = pullSortss.head
  private lazy val pullSorts2 = pullSortss(1)
  private lazy val pullSorts3 = pullSortss(2)
  private lazy val pullSorts4 = pullSortss(3)
  private lazy val pullSorts5 = pullSortss(4)
  private lazy val pullSorts6 = pullSortss(5)
  private lazy val pullSorts7 = pullSortss(6)

  private lazy val pullBranch1: jIterator[?] => List[Any] = {
    if (levels == 1)
      pullOptNestedLeaf(pullCasts1, pullSorts1)
    else
      pullOptNestedBranch(pullCasts1, pullSorts1, pullBranch2, refDepths(1))
  }

  private lazy val pullBranch2: jIterator[?] => List[Any] = {
    if (levels == 2)
      pullOptNestedLeaf(pullCasts2, pullSorts2)
    else
      pullOptNestedBranch(pullCasts2, pullSorts2, pullBranch3, refDepths(2))
  }

  private lazy val pullBranch3: jIterator[?] => List[Any] = {
    if (levels == 3)
      pullOptNestedLeaf(pullCasts3, pullSorts3)
    else
      pullOptNestedBranch(pullCasts3, pullSorts3, pullBranch4, refDepths(3))
  }

  private lazy val pullBranch4: jIterator[?] => List[Any] = {
    if (levels == 4)
      pullOptNestedLeaf(pullCasts4, pullSorts4)
    else
      pullOptNestedBranch(pullCasts4, pullSorts4, pullBranch5, refDepths(4))
  }

  private lazy val pullBranch5: jIterator[?] => List[Any] = {
    if (levels == 5)
      pullOptNestedLeaf(pullCasts5, pullSorts5)
    else
      pullOptNestedBranch(pullCasts5, pullSorts5, pullBranch6, refDepths(5))
  }

  private lazy val pullBranch6: jIterator[?] => List[Any] = {
    if (levels == 6)
      pullOptNestedLeaf(pullCasts6, pullSorts6)
    else
      pullOptNestedBranch(pullCasts6, pullSorts6, pullBranch7, refDepths(6))
  }

  private lazy val pullBranch7: jIterator[?] => List[Any] = {
    pullOptNestedLeaf(pullCasts7, pullSorts7)
  }

  protected lazy val pullOptNestedData: AnyRef => AnyRef = {
    (rowValue: AnyRef) => pullBranch1(rowValue.asInstanceOf[jMap[?, ?]].values.iterator)
  }
}