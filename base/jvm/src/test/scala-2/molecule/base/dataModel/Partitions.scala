package molecule.base.dataModel

import molecule.DataModel

object Partitions extends DataModel(3) {

//  trait Tx extends TxBase {
//    val myTxAttr = oneInt
//  }

  object partA {
    trait Ns {
      val int    = oneInt
      val string = oneString
      val ref1   = one[Ref1]
    }
    trait Ref1 {
      val str1 = oneString("foo")
      val int1 = oneInt.unique.descr("bar").alias("hej")
    }
  }

  object partB {
    trait Ns {
      val int    = oneInt
      val string = oneString
      val ref1   = one[Ref1]
    }
    trait Ref1 {
      val str1 = oneString("foo")
      val int1 = oneInt.unique.descr("bar").alias("hej")
    }
  }
}

