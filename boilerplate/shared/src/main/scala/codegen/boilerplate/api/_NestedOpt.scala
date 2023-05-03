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
         |trait ${fileName}Op${_0}${`[A..V]`} extends Nested${_0}${`[A..V]`} { self: Molecule[$tpl] =>
         |  protected def _nestedOpt[Tpl](nestedElements: List[Element]): Tx${_1}[${`A..V, `}Seq[Tpl]] = new Tx${_1}[${`A..V, `}Seq[Tpl]](elements.init :+ NestedOpt(elements.last.asInstanceOf[Ref], nestedElements))
         |}
         |
         |trait $fileName_$arity${`[A..V]`} extends ${fileName}Op${_0}${`[A..V]`} { self: Molecule[$tpl] =>
         |  final def *?[a                                                               ] (nested: Molecule_01[a                                                               ]): Tx${_1}[${`A..V, `}Seq[a                                                                 ]] = _nestedOpt[a                                                                 ](nested.elements)
         |  final def *?[a, b                                                            ] (nested: Molecule_02[a, b                                                            ]): Tx${_1}[${`A..V, `}Seq[(a, b                                                            )]] = _nestedOpt[(a, b                                                            )](nested.elements)
         |  final def *?[a, b, c                                                         ] (nested: Molecule_03[a, b, c                                                         ]): Tx${_1}[${`A..V, `}Seq[(a, b, c                                                         )]] = _nestedOpt[(a, b, c                                                         )](nested.elements)
         |  final def *?[a, b, c, d                                                      ] (nested: Molecule_04[a, b, c, d                                                      ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d                                                      )]] = _nestedOpt[(a, b, c, d                                                      )](nested.elements)
         |  final def *?[a, b, c, d, e                                                   ] (nested: Molecule_05[a, b, c, d, e                                                   ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e                                                   )]] = _nestedOpt[(a, b, c, d, e                                                   )](nested.elements)
         |  final def *?[a, b, c, d, e, f                                                ] (nested: Molecule_06[a, b, c, d, e, f                                                ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f                                                )]] = _nestedOpt[(a, b, c, d, e, f                                                )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g                                             ] (nested: Molecule_07[a, b, c, d, e, f, g                                             ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g                                             )]] = _nestedOpt[(a, b, c, d, e, f, g                                             )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h                                          ] (nested: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h                                          )]] = _nestedOpt[(a, b, c, d, e, f, g, h                                          )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i                                       ] (nested: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i                                       )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i                                       )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j                                    ] (nested: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j                                    )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j                                    )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k                                 ] (nested: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k                                 )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k                                 )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (nested: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l                              )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l                              )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (nested: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (nested: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (nested: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (nested: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (nested: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (nested: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (nested: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (nested: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (nested: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](nested.elements)
         |  final def *?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (nested: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Tx${_1}[${`A..V, `}Seq[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)]] = _nestedOpt[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](nested.elements)
         |}
         |""".stripMargin
  }
}
