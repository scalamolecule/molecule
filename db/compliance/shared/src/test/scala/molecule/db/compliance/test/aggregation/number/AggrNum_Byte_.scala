// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrNum_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[Byte] = tolerantByteEquality(toleranceByte)
    val sumAll = ((byte1 + byte2 + byte2 + byte3 + byte4) * 100 / byte100).asInstanceOf[Byte]
    val sum1   = ((byte1 + byte2) * 100 / byte100).asInstanceOf[Byte]
    val sum2   = ((byte2 + byte3 + byte4) * 100 / byte100).asInstanceOf[Byte]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + byte1).asInstanceOf[Byte]
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // 1 attribute
      _ <- Entity.byte(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.byte(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum)(byte1).query.get.map(_ ==> Nil)

      _ <- Entity.byte(sum).not(byte1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum).<=(byte1).query.get.map(_ ==> Nil)

      _ <- Entity.byte(sum).>(byte1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.byte(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.byte(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.byte(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(sum)(byte1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(sum).not(byte1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.byte(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(sum).<=(byte1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(sum).>(byte1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.byte(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(sum).>=(sum2).query.get.map(_ ==> List(b))

      // Include aggregated attribute too
      _ <- Entity.byte.a1.byte(sum).query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2, byte2 + byte2),
        (byte3, byte3),
        (byte4, byte4),
      ))
      _ <- Entity.byte(sum).byte.a1.query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2 + byte2, byte2),
        (byte3, byte3),
        (byte4, byte4),
      ))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = byte2.toString.toDouble // middle number
    val median1   = ((byte1 + byte2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = byte5.toString.toDouble // middle number
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte5),
        (2, byte9),
      )).transact

      // 1 attribute
      _ <- Entity.byte(median).query.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.byte(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.byte(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = byte2.toString.toDouble // middle number
      val median1   = ((byte1 + byte2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = byte5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte5),
          (2, byte9),
        )).transact

        // 1 attribute
        _ <- Entity.byte(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.byte(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.byte(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.byte(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.byte(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.byte(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.byte(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.byte(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.byte(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.byte(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.byte(median).>=(median2).query.get.map(_ ==> List(b))


        // Include aggregated attribute too
        _ <- Entity.byte.a1.byte(median).query.get.map { res =>
          res(0)._1 ==> byte1
          res(0)._2 ==~ byte1

          res(1)._1 ==> byte2
          res(1)._2 ==~ (byte2 + byte2).toDouble * 100 / 200.0

          res(2)._1 ==> byte5
          res(2)._2 ==~ byte5

          res(3)._1 ==> byte9
          res(3)._2 ==~ byte9
        }

        _ <- Entity.byte.byte(median).d1.query.get.map { res =>
          res(0)._1 ==> byte9
          res(0)._2 ==~ byte9

          res(1)._1 ==> byte5
          res(1)._2 ==~ byte5

          res(2)._1 ==> byte2
          res(2)._2 ==~ (byte2 + byte2).toDouble * 100 / 200.0

          res(3)._1 ==> byte1
          res(3)._2 ==~ byte1
        }
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((byte1 + byte2 + byte2 + byte3 + byte4).toDouble * 100 / 500.0)
    val avg1   = ((byte1 + byte2).toDouble * 100 / 200.0)
    val avg2   = ((byte2 + byte3 + byte4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // 1 attribute
      _ <- Entity.byte(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.byte(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.byte(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.byte(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.byte(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.byte(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.byte(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.byte(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.byte(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.byte(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.byte(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(avg).>=(avg2).query.get.map(_ ==> List(b))


      // Include aggregated attribute too
      _ <- Entity.byte.a1.byte(avg).query.get.map { res =>
        res(0)._1 ==> byte1
        res(0)._2 ==~ byte1

        res(1)._1 ==> byte2
        res(1)._2 ==~ (byte2 + byte2).toDouble * 100 / 200.0

        res(2)._1 ==> byte3
        res(2)._2 ==~ byte3

        res(3)._1 ==> byte4
        res(3)._2 ==~ byte4
      }

      _ <- Entity.byte.byte(avg).d1.query.get.map { res =>
        res(0)._1 ==> byte4
        res(0)._2 ==~ byte4

        res(1)._1 ==> byte3
        res(1)._2 ==~ byte3

        res(2)._1 ==> byte2
        res(2)._2 ==~ (byte2 + byte2).toDouble * 100 / 200.0

        res(3)._1 ==> byte1
        res(3)._2 ==~ byte1
      }
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(byte1, byte2, byte2, byte3, byte4)
    val variance1   = varianceOf(byte1, byte2)
    val variance2   = varianceOf(byte2, byte3, byte4)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // 1 attribute
      _ <- Entity.byte(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.byte(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.byte(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(byte1, byte2, byte2, byte3, byte4)
      val variance1   = varianceOf(byte1, byte2)
      val variance2   = varianceOf(byte2, byte3, byte4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        // 1 attribute
        _ <- Entity.byte(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.byte(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes

        _ <- Entity.i.a1.byte(variance)(variance1).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.byte(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(variance).not(1.0).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.byte(variance).not(variance1).query.get.map(_.head._2 ==~ variance2)

        _ <- Entity.i.a1.byte(variance).<(variance2).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.byte(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(variance).<=(variance2).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.byte(variance).<=(variance1).query.get.map(_.head._2 ==~ variance1)

        _ <- Entity.i.a1.byte(variance).>(variance1).query.get.map(_.head._2 ==~ variance2)
        _ <- Entity.i.a1.byte(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(variance).>=(variance1).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.byte(variance).>=(variance2).query.get.map(_.head._2 ==~ variance2)


        // Include aggregated attribute too
        _ <- Entity.byte.a1.byte(variance).query.get.map { res =>
          res(0)._1 ==> byte1
          res(0)._2 ==~ 0

          res(1)._1 ==> byte2
          res(1)._2 ==~ varianceOf(byte2, byte2) // always 0

          res(2)._1 ==> byte3
          res(2)._2 ==~ 0

          res(3)._1 ==> byte4
          res(3)._2 ==~ 0
        }

        _ <- Entity.byte.a2.byte(variance).d1.query.get.map { res =>
          res(0)._1 ==> byte1
          res(0)._2 ==~ varianceOf(byte2, byte2) // always 0

          res(1)._1 ==> byte2
          res(1)._2 ==~ 0

          res(2)._1 ==> byte3
          res(2)._2 ==~ 0

          res(3)._1 ==> byte4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(byte1, byte2, byte2, byte3, byte4)
    val stddev1   = stdDevOf(byte1, byte2)
    val stddev2   = stdDevOf(byte2, byte3, byte4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (1, byte2),
        (2, byte2),
        (2, byte3),
        (2, byte4),
      )).transact

      // 1 attribute
      _ <- Entity.byte(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.byte(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.byte(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(byte1, byte2, byte2, byte3, byte4)
      val stddev1   = stdDevOf(byte1, byte2)
      val stddev2   = stdDevOf(byte2, byte3, byte4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.byte.insert(List(
          (1, byte1),
          (1, byte2),
          (2, byte2),
          (2, byte3),
          (2, byte4),
        )).transact

        // 1 attribute
        _ <- Entity.byte(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.byte(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.byte(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.byte(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.byte(stddev)(stddev1).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.byte(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(stddev).not(1.0).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.byte(stddev).not(stddev1).query.get.map(_.head._2 ==~ stddev2)

        _ <- Entity.i.a1.byte(stddev).<(stddev2).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.byte(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(stddev).<=(stddev2).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.byte(stddev).<=(stddev1).query.get.map(_.head._2 ==~ stddev1)

        _ <- Entity.i.a1.byte(stddev).>(stddev1).query.get.map(_.head._2 ==~ stddev2)
        _ <- Entity.i.a1.byte(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.byte(stddev).>=(stddev1).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.byte(stddev).>=(stddev2).query.get.map(_.head._2 ==~ stddev2)


        // Include aggregated attribute too
        _ <- Entity.byte.a1.byte(stddev).query.get.map { res =>
          res(0)._1 ==> byte1
          res(0)._2 ==~ 0

          res(1)._1 ==> byte2
          res(1)._2 ==~ stdDevOf(byte2, byte2) // always 0

          res(2)._1 ==> byte3
          res(2)._2 ==~ 0

          res(3)._1 ==> byte4
          res(3)._2 ==~ 0
        }

        _ <- Entity.byte.a2.byte(stddev).d1.query.get.map { res =>
          res(0)._1 ==> byte1
          res(0)._2 ==~ stdDevOf(byte2, byte2) // always 0

          res(1)._1 ==> byte2
          res(1)._2 ==~ 0

          res(2)._1 ==> byte3
          res(2)._2 ==~ 0

          res(3)._1 ==> byte4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }
}