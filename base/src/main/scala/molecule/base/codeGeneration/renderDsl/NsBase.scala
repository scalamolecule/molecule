package molecule.base.codeGeneration.renderDsl

import molecule.base.codeGeneration.parseDataModel.parserAST.{Enum, Namespace, ParseModel, Ref, Val}
import molecule.base.codeGeneration.parseDataModel.parserAST._


case class NsBase(model: ParseModel, namespace: Namespace)
  extends Formatting(model, namespace) {

  val tpeMan = List.newBuilder[String]
  val tpeOpt = List.newBuilder[String]
  val tpeTac = List.newBuilder[String]

  attrs.collect {
    case a: Val =>
      val (card, ns_attrM, ns_attrO, ns__ref, attr, attrO, attr_, tpe, tpB, tpO, ref, refNsPad) = formatted(a)

      val (clsMan, clsTac, clsOpt) = nsData(a)
      lazy val opts    = getOptions(a, a.bi)
      lazy val optsTac = getOptions(a, a.bi, false)

      tpeMan += s"trait $attr  extends $clsMan $opts"

      if (isSchema) {
        if (optSchemaAttrs.contains(a.attr)) {
          tpeOpt += s"trait $attrO extends $clsOpt$opts"
        }
      } else {
        tpeOpt += s"trait $attrO extends $clsOpt$opts"
      }

      tpeTac += s"trait $attr_ extends $clsTac$optsTac"

    case a: Ref =>
      val (card, ns_attrM, ns_attrO, ns__ref, attr, attrO, attr_, tpe, tpB, tpO, ref, refNsPad) = formatted(a)

      val (clsMan, clsTac, clsOpt) = nsData(a)
      lazy val opts    = getOptions(a, a.bi)
      lazy val optsTac = getOptions(a, a.bi, false)

      tpeMan += s"trait $attr  extends $clsMan $opts"
      tpeOpt += s"trait $attrO extends $clsOpt$opts"
      tpeTac += s"trait $attr_ extends $clsTac$optsTac"

    case a: Enum =>
      val (card, ns_attrM, ns_attrO, ns__ref, attr, attrO, attr_, tpe, tpB, tpO, ref, refNsPad) = formatted(a)

      val (clsMan, clsTac, clsOpt) = nsData(a)
      lazy val opts    = getOptions(a, a.bi)
      lazy val optsTac = getOptions(a, a.bi, false)

      val enumValues = s"private lazy val ${a.enums.mkString(", ")} = EnumValue"

      tpeMan += s"trait $attr  extends $clsMan $opts { $enumValues }"
      tpeOpt += s"trait $attrO extends $clsOpt$opts { $enumValues }"
      tpeTac += s"trait $attr_ extends $clsTac$optsTac { $enumValues }"
  }

  val nsAttrs = {
    if (isSchema) {
      (tpeMan.result() ++ Seq("") ++ tpeOpt.result() ++ Seq("") ++ tpeTac.result()).mkString("\n  ")
    } else if (isDatom) {
      (tpeMan.result() ++ Seq("") ++ tpeTac.result()).mkString("\n  ")
    } else if (isGeneric) {
      tpeMan.result().mkString("\n  ")
    } else {
      (tpeMan.result() ++ Seq("") ++ tpeOpt.result() ++ Seq("") ++ tpeTac.result()).mkString("\n  ")
    }
  }

  val attrsObj =
    s"""object ${ns}_ {
       |  $nsAttrs
       |}
       |""".stripMargin

  val (imports, body) = if (isGeneric) {
    (
      Seq(
        "java.util.Date",
        "molecule.boilerplate.markers.attrMarkers._",
      ),
      attrsObj
    )

  } else {
    (
      Seq(
        "java.util.Date",
        "java.net.URI",
        "java.util.UUID",
        "molecule.boilerplate.api.generic.dsl.Datom.Datom_.e_",
        "molecule.boilerplate.markers.argKindMarkers._",
        "molecule.boilerplate.markers.attrMarkers._",
        "molecule.boilerplate.markers.bidirectionalMarkers._",
        "molecule.boilerplate.markers.namespaceMarkers._",
        "molecule.boilerplate.markers.schemaOptionMarkers._",
      ),
      s"""trait $ns
         |
         |object $ns extends ${ns}_0 with FirstNS {
         |  final def apply(eid: Long, eids: Long*): ${ns}_0_OneT[e_, Long] with NsEids with Vs  = ???
         |  final def apply(eids: Iterable[Long])  : ${ns}_0_OneT[e_, Long] with NsEids with CVs = ???
         |}
         |
         |
         |$attrsObj
         |""".stripMargin
    )
  }

  def get: String = Template(ns, pkg, model.domain, body, imports)
}
