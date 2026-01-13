package molecule.db.compliance.test.aggregation.number

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class AggrNum_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[Int] = tolerantIntEquality(toleranceInt)
    val sumAll = ((int1 + int2 + int2 + int3 + int4) * 100 / int100).asInstanceOf[Int]
    val sum1   = ((int1 + int2) * 100 / int100).asInstanceOf[Int]
    val sum2   = ((int2 + int3 + int4) * 100 / int100).asInstanceOf[Int]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + int1).asInstanceOf[Int]
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // 1 attribute
      _ <- Entity.int(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.int(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum)(int1).query.get.map(_ ==> Nil)

      _ <- Entity.int(sum).not(int1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum).<=(int1).query.get.map(_ ==> Nil)

      _ <- Entity.int(sum).>(int1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.int(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.int(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.int(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(sum)(int1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(sum).not(int1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.int(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(sum).<=(int1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(sum).>(int1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.int(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(sum).>=(sum2).query.get.map(_ ==> List(b))

      // Include aggregated attribute too
      _ <- Entity.int.a1.int(sum).query.get.map(_ ==> List(
        (int1, int1),
        (int2, int2 + int2),
        (int3, int3),
        (int4, int4),
      ))
      _ <- Entity.int(sum).int.a1.query.get.map(_ ==> List(
        (int1, int1),
        (int2 + int2, int2),
        (int3, int3),
        (int4, int4),
      ))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = int2.toString.toDouble // middle number
    val median1   = ((int1 + int2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = int5.toString.toDouble // middle number
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int5),
        (2, int9),
      )).transact

      // 1 attribute
      _ <- Entity.int(median).query.i.get.map(_.head ==~ medianAll)

      // n attributes
      _ <- Entity.i.a1.int(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.int(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val medianAll = int2.toString.toDouble // middle number
      val median1   = ((int1 + int2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
      val median2   = int5.toString.toDouble // middle number
      val a         = (1, median1)
      val b         = (2, median2)

      for {
        _ <- Entity.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int5),
          (2, int9),
        )).transact

        // 1 attribute
        _ <- Entity.int(median)(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(median).not(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median).not(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median).<(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(median).>(1.0).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median).>(medianAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
        _ <- Entity.int(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.int(median)(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.int(median)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(median).not(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.int(median).not(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.int(median).<(median2).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.int(median).<(median1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(median).<=(median1).query.get.map(_ ==> List(a))
        _ <- Entity.i.a1.int(median).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(median).>(1.0).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.int(median).>(median1).query.get.map(_ ==> List(b))

        _ <- Entity.i.a1.int(median).>=(median1).query.get.map(_ ==> List(a, b))
        _ <- Entity.i.a1.int(median).>=(median2).query.get.map(_ ==> List(b))


        // Include aggregated attribute too
        _ <- Entity.int.a1.int(median).query.get.map { res =>
          res(0)._1 ==> int1
          res(0)._2 ==~ int1

          res(1)._1 ==> int2
          res(1)._2 ==~ (int2 + int2).toDouble * 100 / 200.0

          res(2)._1 ==> int5
          res(2)._2 ==~ int5

          res(3)._1 ==> int9
          res(3)._2 ==~ int9
        }

        _ <- Entity.int.int(median).d1.query.get.map { res =>
          res(0)._1 ==> int9
          res(0)._2 ==~ int9

          res(1)._1 ==> int5
          res(1)._2 ==~ int5

          res(2)._1 ==> int2
          res(2)._2 ==~ (int2 + int2).toDouble * 100 / 200.0

          res(3)._1 ==> int1
          res(3)._2 ==~ int1
        }
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((int1 + int2 + int2 + int3 + int4).toDouble * 100 / 500.0)
    val avg1   = ((int1 + int2).toDouble * 100 / 200.0)
    val avg2   = ((int2 + int3 + int4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // 1 attribute
      _ <- Entity.int(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.int(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.int(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.int(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.int(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.int(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.int(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.int(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.int(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.int(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.int(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(avg).>=(avg2).query.get.map(_ ==> List(b))


      // Include aggregated attribute too
      _ <- Entity.int.a1.int(avg).query.get.map { res =>
        res(0)._1 ==> int1
        res(0)._2 ==~ int1

        res(1)._1 ==> int2
        res(1)._2 ==~ (int2 + int2).toDouble * 100 / 200.0

        res(2)._1 ==> int3
        res(2)._2 ==~ int3

        res(3)._1 ==> int4
        res(3)._2 ==~ int4
      }

      _ <- Entity.int.int(avg).d1.query.get.map { res =>
        res(0)._1 ==> int4
        res(0)._2 ==~ int4

        res(1)._1 ==> int3
        res(1)._2 ==~ int3

        res(2)._1 ==> int2
        res(2)._2 ==~ (int2 + int2).toDouble * 100 / 200.0

        res(3)._1 ==> int1
        res(3)._2 ==~ int1
      }
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(int1, int2, int2, int3, int4)
    val variance1   = varianceOf(int1, int2)
    val variance2   = varianceOf(int2, int3, int4)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // 1 attribute
      _ <- Entity.int(variance).query.get.map(_.head ==~ varianceAll)

      // n attributes
      _ <- Entity.i.a1.int(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }
    } yield ()
  }


  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.int(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val varianceAll = varianceOf(int1, int2, int2, int3, int4)
      val variance1   = varianceOf(int1, int2)
      val variance2   = varianceOf(int2, int3, int4)
      val a           = (1, variance1)
      val b           = (2, variance2)
      for {
        _ <- Entity.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        // 1 attribute
        _ <- Entity.int(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance).not(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance).<(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance).>(varianceAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
        _ <- Entity.int(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes

        _ <- Entity.i.a1.int(variance)(variance1).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.int(variance)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(variance).not(1.0).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.int(variance).not(variance1).query.get.map(_.head._2 ==~ variance2)

        _ <- Entity.i.a1.int(variance).<(variance2).query.get.map(_.head._2 ==~ variance1)
        _ <- Entity.i.a1.int(variance).<(variance1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(variance).<=(variance2).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.int(variance).<=(variance1).query.get.map(_.head._2 ==~ variance1)

        _ <- Entity.i.a1.int(variance).>(variance1).query.get.map(_.head._2 ==~ variance2)
        _ <- Entity.i.a1.int(variance).>(variance2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(variance).>=(variance1).query.get.map { res =>
          res(0)._2 ==~ variance1
          res(1)._2 ==~ variance2
        }
        _ <- Entity.i.a1.int(variance).>=(variance2).query.get.map(_.head._2 ==~ variance2)


        // Include aggregated attribute too
        _ <- Entity.int.a1.int(variance).query.get.map { res =>
          res(0)._1 ==> int1
          res(0)._2 ==~ 0

          res(1)._1 ==> int2
          res(1)._2 ==~ varianceOf(int2, int2) // always 0

          res(2)._1 ==> int3
          res(2)._2 ==~ 0

          res(3)._1 ==> int4
          res(3)._2 ==~ 0
        }

        _ <- Entity.int.a2.int(variance).d1.query.get.map { res =>
          res(0)._1 ==> int1
          res(0)._2 ==~ varianceOf(int2, int2) // always 0

          res(1)._1 ==> int2
          res(1)._2 ==~ 0

          res(2)._1 ==> int3
          res(2)._2 ==~ 0

          res(3)._1 ==> int4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(int1, int2, int2, int3, int4)
    val stddev1   = stdDevOf(int1, int2)
    val stddev2   = stdDevOf(int2, int3, int4)
    val a         = (1, stddev1)
    val b         = (2, stddev2)
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (1, int2),
        (2, int2),
        (2, int3),
        (2, int4),
      )).transact

      // 1 attribute
      _ <- Entity.int(stddev).query.get.map(_.head ==~ stddevAll)

      // n attributes
      _ <- Entity.i.a1.int(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.int(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val stddevAll = stdDevOf(int1, int2, int2, int3, int4)
      val stddev1   = stdDevOf(int1, int2)
      val stddev2   = stdDevOf(int2, int3, int4)
      val a         = (1, stddev1)
      val b         = (2, stddev2)
      for {
        _ <- Entity.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        // 1 attribute
        _ <- Entity.int(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev).not(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev).<(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev).<=(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.int(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev).>(stddevAll).query.get.map(_ ==> Nil)

        _ <- Entity.int(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
        _ <- Entity.int(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


        // n attributes
        _ <- Entity.i.a1.int(stddev)(stddev1).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.int(stddev)(1.0).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(stddev).not(1.0).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.int(stddev).not(stddev1).query.get.map(_.head._2 ==~ stddev2)

        _ <- Entity.i.a1.int(stddev).<(stddev2).query.get.map(_.head._2 ==~ stddev1)
        _ <- Entity.i.a1.int(stddev).<(stddev1).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(stddev).<=(stddev2).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.int(stddev).<=(stddev1).query.get.map(_.head._2 ==~ stddev1)

        _ <- Entity.i.a1.int(stddev).>(stddev1).query.get.map(_.head._2 ==~ stddev2)
        _ <- Entity.i.a1.int(stddev).>(stddev2).query.get.map(_ ==> Nil)

        _ <- Entity.i.a1.int(stddev).>=(stddev1).query.get.map { res =>
          res(0)._2 ==~ stddev1
          res(1)._2 ==~ stddev2
        }
        _ <- Entity.i.a1.int(stddev).>=(stddev2).query.get.map(_.head._2 ==~ stddev2)


        // Include aggregated attribute too
        _ <- Entity.int.a1.int(stddev).query.get.map { res =>
          res(0)._1 ==> int1
          res(0)._2 ==~ 0

          res(1)._1 ==> int2
          res(1)._2 ==~ stdDevOf(int2, int2) // always 0

          res(2)._1 ==> int3
          res(2)._2 ==~ 0

          res(3)._1 ==> int4
          res(3)._2 ==~ 0
        }

        _ <- Entity.int.a2.int(stddev).d1.query.get.map { res =>
          res(0)._1 ==> int1
          res(0)._2 ==~ stdDevOf(int2, int2) // always 0

          res(1)._1 ==> int2
          res(1)._2 ==~ 0

          res(2)._1 ==> int3
          res(2)._2 ==~ 0

          res(3)._1 ==> int4
          res(3)._2 ==~ 0
        }
      } yield ()
    }
  }
}