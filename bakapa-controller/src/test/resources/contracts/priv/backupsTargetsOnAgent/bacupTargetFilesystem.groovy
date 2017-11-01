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
            name("0 create FilesystemBackupTarget")
            request {
                method 'POST'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                    body([
                            targetType: "FILESYSTEM",
                            path      : $(consumer(anyNonEmptyString()), producer("/etc/")),
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
                        targetType    : "FILESYSTEM",
                        path          : $(consumer(fromRequest().body('$.path')), producer("/etc/"))

                ])
            }
        },

        Contract.make {
            name("1 get FilesystemBackupTarget")
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
                                        targetType    : "FILESYSTEM",
                                        path          : "/etc/",
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