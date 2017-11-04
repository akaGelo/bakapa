package contracts

import groovy.transform.Field
import org.springframework.cloud.contract.spec.Contract
import ru.vyukov.contract.RequestPatterns
import ru.vyukov.contract.ResponsePatterns


@Field static def TEST_AGENT_ID = "testAgentId-1"

@Field static def TEST_BACKUP_TARGET_ID = "testBackupTargetId-1"

[
        Contract.make {
            name("get BackupTargets")

            priority 1

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
                                        backupTargetId : $(consumer(TEST_BACKUP_TARGET_ID), producer(anyNonEmptyString())),
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
        },

        Contract.make {
            name("get test BackupTargets")

            priority 1

            request {
                method 'GET'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/${TEST_BACKUP_TARGET_ID}/") {

                }
            }

            response {
                status 200
                body([

                        backupTargetId : $(consumer(TEST_BACKUP_TARGET_ID), producer(anyNonEmptyString())),
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
                ])
                headers {
                    contentType("application/json")
                }

            }
        }


]