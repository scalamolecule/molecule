package codegen.core.api

import codegen.CoreGenBase

object _Save extends CoreGenBase( "Save", "/api") {

  val content = {
    val traits = (2 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |trait Save {
       |  def _saveOp(tpls: Seq[Product]): SaveOps
       |}
       |
       |object Save  {
       |
       |  trait Save_1[A] extends Save {
       |    def apply(a: A, as: A*): SaveOps = _saveOp((a +: as).map(a => Tuple1(a)))
       |    def apply(tpls: Seq[A]): SaveOps = _saveOp(tpls.map(a => Tuple1(a)))
       |  }
       |$traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 2)
    val body =
      s"""
         |  trait Save_$arity[${`A..V`}] extends Save {
         |    def apply(${`a:A..v:V`})                : SaveOps = _saveOp(Seq((${`a..v`})))
         |    def apply(tpl: (${`A..V`}), more: (${`A..V`})*): SaveOps = _saveOp(tpl +: more)
         |    def apply(tpls: Seq[(${`A..V`})])$pad         : SaveOps = _saveOp(tpls)
         |  }""".stripMargin
  }
}
