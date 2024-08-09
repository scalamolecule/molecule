package codegen.datalog.core.query.casting

import codegen.DatomicGenBase

object _CastOptRefBranch extends DatomicGenBase("CastOptRefBranch", "/query/casting") {

  val content = {
    val pullBranchX    = (1 to 21).map(i => s"case ${caseN(i)} => pullBranch$i(pullCasts)").mkString("\n      ")
    val resolveMethods = (1 to 21).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.datalog.core.query.casting
       |
       |import java.util.{Iterator => jIterator, Map => jMap}
       |import scala.annotation.tailrec
       |
       |
       |trait $fileName_ {
       |
       |  @tailrec
       |  final private def resolveArities(
       |    arities: List[Int],
       |    casts: List[jIterator[_] => Any],
       |    pullNested: jIterator[_] => Option[Any],
       |    acc: List[jIterator[_] => Any],
       |  ): List[jIterator[_] => Any] = {
       |    arities match {
       |      case 1 :: as =>
       |        resolveArities(as, casts.tail, pullNested, acc :+ casts.head)
       |
       |      // Nested
       |      case -1 :: Nil =>
       |        resolveArities(Nil, Nil, pullNested, acc :+ pullNested)
       |
       |      case _ => acc
       |    }
       |  }
       |
       |  final protected def pullOptRefBranch(
       |    arities: List[Int],
       |    pullCasts0: List[jIterator[_] => Any],
       |    pullNested: jIterator[_] => Option[Any]
       |  ): jIterator[_] => Option[Any] = {
       |    val pullCasts = resolveArities(arities, pullCasts0, pullNested, Nil)
       |    pullCasts.length match {
       |      $pullBranchX
       |    }
       |  }
       |
       |  final private def resolve(
       |    cast: java.util.Iterator[_] => Any
       |  ): jIterator[_] => Option[Any] = {
       |    val handleMap = (optionalData: jMap[_, _]) =>
       |      Some(cast(optionalData.values().iterator()))
       |    (it: jIterator[_]) =>
       |      try {
       |        it.next match {
       |          case maps: jMap[_, _] => handleMap(maps)
       |          case _                => None
       |        }
       |      } catch {
       |        case _: NullValueException => None
       |        case e: Throwable          => throw e
       |      }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val casters  = (1 to i).map { j => s"c$j" }.mkString(", ")
    val castings = (1 to i).map { j => s"c$j(it)" }.mkString(",\n        ")
    val body     =
      s"""
         |  final private def pullBranch$i(
         |    pullCasts: List[jIterator[_] => Any]
         |  ): jIterator[_] => Option[Any] = {
         |    val List($casters) = pullCasts
         |    resolve((it: java.util.Iterator[_]) =>
         |      (
         |        $castings
         |      )
         |    )
         |  }""".stripMargin
  }
}
