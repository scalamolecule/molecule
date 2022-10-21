package molecule.base.codeGen

import molecule.base.CodeGenTesting
import utest._

trait CoreTest_Base extends CodeGenTesting {


  override def tests: Tests = Tests {
    "Base" - nsBase(
      """package moleculeTests.dataModels.core.base.dataModel
        |
        |import molecule.DataModel
        |
        |@MaxArity(2)
        |object CoreTestDataModel {
        |
        |  trait Ns {
        |    val str = oneString
        |    val int = oneInt
        |  }
        |}""".stripMargin,

      """/*
        |* AUTO-GENERATED Molecule DSL for namespace `Ns`
        |*
        |* To change:
        |* 1. Edit data model in moleculeTests.dataModels.core.base.dsl.dataModel.CoreTestDataModel
        |* 2. `sbt clean compile -Dmolecule=true`
        |*/
        |package moleculeTests.dataModels.core.base.dsl.CoreTest
        |
        |import java.net.URI
        |import java.util.Date
        |import java.util.UUID
        |import molecule.boilerplate.markers.argKindMarkers._
        |import molecule.boilerplate.markers.attrMarkers._
        |import molecule.boilerplate.markers.bidirectionalMarkers._
        |import molecule.boilerplate.markers.namespaceMarkers._
        |import molecule.boilerplate.markers.schemaOptionMarkers._
        |import scala.language.higherKinds
        |
        |object Ns extends Ns_0_0[Ns_, Init] with FirstNS {
        |  final override def apply(eid: Long, eids: Long*): Ns_0_0[Ns_, Init] with ArgsKind[Args]    = ???
        |  final override def apply(eids: Iterable[Long])  : Ns_0_0[Ns_, Init] with ArgsKind[SeqArgs] = ???
        |}
        |
        |trait _Ns_ {
        |  trait str  extends OneString  with Index { ns: NS => }
        |  trait int  extends OneInt     with Index { ns: NS => }
        |
        |  trait str$ extends OneString$ with Index { ns: NS => }
        |  trait int$ extends OneInt$    with Index { ns: NS => }
        |
        |  trait str_ extends OneString_ with Index { ns: NS => }
        |  trait int_ extends OneInt_    with Index { ns: NS => }
        |}
        |
        |
        |trait Ns_[props] { def Ns: props = ??? }
        |
        |trait Ns_str               { lazy val str              : String       = ??? }
        |trait Ns_str_min           { lazy val str_min          : String       = ??? }
        |trait Ns_str_max           { lazy val str_max          : String       = ??? }
        |trait Ns_str_rand          { lazy val str_rand         : String       = ??? }
        |trait Ns_str_sample        { lazy val str_sample       : String       = ??? }
        |trait Ns_str_mins          { lazy val str_mins         : List[String] = ??? }
        |trait Ns_str_maxs          { lazy val str_maxs         : List[String] = ??? }
        |trait Ns_str_distinct      { lazy val str_distinct     : List[String] = ??? }
        |trait Ns_str_rands         { lazy val str_rands        : List[String] = ??? }
        |trait Ns_str_samples       { lazy val str_samples      : List[String] = ??? }
        |trait Ns_str_count         { lazy val str_count        : Int          = ??? }
        |trait Ns_str_countDistinct { lazy val str_countDistinct: Int          = ??? }
        |
        |trait Ns_int               { lazy val int              : Int       = ??? }
        |trait Ns_int_min           { lazy val int_min          : Int       = ??? }
        |trait Ns_int_max           { lazy val int_max          : Int       = ??? }
        |trait Ns_int_rand          { lazy val int_rand         : Int       = ??? }
        |trait Ns_int_sample        { lazy val int_sample       : Int       = ??? }
        |trait Ns_int_sum           { lazy val int_sum          : Int       = ??? }
        |trait Ns_int_median        { lazy val int_median       : Int       = ??? }
        |trait Ns_int_mins          { lazy val int_mins         : List[Int] = ??? }
        |trait Ns_int_maxs          { lazy val int_maxs         : List[Int] = ??? }
        |trait Ns_int_distinct      { lazy val int_distinct     : List[Int] = ??? }
        |trait Ns_int_rands         { lazy val int_rands        : List[Int] = ??? }
        |trait Ns_int_samples       { lazy val int_samples      : List[Int] = ??? }
        |trait Ns_int_count         { lazy val int_count        : Int       = ??? }
        |trait Ns_int_countDistinct { lazy val int_countDistinct: Int       = ??? }
        |trait Ns_int_avg           { lazy val int_avg          : Double    = ??? }
        |trait Ns_int_variance      { lazy val int_variance     : Double    = ??? }
        |trait Ns_int_stddev        { lazy val int_stddev       : Double    = ??? }
        |
        |trait opt_Ns_str { lazy val str$: Option[String] = ??? }
        |trait opt_Ns_int { lazy val int$: Option[Int   ] = ??? }""".stripMargin
    )
  }
}
