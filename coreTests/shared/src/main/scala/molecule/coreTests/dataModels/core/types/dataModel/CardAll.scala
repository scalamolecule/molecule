package molecule.coreTests.dataModels.core.types.dataModel

import molecule.DataModel

object CardAll extends DataModel(3) {

  trait NsAll {
    val n  = oneInt
    val nn = setInt

    // Cardinality one
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

    // Cardinality many (Set)
    val strings     = setString
    val ints        = setInt
    val longs       = setLong
    val floats      = setFloat
    val doubles     = setDouble
    val booleans    = setBoolean
    val bigInts     = setBigInt
    val bigDecimals = setBigDecimal
    val dates       = setDate
    val uuids       = setUUID
    val uris        = setURI
    val bytes       = setByte
    val shorts      = setShort
    val chars       = setChar
  }
}