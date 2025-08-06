// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class AggrNum_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[BigInt] = tolerantBigIntEquality(toleranceBigInt)
    val sumAll = ((bigInt1 + bigInt2 + bigInt2 + bigInt3 + bigInt4) * 100 / bigInt100).asInstanceOf[BigInt]
    val sum1   = ((bigInt1 + bigInt2) * 100 / bigInt100).asInstanceOf[BigInt]
    val sum2   = ((bigInt2 + bigInt3 + bigInt4) * 100 / bigInt100).asInstanceOf[BigInt]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + bigInt1).asInstanceOf[BigInt]
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.bigInt(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum)(bigInt1).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(sum).not(bigInt1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum).<=(bigInt1).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(sum).>(bigInt1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.bigInt(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.bigInt(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.bigInt(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(sum)(bigInt1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(sum).not(bigInt1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigInt(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(sum).<=(bigInt1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(sum).>(bigInt1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigInt(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(sum).>=(sum2).query.get.map(_ ==> List(b))

      // Include aggregated attribute too
      _ <- Entity.bigInt.a1.bigInt(sum).query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2, bigInt2 + bigInt2),
        (bigInt3, bigInt3),
        (bigInt4, bigInt4),
      ))
      _ <- Entity.bigInt(sum).bigInt.a1.query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2 + bigInt2, bigInt2),
        (bigInt3, bigInt3),
        (bigInt4, bigInt4),
      ))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = bigInt2.toString.toDouble // middle number
    val median1   = ((bigInt1 + bigInt2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = bigInt5.toString.toDouble // middle number
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt5),
        (2, bigInt9),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(median).query.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.bigInt(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigInt(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = bigInt2.toString.toDouble // middle number
      val median1   = ((bigInt1 + bigInt2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = bigInt5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt5),
          (2, bigInt9),
        )).transact

        // 1 attribute
        _ <- Entity.bigInt(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.bigInt(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.bigInt(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigInt(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigInt(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.bigInt(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigInt(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.bigInt(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigInt(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.bigInt(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.bigInt(median).>=(median2).query.get.map(_ ==> List(b))


        // Include aggregated attribute too
        _ <- Entity.bigInt.a1.bigInt(median).query.get.map { res =>
          res(0)._1 ==> bigInt1
          res(0)._2 ==~ bigInt1

          res(1)._1 ==> bigInt2
          res(1)._2 ==~ (bigInt2 + bigInt2).toDouble * 100 / 200.0

          res(2)._1 ==> bigInt5
          res(2)._2 ==~ bigInt5

          res(3)._1 ==> bigInt9
          res(3)._2 ==~ bigInt9
        }

        _ <- Entity.bigInt.bigInt(median).d1.query.get.map { res =>
          res(0)._1 ==> bigInt9
          res(0)._2 ==~ bigInt9

          res(1)._1 ==> bigInt5
          res(1)._2 ==~ bigInt5

          res(2)._1 ==> bigInt2
          res(2)._2 ==~ (bigInt2 + bigInt2).toDouble * 100 / 200.0

          res(3)._1 ==> bigInt1
          res(3)._2 ==~ bigInt1
        }
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((bigInt1 + bigInt2 + bigInt2 + bigInt3 + bigInt4).toDouble * 100 / 500.0)
    val avg1   = ((bigInt1 + bigInt2).toDouble * 100 / 200.0)
    val avg2   = ((bigInt2 + bigInt3 + bigInt4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.bigInt(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.bigInt(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.bigInt(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.bigInt(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.bigInt(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigInt(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.bigInt(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.bigInt(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(avg).>=(avg2).query.get.map(_ ==> List(b))


      // Include aggregated attribute too
      _ <- Entity.bigInt.a1.bigInt(avg).query.get.map { res =>
        res(0)._1 ==> bigInt1
        res(0)._2 ==~ bigInt1

        res(1)._1 ==> bigInt2
        res(1)._2 ==~ (bigInt2 + bigInt2).toDouble * 100 / 200.0

        res(2)._1 ==> bigInt3
        res(2)._2 ==~ bigInt3

        res(3)._1 ==> bigInt4
        res(3)._2 ==~ bigInt4
      }

      _ <- Entity.bigInt.bigInt(avg).d1.query.get.map { res =>
        res(0)._1 ==> bigInt4
        res(0)._2 ==~ bigInt4

        res(1)._1 ==> bigInt3
        res(1)._2 ==~ bigInt3

        res(2)._1 ==> bigInt2
        res(2)._2 ==~ (bigInt2 + bigInt2).toDouble * 100 / 200.0

        res(3)._1 ==> bigInt1
        res(3)._2 ==~ bigInt1
      }
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
    val variance1   = varianceOf(bigInt1, bigInt2)
    val variance2   = varianceOf(bigInt2, bigInt3, bigInt4)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.bigInt(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigInt(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
      val variance1   = varianceOf(bigInt1, bigInt2)
      val variance2   = varianceOf(bigInt2, bigInt3, bigInt4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // 1 attribute
        _ <- Entity.bigInt(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.bigInt(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes

        _ <- Entity.i.a1.bigInt(variance)(variance1).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.bigInt(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(variance).not(1.0).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigInt(variance).not(variance1).query.get.map(_.head._2 ==~ variance2)

        _ <- Entity.i.a1.bigInt(variance).<(variance2).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.bigInt(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(variance).<=(variance2).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigInt(variance).<=(variance1).query.get.map(_.head._2 ==~ variance1)

        _ <- Entity.i.a1.bigInt(variance).>(variance1).query.get.map(_.head._2 ==~ variance2)
        _ <- Entity.i.a1.bigInt(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(variance).>=(variance1).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.bigInt(variance).>=(variance2).query.get.map(_.head._2 ==~ variance2)


        // Include aggregated attribute too
        _ <- Entity.bigInt.a1.bigInt(variance).query.get.map { res =>
          res(0)._1 ==> bigInt1
          res(0)._2 ==~ 0

          res(1)._1 ==> bigInt2
          res(1)._2 ==~ varianceOf(bigInt2, bigInt2) // always 0

          res(2)._1 ==> bigInt3
          res(2)._2 ==~ 0

          res(3)._1 ==> bigInt4
          res(3)._2 ==~ 0
        }

        _ <- Entity.bigInt.a2.bigInt(variance).d1.query.get.map { res =>
          res(0)._1 ==> bigInt1
          res(0)._2 ==~ varianceOf(bigInt2, bigInt2) // always 0

          res(1)._1 ==> bigInt2
          res(1)._2 ==~ 0

          res(2)._1 ==> bigInt3
          res(2)._2 ==~ 0

          res(3)._1 ==> bigInt4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
    val stddev1   = stdDevOf(bigInt1, bigInt2)
    val stddev2   = stdDevOf(bigInt2, bigInt3, bigInt4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.bigInt(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.bigInt(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(bigInt1, bigInt2, bigInt2, bigInt3, bigInt4)
      val stddev1   = stdDevOf(bigInt1, bigInt2)
      val stddev2   = stdDevOf(bigInt2, bigInt3, bigInt4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.bigInt.insert(List(
          (1, bigInt1),
          (1, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
          (2, bigInt4),
        )).transact

        // 1 attribute
        _ <- Entity.bigInt(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.bigInt(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.bigInt(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.bigInt(stddev)(stddev1).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.bigInt(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(stddev).not(1.0).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigInt(stddev).not(stddev1).query.get.map(_.head._2 ==~ stddev2)

        _ <- Entity.i.a1.bigInt(stddev).<(stddev2).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.bigInt(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(stddev).<=(stddev2).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigInt(stddev).<=(stddev1).query.get.map(_.head._2 ==~ stddev1)

        _ <- Entity.i.a1.bigInt(stddev).>(stddev1).query.get.map(_.head._2 ==~ stddev2)
        _ <- Entity.i.a1.bigInt(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.bigInt(stddev).>=(stddev1).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.bigInt(stddev).>=(stddev2).query.get.map(_.head._2 ==~ stddev2)


        // Include aggregated attribute too
        _ <- Entity.bigInt.a1.bigInt(stddev).query.get.map { res =>
          res(0)._1 ==> bigInt1
          res(0)._2 ==~ 0

          res(1)._1 ==> bigInt2
          res(1)._2 ==~ stdDevOf(bigInt2, bigInt2) // always 0

          res(2)._1 ==> bigInt3
          res(2)._2 ==~ 0

          res(3)._1 ==> bigInt4
          res(3)._2 ==~ 0
        }

        _ <- Entity.bigInt.a2.bigInt(stddev).d1.query.get.map { res =>
          res(0)._1 ==> bigInt1
          res(0)._2 ==~ stdDevOf(bigInt2, bigInt2) // always 0

          res(1)._1 ==> bigInt2
          res(1)._2 ==~ 0

          res(2)._1 ==> bigInt3
          res(2)._2 ==~ 0

          res(3)._1 ==> bigInt4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }
}