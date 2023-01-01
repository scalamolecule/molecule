package codegen.core.marshalling.pack

import codegen.CoreGenBase

object _Packers extends CoreGenBase("Packers", "/marshalling/pack") {

  val content = {
    val unpackX       = (1 to 22).map(i => s"case $i => resolve$i(packers)").mkString("\n      ")
    val unpackMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.marshalling.pack
       |
       |import molecule.boilerplate.ast.Model._
       |
       |trait ${fileName}_ { self: Tpls2DTO =>
       |
       |  def getPacker(elements: Seq[Element]): Product => Unit = {
       |    val packers: List[Product => Unit] = resolvePackers(elements, Nil)
       |    packers.length match {
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
         |  final private def resolve$i(packers: List[Product => Unit]): Product => Unit = {
         |    val List($packers) = packers
         |    (tpl: Product) => {
         |      $calls
         |    }
         |  }""".stripMargin
  }
}
