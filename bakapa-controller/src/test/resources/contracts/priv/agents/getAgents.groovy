package contracts

import groovy.transform.Field


@Field static def TEST_AGENT_ID = "testAgentId-1"
@Field static def TEST_AGENT_ID_2 = "testAgentId-2"

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/private/agents/'

    }

    response {
        status 200
        body([
                [
                        agent              : [
                                agentId   : $(consumer(TEST_AGENT_ID), producer(regex("testAgentId.+"))),
                                note      : $(consumer(anyNonBlankString()), producer(null)),
                                createDate: anyIso8601WithOffset(),
                        ],
                        backupsTargetsCount: anyNumber(),
                        status             : anyOf("OFFLINE", "ONLINE")

                ],
                [
                        agent              : [
                                agentId   : $(consumer(TEST_AGENT_ID_2), producer(regex("testAgentId.+"))),
                                note      : $(consumer(anyNonBlankString()), producer(null)),
                                createDate: anyIso8601WithOffset(),
                        ],
                        backupsTargetsCount: anyNumber(),
                        status             : anyOf("OFFLINE", "ONLINE")
                ]

        ])



        headers {
            contentType("application/json")
        }
    }

}