package molecule.base.codeGeneration.renderDsl

object Template {

  def apply(
    ns: String,
    pkg: String,
    domain: String,
    body: String,
    imports: Seq[String]
  ): String = {
    s"""/*
       |* AUTO-GENERATED Molecule DSL for namespace `$ns`
       |*
       |* To change:
       |* 1. Edit data model in $pkg.dataModel.$domain
       |* 2. `sbt clean compile -Dmolecule=true`
       |*/
       |package $pkg.$domain
       |
       |import ${imports.sorted.mkString("\nimport ")}
       |
       |
       |$body
       |""".stripMargin
  }
}
