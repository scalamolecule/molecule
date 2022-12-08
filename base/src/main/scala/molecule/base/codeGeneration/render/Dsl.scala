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
      "molecule.boilerplate.ast.MoleculeModel",
      "molecule.boilerplate.ast.MoleculeModel._",
    )
    val typeImports = attrs.collect {
      case MetaAttr(_, _, "Date", _, _, _, _, _) => "java.util.Date"
      case MetaAttr(_, _, "UUID", _, _, _, _, _) => "java.util.UUID"
      case MetaAttr(_, _, "URI", _, _, _, _, _)  => "java.net.URI"
    }.distinct
    (baseImports ++ typeImports).sorted.mkString("import ", "\nimport ", "")
  }

  val baseNs: String = {
    val man = List.newBuilder[String]
    val opt = List.newBuilder[String]
    val tac = List.newBuilder[String]
    attrs.collect {
      case MetaAttr(attr, card, tpe, _, _, _, _, _) if !genericAttrs.contains(attr) =>
        val padA    = padAttr(attr)
        val padT    = padType(tpe)
        val attrMan = "Attr" + card.marker + "Man" + tpe
        val attrOpt = "Attr" + card.marker + "Opt" + tpe
        val attrTac = "Attr" + card.marker + "Tac" + tpe
        man += s"""protected lazy val ${attr}_man$padA: $attrMan$padT = $attrMan$padT("$ns", "$attr"$padA)"""
        opt += s"""protected lazy val ${attr}_opt$padA: $attrOpt$padT = $attrOpt$padT("$ns", "$attr"$padA)"""
        tac += s"""protected lazy val ${attr}_tac$padA: $attrTac$padT = $attrTac$padT("$ns", "$attr"$padA)"""
    }
    val attrDefs = (man.result() ++ Seq("") ++ opt.result() ++ Seq("") ++ tac.result()).mkString("\n  ")
    s"""trait $ns extends Generic {
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
       |object $ns extends $ns_0[Nothing](Nil) {
       |  final def apply(eid: Long, eids: Long*) = new $ns_1[Long, Long](Seq(AttrOneTacLong("Generic", "e", Appl, eid +: eids)))
       |  final def apply(eids: Iterable[Long])   = new $ns_1[Long, Long](Seq(AttrOneTacLong("Generic", "e", Appl, eids.toSeq)))
       |}
       |
       |
       |$nss
       |""".stripMargin
}
