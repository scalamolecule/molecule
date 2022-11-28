package codegen.db.datomic.query.casting

import codegen.DatomicGenBase

object _NestOptFlatten extends DatomicGenBase("NestOptFlatten", "/query/casting") {

  val content = {
    val pullBranch0_X    = (1 to 21).map(i => s"case $i => pullBranch0_$i(casts)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query.casting
       |
       |import java.util.{Iterator => jIterator, Map => jMap}
       |import molecule.core.query.Model2Query
       |import molecule.db.datomic.query.Base
       |import scala.collection.mutable.ArrayBuffer
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl]
       |  with CastNestedOptBranchFlatten_[Tpl] with CastNestedOptLeafFlatten_[Tpl] =>
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
       |  private lazy val pullBranch1: jIterator[_] => List[Any] = {
       |    if (levels == 1) pullLeafFlatten(pullCasts1) else pullBranchFlatten(pullCasts1, pullBranch2, 0)
       |  }
       |  private lazy val pullBranch2: jIterator[_] => List[Any] = {
       |    if (levels == 2) pullLeafFlatten(pullCasts2) else pullBranchFlatten(pullCasts2, pullBranch3, 1)
       |  }
       |  private lazy val pullBranch3: jIterator[_] => List[Any] = {
       |    if (levels == 3) pullLeafFlatten(pullCasts3) else pullBranchFlatten(pullCasts3, pullBranch4, 2)
       |  }
       |  private lazy val pullBranch4: jIterator[_] => List[Any] = {
       |    if (levels == 4) pullLeafFlatten(pullCasts4) else pullBranchFlatten(pullCasts4, pullBranch5, 3)
       |  }
       |  private lazy val pullBranch5: jIterator[_] => List[Any] = {
       |    if (levels == 5) pullLeafFlatten(pullCasts5) else pullBranchFlatten(pullCasts5, pullBranch6, 4)
       |  }
       |  private lazy val pullBranch6: jIterator[_] => List[Any] = {
       |    if (levels == 6) pullLeafFlatten(pullCasts6) else pullBranchFlatten(pullCasts6, pullBranch7, 5)
       |  }
       |  private lazy val pullBranch7: jIterator[_] => List[Any] = {
       |    pullLeafFlatten(pullCasts7)
       |  }
       |
       |  final protected lazy val pullRowFlatten2tpl: Row => Tpl = {
       |    casts.length match {
       |      case 0 => pullBranch0_0
       |      $pullBranch0_X
       |    }
       |  }
       |
       |  final private def pullBranch0_0: Row => Tpl = {
       |    (row: Row) => pullBranch1(row.get(0).asInstanceOf[jMap[_, _]].values.iterator).asInstanceOf[Tpl]
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (0 until i).map { j => s"val c$j = casts($j)" }.mkString("\n    ")
    val castings = (0 until i).map { j => s"c$j(row.get($j))" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def pullBranch0_$i(casts: ArrayBuffer[AnyRef => AnyRef]): Row => Tpl = {
         |    $casters
         |    (row: Row) =>
         |      (
         |        $castings,
         |        pullBranch1(row.get($i).asInstanceOf[jMap[_, _]].values.iterator)
         |      ).asInstanceOf[Tpl]
         |  }""".stripMargin
  }
}
