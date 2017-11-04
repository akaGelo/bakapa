package contracts

import groovy.transform.Field
import org.springframework.cloud.contract.spec.Contract

import ru.vyukov.contract.RequestPatterns;
import ru.vyukov.contract.ResponsePatterns;


@Field static def TEST_AGENT_ID = "testAgentId-1"
@Field static def TEST_AGENT_ID_2 = "testAgentId-2"

/**
 * Scene
 * 1) create database target
 * 2) create filesystem target
 * 3) get  check
 *
 */

[
        Contract.make {
            name("0 create DatabaseBackupTarget")
            request {
                method 'POST'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                    body([
                            targetType     : anyOf("MONGODB", "MYSQL", "POSTGRESQL"),
                            trigger        : $(RequestPatterns.anyCronExpression()),
                            location       : [
                                    host    : "localhost",
                                    port    : $(consumer(matching(regex("[0-9]+"))), producer(123)),
                                    database: anyNonBlankString(),
                            ],
                            userCredentials: [
                                    username: anyNonBlankString(),
                                    password: anyNonBlankString(),
                            ],
                            options        : [
                                    excludeTables: [anyNonBlankString()],
                            ],
                    ])
                }

                headers {
                    contentType(applicationJson())
                }
            }

            response {
                status 200
                body([
                        backupTargetId : anyNonEmptyString(),
                        targetType     : fromRequest().body('$.targetType'),
                        trigger        : fromRequest().body('$.trigger'),
                        location       : [
                                host    : fromRequest().body('$.location.host'),
                                port    : fromRequest().body('$.location.port'),
                                database: fromRequest().body('$.location.database'),
                        ],
                        userCredentials: [
                                username: fromRequest().body('$.userCredentials.username'),
                                password: fromRequest().body('$.userCredentials.password'),
                        ],
                        options        : [
                                excludeTables: [fromRequest().body('$.options.excludeTables[0]')],
                        ]
                ])
                headers {
                    contentType("application/json")
                }
            }
        },

        Contract.make {
            name("1 get DatabaseBackupTarget")

            request {
                method 'GET'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                }
            }

            response {
                status 200
                body([
                        [
                                backupTarget : [
                                        backupTargetId : anyNonEmptyString(),
                                        targetType     : anyOf("MONGODB", "MYSQL", "POSTGRESQL"),
                                        trigger        : $(ResponsePatterns.anyCronExpression()),
                                        location       : [
                                                host    : anyNonEmptyString(),
                                                database: anyNonBlankString(),
                                                port    : anyNumber(),
                                        ],
                                        userCredentials: [
                                                username: anyNonBlankString(),
                                                password: anyNonBlankString(),
                                        ],
                                        options        : [
                                                excludeTables: [anyNonBlankString()],
                                        ]
                                ],
                                executionInfo: [
                                        lastSizeBytes         : anyNumber(),
                                        lastExecutionTimestamp: anyIso8601WithOffset(),
                                        lastStatus            : anyOf("SUCCESS", "ERROR"),
                                        nextExecutionTimestamp: anyIso8601WithOffset(),

                                ]
                        ]
                ])
                headers {
                    contentType("application/json")
                }

            }
        }


]