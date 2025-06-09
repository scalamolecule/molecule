package boilerplate.db.core.transaction

import boilerplate.db.core.CoreBase

object _InsertResolvers extends CoreBase("InsertResolvers", "/transaction") {

  val content = {
    val resolveX       = (1 to 22).map(i => s"case ${caseN(i)} => resolve$i(resolvers)").mkString("\n      ")
    val resolveMethods = (1 to 22).map(arity => Chunk(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.transaction
       |
       |import molecule.db.core.ast.*
       |
       |trait $fileName_ {
       |
       |  protected def resolve(
       |    elements: List[Element],
       |    resolvers: List[Product => Unit],
       |    tplIndex: Int,
       |    prevRefs: List[String]
       |  ): List[Product => Unit]
       |
       |  def getResolver(elements: List[Element]): Product => Unit = {
       |    val resolvers: List[Product => Unit] = resolve(elements, Nil, 0, Nil)
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
