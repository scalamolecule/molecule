/*
* AUTO-GENERATED Molecule DSL for namespace `Log`
*
* To change:
* 1. Edit data model in molecule.core.generic.dataModel/LogDataModel
* 2. `sbt clean compile`
* 3. Re-compile project in IDE
*/
package molecule.core.generic.Log

import java.util.Date
import molecule.core.dsl.base._
import scala.language.higherKinds

trait Log_0_1[o0[_], p0, A] extends Log with NS_0_01[o0, p0, A]

trait Log_0_1_L0[o0[_], p0, A] extends Log_0_1[o0, p0, A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L0[o0, p0 with Prop, A, Tpe], Nothing] with Log_0_2_L0[o0, p0 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L0[o0, p0          , A     ], Nothing] with Log_0_1_L0[o0, p0          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L1[o0[_], p0, o1[_], p1, A] extends Log_0_1[o0, p0 with o1[p1], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L1[o0, p0, o1, p1 with Prop, A, Tpe], Nothing] with Log_0_2_L1[o0, p0, o1, p1 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L1[o0, p0, o1, p1          , A     ], Nothing] with Log_0_1_L1[o0, p0, o1, p1          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L2[o0[_], p0, o1[_], p1, o2[_], p2, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L2[o0, p0, o1, p1, o2, p2 with Prop, A, Tpe], Nothing] with Log_0_2_L2[o0, p0, o1, p1, o2, p2 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L2[o0, p0, o1, p1, o2, p2          , A     ], Nothing] with Log_0_1_L2[o0, p0, o1, p1, o2, p2          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L3[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2 with o3[p3]]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L3[o0, p0, o1, p1, o2, p2, o3, p3 with Prop, A, Tpe], Nothing] with Log_0_2_L3[o0, p0, o1, p1, o2, p2, o3, p3 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L3[o0, p0, o1, p1, o2, p2, o3, p3          , A     ], Nothing] with Log_0_1_L3[o0, p0, o1, p1, o2, p2, o3, p3          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L4[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4]]]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4 with Prop, A, Tpe], Nothing] with Log_0_2_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4          , A     ], Nothing] with Log_0_1_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L5[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5]]]]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5 with Prop, A, Tpe], Nothing] with Log_0_2_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5          , A     ], Nothing] with Log_0_1_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L6[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6]]]]]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6 with Prop, A, Tpe], Nothing] with Log_0_2_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6          , A     ], Nothing] with Log_0_1_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}


trait Log_0_1_L7[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, o7[_], p7, A] extends Log_0_1[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6 with o7[p7]]]]]]], A] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[Log_0_2_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7 with Prop, A, Tpe], Nothing] with Log_0_2_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7 with Prop, A, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[Log_0_1_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7          , A     ], Nothing] with Log_0_1_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7          , A     ]

  final lazy val e          : Next[e         , Log_e        , Long   ] = ???
  final lazy val a          : Next[a         , Log_a        , String ] = ???
  final lazy val v          : Next[v         , Log_v        , Any    ] = ???
  final lazy val t          : Next[t         , Log_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , Log_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , Log_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , Log_op       , Boolean] = ???
}

     
