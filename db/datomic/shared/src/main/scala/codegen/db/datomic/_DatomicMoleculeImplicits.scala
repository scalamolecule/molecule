package codegen.db.datomic

import codegen.DatomicGenBase

object _DatomicMoleculeImplicits extends DatomicGenBase( "package", "") {

  val content = {
    val moleculeFactories = (1 to 22).map(arity => MoleculeFactory(arity).body).mkString("\n")
    val compositeFactories = (1 to 22).map(arity => CompositeFactory(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db
       |
       |import molecule.boilerplate.api._
       |import molecule.core.MoleculeImplicits_
       |import molecule.db.datomic.api._
       |import scala.language.implicitConversions
       |
       |package object datomic extends MoleculeImplicits_ {
       |$moleculeFactories
       |
       |$compositeFactories
       |}""".stripMargin
  }

  case class MoleculeFactory(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit final override def m[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): DatomicMoleculeApi_$n0[${`A..V`}] = new DatomicMoleculeApi_$n0(molecule)""".stripMargin
  }

  case class CompositeFactory(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit final override def m[$Tn](composite: Composite_$n0[$Tn]): DatomicMoleculeApi_$n0[$Tn] = new DatomicMoleculeApi_$n0(composite)""".stripMargin
  }
}
