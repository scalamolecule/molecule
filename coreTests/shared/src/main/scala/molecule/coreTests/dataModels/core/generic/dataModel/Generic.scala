//package molecule.coreTests.dataModels.core.generic.dataModel
//
//import molecule.DataModel
//
//object Generic extends DataModel(4) {
//
//  trait Generic {
//    val e      = oneLong.descr("Entity id")
//    val a      = oneString.descr("Attribute name")
//    val v      = oneString.descr("String representation of any type of value")
//    val tx     = oneLong.descr("Transaction entity id")
//    val txDate = oneDate.descr("Transaction time as java.util.Date")
//    val txOp   = oneBoolean.descr("Transaction operation (add: True or retract: False")
//  }
//}
