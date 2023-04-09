package codegen.core.transaction

import codegen.CoreGenBase

object _InsertResolvers extends CoreGenBase("InsertResolvers", "/transaction") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case $i => resolve$i(resolvers)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import molecule.base.ast.SchemaAST.MetaNs
       |import molecule.base.error.InsertError
       |import molecule.boilerplate.ast.Model._
       |
       |trait $fileName_ {
       |
       |  protected def resolve(
       |    nsMap: Map[String, MetaNs],
       |    elements: List[Element],
       |    resolvers: List[Product => Unit],
       |    tpl: Int,
       |    tplIndex: Int
       |  ): List[Product => Unit]
       |
       |  def getResolver(
       |    nsMap: Map[String, MetaNs],
       |    elements: List[Element],
       |    outerTpl: Int = 0
       |  ): Product => Unit = {
       |    val resolvers: List[Product => Unit] =
       |      resolve(nsMap, elements, Nil, outerTpl, 0)
       |
       |    resolvers.length match {
       |      $resolveX
       |    }
       |  }
       |$resolveMethods
       |}""".stripMargin
  }

  case class Chunk(i: Int) extends TemplateVals(i) {
    val resolvers = (1 to i).map { j => s"r$j" }.mkString(", ")
    val calls     = (1 to i).map { j => s"r$j(tpl)" }.mkString("\n      ")
    val body      =
      s"""
         |  final private def resolve$i(
         |    resolvers: List[Product => Unit]
         |  ): Product => Unit = {
         |    val List($resolvers) = resolvers
         |    (tpl: Product) => {
         |      $calls
         |    }
         |  }""".stripMargin
  }
}
