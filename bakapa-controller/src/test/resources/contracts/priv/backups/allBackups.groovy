package contracts.priv.backups

import org.springframework.cloud.contract.spec.Contract
import ru.vyukov.contract.RequestPatterns
import ru.vyukov.contract.ResponsePatterns

[
        Contract.make {
            name("all backups")
            request {
                method 'GET'
                urlPath("/private/backups/") {
                    queryParameters {
                        parameter 'page': $(RequestPatterns.anyShortPositiveNumber(), p(0))
                        parameter 'size': $(RequestPatterns.anyShortPositiveNumber(), p(11))
                    }
                }
            }

            response {
                status 200
                body([

                        number          : fromRequest().query("page"),
                        numberOfElements: ResponsePatterns.anyShortPositiveNumber(),
                        totalElements   : ResponsePatterns.anyShortPositiveNumber(),
                        totalPages      : ResponsePatterns.anyShortPositiveNumber(),
                        size            : fromRequest().query("size"),
                        content         : [
                                [
                                        backupId      : anyNonEmptyString(),
                                        startTimestamp: anyIso8601WithOffset(),
                                        state         : anyOf("INPROGRESS", "SUCCESS", "ERROR"),
                                        size          : ResponsePatterns.anyShortPositiveNumber(),
                                        backupTarget  : [
                                                backupTargetId: anyNonEmptyString(),
                                                trigger       : $(ResponsePatterns.anyCronExpression()),
                                                targetType    : "FILESYSTEM",
                                                path          : anyNonEmptyString(),
                                                agent         : [
                                                        agentId   : anyNonEmptyString(),
                                                        note      : $(c(null), p(anyNonEmptyString())),
                                                        createDate: anyIso8601WithOffset(),
                                                ]

                                        ]
                                ]
                        ]
                ])
                headers {
                    contentType("application/json")
                }
            }
        },

        Contract.make {
            name("all backups sorted by startTimestamp")
            request {
                method 'GET'
                urlPath("/private/backups/") {
                    queryParameters {
                        parameter 'page': $(RequestPatterns.anyShortPositiveNumber(), p(0))
                        parameter 'size': $(RequestPatterns.anyShortPositiveNumber(), p(11))
                        parameter 'sort': $(anyNonEmptyString(), p("startTimestamp"))
                    }
                }
            }

            response {
                status 200
                body([

                        number          : fromRequest().query("page"),
                        numberOfElements: ResponsePatterns.anyShortPositiveNumber(),
                        totalElements   : ResponsePatterns.anyShortPositiveNumber(),
                        totalPages      : ResponsePatterns.anyShortPositiveNumber(),
                        size            : fromRequest().query("size"),
                        content         : [
                                [
                                        backupId      : anyNonEmptyString(),
                                        startTimestamp: anyIso8601WithOffset(),
                                        state         : anyOf("INPROGRESS", "SUCCESS", "ERROR"),
                                        size          : ResponsePatterns.anyShortPositiveNumber(),
                                        backupTarget  : [
                                                backupTargetId: anyNonEmptyString(),
                                                trigger       : $(ResponsePatterns.anyCronExpression()),
                                                targetType    : "FILESYSTEM",
                                                path          : anyNonEmptyString(),
                                                agent         : [
                                                        agentId   : anyNonEmptyString(),
                                                        note      : $(c(null), p(anyNonEmptyString())),
                                                        createDate: anyIso8601WithOffset(),
                                                ]
                                        ]
                                ]
                        ]
                ])
                headers {
                    contentType("application/json")
                }
            }
        }
]