package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _NestOpt extends DatomicGenBase("NestOpt", "/query/casting") {

  val content = {
    val pullBranch0_X    = (1 to 22).map(i => s"case $i => cast$i(casters)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datomic.query.casting
       |
       |import java.util.{Iterator => jIterator, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.datomic.query.DatomicQueryBase
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_[Tpl]
       |  extends CastNestedOptBranch_
       |    with CastNestedOptLeaf_
       |    with CastRow2Tpl_
       |    with DatomicQueryBase { self: Model2Query =>
       |
       |  private lazy val levels = pullCastss.length
       |
       |  private lazy val pullCasts1 = pullCastss.head
       |  private lazy val pullCasts2 = pullCastss(1)
       |  private lazy val pullCasts3 = pullCastss(2)
       |  private lazy val pullCasts4 = pullCastss(3)
       |  private lazy val pullCasts5 = pullCastss(4)
       |  private lazy val pullCasts6 = pullCastss(5)
       |  private lazy val pullCasts7 = pullCastss(6)
       |
       |  private lazy val pullSorts1 = pullSortss.head
       |  private lazy val pullSorts2 = pullSortss(1)
       |  private lazy val pullSorts3 = pullSortss(2)
       |  private lazy val pullSorts4 = pullSortss(3)
       |  private lazy val pullSorts5 = pullSortss(4)
       |  private lazy val pullSorts6 = pullSortss(5)
       |  private lazy val pullSorts7 = pullSortss(6)
       |
       |  private lazy val pullBranch1: jIterator[_] => List[Any] = {
       |    if (levels == 1)
       |      pullLeaf(aritiess(1), pullCasts1, pullSorts1)
       |    else
       |      pullBranch(aritiess(1), pullCasts1, pullSorts1, pullBranch2, refDepths(1))
       |  }
       |
       |  private lazy val pullBranch2: jIterator[_] => List[Any] = {
       |    if (levels == 2)
       |      pullLeaf(aritiess(2), pullCasts2, pullSorts2)
       |    else
       |      pullBranch(aritiess(2), pullCasts2, pullSorts2, pullBranch3, refDepths(2))
       |  }
       |
       |  private lazy val pullBranch3: jIterator[_] => List[Any] = {
       |    if (levels == 3)
       |      pullLeaf(aritiess(3), pullCasts3, pullSorts3)
       |    else
       |      pullBranch(aritiess(3), pullCasts3, pullSorts3, pullBranch4, refDepths(3))
       |  }
       |
       |  private lazy val pullBranch4: jIterator[_] => List[Any] = {
       |    if (levels == 4)
       |      pullLeaf(aritiess(4), pullCasts4, pullSorts4)
       |    else
       |      pullBranch(aritiess(4), pullCasts4, pullSorts4, pullBranch5, refDepths(4))
       |  }
       |
       |  private lazy val pullBranch5: jIterator[_] => List[Any] = {
       |    if (levels == 5)
       |      pullLeaf(aritiess(5), pullCasts5, pullSorts5)
       |    else
       |      pullBranch(aritiess(5), pullCasts5, pullSorts5, pullBranch6, refDepths(5))
       |  }
       |
       |  private lazy val pullBranch6: jIterator[_] => List[Any] = {
       |    if (levels == 6)
       |      pullLeaf(aritiess(6), pullCasts6, pullSorts6)
       |    else
       |      pullBranch(aritiess(6), pullCasts6, pullSorts6, pullBranch7, refDepths(6))
       |  }
       |
       |  private lazy val pullBranch7: jIterator[_] => List[Any] = {
       |    pullLeaf(aritiess(7), pullCasts7, pullSorts7)
       |  }
       |
       |  protected lazy val pullNestedData: AnyRef => AnyRef = {
       |    (rowValue: AnyRef) => pullBranch1(rowValue.asInstanceOf[jMap[_, _]].values.iterator)
       |  }
       |
       |  @tailrec
       |final private def resolveArities(
       |    arities: List[List[Int]],
       |    casts: List[AnyRef => AnyRef],
       |    rowIndex: Int,
       |    rowIndexTx: Int,
       |    acc: List[Row => AnyRef]
       |  ): List[Row => AnyRef] = {
       |    arities match {
       |      case List(1) :: as =>
       |        val cast = (row: Row) => casts.head(row.get(rowIndex))
       |        resolveArities(as, casts.tail, rowIndex + 1, rowIndexTx, acc :+ cast)
       |
       |      // NestedOpt
       |      case List(-1) :: as =>
       |        val cast = (row: Row) => casts.head(row.get(rowIndex))
       |        resolveArities(as, casts.tail, rowIndexTx, rowIndexTx, acc :+ cast)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected lazy val pullRow2tpl: Row => Tpl = {
       |    val arities    = aritiess.head
       |    val casts      = castss.head
       |    val rowIndexTx = arities.flatten.takeWhile(_ != -1).sum + 1
       |    val casters    = resolveArities(arities, casts, 0, rowIndexTx, Nil)
       |    casters.length match {
       |      $pullBranch0_X
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map { j => s"c$j" }.mkString(", ")
    val castings = (1 to i).map { j => s"c$j(row)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def cast$i(casters: List[Row => AnyRef]): Row => Tpl = {
         |    val List($casters) = casters
         |    (row: Row) =>
         |      (
         |        $castings
         |      ).asInstanceOf[Tpl]
         |  }""".stripMargin
  }
}
