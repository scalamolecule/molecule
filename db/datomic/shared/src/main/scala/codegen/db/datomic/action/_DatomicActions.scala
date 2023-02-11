package codegen.db.datomic.action

import codegen.DatomicGenBase

object _DatomicActions extends DatomicGenBase("DatomicActions", "/action") {

  val content = {
    val classes = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.action
       |
       |import molecule.boilerplate.api._
       |import molecule.core.action._
       |
       |
       |class DatomicActions_00(molecule: Molecule_00) extends Actions_00 {
       |  override def delete: DatomicDelete = new DatomicDelete(molecule.elements)
       |}
       |$classes
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val p    = if (arity == 1) "   " else if (arity >= 10) "  " else " "
    val body =
      s"""
         |class ${fileName}_$n0[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]) extends Actions_$n0[${`A..V`}] {
         |  override def save  : DatomicSave${`..N`}     = new DatomicSave(molecule.elements)
         |  override def insert: DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule.elements)
         |  override def query : DatomicQuery[${`(A..V)`}]$p = new DatomicQuery[${`(A..V)`}](molecule.elements)
         |  override def update: DatomicUpdate${`..N`}   = new DatomicUpdate(molecule.elements)
         |  override def upsert: DatomicUpdate${`..N`}   = new DatomicUpdate(molecule.elements, true)
         |  override def delete: DatomicDelete${`..N`}   = new DatomicDelete(molecule.elements)
         |}""".stripMargin
  }
}
