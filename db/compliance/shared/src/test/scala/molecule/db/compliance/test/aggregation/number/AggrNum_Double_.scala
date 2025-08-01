// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrNum_Double_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val sumAll = ((double1 + double2 + double2 + double3 + double4) * 100 / double100).asInstanceOf[Double]
    val sum1   = ((double1 + double2) * 100 / double100).asInstanceOf[Double]
    val sum2   = ((double2 + double3 + double4) * 100 / double100).asInstanceOf[Double]
    val a      = (1, sum1)
    val b      = (2, sum2)
    val bigger = (sumAll + double1).asInstanceOf[Double]
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double3),
        (2, double4),
      )).transact

      // 1 attribute
      _ <- Entity.double(sum).query.get.map(_.head ==~ sumAll)

      _ <- Entity.double(sum)(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum)(double1).query.get.map(_ ==> Nil)

      _ <- Entity.double(sum).not(double1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum).not(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(sum).<(bigger).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum).<(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(sum).<=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum).<=(double1).query.get.map(_ ==> Nil)

      _ <- Entity.double(sum).>(double1).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum).>(sumAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(sum).>=(sumAll).query.get.map(_.head ==~ sumAll)
      _ <- Entity.double(sum).>=(bigger).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(sum).query.get.map { res =>
        res(0)._2 ==~ sum1
        res(1)._2 ==~ sum2
      }

      _ <- Entity.i.a1.double(sum)(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(sum)(double1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(sum).not(double1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(sum).not(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(sum).<(sum2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(sum).<(sum1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(sum).<=(sum1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(sum).<=(double1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(sum).>(double1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(sum).>(sum1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(sum).>=(sum1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(sum).>=(sum2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val medianAll = double2.toString.toDouble // middle number
    val median1   = ((double1 + double2).toDouble * 100) / 200.0 // average of 2 middle numbers (avoid rounding errors)
    val median2   = double5.toString.toDouble // middle number
    val a         = (1, median1)
    val b         = (2, median2)
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double5),
        (2, double9),
      )).transact

      // 1 attribute
      _ <- Entity.double(median).query.get.map(_.head ==~ medianAll)

      _ <- Entity.double(median)(medianAll).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(median).not(1.0).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median).not(medianAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(median).<(medianAll + 1.0).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median).<(medianAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(median).<=(medianAll).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(median).>(1.0).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median).>(medianAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(median).>=(medianAll).query.get.map(_.head ==~ medianAll)
      _ <- Entity.double(median).>=(medianAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(median).query.get.map { res =>
        res(0)._2 ==~ median1
        res(1)._2 ==~ median2
      }

      _ <- Entity.i.a1.double(median)(median1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(median)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(median).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(median).not(median1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(median).<(median2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(median).<(median1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(median).<=(median1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(median).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(median).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(median).>(median1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(median).>=(median1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(median).>=(median2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val avgAll = ((double1 + double2 + double2 + double3 + double4).toDouble * 100 / 500.0)
    val avg1   = ((double1 + double2).toDouble * 100 / 200.0)
    val avg2   = ((double2 + double3 + double4).toDouble * 100 / 300.0)
    val a      = (1, avg1)
    val b      = (2, avg2)
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double3),
        (2, double4),
      )).transact

      // 1 attribute
      _ <- Entity.double(avg).query.get.map(_.head ==~ avgAll)

      _ <- Entity.double(avg)(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).not(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).not(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).<(avgAll + 1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).<(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).<=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).>(1.0).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).>(avgAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(avg).>=(avgAll).query.get.map(_.head ==~ avgAll)
      _ <- Entity.double(avg).>=(avgAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(avg).query.get.map { res =>
        res(0)._2 ==~ avg1
        res(1)._2 ==~ avg2
      }

      _ <- Entity.i.a1.double(avg)(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).not(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(avg).<(avg2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg).<(avg1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).<=(avg1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(avg).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(avg).>(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).>(avg1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(avg).>=(avg1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(avg).>=(avg2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val varianceAll = varianceOf(double1, double2, double2, double3, double4)
    val variance1   = varianceOf(double1, double2)
    val variance2   = varianceOf(double2, double3, double4)
    val a      = (1, variance1)
    val b      = (2, variance2)
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double3),
        (2, double4),
      )).transact

      // 1 attribute
      _ <- Entity.double(variance).query.get.map(_.head ==~ varianceAll)

      _ <- Entity.double(variance)(varianceAll).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(variance).not(1.0).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance).not(varianceAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(variance).<(varianceAll + 1.0).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance).<(varianceAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(variance).<=(varianceAll).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(variance).>(1.0).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance).>(varianceAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(variance).>=(varianceAll).query.get.map(_.head ==~ varianceAll)
      _ <- Entity.double(variance).>=(varianceAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(variance).query.get.map { res =>
        res(0)._2 ==~ variance1
        res(1)._2 ==~ variance2
      }

      _ <- Entity.i.a1.double(variance)(variance1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(variance)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(variance).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(variance).not(variance1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(variance).<(variance2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(variance).<(variance1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(variance).<=(variance2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(variance).<=(variance1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.double(variance).>(variance1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(variance).>(variance2).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(variance).>=(variance1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(variance).>=(variance2).query.get.map(_ ==> List(b))
    } yield ()
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    val stddevAll = stdDevOf(double1, double2, double2, double3, double4)
    val stddev1   = stdDevOf(double1, double2)
    val stddev2   = stdDevOf(double2, double3, double4)
    val a      = (1, stddev1)
    val b      = (2, stddev2)
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (1, double2),
        (2, double2),
        (2, double3),
        (2, double4),
      )).transact

      // 1 attribute
      _ <- Entity.double(stddev).query.get.map(_.head ==~ stddevAll)

      _ <- Entity.double(stddev)(stddevAll).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(stddev).not(1.0).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev).not(stddevAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(stddev).<(stddevAll + 1.0).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev).<(stddevAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(stddev).<=(stddevAll).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev).<=(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.double(stddev).>(1.0).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev).>(stddevAll).query.get.map(_ ==> Nil)

      _ <- Entity.double(stddev).>=(stddevAll).query.get.map(_.head ==~ stddevAll)
      _ <- Entity.double(stddev).>=(stddevAll + 1.0).query.get.map(_ ==> Nil)


      // n attributes
      _ <- Entity.i.a1.double(stddev).query.get.map { res =>
        res(0)._2 ==~ stddev1
        res(1)._2 ==~ stddev2
      }

      _ <- Entity.i.a1.double(stddev)(stddev1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(stddev)(1.0).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(stddev).not(1.0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(stddev).not(stddev1).query.get.map(_ ==> List(b))

      _ <- Entity.i.a1.double(stddev).<(stddev2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(stddev).<(stddev1).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(stddev).<=(stddev2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(stddev).<=(stddev1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.double(stddev).>(stddev1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(stddev).>(stddev2).query.get.map(_ ==> Nil)

      _ <- Entity.i.a1.double(stddev).>=(stddev1).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(stddev).>=(stddev2).query.get.map(_ ==> List(b))
    } yield ()
  }
}