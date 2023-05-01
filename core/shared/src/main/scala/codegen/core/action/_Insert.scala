package codegen.core.action

import codegen.CoreGenBase

object _Insert extends CoreGenBase( "Insert", "/api") {

  val content = {
    val traits = (2 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.boilerplate.ast.Model.Element
       |
       |case class ${fileName}_1[A](elements0: List[Element]) extends $fileName_ {
       |  final def apply(a: A, as: A*): Insert = InsertTpls(elements0, (a +: as).map(a => Tuple1(a)))
       |  final def apply(tpls: Seq[A]): Insert = InsertTpls(elements0, tpls.map(a => Tuple1(a)))
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 2)
    val body =
      s"""
         |case class $fileName_$arity[${`A..V`}](elements0: List[Element]) {
         |  final def apply(${`a:A..v:V`})                : Insert = InsertTpls(elements0, Seq((${`a..v`})))
         |  final def apply(tpl: ${`(A..V)`}, more: ${`(A..V)`}*): Insert = InsertTpls(elements0, tpl +: more)
         |  final def apply(tpls: Seq[${`(A..V)`}])$pad         : Insert = InsertTpls(elements0, tpls)
         |}""".stripMargin
  }
}
