package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._


case class Dsl(schema: MetaSchema, partPrefix: String, namespace: MetaNs)
  extends DslFormatting(schema, namespace) {

  val imports: String = {
    val baseImports = Seq(
      "molecule.base.ast.SchemaAST._",
      "molecule.boilerplate.api.Keywords._",
      "molecule.boilerplate.api._",
      "molecule.boilerplate.api.expression._",
      "molecule.boilerplate.ast.Model",
      "molecule.boilerplate.ast.Model._",
    )
    val typeImports = attrs.collect {
      case MetaAttr(_, _, "Date", _, _, _, _, _, _, _) => "java.util.Date"
      case MetaAttr(_, _, "UUID", _, _, _, _, _, _, _) => "java.util.UUID"
      case MetaAttr(_, _, "URI", _, _, _, _, _, _, _)  => "java.net.URI"
    }.distinct
    (baseImports ++ typeImports).sorted.mkString("import ", "\nimport ", "")
  }

  val validationExtractor = Dsl_Validations(schema, namespace)

  val baseNs: String = {
    val man = List.newBuilder[String]
    val opt = List.newBuilder[String]
    val tac = List.newBuilder[String]
    val vas = List.newBuilder[String]
    attrs.collect {
      case MetaAttr(attr, card, tpe, refNsOpt, _, _, _, _, valueAttrs, validations) =>
        val valids  = if (validations.nonEmpty) {
          val valueAttrMetas = attrs.collect {
            case MetaAttr(attr1, card1, tpe1, _, _, _, _, _, _, _)
              if valueAttrs.contains(attr1) =>
              val fullTpe = if (card1.isInstanceOf[CardOne.type]) tpe1 else s"Set[$tpe1]"
              (attr1, fullTpe, s"Attr${card1._marker}Man$tpe1", s"${card1._marker}$tpe1")
          }.sortBy(_._1)
          vas += validationExtractor.validationMethod(attr, tpe, validations, valueAttrMetas)
          if (valueAttrs.isEmpty) {
            s", validator = Some(validation_$attr)"
          } else {
            val valueAttrsStr = valueAttrs.mkString("\"", "\", \"", "\"")
            s", validator = Some(validation_$attr), valueAttrs = Seq($valueAttrsStr)"
          }
        } else ""
        val padA    = padAttr(attr)
        val padT0   = padType(tpe)
        val refNs   = refNsOpt.fold("")(refNs => s""", refNs = Some("$refNs")""")
        val attrMan = "Attr" + card._marker + "Man" + tpe
        val attrOpt = "Attr" + card._marker + "Opt" + tpe
        val attrTac = "Attr" + card._marker + "Tac" + tpe
        man += s"""protected lazy val ${attr}_man$padA: $attrMan$padT0 = $attrMan$padT0("$ns", "$attr"$padA$refNs$valids)"""
        if (attr != "id") {
          opt += s"""protected lazy val ${attr}_opt$padA: $attrOpt$padT0 = $attrOpt$padT0("$ns", "$attr"$padA$refNs$valids)"""
        }
        tac += s"""protected lazy val ${attr}_tac$padA: $attrTac$padT0 = $attrTac$padT0("$ns", "$attr"$padA$refNs$valids)"""
    }
    val vas1     = vas.result()
    val vas2     = if (vas1.isEmpty) Nil else "" +: vas1
    val attrDefs = (man.result() ++ Seq("") ++ opt.result() ++ Seq("") ++ tac.result() ++ vas2).mkString("\n  ")

    s"""trait ${ns}_base {
       |  $attrDefs
       |}""".stripMargin
  }

  val txs_ = if (ns == "Tx") Dsl_CompositeTxs(maxArity).content else ""

  val nss: String = (0 to schema.maxArity).map(Dsl_Arities(schema, partPrefix, namespace, _).get).mkString("\n\n")

  def get: String = {
    s"""/*
       |* AUTO-GENERATED Molecule DSL boilerplate code for namespace `$ns`
       |*
       |* To change:
       |* 1. Edit data model in $pkg.dataModel.$domain
       |* 2. `sbt compile -Dmolecule=true`
       |*/
       |package $pkg.$domain
       |
       |$imports
       |
       |
       |$baseNs
       |
       |object $ns extends $ns_0[Nothing](Nil) {
       |  final def apply(id: Long, ids: Long*) = new $ns_0[Long](List(AttrOneTacLong("$ns", "id", Eq, id +: ids)))
       |  final def apply(ids: Iterable[Long])  = new $ns_0[Long](List(AttrOneTacLong("$ns", "id", Eq, ids.toSeq)))
       |}$txs_
       |
       |
       |$nss
       |""".stripMargin
  }
}
