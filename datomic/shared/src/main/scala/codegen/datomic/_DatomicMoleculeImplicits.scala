package codegen.datomic

import codegen.DatomicGenBase

object _DatomicMoleculeImplicits extends DatomicGenBase( "package", "") {

  val content = {
    val moleculeFactories = (1 to 22).map(arity => MoleculeFactory(arity).body).mkString("\n")
    val compositeFactories = (1 to 22).map(arity => CompositeFactory(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule
       |
       |import molecule.boilerplate.api._
       |import molecule.core.MoleculeImplicits_
       |import molecule.datomic.action._
       |import molecule.datomic.api.DatomicApiAsync
       |import scala.language.implicitConversions
       |
       |package object datomic {
       |
       |  object async extends DatomicMoleculeImplicits with DatomicApiAsync
       |  object sync extends DatomicMoleculeImplicits with DatomicApiAsync
       |  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
       |  object zio extends DatomicMoleculeImplicits with DatomicApiAsync
       |  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync
       |
       |  private[datomic] trait DatomicMoleculeImplicits extends MoleculeImplicits_ {
       |    implicit final override def m(molecule: Molecule_00): DatomicActions_00 = new DatomicActions_00(molecule)
       |$moleculeFactories
       |
       |    implicit final override def m(composite: Composite_00): DatomicActions_00 = new DatomicActions_00(composite)
       |$compositeFactories
       |  }
       |}""".stripMargin
  }

  case class MoleculeFactory(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""    implicit final override def m[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): DatomicActions_$n0[${`A..V`}] = new DatomicActions_$n0(molecule)""".stripMargin
  }

  case class CompositeFactory(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""    implicit final override def m[$Tn](composite: Composite_$n0[$Tn]): DatomicActions_$n0[$Tn] = new DatomicActions_$n0(composite)""".stripMargin
  }
}
