package codegen.db.datomic.api

import codegen.DatomicGenBase
import codegen.db.datomic.query.casting._CastFlat.fileName

object _DatomicMoleculeApi extends DatomicGenBase("DatomicMoleculeApi", "/api") {

  val content = {
    val classes = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.api
       |
       |import molecule.boilerplate.api._
       |import molecule.core.api._
       |
       |$classes
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val p    = if (arity == 1) "" else if (arity >= 10) " " else "  "
    val body =
      s"""
         |class ${fileName}_$n0[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]) extends MoleculeApi_$n0[${`A..V`}] {
         |  override def save  : DatomicSaveOps${`..N`}$p  = new DatomicSaveOps(molecule.elements)
         |  override def insert: DatomicInsert_$arity[${`A..V`}]$p = new DatomicInsert_$arity[${`A..V`}](molecule)
         |  override def update: DatomicInsert_$arity[${`A..V`}]$p = new DatomicInsert_$arity[${`A..V`}](molecule)
         |  override def delete: DatomicInsert_$arity[${`A..V`}]$p = new DatomicInsert_$arity[${`A..V`}](molecule)
         |  override def query : DatomicQueryOps[${`(A..V)`}] = new DatomicQueryOps[${`(A..V)`}](molecule.elements)
         |}""".stripMargin
  }
}
