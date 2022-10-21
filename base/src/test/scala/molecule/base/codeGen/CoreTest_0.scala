package molecule.base.codeGen

import molecule.base.CodeGenTesting
import utest._

object CoreTest_0 extends CodeGenTesting {


  override def tests: Tests = Tests {

    "NsArity 0" - nsArity(0,
      """package moleculeTests.dataModels.core.base.dataModel
        |
        |import molecule.DataModel
        |
        |object CoreTest extends DataModel(2) {
        |
        |  trait Ns {
        |    val str  = oneString
        |    val strs = manyString
        |    val parent  = one[Ns]
        |    val ref1    = one[Ref1]
        |    val int  = oneInt
        |    val ints = manyInt
        |  }
        |
        |  trait Ref1 {
        |    val str1    = oneString.fulltext
        |    val int1    = oneInt
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
        |import molecule.boilerplate.markers.attrMarkers._
        |import molecule.boilerplate.markers.bidirectionalMarkers._
        |import molecule.boilerplate.markers.namespaceMarkers._
        |import molecule.boilerplate.markers.schemaOptionMarkers._
        |import molecule.boilerplate.api.aggregates.aggr_0._
        |import molecule.boilerplate.api.compositeInit.CompositeInit_0
        |import molecule.boilerplate.api.generic.dsl.Datom._
        |import molecule.boilerplate.api.nested._
        |import molecule.boilerplate.api.txMetaData._
        |import scala.language.higherKinds
        |
        |trait Ns_0[CurNs, o0[_], p0] extends _Ns_
        |    with Molecule_00          [o0, p0]
        |    with CompositeInit_0[o0, p0]
        |    with Tx_0           [o0, p0] {
        |
        |  type Next[Attr, Prop, Tpe]
        |  type Stay[Attr           ] = Attr with CurNs
        |
        |  final lazy val str  : Next[str, Ns_str, String] = ???
        |  final lazy val int  : Next[int, Ns_int, Int   ] = ???
        |
        |  final lazy val str$ : Next[str$, opt_Ns_str, Option[String]] = ???
        |  final lazy val int$ : Next[int$, opt_Ns_int, Option[Int   ]] = ???
        |
        |  final lazy val str_ : Stay[str_] = ???
        |  final lazy val int_ : Stay[int_] = ???
        |}
        |
        |
        |trait Ns_0_0[o0[_], p0]
        |  extends Ns_0[Ns_0_0[o0, p0], o0, p0]
        |    with Datom_0_0[o0, p0, Ns_0_0, Ns_1_0]
        |    with Aggr_0_0 [o0, p0, Ns_0_0] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_0[o0, p0 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_1[o0[_], p0, o1[_], p1]
        |  extends Ns_0[Ns_0_1[o0, p0, o1, p1], o0, p0 with o1[p1]]
        |    with Datom_0_1[o0, p0, o1, p1, Ns_0_1, Ns_1_1]
        |    with Aggr_0_1 [o0, p0, o1, p1, Ns_0_1] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_1[o0, p0, o1, p1 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_2[o0[_], p0, o1[_], p1, o2[_], p2]
        |  extends Ns_0[Ns_0_2[o0, p0, o1, p1, o2, p2], o0, p0 with o1[p1 with o2[p2]]]
        |    with Datom_0_2[o0, p0, o1, p1, o2, p2, Ns_0_2, Ns_1_2]
        |    with Aggr_0_2 [o0, p0, o1, p1, o2, p2, Ns_0_2] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_2[o0, p0, o1, p1, o2, p2 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_3[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3]
        |  extends Ns_0[Ns_0_3[o0, p0, o1, p1, o2, p2, o3, p3], o0, p0 with o1[p1 with o2[p2 with o3[p3]]]]
        |    with Datom_0_3[o0, p0, o1, p1, o2, p2, o3, p3, Ns_0_3, Ns_1_3]
        |    with Aggr_0_3 [o0, p0, o1, p1, o2, p2, o3, p3, Ns_0_3] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_3[o0, p0, o1, p1, o2, p2, o3, p3 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_4[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4]
        |  extends Ns_0[Ns_0_4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4]]]]]
        |    with Datom_0_4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, Ns_0_4, Ns_1_4]
        |    with Aggr_0_4 [o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, Ns_0_4] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_5[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5]
        |  extends Ns_0[Ns_0_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5]]]]]]
        |    with Datom_0_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, Ns_0_5, Ns_1_5]
        |    with Aggr_0_5 [o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, Ns_0_5] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_6[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6]
        |  extends Ns_0[Ns_0_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6]]]]]]]
        |    with Datom_0_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, Ns_0_6, Ns_1_6]
        |    with Aggr_0_6 [o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, Ns_0_6] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6 with Prop, Tpe]
        |}
        |
        |
        |trait Ns_0_7[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, o7[_], p7]
        |  extends Ns_0[Ns_0_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6 with o7[p7]]]]]]]]
        |    with Datom_0_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7, Ns_0_7, Ns_1_7]
        |    with Aggr_0_7 [o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7, Ns_0_7] {
        |
        |  type Next[Attr, Prop, Tpe] = Attr with Ns_1_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7 with Prop, Tpe]
        |}""".stripMargin
    )
  }
}
