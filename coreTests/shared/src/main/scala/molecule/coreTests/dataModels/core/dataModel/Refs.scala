package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Refs extends DataModel(10) {

  trait Ns {
    val n    = oneInt
    val int  = oneInt
    val str  = oneString
    val ints = setInt
    val strs = setString
    val r1   = one[R1]
    val rs1  = many[R1]
  }

  trait R1 {
    val n1    = oneInt
    val int1  = oneInt
    val str1  = oneString
    val ints1 = setInt
    val strs1 = setString
    val r2    = one[R2]
    val rs2   = many[R2]
  }

  trait R2 {
    val n2    = oneInt
    val int2  = oneInt
    val str2  = oneString
    val ints2 = setInt
    val strs2 = setString
    val r3    = one[R3]
    val rs3   = many[R3]
  }

  trait R3 {
    val n3    = oneInt
    val int3  = oneInt
    val str3  = oneString
    val ints3 = setInt
    val strs3 = setString
    val r4    = one[R4]
    val rs4   = many[R4]
  }

  trait R4 {
    val n4    = oneInt
    val int4  = oneInt
    val str4  = oneString
    val ints4 = setInt
    val strs4 = setString
    val r5    = one[R5]
    val rs5   = many[R5]
  }

  trait R5 {
    val n5    = oneInt
    val int5  = oneInt
    val str5  = oneString
    val ints5 = setInt
    val strs5 = setString
    val r6    = one[R6]
    val rs6   = many[R6]
  }

  trait R6 {
    val n6    = oneInt
    val int6  = oneInt
    val str6  = oneString
    val ints6 = setInt
    val strs6 = setString
    val r7    = one[R7]
    val rs7   = many[R7]
  }

  trait R7 {
    val n7    = oneInt
    val int7  = oneInt
    val str7  = oneString
    val ints7 = setInt
    val strs7 = setString
  }
}
