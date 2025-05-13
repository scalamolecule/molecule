package boilerplate.db.core.action

import boilerplate.db.core.CoreGenBase

object _Insert extends CoreGenBase( "Insert", "/action") {

  val content = {
    val traits = (2 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.action
       |
       |import molecule.db.core.ast.Element
       |
       |case class ${fileName}_1[A](elements: List[Element]) {
       |  final def apply(a: A, as: A*) = Insert(elements, (a +: as).map(a => Tuple1(a)))
       |  final def apply(tpls: Seq[A]) = Insert(elements, tpls.map(a => Tuple1(a)))
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 2)
    val body =
      s"""
         |case class $fileName_$arity[${`A..V`}](elements: List[Element]) {
         |  final def apply(${`a:A..v:V`})                 = Insert(elements, Seq((${`a..v`})))
         |  final def apply(tpl: ${`(A..V)`}, more: ${`(A..V)`}*) = Insert(elements, tpl +: more)
         |  final def apply(tpls: Seq[${`(A..V)`}])$pad          = Insert(elements, tpls)
         |}""".stripMargin
  }
}
