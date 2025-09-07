package molecule.db.compliance.test.validation.insert

import molecule.core.error.{InsertError, InsertErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class Nested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "2 levels" - validation {
    for {
      // bad, List(ok, ok)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa.com",
            "b@bb.com",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.int",
                    Seq(
                      s"""Tpe.int with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Nil // nested errors
                  )
                )
              )
            )
        }


      // ok, List(bad, ok)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (3, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


      // ok, List(ok, bad)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (3, List(
            "a@aa.com",
            "b@bb",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


      // bad, List(bad, ok)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.int",
                    Seq(
                      s"""Tpe.int with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Nil // nested errors
                  ),
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


      // bad, List(ok, bad)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb.com",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.int",
                    Seq(
                      s"""Tpe.int with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Nil // nested errors
                  ),
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


      // ok, List(bad, bad)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (3, List(
            "a@aa",
            "b@bb",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }


      // bad, List(bad, bad)
      _ <- Tpe.int.Refs.*(Strings.email).insert(
          (1, List(
            "a@aa",
            "b@bb",
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0,
                    "Tpe.int",
                    Seq(
                      s"""Tpe.int with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Nil // nested errors
                  ),
                  InsertError(
                    0,
                    "Tpe.refs",
                    Nil, // (no errors for ref itself)
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`a@aa` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            0,
                            "Strings.email",
                            Seq(
                              "`b@bb` is not a valid email"
                            ),
                            Nil // ( no further nested errors)
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }
    } yield ()
  }


  "3 levels" - validation {
    for {
      _ <- Tpe.int.Refs.*(Strings.email.AllowedAttrs.*(AllowedAttrs.luckyNumber)).insert(
          (1, List(
            ("a@aa.com", List(1, 7)),
            ("b@bb", List(9, 2))
          ))
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Tpe.int",
                    Seq(
                      s"""Tpe.int with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Seq()
                  ),
                  InsertError(
                    0, // tuple index
                    "Tpe.refs",
                    Seq(),
                    Seq(
                      (
                        0, // nested row index
                        Seq(
                          InsertError(
                            0, // tuple index
                            "Strings.allowedAttrs",
                            Seq(),
                            Seq(
                              (
                                0, // nested row index
                                Seq(
                                  InsertError(
                                    0, // tuple index
                                    "AllowedAttrs.luckyNumber",
                                    Seq(
                                      """Value `1` is not one of the allowed values in Seq(7, 9, 13)"""
                                    ),
                                    Seq()
                                  )
                                )
                              )
                            )
                          )
                        )
                      ),
                      (
                        1, // nested row index
                        Seq(
                          InsertError(
                            0, // tuple index
                            "Strings.email",
                            Seq(
                              """`b@bb` is not a valid email"""
                            ),
                            Seq()
                          ),
                          InsertError(
                            0, // tuple index
                            "Strings.allowedAttrs",
                            Seq(),
                            Seq(
                              (
                                1, // nested row index
                                Seq(
                                  InsertError(
                                    0, // tuple index
                                    "AllowedAttrs.luckyNumber",
                                    Seq(
                                      """Value `2` is not one of the allowed values in Seq(7, 9, 13)"""
                                    ),
                                    Seq()
                                  )
                                )
                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
        }
    } yield ()
  }
}
