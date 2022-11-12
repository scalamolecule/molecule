package molecule.boilerplate.api


import molecule.boilerplate.ast.MoleculeModel._

trait Generic {

  protected lazy val e_man     : AttrOneManLong    = AttrOneManLong   ("Generic", "e")
  protected lazy val a_man     : AttrOneManString  = AttrOneManString ("Generic", "a")
  protected lazy val v_man     : AttrOneManString  = AttrOneManString ("Generic", "v")
  protected lazy val tx_man    : AttrOneManLong    = AttrOneManLong   ("Generic", "tx")
  protected lazy val txDate_man: AttrOneManDate    = AttrOneManDate   ("Generic", "txDate")
  protected lazy val txOp_man  : AttrOneManBoolean = AttrOneManBoolean("Generic", "txOp")

  protected lazy val e_tac     : AttrOneTacLong    = AttrOneTacLong   ("Generic", "e")
  protected lazy val a_tac     : AttrOneTacString  = AttrOneTacString ("Generic", "a")
  protected lazy val v_tac     : AttrOneTacString  = AttrOneTacString ("Generic", "v")
  protected lazy val tx_tac    : AttrOneTacLong    = AttrOneTacLong   ("Generic", "tx")
  protected lazy val txDate_tac: AttrOneTacDate    = AttrOneTacDate   ("Generic", "txDate")
  protected lazy val txOp_tac  : AttrOneTacBoolean = AttrOneTacBoolean("Generic", "txOp")
}
