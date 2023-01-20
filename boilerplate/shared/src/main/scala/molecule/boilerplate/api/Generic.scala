package molecule.boilerplate.api


import molecule.boilerplate.ast.Model._

trait Generic {

  protected lazy val e_man     : AttrOneManLong    = AttrOneManLong   ("_Generic", "e")
  protected lazy val a_man     : AttrOneManString  = AttrOneManString ("_Generic", "a")
  protected lazy val v_man     : AttrOneManString  = AttrOneManString ("_Generic", "v")
  protected lazy val tx_man    : AttrOneManLong    = AttrOneManLong   ("_Generic", "tx")
  protected lazy val txDate_man: AttrOneManDate    = AttrOneManDate   ("_Generic", "txDate")
  protected lazy val txOp_man  : AttrOneManBoolean = AttrOneManBoolean("_Generic", "txOp")

  protected lazy val e_tac     : AttrOneTacLong    = AttrOneTacLong   ("_Generic", "e")
  protected lazy val a_tac     : AttrOneTacString  = AttrOneTacString ("_Generic", "a")
  protected lazy val v_tac     : AttrOneTacString  = AttrOneTacString ("_Generic", "v")
  protected lazy val tx_tac    : AttrOneTacLong    = AttrOneTacLong   ("_Generic", "tx")
  protected lazy val txDate_tac: AttrOneTacDate    = AttrOneTacDate   ("_Generic", "txDate")
  protected lazy val txOp_tac  : AttrOneTacBoolean = AttrOneTacBoolean("_Generic", "txOp")
}
