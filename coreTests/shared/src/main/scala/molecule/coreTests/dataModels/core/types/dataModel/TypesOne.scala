package molecule.coreTests.dataModels.core.types.dataModel

import molecule.DataModel

object TypesOne extends DataModel(3) {

  trait NsOne {
    val n          = oneInt
    val string     = oneString
    val int        = oneInt
    val long       = oneLong
    val float      = oneFloat
    val double     = oneDouble
    val boolean    = oneBoolean
    val bigInt     = oneBigInt
    val bigDecimal = oneBigDecimal
    val date       = oneDate
    val uuid       = oneUUID
    val uri        = oneURI
    val byte       = oneByte
    val short      = oneShort
    val char       = oneChar
  }
}