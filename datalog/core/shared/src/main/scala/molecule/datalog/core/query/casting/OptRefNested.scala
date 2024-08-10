package molecule.datalog.core.query.casting

import java.util.{Iterator => jIterator, Map => jMap}
import molecule.datalog.core.query.DatomicQueryBase


trait OptRefNested
  extends CastOptRefBranch_
    with CastOptRefLeaf_
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

  private lazy val pullBranch1: jIterator[_] => Option[Any] = {
    if (levels == 1)
      pullOptRefLeaf(pullCasts1)
    else
      pullOptRefBranch(pullCasts1, pullBranch2, refDepths(1))
  }

  private lazy val pullBranch2: jIterator[_] => Option[Any] = {
    if (levels == 2)
      pullOptRefLeaf(pullCasts2)
    else
      pullOptRefBranch(pullCasts2, pullBranch3, refDepths(2))
  }

  private lazy val pullBranch3: jIterator[_] => Option[Any] = {
    if (levels == 3)
      pullOptRefLeaf(pullCasts3)
    else
      pullOptRefBranch(pullCasts3, pullBranch4, refDepths(3))
  }

  private lazy val pullBranch4: jIterator[_] => Option[Any] = {
    if (levels == 4)
      pullOptRefLeaf(pullCasts4)
    else
      pullOptRefBranch(pullCasts4, pullBranch5, refDepths(4))
  }

  private lazy val pullBranch5: jIterator[_] => Option[Any] = {
    if (levels == 5)
      pullOptRefLeaf(pullCasts5)
    else
      pullOptRefBranch(pullCasts5, pullBranch6, refDepths(5))
  }

  private lazy val pullBranch6: jIterator[_] => Option[Any] = {
    if (levels == 6)
      pullOptRefLeaf(pullCasts6)
    else
      pullOptRefBranch(pullCasts6, pullBranch7, refDepths(6))
  }

  private lazy val pullBranch7: jIterator[_] => Option[Any] = {
    pullOptRefLeaf(pullCasts7)
  }

  protected def pullOptRefData: AnyRef => AnyRef = {
    val lambda = (rowValue: AnyRef) => {

      println("=== " + rowValue)
      //      println("=== " + rowValue.asInstanceOf[jMap[_, _]].values.iterator().next)
      pullBranch1(rowValue.asInstanceOf[jMap[_, _]].values.iterator)
    }
    println(s"lambda: " + lambda)
    lambda
  }
}