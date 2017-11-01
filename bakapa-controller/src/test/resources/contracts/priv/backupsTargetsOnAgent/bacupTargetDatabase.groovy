package contracts

import groovy.transform.Field
import org.springframework.cloud.contract.spec.Contract


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
                            targetType   : anyOf("MONGODB", "MYSQL", "POSTGRESQL"),
                            host         : "localhost",
                            username     : anyNonBlankString(),
                            database     : anyNonBlankString(),
                            port         : $(consumer(matching(regex("[0-9]+"))), producer(123)),
                            password     : anyNonBlankString(),
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
                        targetType    : fromRequest().body('$.targetType'),
                        host          : fromRequest().body('$.host'),
                        database      : fromRequest().body('$.database'),
                        username      : fromRequest().body('$.username'),
                        port          : fromRequest().body('$.port'),
                        password      : fromRequest().body('$.password'),
                        excludeTables : [fromRequest().body('$.excludeTables[0]')],
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
                                        backupTargetId: anyNonEmptyString(),
                                        targetType    : anyOf("MONGODB", "MYSQL", "POSTGRESQL"),
                                        host          : anyNonEmptyString(),
                                        username      : anyNonBlankString(),
                                        database      : anyNonBlankString(),
                                        port          : anyNumber(),
                                        password      : anyNonBlankString(),
                                        excludeTables : [anyNonBlankString()],
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