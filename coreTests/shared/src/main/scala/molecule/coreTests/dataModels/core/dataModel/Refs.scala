package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Refs extends DataModel(10) {

  trait Ns {
    val n     = oneInt
    val int   = oneInt
    val str   = oneString
    val ref1  = one[Ref1]
    val refs1 = many[Ref1]
  }

  trait Ref1 {
    val n1    = oneInt
    val int1  = oneInt
    val str1  = oneString
    val ref2  = one[Ref2]
    val refs2 = many[Ref2]
  }

  trait Ref2 {
    val n2    = oneInt
    val int2  = oneInt
    val str2  = oneString
    val ref3  = one[Ref3]
    val refs3 = many[Ref3]
  }

  trait Ref3 {
    val n3    = oneInt
    val int3  = oneInt
    val str3  = oneString
    val ref4  = one[Ref4]
    val refs4 = many[Ref4]
  }

  trait Ref4 {
    val n4   = oneInt
    val int4 = oneInt
    val str4 = oneString
  }
}
