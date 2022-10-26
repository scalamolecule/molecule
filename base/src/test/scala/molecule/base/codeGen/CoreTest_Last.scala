package molecule.base.codeGen

import molecule.base.CodeGenTesting
import utest._


object CoreTest_Last extends CodeGenTesting {

  override def tests: Tests = Tests {

    "NsArity 2" - dslFirst(
      """package moleculeTests.dataModels.core.base.dataModel
        |
        |import molecule.DataModel
        |
        |object CoreTest extends DataModel(2) {
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
        |import molecule.boilerplate.markers.attrMarkers._
        |import molecule.boilerplate.markers.bidirectionalMarkers._
        |import molecule.boilerplate.markers.NamespaceMarkers._
        |import molecule.boilerplate.markers.schemaOptionMarkers._
        |import molecule.boilerplate.api.aggregates.aggr_2._
        |import molecule.boilerplate.api.compositeInit.CompositeInit_2
        |import molecule.boilerplate.api.generic.dsl.Datom._
        |import molecule.boilerplate.api.nested._
        |import molecule.boilerplate.api.txMetaData._
        |import scala.language.higherKinds
        |
        |trait Ns_2[CurNs, o0[_], p0, A, B] extends _Ns_
        |    with Molecule_02          [o0, p0, A, B]
        |    with CompositeInit_2[o0, p0, A, B]
        |    with Tx_2           [o0, p0, A, B] {
        |
        |  type Stay[Attr] = Attr with CurNs
        |
        |  final lazy val str_ : Stay[str_] = ???
        |  final lazy val int_ : Stay[int_] = ???
        |}
        |
        |
        |trait Ns_2_0[o0[_], p0, A, B]
        |  extends Ns_2[Ns_2_0[o0, p0, A, B], o0, p0, A, B]
        |    with Datom_2_0[o0, p0, A, B, Ns_2_0, Nothing] {
        |
        |  final def Self: Ns_2_1[o0, p0, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_1[o0[_], p0, o1[_], p1, A, B]
        |  extends Ns_2[Ns_2_1[o0, p0, o1, p1, A, B], o0, p0 with o1[p1], A, B]
        |    with Datom_2_1[o0, p0, o1, p1, A, B, Ns_2_1, Nothing] {
        |
        |  final def Self: Ns_2_2[o0, p0, o1, p1, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_2[o0[_], p0, o1[_], p1, o2[_], p2, A, B]
        |  extends Ns_2[Ns_2_2[o0, p0, o1, p1, o2, p2, A, B], o0, p0 with o1[p1 with o2[p2]], A, B]
        |    with Datom_2_2[o0, p0, o1, p1, o2, p2, A, B, Ns_2_2, Nothing] {
        |
        |  final def Self: Ns_2_3[o0, p0, o1, p1, o2, p2, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_3[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, A, B]
        |  extends Ns_2[Ns_2_3[o0, p0, o1, p1, o2, p2, o3, p3, A, B], o0, p0 with o1[p1 with o2[p2 with o3[p3]]], A, B]
        |    with Datom_2_3[o0, p0, o1, p1, o2, p2, o3, p3, A, B, Ns_2_3, Nothing] {
        |
        |  final def Self: Ns_2_4[o0, p0, o1, p1, o2, p2, o3, p3, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_4[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, A, B]
        |  extends Ns_2[Ns_2_4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, A, B], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4]]]], A, B]
        |    with Datom_2_4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, A, B, Ns_2_4, Nothing] {
        |
        |  final def Self: Ns_2_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_5[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, A, B]
        |  extends Ns_2[Ns_2_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, A, B], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5]]]]], A, B]
        |    with Datom_2_5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, A, B, Ns_2_5, Nothing] {
        |
        |  final def Self: Ns_2_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_6[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, A, B]
        |  extends Ns_2[Ns_2_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, A, B], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6]]]]]], A, B]
        |    with Datom_2_6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, A, B, Ns_2_6, Nothing] {
        |
        |  final def Self: Ns_2_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, Ns_, Init, A, B] with SelfJoin = ???
        |}
        |
        |
        |trait Ns_2_7[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, o7[_], p7, A, B]
        |  extends Ns_2[Ns_2_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7, A, B], o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6 with o7[p7]]]]]]], A, B]
        |    with Datom_2_7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7, A, B, Ns_2_7, Nothing] {
        |}""".stripMargin
    )
  }
}
