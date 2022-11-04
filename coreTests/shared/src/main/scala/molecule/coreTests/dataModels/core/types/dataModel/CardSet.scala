package molecule.coreTests.dataModels.core.types.dataModel

import molecule.DataModel

object CardSet extends DataModel(3) {

  trait NsSet {
    val n           = oneInt
    val nn          = setInt
    val strings     = setString.fulltext
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