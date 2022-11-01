package molecule.coreTests.dataModels.core.types.dataModel

import molecule.DataModel

object CardSet extends DataModel(3) {

  trait NsSet {
    val n          = oneInt
    val string     = setString.fulltext
    val int        = setInt
    val long       = setLong
    val float      = setFloat
    val double     = setDouble
    val boolean    = setBoolean
    val bigInt     = setBigInt
    val bigDecimal = setBigDecimal
    val date       = setDate
    val uuid       = setUUID
    val uri        = setURI
    val byte       = setByte
    val short      = setShort
    val char       = setChar
  }
}