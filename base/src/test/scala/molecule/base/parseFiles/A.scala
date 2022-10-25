package molecule.base.parseFiles

import molecule.DataModel

object A extends DataModel(3) {

  object aa {
    trait Ns {
      val str   = oneString.descr("foo").alias("xxx")
      val int   = oneInt.unique.descr("bar").validation(_ > 7)
      val hejsa = one[bb.Ref1]
    }
  }

  object bb {
    trait Ref1 {
      val str1 = oneString.descr("foo")
      val int1 = oneInt.unique.descr("bar").alias("hej")
    }
  }
}
