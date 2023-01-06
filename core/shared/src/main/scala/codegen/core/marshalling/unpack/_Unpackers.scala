package codegen.core.marshalling.unpack

import codegen.CoreGenBase

object _Unpackers extends CoreGenBase( "Unpackers", "/marshalling/unpack") {

  val content = {
    val unpackX       = (1 to 22).map(i => s"case $i => resolve$i(unpackers)").mkString("\n      ")
    val unpackMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.marshalling.unpack
       |
       |import molecule.boilerplate.ast.Model._
       |
       |trait ${fileName}_[Tpl] { self: DTO2tpls[Tpl] =>
       |
       |  def getUnpacker(elements: Seq[Element]): () => Any = {
       |    val unpackers: List[() => Any] = resolveUnpackers(elements, Nil)
       |    unpackers.length match {
       |      $unpackX
       |    }
       |  }
       |$unpackMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val unpackers = (1 to i).map { j => s"u$j" }.mkString(", ")
    val calls     = (1 to i).map { j => s"u$j()" }.mkString(",\n        ")
    val body      =
      s"""
         |  final private def resolve$i(unpackers: List[() => Any]): () => Any = {
         |    val List($unpackers) = unpackers
         |    () => {
         |      (
         |        $calls
         |      )
         |    }
         |  }""".stripMargin
  }
}
