package contracts

import org.springframework.cloud.contract.spec.Contract

/**
 * Scene
 * 1) create database target
 * 2) create filesystem target
 * 3) get  check
 *
 */


import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.ServerDslProperty


final String TEST_AGENT_ID = "testAgentIdOne"


[
        Contract.make {
            name("create DatabaseBackupTarget")
            request {
                method 'POST'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                    body([
                            targetType   : "MONGODB",
                            host         : "localhost",
                            username     : "testDb",
                            port         : 27017,
                            password     : "qwerty",
                            excludeTables: [anyNonBlankString()],
                    ])
                }

                headers {
                    contentType(applicationJson())
                }
            }

            response {
                status 200
                body([
                        backupTargetId: anyNonEmptyString(),
                        targetType   : "MONGODB",
                        host         : "localhost",
                        username     : "testDb",
                        port         : 27017,
                        password     : "qwerty",
                        excludeTables: [anyNonBlankString()],
                ])
            }
        },

        Contract.make {
            name("get DatabaseBackupTarget")
            request {
                method 'GET'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                }

                headers {
                    contentType(applicationJson())
                }
            }

            response {
                status 200
                body([
                        [
                                backupTargetId: anyNonEmptyString(),
                                targetType   : "MONGODB",
                                host         : "localhost",
                                username     : "testDb",
                                port         : 27017,
                                password     : "qwerty",
                                excludeTables: [anyNonBlankString()],
                        ]
                ])
            }
        }


]