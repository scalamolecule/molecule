package molecule.core.macros

import scala.reflect.macros.blackbox


/** Macro to make transaction function invocations. */
class TxFunctionCall(val c: blackbox.Context) extends MacroHelpers {

  import c.universe._

  val q = InspectMacro("TxFunctionCall", 1)

  private[this] def resolve(txFnCall: Tree, txMolecules: Seq[Tree], op: String): Tree = {
    val q"$owner.$txFn(..$args)(..$conn)" = txFnCall
    val ownerType                         = owner.tpe.toString
    val txFnIdentifier                    = ownerType.take(ownerType.length - 4) + txFn + "__txfn"
    op match {
      case "sync"    => q"_root_.molecule.core.api.TxFunctions.txFnCall($txFnIdentifier, Seq(..$txMolecules), ..$args)"
      case "async"   => q"_root_.molecule.core.api.TxFunctions.asyncTxFnCall($txFnIdentifier, Seq(..$txMolecules), ..$args)"
      case "inspect" => q"_root_.molecule.core.api.TxFunctions.inspectTxFnCall($txFnIdentifier, Seq(..$txMolecules), ..$args)"
    }
  }

  final def txFnCall(txFnCall: Tree, txMolecules: Tree*): Tree = resolve(txFnCall, txMolecules, "sync")

  final def asyncTxFnCall(txFnCall: Tree, txMolecules: Tree*): Tree = resolve(txFnCall, txMolecules, "async")

  final def inspectTxFnCall(txFnCall: Tree, txMolecules: Tree*): Tree = resolve(txFnCall, txMolecules, "inspect")

}