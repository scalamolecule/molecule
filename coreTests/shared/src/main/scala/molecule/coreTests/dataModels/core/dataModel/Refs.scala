package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Refs extends DataModel(10) {

  trait Ns {
    val i          = oneInt
    val s          = oneString
    val b          = oneBoolean
    val r1         = one[R1]
    val rs1        = many[R1]
    val self       = one[Ns]
    val owned1     = one[R1].owner
    val ownedMany1 = many[R1].owner

  }

  trait R1 {
    val i          = oneInt
    val s          = oneString
    val r2         = one[R2]
    val r2a        = one[R2]
    val rs2        = many[R2]
    val owned2     = one[R2].owner
    val ownedMany2 = many[R2].owner
  }

  trait R2 {
    val i   = oneInt
    val s   = oneString
    val ii  = setInt
    val r3  = one[R3]
    val r3a = one[R3]
    val rs3 = many[R3]
  }

  trait R3 {
    val i   = oneInt
    val s   = oneString
    val r4  = one[R4]
    val r4a = one[R4]
    val rs4 = many[R4]
  }

  trait R4 {
    val i   = oneInt
    val s   = oneString
    val r5  = one[R5]
    val rs5 = many[R5]
  }

  trait R5 {
    val i   = oneInt
    val s   = oneString
    val r6  = one[R6]
    val rs6 = many[R6]
  }

  trait R6 {
    val i   = oneInt
    val s   = oneString
    val r7  = one[R7]
    val rs7 = many[R7]
  }

  trait R7 {
    val i = oneInt
    val s = oneString
  }
}
