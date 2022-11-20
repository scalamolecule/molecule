package codegen.db.datomic.query

import codegen.DatomicGenBase

object _NestOpt extends DatomicGenBase("NestOpt", "/query") {

  val content = {
    val pullBranch0_X    = (1 to 21).map(i => s"case $i => pullBranch0_$i(casts)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.query
       |
       |import java.util.{Iterator => jIterator, Map => jMap}
       |import molecule.core.query.Model2Query
       |import scala.collection.mutable.ArrayBuffer
       |
       |
       |trait ${fileName}_[Tpl] { self: Model2Query[Tpl] with Base[Tpl]
       |  with CastNestedOptBranch_[Tpl] with CastNestedOptLeaf_[Tpl] =>
       |
       |  lazy val levels = pullCastss.length
       |
       |  lazy val pullCasts1 = pullCastss.head
       |  lazy val pullCasts2 = pullCastss(1)
       |  lazy val pullCasts3 = pullCastss(2)
       |  lazy val pullCasts4 = pullCastss(3)
       |  lazy val pullCasts5 = pullCastss(4)
       |  lazy val pullCasts6 = pullCastss(5)
       |  lazy val pullCasts7 = pullCastss(6)
       |
       |  lazy val pullBranch1: jIterator[_] => List[Any] = {
       |    if (levels == 1) pullLeaf(pullCasts1) else pullBranch(pullCasts1, pullBranch2)
       |  }
       |  lazy val pullBranch2: jIterator[_] => List[Any] = {
       |    if (levels == 2) pullLeaf(pullCasts2) else pullBranch(pullCasts2, pullBranch3)
       |  }
       |  lazy val pullBranch3: jIterator[_] => List[Any] = {
       |    if (levels == 3) pullLeaf(pullCasts3) else pullBranch(pullCasts3, pullBranch4)
       |  }
       |  lazy val pullBranch4: jIterator[_] => List[Any] = {
       |    if (levels == 4) pullLeaf(pullCasts4) else pullBranch(pullCasts4, pullBranch5)
       |  }
       |  lazy val pullBranch5: jIterator[_] => List[Any] = {
       |    if (levels == 5) pullLeaf(pullCasts5) else pullBranch(pullCasts5, pullBranch6)
       |  }
       |  lazy val pullBranch6: jIterator[_] => List[Any] = {
       |    if (levels == 6) pullLeaf(pullCasts6) else pullBranch(pullCasts6, pullBranch7)
       |  }
       |  lazy val pullBranch7: jIterator[_] => List[Any] = {
       |    pullLeaf(pullCasts7)
       |  }
       |
       |  final protected lazy val pullRow2tpl: Row => Tpl = {
       |    casts.length match {
       |      $pullBranch0_X
       |    }
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
