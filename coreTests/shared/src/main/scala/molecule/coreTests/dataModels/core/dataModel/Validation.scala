package molecule.coreTests.dataModels.core.dataModel

import java.util.Date
import molecule.DataModel

object Validation extends DataModel(4) {

  trait Format {
    val noErrorMsg               = oneInt.validate(_ > 2)
    val errorMsg                 = oneInt.validate(
      _ > 2,
      "One-line error msg"
    )
    val errorMsgWithValue        = oneInt.validate(
      _ > 2,
      "One-line error msg. Found _value_"
    )
    val errorMsgWithValueQuoted  = oneString.validate(
      _.startsWith("hello"),
      "Expected hello. Found \"_value_\"."
    )
    val errorMsgWithValueQuoted2 = oneString.validate(
      _.startsWith("hello"),
      """Expected hello. Found "_value_"."""
    )
    val multilineErrorMsg        = oneInt.validate((v: Int) => v.>(2),
      """Long error explanation
        |with multiple lines""".stripMargin
    )
    val multilineMsgWithValue    = oneInt.validate((v: Int) => v.>(2),
      """Validation failed:
        |Input value _value_ is not bigger than 2.""".stripMargin
    )
    val multilineMsgWithValue2   = oneInt.validate((v: Int) => v.>(2),
      """Validation failed:
        |Input value "_value_" is not bigger than 2.""".stripMargin
    )

    val multiLine  = oneInt.validate { v =>
      val data   = 22
      val result = data % 10
      v > result
    }
    val multiLine2 = oneInt.validate(
      { v =>
        val data   = 22
        val result = {
          data % 10
        }
        v > result
      },
      "One-line error msg"
    )
    val multiLine3 = oneInt.validate({ v =>
      val data   = 22
      val result = data % 10
      v > result
    },
      """Long error explanation
        |with multiple lines""".stripMargin
    )


    val logic = oneInt.validate(
      v => v >= 3 && v <= 9 && v != 7 && v % 2 == 1,
      "Value must be an odd number between 3 and 9 but not 7"
    )

    val multipleErrors = oneInt.validate {
      case v if v > 2  => "Number must be bigger than 2. Found: _value_"
      case v if v < 10 => "Number must be smaller than 10. Found: _value_"
      case v if {
        val count = (3 to 9).length
        v != count
      }                => "Number must not be count of allowed numbers. Found: _value_"
      case v if {
        // Comments in code blocks are transferred to boilerplate code
        val divider = 2
        v % divider == 1
      }                =>
        """Number must
          |be odd. Found: _value_""".stripMargin
    }
  }

  trait Type {
    val string     = oneString.validate(_ > "a")
    val int        = oneInt.validate(_ > 1)
    val long       = oneLong.validate(_ > 1L)
    val float      = oneFloat.validate(_ > 1.1f)
    val double     = oneDouble.validate(_ > 1.1)
    val boolean    = oneBoolean.validate(_ == false)
    val bigInt     = oneBigInt.validate(_ > BigInt(1))
    val bigDecimal = oneBigDecimal.validate(_ > BigDecimal(1.1))
    val date       = oneDate.validate(_.after(new Date(993942000000L))) // "2001-07-01 00:00:00"
    val uuid       = oneUUID.validate(_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val uri        = oneURI.validate(_.toString.length > 1)
    val byte       = oneByte.validate(_ > 1)
    val short      = oneShort.validate(_ > 1)
    val char       = oneChar.validate(_ > 'a')
    val ref        = one[Strings].validate(_ > 1L)

    val strings     = setString.validate(_ > "a")
    val ints        = setInt.validate(_ > 1)
    val longs       = setLong.validate(_ > 1L)
    val floats      = setFloat.validate(_ > 1.1f)
    val doubles     = setDouble.validate(_ > 1.1)
    val booleans    = setBoolean.validate(_ == false)
    val bigInts     = setBigInt.validate(_ > BigInt(1))
    val bigDecimals = setBigDecimal.validate(_ > BigDecimal(1.1))
    val dates       = setDate.validate(_.after(new Date(993942000000L))) // "2001-07-01 00:00:00"
    val uuids       = setUUID.validate(_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val uris        = setURI.validate(_.toString.length > 1)
    val bytes       = setByte.validate(_ > 1)
    val shorts      = setShort.validate(_ > 1)
    val chars       = setChar.validate(_ > 'a')
    val refs        = many[Strings].validate(_ > 1L)
  }

  trait Strings {
    val email        = oneString.email
    val emailWithMsg = oneString.email("Please provide a real email")

    val regex        = oneString.regex("^[a-zA-Z0-9]+$")
    val regexWithMsg = oneString.regex(
      "^[a-zA-Z0-9]+$",
      "Username cannot contain special characters."
    )
    val enums        = many[Allowed]
  }

  trait Allowed {
    val luckyNumber  = oneInt.allowed(7, 9, 13)
    val luckyNumber2 = oneInt.allowed(
      Seq(7, 9, 13),
      "Lucky number can only be 7, 9 or 13"
    )
  }


  // todo --------------------------------------

  //  trait AttrValue {
  //    val min1 = oneInt
  //    val mid1 = oneInt.validate(i => i > min1.value && i < max1.value)
  //    val max1 = oneInt
  //
  //    // Same as
  //    val min2 = oneInt.validate(_ < mid2.value)
  //    val mid2 = oneInt.validate(_ < max2.value)
  //    val max2 = oneInt
  //
  //    // Same as
  //    val min3 = oneInt.validate(_ < mid2.value)
  //    val mid3 = oneInt
  //    val max3 = oneInt.validate(_ > mid2.value)
  //
  //    val compareWithCalculation = oneInt.validate(_ > date.value.getTime.toInt)
  //    val date                   = oneDate
  //
  //    val int5 = oneInt.validate(_ > ints.value) // hmm, shouldn't be possible
  //    val ints = setInt
  //  }
  //
  //  trait Mandatory {
  //    val username = oneString.mandatory
  //    val email    = oneInt.mandatory
  //    val age      = oneString
  //  }
  //
  //  trait Require {
  //    // tuple enforcement
  //    val lat  = oneInt.require(long)
  //    val long = oneInt.descr("(will reversely also require `lat`")
  //
  //    // triple enforcement
  //    val x = oneInt.require(y, z)
  //    val y = oneInt.descr("(will reversely also require `x` and `z`")
  //    val z = oneInt.descr("(will reversely also require `x` and `y`")
  //
  //    // Asymmetric requirement
  //    val required  = oneString.descr("Doesn't need requiring attribute value")
  //    val requiring = oneString.require(required).descr("Needs required attribute value")
  //  }
}