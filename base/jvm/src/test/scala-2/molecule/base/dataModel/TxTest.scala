package molecule.base.dataModel

import molecule.DataModel


object TxTest extends DataModel(6) {

  trait Tx extends TxBase {
    val myTxAttr = oneInt
  }

  trait Ns {
//    val id = oneLong
//    val tx = one[Tx]
    val i  = oneInt
    val s  = oneString
  }
}
