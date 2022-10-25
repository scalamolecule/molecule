package molecule.base.codeGeneration.render

import molecule.base.ast.SchemaAST.{MetaAttr, MetaNs, MetaSchema}


case class Dsl(schema: MetaSchema, namespace: MetaNs)
  extends DslFormatting(schema, namespace) {

  val imports: String = {
    val baseImports = Seq(
      "molecule.base.ast.SchemaAST._",
      "molecule.boilerplate.api._",
      "molecule.boilerplate.api.expression._",
      "molecule.boilerplate.api.Keywords._",
      "molecule.boilerplate.ast.MoleculeModel._",
      "molecule.boilerplate.markers.NamespaceMarkers._",
    )
    val typeImports = attrs.collect {
      case MetaAttr(_, _, "Date", _, _, _, _, _) => "java.util.Date"
      case MetaAttr(_, _, "UUID", _, _, _, _, _) => "java.util.UUID"
      case MetaAttr(_, _, "URI", _, _, _, _, _)  => "java.net.URI"
    }
    (baseImports ++ typeImports).sorted.mkString("import ", "\nimport ", "")
  }

  val baseNs: String = {
    val man = List.newBuilder[String]
    val opt = List.newBuilder[String]
    val tac = List.newBuilder[String]
    attrs.foreach {
      case MetaAttr(attr, card, tpe, refNs, options, descr, alias, validation) =>
        val padA = padAttr(attr)
        val padT = padType(tpe)
        val atomMan = "Atom" + card.marker + "Man" + tpe
        val atomOpt = "Atom" + card.marker + "Opt" + tpe
        val atomTac = "Atom" + card.marker + "Tac" + tpe
        man += s"""protected lazy val ${attr}_man$padA: $atomMan$padT = $atomMan$padT("$ns", "$attr"$padA)"""
        opt += s"""protected lazy val ${attr}_opt$padA: $atomOpt$padT = $atomOpt$padT("$ns", "$attr"$padA)"""
        tac += s"""protected lazy val ${attr}_tac$padA: $atomTac$padT = $atomTac$padT("$ns", "$attr"$padA)"""
    }
    val attrDefs = (man.result() ++ Seq("") ++ opt.result() ++ Seq("") ++ tac.result()).mkString("\n  ")
    s"""trait $ns {
       |  $attrDefs
       |}""".stripMargin
  }

  val nss: String = (0 to schema.maxArity).map(Dsl_Arities(schema, namespace, _).get).mkString("\n\n")

  def get: String =
    s"""/*
       |* AUTO-GENERATED Molecule DSL boilerplate code for namespace `$ns`
       |*
       |* To change:
       |* 1. Edit data model in $pkg.dataModel.$domain
       |* 2. `sbt clean compile -Dmolecule=true`
       |*/
       |package $pkg.$domain
       |
       |$imports
       |
       |
       |$baseNs
       |
       |object $ns extends ${ns}_0[Nothing](Nil)
       |
       |
       |$nss
       |""".stripMargin
}
