package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _OptRef extends BoilerplateGenBase("OptRef", "/api") {
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
         |trait ${fileName}Op${_0}[${`A..V, `}Ns[${`_, _, _`}]] {
         |  protected def _optRef[RefTpl](optRefElements: List[Element]): Ns[${`A..V, `}Option[RefTpl], Any] = ???
         |}
         |trait $fileName_$arity[${`A..V, `}Ns[${`_, _, _`}]] { self: ${fileName}Op${_0}[${`A..V, `}Ns] =>
         |  final def ?[a                                                               ] (optRef: Molecule_01[a                                                               ]): Ns[${`A..V, `}Option[a                                                                 ], Any] = _optRef[a                                                                 ](optRef.elements)
         |  final def ?[a, b                                                            ] (optRef: Molecule_02[a, b                                                            ]): Ns[${`A..V, `}Option[(a, b                                                            )], Any] = _optRef[(a, b                                                            )](optRef.elements)
         |  final def ?[a, b, c                                                         ] (optRef: Molecule_03[a, b, c                                                         ]): Ns[${`A..V, `}Option[(a, b, c                                                         )], Any] = _optRef[(a, b, c                                                         )](optRef.elements)
         |  final def ?[a, b, c, d                                                      ] (optRef: Molecule_04[a, b, c, d                                                      ]): Ns[${`A..V, `}Option[(a, b, c, d                                                      )], Any] = _optRef[(a, b, c, d                                                      )](optRef.elements)
         |  final def ?[a, b, c, d, e                                                   ] (optRef: Molecule_05[a, b, c, d, e                                                   ]): Ns[${`A..V, `}Option[(a, b, c, d, e                                                   )], Any] = _optRef[(a, b, c, d, e                                                   )](optRef.elements)
         |  final def ?[a, b, c, d, e, f                                                ] (optRef: Molecule_06[a, b, c, d, e, f                                                ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f                                                )], Any] = _optRef[(a, b, c, d, e, f                                                )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g                                             ] (optRef: Molecule_07[a, b, c, d, e, f, g                                             ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g                                             )], Any] = _optRef[(a, b, c, d, e, f, g                                             )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h                                          ] (optRef: Molecule_08[a, b, c, d, e, f, g, h                                          ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h                                          )], Any] = _optRef[(a, b, c, d, e, f, g, h                                          )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i                                       ] (optRef: Molecule_09[a, b, c, d, e, f, g, h, i                                       ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i                                       )], Any] = _optRef[(a, b, c, d, e, f, g, h, i                                       )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j                                    ] (optRef: Molecule_10[a, b, c, d, e, f, g, h, i, j                                    ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j                                    )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j                                    )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k                                 ] (optRef: Molecule_11[a, b, c, d, e, f, g, h, i, j, k                                 ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k                                 )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k                                 )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l                              ] (optRef: Molecule_12[a, b, c, d, e, f, g, h, i, j, k, l                              ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l                              )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l                              )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m                           ] (optRef: Molecule_13[a, b, c, d, e, f, g, h, i, j, k, l, m                           ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m                           )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ] (optRef: Molecule_14[a, b, c, d, e, f, g, h, i, j, k, l, m, n                        ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n                        )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ] (optRef: Molecule_15[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o                     )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ] (optRef: Molecule_16[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p                  )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ] (optRef: Molecule_17[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q               )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ] (optRef: Molecule_18[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r            )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ] (optRef: Molecule_19[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s         )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ] (optRef: Molecule_20[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t      )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ] (optRef: Molecule_21[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   ]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u   )](optRef.elements)
         |  final def ?[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v] (optRef: Molecule_22[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v]): Ns[${`A..V, `}Option[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)], Any] = _optRef[(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)](optRef.elements)
         |}
         |""".stripMargin
  }
}
