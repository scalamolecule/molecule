package molecule.base.dataModel

import molecule.DataModel

object Partitions extends DataModel(3) {

  object partA {
    trait Ns {
      val int    = oneInt
      val string = oneString
      val ref1   = one[Ref1]
    }
    trait Ref1 {
      val str1 = oneString.description("foo")
      val int1 = oneInt.unique.description("bar").alias("hej")
    }
  }

  object partB {
    trait Ns {
      val int    = oneInt
      val string = oneString
      val ref1   = one[Ref1]
    }
    trait Ref1 {
      val str1 = oneString.description("foo")
      val int1 = oneInt.unique.description("bar").alias("hej")
    }
  }
}

