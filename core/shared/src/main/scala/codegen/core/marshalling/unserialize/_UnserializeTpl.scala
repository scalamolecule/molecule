package codegen.core.marshalling.unserialize

import codegen.CoreGenBase

object _UnserializeTpl extends CoreGenBase( "UnserializeTpl", "/marshalling/unserialize") {

  val content = {
    val unpackX       = (1 to 22).map(i => s"case $i => resolve$i(unpicklers)").mkString("\n      ")
    val unpackMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.marshalling.unpack
       |
       |import molecule.boilerplate.ast.Model._
       |
       |trait ${fileName}_[Tpl] { self: DTO2tpls[Tpl] =>
       |
       |  def getUnpickler(elements: List[Element]): () => Any = {
       |    val unpicklers: List[() => Any] = resolveUnpicklers(elements, Nil)
       |    unpicklers.length match {
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
         |  final private def resolve$i(unpicklers: List[() => Any]): () => Any = {
         |    val List($unpackers) = unpicklers
         |    () => {
         |      (
         |        $calls
         |      )
         |    }
         |  }""".stripMargin
  }
}
