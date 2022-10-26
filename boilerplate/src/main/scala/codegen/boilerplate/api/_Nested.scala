package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Nested extends BoilerplateGenBase("Nested", "/api") {
  val content = {
    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |molecule.boilerplate.api
       |
       |import molecule.boilerplate.markers.NamespaceMarkers._
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
       |object $fileName {
       |
       |  trait NestedBase
       |  $traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |  trait ${fileName}_$arity[${`A..V, `}$ns_1[${`_1`}]] extends NestedBase {
         |    final def * [a                                                               ] (nested: Molecule_01[a                                                               ]): $ns_1[${`A..V, `}Seq[a                                                                 ]] = ???
         |    final def * [a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): $ns_1[${`A..V, `}Seq[(a, b                                                            )]] = ???
         |    final def * [a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): $ns_1[${`A..V, `}Seq[(a, b, c                                                         )]] = ???
         |    final def * [a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d                                                      )]] = ???
         |    final def * [a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e                                                   )]] = ???
         |    final def * [a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f                                                )]] = ???
         |    final def * [a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )]] = ???
         |    final def * [a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )]] = ???
         |    final def * [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)]] = ???
         |    final def *?[a                                                               ] (nested: Molecule_01[a                                                               ]): $ns_1[${`A..V, `}Seq[a                                                                 ]] = ???
         |    final def *?[a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): $ns_1[${`A..V, `}Seq[(a, b                                                            )]] = ???
         |    final def *?[a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): $ns_1[${`A..V, `}Seq[(a, b, c                                                         )]] = ???
         |    final def *?[a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d                                                      )]] = ???
         |    final def *?[a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e                                                   )]] = ???
         |    final def *?[a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f                                                )]] = ???
         |    final def *?[a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )]] = ???
         |    final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): $ns_1[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)]] = ???
         |  }""".stripMargin
  }
}
