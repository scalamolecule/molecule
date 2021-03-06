/*
* AUTO-GENERATED Molecule DSL for namespace `AVET`
*
* To change:
* 1. Edit data model in molecule.core.generic.dataModel/AVETDataModel
* 2. `sbt clean compile`
* 3. Re-compile project in IDE
*/
package molecule.core.generic.AVET

import java.util.Date
import molecule.core.dsl.base._
import scala.language.higherKinds

trait AVET_0_4[o0[_], p0, A, B, C, D] extends AVET with NS_0_04[o0, p0, A, B, C, D]

trait AVET_0_4_L0[o0[_], p0, A, B, C, D] extends AVET_0_4[o0, p0, A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L0[o0, p0 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L0[o0, p0 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L0[o0, p0          , A, B, C, D     ], Nothing] with AVET_0_4_L0[o0, p0          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L1[o0[_], p0, o1[_], p1, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L1[o0, p0, o1, p1 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L1[o0, p0, o1, p1 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L1[o0, p0, o1, p1          , A, B, C, D     ], Nothing] with AVET_0_4_L1[o0, p0, o1, p1          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L2[o0[_], p0, o1[_], p1, o2[_], p2, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L2[o0, p0, o1, p1, o2, p2 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L2[o0, p0, o1, p1, o2, p2 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L2[o0, p0, o1, p1, o2, p2          , A, B, C, D     ], Nothing] with AVET_0_4_L2[o0, p0, o1, p1, o2, p2          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L3[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2 with o3[p3]]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L3[o0, p0, o1, p1, o2, p2, o3, p3 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L3[o0, p0, o1, p1, o2, p2, o3, p3 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L3[o0, p0, o1, p1, o2, p2, o3, p3          , A, B, C, D     ], Nothing] with AVET_0_4_L3[o0, p0, o1, p1, o2, p2, o3, p3          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L4[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4]]]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4          , A, B, C, D     ], Nothing] with AVET_0_4_L4[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L5[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5]]]]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5          , A, B, C, D     ], Nothing] with AVET_0_4_L5[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L6[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6]]]]]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6          , A, B, C, D     ], Nothing] with AVET_0_4_L6[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}


trait AVET_0_4_L7[o0[_], p0, o1[_], p1, o2[_], p2, o3[_], p3, o4[_], p4, o5[_], p5, o6[_], p6, o7[_], p7, A, B, C, D] extends AVET_0_4[o0, p0 with o1[p1 with o2[p2 with o3[p3 with o4[p4 with o5[p5 with o6[p6 with o7[p7]]]]]]], A, B, C, D] {
  type Next[Attr[_, _], Prop, Tpe] = Attr[AVET_0_5_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7 with Prop, A, B, C, D, Tpe], Nothing] with AVET_0_5_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7 with Prop, A, B, C, D, Tpe]
  type Stay[Attr[_, _], Prop, Tpe] = Attr[AVET_0_4_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7          , A, B, C, D     ], Nothing] with AVET_0_4_L7[o0, p0, o1, p1, o2, p2, o3, p3, o4, p4, o5, p5, o6, p6, o7, p7          , A, B, C, D     ]

  final lazy val e          : Next[e         , AVET_e        , Long   ] = ???
  final lazy val a          : Next[a         , AVET_a        , String ] = ???
  final lazy val v          : Next[v         , AVET_v        , Any    ] = ???
  final lazy val t          : Next[t         , AVET_t        , Long   ] = ???
  final lazy val tx         : Next[tx        , AVET_tx       , Long   ] = ???
  final lazy val txInstant  : Next[txInstant , AVET_txInstant, Date   ] = ???
  final lazy val op         : Next[op        , AVET_op       , Boolean] = ???
}

     
