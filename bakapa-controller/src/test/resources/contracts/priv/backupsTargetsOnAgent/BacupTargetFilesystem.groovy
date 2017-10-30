package contracts

import org.springframework.cloud.contract.spec.Contract

/**
 * Scene
 * 1) create database target
 * 2) create filesystem target
 * 3) get  check
 *
 */
final String TEST_AGENT_ID = "testAgentIdOne"


[
        Contract.make {
            name("0 create FilesystemBackupTarget")
            request {
                method 'POST'
                urlPath("/private/agents/${TEST_AGENT_ID}/targets/") {

                    body([
                            targetType: "FILESYSTEM",
                            path      : "/etc/",
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
                        path          : "/etc/",
                ])
            }
        },

        Contract.make {
            name("1 get FilesystemBackupTarget")
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
            }
        }


]