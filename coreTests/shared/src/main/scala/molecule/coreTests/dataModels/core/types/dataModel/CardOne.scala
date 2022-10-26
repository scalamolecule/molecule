package molecule.coreTests.dataModels.core.types.dataModel

import molecule.DataModel

object CardOne extends DataModel(3) {

  trait One {
    val n          = oneInt
    val string     = oneString.fulltext
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
    val char       = oneChar
    val byte       = oneByte
    val short      = oneShort
  }
}