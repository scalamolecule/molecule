//package molecule.base.dataModel
//
//import molecule.DataModel
//
//object A extends DataModel(3) {
//
//  //  trait Ns {
//  //    //      val str   = oneString.descr("foo").alias("xxx")
//  //    //      val int   = oneInt.unique.descr("bar").validation(_ > 7)
//  //    val int     = oneInt
//  //    val string  = oneString
//  //    val ints    = setInt
//  //    val strings = setString
//  //    val ref1   = one[Ref1]
//  //  }
//  //
//  //  trait Ref1 {
//  //    val str1 = oneString.descr("foo")
//  //    val int1 = oneInt.unique.descr("bar").alias("hej")
//  //  }
//
//  //    val t      = oneLong.descr("Transaction time t")
//
////  trait Datom {
////    val e      = oneLong.descr("Entity id")
////    val a      = oneString.descr("Attribute name")
////    val v      = oneString.descr("String representation of any type of value")
////    val tx     = oneLong.descr("Transaction entity id")
////    val txDate = oneDate.descr("Transaction time as java.util.Date")
////    val txOp   = oneBoolean.descr("Transaction operation (add: True or retract: False")
////  }
//
//  trait Ns {
//    val n     = oneInt
//    val int   = oneInt
//    val str   = oneString
//    val ref1  = one[Ref1]
//    val ref2  = one[Ref2]
//    val refs1 = many[Ref1]
//  }
//
//  trait Ref1 {
//    val n1    = oneInt
//    val int1  = oneInt
//    val str1  = oneString
//    val ref2  = one[Ref2]
//    val refs2 = many[Ref2]
//  }
//
//  trait Ref2 {
//    val n2    = oneInt
//    val int2  = oneInt
//    val str2  = oneString
//    val ref3  = one[Ref3]
//    val refs3 = many[Ref3]
//  }
//
//  trait Ref3 {
//    val n3    = oneInt
//    val int3  = oneInt
//    val str3  = oneString
//    val ref4  = one[Ref4]
//    val refs4 = many[Ref4]
//  }
//
//  trait Ref4 {
//    val n4   = oneInt
//    val int4 = oneInt
//    val str4 = oneString
//  }
//}
//
