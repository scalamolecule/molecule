package molecule.boilerplate.api


import molecule.boilerplate.ast.Model._

trait Generic {
  protected lazy val eid_man   : AttrOneManLong = AttrOneManLong("_Generic", "eid")
  protected lazy val txId_man  : AttrOneManLong = AttrOneManLong("_Generic", "txId")
  protected lazy val txDate_man: AttrOneManDate = AttrOneManDate("_Generic", "txDate")

  protected lazy val eid_tac   : AttrOneTacLong = AttrOneTacLong("_Generic", "eid")
  protected lazy val txId_tac  : AttrOneTacLong = AttrOneTacLong("_Generic", "txId")
  protected lazy val txDate_tac: AttrOneTacDate = AttrOneTacDate("_Generic", "txDate")
}
