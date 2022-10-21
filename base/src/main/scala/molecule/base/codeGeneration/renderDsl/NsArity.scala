package molecule.base.codeGeneration.renderDsl

import molecule.base.codeGeneration.parseDataModel.parserAST.{Namespace, ParseModel}
import molecule.base.codeGeneration.parseDataModel.parserAST._

case class NsArity(model: ParseModel, namespace: Namespace, arity: Int)
  extends Formatting(model, namespace, arity) {

  val compositeInit = s"CompositeInit_$arity${`[A..V]`}"
  val txNs          = s"Tx_$arity${`[A..V]`}"

  // Collect traits for each level for this namespace/arity
  val arityTraits = NsArityLevel(model, namespace, arity).get

  val (imports, body) = if (isGeneric) {
    val exprImport = if (isDatom) Nil else Seq("molecule.boilerplate.api.expression._")
    (
      exprImport ++ Seq(
        "java.util.Date",
        "molecule.boilerplate.markers.namespaceMarkers._",
        "scala.language.higherKinds",
        s"$pkg.$domain.${ns}_._",
      ),
      arityTraits
    )

  } else {
    (
      (if (arity == maxArity) Nil else Seq(
        s"molecule.boilerplate.api.nested._",
      )) ++ Seq(
        "java.util.Date",
        "java.net.URI",
        "java.util.UUID",
        s"molecule.boilerplate.api.compositeInit.CompositeInit_$arity",
        s"molecule.boilerplate.api.expression._",
        s"molecule.boilerplate.api.generic.dsl.Datom._",
        s"molecule.boilerplate.api.txMetaData._",
        s"molecule.boilerplate.markers.bidirectionalMarkers._",
        s"molecule.boilerplate.markers.namespaceMarkers._",
        "scala.language.higherKinds",
        s"$pkg.$domain.${ns}_._",
      ),

      s"""trait $ns_0${`[A..V]`}
         |  extends $NS
         |    with $compositeInit
         |    with $txNs
         |    with $arityTraits""".stripMargin
    )
  }

  def get: String = Template(ns, pkg, model.domain, body, imports)
}
