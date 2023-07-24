package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _NestedOpt extends BoilerplateGenBase("NestedOpt", "/api") {
  val content = {
    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.ast.Model._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}Op${_0}[${`A..V, `}Tx[${`_, _`}]] extends TxMetaData_1_[Seq[NestedTpl]]) with Nested${_0}[${`A..V, `}Tx] { self: Molecule[$tpl] =>
         |  protected def _nestedOpt2tx[Tpl](nestedElements: List[Element]): Tx[${`A..V, `}Seq[Tpl]] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}Tx[${`_, _`}]] { self: Molecule[$tpl] with ${fileName}Op${_0}[${`A..V, `}Tx] =>
         |  final def *?[a                                                               ] (nested: Molecule_01[a                                                               ]): Tx[${`A..V, `}Seq[a                                                                 ]] = _nestedOpt2tx[a                                                                 ](nested.elements)
         |  final def *?[a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): Tx[${`A..V, `}Seq[(a, b                                                            )]] = _nestedOpt2tx[(a, b                                                            )](nested.elements)
         |  final def *?[a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): Tx[${`A..V, `}Seq[(a, b, c                                                         )]] = _nestedOpt2tx[(a, b, c                                                         )](nested.elements)
         |  final def *?[a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): Tx[${`A..V, `}Seq[(a, b, c, d                                                      )]] = _nestedOpt2tx[(a, b, c, d                                                      )](nested.elements)
         |  final def *?[a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): Tx[${`A..V, `}Seq[(a, b, c, d, e                                                   )]] = _nestedOpt2tx[(a, b, c, d, e                                                   )](nested.elements)
         |  final def *?[a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f                                                )]] = _nestedOpt2tx[(a, b, c, d, e, f                                                )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )]] = _nestedOpt2tx[(a, b, c, d, e, f, g                                             )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h                                          )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i                                       )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j                                    )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k                                 )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l                              )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Tx[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)]] = _nestedOpt2tx[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](nested.elements)
         |}
         |""".stripMargin
  }
}
