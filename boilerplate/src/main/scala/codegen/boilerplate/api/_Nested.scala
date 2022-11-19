package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Nested extends BoilerplateGenBase("Nested", "/api") {
  val content = {
    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.ast.MoleculeModel._
       |
       |/** Add nested molecule.
       |  * <br><br>
       |  * Related data of cardinality-many referenced entities can be queried in a "flat" way:
       |  * {{{
       |  * m(Order.no.LineItem.product.price.quantity).get.map(_ ==> List(
       |  *   (23, "Chocolate", 48.00, 1),
       |  *   (23, "Whisky", 38.00, 2)
       |  * )
       |  * }}}
       |  * For convenience, Molecule offers to automatically nest the same data so that
       |  * redundancy is avoided and we can work straight on the hierarchical data:
       |  * {{{
       |  * m(Order.no * LineItem.product.price.quantity).get.map(_ ==> List(
       |  *   (23, List(("Chocolate", 48.00, 1), ("Whisky", 38.00, 2)))
       |  * )
       |  * }}}
       |  * Nested molecules can nest up to 7 levels deep.
       |  * <br><br>
       |  * Internally, Molecule adds entity ids to each level in the query to be able to group data on each level by a unique entity id.
       |  */
       |trait NestedBase
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait NestedOp${_0}[${`A..V, `}$ns_1[${`_, _, _`}]] extends NestedBase {
         |  protected def _nestedMan[Tpl](nestedElements: Seq[Element]): $ns_1[${`A..V, `}Seq[Tpl], Nothing] = ???
         |  protected def _nestedOpt[Tpl](nestedElements: Seq[Element]): $ns_1[${`A..V, `}Seq[Tpl], Nothing] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V, `}$ns_1[${`_, _, _`}]] extends NestedOp${_0}[${`A..V, `}$ns_1] {
         |  final def * [a                                                               ] (nested: Molecule_01[a                                                               ]): $ns_1[${`A..V, `}Seq[a                                                                 ], Nothing] = _nestedMan[a                                                                 ](nested.elements)
         |  final def * [a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): $ns_1[${`A..V, `}Seq[(a, b                                                            )], Nothing] = _nestedMan[(a, b                                                            )](nested.elements)
         |  final def * [a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): $ns_1[${`A..V, `}Seq[(a, b, c                                                         )], Nothing] = _nestedMan[(a, b, c                                                         )](nested.elements)
         |  final def * [a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d                                                      )], Nothing] = _nestedMan[(a, b, c, d                                                      )](nested.elements)
         |  final def * [a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e                                                   )], Nothing] = _nestedMan[(a, b, c, d, e                                                   )](nested.elements)
         |  final def * [a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f                                                )], Nothing] = _nestedMan[(a, b, c, d, e, f                                                )](nested.elements)
         |  final def * [a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )], Nothing] = _nestedMan[(a, b, c, d, e, f, g                                             )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h                                          )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i                                       )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j                                    )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k                                 )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l                              )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](nested.elements)
         |  final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)], Nothing] = _nestedMan[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](nested.elements)
         |  final def *?[a                                                               ] (nested: Molecule_01[a                                                               ]): $ns_1[${`A..V, `}Seq[a                                                                 ], Nothing] = _nestedOpt[a                                                                 ](nested.elements)
         |  final def *?[a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): $ns_1[${`A..V, `}Seq[(a, b                                                            )], Nothing] = _nestedOpt[(a, b                                                            )](nested.elements)
         |  final def *?[a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): $ns_1[${`A..V, `}Seq[(a, b, c                                                         )], Nothing] = _nestedOpt[(a, b, c                                                         )](nested.elements)
         |  final def *?[a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d                                                      )], Nothing] = _nestedOpt[(a, b, c, d                                                      )](nested.elements)
         |  final def *?[a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e                                                   )], Nothing] = _nestedOpt[(a, b, c, d, e                                                   )](nested.elements)
         |  final def *?[a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f                                                )], Nothing] = _nestedOpt[(a, b, c, d, e, f                                                )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g                                             )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h                                          )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i                                       )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j                                    )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k                                 )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l                              )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)], Nothing] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](nested.elements)
         |}
         |""".stripMargin
  }
}
