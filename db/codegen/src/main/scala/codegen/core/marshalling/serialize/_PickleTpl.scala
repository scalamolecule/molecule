package codegen.core.marshalling.serialize

import codegen.core.CoreGenBase

object _PickleTpl extends CoreGenBase("PickleTpl", "/marshalling/serialize") {

  val content = {
    val unpackX       = (1 to 22).map(i => s"case ${caseN(i)} => resolve$i(picklers)").mkString("\n      ")
    val unpackMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.marshalling.serialize
       |
       |import molecule.db.core.ast._
       |
       |trait $fileName_ { self: PickleTpls =>
       |
       |  def getPickler(elements: List[Element]): Product => Unit = {
       |    val picklers: List[Product => Unit] = resolvePicklers(elements, Nil, 0)
       |    picklers.length match {
       |      $unpackX
       |    }
       |  }
       |$unpackMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val packers = (1 to i).map { j => s"p$j" }.mkString(", ")
    val calls   = (1 to i).map { j => s"p$j(tpl)" }.mkString("\n      ")
    val body    =
      s"""
         |  final private def resolve$i(picklers: List[Product => Unit]): Product => Unit = {
         |    val List($packers) = picklers
         |    (tpl: Product) => {
         |      $calls
         |    }
         |  }""".stripMargin
  }
}
