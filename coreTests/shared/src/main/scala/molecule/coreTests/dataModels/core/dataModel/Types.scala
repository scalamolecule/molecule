package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Types extends DataModel(3) {

  trait Ns {
    val n  = oneInt

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
    val ref        = one[Ref]

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
    val refs        = many[Ref]
  }

  trait Ref {
    val n = oneInt
    val nss = many[Ns]
  }
}