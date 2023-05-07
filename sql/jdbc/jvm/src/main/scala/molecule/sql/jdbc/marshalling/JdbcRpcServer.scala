package molecule.sql.jdbc.marshalling

import molecule.core.marshalling.MoleculeRpcServer

/**
 * Server app serving http requests/ws from client
 *
 * Run with
 *
 *    sbt datomicJVM/run
 */
object JdbcRpcServer extends MoleculeRpcServer(JdbcRpcJVM) with App
